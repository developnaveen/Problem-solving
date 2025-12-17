package com.example.springbatch.processor;

import org.springframework.batch.item.ItemProcessor;
import com.example.springbatch.domain.User;

public class UserItemProcessor implements ItemProcessor<User, User> {

    @Override
    public User process(User item) {
        System.out.println(">>> Processing user: " + item);
        return item;   // pass-through for now
    }
}
