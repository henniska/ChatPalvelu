
package com.mycompany.domain;

import javax.persistence.Entity;
import javax.persistence.OneToOne;
import org.springframework.data.jpa.domain.AbstractPersistable;

@Entity
public class Visits extends AbstractPersistable<Long> {
    
    @OneToOne
    private Forum forum;
    
    private String name;
    
    private int views;

    public Forum getForum() {
        return forum;
    }

    public void setForum(Forum forum) {
        this.forum = forum;
    }

    public int getViews() {
        return views;
    }

    public void setViews(int views) {
        this.views = views;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
    
    
}
