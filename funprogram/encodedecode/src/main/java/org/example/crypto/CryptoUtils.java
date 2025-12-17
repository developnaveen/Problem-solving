package org.example.crypto;

import javax.crypto.*;
import javax.crypto.spec.GCMParameterSpec;
import javax.crypto.spec.PBEKeySpec;
import javax.crypto.spec.SecretKeySpec;
import java.io.*;
import java.nio.charset.StandardCharsets;
import java.nio.file.*;
import java.security.*;
import java.security.spec.*;
import java.util.Base64;

public final class CryptoUtils {

    private CryptoUtils() {}

    // ---------- HASHING ----------
    public static String sha256Hex(String text) throws NoSuchAlgorithmException {
        MessageDigest md = MessageDigest.getInstance("SHA-256");
        byte[] h = md.digest(text.getBytes(StandardCharsets.UTF_8));
        return bytesToHex(h);
    }

    public static String sha256HexOfFile(Path file) throws Exception {
        MessageDigest md = MessageDigest.getInstance("SHA-256");
        try (InputStream in = Files.newInputStream(file)) {
            byte[] buf = new byte[8192];
            int r;
            while ((r = in.read(buf)) != -1) md.update(buf, 0, r);
        }
        return bytesToHex(md.digest());
    }

    // ---------- AES-GCM ----------
    public static SecretKey generateRandomAesKey(int bits) throws NoSuchAlgorithmException {
        KeyGenerator kg = KeyGenerator.getInstance("AES");
        kg.init(bits);
        return kg.generateKey();
    }

    // Derive AES key using PBKDF2WithHmacSHA256
    public static SecretKey deriveAesKeyFromPassword(char[] password, byte[] salt, int iterations, int keySizeBits) throws Exception {
        PBEKeySpec spec = new PBEKeySpec(password, salt, iterations, keySizeBits);
        SecretKeyFactory skf = SecretKeyFactory.getInstance("PBKDF2WithHmacSHA256");
        byte[] keyBytes = skf.generateSecret(spec).getEncoded();
        return new SecretKeySpec(keyBytes, "AES");
    }

    // AES-GCM encrypt: returns base64 of (iv || ciphertext)
    public static String aesGcmEncryptToBase64(SecretKey key, byte[] plain) throws Exception {
        Cipher cipher = Cipher.getInstance("AES/GCM/NoPadding");
        byte[] iv = new byte[12]; // 96-bit recommended
        SecureRandom rnd = SecureRandom.getInstanceStrong();
        rnd.nextBytes(iv);
        GCMParameterSpec spec = new GCMParameterSpec(128, iv);
        cipher.init(Cipher.ENCRYPT_MODE, key, spec);
        byte[] ct = cipher.doFinal(plain);
        byte[] out = new byte[iv.length + ct.length];
        System.arraycopy(iv, 0, out, 0, iv.length);
        System.arraycopy(ct, 0, out, iv.length, ct.length);
        return Base64.getEncoder().encodeToString(out);
    }

    // AES-GCM decrypt from base64(iv || ciphertext)
    public static byte[] aesGcmDecryptFromBase64(SecretKey key, String base64IvCt) throws Exception {
        byte[] ivCt = Base64.getDecoder().decode(base64IvCt);
        if (ivCt.length < 12 + 16) throw new IllegalArgumentException("Ciphertext too short");
        byte[] iv = new byte[12];
        System.arraycopy(ivCt, 0, iv, 0, 12);
        byte[] ct = new byte[ivCt.length - 12];
        System.arraycopy(ivCt, 12, ct, 0, ct.length);
        Cipher cipher = Cipher.getInstance("AES/GCM/NoPadding");
        GCMParameterSpec spec = new GCMParameterSpec(128, iv);
        cipher.init(Cipher.DECRYPT_MODE, key, spec);
        return cipher.doFinal(ct);
    }

    // ---------- RSA ----------
    public static KeyPair generateRsaKeyPair(int bits) throws NoSuchAlgorithmException {
        KeyPairGenerator kpg = KeyPairGenerator.getInstance("RSA");
        kpg.initialize(bits);
        return kpg.generateKeyPair();
    }

    // RSA encrypt small payload with OAEP SHA-256
    public static String rsaEncryptToBase64(PublicKey pub, byte[] plain) throws Exception {
        Cipher cipher = Cipher.getInstance("RSA/ECB/OAEPWithSHA-256AndMGF1Padding");
        cipher.init(Cipher.ENCRYPT_MODE, pub);
        return Base64.getEncoder().encodeToString(cipher.doFinal(plain));
    }

    // RSA decrypt OAEP SHA-256
    public static byte[] rsaDecryptFromBase64(PrivateKey priv, String base64Cipher) throws Exception {
        Cipher cipher = Cipher.getInstance("RSA/ECB/OAEPWithSHA-256AndMGF1Padding");
        cipher.init(Cipher.DECRYPT_MODE, priv);
        return cipher.doFinal(Base64.getDecoder().decode(base64Cipher));
    }

    // ---------- Key (de)serialization ----------
    public static void savePublicKey(PublicKey key, Path outFile) throws IOException {
        byte[] encoded = key.getEncoded(); // X.509
        try (Writer w = Files.newBufferedWriter(outFile, StandardCharsets.UTF_8)) {
            w.write("-----BEGIN PUBLIC KEY-----\n");
            w.write(Base64.getMimeEncoder().encodeToString(encoded));
            w.write("\n-----END PUBLIC KEY-----\n");
        }
    }

    public static void savePrivateKey(PrivateKey key, Path outFile) throws IOException {
        byte[] encoded = key.getEncoded(); // PKCS#8
        try (Writer w = Files.newBufferedWriter(outFile, StandardCharsets.UTF_8)) {
            w.write("-----BEGIN PRIVATE KEY-----\n");
            w.write(Base64.getMimeEncoder().encodeToString(encoded));
            w.write("\n-----END PRIVATE KEY-----\n");
        }
    }

    public static PublicKey loadPublicKey(Path pemFile) throws Exception {
        String pem = Files.readString(pemFile, StandardCharsets.UTF_8);
        String b64 = pem.replaceAll("-----BEGIN PUBLIC KEY-----", "")
                .replaceAll("-----END PUBLIC KEY-----", "")
                .replaceAll("\\s", "");
        byte[] decoded = Base64.getDecoder().decode(b64);
        X509EncodedKeySpec spec = new X509EncodedKeySpec(decoded);
        KeyFactory kf = KeyFactory.getInstance("RSA");
        return kf.generatePublic(spec);
    }

    public static PrivateKey loadPrivateKey(Path pemFile) throws Exception {
        String pem = Files.readString(pemFile, StandardCharsets.UTF_8);
        String b64 = pem.replaceAll("-----BEGIN PRIVATE KEY-----", "")
                .replaceAll("-----END PRIVATE KEY-----", "")
                .replaceAll("\\s", "");
        byte[] decoded = Base64.getDecoder().decode(b64);
        PKCS8EncodedKeySpec spec = new PKCS8EncodedKeySpec(decoded);
        KeyFactory kf = KeyFactory.getInstance("RSA");
        return kf.generatePrivate(spec);
    }

    // ---------- File hybrid encrypt / decrypt ----------
    // Encrypt a file using AES-GCM, encrypt AES key with RSA public key, write JSON package
    public static void encryptFileHybrid(Path inFile, Path outFile, PublicKey recipientPub) throws Exception {
        byte[] plain = Files.readAllBytes(inFile);
        SecretKey aes = generateRandomAesKey(256);
        String aesIvCt = aesGcmEncryptToBase64(aes, plain); // base64(iv||ct)
        String encAesKey = rsaEncryptToBase64(recipientPub, aes.getEncoded());
        String json = "{\n" +
                "  \"alg\":\"AES-GCM\",\n" +
                "  \"encKey\":\"" + encAesKey + "\",\n" +
                "  \"payload\":\"" + aesIvCt + "\"\n" +
                "}";
        Files.writeString(outFile, json, StandardCharsets.UTF_8, StandardOpenOption.CREATE, StandardOpenOption.TRUNCATE_EXISTING);
    }

    // Decrypt file previously produced by encryptFileHybrid
    public static void decryptFileHybrid(Path inFile, Path outFile, PrivateKey recipientPriv) throws Exception {
        String json = Files.readString(inFile, StandardCharsets.UTF_8);
        // Very small JSON parse (no deps) - expect exact names
        String encKey = extractJsonValue(json, "encKey");
        String payload = extractJsonValue(json, "payload");
        byte[] aesKeyBytes = rsaDecryptFromBase64(recipientPriv, encKey);
        SecretKey aes = new SecretKeySpec(aesKeyBytes, "AES");
        byte[] plain = aesGcmDecryptFromBase64(aes, payload);
        Files.write(outFile, plain);
    }

    private static String extractJsonValue(String json, String key) {
        String pattern = "\"" + key + "\"\\s*:\\s*\"";
        int i = json.indexOf(pattern);
        if (i < 0) return null;
        i += pattern.length();
        int j = json.indexOf('"', i);
        if (j < 0) return null;
        return json.substring(i, j);
    }

    // ---------- Utilities ----------
    public static String bytesToHex(byte[] b) {
        StringBuilder sb = new StringBuilder(b.length * 2);
        for (byte x : b) sb.append(String.format("%02x", x & 0xff));
        return sb.toString();
    }

    public static String toBase64(byte[] b) { return Base64.getEncoder().encodeToString(b); }
    public static byte[] fromBase64(String s) { return Base64.getDecoder().decode(s); }
}
