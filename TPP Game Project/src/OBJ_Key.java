import java.io.FileInputStream;
import java.io.IOException;
import javax.imageio.ImageIO;
public class OBJ_Key extends Character {
    public OBJ_Key(GamePanel gp) {
        super(gp);

        name = "Key";
        down1 = setup("TPP Game Project/res/Objects/key",gp.tileSize,gp.tileSize);
    }
}
