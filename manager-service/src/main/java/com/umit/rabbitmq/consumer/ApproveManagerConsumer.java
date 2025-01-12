package com.umit.rabbitmq.consumer;

import com.umit.rabbitmq.model.ApproveManagerModel;
import com.umit.service.ManagerService;
import lombok.RequiredArgsConstructor;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class ApproveManagerConsumer {

    private final ManagerService managerService;

    @RabbitListener(queues = "approve-manager-queue")
    public void createUserListener(ApproveManagerModel model){
        System.out.println("Kuyruk gelen mesaj: " + model);
        managerService.approveManager(model.getManagerId());
    }

}
