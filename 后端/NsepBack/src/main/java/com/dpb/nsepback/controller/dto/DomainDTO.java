package com.dpb.nsepback.controller.dto;

import lombok.Data;

@Data
public class DomainDTO {
    private String ID;
    private String SubDomain;
    public DomainDTO(String ID,String SubDomain){
        this.ID = ID;
        this.SubDomain = SubDomain;
    }
}
