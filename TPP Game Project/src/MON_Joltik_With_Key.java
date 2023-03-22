import java.util.Random;

public class MON_Joltik_With_Key extends Character {
    private int updateCounter = 0;
    public MON_Joltik_With_Key(GamePanel gp) {
        super(gp);

        type = 2;
        name = "Joltik";
        speed = 8;
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
    public void checkDrop() {
        gp.obj[0][9] = new OBJ_Key(gp);
        gp.obj[0][9].worldX = worldX;
        gp.obj[0][9].worldY = worldY;
    }
    public void getImage() {
        up1 = setup("TPP Game Project/res/monster/joltik_up_1",gp.tileSize,gp.tileSize);
        up2 = setup("TPP Game Project/res/monster/joltik_up_2",gp.tileSize,gp.tileSize);
        down1 = setup("TPP Game Project/res/monster/joltik_down_1",gp.tileSize,gp.tileSize);
        down2 = setup("TPP Game Project/res/monster/joltik_down_2",gp.tileSize,gp.tileSize);
        left1 = setup("TPP Game Project/res/monster/joltik_left_1",gp.tileSize,gp.tileSize);
        left2 = setup("TPP Game Project/res/monster/joltik_left_2",gp.tileSize,gp.tileSize);
        right1 = setup("TPP Game Project/res/monster/joltik_right_1",gp.tileSize,gp.tileSize);
        right2 = setup("TPP Game Project/res/monster/joltik_right_2",gp.tileSize,gp.tileSize);
    }
    public void update() {
        super.update();
        int xDistance = Math.abs(worldX - gp.player.worldX);
        int yDistance = Math.abs(worldY - gp.player.worldY);
        int tileDistance = (xDistance + yDistance) / gp.tileSize;

        if (onPath == false && tileDistance < 20) {
            int i = new Random().nextInt(100) + 5;
            if (i > 50) {
                onPath = true;
            }

        }
        if (onPath == true && tileDistance > 20) {
            onPath = false;
        }
    }
    public void setAction() {
        if (onPath == true) {
            if (updateCounter > 30) {
                speed = 10;
                int goalCol = (gp.player.worldX + gp.player.solidArea.x) / gp.tileSize;
                int goalRow = (gp.player.worldY + gp.player.solidArea.y) / gp.tileSize;
                searchPath(goalCol, goalRow);
            } if (updateCounter == 60) {
                updateCounter = 0;
                speed = 0;
            }
            updateCounter++;
        } else {
            actionLockCounter++;

            if (actionLockCounter == 60) { // Slows down NPC so he moves every 2 seconds (60FPS)
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
        if (gp.player.direction.equals("up-right") || gp.player.direction.equals("down-right")) {
            direction = "right";
        } else if (gp.player.direction.equals("up-left") || gp.player.direction.equals("down-left")) {
            direction = "left";
        } else {
            direction = gp.player.direction;
        }

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

            }  if (enTopY < nextY && enLeftX >= nextX && enRightX < nextX + gp.tileSize) {
                direction = "down";
            }  if (enTopY >= nextY && enBottomY < nextY + gp.tileSize){
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

            //if reaches goal stops search
//            int nextCol = gp.pFinder.pathList.get(0).col;
//            int nextRow = gp.pFinder.pathList.get(0).row;
//            if (nextCol == goalCol && nextRow == goalRow){
//                System.out.println("LEZ FUCKING GOOOOO");
//                onPath = false;
//            }
        }

    }


}
