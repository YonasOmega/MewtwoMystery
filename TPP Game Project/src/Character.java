import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Random;

/**
 *  Template class for Player, Enemy, NPC
 */

public class Character {

    GamePanel gp;
    public BufferedImage up1, up2, down1, down2, left1,left2, right1, right2;   //Instantiates Sprite Images.
    public BufferedImage attackUp1, attackUp2, attackDown1, attackDown2, attackLeft1, attackLeft2,
                        attackRight1,attackRight2;
    public Rectangle solidArea = new Rectangle(0, 0, 48, 48); //was 0,0,48,48 changed for collision issues
    public Rectangle attackArea = new Rectangle(0, 0, 0, 0);
    public int solidAreaDefaultX, solidAreaDefaultY;
    public boolean collision = false;
    public BufferedImage image, image2, image3;
    public boolean inRage = false;
    public boolean BossDead = false;
    String dialogues[] = new String[20];
    public int level;
    public int strength;
    public int dexterity;
    public int attack;
    public int defense;
    public int exp;
    public int nextLevelExp;
    public int coin;
    public Character currentWeapon;
    public Character currentShield;
    double mx;//Mouse position
    double my;
   double VelX;
    double VelY;

    // STATE
    public int worldX, worldY;
    public String direction = "down"; //Holds direction String.
    public int spriteNum = 1;       //Used to set values to individual sprites.
    int dialogueIndex = 0;
    public boolean collisionOn = false;
    public boolean invincible = false;
    boolean attacking = false;
   public boolean alive = true;
    public boolean dying = false;
    public boolean reviving;

    boolean hpBarOn = false;
    public boolean onPath = false;


    //COUNTER
    public int invincibleCounter = 0;
    public int spriteCounter = 0;   //Used as timer to determine when to switch sprites.
    public int slowCounter = 0;
    public int shotAvailableCounter = 0;
    public int actionLockCounter = 0;
    int dyingCounter = 0;
    int hpBarCounter = 0;
    int drawRange =5;


    //CHARACTER ATTRIBUTES
    public int speed;
    public int type;
    public String name;
    public int maxLife;
    public int life;
    public int maxMana;
    public int mana;
    public Projectile projectile;
    public int attackValue;
    public int defenseValue;
    public int useCost;


    public Character(GamePanel gp) {
        this.gp = gp;
    }
    public int getCenterX() {
        int centerX = worldX + left1.getWidth()/2;
        return centerX;
    }
    public int getCenterY() {
        int centerY = worldY + up1.getHeight()/2;
        return centerY;
    }
    public int getXdistance(Character target) {
        int xDistance = Math.abs(getCenterX() - target.getCenterX());
        return xDistance;
    }
    public int getYdistance(Character target) {
        int yDistance = Math.abs(getCenterY() - target.getCenterY());
        return yDistance;
    }
    public int getTileDistance(Character target) {
        int tileDistance = (getXdistance(target) + getYdistance(target))/gp.tileSize;
        return tileDistance;
    }
    public int getGoalCol(Character target) {
        int goalCol = (target.worldX + target.solidArea.x) / gp.tileSize;
        return goalCol;
    }
    public int getGoalRow(Character target) {
        int goalRow = (target.worldY + target.solidArea.y) / gp.tileSize;
        return goalRow;
    }
    public void setAction() {}
    public void damageReaction() {};
    public void speak() {
        if (dialogues[dialogueIndex] == null) {
            dialogueIndex = 0;
        }
        gp.ui.currentDialogue = dialogues[dialogueIndex];
        dialogueIndex++;

        switch(gp.player.direction) {
            case "up":
                direction = "down";
                break;
            case "down":
                direction = "up";
                //System.out.println("i swear to god");
                break;
            case "left":
                direction = "right";
                break;
            case "right":
                direction = "left";
                break;

        }
    }
    public void checkCollision(){
        collisionOn = false;
        gp.cChecker.checkTile(this);
        gp.cChecker.checkObject(this, false);
        gp.cChecker.checkCharacter(this, gp.npc);
        gp.cChecker.checkCharacter(this, gp.monster);
        boolean contactPlayer = gp.cChecker.checkPlayer(this);

        if (this.type <=2 && contactPlayer == true) {
            damagePlayer(attack);
        }
    }
    public void update() {

        setAction();

        checkCollision();

        //if collision is false player can move
        if (collisionOn == false) {

            switch (direction) {
                case "up":
                    worldY -= speed;
                    break;
                case "down":
                    worldY += speed;
                    break;
                case "left":
                    worldX -= speed;
                    break;
                case "right":
                    worldX += speed;
                    break;
            }
        }


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
        if (invincible == true) {

            invincibleCounter++;
            if (invincibleCounter > 40) {
                invincible = false;
                invincibleCounter = 0;
            }
        }
        if (shotAvailableCounter < 45) {
            shotAvailableCounter++;
        }
    }
    public void checkShootOrNot(int rate, int shotInterval) {
        int i = new Random().nextInt(rate);
        if (i == 0 && projectile.alive == false && shotAvailableCounter == shotInterval){
            OBJ_Player_Projectile proj = new OBJ_Player_Projectile(gp,this);
            proj.set(this.worldX, this.worldY, gp.player.worldX, gp.player.worldY, "polar", true, this);
            gp.projectileList.add(proj);
            shotAvailableCounter=0;
        }
    }
    public void checkStartChasingOrNot(Character target, int distance, int rate) {
        if (getTileDistance(target) < distance) {
            int i = new Random().nextInt(rate);
            if (i == 0) {
                onPath = true;
            }
        }
    }
    public void checkStopChasingOrNot(Character target, int distance, int rate) {
        if (getTileDistance(target) > distance) {
            int i = new Random().nextInt(rate);
            if (i == 0) {
                onPath = false;
            }
        }
    }
    public void getRandomDirection() {
        actionLockCounter++;

        if (actionLockCounter == 120) {
            Random random = new Random();
            int i = random.nextInt(100)+1;

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
    public void attacking(){
        spriteCounter++;

        if (spriteCounter <=5){//for speed of animation
            spriteNum =1;

        }if (spriteCounter>5 &&spriteCounter<=25){
            spriteNum=2;

            //SAVE CURRENT X, Y, AND SOLID AREA
            int currentWorldX = worldX;
            int currentWorldY = worldY;
            int solidAreaWidth = solidArea.width;
            int solidAreaHeight = solidArea.height;
            //ADJUST PLAYER'S X,Y, AND SOLID AREA
            switch(direction){
                case"up":worldY-=attackArea.height; break;
                case"down":worldY+=attackArea.height;break;
                case"left":worldX-=attackArea.width;break;
                case"right":worldX+=attackArea.width; break;

            }
            //attack area becomes solid area
            solidArea.width = attackArea.width;
            solidArea.height = attackArea.height;

            if (type <= 2) {
                if (gp.cChecker.checkPlayer(this) == true) {
                    damagePlayer(attack);
                }
            } else { // Player
                //check monster collision with the updated worldx, worldy and solid area
                int monsterIndex = gp.cChecker.checkCharacter(this,gp.monster);
                gp.player.damageMonster(monsterIndex, attack);
            }

            worldX=currentWorldX;
            worldY=currentWorldY;
            solidArea.width = solidAreaWidth;
            solidArea.height = solidAreaHeight;


        }
        if (spriteCounter>25){
            spriteNum=1;
            spriteCounter =0;
            attacking=false;
        }
    }
    public void moveTowardPlayer(int interval) {
        actionLockCounter++;

        if (actionLockCounter > interval) {
            if (getXdistance(gp.player) > getYdistance(gp.player)) {
                if (gp.player.getCenterX() < getCenterX()) {
                    direction = "left";
                }
                else {
                    direction = "right";
                }
            }
            else if (getXdistance(gp.player) < getYdistance(gp.player)) {
                if (gp.player.getCenterY() < getCenterY()) {
                    direction = "up";
                }
                else {
                    direction = "down";
                }
            }
            actionLockCounter = 0;
        }
    }
    public void damagePlayer(int attack){
        if (gp.player.invincible == false) {
            // we can give damage
            gp.playSE(6);
            int damage = attack - gp.player.defense;
            if (damage < 0){
                damage = 0;
            }
            gp.player.life -= damage;
            gp.player.invincible = true;
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
            if (dying == true||type==3&&reviving==true) {

                dyingAnimation(g2);
            }

            g2.drawImage(image, screenX, screenY,null);
            changeAlpha(g2, 1f);
        }
    }
    public void dyingAnimation(Graphics2D g2) {

        dyingCounter++;
        int i = 5;


        if (dyingCounter <= 5) {
            changeAlpha(g2, 0f);
            if (reviving==true&&type==3) {
                gp.stopMusic();
                gp.playMusic(7);
                i=20;
                life += (double) maxLife / 10;
            }

        }
        if (dyingCounter > i && dyingCounter <= i*2) {
            changeAlpha(g2, 1f);
            if (reviving==true&&type==3) {
                life += (double) maxLife / 10;
            }

        }
        if (dyingCounter > i*2 && dyingCounter <= i*3) {
            changeAlpha(g2, 0f);
            if (reviving==true&&type==3) {
                life += (double) maxLife / 10;
            }
        }
        if (dyingCounter > i*3 && dyingCounter <= i*4) {
            changeAlpha(g2, 1f);
            if (reviving==true&&type==3) {
                life += (double) maxLife / 10;
            }
        }
        if (dyingCounter > i*4 && dyingCounter <= i*5) {
            changeAlpha(g2, 0f);
            if (reviving==true&&type==3) {
                life += (double) maxLife / 10;
            }
        }
        if (dyingCounter > i*5 && dyingCounter <= i*6) {
            changeAlpha(g2, 1f);
            if (reviving==true&&type==3) {
                life += (double) maxLife / 10;
            }
        }
        if (dyingCounter > i*6 && dyingCounter <= i*7) {
            changeAlpha(g2, 0f);
            if (reviving==true&&type==3) {
                life += (double) maxLife / 10;
            }
        }
        if (dyingCounter > i*7 && dyingCounter <= i*8) {
            changeAlpha(g2, 1f);
            if (reviving==true&&type==3) {
                life += (double) maxLife / 10;
            }
        }
        if (dyingCounter > i*8) {
            if (reviving==true&&type==3){
                dying=false;
                alive=true;
                life=maxLife;
                gp.stopMusic();
                gp.playMusic(2);
                for (int j = 0; j < 999999999; j++) {
                    for (int k = 0; k < 999999999; k++) {
                    }
                }


            }else{
                dying = false;
                alive = false;
            }

        }
    }

    public void changeAlpha(Graphics2D g2, float alphaValue) {
        g2.setComposite(AlphaComposite.getInstance(AlphaComposite.SRC_OVER, alphaValue));

    }
    public void missle() {}
    public void checkDrop() {}
    public BufferedImage setup(String imagePath, int width, int height) {
        UtilityTool uTool = new UtilityTool();
        BufferedImage image = null;

        try {
            image = ImageIO.read(new FileInputStream(imagePath + ".png"));
            image = uTool.scaleImage(image, width, height);
        }catch(IOException e) {
            e.printStackTrace();
        }
        return image;
    }
    public BufferedImage setup(String imagePath, int width, int height, int scale) {
        UtilityTool uTool = new UtilityTool();
        BufferedImage image = null;

        try {
            image = ImageIO.read(new FileInputStream(imagePath + ".png"));
            image = uTool.scaleImage(image, width * scale, height * scale);
        }catch(IOException e) {
            e.printStackTrace();
        }
        return image;
    }
    public void searchPath(int goalCol, int goalRow){
        int startCol = (worldX + solidArea.x)/gp.tileSize;
        int startRow = (worldY + solidArea.y)/gp.tileSize;
//        startCol-=1;
//       startRow-=1;


        gp.pFinder.setNodes(startCol,startRow,goalCol, goalRow);
        if (gp.pFinder.search()==true){// IF ITS FOUND A PATH


            //NEXT WORLDX & WORLDY
            int nextX = gp.pFinder.pathList.get(0).col * gp.tileSize;
            int nextY = gp.pFinder.pathList.get(0).row * gp.tileSize;

            int enLeftX = worldX-1 +solidArea.x;
            int enRightX = worldX-1 + solidArea.x + solidArea.width;
            int enTopY = worldY + solidArea.y;
            int enBottomY = worldY + solidArea.y + solidArea.height;


            if (enTopY > nextY && enLeftX >= nextX && enRightX < nextX + gp.tileSize){
                direction = "up";

            } else if (enTopY < nextY && enLeftX >= nextX && enRightX < nextX + gp.tileSize){
                direction = "down";
            } else if (enTopY >= nextY && enBottomY < nextY + gp.tileSize){
                //left or right
                if (enLeftX > nextX) {
                    direction = "left";

                }
                if (enLeftX < nextX){
                    direction = "right";
                }
            }
            else if (enTopY > nextY && enLeftX > nextX){
                //up or left
                direction = "up";
                checkCollision();

                if (collisionOn == true){
                    direction = "left";
                }
            } else if (enTopY > nextY && enLeftX < nextX) {
                //up or right
                direction = "up";
                checkCollision();

                if (collisionOn==true){
                    direction = "right";
                }


            } else if (enTopY < nextY && enLeftX > nextX) {
                //down or left
                direction= "down";
                checkCollision();
                if (collisionOn == true){

                    direction = "left";
                }
            } else if (enTopY < nextY && enLeftX < nextX){
                //down or right
                direction = "down";
                checkCollision();
                if (collisionOn == true){
                    direction = "right";
                }
            }
        }

    }

}
