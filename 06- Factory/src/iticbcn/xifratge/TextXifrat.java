package iticbcn.xifratge;
public class TextXifrat {
    public String dadesXifrades;
    public byte[] bytes;

    @Override
    public String toString(){
        return new String(bytes);
    }

    public TextXifrat(String msg, String clau){

    }

    public byte[] getBytes(){
        return this.bytes;
    }
}
