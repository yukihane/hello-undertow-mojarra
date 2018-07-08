package com.github.yukihane.java.undertow.bean;

import javax.faces.bean.ManagedBean;

@ManagedBean
public class Hello {
    final String world = "Hello World!";

    public String getWorld() {
        return world;
    }

}
