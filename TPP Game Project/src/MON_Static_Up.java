import java.util.Random;

public class MON_Static_Up extends Character {
    public MON_Static_Up(GamePanel gp) {
        super(gp);
        direction = "up";
        type = 2;
        name = "Green Slime";
        speed = 1;
        maxLife = 15;
        life = maxLife;
        attack = 5;
        defense = 0;
        projectile = new OBJ_ElecticBall(gp);


        solidArea.x = 3;
        solidArea.y = 18;
        solidArea.width = 42;
        solidArea.height = 30;
        solidAreaDefaultX = solidArea.x;
        solidAreaDefaultY = solidArea.y;

        getImage();
    }
    public void getImage() {
        up1 = setup("TPP Game Project/res/monster/elec_up_1",gp.tileSize,gp.tileSize);
        up2 = setup("TPP Game Project/res/monster/elec_up_2",gp.tileSize,gp.tileSize);
        down1 = setup("TPP Game Project/res/monster/elec_up_1",gp.tileSize,gp.tileSize);
        down2 = setup("TPP Game Project/res/monster/elec_up_2",gp.tileSize,gp.tileSize);
        left1 = setup("TPP Game Project/res/monster/elec_up_1",gp.tileSize,gp.tileSize);
        left2 = setup("TPP Game Project/res/monster/elec_up_2",gp.tileSize,gp.tileSize);
        right1 = setup("TPP Game Project/res/monster/elec_up_1",gp.tileSize,gp.tileSize);
        right2 = setup("TPP Game Project/res/monster/elec_up_2",gp.tileSize,gp.tileSize);
    }
    public void setAction() {

        int i = new Random().nextInt(100)+1;
        if(i>90&&projectile.alive==false&&shotAvailableCounter == 45){
            projectile.set(worldX, worldY, direction, true, this);
            gp.projectileList.add(projectile);
            shotAvailableCounter=0;//d
        }
    }
    public void update() {

        setAction();


        //Alternates sprite number so draw method can switch sprites for animation.

        spriteCounter++;    //every frame increases counter by 1
        if (spriteCounter > 10) {    //changes sprite every 10 frames
            if (spriteNum == 1) {
                spriteNum = 2;
            } else if (spriteNum == 2) {
                spriteNum = 1;
            }
            spriteCounter = 0;
        }
        if(invincible == true) {

            invincibleCounter++;
            if(invincibleCounter > 40) {
                invincible = false;
                invincibleCounter = 0;
            }
        }
        if(shotAvailableCounter < 45) {
            shotAvailableCounter++;
        }
    }


}
