package GameState;

/**
 * Created by Piotrek on 2017-01-02.
 */

/**
 * Abstrakcyjna klasa stanu gry. Zawiera wszystkie glowne funkcje poszczegolnych stanow.
 */
public abstract class GameState {

    protected GameStateManager gsm;
    /**
     * Metoda inicjalizujaca elementy stanu.
     */
    public abstract void init();
    /**
     * Metoda aktualizujaca stan gry.
     */
    public abstract void update();
    /**
     * Metoda rysujaca stan gry.
     * @param g tlo na ktorym rysuje.
     */
    public abstract void draw(java.awt.Graphics2D g);
    /**
     * Metoda odpowiedzialna za reakcje na wcisniecie klawisza.
     * @param k klawisz wczytany z klawiatury
     */
    public abstract void keyPressed(int k);
    /**
     * Metoda odpowiedzialna za reakcje na puszczenie klawisza.
     * @param k klawisz wczytany z klawiatury
     */
    public abstract void keyReleased(int k);
}
