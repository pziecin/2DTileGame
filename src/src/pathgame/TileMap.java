package pathgame;
/**
 * Created by Piotrek on 2016-12-26.
 */
import java.awt.image.BufferedImage;
import java.io.*;
import java.awt.*;
import java.awt.image.*;
import javax.imageio.ImageIO;

/**
 * Klasa generujaca cala mape gracza.
 */
public class TileMap {
    //position
    /**
     * zmienna poziomej pozycji mapy
     */
    private int x;
    /**
     * zmienna pionowej pozycji mapy
     */
    private int y;
    /**
     * zmiena przechowujaca rozmiar pojedynczego kafelka mapy
     */
    private int tileSize;
    /**
     * zmienna przechowujaca wartosci mapy
     */
    private int[][] map;
    /**
     * zmienna szerokosci mapy
     */
    private int mapWidth;
    /**
     * zmienna wysokosci mapy
     */
    private int mapHeight;
    /**
     * obraz kafelka mapy
     */
    private BufferedImage tileset;
    /**
     * poszczegolne kafelki mapy
     */
    private Tile[][] tiles;

    /**
     * minimalna szerokosc mapy
     */
    private int minx;
    private int miny;
    private int maxx = 0;

    /**
     * Konstruktor mapy wczytuje strumien tekstowy i konwertuje do tablicy.
     * @param s sciezka do pliku tekstowego
     * @param tileSize rozmiar kafelka
     */
    public TileMap(String s, int tileSize) {
        this.tileSize = tileSize;
        try{
            BufferedReader br = new BufferedReader(new FileReader(s));
            mapWidth = Integer.parseInt(br.readLine());
            System.out.println("MapWidth: " + mapWidth);
            mapHeight = Integer.parseInt(br.readLine());
            map = new int[mapHeight][mapWidth];

            minx = Board.WIDTH / 2- mapWidth * tileSize;
            miny = Board.HEIGHT / 2 - mapHeight * tileSize;



            String delimiters = "\\s+";
            for(int row = 0; row < mapHeight; row++){
                String line = br.readLine();
                String[] tokens = line.split(delimiters);
                for(int col = 0; col < mapWidth; col++){
                    map[row][col] = Integer.parseInt(tokens[col]);
                }
            }
        }
        catch (Exception e){}
    }

    /**
     * sciezka wczytujaca plik tekstowy z grafika.
     * @param s sciezka do grafiki z kafelkiem.
     */
    public void loadTiles(String s){

        try{
            tileset = ImageIO.read(new File(s));
            int numTilesAcross = (tileset.getWidth()) / (tileSize);
            tiles = new Tile[2][numTilesAcross];
            BufferedImage subimage;
            for(int col = 0; col < numTilesAcross; col++){
                subimage = tileset.getSubimage(
                        col * tileSize + col,
                        0,
                        tileSize,
                        tileSize
                );
                tiles[0][col] = new Tile(subimage, false);
                subimage = tileset.getSubimage(
                        col*tileSize + col,
                        tileSize,
                        tileSize,
                        tileSize
                );
                tiles[1][col] = new Tile(subimage, true);
            }
        }
        catch(Exception e){
            e.printStackTrace();
        }
    }

    /**
     * metoda aktualizuje mape
     */
    public void update() {
    }

    /**
     * metoda zwracajaca pozycje pozioma mapy
     * @return zwraca polozenie mapy w osi x
     */
    public int getx() {return x;}

    /**
     * metoda zwracajaca pozycje pionowa mapy
     * @return zwraca polozenie mapy w osi y
     */
    public int gety() {return y;}

    /**
     * metoda zwraca liczbe kolumn w poziomie
     * @param x szerokosc mapy
     * @return zwraca liczbe kolumn w poziomie
     */
    public int getColTile(int x){
        return x / tileSize;
    }

    /*public int getColTileScale(int x, int widthscale){
        return x / tileSize * Board.WIDTH/widthscale;
    }*/
    /**
     * metoda zwraca liczbe kolumn w pionie
     * @param y wysokosc mapy
     * @return zwraca liczbe kolumn w pionie
     */
    public int getRowTile(int y){
        return y / tileSize;
    }

    /*public int getRowTileScal(int y, int heightscale){
        return y / tileSize * Board.HEIGHT/heightscale;
    }*/

    /*public int getTile(int row, int col) {
        return map[row][col];
    }*/

    /**
     * Metoda zwracajaca mape
     * @return zwraca mape
     */
    public int[][] getMap(){
        return map;
    }

    /**
     * metoda zwraca szerokosc mapy
     * @return zwraca szerokosc mapy
     */
    public int getMapWidth(){return mapWidth*tileSize;}
    /**
     * metoda zwraca szerokosc mapy
     * @return zwraca szerokosc mapy
     */
    public int getMapHeight(){return mapHeight*tileSize;}

    /**
     * Metoda pobierajaca rozmiar kafelka.
     * @return zwraca rozmiar kafelka
     */
    public int getTileSize(){
        return tileSize;
    }

    /**
     * Metoda sprawdza kolizje.
     * @param row wiersz
     * @param col kolumna
     * @return
     */
    public boolean isBlocked(int row, int col){
        int matrix = map[row][col];
        int r = matrix / tiles[0].length;
        int c = matrix % tiles[0].length;
        return tiles[r][c].isBlocked();
    }

    /**
     * Metoda ustawia widziana pozycje mapy w poziomie.
     * @param i rozmiar mapy
     */
    public void setx(int i) {
        System.out.println(x);
        System.out.println(i);
        System.out.println(minx);
        System.out.println(maxx);
        x = i;
        if(x < minx){
            x = minx;
        }
        if(x>maxx){
            x = maxx;
        }
    }

    /**
     * Metoda ustawia widziana pozycje mapy w poziomie.
     * @param i
     */
    public void sety(int i) {
        y = i;
        if(y < miny){
            y= miny;
        }
    }

    /**
     *
     * @param g
     * @param width
     * @param height
     */
    public void draw(Graphics2D g, int width, int height) {
        for(int row = 0; row <mapHeight; row++) {
            for (int col = 0; col < mapWidth; col++) {
                int rowcol = this.map[row][col];
                int r = rowcol / tiles[0].length;
                int c = rowcol % tiles[0].length;


                g.drawImage(
                        tiles[r][c].getImage(),
                        (x + col * tileSize) * width/Board.WIDTH,
                        (y + row  * tileSize) * height/Board.HEIGHT,
                        32 * width/Board.WIDTH,
                        32 * height/Board.HEIGHT,
                        null
                );
            }
        }
    }


}
