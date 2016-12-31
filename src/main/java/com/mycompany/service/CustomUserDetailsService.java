/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import com.mycompany.domain.Account;
import com.mycompany.repository.AccountRepository;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import org.springframework.security.core.GrantedAuthority;
 
@Service
public class CustomUserDetailsService implements UserDetailsService {

    
    @Autowired
    private AccountRepository userRepository;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Account user = userRepository.findByUsername(username);
        
        if (user == null) {
            throw new UsernameNotFoundException("No such user: " + username);
        }

//        List<GrantedAuthority> grantedAuthorities = new ArrayList<>();
//        for (String role : user.getRoles()){
//            grantedAuthorities.add(new SimpleGrantedAuthority(role));
//        }
        String role = user.getRole();
        
        if (role == null) {
            throw new UsernameNotFoundException("No such user (aka role): " + username);
        }

        return new org.springframework.security.core.userdetails.User(
                user.getUsername(), 
                user.getPassword(), 
                true,
                true,
                true,
                true,
                Arrays.asList(new SimpleGrantedAuthority(role)));
    }
}
