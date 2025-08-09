package com.springboot.authentica.service;

import com.springboot.authentica.entity.User;
import com.springboot.authentica.repository.UserRepo;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.ArrayList;

@Service
@RequiredArgsConstructor
public class UserDetailsService implements org.springframework.security.core.userdetails.UserDetailsService {

    private final UserRepo userRepo;

    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        User existUser = userRepo.findByEmail(email)
                .orElseThrow(() -> new UsernameNotFoundException("Email not exists" + email));
        return new org.springframework.security.core.userdetails.User(existUser.getEmail(), existUser.getPassword(), new ArrayList<>());
    }
}
