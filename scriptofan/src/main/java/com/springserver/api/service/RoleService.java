package com.springserver.api.service;

import com.springserver.api.model.Role;
import com.springserver.api.repository.RoleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.time.Instant;
import java.util.Optional;

@Component
public class RoleService {

    @Autowired
    private RoleRepository roleRepository;

    public Role createRole(String roleName, String createdBy) {
        Role role = new Role();
        role.setRoleName(roleName);
        role.setCreatedBy(createdBy);

        return roleRepository.save(role);
    }

    public void deleteRole(Role role, String deletedBy) {
        role.setDeleteTime(Instant.now());
        role.setDeletedBy(deletedBy);
        roleRepository.save(role);
    }

    public Role update(Role role, Role roleRequest, String updatedBy) {
        role.setRoleName(roleRequest.getRoleName());
        role.setUpdatedBy(updatedBy);
        role.setUpdateTime(Instant.now());
        return roleRepository.save(role);
    }
}
