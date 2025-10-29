package iticbcn.xifratge;

import java.nio.charset.StandardCharsets;

public class TextXifrat {
    private byte[] bytes;
    
    public TextXifrat(byte[] bytes) {
        this.bytes = bytes;
    }
    
    public byte[] getBytes() {
        return bytes;
    }
    
    @Override
    public String toString() {
        if (bytes == null) return "null";
        try {
            String text = new String(bytes, StandardCharsets.UTF_8);
            
            boolean hasControlChars = false;
            for (byte b : bytes) {
                if (b < 32 && b != 9 && b != 10 && b != 13) { 
                    hasControlChars = true;
                    break;
                }
            }
            
            if (hasControlChars) {
                StringBuilder hex = new StringBuilder();
                for (byte b : bytes) {
                    hex.append(String.format("%02x", b));
                }
                return hex.toString();
            } else {
                return text;
            }
        } catch (Exception e) {
            return "[Datos cifrados]";
        }
    }
}