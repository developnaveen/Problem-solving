package org.example.crypto;

import javax.crypto.Cipher;
import javax.crypto.SecretKey;
import javax.crypto.spec.GCMParameterSpec;
import java.nio.ByteBuffer;
import java.nio.charset.StandardCharsets;
import java.security.PrivateKey;
import java.util.HexFormat;

public class Decode {
    private static final HexFormat HEX = HexFormat.of();

    public String decodeAes(String data, SecretKey key) throws Exception{
        byte[] combined = HEX.parseHex(data);
        ByteBuffer bb = ByteBuffer.wrap(combined);
        byte[] iv = new byte[12];
        bb.get(iv);
        byte[] cipertext =new byte[bb.remaining()];
        bb.get(cipertext);

        Cipher c = Cipher.getInstance("AES/GCM/NoPadding");
        c.init(Cipher.DECRYPT_MODE, key, new GCMParameterSpec(128, iv));
        return new String(c.doFinal(cipertext), StandardCharsets.UTF_8);
    }

    public String decodeRsa(String data, PrivateKey key) throws Exception{
        Cipher c = Cipher.getInstance("RSA/ECB/OAEPWithSHA-256AndMGF1Padding");
        c.init(Cipher.DECRYPT_MODE, key);
        return new String(c.doFinal(HEX.parseHex(data)), StandardCharsets.UTF_8);
    }


}
