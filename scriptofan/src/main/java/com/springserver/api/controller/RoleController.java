package com.springserver.api.controller;

import com.springserver.api.model.Role;
import com.springserver.api.provider.ResourceNotFoundException;
import com.springserver.api.repository.RoleRepository;
import com.springserver.api.service.RoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
@RequestMapping("/role")
public class RoleController {

    @Autowired
    private RoleService roleService;

    @Autowired
    private RoleRepository roleRepository;


    @PostMapping
    public @ResponseBody Role createRole(Authentication authentication, @RequestParam String name) {
        return roleService.createRole(name, authentication.getName());
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteRole(Authentication authentication, @RequestParam String id) {
        Optional<Role> role = roleRepository.findById(id);
        if (role.isEmpty()) {
            throw new ResourceNotFoundException("Role", "id", id);
        }
        roleService.deleteRole(role.get(), authentication.getName());
        return ResponseEntity.ok().build();
    }

    @GetMapping
    public Iterable<Role> getAllRoles(Authentication authentication) {
        return roleRepository.findAll();
    }

    @PutMapping("/{id}")
    public Role updateRole(Authentication authentication, @PathVariable String id, @RequestBody Role roleRequest) {
        Optional<Role> role = roleRepository.findById(id);
        if (role.isEmpty()) {
            throw new ResourceNotFoundException("Role", "id", id);
        }

        return roleService.update(role.get(), roleRequest, authentication.getName());
    }

    @GetMapping("/{id}")
    public Role getRole(@PathVariable String id) {
        Optional<Role> role = roleRepository.findById(id);
        if (role.isEmpty()) {
            throw new ResourceNotFoundException("Role", "id", id);
        }

        return role.get();
    }
}
