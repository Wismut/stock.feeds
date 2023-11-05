package com.example.stock.feeds.config;

import com.example.stock.feeds.model.Company;
import com.example.stock.feeds.model.Stock;
import io.r2dbc.spi.ConnectionFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.ClassPathResource;
import org.springframework.data.redis.connection.ReactiveRedisConnectionFactory;
import org.springframework.data.redis.core.ReactiveRedisTemplate;
import org.springframework.data.redis.serializer.Jackson2JsonRedisSerializer;
import org.springframework.data.redis.serializer.RedisSerializationContext;
import org.springframework.r2dbc.connection.init.ConnectionFactoryInitializer;
import org.springframework.r2dbc.connection.init.ResourceDatabasePopulator;

@Configuration
public class WebConfig {
    @Bean
    ConnectionFactoryInitializer initializer(ConnectionFactory connectionFactory) {

        ConnectionFactoryInitializer initializer = new ConnectionFactoryInitializer();
        initializer.setConnectionFactory(connectionFactory);
        initializer.setDatabasePopulator(new ResourceDatabasePopulator(new ClassPathResource("schema.sql")));

        return initializer;
    }

    @Bean
    public ReactiveRedisTemplate<String, Stock> reactiveRedisStockTemplate(ReactiveRedisConnectionFactory factory) {
        Jackson2JsonRedisSerializer<Stock> serializer = new Jackson2JsonRedisSerializer<>(Stock.class);

        RedisSerializationContext.RedisSerializationContextBuilder<String, Stock> builder =
                RedisSerializationContext.newSerializationContext(new Jackson2JsonRedisSerializer<>(String.class));
        RedisSerializationContext<String, Stock> context = builder.value(serializer).build();

        return new ReactiveRedisTemplate<>(factory, context);
    }

    @Bean
    public ReactiveRedisTemplate<String, Company> reactiveRedisCompanyTemplate(ReactiveRedisConnectionFactory factory) {
        Jackson2JsonRedisSerializer<Company> serializer = new Jackson2JsonRedisSerializer<>(Company.class);

        RedisSerializationContext.RedisSerializationContextBuilder<String, Company> builder =
                RedisSerializationContext.newSerializationContext(new Jackson2JsonRedisSerializer<>(String.class));
        RedisSerializationContext<String, Company> context = builder.value(serializer).build();

        return new ReactiveRedisTemplate<>(factory, context);
    }
}
