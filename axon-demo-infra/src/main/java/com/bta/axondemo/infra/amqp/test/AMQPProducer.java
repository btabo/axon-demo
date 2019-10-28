package com.bta.axondemo.infra.amqp.test;

import com.bta.axondemo.infra.amqp.axon.RabbitMQProperties;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Component;

@Component
@Profile("rabbitmq")
public class AMQPProducer {

    @Autowired
    private RabbitTemplate rabbitTemplate;

    @Autowired
    RabbitMQProperties rabbitMQProperties;

    public void sendMessage(Notification msg){
        System.out.println("Send msg = " + msg.getMsg());
        rabbitTemplate.convertAndSend(rabbitMQProperties.getExchangeName(), rabbitMQProperties.getRoutingKey(), msg);
    }
}
