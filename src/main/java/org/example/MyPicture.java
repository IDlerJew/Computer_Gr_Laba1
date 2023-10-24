package org.example;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

public class MyPicture {

    BufferedImage myPicture;
    public MyPicture(String image) throws IOException {
        myPicture = ImageIO.read(new File(image));

    }

    public int getWidht() {
        return myPicture.getWidth();
    }

    @Override
    public String toString() {
        return "MyPicture={"+
                 "myPicture=" + myPicture +
                "}";
    }

    public int getHeight() {
        return myPicture.getHeight();
    }

    public BufferedImage getMyPicture() {
        return myPicture;
    }


    public int[][] ArrayOfPixels(){


        int[][] pixels = new int[myPicture.getWidth()][myPicture.getHeight()];

        for( int i = 0; i < myPicture.getWidth(); i++ )
            for( int j = 0; j < myPicture.getHeight(); j++ )
                pixels[i][j] = myPicture.getRGB( i, j );
        return  pixels;
    }
    public void imageCheck(){
        JLabel picLabe1 = new JLabel(new ImageIcon(myPicture));
        JPanel jPanel= new JPanel();
        jPanel.add(picLabe1);
        JFrame f = new JFrame();
        f.setSize(new Dimension(myPicture.getWidth(),myPicture.getWidth()));
        f.add(jPanel);
        f.setVisible(true);
    }
}
