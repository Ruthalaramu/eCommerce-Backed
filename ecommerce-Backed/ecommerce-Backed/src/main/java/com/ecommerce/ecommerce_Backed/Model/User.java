package com.ecommerce.ecommerce_Backed.Model;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

@Entity
@Getter
@Setter
@Table(name = "user")
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;
    private String password;
    private String email;
    @Column(unique = true)
    private String mobileNumber;
    @Column(name = "is_active", columnDefinition = "TINYINT(1)")
    private Boolean  isActive;
    private String role; // USER or ADMIN
    @OneToMany(mappedBy = "user",cascade = CascadeType.ALL)
    private List<Address>address= new ArrayList<>();
}
