package com.iticbcn.xifratge;

import java.nio.charset.StandardCharsets;
import java.security.KeyPair;
import java.security.KeyPairGenerator;
import java.security.PrivateKey;
import java.security.PublicKey;

import javax.crypto.Cipher;

public class ClauPublica {
    public KeyPair generaParellClausRSA() throws Exception{
        KeyPairGenerator keyPairGenerator = KeyPairGenerator.getInstance("RSA");
        keyPairGenerator.initialize(2048); 
        KeyPair parellclaus = keyPairGenerator.generateKeyPair();
        return parellclaus;
    }

    public byte[] xifraRSA(String msg, PublicKey clauPublica) throws Exception{
        Cipher cipher = Cipher.getInstance("RSA");
        cipher.init(Cipher.ENCRYPT_MODE, clauPublica);
        byte[] msgBytes = msg.getBytes(StandardCharsets.UTF_8);
        byte[] msgEncriptat = cipher.doFinal(msgBytes);
        return msgEncriptat;
    }

    public String desxifraRSA(byte[] msgXifrat, PrivateKey ClauPrivada) throws Exception{
        Cipher cipher = Cipher.getInstance("RSA");
        cipher.init(Cipher.DECRYPT_MODE, ClauPrivada);
        byte[] msgDesencriptatBytes = cipher.doFinal(msgXifrat);
        String msgDesencriptat = new String(msgDesencriptatBytes, StandardCharsets.UTF_8);
        return msgDesencriptat;
    }
}
