package iticbcn.xifratge;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Random;

public class XifradorPolialfabetic implements Xifrador{
    private  long clauSecreta = 8;
    public  final char[] alfabet = "AÁÀBCÇDEÉÈFGHIÍÌÏJKLMNÑOÓÒPQRSTUÚÙÜVWXYZ".toCharArray();
    private  char[] alfabetPermutat;
    private  Random random; 

    public void initRandom(long clauSecreta){
        random = new Random(clauSecreta);
    }

    public void permutaAlfabet(char[] alfabet){
        List<Character> alfabetList = new ArrayList<Character>();
        String resultat = "";
        for(int i = 0;i < alfabet.length;i++){
            alfabetList.add(alfabet[i]);
        }
        Collections.shuffle(alfabetList, random);
        for(int i = 0;i < alfabet.length;i++){
            resultat = resultat  + alfabetList.get(i);
        }
        alfabetPermutat = resultat.toCharArray();
    }

    public String xifraPoliAlfa(String msg){
        String resultat ="";
        for(int i=0;i < msg.length();i++){
            char c = msg.charAt(i);
            permutaAlfabet(alfabet);
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

    public String desxifraPoliAlfa(String msgXifrat){
        String resultat ="";
        for(int i=0;i < msgXifrat.length();i++){
            char c = msgXifrat.charAt(i);
            permutaAlfabet(alfabet);
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