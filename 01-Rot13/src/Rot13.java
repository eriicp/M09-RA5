public class Rot13{
    public static void main(String[] args){
        System.out.println("Xifrat");
        System.out.println("---------");
        for(int i = 0;i < args.length;i++){
            System.out.println(args[i] + "         =>" +xifraRot13(args[i]));
        }
        System.out.println("Desxifrat");
        System.out.println("---------");
        for(int i = 0;i < args.length;i++){
            System.out.println(xifraRot13(args[i])+ "         =>" + desxifraRot13(xifraRot13(args[i])));
        }
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
                for(int j = 0; j < mArray.length;j++){
                    if(c == mArray[j]){
                        c = mArray[(j + 13) % mArray.length];
                        break;
                    }
                }
            }
            else{
                for(int j = 0; j < MArray.length;j++){
                    if(c == MArray[j]){
                        c = MArray[(j + 13) % MArray.length];
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
                for(int j = 0; j < mArray.length;j++){
                    if(c == mArray[j]){
                        c = mArray[(j - 13 + mArray.length) % mArray.length];
                        break;
                    }
                }
            }
            else{
                for(int j = 0; j < MArray.length;j++){
                    if(c == MArray[j]){
                        c = MArray[(j - 13 + MArray.length) % MArray.length];
                        break;
                    }
                }
            }
            resultat += c;
        }
        return resultat;
    }
}