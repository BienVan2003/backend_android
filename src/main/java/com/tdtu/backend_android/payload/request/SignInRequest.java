package com.tdtu.backend_android.payload.request;

import lombok.Data;

@Data
public class SignInRequest {
    private String username;
    private String password;
}
