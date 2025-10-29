package iticbcn.xifratge;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Random;

public class XifradorPolialfabetic implements Xifrador {
    public final char[] alfabet = "AÁÀBCÇDEÉÈFGHIÍÌÏJKLMNÑOÓÒPQRSTUÚÙÜVWXYZ".toCharArray();
    private char[] alfabetPermutat;
    private Random random; 
    private long clauActual;

    @Override
    public TextXifrat xifra(String msg, String clau) throws ClauNoSuportada {
        try {
            long seed = Long.parseLong(clau);
            initRandom(seed);
            String resultat = xifraPoliAlfa(msg);
            return new TextXifrat(resultat.getBytes(java.nio.charset.StandardCharsets.UTF_8));
        } catch (NumberFormatException e) {
            throw new ClauNoSuportada("La clau per xifrat Polialfabètic ha de ser un String convertible a long");
        }
    }

    @Override
    public String desxifra(TextXifrat xifrat, String clau) throws ClauNoSuportada {
        try {
            if (xifrat == null) return null;
            
            long seed = Long.parseLong(clau);
            initRandom(seed);
            String msgXifrat = new String(xifrat.getBytes(), java.nio.charset.StandardCharsets.UTF_8);
            return desxifraPoliAlfa(msgXifrat);
        } catch (NumberFormatException e) {
            throw new ClauNoSuportada("La clau de Polialfabètic ha de ser un String convertible a long");
        }
    }

    public void initRandom(long clauSecreta){
        random = new Random(clauSecreta);
        clauActual = clauSecreta;
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
        // Reiniciar el random para asegurar la misma secuencia
        initRandom(clauActual);
        
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
        // Reiniciar el random para asegurar la misma secuencia
        initRandom(clauActual);
        
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