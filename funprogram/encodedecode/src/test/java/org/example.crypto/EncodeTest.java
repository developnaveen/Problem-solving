package org.example.crypto;

import org.junit.jupiter.api.Test;

import javax.crypto.KeyGenerator;
import javax.crypto.SecretKey;

import java.security.KeyPair;
import java.security.KeyPairGenerator;

import static org.junit.jupiter.api.Assertions.*;

public class EncodeTest {

    @Test
    void encodAesShouldReturn() throws Exception {
        Encode ec = new Encode();
        Decode dc = new Decode();
        String data = "naveen";

        KeyGenerator kg = KeyGenerator.getInstance("AES");
        kg.init(256);
        SecretKey sk= kg.generateKey();
        String result = ec.encodAes(data,sk);
        String result1 = dc.decodeAes(result, sk);
        assertEquals(data,result1);
    }

    @Test
    void encodeRsaShouldReturn() throws Exception {
        Encode ec = new Encode();
        Decode dc = new Decode();
        String data = "naveen";

        KeyPairGenerator kpg = KeyPairGenerator.getInstance("RSA");
        kpg.initialize(1024);// min key size is 1024
        KeyPair kp = kpg.generateKeyPair();
        String result = ec.encodeRsa(data,kp.getPublic());
        String result1 = dc.decodeRsa(result, kp.getPrivate());
        assertEquals(data,result1);
    }


}
