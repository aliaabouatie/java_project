package UI;

public class Spielwiese {
    public static void main(String[] args) {

        Spielbrett spiel1 = new Spielbrett();
        MinMaxAI computer1 = new MinMaxAI();
        computerSpiele(spiel1, computer1, 'X');
        spiel1.gibSpielbrettAusTemp();
        spiel1.setzeMarker(2,2, 'O');
        spiel1.gibSpielbrettAusTemp();
        computerSpiele(spiel1, computer1, 'X');
        spiel1.gibSpielbrettAusTemp();
        spiel1.setzeMarker(0,1, 'O');
        spiel1.gibSpielbrettAusTemp();
        computerSpiele(spiel1, computer1, 'X');
        spiel1.gibSpielbrettAusTemp();


        //SpielbrettEndlos spiel2 = new SpielbrettEndlos();
        //System.out.println(spiel2.holeMarker(0,0));
        //spiel2.gibSpielbrettAusTemp();
    }

    public static void computerSpiele(Spielbrett aktuellesBrett, MinMaxAI computer, char zeichen){
        int[] ergebnisArray = new int[2];
        ergebnisArray = computer.sucheBestenZug(aktuellesBrett);
        int reihePos = ergebnisArray[0];
        int zeilePos = ergebnisArray[1];

        aktuellesBrett.setzeMarker(reihePos, zeilePos,zeichen);
    }
}
