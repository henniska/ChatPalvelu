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
import com.mycompany.domain.Role;
import com.mycompany.repository.AccountRepository;
import java.util.ArrayList;
import java.util.List;
import org.springframework.security.core.GrantedAuthority;
 
@Service
public class CustomUserDetailsService implements UserDetailsService {
 
//    @Autowired
//    private AccountRepository accountRepository;
// 
//    @Override
//    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
//        Account account = accountRepository.findByUsername(username);
//        if (account == null) {
//            throw new UsernameNotFoundException("No such user: " + username);
//        }
// 
//        return new org.springframework.security.core.userdetails.User(
//                account.getUsername(),
//                account.getPassword(),
//                true,
//                true,
//                true,
//                true,
//                Arrays.asList(new SimpleGrantedAuthority("USER")));
//    }
    
    @Autowired
    private AccountRepository userRepository;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Account user = userRepository.findByUsername(username);
        
        if (user == null) {
            throw new UsernameNotFoundException("No such user: " + username);
        }

        List<GrantedAuthority> grantedAuthorities = new ArrayList<>();
        List<Role> list = user.getRoles();
        for (int i = 0; i < list.size(); i++) {
            grantedAuthorities.add(new SimpleGrantedAuthority(list.get(i).getName()));
            
        }
//        for (Role role : user.getRoles()){
//            grantedAuthorities.add(new SimpleGrantedAuthority(role.getName()));
//        }

        return new org.springframework.security.core.userdetails.User(
                user.getUsername(), 
                user.getPassword(), 
                true,
                true,
                true,
                true,
                grantedAuthorities);
    }
}
