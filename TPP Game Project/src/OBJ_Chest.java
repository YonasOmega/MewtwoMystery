import javax.imageio.ImageIO;
import java.io.FileInputStream;
import java.io.IOException;

public class OBJ_Chest extends Character {
    GamePanel gp;
    public OBJ_Chest(GamePanel gp) {
        super(gp);

        name = "Chest";
        down1 = setup("TPP Game Project/res/Objects/portal",gp.tileSize,gp.tileSize);
    }
}
