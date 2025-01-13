package com.umit.config.rabbitmq;

import org.springframework.amqp.core.Binding;
import org.springframework.amqp.core.BindingBuilder;
import org.springframework.amqp.core.DirectExchange;
import org.springframework.amqp.core.Queue;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class RabbitMQConfig {

    private final String EXCHANGE_COMPANY = "company-exchange";
    @Bean
    DirectExchange directExchange(){
        return new DirectExchange(EXCHANGE_COMPANY);
    }


    /**
     * Manager -> Company; createCompany methodu ile iletişime geçen kuyruk.
     */
    private final String QUEUE_CREATE_COMPANY = "create-company-queue";
    @Bean
    Queue queueCreateCompany(){
        return new Queue(QUEUE_CREATE_COMPANY);
    }


    /**
     * Company -> Manager; approveManager methodu ile iletişime geçen kuyruk.
     */

    private final String QUEUE_APPROVE_MANAGER = "approve-manager-queue";

    private final String BINDING_KEY_APPROVE_MANAGER = "approve-manager-binding-key";


    @Bean
    Queue queueApproveManager(){
        return new Queue(QUEUE_APPROVE_MANAGER);
    }

    @Bean
    Binding bindingApproveManager(final DirectExchange directExchange, final Queue queueApproveManager){
        return BindingBuilder.bind(queueApproveManager).to(directExchange).with(BINDING_KEY_APPROVE_MANAGER);
    }


    /**
     * Company -> Manager; rejectManager methodu ile iletişime geçen kuyruk.
     */

    private final String QUEUE_REJECT_MANAGER = "reject-manager-queue";

    private final String BINDING_KEY_REJECT_MANAGER = "reject-manager-binding-key";


    @Bean
    Queue queueRejectManager(){
        return new Queue(QUEUE_REJECT_MANAGER);
    }

    @Bean
    Binding bindingRejectManager(final DirectExchange directExchange, final Queue queueRejectManager){
        return BindingBuilder.bind(queueRejectManager).to(directExchange).with(BINDING_KEY_REJECT_MANAGER);
    }

}
