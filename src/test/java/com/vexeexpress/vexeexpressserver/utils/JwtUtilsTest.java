package com.vexeexpress.vexeexpressserver.utils;

import com.vexeexpress.vexeexpressserver.APP.BMS.utils.JwtUtils;
import org.junit.Test;

import static org.junit.Assert.*;

public class JwtUtilsTest {

    @Test
    public void testGenerateToken() {
        String username = "testuser";
        String token = JwtUtils.GenerateToken(username);
        assertNotEquals(token, "", "Token should not be emp");
    }

    @Test
    public void testCheckValidLoginToken() {
        String username = "testuser";
        String token = JwtUtils.GenerateToken(username);

        // Token hợp lệ
        assertTrue("Token should be valid", JwtUtils.CheckValidLoginToken(token));
    }
}
