package com.umit.config;

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

}
