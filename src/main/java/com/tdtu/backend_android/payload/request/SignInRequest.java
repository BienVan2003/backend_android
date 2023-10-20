package com.tdtu.backend_android.payload.request;

import lombok.Data;

@Data
public class SignInRequest {
    private String email;
    private String password;
}
