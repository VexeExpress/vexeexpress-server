package com.vexeexpress.vexeexpressserver.APP.BMS.libs;

import lombok.Getter;

@Getter
public enum ErrorMessage {
    NO_USER_FOUND("Không tìm thấy người dùng"),
    ACCOUNT_IS_LOCKED("Tài khoản bị khóa"),
    CANT_GENERATE_LOGIN_TOKEN("Không tạo được login token"),
    ERROR_IN_SERVER("Lỗi từ server");

    public final String message;

    ErrorMessage(String message) {
        this.message = message;
    }
}
