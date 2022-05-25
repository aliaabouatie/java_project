package UI;

public class Marker {
    protected char zeichen;

    public Marker(){}
    public Marker (char zeichen){
        this.zeichen = zeichen;
    }

    public char getZeichen() {
        return zeichen;
    }

    public void setZeichen(char zeichen){
        this.zeichen = zeichen;
    }
}
