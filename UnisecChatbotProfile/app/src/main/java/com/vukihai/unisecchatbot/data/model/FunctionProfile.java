package com.vukihai.unisecchatbot.data.model;

import java.util.Objects;

public class FunctionProfile {
    private String iconFunction;
    private String nameFunction;

    public FunctionProfile(String iconFunction, String nameFunction) {
        this.iconFunction = iconFunction;
        this.nameFunction = nameFunction;
    }

    public void setnameFunction(String nameFunction) {
        this.nameFunction = nameFunction;
    }

    public void seticonFunction(String iconFunction) {
          this.iconFunction = iconFunction;
    }

    public String geticonFunction() {
        return iconFunction;
    }

    public String getnameFunction() {
        return nameFunction;
    }
}
