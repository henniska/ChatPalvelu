
package com.mycompany.controller;

import com.mycompany.domain.Account;
import com.mycompany.domain.Forum;
import com.mycompany.domain.Post;
import com.mycompany.domain.Visits;
import com.mycompany.repository.AccountRepository;
import com.mycompany.repository.ForumRepository;
import com.mycompany.repository.VisitsRepository;
import java.util.ArrayList;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
@RequestMapping("/forum")
public class ForumController {
    
    @Autowired
    VisitsRepository visitsRepository;
    
    @Autowired
    AccountRepository accountRepository;
    
    @Autowired
    ForumRepository forumRepository;

    @RequestMapping(method = RequestMethod.GET)
    public String view(Model model) {
        model.addAttribute("forums", forumRepository.findAll());
        return "forum";
    }
 
    @RequestMapping(method = RequestMethod.POST)
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
            
            Visits v = new Visits();
            v.setForum(f);
            v.setName(title.trim());
            v.setViews(0);
            visitsRepository.save(v);
            return "redirect:/forum/" + f.getTitle();
        }
        System.out.println("SOMETHING WRONG HERE");
        return "redirect:/forum";
    }


}
