package com.dpb.nsepback.controller.dto;

import lombok.Data;

@Data
public class OPDTO {
    private String username;
    private String phone;
    private String add;

    public OPDTO(String username, String phone, String add){
        this.username = username;
        this.phone = phone;
        this.add = add;
    }
}
