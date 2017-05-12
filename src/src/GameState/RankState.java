package GameState;

/**
 * Created by Piotrek on 2017-01-06.
 */
import pathgame.Background;
import pathgame.ScorTable;
import pathgame.Score;

import java.awt.*;
import java.awt.event.KeyEvent;

/**
 * Klasa odpowiedzialna za stan rankingu graczy.
 */
public class RankState extends GameState{
    /**
     * Tlo stanu.
     */
    private Background bg;
    /**
     * Tabela wynikow
     */
    private ScorTable scoretable;
    /**
     * Kolor wyswietlanych napisow.
     */
    private Color tileColor;
    /**
     * Czcionka napisow tytul.
     */
    private Font tileFont;
    /**
     *  Czcionka napisow zwyklych.
     */
    private Font font;

    /**
     * Konstruktor rankingu
     * @param gsm manager stanow.
     */
    public RankState (GameStateManager gsm){
        this.gsm = gsm;

        try{
            bg = new Background("src/configfile/bg2.gif", 1);

            tileColor = new Color(128, 0, 0);
            tileFont = new Font(
                    "Century Gothic",
                    Font.PLAIN,
                    28
            );

            font = new Font(
                    "Arial",
                    Font.PLAIN,
                    12
            );
            init();
        }
        catch(Exception e){
            e.printStackTrace();
        }
    }

    /**
     * Metoda inicjalizujaca ranking stanu.
     */
    public void init() {
        scoretable = new ScorTable("src/configfile/rank.txt");
    }

    /**
     * Metoda aktualizujaca stan rankingu.
     */
    public void update() {}

    /**
     * Metoda rysujaca stan rankingu.
     * @param g tlo na ktorym rysuje.
     */
    public void draw(Graphics2D g) {
        //draw bg
        bg.draw(g);

        //draw title
        scoretable.draw(g);
        g.setColor(tileColor);
        g.setFont(tileFont);
        g.drawString("Ranking:", 100, 50);
        g.setFont(font);
        g.drawString("<- Backspace ",10,220);
    }

    /**
     * Metoda wybierajaca stan gry.
     */
    private void select(){
        gsm.setState(GameStateManager.MENUSTATE);
    }

    /**
     * Metoda odpowiedzialna za to co sie stanie po wcisnieciu klawisza.
     * @param k klawisz wczytany z klawiatury
     */
    public void keyPressed(int k) {
        if(k == KeyEvent.VK_BACK_SPACE){
            select();
        }
    }
    /**
     * Metoda odpowiedzialna za to co sie staie po puszczeniu klawisza.
     * @param k klawisz wczytany z klawiatury
     */
    public void keyReleased(int k) {
    }
}
