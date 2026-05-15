package com.chingon.FoodStorageApp.identity.service;

import com.chingon.FoodStorageApp.identity.entity.User;
import com.chingon.FoodStorageApp.identity.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class CustomUserDetailsService implements UserDetailsService {

    private final UserRepository userRepository;

    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        User user = userRepository.findByEmail(email)
                .orElseThrow(() -> new UsernameNotFoundException("User with email %s not found!".formatted(email)));

        System.out.println(user);

        return org.springframework.security.core.userdetails.User
                .withUsername(email)
                .password(user.getPasswordHash())
                .roles(user.getSecRole().name())
                .build();
    }
}
