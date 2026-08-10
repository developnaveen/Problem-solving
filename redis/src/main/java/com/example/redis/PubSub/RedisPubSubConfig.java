package com.example.redis.PubSub;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.connection.RedisConnectionFactory;
import org.springframework.data.redis.listener.ChannelTopic;
import org.springframework.data.redis.listener.RedisMessageListenerContainer;

//@Configuration
//public class RedisPubSubConfig {
//
//    @Bean
//    public ChannelTopic redisTopic() {
//        return new ChannelTopic("loan-channel");
//    }
//
//    @Bean
//    public RedisMessageListenerContainer redisContainer(RedisConnectionFactory connectionFactory, RedisSubscriber subscriber, ChannelTopic redisTopic) {
//
//        RedisMessageListenerContainer container = new RedisMessageListenerContainer();
//
//        container.setConnectionFactory(connectionFactory);
//
//        container.addMessageListener(subscriber, redisTopic);
//
//        return container;
//    }
// }

@Configuration
public class RedisPubSubConfig {

    @Bean
    public RedisMessageListenerContainer redisContainer(
            RedisConnectionFactory connectionFactory,
            RedisSubscriber subscriber) {

        RedisMessageListenerContainer container =
                new RedisMessageListenerContainer();

        container.setConnectionFactory(connectionFactory);

        // Subscribe ONLY to loan-channel
        container.addMessageListener(
                subscriber,
                new ChannelTopic("loan-channel")
        );

        return container;
    }
}
