
package com.mycompany.controller;

import com.mycompany.domain.Account;
import com.mycompany.domain.Forum;
import com.mycompany.domain.Post;
import com.mycompany.repository.AccountRepository;
import com.mycompany.repository.ForumRepository;
import com.mycompany.repository.PostRepository;
import com.mycompany.service.VisitsService;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.annotation.Secured;
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
    VisitsService visitsService;
    
    @Autowired
    AccountRepository accountRepository;
    
    @Autowired
    ForumRepository forumRepository;
    
    @Autowired
    PostRepository postRepository;

    @RequestMapping(value="/forum/{title}", method = RequestMethod.GET)
    public String view(Model model, @PathVariable String title) {
        Forum f = forumRepository.findByTitle(title);
        visitsService.increaseViews(title);
        model.addAttribute("visits", visitsService.getViews(title));
        model.addAttribute("forum", f);
        model.addAttribute("posts", f.getPosts());
        return "single";
    }
 
    @RequestMapping(value = "/forum/{title}", method = RequestMethod.POST)
    public String add(@PathVariable String title, @RequestParam String name, String content) {
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
        visitsService.decreaseViews(title);
        
        return "redirect:/forum/" + title;
    }
    
    @Secured("ADMIN")
    @RequestMapping(value = "/forum/{title}/{id}", method = RequestMethod.POST)
    public String delete(@PathVariable String title, @PathVariable Long id) {

        Forum f = forumRepository.findByTitle(title);
        List<Post> list = f.getPosts();
        for (int i = 0; i < list.size(); i++) {
            Post p = list.get(i);
            if (p == postRepository.findOne(id)) {
                list.remove(p);
                break;
            }
        }
        f.setPosts(list);
        forumRepository.save(f);
        postRepository.delete(id);
        visitsService.decreaseViews(title);
        return "redirect:/forum/" + title;
    }

    
}
