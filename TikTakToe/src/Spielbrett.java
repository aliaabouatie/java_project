public class Spielbrett {
    protected Marker[][] spielfeld;
    public Spielbrett(){
        spielfeld = new Marker[3][3];
        fuelleFelder();
    }

    public void setzeMarker (int reihe, int spalte, char zeichen){
        spielfeld[reihe][spalte] = new Marker(zeichen);
    }

    public Marker holeMarker (int reihe, int spalte){
        return spielfeld[reihe][spalte];
    }

    public void fuelleFelder(){ //Alle Felder werden mit N gefüllt, weiß nicht ob ich diese Funktion brauche, wahrscheinlich macht Marc sie
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                this.spielfeld[i][j] = new Marker('N');
            }
        }
    }
    public boolean checkSpielfeldVoll(){
        boolean spielvorbei = true;
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                if(this.spielfeld[i][j].getZeichen() == 'N'){
                    spielvorbei = false;
                }
            }
        }
        return spielvorbei;
    }

    public void gibSpielbrettAusTemp(){
        System.out.println("Jetziges Spielbrett");
        for (int i = 0; i < 3; i++) {
            System.out.println(spielfeld[i][0].getZeichen() + " " + spielfeld[i][1].getZeichen() + " " + spielfeld[i][2].getZeichen());
        }
    }

    public boolean checkWinZeichen(char zeichen){ //Wenn gewonnen dann true zurückgeben
        boolean hatGewonnen = false;
        for(int i = 0; i<=2; i++) {//prüft horizontal
            if (spielfeld[i][0].getZeichen() == zeichen && spielfeld[i][1].getZeichen() == zeichen && spielfeld[i][2].getZeichen() == zeichen) {
                hatGewonnen = true;
                return hatGewonnen;
            }
            if (spielfeld[0][i].getZeichen() == zeichen && spielfeld[1][i].getZeichen() == zeichen && spielfeld[2][i].getZeichen() == zeichen) {
                hatGewonnen = true;
                return hatGewonnen;
            }
        }
        if (spielfeld[0][0].getZeichen() == zeichen && spielfeld[1][1].getZeichen() == zeichen && spielfeld[2][2].getZeichen() == zeichen) {//prüft erste diagonale
            hatGewonnen = true;
            return hatGewonnen;
        }
        if (spielfeld[0][2].getZeichen() == zeichen && spielfeld[1][1].getZeichen() == zeichen && spielfeld[2][0].getZeichen() == zeichen) {//prüft zweite diagonale
            hatGewonnen = true;
            return hatGewonnen;
        }
        return hatGewonnen;
    }
}
