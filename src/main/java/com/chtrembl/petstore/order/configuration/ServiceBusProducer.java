package com.chtrembl.petstore.order.configuration;

import com.azure.messaging.servicebus.ServiceBusClientBuilder;
import com.azure.messaging.servicebus.ServiceBusSenderClient;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;

@Configuration
public class ServiceBusProducer {

    @Value("${petstore.bus.connection}")
    private String connectionString;
    @Value("${petstore.bus.queue}")
    private String queueName;

    @Bean
    public ServiceBusSenderClient serviceBusSenderClient() {
        // create a Service Bus Sender client for the queue
        return new ServiceBusClientBuilder()
                .connectionString(connectionString)
                .sender()
                .queueName(queueName)
                .buildClient();
    }
}
