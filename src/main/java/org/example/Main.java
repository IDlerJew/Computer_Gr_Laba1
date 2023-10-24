package org.example;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

public class Main {
    public static void main(String[] args) {
        try {
            BufferedImage image = ImageIO.read(new File("C:/Users/IDler/Desktop/2231.jpg"));

            //ImageEqualization equalization = new ImageEqualization();
           // BufferedImage equalizedImage = equalization.equalize(image);

          //  ImageIO.write(equalizedImage, "jpg", new File("C:/Users/IDler/Desktop/554.jpg"));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}