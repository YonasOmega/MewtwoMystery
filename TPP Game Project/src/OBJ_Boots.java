import javax.imageio.ImageIO;
import java.io.FileInputStream;
import java.io.IOException;

public class OBJ_Boots extends Character{
    GamePanel gp;
    public OBJ_Boots(GamePanel gp) {
        super(gp);

        name = "Boots";
        down1 = setup("TPP Game Project/res/Objects/Grid_X_Speed",gp.tileSize,gp.tileSize);
    }
}
