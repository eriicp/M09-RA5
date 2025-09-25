public class RotX{
        final static String minuscules = "aáàbcçdéèfghiíìïjklmnñoóòpqrstuúùüvwxyz";
        final static String majuscules = "AÁÀBCÇDÉÈFGHIÍÌÏJKLMNÑOÓÒPQRSTUÚÙÜVWXYZ";
        final static char[] mArray = minuscules.toCharArray();
        final static char[] MArray = majuscules.toCharArray();
    public static void main(String[] args) {
        int[] numDesplaçat = {0,2,4,6};
        String[] paraulesProba = {"ABC","XYZ","Hola, Mr. calçot","Perdó, per tu què és?"};
        System.out.println("\nXifrat"+"\n---------");
        for(int i = 0;i < paraulesProba.length;i++){
            System.out.println("("+numDesplaçat[i]+")"+paraulesProba[i] + "         =>" +xifraRotX(paraulesProba[i],numDesplaçat[i]));
        }
        System.out.println("\nDesxifrat"+"\n---------");
        for(int i = 0;i < paraulesProba.length;i++){
            System.out.println("("+numDesplaçat[i]+")"+xifraRotX(paraulesProba[i],numDesplaçat[i])+ "         =>" + desxifraRotX(xifraRotX(paraulesProba[i],numDesplaçat[i]),numDesplaçat[i]));
        }
        System.out.println("\nMissatge xifrat:" + xifraRotX(paraulesProba[3], numDesplaçat[3])+"\n---------");
        forcaBrutaRotX(xifraRotX(paraulesProba[3], numDesplaçat[3]));
    }

public static String xifraRotX (String cadena, int desplaçament){
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

    public static String desxifraRotX (String cadena, int desplaçament){
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

   public static String forcaBrutaRotX(String cadenaXifrada){
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