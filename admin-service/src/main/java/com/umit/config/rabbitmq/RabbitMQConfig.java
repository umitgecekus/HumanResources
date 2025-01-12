package com.umit.config.rabbitmq;

import org.springframework.amqp.core.Binding;
import org.springframework.amqp.core.BindingBuilder;
import org.springframework.amqp.core.DirectExchange;
import org.springframework.amqp.core.Queue;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class RabbitMQConfig {

    private final String EXCHANGE_AUTH = "auth-exchange";
    private final String QUEUE_AUTH = "auth-queue";
    private final String BINDING_KEY_AUTH = "auth-binding-key";

    @Bean
    DirectExchange directExchange(){
        return new DirectExchange(EXCHANGE_AUTH);
    }

    @Bean
    Queue queueAuth(){
        return new Queue(QUEUE_AUTH);
    }

    @Bean
    Binding bindingAuth(final DirectExchange directExchange, final Queue queueAuth){
        return BindingBuilder.bind(queueAuth).to(directExchange).with(BINDING_KEY_AUTH);
    }

    /**
     * Admin -> Employee; managerOrAdminUpdateEmployee methodu ile iletisime gecen kuyruk
     */
    private final String QUEUE_MANAGER_OR_ADMIN_UPDATE_EMPLOYEE = "manager-or-admin-update-employee-queue";
    private final String BINDING_KEY_MANAGER_OR_ADMIN_UPDATE_EMPLOYEE = "manager-or-admin-update-employee-binding-key";
    @Bean
    Queue queueManagerOrAdminUpdateEmployee(){
        return new Queue(QUEUE_MANAGER_OR_ADMIN_UPDATE_EMPLOYEE);
    }
    @Bean
    Binding bindingManagerOrAdminUpdateEmployee(final DirectExchange directExchange, final Queue queueManagerOrAdminUpdateEmployee){
        return BindingBuilder.bind(queueManagerOrAdminUpdateEmployee).to(directExchange).with(BINDING_KEY_MANAGER_OR_ADMIN_UPDATE_EMPLOYEE);
    }

    /**
     * Admin -> Manager; adminUpdateManager methodu ile iletisime gecen kuyruk
     */
    private final String QUEUE_ADMIN_UPDATE_MANAGER = "admin-update-manager-queue";
    private final String BINDING_KEY_ADMIN_UPDATE_MANAGER = "admin-update-manager-binding-key";
    @Bean
    Queue queueAdminUpdateManager(){
        return new Queue(QUEUE_ADMIN_UPDATE_MANAGER);
    }
    @Bean
    Binding bindingAdminUpdateManager(final DirectExchange directExchange, final Queue queueAdminUpdateManager){
        return BindingBuilder.bind(queueAdminUpdateManager).to(directExchange).with(BINDING_KEY_ADMIN_UPDATE_MANAGER);
    }

}
