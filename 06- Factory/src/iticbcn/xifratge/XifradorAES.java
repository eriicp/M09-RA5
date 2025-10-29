package iticbcn.xifratge;

import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.security.SecureRandom;
import java.util.Arrays;

import javax.crypto.Cipher;
import javax.crypto.spec.IvParameterSpec;
import javax.crypto.spec.SecretKeySpec;

public class XifradorAES implements Xifrador {
    private static final String ALGORISME_XIFRAT = "AES";
    private static final String ALGORISME_HASH = "SHA-256";
    private static final String FORMAT_AES = "AES/CBC/PKCS5Padding";
    private static final int MIDA_IV = 16;
    
    @Override
    public TextXifrat xifra(String msg, String clau) throws ClauNoSuportada {
        try {
            byte[] bytesString = msg.getBytes(StandardCharsets.UTF_8);
            
            // Generar IV único para cada encriptación
            byte[] iv = new byte[MIDA_IV];
            SecureRandom randomSecureRandom = new SecureRandom();
            randomSecureRandom.nextBytes(iv);
            IvParameterSpec ivParameterSpec = new IvParameterSpec(iv);
            
            // Generar clave a partir del password
            MessageDigest digest = MessageDigest.getInstance(ALGORISME_HASH);
            byte[] hashbytes = digest.digest(clau.getBytes(StandardCharsets.UTF_8));
            SecretKeySpec secretKey = new SecretKeySpec(hashbytes, ALGORISME_XIFRAT);

            // Cifrar
            Cipher cipher = Cipher.getInstance(FORMAT_AES);
            cipher.init(Cipher.ENCRYPT_MODE, secretKey, ivParameterSpec);
            byte[] msgXifrat = cipher.doFinal(bytesString);

            // Combinar IV + mensaje cifrado
            byte[] resultat = new byte[iv.length + msgXifrat.length];
            System.arraycopy(iv, 0, resultat, 0, iv.length);
            System.arraycopy(msgXifrat, 0, resultat, iv.length, msgXifrat.length);
            return new TextXifrat(resultat);
        } catch (Exception e) {
            System.err.println("Error de xifrat AES: " + e.getLocalizedMessage());
            System.exit(1);
            return null;
        }
    }

    @Override
    public String desxifra(TextXifrat xifrat, String clau) throws ClauNoSuportada {
        try {
            byte[] bMsgXifrat = xifrat.getBytes();
            
            // Extraer IV (primeros 16 bytes)
            byte[] iv = Arrays.copyOfRange(bMsgXifrat, 0, MIDA_IV);
            
            // Extraer mensaje cifrado (resto de bytes)
            byte[] msgXifrat = Arrays.copyOfRange(bMsgXifrat, MIDA_IV, bMsgXifrat.length);
            
            // Generar clave a partir del password
            MessageDigest digest = MessageDigest.getInstance(ALGORISME_HASH);
            byte[] hashbytes = digest.digest(clau.getBytes(StandardCharsets.UTF_8));
            SecretKeySpec secretKey = new SecretKeySpec(hashbytes, ALGORISME_XIFRAT);

            // Descifrar
            Cipher cipher = Cipher.getInstance(FORMAT_AES);
            cipher.init(Cipher.DECRYPT_MODE, secretKey, new IvParameterSpec(iv));
            byte[] msgDesxifrat = cipher.doFinal(msgXifrat);
            
            return new String(msgDesxifrat, StandardCharsets.UTF_8);
        } catch (Exception e) {
            System.err.println("Error de desxifrat AES: " + e.getLocalizedMessage());
            System.exit(1);
            return null;
        }
    }
}