package com.umit.rabbitmq.producer;

import com.umit.rabbitmq.model.ApproveManagerModel;
import lombok.RequiredArgsConstructor;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class ApproveManagerProducer {

    private final RabbitTemplate rabbitTemplate;
    public void sendMessage(ApproveManagerModel model){
        rabbitTemplate.convertAndSend("company-exchange", "approve-manager-binding-key", model);
    }

}
