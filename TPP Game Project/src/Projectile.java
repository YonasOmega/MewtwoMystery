
public class Projectile extends Character{
    Character user;
    public Projectile(GamePanel gp) {
        super(gp);

    }

    public void set(int worldX, int worldY, String direction, boolean alive, Character user) {

        this.worldX = worldX;
        this.worldY = worldY;
        this.direction = direction;
        this.alive = alive;
        this.user = user;
        this.life = this.maxLife;

    }

    public void update() {

        if(user == gp.player) {
            //System.out.println(direction);
            int monsterIndex = gp.cChecker.checkCharacter(this, gp.monster);
            if(monsterIndex != 999) {
                gp.player.damageMonster(monsterIndex, attack);
                alive = false;

            }
            //Prevents player from shooting through tiles
            gp.cChecker.checkTile(this);
            //System.out.println(collisionOn);
            if(collisionOn==true){
                alive = false;
                gp.playSE(1);
            }
            collisionOn = false;        //resets collision
        }
        if(user != gp.player) {
            boolean contactPlayer = gp.cChecker.checkPlayer(this);
            if(gp.player.invincible == false && contactPlayer == true){
                damagePlayer(attack);
                alive = false;
            }
            life--;
            if(life <= 0) {
                alive = false;
            }
        }
        //
        //
         //System.out.println(direction);
        switch (direction) {
            case "up": worldY -= speed; break;
            case "down": worldY += speed; break;
            case "left": worldX -= speed; break;
            case "right": worldX += speed; break;
            case "up-right": worldX += speed;
                             worldY -= speed; break;
            case "down-right": worldX += speed;
                               worldY += speed; break;
            case "up-left": worldX -= speed;
                            worldY -= speed; break;
            case "down-left": worldX -= speed;
                              worldY += speed; break;
            case "polar": //System.out.println(worldX+ ", "+ worldY);
                worldX+=VelX;
                worldY+=VelY;
                //System.out.println("ha");
                           break;
        }


        spriteCounter++;
        if(spriteCounter > 12) {
            if(spriteNum == 1) {
                spriteNum = 2;
            }
            else if(spriteNum == 2) {
                spriteNum = 1;
            }
            spriteCounter = 0;
        }
    }
}
