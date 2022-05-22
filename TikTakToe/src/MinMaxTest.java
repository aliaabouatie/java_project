public class MinMaxTest {

    public static char[][] datenArray = new char[3][3];


    //kreuz = X
    //kreis = O
    //null = N
    //Computer ist immer Kreuz
    //Maximizer = Kreuz
    //Minimizer = Kreis

    public static void main(String[] args) {
        fuelleFelder();
        datenArray[1][1] = 'O';
        //System.out.println(datenArray);
        System.out.println("Jetziges Spielbrett");
        for (int i = 0; i < 3; i++) {
            System.out.println(datenArray[i][0] + " " + datenArray[i][1] + " " + datenArray[i][2]);
        }
        //sucheBestenZug();
        System.out.println("BesterZug: ");
        System.out.println(sucheBestenZug()[0]);
        System.out.println(sucheBestenZug()[1]);

    }

    public static void fuelleFelder(){ //Alle Felder werden mit N gefüllt, weiß nicht ob ich diese Funktion brauche, wahrscheinlich macht Marc sie
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                datenArray[i][j] = 'N';

            }
        }
    }

    public static boolean checkSpielbrettVoll(){
        boolean spielvorbei = true;
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
               if(datenArray[i][j] == 'N'){
                   spielvorbei = false;
               }
            }
        }
        return spielvorbei;
    }

    public static int miniMax(boolean istMaxDran){
        int spielbrettWert;
        if(checkWinZeichen('X')){
            spielbrettWert = 10;
        }
        else if(checkWinZeichen('O')){
            spielbrettWert = -10;
        } else{
            spielbrettWert = 0;
        }

        if(spielbrettWert == 10 || spielbrettWert == -10 || checkSpielbrettVoll() == true){
            return spielbrettWert;
        }
        if(istMaxDran){
            int hoechsterWert = Integer.MIN_VALUE;
            for(int i = 0; i<3; i++){
                for(int j = 0; j<3; j++){
                    if(datenArray[i][j] == 'N'){
                        datenArray[i][j] = 'X';
                        hoechsterWert = Math.max(hoechsterWert, miniMax(false));
                        datenArray[i][j] = 'N';
                    }
                }
            }
            return hoechsterWert;
        } else{ //Minimizer ist dran
            int niedirgsterWert = Integer.MAX_VALUE;
            for(int i = 0; i<3; i++){
                for(int j = 0; j<3; j++){
                    if(datenArray[i][j] == 'N'){
                        datenArray[i][j] = 'O';
                        niedirgsterWert = Math.min(niedirgsterWert, miniMax(true));
                        datenArray[i][j] = 'N';
                    }
                }
            }
            return  niedirgsterWert;
        }

    }

    public static int[] sucheBestenZug(){ //gibt die Position zurück wo computer setzen soll
        int[] besterZug = new int[2];
        int besterZustandsWert = Integer.MIN_VALUE;

        for(int i = 0; i< 3; i++){
            for(int j = 0; j<3; j++){
                if(datenArray[i][j] == 'N'){
                    datenArray[i][j] = 'X';
                    int zugWert = miniMax(false);
                    datenArray[i][j] = 'N';
                    if(zugWert > besterZustandsWert){
                        besterZug[0] = i;
                        besterZug[1] = j;
                        besterZustandsWert = zugWert;
                    }
                }
            }
        }
        return besterZug;

    }

    public static boolean checkWinZeichen(char zeichen){ //Wenn gewonnen dann true zurückgeben
        boolean hatGewonnen = false;
        for(int i = 0; i<=2; i++) {//prüft horizontal
            if (datenArray[i][0] == zeichen && datenArray[i][1] == zeichen && datenArray[i][2] == zeichen) {
                hatGewonnen = true;
                return hatGewonnen;
            }
            if (datenArray[0][i] == zeichen && datenArray[1][i] == zeichen && datenArray[2][i] == zeichen) {
                hatGewonnen = true;
                return hatGewonnen;
            }
        }
        if (datenArray[0][0] == zeichen && datenArray[1][1] == zeichen && datenArray[2][2] == zeichen) {//prüft erste diagonale
            hatGewonnen = true;
            return hatGewonnen;
        }
        if (datenArray[0][2] == zeichen && datenArray[1][1] == zeichen && datenArray[2][0] == zeichen) {//prüft zweite diagonale
            hatGewonnen = true;
            return hatGewonnen;
        }
        return hatGewonnen;
    }




}
