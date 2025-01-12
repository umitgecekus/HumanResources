package com.umit.config.rabbitmq;

import org.springframework.amqp.core.Queue;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class RabbitMQConfig {

    /**
     * Auth -> MailService; sendMailActivation methoduna mesaj gönderir.
     */

    private final String QUEUE_SEND_MAIL_ACTIVATION = "send-mail-activation-queue";

    @Bean
    Queue queueSendMailActivation(){
        return new Queue(QUEUE_SEND_MAIL_ACTIVATION);
    }


    /**
     * Auth -> MailService; sendMailReject methoduna mesaj gönderir.
     */
    private final String QUEUE_SEND_MAIL_REJECT = "send-mail-reject-queue";

    @Bean
    Queue queueSendMailReject(){
        return new Queue(QUEUE_SEND_MAIL_REJECT);
    }

}
