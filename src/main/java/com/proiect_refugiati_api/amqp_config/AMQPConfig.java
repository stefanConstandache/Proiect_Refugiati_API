package com.proiect_refugiati_api.amqp_config;

import org.springframework.amqp.core.AnonymousQueue;
import org.springframework.context.annotation.Bean;
import org.springframework.amqp.core.*;
import org.springframework.context.annotation.Configuration;

@Configuration
public class AMQPConfig {

    @Bean
    public DirectExchange direct() {
        return new DirectExchange("some.exchange");
    }

    @Bean
    public Queue autoDeleteQueue1() {
        return new AnonymousQueue();
    }

    @Bean
    public Queue autoDeleteQueue2() {
        return new AnonymousQueue();
    }

    @Bean
    public Binding binding1a(DirectExchange direct,
                             Queue autoDeleteQueue1) {
        return BindingBuilder.bind(autoDeleteQueue1)
                .to(direct)
                .with("orange");
    }

    @Bean
    public Binding binding1b(DirectExchange direct,
                             Queue autoDeleteQueue1) {
        return BindingBuilder.bind(autoDeleteQueue1)
                .to(direct)
                .with("black");
    }

    @Bean
    public Binding binding2a(DirectExchange direct,
                             Queue autoDeleteQueue2) {
        return BindingBuilder.bind(autoDeleteQueue2)
                .to(direct)
                .with("green");
    }

    @Bean
    public Binding binding2b(DirectExchange direct,
                             Queue autoDeleteQueue2) {
        return BindingBuilder.bind(autoDeleteQueue2)
                .to(direct)
                .with("black");
    }

    @Bean
    public AMQPReceiver receiver() {
        return new AMQPReceiver();
    }


    //@Profile("sender")
    @Bean
    public AMQPSender sender() {
        return new AMQPSender();
    }
}
