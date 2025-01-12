package com.umit.rabbitmq.producer;

import com.umit.rabbitmq.model.ApproveAuthModel;
import lombok.RequiredArgsConstructor;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class ApproveAuthProducer {

    private final RabbitTemplate rabbitTemplate;
    public void sendMessage(ApproveAuthModel model){
        rabbitTemplate.convertAndSend("manager-exchange", "approve-auth-binding-key", model);
    }

}
