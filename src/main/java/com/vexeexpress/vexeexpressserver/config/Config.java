package com.vexeexpress.vexeexpressserver.config;

import java.util.Objects;

public class Config {
    public String SecretKey;
    public int ExpirationTime;

    public Config() {
        this.SecretKey = System.getenv("SECRET_KEY") != null ? System.getenv("SECRET_KEY") : "SECRETKEY";;
        // Lấy giá trị ExpirationTime từ biến môi trường hoặc gán giá trị mặc định
        String expirationTimeStr = System.getenv("JWT_EXPIRATION_TIME");
        if (expirationTimeStr != null) {
            try {
                this.ExpirationTime = Integer.parseInt(expirationTimeStr);
            } catch (NumberFormatException e) {
                // Nếu không thể chuyển đổi, gán giá trị mặc định
                this.ExpirationTime = 1000 * 60 * 60; // 1 giờ
            }
        } else {
            // Gán giá trị mặc định
            this.ExpirationTime = 1000 * 60 * 60; // 1 giờ
        }
    }
}
