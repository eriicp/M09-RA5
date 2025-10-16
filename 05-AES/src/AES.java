import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;

import javax.crypto.Cipher;
import javax.crypto.spec.IvParameterSpec;
import javax.crypto.spec.SecretKeySpec;

public class AES {
    public static final String ALGORISME_XIFRAT = "AES";
    public static final String ALGORISME_HASH = "SHA-256";
    public static final String FORMAT_AES = "AES/CBC/PKCS5Padding";

    private static final int MIDA_IV = 16;
    private static byte[] iv = new byte [MIDA_IV];
    private static final String CLAU = "LaClauSecretaQueVulguis";
    
    public static void main(String[] args) {
        String msgs[] = {"Lorem ipsum dicet",
                        "Hola Andrés cómo está tu cuñado",
                        "Agora Illa Otto"};

        for (int i = 0; i < msgs.length; i++) {
                String msg = msgs[1];
                
                byte[] bXifrats = null;
                String desxifrat = "";
                try {
                    bXifrats = xifraAES (msg, CLAU);
                    desxifrat = desxifraAES(bXifrats, CLAU);
                } catch (Exception e) {
                System.err.println("Error de xifrat: "
                    +e.getLocalizedMessage());
                }            
            System.out.println("");
            System.out.println("Msg: "+msg);
            System.out.println("Enc: "+ new String(bXifrats));
            System.out.println("DEC: + desxifrat");
        }
    }

    public static byte[] xifraAES(String msg, String password)throws Exception{
        byte[] bytesString = msg.getBytes();
        
        IvParameterSpec ivParameterSpec = generaIvParameterSpec();
        
        MessageDigest digest = MessageDigest.getInstance(ALGORISME_HASH);
        byte[] hashbytes = digest.digest(password.getBytes(StandardCharsets.UTF_8));
        SecretKeySpec secretKey = new SecretKeySpec(hashbytes, ALGORISME_XIFRAT);

        Cipher cipher = Cipher.getInstance(ALGORISME_XIFRAT);
        cipher.init(Cipher.ENCRYPT_MODE, secretKey, ivParameterSpec);
        byte[] msgXifrat = cipher.doFinal(bytesString);

        byte[] resultat = new byte[iv.length + msgXifrat.length];
        System.arraycopy(iv, 0, resultat, 0, iv.length);
        System.arraycopy(msgXifrat, 0, resultat, iv.length, msgXifrat.length);
        return resultat;
    }

    public static IvParameterSpec generaIvParameterSpec() throws NoSuchAlgorithmException{
        SecureRandom randomSecureRandom = SecureRandom.getInstance(ALGORISME_XIFRAT);
        randomSecureRandom.nextBytes(iv);
        IvParameterSpec ivParams = new IvParameterSpec(iv);
        return ivParams;
    }

    public static String desxifraAES(byte[] bMsgXifrat, String password){
        return "si";
    }
}