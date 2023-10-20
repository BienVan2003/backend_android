package com.tdtu.backend_android.model;

import javax.persistence.*;
import lombok.Data;

@Entity
@Table
@Data
public class Folder {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long folder_id;
    private String name;
}
