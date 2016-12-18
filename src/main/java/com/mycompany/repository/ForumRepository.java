
package com.mycompany.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import com.mycompany.domain.Forum;
 
public interface ForumRepository extends JpaRepository<Forum, Long> {
    
    Forum findByTitle(String title);
}
