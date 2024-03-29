package com.codecraftery.Code.craftery.server.side.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotEmpty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

/**
 * @author Natasa Todorov Markovic
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Entity
@Table
public class Role {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @NotEmpty
    @Column(unique = true)
    private String name;

    @ManyToMany(mappedBy = "roles")
    private List<User> users;
}
