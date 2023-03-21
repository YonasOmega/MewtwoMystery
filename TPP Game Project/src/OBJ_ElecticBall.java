public class OBJ_ElecticBall extends Projectile{
    GamePanel gp;
    public OBJ_ElecticBall(GamePanel gp) {
        super(gp);
        this.gp = gp;

        name = "ElectricBall";
        speed = 8;
        maxLife = 80;
        life = maxLife;
        attack = 3;
        useCost = 1;
        alive = false;
        getImage();
    }

    public void getImage() {
        up1 = setup("TPP Game Project/res/Projectile/Electric_down_1",gp.tileSize,gp.tileSize);
        up2 = setup("TPP Game Project/res/Projectile/Electric_down_1",gp.tileSize,gp.tileSize);
        down1 = setup("TPP Game Project/res/Projectile/Electric_down_1",gp.tileSize,gp.tileSize);
        down2 = setup("TPP Game Project/res/Projectile/Electric_down_1",gp.tileSize,gp.tileSize);
        right1 = setup("TPP Game Project/res/Projectile/Electric_down_1",gp.tileSize,gp.tileSize);
        right2 = setup("TPP Game Project/res/Projectile/Electric_down_1",gp.tileSize,gp.tileSize);
        left1 = setup("TPP Game Project/res/Projectile/Electric_down_1",gp.tileSize,gp.tileSize);
        left2 = setup("TPP Game Project/res/Projectile/Electric_down_1",gp.tileSize,gp.tileSize);
    }
}
