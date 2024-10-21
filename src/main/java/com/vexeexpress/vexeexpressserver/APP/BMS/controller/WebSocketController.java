package com.vexeexpress.vexeexpressserver.APP.BMS.controller;

import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;

@Controller
@CrossOrigin(origins = "http://localhost:3000")
public class WebSocketController {
    @MessageMapping("/send")
    @SendTo("/topic/item-updates")
    public ItemUpdate handleItemSelection(ItemUpdate itemUpdate) {
        System.out.println("Item selected: " + itemUpdate.getItemId()); // Log message for debugging
        return itemUpdate;
    }


    public static class ItemUpdate {
        private int itemId;
        private Boolean status;
        private String username;
        private Long userId;

        // Getters and setters...
        public int getItemId() {
            return itemId;
        }

        public void setItemId(int itemId) {
            this.itemId = itemId;
        }

        public Boolean getStatus() {
            return status;
        }

        public void setStatus(Boolean status) {
            this.status = status;
        }




        public String getUsername() {
            return username;
        }

        public void setUsername(String username) {
            this.username = username;
        }


        public Long getUserId() {
            return userId;
        }

        public void setUserId(Long userId) {
            this.userId = userId;
        }
    }
}
