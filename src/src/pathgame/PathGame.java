package pathgame;
/**
 * Created by Piotrek on 2016-12-25.
 */

import javax.swing.*;

/**

 */
public class PathGame{
    /**
     * Metoda glowna main otwierajaca okno i ustawiajaca w nim tlo Board @link Board
     * @param args
     */
    public static void main(String[] args) {
        JFrame window = new JFrame("Mission to Mars");
        window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        window.setContentPane(new Board());
        window.pack();
        window.setVisible(true);
    }
}