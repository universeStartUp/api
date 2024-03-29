package com.pe.unieventia.security.domain.service;

import com.pe.unieventia.security.domain.persistence.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UserService {
    private final UserRepository userRepository;

    public UserDetailsService userDetailsService() {
        return new UserDetailsService() {
            @Override
            public UserDetails loadUserByUsername(String username) {
                String[] emailParts = username.split("@");
                return userRepository
                        .findByEmail_LocalAndEmail_EmailDomain_Domain(emailParts[0], emailParts[1])
                        .orElseThrow(() -> new UsernameNotFoundException("User with email" + username + "doesn't exist"));
            }
        };
    }
}