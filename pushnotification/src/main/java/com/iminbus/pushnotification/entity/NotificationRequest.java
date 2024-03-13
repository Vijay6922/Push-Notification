package com.iminbus.pushnotification.entity;

import lombok.*;

@Data
public class NotificationRequest {
    private String title;
    private String body;
    private String topic;
    private String token;
}
