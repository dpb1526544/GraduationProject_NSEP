package com.dpb.nsepback.controller.ToolsController.SubDomain;

public class Res {
    private String name ;
    private String info ;

    public Res(String name, String info) {
        this.name = name;
        this.info = info;
    }

    public Res() {
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getInfo() {
        return info;
    }

    public void setInfo(String info) {
        this.info = info;
    }
    public String toString(){
        return this.name+this.info;
    }
}
