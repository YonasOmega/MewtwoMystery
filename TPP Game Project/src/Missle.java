import java.util.Random;

public class Missle extends Character {
    int timer = 0;
    public Missle(GamePanel gp) {
        super(gp);
        System.out.println("ok buddy");
        type = 2;
        name = "Missle";
        speed = 8;
        maxLife = 1;
        life = maxLife;
        attack = 5;
        defense = 0;



        solidArea.x = 3;
        solidArea.y = 18;
        solidArea.width = 42;
        solidArea.height = 30;
        solidAreaDefaultX = solidArea.x;
        solidAreaDefaultY = solidArea.y;

        getImage();
    }
    public void getImage() {
        up1 = setup("TPP Game Project/res/monster/pichu_missle_down_1",gp.tileSize,gp.tileSize);
        up2 = setup("TPP Game Project/res/monster/pichu_missle_down_2",gp.tileSize,gp.tileSize);
        down1 = setup("TPP Game Project/res/monster/pichu_missle_down_1",gp.tileSize,gp.tileSize);
        down2 = setup("TPP Game Project/res/monster/pichu_missle_down_2",gp.tileSize,gp.tileSize);
        left1 = setup("TPP Game Project/res/monster/pichu_missle_down_1",gp.tileSize,gp.tileSize);
        left2 = setup("TPP Game Project/res/monster/pichu_missle_down_2",gp.tileSize,gp.tileSize);
        right1 = setup("TPP Game Project/res/monster/pichu_missle_down_1",gp.tileSize,gp.tileSize);
        right2 = setup("TPP Game Project/res/monster/pichu_missle_down_2",gp.tileSize,gp.tileSize);
    }
    public void update(){
        timer++;
        super.update();
        int xDistance = Math.abs(worldX - gp.player.worldX);
        int yDistance = Math.abs(worldY - gp.player.worldY);
        int tileDistance = (xDistance + yDistance)/gp.tileSize;

        if( onPath == false && tileDistance < 5){
            int i = new Random().nextInt(100)+1;
                    if(i>50){
                        onPath= true;
                    }

        }
        if(onPath==true&&tileDistance>50){
            onPath = false;
        }
        if (gp.cChecker.checkPlayer(this) || timer >= 600) {
            life = 0;
        }
    }
    public void setAction() {

        if(onPath == true ) {
//            int goalCol = 39;
//            int goalRow = 7;
            int goalCol = (gp.player.worldX + gp.player.solidArea.x)/gp.tileSize;
            int goalRow = (gp.player.worldY + gp.player.solidArea.y)/gp.tileSize;

            searchPath(goalCol, goalRow);

            int i = new Random().nextInt(100)+1;

        }else {
            actionLockCounter++;

            if (actionLockCounter == 120) { // Slows down NPC so he moves every 2 seconds (60FPS)
                Random random = new Random();
                int i = random.nextInt(100) + 1; //random num 1 - 100

                if (i <= 25) {
                    direction = "up";
                }
                if (i > 25 && i <= 50) {
                    direction = "down";
                }
                if (i > 50 && i <= 75) {
                    direction = "left";
                }
                if (i > 65 && i <= 100) {
                    direction = "right";
                }

                actionLockCounter = 0;
            }
        }

    }
    public void damageReaction() {

        actionLockCounter = 0;

        onPath=true;
        if(gp.player.direction.equals("up-right") || gp.player.direction.equals("down-right")) {
            direction = "right";
        } else if(gp.player.direction.equals("up-left") || gp.player.direction.equals("down-left")) {
            direction = "left";
        } else {
            direction = gp.player.direction;
        }

    }
}
