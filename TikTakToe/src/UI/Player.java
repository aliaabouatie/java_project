package UI;

import javax.naming.Name;

public class Player {
    private String Name;
    private Marker Marker;
    private boolean startet;

    public Player(String name, Marker zeichen,boolean startet) {
        Name = name;
        this.Marker = zeichen;
        this.startet = startet;
    }


    public String getName() {
        return Name;
    }

    public void setName(String name) {
        Name = name;
    }

    public Marker getMarker() {
        return Marker;
    }

    public void setMarker(UI.Marker marker) {
        Marker = marker;
    }

    public boolean isStartet() {
        return startet;
    }

    public void setStartet(boolean startet) {
        this.startet = startet;
    }
}
