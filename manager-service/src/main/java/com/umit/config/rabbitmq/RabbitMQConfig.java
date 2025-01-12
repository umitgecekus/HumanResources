package com.umit.config.rabbitmq;

import org.springframework.amqp.core.Binding;
import org.springframework.amqp.core.BindingBuilder;
import org.springframework.amqp.core.DirectExchange;
import org.springframework.amqp.core.Queue;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class RabbitMQConfig {

    private final String EXCHANGE_MANAGER = "manager-exchange";
    @Bean
    DirectExchange directExchange(){
        return new DirectExchange(EXCHANGE_MANAGER);
    }

    /**
     * Auth -> Manager; createManager methoduna mesaj gonderir.
     */
    private final String QUEUE_AUTH_CREATE_MANAGER = "auth-create-manager-queue";
    @Bean
    Queue queueAuthCreateManager(){
        return new Queue(QUEUE_AUTH_CREATE_MANAGER);
    }


    /**
     * Manager -> Company; createCompany methodu ile iletisime gecen kuyruk.
     */

    private final String QUEUE_CREATE_COMPANY = "create-company-queue";
    private final String BINDING_KEY_CREATE_COMPANY = "create-company-binding-key";
    @Bean
    Queue queueCreateCompany(){
        return new Queue(QUEUE_CREATE_COMPANY);
    }

    @Bean
    Binding bindingCreateCompany(final DirectExchange directExchange, final Queue queueCreateCompany){
        return BindingBuilder.bind(queueCreateCompany).to(directExchange).with(BINDING_KEY_CREATE_COMPANY);
    }

    /**
     * Company -> Manager; approveManager methodu ile iletisime gecen kuyruk.
     */

    private final String QUEUE_APPROVE_MANAGER = "approve-manager-queue";
    @Bean
    Queue queueApproveManager(){
        return new Queue(QUEUE_APPROVE_MANAGER);
    }

    /**
     * Manager -> Auth; approveAuth methodu ile iletisime gecen kuyruk.
     */

    private final String QUEUE_APPROVE_AUTH = "approve-auth-queue";
    private final String BINDING_KEY_APPROVE_AUTH = "approve-auth-binding-key";
    @Bean
    Queue queueApproveAuth(){
        return new Queue(QUEUE_APPROVE_AUTH);
    }

    @Bean
    Binding bindingApproveAuth(final DirectExchange directExchange, final Queue queueApproveAuth){
        return BindingBuilder.bind(queueApproveAuth).to(directExchange).with(BINDING_KEY_APPROVE_AUTH);
    }

    /**
     * Company -> Manager; rejectManager methodu ile iletişime geçen kuyruk.
     */

    private final String QUEUE_REJECT_MANAGER = "reject-manager-queue";
    @Bean
    Queue queueRejectManager(){
        return new Queue(QUEUE_REJECT_MANAGER);
    }

    /**
     * Manager -> Auth; rejectAuth methodu ile iletişime geçen kuyruk.
     */

    private final String QUEUE_REJECT_AUTH = "reject-auth-queue";
    private final String BINDING_KEY_REJECT_AUTH = "reject-auth-binding-key";
    @Bean
    Queue queueRejectAuth(){
        return new Queue(QUEUE_REJECT_AUTH);
    }

    @Bean
    Binding bindingRejectAuth(final DirectExchange directExchange, final Queue queueRejectAuth){
        return BindingBuilder.bind(queueRejectAuth).to(directExchange).with(BINDING_KEY_REJECT_AUTH);
    }


    /**
     * Admin -> Manager; adminUpdateManager methodu ile iletisime gecen kuyruk
     */
    private final String QUEUE_ADMIN_UPDATE_MANAGER = "admin-update-manager-queue";
    @Bean
    Queue queueAdminUpdateManager(){
        return new Queue(QUEUE_ADMIN_UPDATE_MANAGER);
    }

}
