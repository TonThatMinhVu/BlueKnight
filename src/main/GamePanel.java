package main;
import entity.Player;
import tile.TileManager;

import javax.swing.JPanel;
import java.awt.*;

public class GamePanel extends JPanel implements Runnable
{
    int FPS = 60;
    //Set Man Hinh
    final int originalTileSize =16;
    final int scale=3;
    public final int tileSize = originalTileSize * scale;
    public final int maxScreenCol =26;
    public final int maxScreenRow = 15;
    //
    public final int screenWidth = tileSize * maxScreenCol;//1920pixels
    public final int screenHeight = tileSize * maxScreenRow;//1080pixels
    //MAP SETTINGS
    public final int maxWorldCol = 50;
    public final int maxWorldRow = 50;
    public final int worldWidth = tileSize* maxWorldCol;
    public final int worldHeight = tileSize * maxWorldRow;
    //
    Thread gameThread;
    KeyInput Control = new KeyInput();//KeyInput.java
    //
    TileManager tileM= new TileManager(this);

    public CollisionChecker cChecker = new CollisionChecker(this);
    public Player playerT = new Player(this, Control);




    public GamePanel()
    {
        this.setPreferredSize(new Dimension(screenWidth,screenHeight));
        //Khoi dong game theo kich thuoc da quy dinh
        this.setBackground(Color.black);
        this.setDoubleBuffered(true);
        this.addKeyListener(Control);//Keyinput.java
        //Them phan dieu khien bang ban phim vao game
        this.setFocusable(true);//KeyInput.java
        //Lam cho cai GamePanel "Focus" vao ban phim

    }
    public void startGameThread()
    {
        gameThread = new Thread(this);
        gameThread.start();
    }
    @Override
//    public void run() //Vong lap game
//    {
//        double drawInterval = 1000000000/FPS;
//        double nextDrawTime = System.nanoTime() + drawInterval;
//        long timer=0;
//        double drawCount = 0;
//        while(gameThread != null)
//        {
//            long currentTime = System.nanoTime();
//            System.out.println("Current Time" + currentTime);
//            System.out.println("The game loop is running");
//            update();
//            repaint();
//            drawCount++;
//            try {
//                double remainingTime = nextDrawTime - System.nanoTime();
//                remainingTime = remainingTime/1000000;
//                Thread.sleep((long) remainingTime);
//                timer+=remainingTime/1000000;
//                nextDrawTime += drawInterval;
//                if(remainingTime<0)
//                {
//                    remainingTime = 0;
//                }
//                //
//                if(timer >= 1000000000)
//                {
//                    System.out.println("FPS"+drawCount);
//                }
//
//                }
//            catch (InterruptedException e)
//                {
//                e.printStackTrace();
//                }
//        }
//    }
    public void run()
    {
        double drawInterval = 1000000000/FPS;
        double delta=0;
        long lastTime = System.nanoTime();
        long currentTime;
        long timer=0;
        int drawCount=0;
        while (gameThread !=null)
        {
            currentTime = System.nanoTime();
            delta +=(currentTime - lastTime)/drawInterval;
            timer +=(currentTime - lastTime);
            lastTime= currentTime;
            if(delta >=0)
            {
                update();
                repaint();
                delta --;
                drawCount++;
            }
            if(timer >=1000000000)
            {
                System.out.println("FPS"+ drawCount);
                drawCount = 0;
                timer =0;
            }
        }
    }


    public void update() //Hien thi su duy chuyen cua Player
    {
        playerT.update();
    }

    public void paintComponent(Graphics t)//cap nhat trang thai cua player tren man hinh
    {
        super.paintComponent(t);
        Graphics2D t2 = (Graphics2D)t;
        tileM.draw(t2);
        playerT.draw(t2);
        t2.dispose();
    }
}