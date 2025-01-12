package com.umit.rabbitmq.consumer;

import com.umit.rabbitmq.model.RejectManagerModel;
import com.umit.service.ManagerService;
import lombok.RequiredArgsConstructor;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class RejectManagerConsumer {

    private final ManagerService managerService;

    @RabbitListener(queues = "reject-manager-queue")
    public void createUserListener(RejectManagerModel model){
        System.out.println("Kuyruk gelen mesaj: " + model);
        managerService.rejectManager(model.getManagerId());
    }

}
