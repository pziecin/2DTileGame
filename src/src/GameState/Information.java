package GameState;

/**
 * Created by Piotrek on 2017-01-06.
 */
import pathgame.Background;
import java.awt.*;
import java.awt.event.KeyEvent;

/**
 * Klasa odpowiedzialna za stan informacji o grze.
 */
public class Information extends GameState{

    private Background bg;

    private Color tileColor;
    private Font tileFont;

    private Font font;

    /**
     * Konstruktor stanu informacja.
     * @param gsm
     */
    public Information (GameStateManager gsm){
        this.gsm = gsm;

        try{
            bg = new Background("src/configfile/bg.gif", 1);

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
        }
        catch(Exception e){
            e.printStackTrace();
        }
    }

    /**
     * Metoda inicjalizujaca elementy stanu informujacego.
     */
    public void init() {}

    /**
     * Metoda aktualizujaca stan informacji.
     */
    public void update() {}

    /**
     * Metoda rysujaca stan ifnromacji na aktualnym tle.
     * @param g tlo na ktorym rysuje.
     */
    public void draw(Graphics2D g) {
        //draw bg
        bg.draw(g);

        //draw title
        g.setColor(tileColor);
        g.setFont(tileFont);
        g.drawString("Information:", 10, 50);
        g.setFont(font);
        g.drawString("Zasady gry i sterowanie:", 20, 70);
        g.drawString("1.Sterowanie strzałkami.", 20, 85);
        g.drawString("2.Cofanie do menu \" Backspace.\"", 20, 100);
        g.drawString("3.Zdobywaj gwiazdki (1 gwiazdka 20 punktow).", 20, 115);
        g.drawString("4.Masz 5 zyc.", 20, 130);
        g.drawString("5.Uwazaj by nie dotknąc ściany bo możesz stracić życie.", 20, 145);
        g.drawString("6.Dotrzyj do celu przed tym aż dogoni cie mapa.", 20, 160);
        g.drawString("<- Backspace ",10,220);
    }

    /**
     * Metoda odpowiedzialna za wybieranie stanu gry.
     */
    private void select(){
        gsm.setState(GameStateManager.MENUSTATE);
    }

    /**
     * Metoda odpowiedzialna za dzialanie po wcisnieciu klawisza.
     * @param k klawisz wczytany z klawiatury
     */
    public void keyPressed(int k) {
        if(k == KeyEvent.VK_BACK_SPACE){
            select();
        }
    }
    /**
     * Metoda odpowiedzialna za dzialanie po puszczeniu klawisza.
     * @param k klawisz wczytany z klawiatury
     */
    public void keyReleased(int k) {
    }
}
