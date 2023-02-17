package com.springserver.api.provider;

import com.springserver.api.model.User;
import com.springserver.api.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Component;

import java.util.Arrays;
import java.util.Optional;

@Component
public class UserAuthenticationProvider implements AuthenticationProvider {
    private BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();

    @Autowired
    private UserRepository userRepository;

    @Override
    public Authentication authenticate(Authentication authentication) throws AuthenticationException {
        String name = authentication.getName();
        String password = authentication.getCredentials().toString();

        Optional<User> user = userRepository.findByUserName(name);

        if (user.isEmpty()) {
            throw new BadCredentialsException("User not found.");
        } else if (passwordEncoder.matches(password, user.get().getUserPassword())) {

            String roleName = "user";
            if (user.get().getRole() != null && !user.get().getRole().isDeleted()) {
                roleName = user.get().getRole().getRoleName();
            }

            GrantedAuthority authority = new SimpleGrantedAuthority("ROLE_" + roleName);

            return new UsernamePasswordAuthenticationToken(name, password, Arrays.asList(authority));
        } else {
            throw new BadCredentialsException("Invalid Password.");
        }
    }

    @Override
    public boolean supports(Class<?> authentication) {
        return authentication.equals(UsernamePasswordAuthenticationToken.class);
    }
}
