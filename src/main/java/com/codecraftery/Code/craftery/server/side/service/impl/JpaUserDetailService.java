package com.codecraftery.Code.craftery.server.side.service.impl;

import com.codecraftery.Code.craftery.server.side.model.SecurityUser;
import com.codecraftery.Code.craftery.server.side.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

/**
 * @author Natasa Todorov Markovic
 */
@Service
@RequiredArgsConstructor
public class JpaUserDetailService implements UserDetailsService {
    private final UserRepository userRepository;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {

        return userRepository.findByUsername(username)
                .map(SecurityUser::new)
                .orElseThrow(() -> new UsernameNotFoundException("Username not found: " + username));
    }
}


