package com.ms.email.consume;

import com.ms.email.dtos.EmailRecordDTO;
import com.ms.email.models.Email;
import com.ms.email.services.EmailService;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.beans.BeanUtils;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.stereotype.Component;

@Component
public class EmailConsumer {

    final EmailService emailService;

    public EmailConsumer(EmailService emailService) {
        this.emailService = emailService;
    }

    @RabbitListener(queues = "${broker.queue.email.name}")
    public void listenEmailQueue(@Payload EmailRecordDTO emailRecordDTO){
        var email = new Email();
        BeanUtils.copyProperties(emailRecordDTO,email);
        emailService.sendEmail(email);
    }


}
