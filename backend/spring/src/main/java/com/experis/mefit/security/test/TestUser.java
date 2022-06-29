package com.experis.mefit.security.test;

import lombok.Data;

@Data
public class TestUser {

    private Long uid;
    private String name;
    private String email;
    private boolean isSuper;

}