import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;
import java.util.Arrays;

import javax.crypto.Cipher;
import javax.crypto.spec.IvParameterSpec;
import javax.crypto.spec.SecretKeySpec;

public class AES {
    public static final String ALGORISME_XIFRAT = "AES";
    public static final String ALGORISME_HASH = "SHA-256";
    public static final String FORMAT_AES = "AES/CBC/PKCS5Padding";

    private static final int MIDA_IV = 16;
    private static final String CLAU = "LaClauSecretaQueVulguis";
    
    public static void main(String[] args) {
        String msgs[] = {"Lorem ipsum dicet",
                        "Hola Andrés cómo está tu cuñado",
                        "Agora Illa Otto"};

        for (int i = 0; i < msgs.length; i++) {
                String msg = msgs[i]; 
                
                byte[] bXifrats = null;
                String desxifrat = "";
                try {
                    bXifrats = xifraAES(msg, CLAU);
                    desxifrat = desxifraAES(bXifrats, CLAU);
                } catch (Exception e) {
                    System.err.println("Error de xifrat: " + e.getLocalizedMessage());
                    e.printStackTrace();
                }            
            System.out.println("");
            System.out.println("Msg: " + msg);
            System.out.println("Enc (hex): " + bytesToHex(bXifrats));
            System.out.println("DEC: " + desxifrat); 
        }
    }

    public static byte[] xifraAES(String msg, String password) throws Exception {
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

    public static String desxifraAES(byte[] bMsgXifrat, String password) throws Exception {
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

    private static String bytesToHex(byte[] bytes) {
        StringBuilder sb = new StringBuilder();
        for (byte b : bytes) {
            sb.append(String.format("%02x", b));
        }
        return sb.toString();
    }
}