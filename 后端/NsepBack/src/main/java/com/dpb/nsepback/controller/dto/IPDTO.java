package com.dpb.nsepback.controller.dto;

import lombok.Data;

@Data
public class IPDTO {
    private String ip;
    private String country;
    private String country_code;
    private String province;
    private String city;
    private String distinct;
    private String isp;
    private String operator;
    private String lon;
    private String lat;
    private String net_str;
}
