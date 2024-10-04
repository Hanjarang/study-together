package helloworld.studytogether.controller;

import helloworld.studytogether.jwt.JWTUtil;
import helloworld.studytogether.service.TokenBlacklistService;
import helloworld.studytogether.dto.CustomUserDetails;
import helloworld.studytogether.dto.LoginDTO;
import helloworld.studytogether.entity.User;
import helloworld.studytogether.service.AuthService;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/auth")
public class AuthController {

    private final AuthService authService;
    private final JWTUtil jwtUtil;
    private final TokenBlacklistService tokenBlacklistService;

    public AuthController(AuthService authService, JWTUtil jwtUtil, TokenBlacklistService tokenBlacklistService) {
        this.authService = authService;
        this.jwtUtil = jwtUtil;
        this.tokenBlacklistService = tokenBlacklistService;
    }

    @PostMapping("/login")
    public ResponseEntity<String> login(@RequestBody LoginDTO loginDTO) {
        System.out.println("Login attempt with username: " + loginDTO.getUsername());
        return authService.login(loginDTO);
    }

    @PostMapping("/logout")
    public ResponseEntity<?> logout(@RequestHeader("Authorization") String token) {
        tokenBlacklistService.addToBlacklist(token);
        return ResponseEntity.ok("로그아웃 성공");
    }


}
