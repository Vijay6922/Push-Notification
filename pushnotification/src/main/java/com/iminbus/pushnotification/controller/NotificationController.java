package com.iminbus.pushnotification.controller;

import com.iminbus.pushnotification.entity.NotificationRequest;
import com.iminbus.pushnotification.entity.NotificationResponse;
import com.iminbus.pushnotification.service.FCMService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.concurrent.ExecutionException;

@RestController
public class NotificationController {

    @Autowired
    private FCMService fcmService;   // FCMService to handle Firebase Cloud Messaging


    // POST endpoint to send notification
    @PostMapping("/notification")
    public ResponseEntity sendNotification(@RequestBody NotificationRequest request) throws ExecutionException, InterruptedException {
        fcmService.sendMessageToToken(request);  // Send notification using FCMService and provided request
        return new ResponseEntity<>(new NotificationResponse(HttpStatus.OK.value(), "Notification has been sent."), HttpStatus.OK); // Return a response entity with a success message and HTTP status OK
    }

}
