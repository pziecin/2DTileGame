package GameState;

/**
 * Created by Piotrek on 2017-01-06.
 */
import jdk.internal.util.xml.impl.Input;
import pathgame.Background;
import pathgame.ScorTable;
import pathgame.Score;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;

/**
 * Klasa stanu konca gry. Zawiera caly wyglad ostatniego stanu po skonczeniu gry.
 */
public class EndState extends GameState{

    private Background bg;
    private ScorTable scoretable;
    private Score score;

    private Color tileColor;
    private Font tileFont;
    String a;
    private boolean checker = true;

    JTextField tekst = new JTextField(10);

    private Font font;

    /**
     * Konstruktor ostatniego stanu gry.
     * @param gsm
     */
    public EndState (GameStateManager gsm){
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


            //tekst.setFont(new Font("Comic Sans",Font.PLAIN,25));
            //tekst.setText("AAAAAA");
            init();
        }
        catch(Exception e){
            e.printStackTrace();
        }
    }

    /**
     * Metoda inicjalizujaca elementy ostatniego stanu.
     */
    public void init() { scoretable = new ScorTable("src/configfile/rank.txt");}

    /**
     * Metoda aktualizująca elementy ostatniego stanu.
     */
    public void update() {
        if(checker == true) {
            JFrame frame = new JFrame("Podaj nick gracza");
            JPanel panel = new JPanel();
            frame.add(panel, BorderLayout.CENTER);

            JTextField field = new JTextField(50);
            panel.add(field);

            a = field.getText();
            frame.pack();
            frame.setVisible(true);
            frame.setLocationRelativeTo(null);
            frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
            frame.setResizable(false);
            checker = false;
        }
        //scoretable.setTable(100,a);
        scoretable.setTable(100,"Anonim");
    }
    /**
     * Metoda rysująca ostatni stan.
     */
    public void draw(Graphics2D g) {
        //draw bg
        bg.draw(g);
        //draw title
        g.setColor(tileColor);
        g.setFont(tileFont);
        g.drawString("The End!", 100, 50);
        g.setFont(font);
        g.drawString("Youre Score: 100 " , 20, 100);
        g.drawString("<- Backspace ",10,220);
        tekst.setBounds(400,200,200,20);
        tekst.setBorder(BorderFactory.createLineBorder(Color.BLACK, 0));
        tekst.setEditable(true);

    }

    /**
     * Metoda wybierająca stan gry.
     */
    private void select(){
        gsm.setState(GameStateManager.MENUSTATE);
    }

    /**
     * Metoda odpowiedzialna za aktywne klawisze podczas aktualnego stanu gry.
     * @param k klasziwsz wczytany z klawiatury.
     */
    public void keyPressed(int k) {
        if(k == KeyEvent.VK_BACK_SPACE){
            select();
        }
    }

    /**
     * Metoda ustalajaca co sie dzieje po puszczeniu klawisza.
     * @param k klasziwsz wczytany z klawiatury.
     */
    public void keyReleased(int k) {
    }
}
