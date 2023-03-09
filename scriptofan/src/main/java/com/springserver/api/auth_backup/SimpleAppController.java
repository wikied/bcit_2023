package com.okta.springbootwithauth;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

final AdminService adminService;

@Autowired
SimpleAppController(AdminService adminService) {
    this.adminService = adminService;
}

@RequestMapping("/admin")  
String admin() {  
    adminService.ensureAdmin();  
    return "admin";  
}

@Controller
class SimpleAppController {
    
    @RequestMapping("/restricted")
    String restricted() {
        return "restricted";
    }

}