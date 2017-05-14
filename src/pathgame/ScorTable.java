package pathgame;

/**
 * Created by Piotrek on 2017-01-06.
 */

import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.BufferedReader;
import java.io.FileReader;
import java.util.Formatter;

/**
 * Klasa operaujaca tabela punktow. Wczytuje tabele najlepszych wynikow z pliku tekstowego i tez do niej je zapisuje.
 */
public class ScorTable {
    /**
     * Maksymalna liczba rekordow tabeli wynikow
     */
    private int maxRankTable = 5;
    /**
     * Maksymalna liczba kolumn w tabeli
     */
    int maxCol = 2;
    /**
     * tablica liczby punktow graczy
     */
    private int[] rankTable = new int[maxRankTable];
    /**
     * Tablica nickow graczy
     */
    private String[] rankTableNames = new String[maxRankTable];
    /**
     * formater do zapisu do plik
     */
    private Formatter x;


    private Font font;

    /**
     * Konstruktor tabeli punktow pobiera tabele w postaci pliku tekstowego i go konwertuje do tablicy.
     * @param s sciezka do pliku tekstowego.
     */
    public ScorTable(String s) {
        try {
            BufferedReader br = new BufferedReader(new FileReader(s));
            maxRankTable = Integer.parseInt(br.readLine());
            System.out.println("MaxRankTable " + maxRankTable);
            String delimiters = "\\s+";
            for (int row = 0; row < maxRankTable; row++) {
                String line = br.readLine();
                String[] tokens = line.split(delimiters);
                for (int col = 0; col < maxCol; col++) {
                    rankTable[row] = Integer.parseInt(tokens[1]);
                    rankTableNames[row] = tokens[0];
                }
            }
        } catch (Exception e) {
        }
    }

    /**
     * Metoda otwierajaca plik tekstowy, a jak go nie ma to go tworzy.
     */
    private void openFile() {
        try {
            x = new Formatter("src/configfile/rank.txt");
        } catch (Exception e) {}
    }

    /**
     * Metoda nadpisujaca plik.
     */
    private void addRecords() {
        x.format("%s\n", maxRankTable);
        for (int row = 0; row < maxRankTable-1; row++) {
            x.format("%s %s\n", rankTableNames[row], rankTable[row]);
        }
        x.format("%s %s", rankTableNames[maxRankTable-1], rankTable[maxRankTable-1]);
    }

    /**
     * Metoda zamyka plik
     */
    private void closeFiles() {
        x.close();
    }

    boolean checker = true;

    /**
     * Metoda sprawdza w ktore miejsce wstawic aktualny wynik jaki uzyskal gracz.
     * @param actualscore aktualny wynik gracza.
     */
     private void checkTable(int actualscore,String name){
         for (int row = 0; row < maxRankTable; row++) {
            if (actualscore > rankTable[row] && checker == true) {
                rankTable[row] = actualscore;
                rankTableNames[row] = name;
                checker = false;
            }
         }
    }

    /**
     * Metoda wstawia nowy wynik do tabeli.
     * @param actualscore aktualny wyniki gracza.
     */
    public void setTable(int actualscore, String name){
        openFile();
        checkTable(actualscore, name);
        addRecords();
        closeFiles();
    }

    /**
     * Metoda rysuje tabele na ekranie (tle)
     * @param g ekran (tlo) na ktorym rysuje sie tabele.
     */
    public void draw(Graphics2D g){
        g.setFont(font);
        g.setColor(Color.pink);
        for(int row = 0; row < maxRankTable; row++){
            for(int col = 0; col < maxCol ; col++){
                g.drawString(
                        row+1 + ". " + rankTableNames[row],
                        60,
                        80 + row * 20
                );
                g.drawString(
                        "" + rankTable[row],
                        220,
                        80 + row * 20
                );
            }
        }
    }
}