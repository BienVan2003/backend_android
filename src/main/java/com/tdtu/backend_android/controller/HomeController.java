package com.tdtu.backend_android.controller;

import com.tdtu.backend_android.config.JwtAuthenticationFilter;
import com.tdtu.backend_android.config.JwtTokenProvider;
import com.tdtu.backend_android.payload.request.SignInRequest;
import com.tdtu.backend_android.payload.response.MessageResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;

@RestController
@RequestMapping("/home")
public class HomeController {
    @Autowired
    JwtAuthenticationFilter jwtAuthenticationFilter;
    @Autowired
    JwtTokenProvider jwtTokenProvider;
    @PostMapping
    public ResponseEntity<?> test(HttpServletRequest request) {
        try {
            Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
            String username = ((UserDetails) principal).getUsername();
            return ResponseEntity.ok(new MessageResponse(0, "Test", username));

        }catch (Exception e){
            return ResponseEntity.ok(new MessageResponse(1, "Đăng nhập lỗi", e));
        }

    }
}
