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
import java.awt.*;

public class Animation {
    public Image[] images;
    public int n;// số lg hình
    int curentImg=0;//tạo hoạt hình cho
    public Animation(){
        n=0;
        curentImg =0;
    }
    public void addImage(Image image){
        Image[] ar=images;
        images= new Image[n+1];

        for(int i=0;i<n;i++){
            images[i]=ar[i];
        }
        images[n] = image;
        n++;
    }
    public void update(){
        curentImg++;
        if(curentImg>=n) curentImg=0;// sử dụng để reset hình
    }

    public Image getCurentImg() {
        return images[curentImg];
    }
}

