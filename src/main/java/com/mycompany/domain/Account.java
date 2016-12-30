
package com.mycompany.domain;

import java.util.List;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.ManyToMany;
import org.hibernate.validator.constraints.NotBlank;
import org.springframework.data.jpa.domain.AbstractPersistable;
 
@Entity
public class Account extends AbstractPersistable<Long> {

    @NotBlank
    private String username;
    
    @NotBlank
    private String password;
    
    @ManyToMany(fetch = FetchType.EAGER)
    private List<Role> roles;
 
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
    
    public List<Role> getRoles() {
        return roles;
    }

    public void setRoles(List<Role> roles) {
        this.roles = roles;
    }

 
}
