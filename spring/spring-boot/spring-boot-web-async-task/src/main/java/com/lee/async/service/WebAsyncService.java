package com.lee.async.service;

import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
public class WebAsyncService {

    public String generateUUID() {
        return UUID.randomUUID().toString();
    }

}
