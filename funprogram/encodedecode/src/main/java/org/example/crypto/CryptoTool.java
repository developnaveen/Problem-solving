package org.example.crypto;

import javax.crypto.KeyGenerator;
import javax.crypto.SecretKey;
import java.security.KeyPair;
import java.security.KeyPairGenerator;
import java.util.Scanner;

public class CryptoTool {


    public static void main(String[] args) throws Exception {
        Hashing hs = new Hashing();
        Encode ed = new Encode();
        Scanner sc = new Scanner(System.in);

        System.out.print("Enter the data: ");
        String data = sc.next();

        String hsd = hs.hashData("data");
        hs.verifyPassword("data", hsd);
        System.out.println(hsd);
        System.out.println(hs.verifyPassword("data", hsd));
        // completed the hasing

        KeyGenerator kg = KeyGenerator.getInstance("AES");
        kg.init(256);
        SecretKey sk= kg.generateKey();
        String aes = ed.encodAes(data, sk);
        System.out.println("AES encoded: " + aes );
        // completed the AES encode

        KeyPairGenerator kpg = KeyPairGenerator.getInstance("RSA");
        kpg.initialize(1024);// min key size is 1024
        KeyPair kp = kpg.generateKeyPair();
        String rsa = ed.encodeRsa(data, kp.getPublic());
        System.out.println("RSA encoded: " + rsa );
        // completed the RSA encode

        // start of decode
        Decode dc = new Decode();
        String decodeaes = dc.decodeAes(aes, sk);
        System.out.println("AES decode: " + decodeaes);

        String decodersa =dc.decodeRsa(rsa, kp.getPrivate());
        System.out.println("RSA decode: " + decodersa);

    }
}
