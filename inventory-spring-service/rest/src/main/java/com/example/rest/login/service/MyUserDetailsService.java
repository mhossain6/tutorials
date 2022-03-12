package com.example.rest.login.service;

import com.example.rest.login.model.User;
import com.example.rest.login.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service("userDetailsService")
public class MyUserDetailsService implements UserDetailsService {

    @Autowired
    private UserRepository userRepository;

    public MyUserDetailsService() {
        super();
    }


    @Override
    public UserDetails loadUserByUsername(final String name) throws UsernameNotFoundException {

        try {
            final User user = userRepository.findUser(name);
            if (user == null) {
                throw new UsernameNotFoundException("No user found with username: " + name);
            }

            return new org.springframework.security.core.userdetails.User(user.getUserName(), user.getPassword(), true,
                    true, true, true, getGrantedAuthorities(user.getRole()));
        } catch (final Exception e) {
            throw new RuntimeException(e);
        }
    }

    private List<GrantedAuthority> getGrantedAuthorities(final String role) {
        final List<GrantedAuthority> authorities = new ArrayList<>();
        for (final String privilege : role.split(",")) {
            authorities.add(new SimpleGrantedAuthority(privilege));
        }
        return authorities;
    }

}
