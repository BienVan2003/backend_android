package com.tdtu.backend_android.model;


import lombok.Data;

import javax.persistence.*;

@Entity
@Table
@Data
public class Account {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long account_id;
    private String email;
    private String username;
    private String password;
    private String avatar;

}
