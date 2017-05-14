package GameState;
/**
 * Created by Piotrek on 2017-01-02.
 */

import pathgame.*;

import javax.swing.*;
import java.awt.*;
import java.awt.event.KeyEvent;

/**
 * Klasa odpowiedzialna za 1 poziom stanu gry.
 */
public class Level1State extends GameState{

    private TileMap tileMap;
    private Star star;
    private Background bg;

    public int SCOREPOINTS = 0;
    private Rocket rocket;

    private Health health;

    private Score score;

    /**
     * Konstruktor 1 poziomu gry.
     * @param gsm glowna klasa zarzadzajaca stanami gry.
     */
    public Level1State (GameStateManager gsm){
        this.gsm = gsm;
        init();
    }

    /**
     * Metoda inicjalizujaca poziom 1.
     */
    public void init() {
        tileMap = new TileMap("src/configfile/testmap2.txt", 32);
        tileMap.loadTiles("src/configfile/skala.gif");
        tileMap.setx(100);
        tileMap.sety(100);

        rocket = new Rocket(tileMap);
        rocket.setx(tileMap.getMapWidth() / 2);// setx(50);//
        rocket.sety(tileMap.getMapHeight() - 50); // 50);//

        health = new Health(rocket);
        score = new Score(rocket);
    }

    /**
     * Metoda aktualizujaca poziom 1.
     */
    public void update() {
        tileMap.setx(Board.WIDTH);
        tileMap.sety(Board.HEIGHT);
        rocket.update(tileMap);

        if(rocket.checkIfDead()) {
            selectBack();
        }
        if(rocket.getHealth() == 0){
            selectBack();
        }
        if(rocket.getMapZero() == 0){
            score.setPoints(100);
            SCOREPOINTS = score.getPoints();
            selectNext();
        }
    }

    /**
     * Metoda rysujaca poziom 1.
     * @param g tlo na ktorym rysuje.
     */
    public void draw(Graphics2D g) {
        //clear screen
        g.setColor(Color.BLACK);
        g.fillRect(0, 0, Board.WIDTH, Board.HEIGHT);
        //bg.draw(g);

        //draw health
        health.draw(g);
        // draw tilemap
        tileMap.draw(g,Board.WIDTH, Board.HEIGHT);

        //draw rocket
        rocket.draw(g);

        //draw health
        health.draw(g);

        //draw score
        score.draw(g);
    }

    /**
     * Metoda odpowiedzialna za powrot do stanu menu.
     */
    private void selectBack(){
        gsm.setState(GameStateManager.MENUSTATE);
    }

    /**
     * Metoda odpowiedzialna za zmiane do nastepnego stanu.
     */
    private void selectNext(){gsm.setState(GameStateManager.LEVEL2STATE);}

    /**
     * Metoda odpowiedzialna za to co sie stanie po wcisnieciu klawisza.
     * @param k klawisz wczytany z klawiatury
     */
    public void keyPressed(int k) {
        if(k == KeyEvent.VK_LEFT) {
            rocket.setLeft(true);
        }
        if(k == KeyEvent.VK_RIGHT){
            rocket.setRight(true);
        }
        if(k == KeyEvent.VK_UP){
            rocket.setUpper(true);
        }
        if(k == KeyEvent.VK_DOWN){
            rocket.setDown(true);
        }
        if(k == KeyEvent.VK_BACK_SPACE){
            selectBack();
        }
    }

    /**
     * Metoda odpowiedzialna za to co sie staie po puszczeniu klawisza.
     * @param k klawisz wczytany z klawiatury
     */
    public void keyReleased(int k) {
        if(k == KeyEvent.VK_LEFT) {
            rocket.setLeft(false);
        }
        if(k == KeyEvent.VK_RIGHT){
            rocket.setRight(false);
        }
        if(k == KeyEvent.VK_UP){
            rocket.setUpper(false);
        }
        if(k == KeyEvent.VK_DOWN){
            rocket.setDown(false);
        }
    }
}
