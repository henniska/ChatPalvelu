
package com.mycompany.repository;
 
import org.springframework.data.jpa.repository.JpaRepository;
import com.mycompany.domain.Account;
 
public interface AccountRepository extends JpaRepository<Account, Long> {
    
    Account findByUsername(String username);
}
