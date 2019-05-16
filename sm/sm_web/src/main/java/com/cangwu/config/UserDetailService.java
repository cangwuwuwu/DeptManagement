package com.cangwu.config;

import com.cangwu.entity.Staff;
import com.cangwu.service.SelfService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

/**
 * @Author: Cangwu
 * @Date: 2019/5/13 14:58
 */
@Component("userDetail")
public class UserDetailService implements UserDetailsService {

    @Autowired
    private SelfService selfService;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Staff user = selfService.findUserByAccount(username);
        if (user != null) {
            List<GrantedAuthority> authorities = new ArrayList<GrantedAuthority>();
            if ("cangwu".equals(user.getAccount())) {
                authorities.add(new SimpleGrantedAuthority("ROLE_ADMIN"));
            }
            authorities.add(new SimpleGrantedAuthority("ROLE_USER"));

            return new User(user.getAccount(),
                    user.getPassword(),
                    authorities);
        }

        throw  new UsernameNotFoundException(
                "User '" + username + "' not found.");
    }
}
