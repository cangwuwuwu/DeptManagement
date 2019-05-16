package com.cangwu.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;

/**
 * @Author: Cangwu
 * @Date: 2019/5/9 7:40
 */
public class SecurityConfig extends WebSecurityConfigurerAdapter {

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http
            .authorizeRequests()
                .antMatchers("/submit").permitAll()
                .antMatchers("/delete**").hasRole("ADMIN")
                .anyRequest().authenticated()
            .and()
            .formLogin()
                .loginPage("/toLogin")
                .loginProcessingUrl("/submit")
                .failureForwardUrl("/submit?error")
                .successForwardUrl("/index")
                .permitAll()
            .and()
                //logout
            .logout()
                .logoutUrl("/logout")
                .logoutSuccessUrl("/submit?logout")
                .invalidateHttpSession(true)
            .and()
                .exceptionHandling()
                .accessDeniedPage("/accessError")
            .and()
                .headers().frameOptions().disable()
            .and()
                .csrf().disable();

    }

    @Autowired
    private UserDetailService userDetailService;

    @Override
    public void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.userDetailsService(userDetailService);
    }
}
