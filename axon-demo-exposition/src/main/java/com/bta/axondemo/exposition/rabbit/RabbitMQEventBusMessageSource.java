package com.bta.axondemo.exposition.rabbit;

import com.rabbitmq.client.Channel;
import lombok.extern.slf4j.Slf4j;
import org.axonframework.eventhandling.EventMessage;
import org.axonframework.extensions.amqp.eventhandling.AMQPMessageConverter;
import org.axonframework.extensions.amqp.eventhandling.spring.SpringAMQPMessageSource;
import org.springframework.amqp.core.Message;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Component;

import java.lang.reflect.Field;
import java.util.List;
import java.util.function.Consumer;

@Slf4j
@Component("rabbitMQSpringAMQPMessageSource")
@Profile("rabbitmq")
public class RabbitMQEventBusMessageSource extends SpringAMQPMessageSource {

    @Autowired
    public RabbitMQEventBusMessageSource(final AMQPMessageConverter messageConverter) {
        super(messageConverter);
    }

    @RabbitListener(queues = "axondemo.events.queue")
    @Override
    public void onMessage(final Message message, final Channel channel) {

        try {
            final Field eventProcessorsField = this.getClass().getSuperclass().getDeclaredField("eventProcessors");
            eventProcessorsField.setAccessible(true);
            final List eventProcessors = (List<Consumer<List<? extends EventMessage<?>>>>) eventProcessorsField.get(this);
            log.debug("eventProcessors: {}", eventProcessors);
        } catch (NoSuchFieldException | IllegalAccessException e) {
            e.printStackTrace();
        }

        log.debug("received message: message={}, channel={}", message, channel);
        super.onMessage(message, channel);
    }

}
