
package com.mycompany.controller;

import javax.annotation.PostConstruct;
import com.mycompany.domain.Account;
import com.mycompany.repository.AccountRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class DefaultController {

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Autowired
    private AccountRepository userDetailsRepository;
 
//    @PostConstruct
//    public void init() {
//        if (userDetailsRepository.findByUsername("test") != null) {
//            return;
//        }
// 
//        Account user = new Account();
//        user.setUsername("test");
//        user.setPassword(passwordEncoder.encode("test"));
// 
//        user = userDetailsRepository.save(user);
//    }

//    @RequestMapping("*")
//    public String home() {
//        return "forum";
//    }
}
