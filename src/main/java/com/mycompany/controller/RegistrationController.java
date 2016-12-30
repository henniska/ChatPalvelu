/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.controller;

import com.mycompany.domain.Account;
import com.mycompany.domain.Role;
import com.mycompany.repository.AccountRepository;
import com.mycompany.repository.RoleRepository;
import java.util.ArrayList;
import java.util.List;
import javax.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
@RequestMapping("/registration")
public class RegistrationController {

    @Autowired
    private PasswordEncoder passwordEncoder;

    
    @Autowired
    private AccountRepository accountRepository;
    
    @Autowired
    private RoleRepository roleRepository;
    

    @RequestMapping(method = RequestMethod.GET)
    public String view() {
        return "registration";
    }
    
    @ModelAttribute
    public Account getAccount() {
        return new Account();
    }

    @RequestMapping(method = RequestMethod.POST)
    public String register(@Valid 
            @ModelAttribute Account user,
            BindingResult bindingResult) {
        
        if (bindingResult.hasErrors()) {
            return "registration";
        }

        user.setPassword(passwordEncoder.encode(user.getPassword()));
        List<Role> roles = new ArrayList<>();
        Role r = roleRepository.findByName("USER");
        if (r == null) {
            r = new Role();
            r.setName("USER");
            roleRepository.save(r);
        }
        roles.add(r);
        user.setRoles(roles);
        accountRepository.save(user);
        return "redirect:/login";
    }

}
