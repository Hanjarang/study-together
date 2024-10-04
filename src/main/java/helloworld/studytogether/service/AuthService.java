package helloworld.studytogether.service;

import helloworld.studytogether.dto.LoginDTO;
import helloworld.studytogether.dto.CustomUserDetails;
import helloworld.studytogether.entity.User;
import helloworld.studytogether.repository.UserRepository;
import helloworld.studytogether.jwt.JWTUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.stereotype.Service;

@Service
public class AuthService {

    private final AuthenticationManager authenticationManager;
    private final JWTUtil jwtUtil;
    private final TokenBlacklistService tokenBlacklistService;

    @Autowired
    private UserRepository userRepository;

    public AuthService(AuthenticationManager authenticationManager, UserDetailsService userDetailsService, JWTUtil jwtUtil, TokenBlacklistService tokenBlacklistService) {
        this.authenticationManager = authenticationManager;
        this.jwtUtil = jwtUtil;
        this.tokenBlacklistService = tokenBlacklistService;
    }

    public ResponseEntity<String> login(LoginDTO loginDTO) {
        try {
            // 사용자 인증
            Authentication authentication = authenticationManager.authenticate(
                    new UsernamePasswordAuthenticationToken(loginDTO.getUsername(), loginDTO.getPassword())
            );

            CustomUserDetails customUserDetails = (CustomUserDetails) authentication.getPrincipal();

            // JWT 생성
            String accessToken = jwtUtil.createJwt(customUserDetails.getUsername(), customUserDetails.getAuthorities().iterator().next().getAuthority(), jwtUtil.accessTokenValidity);
            String refreshToken = jwtUtil.createJwt(customUserDetails.getUsername(), customUserDetails.getAuthorities().iterator().next().getAuthority(), jwtUtil.refreshTokenValidity);

            // 토큰을 응답 본문에 포함하여 반환
            return ResponseEntity.ok("{\"accessToken\":\"Bearer " + accessToken + "\", \"refreshToken\":\"Bearer " + refreshToken + "\"}");
        } catch (Exception e) {
            return ResponseEntity.status(401).body("로그인 실패: " + e.getMessage());
        }
    }



}
