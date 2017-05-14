package pathgame;
/**
 * Created by Piotrek on 2016-12-27.
 */

import java.awt.*;
import java.awt.image.BufferedImage;
import javax.imageio.ImageIO;
import java.io.File;

/**
 * Klasa zawierająca parametry i metody gracza (rakiety).
 */

public class Rocket {
    /**
     * Liczba zyc gracza
     */
    private int health;
    /**
     * Maksymalna liczba zyc gracza
     */
    private int maxHealth;
    /**
     * Wynik gracza
     */
    private int score;

    //animation
    /**
     * Pozycja pozioma gracza.
     */
    private double x;
    /**
     * Pozycja pionowa gracza.
     */
    private double y;
    /**
     * Zmiana pozycji gracza w poziomie.
     */
    private double dx;
    /**
     * Zmiana pozycji gracza w pionie.
     */
    private double dy;

    /**
     * Szerokosc postaci gracza
     */
    private int width;
    /**
     * Dlugosc postaci gracza
     */
    private int height;

    /**
     * Atrybut sprawdzajacy czy gracz kieruje sie w lewo.
     */
    private boolean left;
    /**
     * Atrybut sprawdzajacy czy gracz kieruje sie w prawo.
     */
    private boolean right;
    /**
     * Atrybut sprawdzajacy czy gracz kieruje sie w gore.
     */
    private boolean uppper;
    /**
     * Atrybut sprawdzajacy czy gracz kieruje sie w dol.
     */
    private boolean down;

    /**
     * Atrybut sprawdzajacy czy gracz kieruje sie w dol.
     */
    private double moveSpeed;
    /**
     * Atrybut sprawdzajacy czy gracz kieruje sie w dol.
     */
    private double maxSpeed;
    /**
     * Atrybut sprawdzajacy czy gracz kieruje sie w dol.
     */
    private double stopSpeed;

    /**
     * Mapa na ktora rysowany jest gracz.
     */
    private TileMap tileMap;

    /**
     * atrybut odpowiedzialny za kolizje gornej lewej krawedzi.
     */
    private boolean topLeft;
    /**
     * atrybut odpowiedzialny za kolizje gornej prawej krawedzi.
     */
    private boolean topRight;
    /**
     * atrybut odpowiedzialny za kolizje dolnej lewej krawedzi.
     */
    private boolean bottomLeft;
    /**
     * atrybut odpowiedzialny za kolizje dolnej prawej krawedzi.
     */
    private boolean bottomRight;

    /**
     * obiekt animacji do zmiany wyswietlanego obrazu gracza.
     */
    private Animation animation;
    /**
     * atrybut odpowiedzialny za kolizje gornej prawej krawedzi.
     */
    private BufferedImage[] idleSprites;
    /**
     * atrybut odpowiedzialny za kolizje gornej prawej krawedzi.
     */
    private BufferedImage[] flyingSprites;
    /**
     * atrybut odpowiedzialny za kolizje gornej prawej krawedzi.
     */
    private boolean facingLeft;

    /**
     * zmienna do przesuwania mapy.
     */
    double a = -1;

    /**
     * Kontruktor klasy gracza (rakieta).
     * @param tm parametr mapy na której wyświetla się obietk gracza.
     */
    public Rocket(TileMap tm){
        tileMap = tm;
        width = 22;
        height = 22;

        moveSpeed = 0.3;
        maxSpeed = 2.2;
        stopSpeed = 0.15;

        health = maxHealth = 5;
        score = 400;


        rocketloadfiles();

        animation = new Animation(idleSprites);
        facingLeft = false;

    }

    /**
     * Metoda sprawdzająca wszelkie kolizje i efekty przesuwania się gracza po mapie.
     * @param tm
     */
    public void update(TileMap tm) {
        //determine next position
        tileMap = tm;

        moving();

        calculateCornersXY();

        //move the map
        mapmoving();

        checkIfDead();

        // sprite animation
        spriteAnimation();
    }

    /**
     * Metoda która wczytuje pliki konfiguracyjne.
     */
    private void rocketloadfiles() {
        try{

            idleSprites = new BufferedImage[1];
            flyingSprites = new BufferedImage[6];

            idleSprites[0] = ImageIO.read(new File("src/configfile/rocketboyidle.gif"));

            BufferedImage image = ImageIO.read(new File("src/configfile/rocketboyflying.gif"));
            for(int i = 0; i < flyingSprites.length;i++)
                flyingSprites[i] = image.getSubimage(
                        i * width + i,
                        0,
                        width,
                        height
                );

        }
        catch(Exception e){
            e.printStackTrace();
        }
    }

    /**
     * Metoda przesuwająca gracza po mapie.
     */
    private void moving(){
        if(left){
            dx -= moveSpeed;
            if(dx < -maxSpeed){
                dx = -maxSpeed;
            }
        }
        else if(right){
            dx += moveSpeed;
            if(dx > maxSpeed){
                dx = maxSpeed;
            }
        }
        if(down){
            dy += moveSpeed;
            if(dy>maxSpeed){
                dy = maxSpeed;
            }
        }
        else if(uppper){
            dy -= moveSpeed;
            if(dy < -maxSpeed){
                dy = -maxSpeed;
            }
        }
        else{
            if(dx > 0){
                dx -=stopSpeed;
                if(dx<0){
                    dx = 0;
                }
            }
            else if(dx<0){
                dx +=stopSpeed;
                if(dx>0){
                    dx = 0;
                }
            }
            if(dy > 0){
                dy -=stopSpeed;
                if(dy<0){
                    dy = 0;
                }
            }
            else if(dy<0){
                dy +=stopSpeed;
                if(dy>0){
                    dy = 0;
                }
            }
        }
    }

    /**
     * Metoda obliczająca kolizje z poszczególnymi blokami mapy (Tile)
     * @param x szerokość na której znajduje się gracz na mapie.
     * @param y długość na której znajduje się gracz na mapie.
     */
    private void calculateCorners(double x, double y){
        int leftTile = tileMap.getColTile((int) (x - width / 2));
        int rightTile = tileMap.getColTile((int) ((x + width/ 2) - 1));
        int topTile = tileMap.getRowTile((int)((y - height / 2)));
        int bottomTile = tileMap.getRowTile((int)((y + height / 2) - 1));
        topLeft = tileMap.isBlocked(topTile, leftTile);
        topRight = tileMap.isBlocked(topTile, rightTile);
        bottomLeft = tileMap.isBlocked(bottomTile, leftTile);
        bottomRight = tileMap.isBlocked(bottomTile, rightTile);
    }

    /**
     * Metoda licząca co ma się stać po zdezreniu z obiektami na mapie.
     */
    private void calculateCornersXY(){
        //int currCol = tileMap.getColTile((int) x);
        //int currRow = tileMap.getRowTile((int) y);

        double tox = x + dx;
        double toy = y + dy;

        double tempx = x;
        double tempy = y;

        calculateCorners(x, toy);
        if(dy < 0){
            if(topLeft || topRight){
                dy = 0;
                health--;
                tempy -= dy;//*currRow * (tileMap.getTileSize()+ height / 2);
            }
            else{
                tempy += dy;
            }
        }
        if(dy > 0){
            if(bottomLeft||bottomRight){
                dy = 0;
                health--;
                tempy -= dy;//*(currRow + 1) * (tileMap.getTileSize() - height / 2);
            }
            else {
                tempy += dy;
            }
        }
        calculateCorners(tox, y);
        if(dx < 0) {
            if(topLeft || bottomLeft){
                dx = 0;
                health--;
                tempx -= dx; //* currCol * (tileMap.getTileSize() + width / 2);;
            }
            else{
                tempx += dx;
            }
        }
        if(dx > 0) {
            if(topRight||bottomRight){
                dx = 0;
                health--;
                tempx -= dx;// * (currCol + 1) * (tileMap.getTileSize() - width / 2);
            }
            else {
                tempx += dx;
            }
        }

        x = tempx;
        y = tempy;
    }

    /**
     * Funkcja poruszająca mapą.
     */
    private void mapmoving(){
        if(a == -1)
        {
            a = y;
        }
        tileMap.setx((int)(Board.WIDTH / 4 - x));
        tileMap.sety((int)(Board.HEIGHT / 4 - a));
        //tileMap.sety(Board.HEIGHT/4 - a);
        a--;
    }

    /**
     * Funkcja sprawdzająca czy postać żyje.
     * @return zwraca false gdy postać wyszła za mapę.
     */
    public Boolean checkIfDead(){
        if(-(tileMap.gety()-Board.HEIGHT/2) <= y){
            System.out.println(tileMap.gety()-Board.HEIGHT/2);
            return true;
        }
        return false;
    }

    public int getMapZero(){
        return tileMap.gety();
    }

    /**
     * Metoda zarządzająca animacją postaci gracza.
     */
    private void spriteAnimation(){
        if(left || right || uppper || down){
            animation.setFrames(flyingSprites);
            animation.setDelay(100);
        }
        else{
            animation.setFrames(idleSprites);
            animation.setDelay(-1);
        }
        animation.update();

        if(dx < 0){
            facingLeft = true;
        }
        if(dx > 0){
            facingLeft = false;
        }
    }

    /**
     * Metoda rysująca gracza na mapie.
     * @param g pobieran
     */
    public void draw(Graphics2D g){
        double tx = tileMap.getx();
        double ty = tileMap.gety();
        if(facingLeft){
            g.drawImage(
                    animation.getImage(),
                    (int) (tx + x - width / 2 ),
                    (int) (ty + y - height / 2 ),
                    width,
                    height,
                    null
            );
        }
        else{
            g.drawImage(
                    animation.getImage(),
                    (int) (tx + x - width / 2 + width),
                    (int) (ty + y - height / 2),
                    -width,
                    height,
                    null
            );
        }
    }

    /**
     * Metoda ustawiająca pozycję gracza (szerokość)
     * @param i ustawia szerokość pobraną
     */
    public void setx(int i){x = i;}

    /**
     * Metoda ustawiająca pozycję gracza (wysokość)
     * @param i ustawia wysokość pobraną
     */
    public void sety(int i) { y = i;}

    //public double getx() {return x;}
    //public double gety() {return y;}

    /**
     * Metoda pobierająca informacje o aktualnym życiu gracza.
     * @return zwraca ilość żyć.
     */
    public int getHealth() { return health; }

    /**
     * Metoda pobierająca informacje o maksymalnym życiu gracza.
     * @return zwraca maksymalną ilość żyć.
     */
    public int getMaxHealth() { return maxHealth; }

    /**
     * Metoda pobierająca informacje o maksymalnym życiu gracza.
     * @return zwraca ilość punktów zdobytych przez gracza.
     */
    public int getScore() { return score; }

    /**
     * Metoda ustawia wynik gracza np. po przejsciu poziomu.
     * @param score
     */
    public void setScore(int score) { this.score = score;}
    /**
     * Metoda ustawiająca czy gracz porusza się w lewo.
     * @param b parametr ustawia true jak gracz porusza się w lewo.
     */
    public void setLeft(boolean b) {left = b;}
    /**
     * Metoda ustawiająca czy gracz porusza się w prawo.
     * @param b parametr ustawia true jak gracz porusza się w prawo.
     */
    public void setRight(boolean b) {right = b;}

    /**
     * Metoda ustawiająca czy gracz porusza się w górę.
     * @param b parametr ustawia true jak gracz porusza się w górę.
     */
    public void setUpper(boolean b) {uppper = b;}

    /**
     * Metoda ustawiająca czy gracz porusza się w dół.
     * @param b parametr ustawia true jak gracz porusza się w dół.
     */
    public void setDown(boolean b) {down = b;}


}
