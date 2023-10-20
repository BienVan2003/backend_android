package com.tdtu.backend_android.service;

import com.tdtu.backend_android.config.CustomUserDetails;
import com.tdtu.backend_android.model.Account;
import com.tdtu.backend_android.repository.AccountRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.ArrayList;

@Service
public class UserService implements UserDetailsService {
    @Autowired
    private AccountRepository accountRepository;

    @Autowired
    private BCryptPasswordEncoder bCryptPasswordEncoder;
    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Account user = accountRepository.findByUsername(username);
        if (user == null) {
            throw new UsernameNotFoundException("User not found with username: " + username);
        }
        return new org.springframework.security.core.userdetails.User(
                user.getUsername(), user.getPassword(), new ArrayList<>()
        );
    }

    public UserDetails loadUserById(Long id){
        Account account = accountRepository.findById(id).orElse(null);
        return new CustomUserDetails(account);
    }
    public boolean isValidUser(String username, String password) {
        Account user = accountRepository.findByUsername(username);
        return user != null && bCryptPasswordEncoder.matches(password, user.getPassword());
    }
}
