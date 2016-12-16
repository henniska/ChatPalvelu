
package com.mycompany.domain;

import javax.persistence.Entity;
import javax.persistence.ManyToOne;
import org.springframework.data.jpa.domain.AbstractPersistable;
 
@Entity
public class Message extends AbstractPersistable<Long> {
 
    @ManyToOne
    private Account user;
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
}
 
