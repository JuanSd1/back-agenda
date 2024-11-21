package com.back.agenda.service.impl;

import com.back.agenda.service.GmailSender;
import com.back.agenda.service.NotificationService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

@Service
public class NotificationServiceImpl implements NotificationService {

    private static final Logger logger = LoggerFactory.getLogger(NotificationServiceImpl.class);

    @Override
    public void sendNotification(String recipientEmail, String subject, String messageBody) {
        try {
            validateParameters(recipientEmail, subject, messageBody);
            GmailSender.sendEmail(recipientEmail, subject, messageBody);
            logger.info("Notificación enviada a: {}", recipientEmail);
        } catch (IllegalArgumentException e) {
            logger.error("Error de validación al enviar la notificación: {}", e.getMessage());
            throw e;
        } catch (Exception e) {
            logger.error("Error al enviar la notificación: {}", e.getMessage(), e);
            throw new RuntimeException("Error al enviar la notificación. Por favor, intente nuevamente.");
        }
    }

    private void validateParameters(String recipientEmail, String subject, String messageBody) {
        if (recipientEmail == null || recipientEmail.isEmpty()) {
            throw new IllegalArgumentException("La dirección de correo electrónico no puede estar vacía.");
        }
        if (!recipientEmail.matches("^[\\w._%+-]+@[\\w.-]+\\.[a-zA-Z]{2,}$")) {
            throw new IllegalArgumentException("La dirección de correo electrónico no es válida.");
        }
        if (subject == null || subject.isEmpty()) {
            throw new IllegalArgumentException("El asunto no puede estar vacío.");
        }
        if (messageBody == null || messageBody.isEmpty()) {
            throw new IllegalArgumentException("El cuerpo del mensaje no puede estar vacío.");
        }
    }
}
