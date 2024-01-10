package com.dpb.nsepback.controller.dto;

import lombok.Data;

@Data
public class PortDTO {
    private String ip;
    private String port;
    private String service;
    private String isOpen;
//    private String banner;
}
