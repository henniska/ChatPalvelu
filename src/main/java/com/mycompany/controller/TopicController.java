/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.controller;

import com.mycompany.domain.Account;
import com.mycompany.domain.Forum;
import com.mycompany.domain.Post;
import com.mycompany.repository.AccountRepository;
import com.mycompany.repository.ForumRepository;
import java.util.ArrayList;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class TopicController {
    
    @Autowired
    AccountRepository accountRepository;
    
    @Autowired
    ForumRepository forumRepository;

    @RequestMapping(value="/forum/{title}", method = RequestMethod.GET)
    public String view(Model model, @PathVariable String title) {
        model.addAttribute("forum", forumRepository.findByTitle(title));
        return "single";
    }
 
    @RequestMapping(value = "/forum/{title}", method = RequestMethod.POST)
    public String add(@RequestParam String title) {
        if (!title.trim().isEmpty()) {
            Forum f = new Forum();
            Authentication auth = SecurityContextHolder.getContext().getAuthentication();
            String name = auth.getPrincipal().toString();
                    
            f.setTitle(title.trim());
            f.setUserWhoCreated(accountRepository.findByUsername(name));
            f.setPosts(new ArrayList<Post>());
            forumRepository.save(f);
            forumRepository.flush();
            return "redirect:/forum/" + f.getTitle();
        }
        System.out.println("SOMETHING WRONG HERE");
        return "redirect:/forum";
    }


}
