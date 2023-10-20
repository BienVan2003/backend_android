package com.tdtu.backend_android.payload.response;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class MessageResponse {
    private int code;
    private String message;
    private Object data;
}
