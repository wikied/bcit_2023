package com.springserver.api.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

@RestController
public class RoleRestController {

    @Autowired
    private RoleController roleController;


    @PostMapping("/role/create")
    public @ResponseBody String createRole(Authentication authentication, @RequestParam String name) {
        roleController.createRole(name, authentication.getName());
        return "Saved";
    }

    @PostMapping("/role/delete")
    public @ResponseBody String deleteRole(Authentication authentication, @RequestParam String id) {
        roleController.deleteRole(id, authentication.getName());
        return "Saved";
    }

}
