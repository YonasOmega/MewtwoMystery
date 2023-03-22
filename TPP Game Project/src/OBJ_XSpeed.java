public class OBJ_XSpeed extends Character{
    GamePanel gp;
    public OBJ_XSpeed(GamePanel gp) {
        super(gp);

        name = "XSpeed";
        down1 = setup("TPP Game Project/res/Objects/Grid_X_Speed",gp.tileSize,gp.tileSize);
    }
}
