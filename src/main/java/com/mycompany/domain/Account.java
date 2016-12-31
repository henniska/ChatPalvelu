
package com.mycompany.domain;

import java.util.List;
import javax.persistence.Column;
import javax.persistence.ElementCollection;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToMany;
import org.hibernate.validator.constraints.NotBlank;
import org.springframework.data.jpa.domain.AbstractPersistable;
 
@Entity
public class Account extends AbstractPersistable<Long> {

    @Column(unique=true)
    @NotBlank
    private String username;
    
    @NotBlank
    private String password;
    
    @Column(nullable = true)
    @ElementCollection(fetch=FetchType.EAGER)
    private List<String> roles;
 
    public String getUsername() {
        return username;
    }
 
    public void setUsername(String username) {
        this.username = username;
    }
 
    public String getPassword() {
        return password;
    }
 
    public void setPassword(String password) {
        this.password = password;
    }
    
    public List<String> getRoles() {
        return roles;
    }

    public void setRoles(List<String> roles) {
        this.roles = roles;
    }

 
}
