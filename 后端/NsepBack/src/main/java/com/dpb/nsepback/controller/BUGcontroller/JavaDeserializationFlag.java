package com.dpb.nsepback.controller.BUGcontroller;

import java.io.Serializable;

public class JavaDeserializationFlag implements Serializable {

    public String flag;

    public JavaDeserializationFlag(String s) {
        this.flag = s;
    }

    @Override
    public String toString() {
        return  this.flag;
    }
}
