
package com.mycompany.controller;

import javax.annotation.PostConstruct;
import com.mycompany.domain.Account;
import com.mycompany.domain.Role;
import com.mycompany.repository.AccountRepository;
import com.mycompany.repository.RoleRepository;
import java.util.ArrayList;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class DefaultController {
    
    @Autowired
    private PasswordEncoder passwordEncoder;

    @Autowired
    private AccountRepository userRepository;
    
    @Autowired
    private RoleRepository roleRepository;
 
    @PostConstruct
    public void init() {
        Role r1 = new Role();
        Role r2 = new Role();
        r1.setName("USER");
        r2.setName("ADMIN");
        roleRepository.save(r1);
        roleRepository.save(r2);

        
    /*ADMIN LOGIN CREDENTIALS*/
        Account a = new Account();
        a.setUsername("admin");
        a.setPassword(passwordEncoder.encode("admin"));
        List<Role> list = new ArrayList<>();
        list.add(r2);
        a.setRoles(list);
        userRepository.save(a);
    }

    @RequestMapping("*")
    public String doDefaultRedirect() {
        return "redirect:/forum";
    }
    
    @RequestMapping("/welcome")
    public String welcome() {
        return "welcome";
    }
    
    
}
