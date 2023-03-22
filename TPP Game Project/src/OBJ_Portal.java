public class OBJ_Portal extends Character {
    GamePanel gp;
    public OBJ_Portal(GamePanel gp) {
        super(gp);

        name = "Portal";
        down1 = setup("TPP Game Project/res/Objects/portal",gp.tileSize,gp.tileSize);
    }
}
