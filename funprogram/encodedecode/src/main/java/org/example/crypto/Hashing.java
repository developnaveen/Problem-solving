package org.example.crypto;

import org.mindrot.jbcrypt.BCrypt;

public class Hashing  {
    public String hashData(String data) throws Exception {
        /*MessageDigest hash = MessageDigest.getInstance("SHA-256");
        byte[] hashed = hash.digest(data.getBytes());
        return HexFormat.of().formatHex(hashed); */
        // the above we have to use the salting


        return BCrypt.hashpw(data, BCrypt.gensalt(12));
    }

    public boolean verifyPassword(String data, String password){
        return BCrypt.checkpw(data, password);
    }
}
