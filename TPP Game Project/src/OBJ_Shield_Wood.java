public class OBJ_Shield_Wood extends Character{
    public OBJ_Shield_Wood(GamePanel gp) {
        super(gp);
        name = "Wooden Shield";
        down1 = setup("TPP Game Project/res/Objects/Shield_Wood",gp.tileSize,gp.tileSize);
        defenseValue=1;
    }
}
