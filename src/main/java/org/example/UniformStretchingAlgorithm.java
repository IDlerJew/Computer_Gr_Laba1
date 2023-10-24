package org.example;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.awt.image.WritableRaster;
import java.io.File;
import java.io.IOException;

public class UniformStretchingAlgorithm {
    public static void main(String[] args) throws IOException {
        //BufferedImage image = ImageIO.read(new File("C:/Users/IDler/Desktop/KG/firstSample.jpg"));
        BufferedImage image = ImageIO.read(new File("C:/Users/IDler/Desktop/KG/secondSample.jpg"));
         BufferedImage image2= equalize(image);
        ImageIO.write(image2, "jpg", new File("C:/Users/IDler/Desktop/KG/uniform.jpg"));
//        JLabel picLabe1 = new JLabel(new ImageIcon(image));
//        JLabel picLabe2 = new JLabel(new ImageIcon(image2));
//            JPanel jPanel= new JPanel();
//        JPanel jPanel2= new JPanel();

//            jPanel.add(picLabe1);
//            jPanel2.add(picLabe2);
//            JFrame f = new JFrame();
//        JFrame f2 = new JFrame();
//            f.setSize(new Dimension(image.getWidth(),image.getWidth()));
//        f2.setSize(new Dimension(image.getWidth(),image.getWidth()));
//            f.setContentPane(jPanel);
//            f2.setContentPane(jPanel2);
//            f.setVisible(true);
//        f2.setVisible(true);
//            f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
//        f2.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }

    static BufferedImage equalize(BufferedImage image){
        BufferedImage newImage = new BufferedImage(image.getWidth(), image.getHeight(),
                BufferedImage.TYPE_BYTE_GRAY);
        WritableRaster wr = image.getRaster();
        WritableRaster er = newImage.getRaster();
        int totalpixels= wr.getWidth()*wr.getHeight();
        int[] histogram = new int[256];

        for (int i = 0; i < wr.getWidth(); i++) {
            for (int j = 0; j < wr.getHeight(); j++) {
                histogram[wr.getSample(i, j, 0)]++;// берём значения отдельного пикселя
            }
        }

        int[] chistogram = new int[256];
        chistogram[0] = histogram[0];
        for(int i=1;i<256;i++){
            chistogram[i] = chistogram[i-1] + histogram[i];
        }

        float[] arr = new float[256];
        for(int i=0;i<256;i++){
            arr[i] = (float)((chistogram[i]*255.0)/(float)totalpixels);
        }

        for (int i = 0; i < wr.getWidth(); i++) {
            for (int j = 0; j < wr.getHeight(); j++) {
                int nVal = (int) arr[wr.getSample(i, j, 0)];
                er.setSample(i, j, 0, nVal);
            }
        }
        newImage.setData(er);
        return newImage;
    }
}
