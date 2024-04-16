package com.vexeexpress.vexeexpressserver.entity;

import jakarta.persistence.ElementCollection;
import jakarta.persistence.MapKeyColumn;
import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Data
@Document(collection = "bms_bus_company")
public class BmsBusCompany {
    @Id
    private String id;
    private String companyName;
    private String address;
    private String phone;
    private Boolean activeStatus;
    private Date date;

    @Field("appName")
    private Map<String, Object> appName;

    public void addTicketManagement(boolean status, Date dateActive, String usedTime){
        Map<String, Object> ticketManagementInfo = new HashMap<>();
        ticketManagementInfo.put("status", status);
        ticketManagementInfo.put("dateActive", dateActive);
        ticketManagementInfo.put("usedTime", usedTime);
        appName.put("ticketManagement", ticketManagementInfo);
    }
    public void addDriverApp(boolean status, Date dateActive, String usedTime){
        Map<String, Object> driverAppInfo = new HashMap<>();
        driverAppInfo.put("status", status);
        driverAppInfo.put("dateActive", dateActive);
        driverAppInfo.put("usedTime", usedTime);
        appName.put("driverApp", driverAppInfo);
    }
    public void addTicketAgencyApp(boolean status, Date dateActive, String usedTime){
        Map<String, Object> ticketAgencyAppInfo = new HashMap<>();
        ticketAgencyAppInfo.put("status", status);
        ticketAgencyAppInfo.put("dateActive", dateActive);
        ticketAgencyAppInfo.put("usedTime", usedTime);
        appName.put("ticketAgencyApp", ticketAgencyAppInfo);
    }
    public void addCargoManagementApp(boolean status, Date dateActive, String usedTime){
        Map<String, Object> cargoManagementAppInfo = new HashMap<>();
        cargoManagementAppInfo.put("status", status);
        cargoManagementAppInfo.put("dateActive", dateActive);
        cargoManagementAppInfo.put("usedTime", usedTime);
        appName.put("cargoManagementApp", cargoManagementAppInfo);
    }
    public void addTicketSales(boolean status, Date dateActive, String usedTime){
        Map<String, Object> ticketSalesInfo = new HashMap<>();
        ticketSalesInfo.put("status", status);
        ticketSalesInfo.put("dateActive", dateActive);
        ticketSalesInfo.put("usedTime", usedTime);
        appName.put("ticketSales", ticketSalesInfo);
    }
}
