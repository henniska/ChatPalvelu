
package com.mycompany.config;
 
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;
import org.springframework.core.annotation.Order;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
 
@Profile("default")
@Configuration
@EnableWebSecurity
@Order(101)
public class DefaultSecurityConfiguration extends WebSecurityConfigurerAdapter {

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        
        http.csrf().disable();
        http.headers().frameOptions().sameOrigin();
        http.authorizeRequests()
                .antMatchers("/h2-console/*").permitAll()
                .antMatchers("/registration").permitAll()
                .antMatchers("/").permitAll()
                .antMatchers("/welcome").permitAll()
                .anyRequest().authenticated();
        http.formLogin()
                .permitAll()                    
                .and().logout()
                .permitAll();
    }
 
    @Autowired
    public void configureGlobal(AuthenticationManagerBuilder auth) throws Exception {
        auth
            .inMemoryAuthentication()
                .withUser("curly").password("curly").roles("USER");
    }
 
    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }
}
