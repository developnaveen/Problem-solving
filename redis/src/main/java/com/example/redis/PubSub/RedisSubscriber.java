package com.example.redis.PubSub;

import org.springframework.data.redis.connection.Message;
import org.springframework.data.redis.connection.MessageListener;
import org.springframework.stereotype.Component;

@Component
public class RedisSubscriber implements MessageListener {

    @Override
    public void onMessage(Message message, byte[] pattern) {

        String channel = new String(message.getChannel());
        String messageBody = new String(message.getBody());

        System.out.println("Channel: " + channel);
        System.out.println("Message: " + messageBody);
    }
}