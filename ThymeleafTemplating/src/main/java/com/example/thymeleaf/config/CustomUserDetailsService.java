package com.example.thymeleaf.config;

import com.example.thymeleaf.dao.UserRepository;
import com.example.thymeleaf.entity.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class CustomUserDetailsService implements UserDetailsService {

    @Autowired
    UserRepository userRepository;
    @Override
    public UserDetails loadUserByUsername(String usernameOrEmail) throws UsernameNotFoundException {
        Optional<User> optionalUser = userRepository.findByUserNameOrEmail(usernameOrEmail,usernameOrEmail);
        optionalUser.orElseThrow(()->new UsernameNotFoundException("User not found"));
        return optionalUser.map(CustomUserDetails::new).get();
    }
}
