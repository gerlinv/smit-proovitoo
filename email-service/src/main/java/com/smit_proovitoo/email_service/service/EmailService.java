package com.smit_proovitoo.email_service.service;

import com.smit_proovitoo.email_service.config.RabbitMQQueue;

import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

@Service
public class EmailService {

    @Value("${rabbitmq.exchange.name}")
    private String exchange;

    @Value("${rabbitmq.routing-key.suffix}")
    private String routingKeySuffix;

    @Autowired
    protected RabbitTemplate rabbitTemplate;

    public void sendResponse(Long id) {
        String routingKey = RabbitMQQueue.PROCEDURE_RECEIVE_QUEUE + routingKeySuffix;
        rabbitTemplate.convertAndSend(exchange, routingKey, id);
    }

}
