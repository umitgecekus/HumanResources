package com.umit.rabbitmq.consumer;

import com.umit.rabbitmq.model.RejectAuthModel;
import com.umit.service.AuthService;
import lombok.RequiredArgsConstructor;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class RejectAuthConsumer {

    private final AuthService authService;

    @RabbitListener(queues = "reject-auth-queue")
    public void createUserListener(RejectAuthModel model){
        System.out.println("Kuyruk gelen mesaj: " + model);
        authService.rejectAuth(model.getAuthId());
    }

}
