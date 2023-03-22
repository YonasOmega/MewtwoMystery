import java.awt.*;
import java.awt.image.BufferedImage;
import java.text.DecimalFormat;

public class UI {
    GamePanel gp;
    Graphics2D g2;
    public Font maruMonica;
    Font arial_40, arial_80B;
    BufferedImage heart_full, heart_half, heart_blank;
    BufferedImage keyImage;
    public boolean messageOn = false;
    public String message = "";
    int messageCounter = 0;
    public boolean gameFinished = false;
    public int commandNum=0;
    public String currentDialogue = "";

    double playTime;
    int min = 0;
    DecimalFormat dFormat = new DecimalFormat("#0.00");
    public UI(GamePanel gp) {
        this.gp = gp;
        arial_40 = new Font("Arial", Font.PLAIN, 40);
        arial_80B = new Font("Arial", Font.BOLD, 80);
        OBJ_Key key = new OBJ_Key(gp);
        keyImage = key.image;

        //CREATE 2D OBJ
        Character heart = new OBJ_Heart(gp);
        heart_full = heart.image;
        heart_half = heart.image2;
        heart_blank=heart.image3;

    }
    public void showMessage(String text) {
        message = text;
        messageOn = true;
    }
    public void draw(Graphics2D g2) {
        this.g2 = g2;
        g2.setFont(arial_40);
        g2.setColor(Color.white);

        // PLAY STATE
        if (gp.gameState == gp.playState) {
            drawPlayerLife();
        }
        // PAUSE STATE
        if (gp.gameState == gp.pauseState) {
            drawPauseScreen();
            drawPlayerLife();
        }
        // DIAOLOGUE STATE
        if (gp.gameState == gp.dialogueState) {
            drawDialogueScreen();
        }
        //CHARACTER STATE
        if (gp.gameState==gp.characterState){
            drawCharacterScreen();
        }
        // GAME OVER STATE
        if (gp.gameState == gp.gameOverState) {
            drawGameOverScreen();
        }

        if (gameFinished) {
            g2.setFont(arial_40);
            g2.setColor(Color.white);

            String text;
            int textLength;
            int x;
            int y;

            text = "You found the treasure!";
            textLength = (int) g2.getFontMetrics().getStringBounds(text, g2).getWidth();
            x = gp.screenWidth/2 - textLength/2;
            y = gp.screenHeight/2 - (gp.tileSize*3);
            g2.drawString(text, x, y);

            text = "Your time is " + min + ":" + (int)playTime + "!";
            textLength = (int) g2.getFontMetrics().getStringBounds(text, g2).getWidth();
            x = gp.screenWidth/2 - textLength/2;
            y = gp.screenHeight/2 + (gp.tileSize*4);
            g2.drawString(text, x, y);

            g2.setFont(arial_80B);
            g2.setColor(Color.yellow);
            text = "Congratulations!";
            textLength = (int) g2.getFontMetrics().getStringBounds(text, g2).getWidth();
            x = gp.screenWidth/2 - textLength/2;
            y = gp.screenHeight/2 + (gp.tileSize*3);
            g2.drawString(text, x, y);

            gp.gameThread = null;


        } else {
//            g2.setFont(arial_40);
//            g2.setColor(Color.white);
//            g2.drawImage(keyImage, gp.tileSize / 2, gp.tileSize / 2, gp.tileSize, gp.tileSize, null);
//            g2.drawString("x " + gp.player.hasKey, 74, 65);
//
//
//
//
//            // TIME
            playTime +=(double)1/60; // 1/60 because called 60 times per second (60FPS).
            if (playTime > 60.0) {
                min++;
                playTime -= 60;
            }
//            g2.drawString("Time:"+ dFormat.format(playTime), gp.tileSize*11, 65);



            // MESSAGE
            if (messageOn) {
                g2.setFont(g2.getFont().deriveFont(30F));
                g2.drawString(message, gp.tileSize / 2, gp.tileSize * 5);

                messageCounter++;
                if (messageCounter > 120) { //60FPS so 2 SECONDS
                    messageCounter = 0;
                    messageOn = false;
                }
            }
            //TITLE STATE
            if (gp.gameState == gp.titleState){
                drawTitleScreen();
            }
        }
    }
    public void drawPlayerLife(){
        int x = gp.tileSize/2;
        int y = gp.tileSize/2;
        int i=0;
        //DRAW BLANK HEARTS
        while(i < gp.player.maxLife/2){//divide by two so when player is loses a full heart they get a blank heart
            g2.drawImage(heart_blank, x, y, null);
            i++;
            x += gp.tileSize;
        }
        //RESET
        x = gp.tileSize/2;
        y = gp.tileSize/2;
        i=0;

        //DRAW CURRENT LIFE
        while(i< gp.player.life){
            g2.drawImage(heart_half, x, y, null);
            i++;
            if (i<gp.player.life){
                g2.drawImage(heart_full,x,y,null);

                }
            i++;
            x += gp.tileSize;
            }
        }

    public void drawTitleScreen(){

        g2.setColor(new Color(0, 0, 0));
        g2.fillRect(0, 0 , gp.screenWidth, gp.screenHeight);
        //TITLE NAME
        g2.setFont(g2.getFont().deriveFont(Font.BOLD,69F));
        String text = "MewTwo Escape";
        int x = getXforCenteredText(text);
        int y = gp.tileSize*3;

        //SHADOW        timer I think is still going
        g2.setColor(Color.black);
        g2.drawString(text, x+5, y+5);
        //TEXT COLOR
        g2.setColor(Color.WHITE);
        g2.drawString(text, x, y);

        //BLUE BOY IMAGE
        x = gp.screenWidth/2-(gp.tileSize*2)/2;
        y +=gp.tileSize*2;
        g2.drawImage(gp.player.down1, x, y, gp.tileSize*2, gp.tileSize*4, null);

        //MENU
        g2.setFont(g2.getFont().deriveFont(Font.BOLD,48F));

        text = "NEW GAME";
        x = getXforCenteredText(text);
        y+=gp.tileSize*4;
        g2.drawString(text,x,y);
        if (commandNum == 0){
            g2.drawString(">",x-gp.tileSize,y);
        }
        text = "LOAD GAME";
        x = getXforCenteredText(text);
        y+=gp.tileSize;
        g2.drawString(text,x,y);
        if (commandNum == 1){
            g2.drawString(">",x-gp.tileSize,y);
        }

        text = "QUIT";
        x = getXforCenteredText(text);
        y+=gp.tileSize;
        g2.drawString(text,x,y);
        if (commandNum == 2){
            g2.drawString(">",x-gp.tileSize,y);
        }
    }
    public void drawPauseScreen() {
        g2.setFont(g2.getFont().deriveFont(Font.PLAIN, 80F));
        String text = "PAUSED"+"\n "+gp.player.worldX/gp.tileSize+", "+gp.player.worldY/gp.tileSize;
        int length = (int)g2.getFontMetrics().getStringBounds(text, g2).getWidth();
        int x = getXforCenteredText(text);
        int y = gp.screenHeight/2;

        g2.drawString(text, x, y);
    }
    public int getXforCenteredText(String text) {
        int length = (int)g2.getFontMetrics().getStringBounds(text, g2).getWidth();
        int x = gp.screenWidth/2 - length/2;
        return x;
    }
    public void drawDialogueScreen() {
        // WINDOW
        int x = gp.tileSize*2;
        int y = gp.tileSize/2;
        int width = gp.screenWidth - (gp.tileSize*4);
        int height = gp.tileSize*5-4;
        drawSubWindow(x, y, width, height);

        g2.setFont(g2.getFont().deriveFont(Font.PLAIN, 32F));
        x += gp.tileSize;
        y += gp.tileSize;

        for(String line : currentDialogue.split("\n")) {
            g2.drawString(line, x, y);
            y += 40;
        }
    }
    public void drawGameOverScreen() {

        g2.setColor(new Color(0,0,0,150));
        g2.fillRect(0, 0, gp.screenWidth, gp.screenHeight);

        int x;
        int y;
        String text;
        g2.setFont(g2.getFont().deriveFont(Font.BOLD, 110f));

        text = "Game Over";
        // shadow
        g2.setColor(Color.black);
        x = getXforCenteredText(text);
        y = gp.tileSize * 4;
        g2.drawString(text, x, y);
        // main
        g2.setColor(Color.white);
        g2.drawString(text, x-4, y-4);

        // Retry
        g2.setFont(g2.getFont().deriveFont(50f));
        text = "Retry";
        x = getXforCenteredText(text);
        y += gp.tileSize * 4;
        g2.drawString(text, x, y);
        if (commandNum == 0) {
            g2.drawString(">", x-40, y);
        }

        // Back to the title screen
        text = "Quit";
        x = getXforCenteredText(text);
        y += 55;
        g2.drawString(text, x, y);
        if (commandNum == 1) {
            g2.drawString(">", x-40, y);
        }
    }
    public void drawSubWindow(int x, int y, int width, int height) {
        Color c = new Color(0, 0, 0, 210);
        g2.setColor(c);
        g2.fillRoundRect(x, y, width, height, 35, 35);

        c = new Color(255, 255, 255);
        g2.setColor(c);
        g2.setStroke(new BasicStroke(5));
        g2.drawRoundRect(x+5, y+5, width-10, height-10, 25, 25);
    }
    public void drawCharacterScreen(){
        //create a frame
        final int frameX = gp.tileSize*2;
        final int frameY = gp.tileSize;
        final int frameWidth = gp.tileSize*5;
        final int frameHeight = gp.tileSize*10;
        drawSubWindow(frameX, frameY, frameWidth, frameHeight);

        //TEXT
        g2.setColor(Color.white);
        g2.setFont(g2.getFont().deriveFont(32F));

        int textX = frameX +20;
        int textY = frameY + gp.tileSize;
        final int lineHeight = 35;

        //NAMES
        g2.drawString("Level", textX, textY);
        textY+= lineHeight;
        g2.drawString("Life",textX, textY);
        textY += lineHeight;
        g2.drawString("Strength", textX, textY);
        textY+= lineHeight;
        g2.drawString("Dexterity", textX, textY);
        textY+= lineHeight;
        g2.drawString("Attack", textX, textY);
        textY+= lineHeight;
        g2.drawString("Defense", textX, textY);
        textY+= lineHeight;
        g2.drawString("Exp", textX, textY);
        textY+= lineHeight;
        g2.drawString("Next Level", textX, textY);
        textY+= lineHeight;
        g2.drawString("Coin", textX, textY);
        textY+= lineHeight + 15;
        g2.drawString("Weapon", textX, textY);
        textY+= lineHeight + 15;
        g2.drawString("Shield", textX, textY);
        textY+= lineHeight;

        //VALUES
        int tailX = (frameX + frameWidth)-30;
        //reset textY
        textY = frameY +gp.tileSize;
        String value;

        value = String.valueOf(gp.player.level);
        textX = getXforAlignTopRightText(value, tailX);
        g2.drawString(value, textX, textY);
        textY+= lineHeight;
        value = String.valueOf(gp.player.life+"/"+gp.player.maxLife);
        textX = getXforAlignTopRightText(value, tailX);
        g2.drawString(value, textX, textY);
        textY+= lineHeight;


        value = String.valueOf(gp.player.strength);
        textX = getXforAlignTopRightText(value, tailX);
        g2.drawString(value, textX, textY);
        textY+= lineHeight;


        value = String.valueOf(gp.player.dexterity);
        textX = getXforAlignTopRightText(value, tailX);
        g2.drawString(value, textX, textY);
        textY+= lineHeight;


        value = String.valueOf(gp.player.attack);
        textX = getXforAlignTopRightText(value, tailX);
        g2.drawString(value, textX, textY);
        textY+= lineHeight;


        value = String.valueOf(gp.player.defense);
        textX = getXforAlignTopRightText(value, tailX);
        g2.drawString(value, textX, textY);
        textY+= lineHeight;


        value = String.valueOf(gp.player.exp);
        textX = getXforAlignTopRightText(value, tailX);
        g2.drawString(value, textX, textY);
        textY+= lineHeight;


        value = String.valueOf(gp.player.nextLevelExp);
        textX = getXforAlignTopRightText(value, tailX);
        g2.drawString(value, textX, textY);
        textY+= lineHeight;


        value = String.valueOf(gp.player.coin);
        textX = getXforAlignTopRightText(value, tailX);
        g2.drawString(value, textX, textY);
        textY += gp.tileSize;


        g2.drawImage(gp.player.currentWeapon.down1, tailX - gp.tileSize, textY - 32, null);
        textY += gp.tileSize;
        g2.drawImage(gp.player.currentShield.down1, tailX-gp.tileSize, textY - 32, null);







    }
    public int getXforAlignTopRightText(String text, int tailX){
        int length = (int)g2.getFontMetrics().getStringBounds(text, g2).getWidth();
        int x = tailX-length;
        return x;
    }
}
