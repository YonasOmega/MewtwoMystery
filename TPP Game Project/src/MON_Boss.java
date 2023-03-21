import jdk.jfr.Event;

import java.awt.*;
import java.awt.image.BufferedImage;
import java.util.Random;

public class MON_Boss extends Character {
    int tempPlayerX;
    int tempPlayerY;
    int missleCounter = 0;
    int i=1;
    int shotAvailableCounter1;
    public MON_Boss(GamePanel gp) {
        super(gp);
        type = 4;
        name = "Boss";
        speed = 3;
        maxLife = 100;
        life = maxLife;
        attack = 10;
        defense = 0;

        int size = gp.tileSize*5;
        solidArea.x = 48;
        solidArea.y = 48;
        solidArea.width = size - 48*2;//3
        solidArea.height = size - 48;//4
        solidAreaDefaultX = solidArea.x;
        solidAreaDefaultY = solidArea.y;

        getImage();
    }
    public void getImage() {
        int i = 5;
        if(inRage == false) {
            up1 = setup("TPP Game Project/res/monster/magnezone_down_1", gp.tileSize * i, gp.tileSize * i);
            up2 = setup("TPP Game Project/res/monster/magnezone_down_1", gp.tileSize * i, gp.tileSize * i);
            down1 = setup("TPP Game Project/res/monster/magnezone_down_1", gp.tileSize * i, gp.tileSize * i);
            down2 = setup("TPP Game Project/res/monster/magnezone_down_1", gp.tileSize * i, gp.tileSize * i);
            right1 = setup("TPP Game Project/res/monster/magnezone_down_1", gp.tileSize * i, gp.tileSize * i);
            right2 = setup("TPP Game Project/res/monster/magnezone_down_1", gp.tileSize * i, gp.tileSize * i);
            left1 = setup("TPP Game Project/res/monster/magnezone_down_1", gp.tileSize * i, gp.tileSize * i);
            left2 = setup("TPP Game Project/res/monster/magnezone_down_1", gp.tileSize * i, gp.tileSize * i);
        }
        if(inRage == true) {
            up1 = setup("TPP Game Project/res/monster/magnezone_down_2", gp.tileSize * i, gp.tileSize * i);
            up2 = setup("TPP Game Project/res/monster/magnezone_down_2", gp.tileSize * i, gp.tileSize * i);
            down1 = setup("TPP Game Project/res/monster/magnezone_down_2", gp.tileSize * i, gp.tileSize * i);
            down2 = setup("TPP Game Project/res/monster/magnezone_down_2", gp.tileSize * i, gp.tileSize * i);
            right1 = setup("TPP Game Project/res/monster/magnezone_down_2", gp.tileSize * i, gp.tileSize * i);
            right2 = setup("TPP Game Project/res/monster/magnezone_down_2", gp.tileSize * i, gp.tileSize * i);
            left1 = setup("TPP Game Project/res/monster/magnezone_down_2", gp.tileSize * i, gp.tileSize * i);
            left2 = setup("TPP Game Project/res/monster/magnezone_down_2", gp.tileSize * i, gp.tileSize * i);
        }

    }
    public void update(){
        missleCounter++;
        super.update();
        int xDistance = Math.abs(worldX - gp.player.worldX);
        int yDistance = Math.abs(worldY - gp.player.worldY);
        int tileDistance = (xDistance + yDistance)/gp.tileSize;

        if(onPath == false && tileDistance < 5){
            int i = new Random().nextInt(100)+1;
            if(i>50){
                onPath= true;
            }

        }
        if(onPath==true&&tileDistance>20){
            onPath = false;
        }
    }
    public void setAction() {

       if(inRage == false && life < 50) {
            inRage = true;
            getImage();
            speed++;
        }
        if(life <= 0) {
            BossDead = true;

            dying=true;

            gp.eHandler.setBossbattle(false);

        }
        if(getTileDistance(gp.player) < 100) {
            moveTowardPlayer(60);
            if(shotAvailableCounter1%25==0) {
//                System.out.println(shotAvailableCounter1+"\n"+"--------");
                tempPlayerX=gp.player.worldX;
                tempPlayerY=gp.player.worldY;
                if(shotAvailableCounter%15==0) {


                    OBJ_Boss_Projectile proj = new OBJ_Boss_Projectile(gp, this);
                    proj.set(getCenterX(), getCenterY(), tempPlayerX, tempPlayerY, "polar", true, this);
                    gp.projectileList.add(proj);
                }
            }
            if(shotAvailableCounter1==50){

            }
            if(shotAvailableCounter1 == 100){
                    shotAvailableCounter1 = 0;


            }
            if(shotAvailableCounter1 < 100) {
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
    public void missle() {

       if(inRage && missleCounter >= 400) {

            int  rightX = (gp.monster[0][2].getCenterX() )/gp.tileSize + 3;
            int leftX = (gp.monster[0][2].worldX)/gp.tileSize - 1;
            int upY = (gp.monster[0][2].worldY)/gp.tileSize - 1 ;
            int downY = (gp.monster[0][2].getCenterY())/gp.tileSize;

            int rightSide= gp.tileM.mapTileNum[gp.currentMap][rightX][getCenterY()/gp.tileSize];
            int leftSide = gp.tileM.mapTileNum[gp.currentMap][leftX][getCenterY()/gp.tileSize];
            int upSide = gp.tileM.mapTileNum[gp.currentMap][getCenterX()/gp.tileSize][upY];
            int downSide = gp.tileM.mapTileNum[gp.currentMap][getCenterX()/gp.tileSize][downY];
            int validX = 0;
            int validY = 0;
            if (gp.tileM.Tiles[rightSide].collision == false) {
                System.out.println("rightSide");
                validX = rightX*gp.tileSize;
                validY = getCenterY();
            } else if (gp.tileM.Tiles[leftSide ].collision == false) {
                System.out.println("leftSide");
                validX = leftX*gp.tileSize;
                validY = getCenterY();
            } else if (gp.tileM.Tiles[upSide ].collision == false) {
                System.out.println("upSide");
                validX = getCenterX();
                validY = upY*gp.tileSize;
            } else if (gp.tileM.Tiles[downSide ].collision == false) {
                System.out.println("downSide");
                validX = getCenterX();
                validY = downY*gp.tileSize;
            }

            gp.monster[0][29+i] = new Missle(gp);
            gp.monster[0][29+i].worldX = validX;
            gp.monster[0][29+i].worldY = validY;
            i++;
            if(i==10){
                i=1;
            }
           System.out.println("Missle Loc: "+validX/gp.tileSize+", "+validY/gp.tileSize);
           System.out.println("Monster Loc: "+getCenterX()/gp.tileSize+", "+ getCenterY()/gp.tileSize);
           missleCounter=0;
        }
    }

    public void checkDrop() {
        gp.stopMusic();
        gp.playMusic(1);//suspence music
        gp.eHandler.setBossbattle(false);
        gp.monster[0][26] = new MON_Boss_Super(gp);
        gp.monster[0][26].worldX = gp.monster[0][2].worldX;
        gp.monster[0][26].worldY = gp.monster[0][2].worldY;

        gp.monster[0][100] = new MON_Joltik(gp);
        gp.monster[0][100].worldX = 137 * gp.tileSize;
        gp.monster[0][100].worldY = 20 * gp.tileSize;

        gp.monster[0][101] = new MON_Joltik(gp);
        gp.monster[0][101].worldX = 130 * gp.tileSize;
        gp.monster[0][101].worldY = 20 * gp.tileSize;

        gp.monster[0][102] = new MON_Joltik(gp);
        gp.monster[0][102].worldX = 118 * gp.tileSize;
        gp.monster[0][102].worldY = 20 * gp.tileSize;

        gp.monster[0][103] = new MON_Joltik(gp);
        gp.monster[0][103].worldX = 111 * gp.tileSize;
        gp.monster[0][103].worldY = 20 * gp.tileSize;
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
    public void draw(Graphics2D g2) {
        if(drawRange>10){
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
            if(type <= 2 && hpBarOn == true) {

                double oneScale = (double)gp.tileSize/maxLife;
                double hpBarValue = oneScale*life;

                g2.setColor(new Color(35, 35, 35));
                g2.fillRect(screenX-1, screenY-16, gp.tileSize+2, 12);

                g2.setColor(new Color(255, 0, 30));
                g2.fillRect(screenX, screenY - 15,(int)hpBarValue, 10);

                hpBarCounter++;
                if(hpBarCounter > 600) {
                    hpBarCounter = 0;
                    hpBarOn = false;
                }
            }
            if(type==4){
                drawRange=80;
                double oneScale = (double)gp.screenWidth/maxLife;
                double hpBarValue = oneScale*life;

                g2.setColor(new Color(35, 35, 35));
                g2.fillRect(0, 0, gp.screenWidth, gp.tileSize);

                g2.setColor(new Color(255, 0, 30));
                g2.fillRect(1, 1,(int)hpBarValue, gp.tileSize-1);


            }

            if(invincible == true) {
                hpBarOn = true;
                hpBarCounter = 0;
                //changeAlpha(g2,0.4f);

            }
            if(dying == true) {
                gp.stopMusic();
                dyingAnimation(g2);
                gp.eHandler.setBossbattle(false);
                alive=false;
            }

            g2.drawImage(image, screenX, screenY,null);
            changeAlpha(g2, 1f);
        }
    }
    public void dyingAnimation(Graphics2D g2) {



            dyingCounter++;
            int i = 10;

            if (dyingCounter <= 5) {
                changeAlpha(g2, 0f);
            }
            if (dyingCounter > i && dyingCounter <= i * 2) {
                changeAlpha(g2, 1f);
            }
            if (dyingCounter > i * 2 && dyingCounter <= i * 3) {
                changeAlpha(g2, 0f);
            }
            if (dyingCounter > i * 3 && dyingCounter <= i * 4) {
                changeAlpha(g2, 1f);
            }
            if (dyingCounter > i * 4 && dyingCounter <= i * 5) {
                changeAlpha(g2, 0f);
            }
            if (dyingCounter > i * 5 && dyingCounter <= i * 6) {
                changeAlpha(g2, 1f);
            }
            if (dyingCounter > i * 6 && dyingCounter <= i * 7) {
                changeAlpha(g2, 0f);
            }
            if (dyingCounter > i * 7 && dyingCounter <= i * 8) {
                changeAlpha(g2, 1f);
            }
            if (dyingCounter > i * 8) {
                dying = false;
                // alive = false;
            }

    }
}


