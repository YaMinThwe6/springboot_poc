package com.yamin.e_comm.service;

import com.yamin.e_comm.model.UserPrincipals;
import com.yamin.e_comm.model.Users;
import com.yamin.e_comm.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class MyUserDetailsService implements UserDetailsService {

    @Autowired
    private UserRepository repo;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Users user = repo.findByUsername(username);
        if (user == null) {
            System.out.println("User Not Found!");
            throw new UsernameNotFoundException("User Not found!");
        }
        return new UserPrincipals(user);
    }
}
