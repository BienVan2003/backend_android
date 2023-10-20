package com.tdtu.backend_android.service;

import com.tdtu.backend_android.model.Account;
import com.tdtu.backend_android.repository.AccountRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


import java.util.List;

@Service
public class AccountService implements CRUDService<Account>{

    @Autowired
    AccountRepository accountRepository;
    @Override
    public List<Account> findAll() {
        return accountRepository.findAll();
    }

    @Override
    public Account findById(Long id) {
        return accountRepository.findById(id).orElse(null);
    }

    @Override
    public Account save(Account entity) {
        return accountRepository.save(entity);
    }

    @Override
    public void delete(Long id) {
        accountRepository.deleteById(id);
    }
    public Account findByEmail(String email){
        return accountRepository.findByEmail(email);
    }
    public Account findByUsername(String username){
        return accountRepository.findByUsername(username);
    }
}
