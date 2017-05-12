package pathgame;
/**
 * Created by Piotrek on 2016-12-25.
 */

import GameState.GameStateManager;

import javax.swing.JPanel;
import java.awt.*;
import java.awt.image.*;
import java.awt.event.*;
import java.util.concurrent.TimeUnit;

/**
 * Jedna z glownych klas, ktora odpowiada za rozmiar okna i to co jest w nim rysowane.
 */
public class Board extends JPanel implements Runnable, KeyListener {
    /**
     * Zmienna wielkosci okna
     */
    // dimensions
    public static final int WIDTH = 640;
    /**
     * Zmienna wysokosci okna.
     */
    public static final int HEIGHT = 480;

    /**
     * Watek gry.
     */
    // game thread
    private Thread thread;
    /**
     * Gdy zmienna jest true to watek jest aktywny
     */
    private boolean running;
    /**
     *
     */
    private int FPS = 60;
    /**
     *
     */
    private final int targetTime = 1000 / FPS;

    // image
    private BufferedImage image;
    private Graphics2D g;

    // game state manager
    private GameStateManager gsm;


   // private TileMap tileMap;
   // private Rocket rocket;

    /**
     * Konstruktor klasy (okna).
     */
    public Board() {
        super();
        setPreferredSize(new Dimension(WIDTH,HEIGHT));
        setFocusable(true);
        requestFocus();
    }

    /**
     * Tworzenie watku.
     */
    public void addNotify() {
        super.addNotify();
        if(thread == null){
            thread = new Thread(this);
            addKeyListener(this);
            thread.start();
        }
        //addKeyListener(this);
    }

    /**
     * Metoda glowna uruchamiajaca wszystkie operacje do wykonania.
     */
    public void run() {
        init();

        long startTime;
        long deltaTime;
        long waitTime;

        while(running){
            startTime = System.nanoTime();

            update();
            render();
            draw();

            deltaTime = System.nanoTime() - startTime;
            waitTime = targetTime - deltaTime / 1000000;
            if(waitTime < 0) waitTime = 5;
            try{
                Thread.sleep(waitTime);
            }
            catch(Exception e){
                e.printStackTrace();
            }
        }
    }

    /**
     * Metoda inicjalizuje okno i managera aktualnej sceny.
     */
    private void init() {
        image = new BufferedImage(WIDTH, HEIGHT, BufferedImage.TYPE_INT_RGB);
        g = (Graphics2D) image.getGraphics();

        running = true;

        gsm = new GameStateManager();
        /*tileMap = new TileMap("src/configfile/testmap2.txt", 32);
        tileMap.loadTiles("src/configfile/skala.gif");
        rocket = new Rocket(tileMap);
        rocket.setx(tileMap.getMapWidth()/2);
        rocket.sety(tileMap.getMapHeight() - 50);*/

    }
    ///////////////////////////////////////

    /**
     * Metoda aktualizujaca okno.
     */
    private void update(){
        gsm.update();
    }

    /**
     * Metoda rysujaca na ekran w zainicjalizowanym oknie.
     */
    private void render (){

       /* g.setColor(Color.BLACK);
        g.fillRect(0, 0, getWidth(), getHeight());*/

        gsm.draw(g);
        /*tileMap.draw(g, getWidth(), getHeight());
        rocket.draw(g, getWidth(), getHeight());*/
    }

    /**
     * Metoda rysujaca podklad.
     */
    private void draw(){
        Graphics g2 = getGraphics();
        g2.drawImage(image, 0, 0, WIDTH * 2, HEIGHT * 2, null);
        g2.dispose();
    }

    /**
     * Metoda odpowiedzialna za klawisze.
     * @param key
     */
    public void keyTyped(KeyEvent key) {}

    /**
     * Metoda odpowiedzialna za wcisniete klawisze.
     * @param key
     */
    public void keyPressed(KeyEvent key) {gsm.keyPressed(key.getKeyCode());}

    /**
     * Metoda sluzaca do sprawdzania co sie dzieje po puszczeniu klawisza.
     * @param key
     */
    public void keyReleased(KeyEvent key) {gsm.keyReleased(key.getKeyCode());}
}
