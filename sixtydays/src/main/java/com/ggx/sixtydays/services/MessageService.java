package com.ggx.sixtydays.services;

import org.springframework.stereotype.Service;

@Service
public class MessageService {

    private String userName;

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }
}
