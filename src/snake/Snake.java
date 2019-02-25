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
import javax.swing.*;
import java.awt.*;
import java.util.Random;

public class Snake {
    int height = 3;
    int []x;
    int []y;
    long beginTime=0;// thời điểm ban đầu
    long t2=0;// thời gian animation
    int speed=200;// tốc độ chơi
    int maxhei=7 ;
    public  static int UP = 1;// tạo biến tĩnh ko thay đổi giá trị
    public  static int DOWN = -1;//
    public  static int LEFT = 2;//
    public  static int RIGHT = -2;//
    int vector = Snake.DOWN;// hướng di chuyển ban đầu là đi xuống
    boolean udAfterChangeVt =  true;

    public Snake(){
        x= new int[100];
        y= new int[100];
        x[0]=5;
        y[0]=4;

        x[1]=5;
        y[1]=3;

        x[2]=5;
        y[2]=2;
    }

    public void resetGame(){
        x= new int[20];
        y= new int[20];
        x[0]=5;
        y[0]=4;

        x[1]=5;
        y[1]=3;

        x[2]=5;
        y[2]=2;
        height = 3;

        vector= Snake.DOWN;
    }

    public void setVector(int v){
        if(vector != -v && udAfterChangeVt)// đk đi của rắn
        {
            vector = v;
            udAfterChangeVt = false;
        }
    }

    public boolean IfQuarryInSnake(int x1,int y1){// food trong rắn
        for (int i=0;i<height;i++)
            if(x[i]==x1 && y[i]==y1)return true;
        return false;
    }

    public Point newQuarry(){// tọa độ điểm mới của mồi
        Random r= new Random();
        int x;
        int y;
        do {
            x = r.nextInt(19);
            y = r.nextInt(19);
        }while(IfQuarryInSnake(x,y));
        return new Point(x,y);
    }

    public int getSpeed(){
        int speed=200;
        for (int i=0;i<GameScreen.level;i++){
            speed*=0.8;
        }
        return speed;
    }

    public void update() {// cập nhật tọa độ để vẽ

        if(height==maxhei){// tăng level
            GameScreen.isPlaying=false;
            resetGame();
            speed= getSpeed();
            maxhei +=3;
            GameScreen.level++;
        }

        for (int i=1;i<height;i++){// game over thì dừng lại
            // kiểm tra va chạm
            if(x[0]==x[i]&&y[0]==y[i]){
                String name= JOptionPane.showInputDialog("Mời bạn nhập tên: ");
                // hàm trả giá trị ms nhập
                FrameScreen.users.add(new User(name, String.valueOf(GameScreen.level)));
                //
                GameScreen.isPlaying=false;
                GameScreen.isGameOver=true;
                GameScreen.point=0;
                GameScreen.level=1;

            }
        }
        if (System.currentTimeMillis() - t2 > speed) {// miệng rắn chuyển động

            udAfterChangeVt = true;

            Data.HeadGoUp.update();
            Data.HeadGoDown.update();
            Data.HeadGoRight.update();
            Data.HeadGoLeft.update();
            t2 = System.currentTimeMillis();
        }
        if (System.currentTimeMillis() - beginTime > speed) {
            //ngay lúc này trừ beginTime >1000 ms(1s)

            if(GameScreen.bg[x[0]][y[0]]==2){
                height++;
                GameScreen.point++;
                GameScreen.bg[x[0]][y[0]]=0;
                GameScreen.bg[newQuarry().x][newQuarry().y]=2;
            }
            for(int i = height-1; i>0;i--){
                x[i]=x[i-1];//tạo rắn liền mạch
                y[i]=y[i-1];//
            }
            if (vector == Snake.UP) y[0]--;
            if (vector == Snake.DOWN) y[0]++;
            if (vector == Snake.LEFT) x[0]--;
            if (vector == Snake.RIGHT) x[0]++;
            beginTime = System.currentTimeMillis();
            //cho begintime = lúc này
        }
        if(x[0]<0) x[0]=19;
        if(x[0]>19) x[0]=0;
        if(y[0]<0) y[0]=19;
        if(y[0]>19) y[0]=0;
    }

    public void paintSnake(Graphics g){
        g.setColor(Color.green);
        for (int i=0;i<height;i++){
            //g.fillRect(x[i]*20+1,y[i]*20+1,18,18);
            g.drawImage(Data.imgBody,x[i]*20+GameScreen.padding,y[i]*20+GameScreen.padding,null);
            if(vector==Snake.UP)
                g.drawImage(Data.HeadGoUp.getCurentImg(),x[0]*20+GameScreen.padding,y[0]*20+GameScreen.padding,null);
            else if(vector==Snake.DOWN)
                g.drawImage(Data.HeadGoDown.getCurentImg(),x[0]*20+GameScreen.padding,y[0]*20+GameScreen.padding,null);
            else if(vector==Snake.LEFT)
                g.drawImage(Data.HeadGoLeft.getCurentImg(),x[0]*20+GameScreen.padding,y[0]*20+GameScreen.padding,null);
            else if(vector==Snake.RIGHT)
                g.drawImage(Data.HeadGoRight.getCurentImg(),x[0]*20+GameScreen.padding,y[0]*20+GameScreen.padding,null);

        }

    }
}

