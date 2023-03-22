public class AssetSetter {
    GamePanel gp;

    public AssetSetter(GamePanel gp) {
        this.gp = gp;
    }

    public void setObject() {

        int mapNum= 0;
        gp.obj[mapNum][0] = new OBJ_Key(gp);
        gp.obj[mapNum][0].worldX = 170 * gp.tileSize; //object location
        gp.obj[mapNum][0].worldY = 80 * gp.tileSize;

        gp.obj[mapNum][1] = new OBJ_Key(gp);
        gp.obj[mapNum][1].worldX = 104 * gp.tileSize;
        gp.obj[mapNum][1].worldY = 173 * gp.tileSize;

        gp.obj[mapNum][2] = new OBJ_XSpeed(gp);
        gp.obj[mapNum][2].worldX = 148 * gp.tileSize;
        gp.obj[mapNum][2].worldY = 100 * gp.tileSize;

        gp.obj[mapNum][3] = new OBJ_Door(gp);
        gp.obj[mapNum][3].worldX = 125 * gp.tileSize;
        gp.obj[mapNum][3].worldY = 109 * gp.tileSize;

        gp.obj[mapNum][4] = new OBJ_Door(gp);
        gp.obj[mapNum][4].worldX = 121 * gp.tileSize;
        gp.obj[mapNum][4].worldY = 109 * gp.tileSize;
//
        gp.obj[mapNum][5] = new OBJ_Door(gp);
        gp.obj[mapNum][5].worldX = 125 * gp.tileSize;
        gp.obj[mapNum][5].worldY = 72 * gp.tileSize;

        gp.obj[mapNum][6] = new OBJ_Portal(gp);
        gp.obj[mapNum][6].worldX = 125 * gp.tileSize;
        gp.obj[mapNum][6].worldY = 19 * gp.tileSize;

        gp.obj[mapNum][8] = new OBJ_Door(gp);
        gp.obj[mapNum][8].worldX = 125 * gp.tileSize;
        gp.obj[mapNum][8].worldY = 24 * gp.tileSize;

    }
    public void setNPC() {
        int mapNum= 0;
        gp.npc[mapNum][0] = new NPC_Scientist(gp);
        gp.npc[mapNum][0].worldX = 123 * gp.tileSize;
        gp.npc[mapNum][0].worldY = 123 * gp.tileSize;
    }
    public void setMonster() {
        int mapNum= 0;
        gp.monster[mapNum][0] = new MON_Magnemite(gp);
        gp.monster[mapNum][0].worldX = gp.tileSize * 80;
        gp.monster[mapNum][0].worldY = gp.tileSize * 160;

        gp.monster[mapNum][1] = new MON_Magnemite(gp);
        gp.monster[mapNum][1].worldX = gp.tileSize * 82;
        gp.monster[mapNum][1].worldY = gp.tileSize * 168;

        gp.monster[mapNum][3] = new MON_Static_Down(gp);
        gp.monster[mapNum][3].worldX = gp.tileSize * 92;
        gp.monster[mapNum][3].worldY = gp.tileSize * 168;

        gp.monster[mapNum][4] = new MON_Static_Up(gp);
        gp.monster[mapNum][4].worldX = gp.tileSize * 94;
        gp.monster[mapNum][4].worldY = gp.tileSize * 179;

        gp.monster[mapNum][5] = new MON_Static_Down(gp);
        gp.monster[mapNum][5].worldX = gp.tileSize * 96;
        gp.monster[mapNum][5].worldY = gp.tileSize * 168;

        gp.monster[mapNum][6] = new MON_Static_Up(gp);
        gp.monster[mapNum][6].worldX = gp.tileSize * 98;
        gp.monster[mapNum][6].worldY = gp.tileSize * 179;

        gp.monster[mapNum][7] = new MON_Static_Down(gp);
        gp.monster[mapNum][7].worldX = gp.tileSize * 100;
        gp.monster[mapNum][7].worldY = gp.tileSize * 168;

        gp.monster[mapNum][8] = new MON_Static_Up(gp);
        gp.monster[mapNum][8].worldX = gp.tileSize * 102;
        gp.monster[mapNum][8].worldY = gp.tileSize * 179;

        gp.monster[mapNum][9] = new MON_Static_Down(gp);
        gp.monster[mapNum][9].worldX = gp.tileSize * 104;
        gp.monster[mapNum][9].worldY = gp.tileSize * 168;

        gp.monster[mapNum][10] = new MON_Static_Up(gp);
        gp.monster[mapNum][10].worldX = gp.tileSize * 106;
        gp.monster[mapNum][10].worldY = gp.tileSize * 179;

        gp.monster[mapNum][11] = new MON_Static_Down(gp);
        gp.monster[mapNum][11].worldX = gp.tileSize * 108;
        gp.monster[mapNum][11].worldY = gp.tileSize * 168;

        gp.monster[mapNum][12] = new MON_Static_Up(gp);
        gp.monster[mapNum][12].worldX = gp.tileSize * 110;
        gp.monster[mapNum][12].worldY = gp.tileSize * 179;

        gp.monster[mapNum][13] = new MON_Static_Down(gp);
        gp.monster[mapNum][13].worldX = gp.tileSize * 112;
        gp.monster[mapNum][13].worldY = gp.tileSize * 168;

        gp.monster[mapNum][14] = new MON_Static_Up(gp);
        gp.monster[mapNum][14].worldX = gp.tileSize * 114;
        gp.monster[mapNum][14].worldY = gp.tileSize * 179;

        gp.monster[mapNum][16] = new MON_Magnemite(gp);
        gp.monster[mapNum][16].worldX = gp.tileSize * 88;
        gp.monster[mapNum][16].worldY = gp.tileSize * 88;

        gp.monster[mapNum][17] = new MON_Magnemite(gp);
        gp.monster[mapNum][17].worldX = gp.tileSize * 70;
        gp.monster[mapNum][17].worldY = gp.tileSize * 95;

        gp.monster[mapNum][18] = new MON_Magnemite(gp);
        gp.monster[mapNum][18].worldX = gp.tileSize * 97;
        gp.monster[mapNum][18].worldY = gp.tileSize * 75;

        gp.monster[mapNum][19] = new MON_Magnemite(gp);
        gp.monster[mapNum][19].worldX = gp.tileSize * 140;
        gp.monster[mapNum][19].worldY = gp.tileSize * 102;

        gp.monster[mapNum][20] = new MON_Magnemite(gp);
        gp.monster[mapNum][20].worldX = gp.tileSize * 146;
        gp.monster[mapNum][20].worldY = gp.tileSize * 98;

        gp.monster[mapNum][21] = new MON_Magnemite(gp);
        gp.monster[mapNum][21].worldX = gp.tileSize * 92;
        gp.monster[mapNum][21].worldY = gp.tileSize * 119;

        gp.monster[mapNum][22] = new MON_Magnemite(gp);
        gp.monster[mapNum][22].worldX = gp.tileSize * 95;
        gp.monster[mapNum][22].worldY = gp.tileSize * 124;

        gp.monster[mapNum][23] = new MON_Magnemite(gp);
        gp.monster[mapNum][23].worldX = gp.tileSize * 165;
        gp.monster[mapNum][23].worldY = gp.tileSize * 78;

        gp.monster[mapNum][24] = new MON_Magnemite(gp);
        gp.monster[mapNum][24].worldX = gp.tileSize * 156;
        gp.monster[mapNum][24].worldY = gp.tileSize * 76;

        gp.monster[mapNum][25] = new MON_Magnemite(gp);
        gp.monster[mapNum][25].worldX = gp.tileSize * 171;
        gp.monster[mapNum][25].worldY = gp.tileSize * 83;

        gp.monster[mapNum][27] = new MON_Joltik(gp);
        gp.monster[mapNum][27].worldX = gp.tileSize * 99;
        gp.monster[mapNum][27].worldY = gp.tileSize * 70;

        gp.monster[mapNum][28] = new MON_Joltik(gp);
        gp.monster[mapNum][28].worldX = gp.tileSize * 94;
        gp.monster[mapNum][28].worldY = gp.tileSize * 71;

        gp.monster[mapNum][29] = new MON_Joltik_With_Key(gp);
        gp.monster[mapNum][29].worldX = gp.tileSize * 90;
        gp.monster[mapNum][29].worldY = gp.tileSize * 72;

        gp.monster[mapNum][30] = new MON_Magnemite(gp);
        gp.monster[mapNum][30].worldX = gp.tileSize * 175;
        gp.monster[mapNum][30].worldY = gp.tileSize * 84;

        gp.monster[mapNum][31] = new MON_Magnemite(gp);
        gp.monster[mapNum][31].worldX = gp.tileSize * 161;
        gp.monster[mapNum][31].worldY = gp.tileSize * 72;

        gp.monster[mapNum][32] = new MON_Magnemite(gp);
        gp.monster[mapNum][32].worldX = gp.tileSize * 159;
        gp.monster[mapNum][32].worldY = gp.tileSize * 72;

        gp.monster[mapNum][33] = new MON_Magnemite(gp);
        gp.monster[mapNum][33].worldX = gp.tileSize * 163;
        gp.monster[mapNum][33].worldY = gp.tileSize * 85;

        gp.monster[mapNum][34] = new MON_Magnemite(gp);
        gp.monster[mapNum][34].worldX = gp.tileSize * 173;
        gp.monster[mapNum][34].worldY = gp.tileSize * 92;

        gp.monster[mapNum][35] = new MON_Magnemite(gp);
        gp.monster[mapNum][35].worldX = gp.tileSize * 153;
        gp.monster[mapNum][35].worldY = gp.tileSize * 85;

    }
    public void setBoss(){
        int mapNum= 0;
        gp.monster[mapNum][2] = new MON_Boss(gp);
        gp.monster[mapNum][2].worldX = gp.tileSize * 125;
        gp.monster[mapNum][2].worldY = gp.tileSize * 28;
    }
}
