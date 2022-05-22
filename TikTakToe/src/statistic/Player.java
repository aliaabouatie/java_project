package statistic;

public class Player {

    private String Spielername;
    private boolean Spielstand;

    public Player(String Spielername, boolean Spielstand){
        this.Spielername= Spielername;
        this.Spielstand= Spielstand;
    }
    public void gewonnen() {Spielstand = true;}
    public void verloren() {Spielstand = false;}
    public boolean Ausgang() {return !Spielstand;}


    //public String toString() {
      //  String Ausgang;
       // if (Ausgang ()) {
        //    Spielstand = "Gewonnen!";
       // } else {
        //    Spielstand = "Verloren!";
       // }
       // return super.toString() + " (Spieler(in)) - " + Spielstand + " )";
    //}
}

