package com.tdtu.backend_android.model;

import javax.persistence.*;
import lombok.Data;

@Entity
@Table
@Data
public class Topic {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;
}
