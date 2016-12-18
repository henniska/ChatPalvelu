
package com.mycompany.domain;

import java.security.Timestamp;
import java.util.Date;
import javax.persistence.Entity;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import org.springframework.data.jpa.domain.AbstractPersistable;
 
@Entity
public class Post extends AbstractPersistable<Long> {
 
    @ManyToOne
    private Account user;
    
    private String title;
    
    private String content;
    
    @Temporal(TemporalType.TIMESTAMP)
    private Date created;
 
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

    public Date getCreated() {
        return created;
    }

    public void setCreated(Date created) {
        this.created = created;
    }
    
    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }
}
 
