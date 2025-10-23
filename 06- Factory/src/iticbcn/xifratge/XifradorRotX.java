package iticbcn.xifratge;
public class XifradorRotX implements Xifrador{
        final String minuscules = "aáàbcçdéèfghiíìïjklmnñoóòpqrstuúùüvwxyz";
        final String majuscules = "AÁÀBCÇDÉÈFGHIÍÌÏJKLMNÑOÓÒPQRSTUÚÙÜVWXYZ";
        final char[] mArray = minuscules.toCharArray();
        final char[] MArray = majuscules.toCharArray();

    public  String xifraRotX (String cadena, int desplaçament){
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

    public  String desxifraRotX (String cadena, int desplaçament){
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

   public  String forcaBrutaRotX(String cadenaXifrada){
        String resultat = "";
        for (int x = 0; x <= minuscules.length();x++){
            for(int i=0;i < cadenaXifrada.length();i++){
                resultat = desxifraRotX(cadenaXifrada, x);
            }
            System.out.println("("+x+")->"+resultat);
            resultat = "";
        }
        return "";
   } 
}