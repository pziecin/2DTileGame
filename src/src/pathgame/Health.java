package pathgame;

/**
 * Created by Piotrek on 2017-01-06.
 */

import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.net.URL;
import javax.imageio.ImageIO;

/**
 * Klasa odpowiedzialna za wyswietlanie zycia na ekranie. Czcionki itd. Klasa nie oblicza zycia.
 */
public class Health {
    /**
     * obiekt rakieta
     */
    private Rocket rocket;
    /**
     * obraz wyswietlanego zycia
     */
    private BufferedImage image;
    /**
     * czcionka wyswietlanego zycia
     */
    private Font font;

    /**
     * Konstruktor klasy obliczajacej zycia, ktora korzysta z klasy rakiety przechowujacej informacje o zyciu..
     * @param r pobienany obiekt rakiety w celu pobrania informacji o zyciu i wyswietleniu jej.
     */
    public Health(Rocket r) {
        rocket = r;
        URL url = Health.class.getResource("/configfile/healthbar.gif");
        try {
            image = ImageIO.read(url);
            font = new Font("Arial", Font.PLAIN, 14);
        }
        catch(Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * Metoda rysujaca zycia na ekranie.
     * @param g obraz ( tlo) na ktorym rysowane jest zycie.
     */
    public void draw(Graphics2D g) {

        g.drawImage(image, 0, 10, null);
        g.setFont(font);
        g.setColor(Color.WHITE);
        g.drawString(
                rocket.getHealth() + "/" + rocket.getMaxHealth(),
                35,
                25
        );
    }
}
