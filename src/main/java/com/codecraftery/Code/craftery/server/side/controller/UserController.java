package com.codecraftery.Code.craftery.server.side.controller;

import com.codecraftery.Code.craftery.server.side.model.User;
import com.codecraftery.Code.craftery.server.side.service.impl.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

/**
 * @author Natasa Todorov Markovic
 */
@RestController
@RequiredArgsConstructor
@RequestMapping("/api")
public class UserController {
    private final UserService userService;
    @PostMapping("/smt")
    public void saveAdmin(@RequestBody User user){
        System.out.println("Bla");
        userService.addAdmin(user);
    }

}


