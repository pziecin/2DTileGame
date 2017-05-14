package pathgame;

/**
 * Created by Piotrek on 2017-01-06.
 */

import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.BufferedReader;
import java.io.FileReader;

/**
 * Klasa główna odpowiadająca za wyswietlanie punktow ale nie przechowuje ich. Punkty znajdują się w klasie Rocket
 */
public class Score {
    /**
     * obiekt gracza
     */
    private Rocket rocket;
    /**
     * zmienna przechowujaca punkty gracza pobrane z rakiety
     */
    private int points;
    /**
     * Czcionka wyswietlanych punktow
     */
    private Font font;

    public Score(Rocket p) {
        rocket = p;
        points = 0;
        try {
            font = new Font("Arial", Font.PLAIN, 14);
        }
        catch(Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * Metoda zwracajaca liczbe punktow.
     * @return
     */
    public int getPoints(){
        return points;
    }
    public void setPoints(int i) { points = points + i;}
    /**
     * Metoda rysujaca punkty na ekranie.
     * @param g ekran (tlo) na ktorym rysowane sa punkty.
     */
    public void draw(Graphics2D g) {

        g.setFont(font);
        g.setColor(Color.WHITE);
        g.drawString(
                "Score: " + points,
                240,
                25
        );
    }
}

