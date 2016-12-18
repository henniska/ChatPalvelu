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
import com.mycompany.repository.PostRepository;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import static javax.persistence.TemporalType.TIMESTAMP;
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
    
    @Autowired
    PostRepository postRepository;

    @RequestMapping(value="/forum/{title}", method = RequestMethod.GET)
    public String view(Model model, @PathVariable String title) {
        Forum f = forumRepository.findByTitle(title);
        model.addAttribute("forum", f);
        model.addAttribute("posts", f.getPosts());
        return "single";
    }
 
    @RequestMapping(value = "/forum/{title}", method = RequestMethod.POST)
    public String add(@PathVariable String title, @RequestParam String name, String content) {
        if (!title.trim().isEmpty()) {
            Forum f = forumRepository.findByTitle(title);
            Authentication auth = SecurityContextHolder.getContext().getAuthentication();
            String user = auth.getName();
            Account a = accountRepository.findByUsername(user);
            
            Post p = new Post();
            p.setUser(a);
            p.setTitle(name);
            p.setContent(content);
            p.setUsername(user);
            postRepository.save(p);
            
            List<Post> list = f.getPosts();
            list.add(p);
            forumRepository.save(f);

            return "redirect:/forum/" + f.getTitle();
        }
        return "redirect:/forum/" + forumRepository.findByTitle(title).getTitle();
    }


}
