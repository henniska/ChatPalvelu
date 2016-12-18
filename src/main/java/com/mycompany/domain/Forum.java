/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.domain;

import java.util.List;
import javax.persistence.Entity;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import org.springframework.data.jpa.domain.AbstractPersistable;

@Entity
public class Forum extends AbstractPersistable<Long> {
    
    @ManyToOne
    private Account userWhoCreated;
    
    private String title;
    
    @OneToMany
    private List<Post> posts;

    public Account getUserWhoCreated() {
        return userWhoCreated;
    }

    public void setUserWhoCreated(Account userWhoCreated) {
        this.userWhoCreated = userWhoCreated;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public List<Post> getPosts() {
        return posts;
    }

    public void setPosts(List<Post> posts) {
        this.posts = posts;
    }
    
    
}
