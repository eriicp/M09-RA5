public class Rot13{
    public static void main(String[] args){
        System.out.println(xifraRot13("b"));
    }

    public static String xifraRot13 (String cadena){
        String resultat = "";
        String minuscules = "aáàbcçdéèfghiíìïjklmnñóòpqrstuúùüvwxyz";
        String majuscules = "AÁÀBCÇDÉÈFGHIÍÌÏJKLMNÑÓÒPQRSTUÚÙÜVWXYZ";
        char[] mArray = minuscules.toCharArray();
        char[] MArray = majuscules.toCharArray();
        for(int i=0;i < cadena.length();i++){
            char c = cadena.charAt(i);
            if(Character.isLowerCase(c)){
                for(int j = 0; j < mArray.length;i++){
                    if(c == mArray[j]){
                        c = mArray[j + 13];
                        break;
                    }
                }
            }
            else{
                for(int j = 0; j < MArray.length;i++){
                    if(c == MArray[j]){
                        c = MArray[j + 13];
                        break;
                    }
                }
            }
            resultat += c;
        }
        return resultat;
    }

    public static String desxifraRot13 (String cadena){
        String resultat = "";
        String minuscules = "aáàbcçdéèfghiíìïjklmnñóòpqrstuúùüvwxyz";
        String majuscules = "AÁÀBCÇDÉÈFGHIÍÌÏJKLMNÑÓÒPQRSTUÚÙÜVWXYZ";
        char[] mArray = minuscules.toCharArray();
        char[] MArray = majuscules.toCharArray();
        for(int i=0;i < cadena.length();i++){
            char c = cadena.charAt(i);
            if(Character.isLowerCase(c)){
                for(int j = 0; j < mArray.length;i++){
                    if(c == mArray[j]){
                        c = mArray[j - 13];
                        break;
                    }
                }
            }
            else{
                for(int j = 0; j < MArray.length;i++){
                    if(c == MArray[j]){
                        c = MArray[j - 13];
                        break;
                    }
                }
            }
            resultat += c;
        }
        return resultat;
    }
}