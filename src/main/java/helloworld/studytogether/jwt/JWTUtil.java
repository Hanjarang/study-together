package helloworld.studytogether.jwt;

import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import org.springframework.stereotype.Component;

import java.util.Date;

@Component
public class JWTUtil {

    public final String secretKey = "your_secret_key"; // 비밀 키
    public final long accessTokenValidity = 60 * 60 * 10L; // 10시간 (초)
    public final long refreshTokenValidity = 60 * 60 * 24 * 7L; // 7일 (초)

    public String createJwt(String username, String role, long validityInSeconds) {
        Date now = new Date();
        Date expiryDate = new Date(now.getTime() + validityInSeconds * 1000); // 밀리초 변환

        return Jwts.builder()
                .setSubject(username)
                .claim("role", role) // 역할 정보 추가
                .setIssuedAt(now)
                .setExpiration(expiryDate)
                .signWith(SignatureAlgorithm.HS256, secretKey)
                .compact();
    }

    public String getUsernameFromToken(String token) {
        return Jwts.parser()
                .setSigningKey(secretKey)
                .parseClaimsJws(token.replace("Bearer ", ""))
                .getBody()
                .getSubject();
    }

    public boolean validateToken(String token) {
        // 여기서 유효성 검사 로직을 추가
        return true; // 간단히 true 반환 (실제 구현 필요)
    }
}
