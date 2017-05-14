package pathgame;
/**
 * Created by Piotrek on 2016-12-26.
 */
import java.awt.image.*;

/**
 * Klasa zawierajaca informacje o kafelku ( 1 polu mapy). Posiada informacje o tym czy dany kafelek
 * jest blokada i czy zajdzie kolizja z rakieta czy nie jest blokada i mozna przez niego przejsc.
 */
public class Tile {
    private BufferedImage image;
    private boolean blocked;

    /**
     * Konstruktor pojedzynczego pola.
     * @param image Pobiera obraz kafelka.
     * @param blocked Ustawia true lub false gdy jest odpowiednio nieprzejezdny lub przejezdny.
     */
    public Tile(BufferedImage image, boolean blocked){
        this.image = image;
        this.blocked = blocked;
    }

    /**
     * Metoda zwracajaca aktualny kafelek.
     * @return zwraca obraz.
     */
    public BufferedImage getImage() {return image;}

    /**
     * Metoda zwracajaca false lub true gdy kafelek jest czarnym polem ( przejezdna sciezka) lub blokada.
     * @return
     */
    public boolean isBlocked() { return blocked;}

}
