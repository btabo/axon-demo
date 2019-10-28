package com.bta.axondemo.infra.amqp.axon;

import lombok.extern.slf4j.Slf4j;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;

@Configuration
@Profile("rabbitmq")
@Slf4j
public class AMQPConfig {


//    @Bean
//    SpringAMQPMessageSource inputMessageSource(final AMQPMessageConverter messageConverter) {
//        return new SpringAMQPMessageSource(messageConverter) {
//            @RabbitListener(queues = "axondemo.events.queue")
//            @Override
//            public void onMessage(final Message message, final Channel channel) {
//                log.debug("received external message: {}, channel: {}", message, channel);
//                super.onMessage(message, channel);
//            }
//        };
//    }


}
