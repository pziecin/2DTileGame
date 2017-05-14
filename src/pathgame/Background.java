package pathgame;
/**
 * Created by Piotrek on 2017-01-02.
 */

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.*;
import java.io.File;

/**
 * Klasa odpowiedzialna za wszelkie grafiki w tle.
 */
public class Background {
    /**
     * obraz/tlo wczytywane z pliku
     */
    private BufferedImage image;

    /**
     * pozycja pozioma obrazu
     */
    private double x;
    /**
     * pozycja pionowa obrazu
     */
    private double y;
    /**
     * przesuniecie w poziomie
     */
    private double dx;
    /**
     * przesuniecie w pionie
     */
    private double dy;

    /**
     * predkosc przesuniecia
     */
    private double moveScale;

    /**
     * Konstruktor klasy ktory wczytuje grafike i ewnetualnie przesuwa ja wzdlug ktorej osi.
     * @param s strumien danych do ktorego podajemy zrodlo grafiki.
     * @param ms predkosc z jaka ma sie przesuwac grafika.
     */
    public Background(String s, double ms){

        try{
            image = ImageIO.read(new File(s));
            moveScale=ms;
        }
        catch(Exception e){
            e.printStackTrace();
        }
        setPosition(0,0);
    }

    /**
     * Metoda ustawia pozycje grafiki
     * @param x pozycja pozioma
     * @param y pozycja pionowa
     */
    public void setPosition(double x, double y){
        this.x = (x * moveScale); //% Board.WIDTH;
        this.y = (y * moveScale); //% Board.HEIGHT;
    }

    /**
     * Metoda ustawiajaca przesuniecie grafiki
     * @param dx pozycja pozioma
     * @param dy pozycja pionowa
     */
    public void setVector(double dx, double dy){
        this.dx = dx;
        this.dy = dy;
    }

    /**
     * Metoda aktualizujaca pozycje grafiki.
     */
    public void update(){
        x += dx;
        y += dy;
    }

    /**
     * Metoda rysujaca grafike.
     * @param g Grafika(podklad) na ktorym bedzie rysowane tlo.
     */
    public void draw(Graphics2D g){
        g.drawImage(image, (int) x, (int) y, null);
        if(x < 0){
            g.drawImage(
                    image,
                    (int) x + Board.WIDTH,
                    (int) y,
                    null
            );
        }
        if(x > 0){
            g.drawImage(
                    image,
                    (int) x - Board.WIDTH,
                    (int) y,
                    null
            );
        }
    }
}
