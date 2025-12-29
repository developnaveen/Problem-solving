package org.example.crypto;

import java.security.MessageDigest;
import java.util.HexFormat;

public class Hashing  {
    public String hashData(String data) throws Exception {
        MessageDigest hash = MessageDigest.getInstance("SHA-256");
        byte[] hashed = hash.digest(data.getBytes());

        return HexFormat.of().formatHex(hashed);
    }
    
}
