import java.awt.*;

public class EventHandler {

    GamePanel gp;
    public boolean bossbattle = false;

    EventRect eventRect[][];

    int previousEventX, getPreviousEventY;
    boolean canTouchEvent = true;



    public EventHandler(GamePanel gp) {
        this.gp = gp;
        eventRect = new EventRect[gp.maxWorldCol][gp.maxWorldRow];

        int col = 0;
        int row =0;
        while(col<gp.maxWorldCol && row<gp.maxWorldRow){
            eventRect[col][row] = new EventRect();
            eventRect[col][row].x = 0;
            eventRect[col][row].y = 0;
            eventRect[col][row].width = gp.tileSize;
            eventRect[col][row].height = gp.tileSize;
            eventRect[col][row].eventRectDefaultX = eventRect[col][row].x;
            eventRect[col][row].eventRectDefaultY = eventRect[col][row].y;

            col++;
            if(col == gp.maxWorldCol){
                col = 0;
                row++;

            }
        }

    }
    public boolean isBossbattlele(){
        if(bossbattle==true){
            return true;
        }
        return false;

    }
    public void setBossbattle(boolean bool){
        bossbattle=bool;
    }

    public void checkEvent() {

        //Check if player character is more than 1 tile away from the last event
        int xDistance = Math.abs(gp.player.worldX - previousEventX);
        int yDistance = Math.abs(gp.player.worldY - previousEventX);
        int distance = Math.max(xDistance, yDistance);
        if(distance >gp.tileSize){
            canTouchEvent=true;
        }
        if(canTouchEvent==true){
            if(hit(34,7, "left") == true) {
            //pass coordinates and direction
            damagePit(34,7,gp.dialogueState);
             }
            for(int i = 179; i <= 183; i++) {
                for(int j = 123; j <= 125; j++) {
                    if(hit(i,j,"any") == true) {
                        healingPool(gp.dialogueState);
                    }
                }
            }
//            if(hit(179,123, "any") == true) {
//                healingPool(gp.dialogueState);
//            }
//            if(hit(180,123, "any") == true) {
//                healingPool(gp.dialogueState);
//            }
//            if(hit(181,123, "any") == true) {
//                healingPool(gp.dialogueState);
//            }
//            if(hit(179,124, "any") == true) {
//                healingPool(gp.dialogueState);
//            }
//            if(hit(179,125, "any") == true) {
//                healingPool(gp.dialogueState);
//            }
//            if(hit(179,126, "any") == true) {
//                healingPool(gp.dialogueState);
//            }
        }

        if(hit(151, 70, "any") == true) {
            teleport(gp.dialogueState,97 ,70);
        }
        if(hit(98, 70, "any") == true) {
            teleport(gp.dialogueState,152,70);
        }
        if(hit(125, 94, "up") == true) {
            bossBattle(gp.dialogueState, 125,42);
        }

    }
    public boolean hit(int col, int row, String reqDirection) { // checks if the player hits the event rectangle

        boolean hit = false;
        gp.player.solidArea.x = gp.player.worldX + gp.player.solidArea.x;
        gp.player.solidArea.y = gp.player.worldY + gp.player.solidArea.y;
        eventRect[col][row].x = col * gp.tileSize + eventRect[col][row].x;
        eventRect[col][row].y = row * gp.tileSize + eventRect[col][row].y;

        if(gp.player.solidArea.intersects(eventRect[col][row]) && eventRect[col][row].eventDone == false) {
            if(gp.player.direction.contentEquals(reqDirection) || reqDirection.contentEquals("any")) {
                hit = true;

                previousEventX = gp.player.worldX;
                previousEventX = gp.player.worldY;
            }
        }

        gp.player.solidArea.x = gp.player.solidAreaDefaultX;
        gp.player.solidArea.y = gp.player.solidAreaDefaultY;
        eventRect[col][row].x = eventRect[col][row].eventRectDefaultX;
        eventRect[col][row].y = eventRect[col][row].eventRectDefaultY;

        return hit;
    }
    public void teleport(int gameState,int x,int y) {

        gp.gameState = gameState;
        //super.speak(0);
        gp.ui.currentDialogue = "Teleport!";
        gp.player.worldX = gp.tileSize * x;
        gp.player.worldY = gp.tileSize * y;
    }
    public void bossBattle(int gameState,int x,int y) {

        gp.gameState = gameState;
        //super.speak(0);
        gp.ui.currentDialogue = "RUMBLE";
        gp.player.worldX = gp.tileSize * x;
        gp.player.worldY = gp.tileSize * y;
        gp.stopMusic();
        gp.playMusic(2);
        gp.aSetter.setBoss();
       setBossbattle(true);
    }
    public void damagePit(int col, int row, int gameState) {

        gp.gameState = gameState;
        System.out.println("oof");
        gp.ui.currentDialogue = "You fell into a pit!";
        gp.player.life -= 1;
        //eventRect[col][row].eventDone = true;
        canTouchEvent =false;
    }
    public void healingPool(int gameState) {
        //System.out.println("heal");
        if(gp.keyH.enterPressed  == true) {
            gp.player.attackCanceled = true;
            gp.gameState = gameState;
            gp.ui.currentDialogue = "Health UP!";
            gp.player.life = gp.player.maxLife;
            canTouchEvent =false;

        }
    }
}
