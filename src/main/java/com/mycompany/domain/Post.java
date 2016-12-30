
package com.mycompany.domain;

import java.util.Date;
import javax.persistence.Entity;
import javax.persistence.ManyToOne;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import org.springframework.data.jpa.domain.AbstractPersistable;
 
@Entity
public class Post extends AbstractPersistable<Long> {
 
    @ManyToOne
    private Account user;
    
    private String username;
    
    private String title;
    
    private String content;
 
    public Account getUser() {
        return user;
    }
 
    public void setUser(Account user) {
        this.user = user;
    }
 
    public String getContent() {
        return content;
    }
 
    public void setContent(String content) {
        this.content = content;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }
    
    
}
 
