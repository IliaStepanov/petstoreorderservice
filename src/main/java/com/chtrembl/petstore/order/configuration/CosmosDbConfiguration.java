package com.chtrembl.petstore.order.configuration;


import com.azure.cosmos.*;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;

import java.util.Collections;

@Configuration
@Profile("cosmos")
public class CosmosDbConfiguration {

    @Value("${petstore.cosmos.account_host}")
    private String accountHost;
    @Value("${petstore.cosmos.account_key}")
    private String accountKey;


    @Bean
    public CosmosClient cosmosClient() {
        return new CosmosClientBuilder()
                .endpoint(accountHost)
                .key(accountKey)
                .preferredRegions(Collections.singletonList("West US"))
                .consistencyLevel(ConsistencyLevel.EVENTUAL)
                .buildClient();
    }

    @Bean
    public CosmosContainer ordersContainer(CosmosClient cosmosClient) {
        CosmosDatabase petstore = cosmosClient.getDatabase("petstore");
        return petstore.getContainer("orders");
    }

}
