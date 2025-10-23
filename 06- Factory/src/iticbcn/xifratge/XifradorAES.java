package iticbcn.xifratge;
import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;
import java.util.Arrays;

import javax.crypto.Cipher;
import javax.crypto.spec.IvParameterSpec;
import javax.crypto.spec.SecretKeySpec;

public class XifradorAES implements Xifrador{
    private  final String ALGORISME_XIFRAT = "AES";
    private  final String ALGORISME_HASH = "SHA-256";
    private final String FORMAT_AES = "AES/CBC/PKCS5Padding";

    private static final int MIDA_IV = 16;
    private static final String CLAU = "LaClauSecretaQueVulguis";

    public TextXifrat xifra(String msg, String clau) throws ClauNoSuportada{
        
    }
    public String desxifra(TextXifrat xifrat, String clau) throws ClauNoSuportada{

    }

    public byte[] xifraAES(String msg, String password) throws Exception {
        byte[] bytesString = msg.getBytes(StandardCharsets.UTF_8);
        
        byte[] iv = new byte[MIDA_IV];
        SecureRandom randomSecureRandom = new SecureRandom();
        randomSecureRandom.nextBytes(iv);
        IvParameterSpec ivParameterSpec = new IvParameterSpec(iv);
        
        MessageDigest digest = MessageDigest.getInstance(ALGORISME_HASH);
        byte[] hashbytes = digest.digest(password.getBytes(StandardCharsets.UTF_8));
        SecretKeySpec secretKey = new SecretKeySpec(hashbytes, ALGORISME_XIFRAT);

        Cipher cipher = Cipher.getInstance(FORMAT_AES); 
        cipher.init(Cipher.ENCRYPT_MODE, secretKey, ivParameterSpec);
        byte[] msgXifrat = cipher.doFinal(bytesString);

        byte[] resultat = new byte[iv.length + msgXifrat.length];
        System.arraycopy(iv, 0, resultat, 0, iv.length);
        System.arraycopy(msgXifrat, 0, resultat, iv.length, msgXifrat.length);
        return resultat;
    }

    public String desxifraAES(byte[] bMsgXifrat, String password) throws Exception {
        byte[] iv = Arrays.copyOfRange(bMsgXifrat, 0, MIDA_IV);
        
        byte[] msgXifrat = Arrays.copyOfRange(bMsgXifrat, MIDA_IV, bMsgXifrat.length);
        
        MessageDigest digest = MessageDigest.getInstance(ALGORISME_HASH);
        byte[] hashbytes = digest.digest(password.getBytes(StandardCharsets.UTF_8));
        SecretKeySpec secretKey = new SecretKeySpec(hashbytes, ALGORISME_XIFRAT);

        Cipher cipher = Cipher.getInstance(FORMAT_AES);
        cipher.init(Cipher.DECRYPT_MODE, secretKey, new IvParameterSpec(iv));
        byte[] msgDesxifrat = cipher.doFinal(msgXifrat);
        
        return new String(msgDesxifrat, StandardCharsets.UTF_8);
    }

    private String bytesToHex(byte[] bytes) {
        StringBuilder sb = new StringBuilder();
        for (byte b : bytes) {
            sb.append(String.format("%02x", b));
        }
        return sb.toString();
    }
}