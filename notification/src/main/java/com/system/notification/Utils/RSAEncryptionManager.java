package com.system.notification.Utils;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;

import javax.crypto.Cipher;
import java.io.File;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.security.KeyFactory;
import java.security.PrivateKey;
import java.security.spec.PKCS8EncodedKeySpec;
import java.util.Base64;

public class RSAEncryptionManager {
    public static final Logger logger = LoggerFactory.getLogger(RSAEncryptionManager.class);
    @Value(value = "${rsa.public-key.path}")
    private String publicKeyPath;
    @Value(value = "${rsa.private-key.path}")
    private String privateKeyPath;
    public static final String RSA_PADDING = "RSA/ECB/PKCS1Padding";


    public byte[] getRSAFileBytes(String filePath) throws IOException {
        File publicKeyFile = new File(filePath);
        return Files.readAllBytes(publicKeyFile.toPath());
    }

    public PrivateKey encodeKeySpecsPrivate() throws Exception {
        PKCS8EncodedKeySpec keySpecPrivate = new PKCS8EncodedKeySpec(getRSAFileBytes(privateKeyPath));
        KeyFactory keyFactory = KeyFactory.getInstance("RSA");
        return keyFactory.generatePrivate(keySpecPrivate);
    }

    private static byte[] decode(String data) {
        return Base64.getDecoder().decode(data);
    }

    public String decrypt(String encryptedMessage) {
        try {
            byte[] encryptedBytes = decode(encryptedMessage);
            Cipher cipher = Cipher.getInstance(RSA_PADDING);
            cipher.init(Cipher.DECRYPT_MODE, encodeKeySpecsPrivate());
            byte[] decryptedMessage = cipher.doFinal(encryptedBytes);
            return new String(decryptedMessage, StandardCharsets.UTF_8);
        } catch (Exception ex){
            logger.warn("decrypt(): getting exception while decrypting message.");
            return null;
        }
    }

}
