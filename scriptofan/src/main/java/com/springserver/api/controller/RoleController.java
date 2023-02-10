package com.springserver.api.controller;

import com.springserver.api.model.Role;
import com.springserver.api.repository.RoleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.time.Instant;
import java.util.Optional;

@Component
public class RoleController {

    @Autowired
    private RoleRepository roleRepository;

    public void createRole(String roleName, String createdBy) {
        Role role = new Role();
        role.setRoleName(roleName);
        role.setCreatedBy(createdBy);

        roleRepository.save(role);
    }

    public void deleteRole(String id, String deletedBy) {
        Optional<Role> roleQuery = roleRepository.findById(id);
        if (roleQuery.isPresent()) {
            Role role = roleQuery.get();
            role.setDeleteTime(Instant.now());
            role.setDeletedBy(deletedBy);
            roleRepository.save(role);
        }
    }
}
