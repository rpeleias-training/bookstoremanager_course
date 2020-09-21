package com.rodrigopeleias.bookstoremanager.users.service;

import com.rodrigopeleias.bookstoremanager.users.dto.AuthenticatedUser;
import com.rodrigopeleias.bookstoremanager.users.dto.JwtRequest;
import com.rodrigopeleias.bookstoremanager.users.dto.JwtResponse;
import com.rodrigopeleias.bookstoremanager.users.entity.User;
import com.rodrigopeleias.bookstoremanager.users.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class AuthenticationService implements UserDetailsService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private JwtTokenManager jwtTokenManager;

    public JwtResponse createAuthenticationToken(JwtRequest jwtRequest) {
        String username = jwtRequest.getUsername();
        authenticate(username, jwtRequest.getPassword());

        UserDetails userDetails = this.loadUserByUsername(username);
        String token = jwtTokenManager.generateToken(userDetails);

        return JwtResponse.builder()
                .jwtToken(token)
                .build();
    }

    private Authentication authenticate(String username, String password) {
        return authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(username, password));
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        User user = userRepository.findByUsername(username)
                .orElseThrow(() -> new UsernameNotFoundException(String.format("User not found with username %s", username)));
        return new AuthenticatedUser(
                user.getUsername(),
                user.getPassword(),
                user.getRole().getDescription()
        );
    }
}
