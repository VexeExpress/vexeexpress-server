package com.vexeexpress.vexeexpressserver.APP.BMS.utils;

import com.vexeexpress.vexeexpressserver.config.Config;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.SignatureException;
import io.jsonwebtoken.security.Keys;

import javax.crypto.SecretKey;
import java.util.Date;

public class JwtUtils {
    private static final SecretKey secretKey = Keys.secretKeyFor(SignatureAlgorithm.HS256);

    // Xác thực token
    private static Claims validateToken(String token) {
        try {
            Config config = new Config();

            return Jwts.parser()
                    .setSigningKey(secretKey) // Sử dụng khóa bí mật
                    .build()
                    .parseClaimsJws(token)
                    .getBody();
        } catch (SignatureException e) {
            // Token chữ ký không hợp lệ
            System.out.println("Invalid JWT signature");
        } catch (Exception e) {
            // Xử lý các lỗi khác như hết hạn, lỗi phân tích
            System.out.println("Invalid JWT token");
        }
        return null;
    }

    public static String GenerateToken(String username) {
        Config config = new Config();

        return Jwts.builder()
                .subject(username)
                .issuedAt(new Date())
                .expiration(new Date(System.currentTimeMillis() + config.ExpirationTime))
                .signWith(secretKey)
                .compact();
    }

    // Xác thực token
    public static boolean CheckValidLoginToken(String token) {
        Claims claims = validateToken(token);
        if (claims == null) {
            return false; // Token không hợp lệ
        }

        return !claims.getExpiration().before(new Date());
    }
}
