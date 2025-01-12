package com.umit.rabbitmq.consumer;

import com.umit.rabbitmq.model.SendMailActivationModel;
import com.umit.service.MailSenderService;
import lombok.RequiredArgsConstructor;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class SendMailActivationConsumer {

    private final MailSenderService mailSenderService;

    @RabbitListener(queues = "send-mail-activation-queue")
    public void createUserListener(SendMailActivationModel model){
        System.out.println("Kuyruk gelen mesaj: " + model);
        mailSenderService.sendMailChangePassword(model.getEmail(), model.getPassword());
    }

}
