package com.boiko.taisa.gallery.domain.entity;

public class UserResponseEntity {
    private String name;

    public UserResponseEntity(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
