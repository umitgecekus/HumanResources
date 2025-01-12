package com.umit.rabbitmq.producer;

import com.umit.rabbitmq.model.CreateCompanyModel;
import lombok.RequiredArgsConstructor;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class CreateCompanyProducer {

    private final RabbitTemplate rabbitTemplate;
    public void sendMessage(CreateCompanyModel model){
        rabbitTemplate.convertAndSend("manager-exchange", "create-company-binding-key", model);
    }

}
