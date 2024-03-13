package com.iminbus.pushnotification.controller;

import com.iminbus.pushnotification.entity.NotificationRequest;
import com.iminbus.pushnotification.service.PushNotificationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class PushNotificationController {

    @Autowired
    private PushNotificationService pushNotificationService;

    @PostMapping("/send-notification")
    public void sendPushNotification(@RequestBody NotificationRequest request) {
        pushNotificationService.sendPushNotification(request.getDeviceToken(), request.getTitle(), request.getBody());
    }
}
