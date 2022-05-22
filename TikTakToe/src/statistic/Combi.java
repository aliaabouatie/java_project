package statistic;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintWriter;
import statistic.Player;


public class Combi {
    private String Name;
    private String Ausgang;

    //Alle dinger geholt ===> Müssen wir die nicht einzeln nochmal bestimmen und schleife
    // Brauchen wir das überhaupt?
    /*public Combi(String Spieler1, String Spieler2, String Ausgang) {
        this.Spieler1=Spieler1;
        this.Spieler2=Spieler2;
        this.Ausgang=Ausgang;
        }*/

        // Versuch aktuellen Spielstand ==> wie aktualisieren wir in csv?
        public void fuegeAktlSpielstand(String Player, String Spielstand) {
            int win = 0;
            if (Spielstand.equals(true)) {
                win += 1;
                System.out.println(Spielstand + win );
            }else{
                win += 0;
                System.out.println(Spielstand + win );
            }

        }
        public void Spieleranlegen(String Name) {
            this.Name = Name;


            try (PrintWriter writer = new PrintWriter("statistic.csv")) {

                StringBuilder sb = new StringBuilder();
                sb.append("Name");
                sb.append(',');
                sb.append("Spielstand");
                sb.append('\n');
                ;
                sb.append(Name);
                sb.append(Ausgang);
                writer.write(sb.toString());

                System.out.println("done!");

            } catch (FileNotFoundException e) {
                System.out.println(e.getMessage());
            }


        }
}

