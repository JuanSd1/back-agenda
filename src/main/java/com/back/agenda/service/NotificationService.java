package com.back.agenda.service;

public interface NotificationService {

    void sendNotification(String recipientEmail, String subject, String messageBody);

}
