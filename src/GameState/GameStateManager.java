package GameState;

/**
 * Created by Piotrek on 2017-01-02.
 */

import java.util.ArrayList;

/**
 * Klasa glowna operaujaca stanami gry. Zmienia stany gry w zaleznosci od tego
 */
public class GameStateManager {

    private ArrayList<GameState> gameStates;
    private int currentState;

    public static final int MENUSTATE = 0; // change to enum
    public static final int LEVEL1STATE = 1; // change to enum
    public static final int RANKSTATE = 2;
    public static final int INFORMATION = 3;
    public static final int LEVEL2STATE = 4;
    public static final int ENDSTATE = 5;

    /**
     * Konstruktor managera stanow. Ustawia jako stan podstawowy MENUSTATE
     */
    public GameStateManager(){

        gameStates = new ArrayList<GameState>();

        currentState = MENUSTATE;
        gameStates.add(new MenuState(this));
        gameStates.add(new Level1State(this));
        gameStates.add(new RankState(this));
        gameStates.add(new Information(this));
        gameStates.add(new Level2State(this));
        gameStates.add(new EndState(this));

    }

    /**
     * Metoda ustawiajaca stan ekranu.
     * @param state pobiera stan ktory ma ustawic.
     */
    public void setState(int state){
        currentState = state;
        gameStates.get(currentState).init();
    }

   /* boolean checkingCurrentState(int currentState){
        if(this.currentState == currentState){
            return true;
        }
        return false;
    }*/

    /**
     * Metoda aktualizujaca stan gry. Sprawdza czy cos sie zmienilo.
     */
    public void update() {
        gameStates.get(currentState).update();
    }

    /**
     * Metoda rysuje aktualny stan gry.
     * @param g rysuje na tle.
     */
    public void draw(java.awt.Graphics2D g){
        gameStates.get(currentState).draw(g);
    }

    /**
     * Metoda pobiera klawisze aktualnego stanu.
     * @param k klawisz pobrany z klawiatury.
     */
    public void keyPressed(int k){
        gameStates.get(currentState).keyPressed(k);
    }

    /**
     * Metoda pobiera klawisze aktualnego stanu.
     * @param k klawisz pobrany z klawiatury.
     */
    public void keyReleased(int k){
        gameStates.get(currentState).keyReleased(k);
    }
}
