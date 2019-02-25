/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package snake;

/**
 *
 * @author Dell
 */

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.io.File;
import java.io.IOException;

public class GameScreen extends JPanel implements Runnable{// vẽ đối tượng lên
    Thread thread;
    static int [][] bg= new int[20][20];
    Snake snake;
    static int padding=10;// khoảng trống
    static int WIDTH = 400;
    static int HEIGHT = 400;
    static boolean isPlaying =false;// tạm dừng game
    static boolean enableTextStart= true;// nhấp nháy chữ;
    static boolean isGameOver=false;//kết thúc
    static int level = 1;
    static int point=0;
    public GameScreen(){
        snake = new Snake();// khởi tạo trc start
        Data.Load();
        Data.LoadAnimation();
        bg[10][10]=2;
        thread= new Thread(this);

        thread.start();// chạy run

    }

    public void paintBg(Graphics g){

        g.setColor(Color.black);// nền
                g.fillRect(0,0,WIDTH+padding*2+300,HEIGHT+padding*2);
        for(int i=0;i<20;i++)
            for(int j=0;j<20;j++){
                //g.fillRect(i*20+1,j*20+1,18,18);
                if(bg[i][j]==2) {
                   // g.drawImage(Data.imgFood,i*20,j*20,null);// ô vuông 18*18
                    g.drawImage(Data.Food.getCurentImg(),i*20+padding,j*20+padding,null);
                    // và có khoảng trống
                }
            }

    }
    private void paintkhung(Graphics g){
        g.setColor(Color.orange);// nền
        g.drawRect(0,0,WIDTH+padding*2,HEIGHT+padding*2);
        g.drawRect(1,1,WIDTH+padding*2-2,HEIGHT+padding*2-2);
        g.drawRect(2,2,WIDTH+padding*2-4,HEIGHT+padding*2-4);
        g.setColor(Color.orange);// nền
        g.drawRect(1,1,WIDTH+padding*2-2+300,HEIGHT+padding*2-2);
        g.drawRect(2,2,WIDTH+padding*2-4+300,HEIGHT+padding*2-4);

    }
    public  void paint(Graphics g){
        paintBg(g);

        snake.paintSnake(g);
        paintkhung(g);
        if(!isPlaying) {// in chữ ra màn hình
            if(enableTextStart) {
                g.setColor(Color.WHITE);
                g.setFont(g.getFont().deriveFont(18.0f));//font chữ to ra
                g.drawString("PRESS SPACE / ENTER TO PLAY", 60, 200);
            }
        }
        if(isGameOver) {// in chữ ra màn hình
            if(enableTextStart) {
                g.setColor(Color.red);
                g.setFont(g.getFont().deriveFont(28.0f));//font chữ to ra
                g.drawString("END GAME", 80, 150);
            }
        }
        g.setColor(Color.WHITE);
        g.setFont(g.getFont().deriveFont(28.0f));//font chữ to ra
        g.drawString(" LEVEL : " + level, 450, 120);
        g.setFont(g.getFont().deriveFont(20.0f));
        g.drawString(" Điểm : "+point*100,450,150);
        for(int i=0;i<FrameScreen.users.size();i++){
            g.drawString(FrameScreen.users.get(i).toString(),450,i*30+180);
        }
    }
    public void run(){
        long t=0,t1=0;

        while (true){
            if (System.currentTimeMillis() - t1 > 500) {
                enableTextStart=!enableTextStart;
                t1 = System.currentTimeMillis();
            }
            if(isPlaying) {
                if (System.currentTimeMillis() - t > 150) {
                    Data.Food.update();
                    t = System.currentTimeMillis();
                }
                snake.update();
            }

            repaint();
            try {
                thread.sleep(20);// ngủ tròn 20ms
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}
