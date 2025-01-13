package com.umit.rabbitmq.producer;

import com.umit.rabbitmq.model.RejectManagerModel;
import lombok.RequiredArgsConstructor;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class RejectManagerProducer {

    private final RabbitTemplate rabbitTemplate;
    public void sendMessage(RejectManagerModel model){
        rabbitTemplate.convertAndSend("company-exchange", "reject-manager-binding-key", model);
    }

}
