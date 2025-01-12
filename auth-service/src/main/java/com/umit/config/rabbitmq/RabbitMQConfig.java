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
    @Bean
    DirectExchange directExchange(){
        return new DirectExchange(EXCHANGE_AUTH);
    }



    /**
     * Auth -> Manager; createManager methoduna mesaj gönderir.
     */
    private final String QUEUE_AUTH_CREATE_MANAGER = "auth-create-manager-queue";
    private final String BINDING_KEY_AUTH_CREATE_MANAGER = "auth-create-manager-binding-key";

    @Bean
    Queue queueAuthCreateManager(){
        return new Queue(QUEUE_AUTH_CREATE_MANAGER);
    }

    @Bean
    Binding bindingAuth(final DirectExchange directExchange, final Queue queueAuthCreateManager){
        return BindingBuilder.bind(queueAuthCreateManager).to(directExchange).with(BINDING_KEY_AUTH_CREATE_MANAGER);
    }

    /**
     * Manager -> Auth; approveAuth methodu ile iletisime gecen kuyruk.
     */
    private final String QUEUE_APPROVE_AUTH = "approve-auth-queue";
    @Bean
    Queue queueApproveAuth(){
        return new Queue(QUEUE_APPROVE_AUTH);
    }


    /**
     * Manager -> Auth; rejectAuth methodu ile iletisime gecen kuyruk.
     */
    private final String QUEUE_REJECT_AUTH = "reject-auth-queue";
    @Bean
    Queue queueRejectAuth(){
        return new Queue(QUEUE_REJECT_AUTH);
    }

    /**
     * Auth -> MailService; sendMailActivation methoduna mesaj gonderir.
     */

    private final String QUEUE_SEND_MAIL_ACTIVATION = "send-mail-activation-queue";
    private final String BINDING_KEY_SEND_MAIL_ACTIVATION = "send-mail-activation-binding-key";

    @Bean
    Queue queueSendMailActivation(){
        return new Queue(QUEUE_SEND_MAIL_ACTIVATION);
    }

    @Bean
    Binding bindingSendMailActivation(final DirectExchange directExchange, final Queue queueSendMailActivation){
        return BindingBuilder.bind(queueSendMailActivation).to(directExchange).with(BINDING_KEY_SEND_MAIL_ACTIVATION);
    }

    /**
     * Auth -> MailService; sendMailReject methoduna mesaj gonderir.
     */

    private final String QUEUE_SEND_MAIL_REJECT = "send-mail-reject-queue";
    private final String BINDING_KEY_SEND_MAIL_REJECT = "send-mail-reject-binding-key";

    @Bean
    Queue queueSendMailReject(){
        return new Queue(QUEUE_SEND_MAIL_REJECT);
    }

    @Bean
    Binding bindingSendMailReject(final DirectExchange directExchange, final Queue queueSendMailReject){
        return BindingBuilder.bind(queueSendMailReject).to(directExchange).with(BINDING_KEY_SEND_MAIL_REJECT);
    }

    /**
     * Auth -> Employee; saveEmployee methodu ile iletisime gecen kuyruk
     */
    private final String QUEUE_AUTH_CREATE_EMPLOYEE = "auth-create-employee-queue";
    private final String BINDING_KEY_AUTH_CREATE_EMPLOYEE = "auth-create-employee-binding-key";

    @Bean
    Queue queueAuthCreateEmployee(){
        return new Queue(QUEUE_AUTH_CREATE_EMPLOYEE);
    }

    @Bean
    Binding bindingAuthCreateEmployee(final DirectExchange directExchange, final Queue queueAuthCreateEmployee){
        return BindingBuilder.bind(queueAuthCreateEmployee).to(directExchange).with(BINDING_KEY_AUTH_CREATE_EMPLOYEE);
    }

}
