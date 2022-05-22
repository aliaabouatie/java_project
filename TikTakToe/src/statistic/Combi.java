package statistic;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.StringTokenizer;
import java.nio.StandardOpenOption;
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
    public void SpielerInCSV(String Player,boolean Spielstand){
        BufferedWriter meinWriter= Files.newBufferedWriter((csv, StandardOpenOption.APPEND));
    }ard
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
                Spielstand.toString
                writer.write(sb.toString());

                System.out.println("done!");

            } catch (FileNotFoundException e) {
                System.out.println(e.getMessage());
            }

            try {
                Path csv= Paths.get("/Users/aliaabouatie/java_project/statistic.csv");

                BufferedReader meinReader= Files.newBufferedReader(csv);
                int zeilenCounter= 1;

                String zeile= meinReader.readLine();

                zeile =  meinReader.readLine();
                /*String currentCsvString = "";
                Player currentCsvString= null;*/

                while (zeile != null) { // null wenn am Dateiende
                    System.out.println(zeilenCounter + ": " + zeile);
                    zeilenCounter++;

                    zeile = zeile.replace(",,", ",0,");
                    StringTokenizer st = new StringTokenizer(zeile, ",");

                    String Player = st.nextToken();
                    boolean Spielstand= Boolean.parseBoolean(st.nextToken());

                    zeile = meinReader.readLine(); // naechste Zeile
                }
                meinReader.close();




            }
            catch (Exception e) {
                e.printStackTrace();
            }

        }
}

