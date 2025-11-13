import javax.crypto.SecretKeyFactory;
import javax.crypto.spec.PBEKeySpec;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.security.spec.InvalidKeySpecException;
import java.util.HexFormat;

public class Hashes {
    public int npass = 0;
    
    public String getSHA512AmbSalt(String pw, String salt) {
        try {
            MessageDigest md = MessageDigest.getInstance("SHA-512");
            String combined = pw + salt;
            byte[] hashBytes = md.digest(combined.getBytes());
            HexFormat hex = HexFormat.of();
            return hex.formatHex(hashBytes);
        } catch (NoSuchAlgorithmException e) {
            throw new RuntimeException("Error: Algoritme SHA-512 no disponible", e);
        }
    }
    
    public String getPBKDF2AmbSalt(String pw, String salt) {
        try {
            char[] passwordChars = pw.toCharArray();
            byte[] saltBytes = salt.getBytes();
            
            PBEKeySpec spec = new PBEKeySpec(passwordChars, saltBytes, 1000, 128);
            SecretKeyFactory skf = SecretKeyFactory.getInstance("PBKDF2WithHmacSHA1");
            byte[] hashBytes = skf.generateSecret(spec).getEncoded();
            
            HexFormat hex = HexFormat.of();
            return hex.formatHex(hashBytes);
        } catch (NoSuchAlgorithmException | InvalidKeySpecException e) {
            throw new RuntimeException("Error: Algoritme PBKDF2 no disponible", e);
        }
    }
    
    public String forcaBruta(String alg, String hash, String salt) {
        npass = 0;
        String charset = "abcdefABCDEF1234567890!";
        
        for (int length = 6; length >= 1; length++) {
            char[] currentPassword = new char[length];
            String result = generateAndTest(alg, hash, salt, charset, currentPassword, 0);
            if (result != null) {
                return result;
            }
        }
        return null;
    }
    
    private String generateAndTest(String alg, String hash, String salt, String charset, char[] currentPassword, int position) {
        if (position == currentPassword.length) {
            npass++;
            String password = new String(currentPassword);
            String testHash;
            
            if ("SHA-512".equals(alg)) {
                testHash = getSHA512AmbSalt(password, salt);
            } else if ("PBKDF2".equals(alg)) {
                testHash = getPBKDF2AmbSalt(password, salt);
            } else {
                return null;
            }
            
            if (testHash.equals(hash)) {
                return password;
            }
            return null;
        }
        
        for (int i = 0; i < charset.length(); i++) {
            currentPassword[position] = charset.charAt(i);
            String result = generateAndTest(alg, hash, salt, charset, currentPassword, position + 1);
            if (result != null) {
                return result;
            }
        }
        return null;
    }
    
    public String getInterval(long t1, long t2) {
        long milliseconds = t2 - t1;
        long seconds = milliseconds / 1000;
        long minutes = seconds / 60;
        long hours = minutes / 60;
        long days = hours / 24;
        
        return String.format("%d dies / %d hores / %d minuts / %d segons / %d millis",
                           days, hours % 24, minutes % 60, seconds % 60, milliseconds % 1000);
    }
    
    public static void main(String[] args) throws Exception {
        String salt = "qpoweiruañslkdfjz";
        String pw = "aaabF!";
        Hashes h = new Hashes();
        String[] aHashes = { h.getSHA512AmbSalt(pw, salt), 
            h.getPBKDF2AmbSalt(pw, salt) };
        String pwTrobat = null;
        String[] algorismes = {"SHA-512", "PBKDF2"};
        for(int i = 0; i < aHashes.length; i++) {
            System.out.printf("===========================\n");
            System.out.printf("Algorisme: %s\n", algorismes[i]);
            System.out.printf("Hash: %s\n", aHashes[i]);
            System.out.printf("----------------------------\n");
            System.out.printf("-- Inici de força bruta ---\n");

            long t1 = System.currentTimeMillis();
            pwTrobat = h.forcaBruta(algorismes[i], aHashes[i], salt);
            long t2 = System.currentTimeMillis();

            System.out.printf("Pass   : %s\n", pwTrobat);
            System.out.printf("Provats: %d\n", h.npass);
            System.out.printf("Temps  : %s\n", h.getInterval(t1, t2));
            System.out.printf("---------------------------\n\n");
        }
    }
}