
package com.mycompany.domain;

import javax.persistence.Entity;
import org.hibernate.validator.constraints.NotBlank;
import org.springframework.data.jpa.domain.AbstractPersistable;
 
@Entity
public class Account extends AbstractPersistable<Long> {

    @NotBlank
    private String username;
    
    @NotBlank
    private String password;
 
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
 
}
