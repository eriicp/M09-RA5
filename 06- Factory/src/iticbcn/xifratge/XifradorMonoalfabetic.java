package iticbcn.xifratge;

import java.util.*;

public class XifradorMonoalfabetic implements Xifrador {
    public final String alfa = "AÁÀBCÇDÉÈFGHIÍÌÏJKLMNÑOÓÒPQRSTUÚÙÜVWXYZ";
    public final char[] alfabet = alfa.toCharArray();
    public char[] alfabetPermutat;
    
    public XifradorMonoalfabetic() {
        this.alfabetPermutat = permutaAlfabet(alfabet);
    }
    
    public char[] permutaAlfabet(char[] alfabet){
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
    
    @Override
    public TextXifrat xifra(String msg, String clau) throws ClauNoSuportada {
        if (clau != null) {
            throw new ClauNoSuportada("Xifratxe monoalfabètic no suporta clau != null");
        }
        
        String resultat = xifraMonoAlfa(msg);
        return new TextXifrat(resultat.getBytes(java.nio.charset.StandardCharsets.UTF_8));
    }

    @Override
    public String desxifra(TextXifrat xifrat, String clau) throws ClauNoSuportada {
        if (clau != null) {
            throw new ClauNoSuportada("Xifratxe monoalfabètic no suporta clau != null");
        }
        
        if (xifrat == null) return null;
        String cadena = new String(xifrat.getBytes(), java.nio.charset.StandardCharsets.UTF_8);
        return desxifraMonoAlfa(cadena);
    }

    public String xifraMonoAlfa(String cadena){
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

    public String desxifraMonoAlfa(String cadena){
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