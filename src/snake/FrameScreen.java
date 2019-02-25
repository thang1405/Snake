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
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.io.*;
import java.util.ArrayList;

public class FrameScreen extends JFrame {// chứa cái gamescreen
    GameScreen gameScreen ;

    public static ArrayList<User> users;

    public FrameScreen() throws IOException {
        gameScreen = new GameScreen();
        add(gameScreen);// thêm hình vào Frame
        setSize(750 ,500);// chiều dài rộng 500
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);// thoát luôn khi tắt

        users= new ArrayList<User>();
        ReadData();

        this.addKeyListener(new handler());// ko sd lệnh trong static dc
        this.addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent e) {
                try {
                    UpdateData();
                } catch (IOException e1) {
                    e1.printStackTrace();
                }
            }
        });

        setVisible(true);// Tạo Frame là có thể nhìn thấy và nên để dưới cùng

    }

    public static void main(String[] args) throws IOException {// chạy tạo Frame
        FrameScreen f= new FrameScreen();


    }
    private class handler implements KeyListener{// di chuyển bằng phím

        @Override
        public void keyTyped(KeyEvent e) { }

        @Override
        public void keyPressed(KeyEvent e) {// trong game này sd phím

            if(e.getKeyCode()==KeyEvent.VK_SPACE
                    || e.getKeyCode()==KeyEvent.VK_ENTER) {//tạm dừng game
                gameScreen.isPlaying = !gameScreen.isPlaying;
                if (gameScreen.isGameOver) {
                    gameScreen.isGameOver = false;
                    gameScreen.snake.resetGame();
                }
            }

            if(e.getKeyCode()==KeyEvent.VK_UP
            || e.getKeyCode()==KeyEvent.VK_W){
                gameScreen.snake.setVector(Snake.UP);// lên
            }
            if(e.getKeyCode()==KeyEvent.VK_DOWN
             || e.getKeyCode()==KeyEvent.VK_S){
                gameScreen.snake.setVector(Snake.DOWN);//xuống
            }
            if(e.getKeyCode()==KeyEvent.VK_LEFT
                    || e.getKeyCode()==KeyEvent.VK_A){
                gameScreen.snake.setVector(Snake.LEFT);//trái
            }
            if(e.getKeyCode()==KeyEvent.VK_RIGHT
                    || e.getKeyCode()==KeyEvent.VK_D){
                gameScreen.snake.setVector(Snake.RIGHT);//phải
            }
        }

        @Override
        public void keyReleased(KeyEvent e) {}
    }

    public static void UpdateData() throws IOException {// lưu dũ liệu ng hơi
        FileWriter fw = new FileWriter("data/data.txt");
        BufferedWriter bw = new BufferedWriter(fw);
        for(User u:users){
            bw.write(u.getName()+ " "+u.getLevel());
            bw.newLine();
        }

        bw.close();
    }

    public static void ReadData() throws IOException { // đọc ra dữ liệu ng chơi
        FileReader fr = new FileReader("data/data.txt");
        BufferedReader br = new BufferedReader(fr);
        String line = null;
        while ((line = br.readLine())!= null){
            String[] str = line.split(" ");
            users.add(new User(str[0],str[1]));
            System.out.println(str[0] + str[1]);
        }
        br.close();
    }
}

