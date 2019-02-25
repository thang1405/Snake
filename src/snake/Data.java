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
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

public class Data {//load hình
    public static BufferedImage spite;
    public static Image imgHead;
    public static Image imgHeadUp;
    public static Image imgHeadDown;
    public static Image imgHeadLeft;
    public static Image imgHeadRight;

    public static Image imgBody;
    public static Image imgFood;
    public static Image imgFood1;
    public static Image imgFood2;
    public static Image imgFood3;

    public static Animation HeadGoUp;
    public static Animation HeadGoDown;
    public static Animation HeadGoLeft;
    public static Animation HeadGoRight;

    public static Animation Food;


    public static void Load() {
        try{
            spite = ImageIO.read(new File("res/spite1.png"));

            imgHead = spite.getSubimage(0,0,20,20);
            imgBody = ImageIO.read(new File("res/Body.png"));
            imgHeadDown = ImageIO.read(new File("res/pacmandown.jpg"));
            imgHeadLeft = ImageIO.read(new File("res/pacmanleft.jpg"));
            imgHeadRight = ImageIO.read(new File("res/pacmanright.jpg"));
            imgHeadUp = ImageIO.read(new File("res/pacmanup.jpg"));

            imgFood = ImageIO.read(new File("res/ghost21.jpg"));
            imgFood1 = ImageIO.read(new File("res/ghost20.jpg"));
            imgFood2 = ImageIO.read(new File("res/ghost30.jpg"));
            imgFood3 = ImageIO.read(new File("res/ghost31.jpg"));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    public static void LoadAnimation(){
        HeadGoUp = new Animation();
        HeadGoUp.addImage(imgHead);
        HeadGoUp.addImage(imgHeadUp);

        HeadGoDown = new Animation();
        HeadGoDown.addImage(imgHead);
        HeadGoDown.addImage(imgHeadDown);

        HeadGoLeft = new Animation();
        HeadGoLeft.addImage(imgHead);
        HeadGoLeft.addImage(imgHeadLeft);

        HeadGoRight = new Animation();
        HeadGoRight.addImage(imgHead);
        HeadGoRight.addImage(imgHeadRight);

        Food = new Animation();
        Food.addImage(imgFood);
        Food.addImage(imgFood1);
        Food.addImage(imgFood2);
        Food.addImage(imgFood3);

    }
}

