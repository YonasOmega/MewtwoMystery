import javax.swing.*;

import java.net.MalformedURLException;

public class Main {
    /**
     * Acts as Game Driver
     *
     * @param args
     */
    public static void main(String[] args) throws MalformedURLException {
        JFrame window = new JFrame();                           //Creates Window


        window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);  //Stops Program when Exit button is pressed

        window.setResizable(false);                             //Resize: currently Unavailable

        window.setTitle("2D Adventure");                        //Title on window border

        GamePanel gamePanel=new GamePanel();                    //Creates new GamePanel

        window.add(gamePanel);

        window.pack();

        window.setLocationRelativeTo(null);

        window.setVisible(true);
        gamePanel.setupGame();
        gamePanel.startGameThread();                            //Starts game
    }
}  