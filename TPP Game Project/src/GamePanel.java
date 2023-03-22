import javax.swing.*;
import java.awt.*;
import java.net.MalformedURLException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;

/**
 *  Instantiates Game and runs game loop.
 */
public class GamePanel extends JPanel implements Runnable {
    //Screen settings
        //Tile sizes
    final int originalTileSize =16;             //16x16 tiles
    final int scale = 4;

    final int tileSize = originalTileSize*scale;// 48x48 tile

    //World Settings
    public final int maxWorldCol=250;
    public final int maxWorldRow=250;
    public final int maxMap =  10;
    public int  currentMap= 0;

        //Screen Resolution
    final int maxScreenCol = 16;
    final int maxScreenRow = 12;
    final int screenWidth = tileSize * maxScreenCol; //786 px
    final int screenHeight = tileSize * maxScreenRow; //576 px
    int FPS =60;
    TileManager tileM = new TileManager(this);
    MouseInput mouseIn = new MouseInput(this);
    KeyHandler keyH= new KeyHandler(this);
    Sound music = new Sound();
    Sound se = new Sound();
    public CollisionChecker cChecker = new CollisionChecker(this);

    public AssetSetter aSetter = new AssetSetter(this);
    public UI ui = new UI(this);
    public EventHandler eHandler = new EventHandler(this);
    public PathFinder pFinder = new PathFinder(this);
    Map map = new Map(this);
    Thread gameThread;                              //Creates time in game for FPS , implements runnable, calls run method


    // ENTITY AND OBJECT
    public Player player = new Player(this, keyH, mouseIn);
    public MON_Boss boss = new MON_Boss(this);
    public Character obj[][] = new Character[maxMap][11];
    public Character npc[][] = new Character[maxMap][10];
    public Character monster[][] = new Character[maxMap][500];
    public ArrayList<Character> projectileList = new ArrayList<>();
    ArrayList<Character> characterList =new ArrayList<>();

    // GAME STATEd
    public final int titleState = 0;
    public int gameState;
    public final int playState = 1;
    public final int pauseState = 2;
    public final int dialogueState = 3;
    public final int characterState = 4;
    public final int gameOverState = 6;
    public final int mapState = 10;

    /**
     * Constructor for game panel that instantiates screen size, color, input and other cool jazz.
     */
    public GamePanel( ) throws MalformedURLException {
        this.setPreferredSize(new Dimension(screenWidth, screenHeight));
        this.setBackground(Color.black);
        this.setDoubleBuffered(true);               //improved rendering performance
        this.addKeyListener(keyH);
        this.addMouseListener(mouseIn);
        this.setFocusable(true);                    //makes gamePanel "focused to receive input", so basically makes input faster ig.
    }

    public void setupGame() {
        aSetter.setObject();
        aSetter.setNPC();
        aSetter.setMonster();
        playMusic(0);
        gameState = titleState;
    }
    public void retry() {
        monster[0][2]=null;
        monster[0][26]=null;
        monster[0][100] = null;
        monster[0][101] = null;
        monster[0][102] = null;
        monster[0][103] = null;
        player.setDefaultPositions();
        player.setDefaultValues();
        player.resetoreLifeAndMan();
        aSetter.setObject();
        aSetter.setMonster();
        aSetter.setNPC();
        playMusic(0);
        eHandler.setBossbattle(false);
    }
    public void restart() {
        player.setDefaultValues();
        player.setDefaultPositions();
        player.resetoreLifeAndMan();
        aSetter.setObject();
        aSetter.setNPC();
        aSetter.setMonster();
        playMusic(0);
    }

    /**
     *  Creates and starts game.
     */
    public void startGameThread(){
        gameThread = new Thread(this);          //Creates new Game.
        gameThread.start();                         //calls run method.
    }

    /**
     *  Game loop for changing data while in real time and managing frame rate.
     */
    @Override
    public void run() {//Game loop
        //Throttles actual computer speed to 60FPS, so it can update properly.
        double drawInterval = 1000000000/FPS;                   //0.0166666666 seconds if 60 fps.
        double nextDrawTime = System.nanoTime() +drawInterval;
        //Calls update and repaint method, repaint(paintComponent) only happens 60FPS
        while(gameThread!=null){
            long currentTime = System.nanoTime();
            //1 UPDATE: update info such as char positions
            update();
            //2 DRAW: shows on screen with updated info
            repaint();
            try {
                double remainingTime = nextDrawTime -currentTime;
                remainingTime=remainingTime/1000000;            //converts nanoseconds to milliseconds.
                if (remainingTime<0){
                    remainingTime=0;
                }
                Thread.sleep((long) remainingTime);              //"sleeps" until it's time to update
                nextDrawTime+=drawInterval;
            }catch (InterruptedException e){
                e.printStackTrace();
            }

        }
    }

    /**
     * Calls update methods in classes
     */
    public void update(){

        if (gameState == playState) {

            player.update();         //Calls player update method.
            for(int i = 0; i < npc[0].length; i++) {
                if (npc[currentMap][i] != null) {
                    npc[currentMap][i].update();
                }
            }
            for(int i = 0; i < monster[0].length; i++) {
                if (monster[currentMap][i] != null) {
                    if (monster[currentMap][i].alive == true && monster[currentMap][i].dying == false) {
                        monster[currentMap][i].missle();
                        monster[currentMap][i].update();
                    }
                    if (monster[currentMap][i].alive == false) {
                       // System.out.println("monster is alive: "+monster[currentMap][i].alive+monster[currentMap][i]);
                        monster[currentMap][i].checkDrop();
                        monster[currentMap][i] = null;
                    }
                    if ( eHandler.isBossbattlele()==true){
                        if ( monster[currentMap][2]==null){
                            eHandler.setBossbattle(false);
                        }
                    }

                }
            }
            for(int i = 0; i < projectileList.size(); i++) {
                if (projectileList.get(i) != null) {
                    if (projectileList.get(i).alive == true) {
                        projectileList.get(i).update();
                    }
                    if (projectileList.get(i).alive == false) {
                        projectileList.remove(i);
                    }
                }
            }
        }
        if (gameState == pauseState) {
            // nothing
        }
    }

    /**
     * Calls draw methods in classes
     *
     * @param graphics the <code>Graphics</code> object to protect
     */
    public void paintComponent(Graphics graphics){

        super.paintComponent(graphics);                 //Referencing JPanel

        Graphics2D graphics2= (Graphics2D)graphics;     //Graphics2D is a class that helps control sophisticated visuals.

        //DEBUG
        long drawStart = 0;
        if (keyH.checkDrawTime) {
            drawStart = System.nanoTime();
        }

        //TITLE SCREEN
        if (gameState == titleState){
            ui.draw(graphics2);
        }

        //MAP SCREEN
        else if (gameState == mapState) {
            map.drawFullMapScreen(graphics2);
        }

        //OTHER
        else{

            //TILE
            tileM.draw(graphics2);                          //Tiles before player so tiles don't cover player.

            characterList.add(player);

            for(int i = 0; i< npc[0].length;i++){
                if (npc[currentMap][i]!=null){
                    characterList.add(npc[currentMap][i]);
                }
            }
            for (int i = 0; i < obj[0].length; i++) {
                if (obj[currentMap][i]!=null){
                    characterList.add(obj[currentMap][i]);
                }
            }
            for (int i = 0; i < monster[0].length; i++) {
                if (monster[currentMap][i]!=null){
                    characterList.add(monster[currentMap][i]);
                }
            }
            for (int i = 0; i < projectileList.size(); i++) {
                if (projectileList.get(i)!=null){
                    characterList.add(projectileList.get(i));
                }
            }

            //SORT
            Collections.sort(characterList, new Comparator<Character>() {
                @Override
                public int compare(Character c1, Character c2) {
                    int result = Integer.compare(c1.worldY,c2.worldY);
                    return result;
                }
            });
            //DRAW CHARACTER/ENTITIES
            for (int i = 0; i < characterList.size(); i++) {
                characterList.get(i).draw(graphics2);


            }

            tileM.draw2(graphics2);
            if (eHandler.isBossbattlele()==true) {
                if (monster[currentMap][2]!=null){
                    monster[currentMap][2].draw(graphics2);
                }else if (monster[currentMap][26]!=null){
                    monster[currentMap][26].draw(graphics2);
                }

            }
            //EMPTY CHARACTER LIST
            characterList.clear();


            //UI
            ui.draw(graphics2);

        }

    }
    public void playMusic(int i){
        music.setFile(i);
        music.play();
        music.loop();
    }
    public void stopMusic(){
        music.stop();

    }
    public void playSE(int i){
        se.setFile(i);
        se.play();
    }
}

