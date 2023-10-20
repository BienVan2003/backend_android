package com.tdtu.backend_android.controller;

import com.tdtu.backend_android.config.CustomUserDetails;
import com.tdtu.backend_android.config.JwtTokenProvider;
import com.tdtu.backend_android.model.Account;
import com.tdtu.backend_android.payload.request.SignInRequest;
import com.tdtu.backend_android.payload.request.SignUpRequest;
import com.tdtu.backend_android.payload.response.MessageResponse;
import com.tdtu.backend_android.service.AccountService;
import com.tdtu.backend_android.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.security.core.context.SecurityContextHolder;

import javax.validation.Valid;

@RestController
public class AuthController {
    @Autowired
    BCryptPasswordEncoder passwordEncoder;
    @Autowired
    AccountService accountService;
    @Autowired
    AuthenticationManager authenticationManager;
    @Autowired
    JwtTokenProvider jwtTokenProvider;
    @Autowired
    UserService userService;
    @PostMapping("/signup")
    public ResponseEntity<?> signUp(@RequestBody @Valid SignUpRequest request, BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            return ResponseEntity
                    .ok(new MessageResponse(1, bindingResult.getAllErrors().get(0).getDefaultMessage(), null));
        }
        try {
            Account account = accountService.findByEmail(request.getEmail());
            if(account == null){
                Account newAccount = new Account();
                newAccount.setUsername(request.getUsername());
                newAccount.setEmail(request.getEmail());
                newAccount.setPassword(passwordEncoder.encode(request.getPassword()));
                accountService.save(newAccount);
                return ResponseEntity.ok(new MessageResponse(0, "Đăng ký thành công", null));
            }
            return ResponseEntity.ok(new MessageResponse(0, "Email đã tồn tại", null));
        }catch (Exception e){
            return ResponseEntity.ok(new MessageResponse(1, "Đăng ký lỗi", e));
        }

    }

    @PostMapping("/signin")
    public ResponseEntity<?> logIn(@RequestBody SignInRequest loginRequest) {
        try {
            if (userService.isValidUser(loginRequest.getUsername(), loginRequest.getPassword())) {
                UserDetails userDetails = userService.loadUserByUsername(loginRequest.getUsername());
                String jwt = jwtTokenProvider.generateToken(userDetails);
                return ResponseEntity.ok(new MessageResponse(0, "Đăng nhập thành công", jwt));
            } else {
                return ResponseEntity.ok(new MessageResponse(1, "Invalid username or password", null));
            }
//            return ResponseEntity.ok(new MessageResponse(0, "Đăng nhập thành công", jwt));

        }catch (Exception e){
            return ResponseEntity.ok(new MessageResponse(1, "Đăng nhập lỗi", e));
        }

    }
}
