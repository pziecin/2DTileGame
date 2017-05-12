package GameState;
/**
 * Created by Piotrek on 2017-01-02.
 */

import pathgame.Background;

import java.awt.*;
import java.awt.event.KeyEvent;

/**
 * Klasa glowna MENU uruchamiajaca sie jako pierwsza.
 */
public class MenuState extends  GameState {

    private Background bg;

    private int currentChoice = 0;
    private String[] options = {
            "Start",
            "Ranking",
            "Information",
            "Quit"
    };

    private Color tileColor;
    private Font tileFont;

    private Font font;

    /**
     * Konstruktor menu glownego.
     * @param gsm glowna klasa zarzadzajaca stanami gry.
     */
    public MenuState(GameStateManager gsm){
        this.gsm = gsm;

        try{
            bg = new Background("src/configfile/bg.gif", 1);
            bg.setVector(-0.1, 0);

            tileColor = new Color(128, 0, 0);
            tileFont = new Font(
                    "Century Gothic",
                    Font.PLAIN,
                    40
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
     * Metoda inicjalizujaca menu.
     */
    @Override
    public void init() {}

    /**
     * Metoda aktualizujaca menu.
     */
    @Override
    public void update() {
        bg.update();
    }

    /**
     * Metoda rysujaca poziom 1.
     * @param g tlo na ktorym rysuje.
     */
    @Override
    public void draw(Graphics2D g) {

        //draw bg
        bg.draw(g);


        g.setFont(tileFont);
        //draw menu options
        for(int j = -2; j < 6; j++)
            for (int i = 0; i < 10; i++) {
                g.setColor(Color.BLACK);
                g.drawString("O", -60*j + 100 + i * 20, 10 + i * 40);
            }
        //draw title
        g.setColor(tileColor);
        g.drawString("Mission to Mars", 10, 70);
        g.setFont(font);
        for(int i = 0; i < options.length; i++){
            if(i == currentChoice){
                g.setColor(Color.orange);
            }
            else {
                g.setColor(tileColor);
            }
            g.drawString(options[i],10,130 + i * 20);
        }

    }

    /**
     * Metoda wybierajaca kolejny stan w zaleznosci od wyboru uzytkownika.
     */
    private void select(){
        if(currentChoice == 0){
            gsm.setState(GameStateManager.LEVEL1STATE);
        }
        if(currentChoice == 1){
            gsm.setState(GameStateManager.RANKSTATE);
        }
        if(currentChoice == 2){
            gsm.setState(GameStateManager.INFORMATION);
        }
        if(currentChoice == 3){
            System.exit(0);
        }

    }
    /**
     * Metoda odpowiedzialna za to co sie stanie po wcisnieciu klawisza.
     * @param k klawisz wczytany z klawiatury
     */
    @Override
    public void keyPressed(int k) {
        if(k == KeyEvent.VK_ENTER){
            select();
        }
        if(k == KeyEvent.VK_UP){
            currentChoice--;
            if(currentChoice == -1){
                currentChoice = options.length - 1;
            }
        }
        if(k== KeyEvent.VK_DOWN){
            currentChoice++;
            if(currentChoice == options.length){
                currentChoice = 0;
            }
        }
    }

    /**
     * Metoda odpowiedzialna za to co sie staie po puszczeniu klawisza.
     * @param k klawisz wczytany z klawiatury
     */
    @Override
    public void keyReleased(int k) {}
}
