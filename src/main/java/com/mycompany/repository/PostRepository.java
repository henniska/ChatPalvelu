/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import com.mycompany.domain.Post;
 
public interface PostRepository extends JpaRepository<Post, Long> {
    
    Post findByTitle(String title);
}
