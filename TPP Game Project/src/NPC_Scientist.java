import java.util.Random;

public class NPC_Scientist extends Character{

    public NPC_Scientist(GamePanel gp) {
        super(gp);

        direction = "down";
        speed = 1;

        getImage();
        setDialogue();
        solidArea.x = 8;
        solidArea.y = 8;
        solidArea.width = 32;
        solidArea.height = 32;
        solidAreaDefaultX = solidArea.x;
        solidAreaDefaultY = solidArea.y;
    }
    public void getImage(){
        up1 = setup("TPP Game Project/res/npc/scientist_up_1",gp.tileSize,gp.tileSize);
        up2 = setup("TPP Game Project/res/npc/scientist_up_2",gp.tileSize,gp.tileSize);
        down1 = setup("TPP Game Project/res/npc/scientist_down_1",gp.tileSize,gp.tileSize);
        down2 = setup("TPP Game Project/res/npc/scientist_down_2",gp.tileSize,gp.tileSize);
        right1 = setup("TPP Game Project/res/npc/scientist_right_1",gp.tileSize,gp.tileSize);
        right2 = setup("TPP Game Project/res/npc/scientist_right_2",gp.tileSize,gp.tileSize);
        left1 = setup("TPP Game Project/res/npc/scientist_left_1",gp.tileSize,gp.tileSize);
        left2 = setup("TPP Game Project/res/npc/scientist_left_2",gp.tileSize,gp.tileSize);
    }
    public void setAction() {

        if(onPath == true) {
//            int goalCol = 39;
//            int goalRow = 7;
            int goalCol = (gp.player.worldX + gp.player.solidArea.x)/gp.tileSize;
            int goalRow = (gp.player.worldY + gp.player.solidArea.y)/gp.tileSize;

            searchPath(goalCol, goalRow);

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
    public void setDialogue() {
        dialogues[0] = "Bhov: please dont kill me!";
        dialogues[1] = "Bhov: bhov did not do any work...:(";
        dialogues[2] = "Bhov: bhov did do work! :]";
        dialogues[3] = "Bhov: give us our money now!";

    }
    public void speak() {
        super.speak();
        onPath = true;
    }
}
