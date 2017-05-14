package pathgame;

/**
 * Created by Piotrek on 2017-01-07.
 */
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import javax.imageio.ImageIO;

/**
 * Klasa odpowiedzialna za gwiazdke w grze.
 */
public class Star {
    private Rocket rocket;

    private int x;
    private int y;
    private int width;
    private int height;
    private int[][] map;

    private BufferedImage image;
    private TileMap tileMap;

    public Star(Rocket r, TileMap tm) {
        tileMap = tm;
        rocket = r;
        try {
            image = ImageIO.read(new File("src/configfile/star.gif"));
        }
        catch(Exception e) {
            e.printStackTrace();
        }
    }

    public void draw(Graphics2D g) {
       // map = tileMap.getMap();
       // for(int row = 0; row < tileMap.getMapHeight(); row++){
            //for(int col = 0; col < tileMap.getMapWidth(); col++){
                //if(map[row][col] == 0){
                    g.drawImage(
                            image,
                            width,
                            height,
                            null
                    );
                //}
           // }
        //}
    }
}
