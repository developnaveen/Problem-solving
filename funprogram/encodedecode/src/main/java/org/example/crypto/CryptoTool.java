package org.example.crypto;

import javax.crypto.SecretKey;
import java.nio.file.Path;
import java.security.KeyPair;
import java.security.PrivateKey;
import java.security.PublicKey;
import java.util.Scanner;

public class CryptoTool {

    private static void usage() {
        System.out.println("CryptoTool - simple hashing / AES-GCM / RSA hybrid tool");
        System.out.println("Usage:");
        System.out.println("  gen-rsa <bits> <pub.pem> <priv.pem>");
        System.out.println("  hash-text <text>");
        System.out.println("  hash-file <file>");
        System.out.println("  encrypt-file <in> <out.json> <recipient_pub.pem>");
        System.out.println("  decrypt-file <in.json> <out> <my_priv.pem>");
        System.out.println("  gen-aes <bits> (prints base64 key)");
        System.out.println("  derive-aes <password> <salt-hex> <iter> <bits> (prints base64 key)");
        System.out.println();
    }

    public static void main(String[] args) throws Exception {
        if (args.length < 1) { usage(); return; }
        String cmd = args[0];

        switch (cmd) {
            case "gen-rsa": {
                if (args.length != 4) { usage(); return; }
                int bits = Integer.parseInt(args[1]);
                Path pub = Path.of(args[2]);
                Path priv = Path.of(args[3]);
                KeyPair kp = CryptoUtils.generateRsaKeyPair(bits);
                CryptoUtils.savePublicKey(kp.getPublic(), pub);
                CryptoUtils.savePrivateKey(kp.getPrivate(), priv);
                System.out.println("Generated RSA " + bits + " keys: " + pub + " , " + priv);
                break;
            }
            case "hash-text": {
                if (args.length != 2) { usage(); return; }
                System.out.println(CryptoUtils.sha256Hex(args[1]));
                break;
            }
            case "hash-file": {
                if (args.length != 2) { usage(); return; }
                System.out.println(CryptoUtils.sha256HexOfFile(Path.of(args[1])));
                break;
            }
            case "encrypt-file": {
                if (args.length != 4) { usage(); return; }
                Path in = Path.of(args[1]);
                Path out = Path.of(args[2]);
                PublicKey pub = CryptoUtils.loadPublicKey(Path.of(args[3]));
                CryptoUtils.encryptFileHybrid(in, out, pub);
                System.out.println("Encrypted file to JSON package: " + out);
                break;
            }
            case "decrypt-file": {
                if (args.length != 4) { usage(); return; }
                Path in = Path.of(args[1]);
                Path out = Path.of(args[2]);
                PrivateKey priv = CryptoUtils.loadPrivateKey(Path.of(args[3]));
                CryptoUtils.decryptFileHybrid(in, out, priv);
                System.out.println("Decrypted file written to: " + out);
                break;
            }
            case "gen-aes": {
                if (args.length != 2) { usage(); return; }
                int bits = Integer.parseInt(args[1]);
                SecretKey k = CryptoUtils.generateRandomAesKey(bits);
                System.out.println("AES key (base64): " + CryptoUtils.toBase64(k.getEncoded()));
                break;
            }
            case "derive-aes": {
                if (args.length != 5) { usage(); return; }
                String password = args[1];
                String saltHex = args[2];
                int iter = Integer.parseInt(args[3]);
                int bits = Integer.parseInt(args[4]);
                byte[] salt = hexToBytes(saltHex);
                SecretKey k = CryptoUtils.deriveAesKeyFromPassword(password.toCharArray(), salt, iter, bits);
                System.out.println("Derived AES key (base64): " + CryptoUtils.toBase64(k.getEncoded()));
                break;
            }
            default:
                usage();
        }
    }

    private static byte[] hexToBytes(String hex) {
        int len = hex.length();
        byte[] out = new byte[len/2];
        for (int i=0;i<len;i+=2) out[i/2] = (byte)((Character.digit(hex.charAt(i),16)<<4)
                + Character.digit(hex.charAt(i+1),16));
        return out;
    }
}
