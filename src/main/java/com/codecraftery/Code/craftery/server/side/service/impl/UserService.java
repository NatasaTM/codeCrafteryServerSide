package com.codecraftery.Code.craftery.server.side.service.impl;

import com.codecraftery.Code.craftery.server.side.model.Role;
import com.codecraftery.Code.craftery.server.side.model.User;
import com.codecraftery.Code.craftery.server.side.repository.RoleRepository;
import com.codecraftery.Code.craftery.server.side.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

/**
 * @author Natasa Todorov Markovic
 */
@Service
@RequiredArgsConstructor
public class UserService {
    private final UserRepository userRepository;
    private final RoleRepository roleRepository;
    private final BCryptPasswordEncoder bCryptPasswordEncoder;



    public void addAdmin(User user){
        String username = user.getUsername();
        String password = bCryptPasswordEncoder.encode(user.getPassword());
        System.out.println("Password: " + password);
        Role role = roleRepository.findById(Long.valueOf(1)).get();
        System.out.println("Role: " + role.getName());
        List<Role> roles=new ArrayList<>();
        roles.add(role);


        User savedUser = new User(username,password,roles);
        userRepository.save(savedUser);

    }
}


