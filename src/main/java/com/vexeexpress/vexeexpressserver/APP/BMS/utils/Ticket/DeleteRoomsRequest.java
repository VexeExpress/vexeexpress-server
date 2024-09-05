package com.vexeexpress.vexeexpressserver.APP.BMS.utils.Ticket;

import lombok.Data;

import java.util.List;
@Data
public class DeleteRoomsRequest {
    private List<Long> roomIds;
}
