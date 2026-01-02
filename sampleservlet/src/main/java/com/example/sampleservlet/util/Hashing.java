package com.example.sampleservlet.util;

import org.mindrot.jbcrypt.BCrypt;

public class Hashing {

    public String  hashData(String data){
        return BCrypt.hashpw(data, BCrypt.gensalt(12));
    }

    public boolean verifyPassword(String data, String password){
        return BCrypt.checkpw(data, password);
    }

}
