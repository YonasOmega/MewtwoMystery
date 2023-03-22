import jdk.jfr.Event;

import java.awt.*;
import java.awt.image.BufferedImage;
import java.util.Random;

public class MON_Boss_Super extends Character {
    int tempPlayerX;
    int tempPlayerY;

    int shotAvailableCounter1;
    public MON_Boss_Super(GamePanel gp) {
        super(gp);
        BossDead=false;
        type = 3;
        name = "Boss";
        speed = 6;
        maxLife = 100;
        life = 0;
        attack = 10;
        defense = 0;
        reviving=true;

        int size = gp.tileSize*5;
        solidArea.x = 48;
        solidArea.y = 48;
        solidArea.width = size - 48*2;
        solidArea.height = size - 48;
        solidAreaDefaultX = solidArea.x;
        solidAreaDefaultY = solidArea.y;
        getImage();
        dying=false;
        alive=true;
        gp.eHandler.setBossbattle(true);
        getImage();
    }
    public void getImage() {
        int i = 5;
        if (reviving == true) {
            up1 = setup("TPP Game Project/res/monster/magnezone_down_2", gp.tileSize * i, gp.tileSize * i);
            up2 = setup("TPP Game Project/res/monster/magnezone_down_2", gp.tileSize * i, gp.tileSize * i);
            down1 = setup("TPP Game Project/res/monster/magnezone_down_2", gp.tileSize * i, gp.tileSize * i);
            down2 = setup("TPP Game Project/res/monster/magnezone_down_2", gp.tileSize * i, gp.tileSize * i);
            right1 = setup("TPP Game Project/res/monster/magnezone_down_2", gp.tileSize * i, gp.tileSize * i);
            right2 = setup("TPP Game Project/res/monster/magnezone_down_2", gp.tileSize * i, gp.tileSize * i);
            left1 = setup("TPP Game Project/res/monster/magnezone_down_2", gp.tileSize * i, gp.tileSize * i);
            left2 = setup("TPP Game Project/res/monster/magnezone_down_2", gp.tileSize * i, gp.tileSize * i);


        }else{
            up1 = setup("TPP Game Project/res/monster/magnezone_down_1", gp.tileSize * i, gp.tileSize * i);
            up2 = setup("TPP Game Project/res/monster/magnezone_down_1", gp.tileSize * i, gp.tileSize * i);
            down1 = setup("TPP Game Project/res/monster/magnezone_down_1", gp.tileSize * i, gp.tileSize * i);
            down2 = setup("TPP Game Project/res/monster/magnezone_down_1", gp.tileSize * i, gp.tileSize * i);
            right1 = setup("TPP Game Project/res/monster/magnezone_down_1", gp.tileSize * i, gp.tileSize * i);
            right2 = setup("TPP Game Project/res/monster/magnezone_down_1", gp.tileSize * i, gp.tileSize * i);
            left1 = setup("TPP Game Project/res/monster/magnezone_down_1", gp.tileSize * i, gp.tileSize * i);
            left2 = setup("TPP Game Project/res/monster/magnezone_down_1", gp.tileSize * i, gp.tileSize * i);
        }

    }
    public void update(){
        super.update();
        int xDistance = Math.abs(worldX - gp.player.worldX);
        int yDistance = Math.abs(worldY - gp.player.worldY);
        int tileDistance = (xDistance + yDistance)/gp.tileSize;
        if (life==maxLife){
            reviving=false;
        }
        if (onPath == false && tileDistance < 5){
            int i = new Random().nextInt(100)+1;
            if (i>50){
                onPath= true;
            }

        }
        if (onPath==true&&tileDistance>20){
            onPath = false;
        }
    }
    public void setAction() {

        if (life == 0&&reviving!=true) {
            gp.stopMusic();
            gp.playMusic(0);
            BossDead = true;
            gp.eHandler.setBossbattle(false);

        }
        if (getTileDistance(gp.player) < 100) {
            moveTowardPlayer(60);
            if (shotAvailableCounter1%25==0) {

                tempPlayerX=gp.player.worldX;
                tempPlayerY=gp.player.worldY;
                if (shotAvailableCounter%15==0) {
                    OBJ_Boss_Projectile proj = new OBJ_Boss_Projectile(gp, this);
                    proj.set(getCenterX(), getCenterY(), tempPlayerX, tempPlayerY, "polar", true, this);
                    gp.projectileList.add(proj);
                }
            }
            if (shotAvailableCounter1==50){

            }
            if (shotAvailableCounter1 == 100){
                shotAvailableCounter1 = 0;


            }
            if (shotAvailableCounter1 < 100) {
                shotAvailableCounter1++;
            }



        } else {
            tempPlayerX=gp.player.worldX;
            tempPlayerY= gp.player.worldY;
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
    public void checkDrop() {
        gp.obj[0][8] = new OBJ_Key(gp);
        gp.obj[0][8].worldX = worldX;
        gp.obj[0][8].worldY = worldY;


    }
    public void damageReaction() {

        actionLockCounter = 0;

        onPath=true;
        if (gp.player.direction.equals("up-right") || gp.player.direction.equals("down-right")) {
            direction = "right";
        } else if (gp.player.direction.equals("up-left") || gp.player.direction.equals("down-left")) {
            direction = "left";
        } else {
            direction = gp.player.direction;
        }

    }
    public void draw(Graphics2D g2) {


        if (drawRange>10){
        }
        BufferedImage image = null;
        int screenX = worldX - gp.player.worldX + gp.player.screenX;
        int screenY = worldY - gp.player.worldY + gp.player.screenY;

        if (worldX + gp.tileSize*drawRange > gp.player.worldX - gp.player.screenX &&
                worldX - gp.tileSize*drawRange < gp.player.worldX + gp.player.screenX &&
                worldY + gp.tileSize*drawRange > gp.player.worldY - gp.player.screenY &&
                worldY - gp.tileSize*drawRange < gp.player.worldY + gp.player.screenY) {
            switch(direction) {
                case "up", "polar":
                    if (spriteNum==1){
                        image = up1;
                    }
                    if (spriteNum == 2){
                        image = up2;
                    }

                    break;
                case "down":
                    if (spriteNum==1){
                        image = down1;
                    }
                    if (spriteNum == 2){
                        image = down2;
                    }
                    break;
                case "right", "up-right", "down-right":
                    if (spriteNum==1){
                        image = right1;
                    }
                    if (spriteNum == 2){
                        image = right2;
                    }
                    break;
                case "left", "up-left", "down-left":
                    if (spriteNum==1){
                        image = left1;
                    }
                    if (spriteNum == 2){
                        image = left2;
                    }
                    break;
            }
            // Monster HP Bar
            if (type <= 2 && hpBarOn == true) {

                double oneScale = (double)gp.tileSize/maxLife;
                double hpBarValue = oneScale*life;

                g2.setColor(new Color(35, 35, 35));
                g2.fillRect(screenX-1, screenY-16, gp.tileSize+2, 12);

                g2.setColor(new Color(255, 0, 30));
                g2.fillRect(screenX, screenY - 15,(int)hpBarValue, 10);

                hpBarCounter++;
                if (hpBarCounter > 600) {
                    hpBarCounter = 0;
                    hpBarOn = false;
                }
            }
            if (type==3){
                drawRange=80;
                double oneScale = (double)gp.screenWidth/maxLife;
                double hpBarValue = oneScale*life;

                g2.setColor(new Color(35, 35, 35));
                g2.fillRect(0, 0, gp.screenWidth, gp.tileSize);

                g2.setColor(new Color(255, 0, 30));
                g2.fillRect(1, 1,(int)hpBarValue, gp.tileSize-1);


            }

            if (invincible == true) {
                hpBarOn = true;
                hpBarCounter = 0;
                //changeAlpha(g2,0.4f);

            }
            if (dying == true) {
                gp.stopMusic();
                gp.playMusic(0);//suspence music
                dyingAnimation(g2);
                gp.eHandler.setBossbattle(false);

            }

            g2.drawImage(image, screenX, screenY,null);
            changeAlpha(g2, 1f);
        }
    }

}

//        type = 2;
//                name = monName;
//                defaultSpeed = 1;
//                speed = defaultSpeed;
//                maxLife = 50;
//                life = maxLife;
//                attack = 10;
//                defense = 2;
//                exp = 50;
//                projectile = new OBJ_ElecticBall(gp);
//                //      knockBackPower = 5;
//
//                int size = gp.tileSize * 5;
//                solidArea.x = 48;
//                solidArea.y = 48;
//                solidArea.width = size - 48 * 2;
//                solidArea.height = size - 48;
//                solidAreaDefaultX = solidArea.x;
//                solidAreaDefaultY = solidArea.y;
//                attackArea.width = 170;
//                attackArea.height = 170;

