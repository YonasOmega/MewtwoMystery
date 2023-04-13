public class CollisionChecker {
    GamePanel gp;

    public CollisionChecker(GamePanel gp) {
        this.gp = gp;
    }

    public void checkTile(Character chara) {
        int charLeftWorldX = chara.worldX + chara.solidArea.x;//finds player hitbox in world
        int charRightWorldX = chara.worldX + chara.solidArea.x + chara.solidArea.width;
        int charTopWorldY = chara.worldY + chara.solidArea.y;
        int charBottomWorldY = chara.worldY + chara.solidArea.y + chara.solidArea.height;

        int charLeftCol = charLeftWorldX / gp.tileSize;
        int charRightCol = charRightWorldX / gp.tileSize;
        int charTopRow = charTopWorldY / gp.tileSize;
        int charBottomRow = charBottomWorldY / gp.tileSize;

        int tileNum1, tileNum2, tileNum3, tileNum4;

        switch (chara.direction) {
            case "up":
                charTopRow = (charTopWorldY - chara.speed) / gp.tileSize;
                tileNum1 = gp.tileM.mapTileNum[gp.currentMap][charLeftCol][charTopRow];
                tileNum2 = gp.tileM.mapTileNum[gp.currentMap][charRightCol][charTopRow];
                if (gp.tileM.Tiles[tileNum1].collision == true || gp.tileM.Tiles[tileNum2].collision == true) {
                    chara.collisionOn = true;
                }
                break;
            case "down":
                charBottomRow = (charBottomWorldY + chara.speed) / gp.tileSize;
                tileNum1 = gp.tileM.mapTileNum[gp.currentMap][charLeftCol][charBottomRow];
                tileNum2 = gp.tileM.mapTileNum[gp.currentMap][charRightCol][charBottomRow];
                if (gp.tileM.Tiles[tileNum1].collision == true || gp.tileM.Tiles[tileNum2].collision == true) {
                    chara.collisionOn = true;
                }
                break;
            case "left":
                charLeftCol = (charLeftWorldX - chara.speed) / gp.tileSize;
                tileNum1 = gp.tileM.mapTileNum[gp.currentMap][charLeftCol][charTopRow];
                tileNum2 = gp.tileM.mapTileNum[gp.currentMap][charLeftCol][charBottomRow];
                if (gp.tileM.Tiles[tileNum1].collision == true || gp.tileM.Tiles[tileNum2].collision == true) {
                    chara.collisionOn = true;
                }
                break;

            case "right":
                charRightCol = (charRightWorldX + chara.speed) / gp.tileSize;
                tileNum1 = gp.tileM.mapTileNum[gp.currentMap][charRightCol][charTopRow];
                tileNum2 = gp.tileM.mapTileNum[gp.currentMap][charRightCol][charBottomRow];
                if (gp.tileM.Tiles[tileNum1].collision == true || gp.tileM.Tiles[tileNum2].collision == true) {
                    chara.collisionOn = true;
                }
                break;
            case "up-right":
                charRightCol = (charRightWorldX + chara.speed) / gp.tileSize;
                charTopRow = (charTopWorldY - chara.speed) / gp.tileSize; // Update charTopRow based on diagonal direction
                tileNum1 = gp.tileM.mapTileNum[gp.currentMap][charRightCol][charTopRow];
                if (gp.tileM.Tiles[tileNum1].collision == true) {
                    chara.collisionOn = true;
                }
                break;

            case "up-left":
                charLeftCol = (charLeftWorldX - chara.speed) / gp.tileSize;
                charTopRow = (charTopWorldY - chara.speed) / gp.tileSize; // Update charTopRow based on diagonal direction
                tileNum1 = gp.tileM.mapTileNum[gp.currentMap][charLeftCol][charTopRow];
                if (gp.tileM.Tiles[tileNum1].collision == true) {
                    chara.collisionOn = true;
                }
                break;

            case "down-right":
                charRightCol = (charRightWorldX + chara.speed) / gp.tileSize;
                charBottomRow = (charBottomWorldY + chara.speed) / gp.tileSize; // Update charBottomRow based on diagonal direction
                tileNum1 = gp.tileM.mapTileNum[gp.currentMap][charRightCol][charBottomRow];
                if (gp.tileM.Tiles[tileNum1].collision == true) {
                    chara.collisionOn = true;
                }
                break;

            case "down-left":
                charLeftCol = (charLeftWorldX - chara.speed) / gp.tileSize;
                charBottomRow = (charBottomWorldY + chara.speed) / gp.tileSize; // Update charBottomRow based on diagonal direction
                tileNum1 = gp.tileM.mapTileNum[gp.currentMap][charLeftCol][charBottomRow];
                if (gp.tileM.Tiles[tileNum1].collision == true) {
                    chara.collisionOn = true;
                }
                break;
            case "polar":
                charTopRow = (charTopWorldY ) / gp.tileSize;
                charBottomRow = (charBottomWorldY) / gp.tileSize;
                charRightCol = (charRightWorldX) / gp.tileSize;
                charLeftCol = (charLeftWorldX) / gp.tileSize;
                tileNum1 = gp.tileM.mapTileNum[gp.currentMap][charLeftCol][charBottomRow];
                tileNum2 = gp.tileM.mapTileNum[gp.currentMap][charRightCol][charBottomRow];
                tileNum3 = gp.tileM.mapTileNum[gp.currentMap][charLeftCol][charTopRow];
                tileNum4 = gp.tileM.mapTileNum[gp.currentMap][charRightCol][charTopRow];
                if (gp.tileM.Tiles[tileNum1].collision == true || gp.tileM.Tiles[tileNum2].collision == true
                        || gp.tileM.Tiles[tileNum3].collision == true || gp.tileM.Tiles[tileNum4].collision == true) {
                    chara.collisionOn = true;

                }
        }
    }

    public int checkObject(Character chara, boolean player){

        int index = 999;

        for (int i = 0; i < gp.obj.length; i++) {
            if (gp.obj[gp.currentMap][i]!= null){
                //get character's solid area position
                    chara.solidArea.x = chara.worldX+ chara.solidArea.x;
                    chara.solidArea.y = chara.worldY+ chara.solidArea.y;

                //get the object's solid area position

                gp.obj[gp.currentMap][i].solidArea.x = gp.obj[gp.currentMap][i].worldX +gp.obj[gp.currentMap][i].solidArea.x;
                gp.obj[gp.currentMap][i].solidArea.y = gp.obj[gp.currentMap][i].worldY +gp.obj[gp.currentMap][i].solidArea.y;

                switch (chara.direction) {
                    case"up":
                        chara.solidArea.y -=chara.speed;
                        break;
                    case"down":
                        chara.solidArea.y +=chara.speed;
                        break;
                    case "left":
                        chara.solidArea.x -=chara.speed;
                        break;
                    case "right":
                        chara.solidArea.x +=chara.speed;
                        break;
                    case"up-left":
                        chara.solidArea.y -=chara.speed;
                        chara.solidArea.x -=chara.speed;
                        break;
                    case"up-right":
                        chara.solidArea.y -=chara.speed;
                        chara.solidArea.x +=chara.speed;
                        break;
                    case"down-left":
                        chara.solidArea.y +=chara.speed;
                        chara.solidArea.x -=chara.speed;
                        break;
                    case"down-right":
                        chara.solidArea.y +=chara.speed;
                        chara.solidArea.x +=chara.speed;
                        break;
                }
                if (chara.solidArea.intersects(gp.obj[gp.currentMap][i].solidArea)){
                    if (gp.obj[gp.currentMap][i].collision ==true){
                        chara.collisionOn = true;
                    }
                    if (player == true){
                        index = i;
                    }
                }

                chara.solidArea.x = chara.solidAreaDefaultX;
                chara.solidArea.y = chara.solidAreaDefaultY;
                gp.obj[gp.currentMap][i].solidArea.x = gp.obj[gp.currentMap][i].solidAreaDefaultX;
                gp.obj[gp.currentMap][i].solidArea.y = gp.obj[gp.currentMap][i].solidAreaDefaultY;

            }
        }

        return index;
    }
    //NPC OR MONSTER
    public int checkCharacter(Character chara, Character[][] target) {
        int index = 999;

        for (int i = 0; i < target[1].length; i++) {
            if (target[gp.currentMap][i]!= null){
                //get character's solid area position
                chara.solidArea.x = chara.worldX+ chara.solidArea.x;
                chara.solidArea.y = chara.worldY+ chara.solidArea.y;

                //get the object's solid area position

                target[gp.currentMap][i].solidArea.x = target[gp.currentMap][i].worldX +target[gp.currentMap][i].solidArea.x;
                target[gp.currentMap][i].solidArea.y = target[gp.currentMap][i].worldY +target[gp.currentMap][i].solidArea.y;


                switch (chara.direction) {
                    case"up":
                        chara.solidArea.y -=chara.speed;
                        break;
                    case"down":
                        chara.solidArea.y +=chara.speed;
                        break;
                    case "left":
                        chara.solidArea.x -=chara.speed;
                        break;
                    case "right":
                        chara.solidArea.x +=chara.speed;
                        break;
                    case"up-left":
                        chara.solidArea.y -=chara.speed;
                        chara.solidArea.x -=chara.speed;
                        break;
                    case"up-right":
                        chara.solidArea.y -=chara.speed;
                        chara.solidArea.x +=chara.speed;
                        break;
                    case"down-left":
                        chara.solidArea.y +=chara.speed;
                        chara.solidArea.x -=chara.speed;
                        break;
                    case"down-right":
                        chara.solidArea.y +=chara.speed;
                        chara.solidArea.x +=chara.speed;
                        break;
                }
                if (chara.solidArea.intersects(target[gp.currentMap][i].solidArea)) {
                    if (target[gp.currentMap][i] != chara) {
                        chara.collisionOn = true;
                        index = i;
                    }
                }

                chara.solidArea.x = chara.solidAreaDefaultX;
                chara.solidArea.y = chara.solidAreaDefaultY;
                target[gp.currentMap][i].solidArea.x = target[gp.currentMap][i].solidAreaDefaultX;
                target[gp.currentMap][i].solidArea.y = target[gp.currentMap][i].solidAreaDefaultY;

            }
        }

        return index;
    }
    public boolean checkPlayer(Character chara) {

        boolean contactPlayer = false;

        //get character's solid area position
        chara.solidArea.x = chara.worldX+ chara.solidArea.x;
        chara.solidArea.y = chara.worldY+ chara.solidArea.y;

        //get the object's solid area position

        gp.player.solidArea.x = gp.player.worldX + gp.player.solidArea.x;
        gp.player.solidArea.y = gp.player.worldY + gp.player.solidArea.y;

        switch (chara.direction) {
            case"up":
                chara.solidArea.y -=chara.speed;
                break;
            case"down":
                chara.solidArea.y +=chara.speed;
                break;
            case "left":
                chara.solidArea.x -=chara.speed;
                break;
            case "right":
                chara.solidArea.x +=chara.speed;
                    break;
            case"up-left":
                chara.solidArea.y -=chara.speed;
                chara.solidArea.x -=chara.speed;
                break;
            case"up-right":
                chara.solidArea.y -=chara.speed;
                chara.solidArea.x +=chara.speed;
                break;
            case"down-left":
                chara.solidArea.y +=chara.speed;
                chara.solidArea.x -=chara.speed;
                break;
            case"down-right":
                chara.solidArea.y +=chara.speed;
                chara.solidArea.x +=chara.speed;
                break;
        }

        if (chara.solidArea.intersects(gp.player.solidArea)){//checks if rectangle is intersecting another
            chara.collisionOn = true;
            contactPlayer = true;
        }

        chara.solidArea.x = chara.solidAreaDefaultX;
        chara.solidArea.y = chara.solidAreaDefaultY;
        gp.player.solidArea.x = gp.player.solidAreaDefaultX;
        gp.player.solidArea.y = gp.player.solidAreaDefaultY;

        return contactPlayer;
    }
}
