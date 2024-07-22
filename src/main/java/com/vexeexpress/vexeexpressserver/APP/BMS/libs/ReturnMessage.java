package com.vexeexpress.vexeexpressserver.APP.BMS.libs;

import lombok.Getter;

@Getter
public enum ReturnMessage {
    LOGIN_SUCCESS("Đăng nhập thành công!"),
    INVALID_LOGIN_INFORMATION("Thông tin đăng nhập không hợp lệ. Vui lòng thử lại!"),
    ACCOUNT_DOESNT_EXIST("Tài khoản không tồn tại!"),
    CANT_GENERATE_LOGIN_TOKEN("Không tạo được login token từ server!"),
    ERROR_IN_SERVER("Hệ thống đã xảy ra lỗi!"),
    NO_LOGIN_TOKEN("Không tìm thấy login token!"),
    INVALID_TOKEN("Token login không hợp lệ!"),
    LOGOUT_SUCCESS("Đăng xuất thành công!");

    public final String message;

    ReturnMessage(String message) {
        this.message = message;
    }
}
