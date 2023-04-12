import javax.imageio.ImageIO;
import java.awt.*;
import java.io.*;

public class TileManager {
    GamePanel gp;
    public Tiles[] Tiles;

    int mapTileNum[][][];
    boolean drawPath = false;


        public TileManager(GamePanel gp){
            this.gp=gp;
            Tiles = new Tiles[10];
            mapTileNum = new int[gp.maxMap][gp.maxWorldCol][gp.maxWorldRow];

            getTileImage();
            loadMap("TPP Game Project/res/maps/Map03.txt",0);
        }

        public void getTileImage(){
                //ImageIO.read(get.Class().getResourceAsStream("TPP Game Project/tiles/grass.png"));
                //tutorial version ^
            setup(0, "black", false);
            setup(1, "floor", false);
            setup(2, "top", true);
      //      setup(3, "earth", false);
            setup(4, "sand", true);
            setup(5, "wall2", true);
            setup(6, "black", true);
            setup(7, "wall3",true);
            setup(8, "heal",false);
            setup(9, "teleport",false);
        }
    public void setup(int index, String imageName, boolean collision) {
            UtilityTool uTool = new UtilityTool();

            try{
                Tiles[index] = new Tiles();
                Tiles[index].image = ImageIO.read(new FileInputStream("TPP Game Project/res/tiles/" + imageName + ".png"));
                Tiles[index].image = uTool.scaleImage(Tiles[index].image, gp.tileSize, gp.tileSize);
                Tiles[index].collision = collision;

            } catch(IOException e) {
                e.printStackTrace();
            }
    }
    public void loadMap(String mapFilePath, int map){
        try{
            InputStream is = new FileInputStream((mapFilePath));
            BufferedReader br = new BufferedReader(new InputStreamReader(is));//reads contents of text file.
            int col=0;
            int row=0;
            while(col<gp.maxWorldCol && row< gp.maxWorldRow){

                String line = br.readLine();//reads whole line of text.

                while (col < gp.maxWorldCol) {
                    String numbers[]=line.split(" ");
                    int num = Integer.parseInt(numbers[col]);
                    mapTileNum[map][col][row]=num;
                    col++;

                }
                if (col==gp.maxWorldCol){
                    col=0;
                    row++;
                }
            }
        }catch(Exception e){

        }
    }
        public void draw(Graphics2D graphics2){
            int worldCol = 0;
            int worldRow = 0;

            while (worldCol < gp.maxWorldCol&& worldRow<gp.maxWorldRow){

                int tileNum=mapTileNum[gp.currentMap][worldCol][worldRow];


                //usually would display only screen size from 0,0
                //with this figures out where player is and displays pixels surrounding them.
                //+gp.player.screenX screen makes it so player is not displayed in right top corner.
                int worldX = worldCol*gp.tileSize;
                int worldY = worldRow*gp.tileSize;
                int screenX = worldX - gp.player.worldX + gp.player.screenX;
                int screenY = worldY - gp.player.worldY + gp.player.screenY;


                if (worldX + gp.tileSize > gp.player.worldX - gp.player.screenX &&
                    worldX - gp.tileSize < gp.player.worldX + gp.player.screenX &&
                    worldY + gp.tileSize > gp.player.worldY - gp.player.screenY &&
                    worldY - gp.tileSize < gp.player.worldY + gp.player.screenY) {//doesn't display pixels if its within camera

                    graphics2.drawImage(Tiles[tileNum].image, screenX, screenY, null);
                }
                worldCol++;

                if (worldCol == gp.maxWorldCol){
                    worldCol = 0;
                    worldRow++;
                }

            }
            if (drawPath == true){
                graphics2.setColor(new Color(255, 0, 0, 70));

                for (int i = 0; i < gp.pFinder.pathList.size(); i++) {
                    int worldX = gp.pFinder.pathList.get(i).col * gp.tileSize;
                    int worldY = gp.pFinder.pathList.get(i).row * gp.tileSize;
                    int screenX = worldX - gp.player.worldX + gp.player.screenX;
                    int screenY = worldY - gp.player.worldY + gp.player.screenY;

                    graphics2.fillRect(screenX, screenY, gp.tileSize, gp.tileSize);


                }
            }

        }
    public void draw2(Graphics2D graphics2) {
        int worldCol = 0;
        int worldRow = 0;

        while (worldCol < gp.maxWorldCol && worldRow < gp.maxWorldRow) {

            int tileNum = mapTileNum[gp.currentMap][worldCol][worldRow];


            //usually would display only screen size from 0,0
            //with this figures out where player is and displays pixels surrounding them.
            //+gp.player.screenX screen makes it so player is not displayed in right top corner.
            int worldX = worldCol * gp.tileSize;
            int worldY = worldRow * gp.tileSize;
            int screenX = worldX - gp.player.worldX + gp.player.screenX;
            int screenY = worldY - gp.player.worldY + gp.player.screenY;


            if (worldX + gp.tileSize > gp.player.worldX - gp.player.screenX &&
                    worldX - gp.tileSize < gp.player.worldX + gp.player.screenX &&
                    worldY + gp.tileSize > gp.player.worldY - gp.player.screenY &&
                    worldY - gp.tileSize < gp.player.worldY + gp.player.screenY && tileNum==2) {//doesn't display pixels if its within camera

                graphics2.drawImage(Tiles[tileNum].image, screenX, screenY, null);
            }
            worldCol++;

            if (worldCol == gp.maxWorldCol) {
                worldCol = 0;
                worldRow++;
            }
        }
    }

}
