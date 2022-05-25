package UI;

public class MinMaxAI {
    public int miniMax(Spielbrett aktuellesBrett, boolean istMaxDran){
        int spielbrettWert;
        if(aktuellesBrett.checkWinZeichen('X')){
            spielbrettWert = 10;
        }
        else if(aktuellesBrett.checkWinZeichen('O')){
            spielbrettWert = -10;
        } else{
            spielbrettWert = 0;
        }

        if(spielbrettWert == 10 || spielbrettWert == -10 || aktuellesBrett.checkSpielfeldVoll() == true){
            return spielbrettWert;
        }
        if(istMaxDran){
            int hoechsterWert = Integer.MIN_VALUE;
            for(int i = 0; i<3; i++){
                for(int j = 0; j<3; j++){
                    if(aktuellesBrett.holeMarker(i,j).getZeichen() == 'N'){
                        aktuellesBrett.setzeMarker(i,j,'X');
                        hoechsterWert = Math.max(hoechsterWert, miniMax(aktuellesBrett, false));
                        aktuellesBrett.setzeMarker(i,j,'N');
                    }
                }
            }
            return hoechsterWert;
        } else{ //Minimizer ist dran
            int niedirgsterWert = Integer.MAX_VALUE;
            for(int i = 0; i<3; i++){
                for(int j = 0; j<3; j++){
                    if(aktuellesBrett.holeMarker(i,j).getZeichen() == 'N'){
                        aktuellesBrett.setzeMarker(i,j,'O');
                        niedirgsterWert = Math.min(niedirgsterWert, miniMax(aktuellesBrett, true));
                        aktuellesBrett.setzeMarker(i,j,'N');
                    }
                }
            }
            return  niedirgsterWert;
        }

    }

    public int[] sucheBestenZug(Spielbrett aktuellesBrett){ //gibt die Position zurück wo computer setzen soll
        int[] besterZug = new int[2];
        int besterZustandsWert = Integer.MIN_VALUE;

        for(int i = 0; i< 3; i++){
            for(int j = 0; j<3; j++){
                if(aktuellesBrett.holeMarker(i,j).getZeichen() == 'N'){
                    aktuellesBrett.setzeMarker(i,j,'X');
                    int zugWert = miniMax(aktuellesBrett, false);
                    aktuellesBrett.setzeMarker(i,j,'N');
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

}
