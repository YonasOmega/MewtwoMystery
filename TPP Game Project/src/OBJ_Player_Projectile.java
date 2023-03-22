public class OBJ_Player_Projectile extends Projectile{
    GamePanel gp;

    public OBJ_Player_Projectile(GamePanel gp, Character user) {
        super(gp);
        this.gp=gp;


        name = "pProj";
        speed = 15;
        maxLife = 80;
        life = maxLife;
        attack = 2;
        useCost = 1;
        alive = false;
        this.user=user;
            getImage(1);

    }
    public void set(int worldX, int worldY, int mx, int my, String direction,boolean alive, Character user) {
        if (user!=gp.player){
            speed =8;
            attack=3;
        }
        this.worldX = worldX;
        this.worldY = worldY;
        this.mx=mx;
        this.my=my;
        this.direction = direction;
        this.alive = alive;
        this.user = user;
       // System.out.println(user);
        this.life = this.maxLife;

        double distX=(mx-(worldX));
        double distY=(my-(worldY));
        double dist = Math.sqrt((Math.pow(distX,2)+Math.pow(distY,2)));
        VelX = (distX/dist)*speed;
        VelY = (distY/dist)*speed;

    }
    public void getImage(int i) {

        up1 = setup("TPP Game Project/res/Projectile/mewtwo_projectile",gp.tileSize,gp.tileSize,i);
        up2 = setup("TPP Game Project/res/Projectile/mewtwo_projectile",gp.tileSize,gp.tileSize,i);
        down1 = setup("TPP Game Project/res/Projectile/mewtwo_projectile",gp.tileSize,gp.tileSize,i);
        down2 = setup("TPP Game Project/res/Projectile/mewtwo_projectile",gp.tileSize,gp.tileSize,i);
        right1 = setup("TPP Game Project/res/Projectile/mewtwo_projectile",gp.tileSize,gp.tileSize,i);
        right2 = setup("TPP Game Project/res/Projectile/mewtwo_projectile",gp.tileSize,gp.tileSize,i);
        left1 = setup("TPP Game Project/res/Projectile/mewtwo_projectile",gp.tileSize,gp.tileSize,i);
        left2 = setup("TPP Game Project/res/Projectile/mewtwo_projectile",gp.tileSize,gp.tileSize,i);
    }

}
