
package com.mycompany.controller;

import javax.annotation.PostConstruct;
import com.mycompany.domain.Account;
import com.mycompany.repository.AccountRepository;
import java.util.Arrays;
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
    
    @PostConstruct
    public void init() {
        
    /*ADMIN LOGIN CREDENTIALS*/
        Account a = new Account();
        a.setUsername("admin");
        a.setPassword(passwordEncoder.encode("admin"));
        a.setRoles(Arrays.asList("ADMIN"));
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
