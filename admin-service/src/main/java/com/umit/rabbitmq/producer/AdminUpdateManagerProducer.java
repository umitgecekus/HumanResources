package com.umit.rabbitmq.producer;

import com.umit.rabbitmq.model.AdminUpdateManagerModel;
import lombok.RequiredArgsConstructor;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AdminUpdateManagerProducer {

    private final RabbitTemplate rabbitTemplate;
    public void sendMessage(AdminUpdateManagerModel model){
        rabbitTemplate.convertAndSend("admin-exchange", "admin-update-manager-binding-key", model);
    }

}
