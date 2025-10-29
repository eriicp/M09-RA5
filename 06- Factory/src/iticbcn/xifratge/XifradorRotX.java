package iticbcn.xifratge;

public class XifradorRotX implements Xifrador {
    // Alfabetos corregidos basados en tu implementación original
    final static String minuscules = "aáàbcçdeéèfghiíìïjklmnñoóòpqrstuúùüvwxyz";
    final static String majuscules = "AÁÀBCÇDEÉÈFGHIÍÌÏJKLMNÑOÓÒPQRSTUÚÙÜVWXYZ";
    final static char[] mArray = minuscules.toCharArray();
    final static char[] MArray = majuscules.toCharArray();
    
    @Override
    public TextXifrat xifra(String msg, String clau) throws ClauNoSuportada {
        try {
            int rotacio = Integer.parseInt(clau);
            if (rotacio < 0 || rotacio > 40) {
                throw new ClauNoSuportada("Clau de RotX ha de ser un sencer de 0 a 40");
            }
            
            String resultat = xifraRotX(msg, rotacio);
            return new TextXifrat(resultat.getBytes(java.nio.charset.StandardCharsets.UTF_8));
        } catch (NumberFormatException e) {
            throw new ClauNoSuportada("Clau de RotX ha de ser un sencer de 0 a 40");
        }
    }

    @Override
    public String desxifra(TextXifrat xifrat, String clau) throws ClauNoSuportada {
        try {
            if (xifrat == null) return null;
            
            int rotacio = Integer.parseInt(clau);
            if (rotacio < 0 || rotacio > 40) {
                throw new ClauNoSuportada("Clau de RotX ha de ser un sencer de 0 a 40");
            }
            
            String msgXifrat = new String(xifrat.getBytes(), java.nio.charset.StandardCharsets.UTF_8);
            return desxifraRotX(msgXifrat, rotacio);
        } catch (NumberFormatException e) {
            throw new ClauNoSuportada("Clau de RotX ha de ser un sencer de 0 a 40");
        }
    }

    public String xifraRotX (String cadena, int desplaçament){
        String resultat = "";
        for(int i=0;i < cadena.length();i++){
            char c = cadena.charAt(i);
            if(Character.isLowerCase(c)){
                for(int j = 0; j < mArray.length;j++){
                    if(c == mArray[j]){
                        c = mArray[(j + desplaçament) % mArray.length];
                        break;
                    }
                }
            }
            else{
                for(int j = 0; j < MArray.length;j++){
                    if(c == MArray[j]){
                        c = MArray[(j + desplaçament) % MArray.length];
                        break;
                    }
                }
            }
            resultat += c;
        }
        return resultat;
    }

    public String desxifraRotX (String cadena, int desplaçament){
        String resultat = "";
        for(int i=0;i < cadena.length();i++){
            char c = cadena.charAt(i);
            if(Character.isLowerCase(c)){
                for(int j = 0; j < mArray.length;j++){
                    if(c == mArray[j]){
                        c = mArray[(j - desplaçament + mArray.length) % mArray.length];
                        break;
                    }
                }
            }
            else{
                for(int j = 0; j < MArray.length;j++){
                    if(c == MArray[j]){
                        c = MArray[(j - desplaçament + MArray.length) % MArray.length];
                        break;
                    }
                }
            }
            resultat += c;
        }
        return resultat;
    }
}