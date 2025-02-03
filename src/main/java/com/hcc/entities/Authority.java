package com.hcc.entities;

public class Authority {
    private Long id;
    private String authority;
    private User user;

    public Authority() {
    }
    public Authority(String authority) {
        this.authority = authority;
    }
}
