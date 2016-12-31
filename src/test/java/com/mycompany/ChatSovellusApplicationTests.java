package com.mycompany;

import com.mycompany.domain.Account;
import com.mycompany.domain.Forum;
import com.mycompany.domain.Post;
import com.mycompany.domain.Visits;
import com.mycompany.repository.AccountRepository;
import com.mycompany.repository.ForumRepository;
import com.mycompany.repository.PostRepository;
import com.mycompany.service.VisitsService;
import com.mycompany.repository.VisitsRepository;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest
public class ChatSovellusApplicationTests {

    @Test
    public void contextLoads() {
    }
    
    @Autowired
    private VisitsService visitsService;

    @Autowired
    private VisitsRepository visitsRepository;
    
    @Autowired
    private PasswordEncoder passwordEncoder;

    @Autowired
    private AccountRepository userRepository;
    
    @Autowired
    ForumRepository forumRepository;
    
    @Autowired
    PostRepository postRepository;

    @Test
    public void testViews() {

        
        Account a = new Account();
        a.setUsername("testUser");
        a.setPassword(passwordEncoder.encode("testPassword"));
        a.setRoles(Arrays.asList("USER"));
        userRepository.save(a);
        
        Post p = new Post();
        p.setContent("content");
        p.setTitle("title");
        p.setUser(a);
        p.setUsername(a.getUsername());
        postRepository.save(p);
        
        List<Post> posts = new ArrayList<>();
        posts.add(p);
        
        Forum f = new Forum();
        f.setUserWhoCreated(a);
        f.setTitle("tests");
        f.setPosts(posts);
        forumRepository.save(f);
        
        Visits v = new Visits();
        v.setViews(2);
        v.setName("tests");
        v.setForum(f);
        visitsRepository.save(v);

        visitsService.increaseViews("tests");
        visitsService.increaseViews("tests");

        int retrieved = visitsService.getViews("tests");

        assertNotNull(retrieved);
        assertEquals(4, retrieved);
    }

}
