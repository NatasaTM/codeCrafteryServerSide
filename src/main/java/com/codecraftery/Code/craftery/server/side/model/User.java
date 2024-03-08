package com.codecraftery.Code.craftery.server.side.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

/**
 * @author Natasa Todorov Markovic
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String username;
    private String password;

    public User(String username, String password, List<Role> roles) {
        this.username = username;
        this.password = password;
        this.roles = roles;
    }

    @ManyToMany(fetch = FetchType.EAGER) // Adjust fetch strategy as needed
    private List<Role> roles;


}
