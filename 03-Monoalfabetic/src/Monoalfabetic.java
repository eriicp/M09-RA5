import java.util.*;

public class Monoalfabetic {
    public static final String alfa = "AÁÀBCÇDÉÈFGHIÍÌÏJKLMNÑOÓÒPQRSTUÚÙÜVWXYZ";
    public static final char[] alfabet = alfa.toCharArray();
    public static char[] alfabetPermutat = permutaAlfabet(alfabet);
    public static void main(String[] args) {
        String[] paraulesProba = {"ABC","XYZ","Hola, Mr. calçot","Perdó, per tu què és?"};
        System.out.println("\nXifrat"+"\n---------");
        for(int i = 0;i < paraulesProba.length;i++){
            System.out.println(paraulesProba[i]+" -> " + xifraMonoAlfa(paraulesProba[i]));
        }
        System.out.println("\nDesxifrat"+"\n---------");
        for(int i = 0;i < paraulesProba.length;i++){
            System.out.println(xifraMonoAlfa(paraulesProba[i])+ " -> " + desxifraMonoAlfa(xifraMonoAlfa(paraulesProba[i])));
        }
        System.out.println("\nAlfabet xifrat"+"\n---------");
        for(int i = 0;i < alfabet.length;i++){
            System.out.println(alfabet[i]+" -> " + alfabetPermutat[i]);
        }
        System.out.println("\nDesxifrat"+"\n---------");
        for(int i = 0;i < alfabet.length;i++){
            System.out.println(alfabetPermutat[i]+" -> " + alfabet[i]);
        }
    }

    public static char[] permutaAlfabet(char[] alfabet){
        List<Character> alfabetList = new ArrayList<Character>();
        String resultat = "";
        for(int i = 0;i < alfabet.length;i++){
            alfabetList.add(alfabet[i]);
        }
        Collections.shuffle(alfabetList);
        for(int i = 0;i < alfabet.length;i++){
            resultat = resultat  + alfabetList.get(i);
        }
        return resultat.toCharArray();
    }

    public static String xifraMonoAlfa(String cadena){
        String resultat ="";
        for(int i=0;i < cadena.length();i++){
            char c = cadena.charAt(i);
            if(Character.isLowerCase(c)){
                c = Character.toUpperCase(c);
                for(int j = 0; j < alfabet.length;j++){
                    if(c == alfabet[j]){
                        c = alfabetPermutat[j];
                        break;
                    }
                }
                c = Character.toLowerCase(c);
            }
            else{
               for(int j = 0; j < alfabet.length;j++){
                    if(c == alfabet[j]){
                        c = alfabetPermutat[j];
                        break;
                    }
                }
            }
            resultat += c;
        }
        return resultat;
    }

    public static String desxifraMonoAlfa(String cadena){
       String resultat ="";
        for(int i=0;i < cadena.length();i++){
            char c = cadena.charAt(i);
            if(Character.isLowerCase(c)){
                c = Character.toUpperCase(c);
                for(int j = 0; j < alfabet.length;j++){
                    if(c == alfabetPermutat[j]){
                        c = alfabet[j];
                        break;
                    }
                }
                c = Character.toLowerCase(c);
            }
            else{
               for(int j = 0; j < alfabet.length;j++){
                    if(c == alfabetPermutat[j]){
                        c = alfabet[j];
                        break;
                    }
                }
            }
            resultat += c;
        }
        return resultat;  
    }
}