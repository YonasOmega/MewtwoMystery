import javax.imageio.ImageIO;
import java.io.FileInputStream;
import java.io.IOException;

public class OBJ_Door extends Character{
    GamePanel gp;
    public OBJ_Door(GamePanel gp) {
        super(gp);

        name = "Door";
        down1 = setup("TPP Game Project/res/Objects/door",gp.tileSize,gp.tileSize);
        collision = true;

        //Changes hitbox of door
        solidArea.x = 0;
        solidArea.y = 16;
        solidArea.width = 48;
        solidArea.height = 32;
        solidAreaDefaultX = solidArea.x;
        solidAreaDefaultY = solidArea.y;
    }
}
