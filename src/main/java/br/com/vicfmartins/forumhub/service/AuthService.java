package br.com.vicfmartins.forumhub.service;

import br.com.vicfmartins.forumhub.domain.ForumUser;
import br.com.vicfmartins.forumhub.dto.AuthRequest;
import br.com.vicfmartins.forumhub.dto.RegisterRequest;
import br.com.vicfmartins.forumhub.dto.TokenResponse;
import br.com.vicfmartins.forumhub.repository.ForumUserRepository;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class AuthService {

    private final ForumUserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private final AuthenticationManager authenticationManager;
    private final JwtService jwtService;

    public AuthService(
            ForumUserRepository userRepository,
            PasswordEncoder passwordEncoder,
            AuthenticationManager authenticationManager,
            JwtService jwtService
    ) {
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
        this.authenticationManager = authenticationManager;
        this.jwtService = jwtService;
    }

    public TokenResponse register(RegisterRequest request) {
        if (userRepository.existsByEmailIgnoreCase(request.email())) {
            throw new IllegalArgumentException("Ja existe um usuario cadastrado com este email.");
        }

        ForumUser user = new ForumUser(
                request.name(),
                request.email(),
                passwordEncoder.encode(request.password()),
                "ROLE_USER"
        );

        ForumUser savedUser = userRepository.save(user);
        String token = jwtService.generateToken(savedUser.getEmail());
        return new TokenResponse(token, "Bearer");
    }

    public TokenResponse login(AuthRequest request) {
        authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(request.email(), request.password())
        );

        String token = jwtService.generateToken(request.email());
        return new TokenResponse(token, "Bearer");
    }
}
