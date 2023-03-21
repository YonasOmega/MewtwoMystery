public class OBJ_Sword_Normal extends Character{

    public OBJ_Sword_Normal(GamePanel gp) {
        super(gp);
        name = "Normal Sword";
        down1 = setup("TPP Game Project/res/Objects/sword_normal", gp.tileSize, gp.tileSize);
        attackValue = 10;
    }
}
