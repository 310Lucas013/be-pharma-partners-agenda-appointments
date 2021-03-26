package com.pharma.appointments.messaging;

import org.springframework.amqp.core.*;
import org.springframework.amqp.rabbit.connection.ConnectionFactory;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.amqp.support.converter.Jackson2JsonMessageConverter;
import org.springframework.amqp.support.converter.MessageConverter;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class MessageBrokerConfiguration {

    @Value("${rabbitmq.queue}")
    private String queueName;
    @Value("${rabbitmq.exchange}")
    private String exchange;
    @Value("${rabbitmq.routingKey}")
    private String routingKey;

    @Bean("queue-create-appointment")
    public Queue queueCreateAppointment() {
        return new Queue("queue-create-appointment", true);
    }

    @Bean("queue-update-appointment")
    public Queue queueUpdateAppointment() {
        return new Queue("queue-update-appointment", true);
    }

    @Bean
    public DirectExchange exchange() {
        return new DirectExchange(exchange);
    }

    @Bean
    public Binding bindingCreateAppointment(@Qualifier("queue-create-appointment") Queue queue, DirectExchange exchange) {
        return BindingBuilder.bind(queue).to(exchange).with("appointment");
    }

    @Bean
    public Binding bindingUpdateAppointment(@Qualifier("queue-update-appointment") Queue queue, DirectExchange exchange) {
        return BindingBuilder.bind(queue).to(exchange).with("appointment");
    }

    @Bean
    public MessageConverter jsonMessageConverter() {
        return new Jackson2JsonMessageConverter();
    }

    @Bean
    public AmqpTemplate rabbitTemplate(ConnectionFactory connectionFactory) {
        final RabbitTemplate rabbitTemplate = new RabbitTemplate(connectionFactory);
        rabbitTemplate.setMessageConverter(jsonMessageConverter());
        return rabbitTemplate;
    }
}

