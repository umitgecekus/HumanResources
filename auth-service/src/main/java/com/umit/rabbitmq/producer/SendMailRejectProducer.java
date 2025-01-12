package com.umit.rabbitmq.producer;

import com.umit.rabbitmq.model.SendMailRejectModel;
import lombok.RequiredArgsConstructor;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class SendMailRejectProducer {

    private final RabbitTemplate rabbitTemplate;

    public void sendMessage(SendMailRejectModel model){
        rabbitTemplate.convertAndSend("auth-exchange", "send-mail-reject-binding-key", model);
    }

}
