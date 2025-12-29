package org.example.crypto;


import javax.crypto.Cipher;
import javax.crypto.SecretKey;
import javax.crypto.spec.GCMParameterSpec;
import java.nio.ByteBuffer;
import java.security.PublicKey;
import java.security.SecureRandom;
import java.util.HexFormat;

public class Encode {
    private static final HexFormat HEX = HexFormat.of();

    public String encodAes(String data, SecretKey key) throws Exception{
        Cipher c = Cipher.getInstance("AES/GCM/NoPadding");
        byte[] iv = new byte[12];
        SecureRandom.getInstanceStrong().nextBytes(iv);
        c.init(Cipher.ENCRYPT_MODE, key, new GCMParameterSpec(128, iv));
        byte[] cybertext = c.doFinal(data.getBytes());
        return HEX.formatHex(ByteBuffer.allocate(iv.length + cybertext.length).put(iv).put(cybertext).array());
    }

    public String encodeRsa(String data, PublicKey key) throws Exception{
        Cipher c = Cipher.getInstance("RSA/ECB/OAEPWithSHA-256AndMGF1Padding");
        c.init(Cipher.ENCRYPT_MODE, key);
        return HEX.formatHex(c.doFinal(data.getBytes()));
    }
}
