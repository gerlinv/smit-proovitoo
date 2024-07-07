package com.smit_proovitoo.backend.config;

import org.springframework.amqp.core.AmqpTemplate;
import org.springframework.amqp.core.Binding;
import org.springframework.amqp.core.BindingBuilder;
import org.springframework.amqp.core.Queue;
import org.springframework.amqp.core.TopicExchange;
import org.springframework.amqp.rabbit.connection.ConnectionFactory;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.amqp.support.converter.Jackson2JsonMessageConverter;
import org.springframework.amqp.support.converter.MessageConverter;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class RabbitMQConfig {

    @Value("${rabbitmq.exchange.name}")
    private String exchange;

    @Value("${rabbitmq.routing-key.suffix}")
    private String routingKeySuffix;

    @Bean
    TopicExchange exchange() {
        return new TopicExchange(exchange);
    }

    @Bean
    Queue emailSendQueue() {
        return new Queue(RabbitMQQueue.PROCEDURE_EMAIL_SEND_QUEUE, false);
    }

    @Bean
    public Binding emailSendBinding() {
        String routingKey = generateRoutingKey(RabbitMQQueue.PROCEDURE_EMAIL_SEND_QUEUE);
        return BindingBuilder
                .bind(emailSendQueue())
                .to(exchange())
                .with(routingKey);
    }

    @Bean
    Queue receiveQueue() {
        return new Queue(RabbitMQQueue.PROCEDURE_RECEIVE_QUEUE, false);
    }

    @Bean
    public Binding receiveBinding() {
        String routingKey = generateRoutingKey(RabbitMQQueue.PROCEDURE_RECEIVE_QUEUE);
        return BindingBuilder
                .bind(receiveQueue())
                .to(exchange())
                .with(routingKey);
    }

    @Bean
    MessageConverter converter() {
        return new Jackson2JsonMessageConverter();
    }

    private String generateRoutingKey(String queueName) {
        return queueName + routingKeySuffix;
    }

    @Bean
    public AmqpTemplate amqpTemplate(ConnectionFactory connectionFactory) {
        RabbitTemplate rabbitTemplate = new RabbitTemplate(connectionFactory);
        rabbitTemplate.setMessageConverter(converter());
        return rabbitTemplate;
    }

}
