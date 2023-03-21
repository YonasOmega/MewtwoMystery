import java.awt.*;
import java.awt.image.BufferedImage;

public class Map extends TileManager {

    GamePanel gp;
    BufferedImage worldMap[];
    public boolean miniMapOn = false;

    public Map(GamePanel gp) {
        super(gp);
        this.gp = gp;
        createWorldMap();
    }
    public void createWorldMap() {
        worldMap = new BufferedImage[gp.maxMap];
        int worldMapWidth = gp.tileSize * gp.maxWorldCol;
        int worldMapHeight = gp.tileSize * gp.maxWorldRow;

       // for(int i = 0; i < gp.maxMap; i++) {
            worldMap[0] = new BufferedImage(worldMapWidth, worldMapHeight, BufferedImage.TYPE_INT_ARGB);
            Graphics2D g2 = (Graphics2D)worldMap[0].createGraphics();

            int col = 0;
            int row = 0;

            while(col < gp.maxWorldCol && row< gp.maxWorldRow) {
                int tileNum = mapTileNum[0][col][row];
                int x = gp.tileSize * col;
                int y = gp.tileSize * row;
                g2.drawImage(Tiles[tileNum].image, x, y, null);

                col++;
                if(col == gp.maxWorldCol) {
                    col = 0;
                    row++;
                }
            }
            g2.dispose();

        //}
    }
    public void drawFullMapScreen(Graphics2D g2) {

        //Background Color
        g2.setColor(Color.black);
        g2.fillRect(0, 0, gp.screenWidth, gp.screenHeight);

        // Draw Map
        int width = 1500;
        int height = 1500;
        int x = gp.screenWidth/2 - width/2;
        int y = gp.screenHeight/2 - height/2;
        g2.drawImage(worldMap[gp.currentMap], x, y, width, height, null);

        // Draw Player
        double scale = (double) (gp.tileSize * gp.maxWorldCol)/width;
        int playerX = (int) (x + gp.player.worldX/scale) - 20;
        int playerY = (int) (y + gp.player.worldY/scale) - 20;
        int playerSize = (int) (gp.tileSize/scale) + 35;
        g2.drawImage(gp.player.down1, playerX, playerY, playerSize, playerSize + 25, null);

        //Hint
        g2.setFont(gp.ui.arial_40.deriveFont(32f));
        g2.setColor(Color.white);
        g2.drawString("Press M to close", 750, 750);

    }
}

