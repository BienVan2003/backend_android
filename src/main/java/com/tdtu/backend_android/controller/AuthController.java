package com.tdtu.backend_android.controller;

import com.tdtu.backend_android.model.Account;
import com.tdtu.backend_android.payload.request.SignInRequest;
import com.tdtu.backend_android.payload.request.SignUpRequest;
import com.tdtu.backend_android.payload.response.MessageResponse;
import com.tdtu.backend_android.service.AccountService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

@RestController
public class AuthController {
    @Autowired
    BCryptPasswordEncoder passwordEncoder;
    @Autowired
    AccountService accountService;
    @PostMapping("/signup")
    public ResponseEntity<?> signUp(@RequestBody @Valid SignUpRequest request, BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            return ResponseEntity
                    .ok(new MessageResponse(1, bindingResult.getAllErrors().get(0).getDefaultMessage(), null));
        }
        try {
            Account account = accountService.findByEmail(request.getEmail());
            if(account == null){
                String[] arr = request.getEmail().split("@");
                Account newAccount = new Account();
                newAccount.setUsername(arr[0]);
                newAccount.setEmail(request.getEmail());
                newAccount.setPassword(passwordEncoder.encode(request.getPassword()));
                accountService.save(newAccount);
                return ResponseEntity.ok(new MessageResponse(0, "Đăng ký thành công", null));
            }
            return ResponseEntity.ok(new MessageResponse(0, "Email đã tồn tại", null));
        }catch (Exception e){
            return ResponseEntity.ok(new MessageResponse(1, "Đăng ký lỗi", null));
        }

    }

    @PostMapping("/signin")
    public ResponseEntity<?> logIn(@RequestBody SignInRequest request) {
        try {
            Account account = accountService.findByEmail(request.getEmail());
            if(account != null && passwordEncoder.matches(request.getPassword(), account.getPassword())){
                return ResponseEntity.ok(new MessageResponse(0, "Đăng nhập thành công", null));
            }
            return ResponseEntity.ok(new MessageResponse(1, "Tài khoản hoặc mật khẩu sai", null));
        }catch (Exception e){
            return ResponseEntity.ok(new MessageResponse(1, "Đăng nhập lỗi", null));
        }

    }
}
