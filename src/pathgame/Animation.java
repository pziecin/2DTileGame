package pathgame;
/**
 * Created by Piotrek on 2016-12-30.
 */


import java.awt.image.*;

/**
 * Klasa odpowiedzialna za animacje postaci. Pobiera aktualna ramke i na podstawie czasu zmienia ja.
 */
public class Animation {
    /**
     * ramki do animacji
     */
    private BufferedImage[] frames;
    /**
     * aktualna ramka
     */
    private int currentFrame;

    /**
     * czas rozpoczecia animacji
     */
    private long startTime;
    /**
     * opoznienie zmiany animacji (ramek)
     */
    private long delay;

    /**
     *  Konstruktor animacji pobiera ramki ustawia aktualna ramke animacji na 0.
     * @param frames
     */
    Animation(BufferedImage[] frames){
        this.frames = frames;
        currentFrame = 0;
        startTime = System.nanoTime();
    }

    /**
     *  Metoda ustawia aktualna ramke.
     * @param frames ramki (gify, jakis obraz) wczytywane do animacji.
     */
    public void setFrames(BufferedImage[] frames){
        this.frames = frames;
       // currentFrame = 0;
        startTime = System.nanoTime();
    }

    /**
     * Metoda ustawia opoznienie zmiany ramek
     * @param d opoznienie
     */
    public void setDelay(long d){
        delay = d;
    }

    /**
     * Metoda ustawiajaca aktualna ramke.
     * @param i parametr ustawiajacy ktora ramka ma byc wyswietlana.
     */
    public void setFrame(int i) { currentFrame = i; }

    /**
     * Metoda sprawdzajaca czas i moment w ktorym ma sie zmieniac ramka.
     */
    public void update() {
        if(delay == -1) return;
        long elapsed = (System.nanoTime() - startTime) / 1000000;
        if(elapsed > delay){
            currentFrame++;
            startTime = System.nanoTime();
        }
        if(currentFrame == frames.length){
            currentFrame = 0;
        }
    }

    /**
     * Metoda zwracajaca aktualna ramke.
     * @return
     */
    public int getFrame() { return currentFrame; }

    /**
     * Metoda wczytujaca klatki animacji.
     * @return zwraca gifa z aktualna ramka.
     */
    public BufferedImage getImage() { return frames[currentFrame]; }
}
