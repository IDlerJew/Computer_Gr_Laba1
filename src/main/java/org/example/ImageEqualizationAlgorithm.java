package org.example;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import javax.imageio.ImageIO;
import javax.swing.*;

public class ImageEqualizationAlgorithm {
    public static void main(String[] args) {
        try {
            //BufferedImage image = ImageIO.read(new File("C:/Users/IDler/Desktop/KG/firstSample.jpg"));
            BufferedImage image = ImageIO.read(new File("C:/Users/IDler/Desktop/KG/secondSample.jpg"));
            BufferedImage image2=test(image);
            ImageIO.write(image2, "jpg", new File("C:/Users/IDler/Desktop/KG/equalization.jpg"));


            JLabel picLabe1 = new JLabel(new ImageIcon(image));
        JLabel picLabe2 = new JLabel(new ImageIcon(image2));
            JPanel jPanel= new JPanel();
        JPanel jPanel2= new JPanel();

            jPanel.add(picLabe1);
            jPanel2.add(picLabe2);
            JFrame f = new JFrame();
        JFrame f2 = new JFrame();
            f.setSize(new Dimension(image.getWidth(),image.getWidth()));
        f2.setSize(new Dimension(image.getWidth(),image.getWidth()));
            f.setContentPane(jPanel);
            f2.setContentPane(jPanel2);
            f.setVisible(true);
        f2.setVisible(true);
            f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        f2.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    static BufferedImage test(BufferedImage image) throws IOException {
        BufferedImage newImage = new BufferedImage(image.getWidth(), image.getHeight(),
                BufferedImage.TYPE_BYTE_GRAY);
        int width = image.getWidth();
        int height = image.getHeight();

        int[] histogram = new int[256];

        // Подсчет количества пикселей каждого значения яркости, проходя по каждому пикселю изображния, каждое значение ярокости от 0 до 255
        for (int i = 0; i < width; i++) {
            for (int j = 0; j < height; j++) {
                Color pixelColor = new Color(image.getRGB(i, j));// берем пиксель
                int grayscaleValue = (pixelColor.getRed() + pixelColor.getGreen() + pixelColor.getBlue()) / 3;  // вычисляем его яркость
                histogram[grayscaleValue]++; // Прибавляем количество данных пикселей яркости на единицу
            }
        }

        int totalPixels = width * height;
        float[] probabilities = new float[256];

        // Вычисление вероятностей каждого значения яркости
        for (int i = 0; i < 256; i++) {
            probabilities[i] = (float) histogram[i] / totalPixels; //Вычисляем вероятность каждого значения яркости, разделив количество
            // пикселей данного значения на общее количество пикселей.
        }

        int[] cumulativeProbabilities = new int[256];

        // Вычисление кумулятивных вероятностей
        float cumulativeProbability = 0;
        for (int i = 0; i < 256; i++) {
            cumulativeProbability += probabilities[i];
            cumulativeProbabilities[i] = Math.round(cumulativeProbability * 255);
        }

        // Применение новых значений яркости к изображению
        for (int i = 0; i < width; i++) {
            for (int j = 0; j < height; j++) {
                Color pixelColor = new Color(image.getRGB(i, j));
                int grayscaleValue = (pixelColor.getRed() + pixelColor.getGreen() + pixelColor.getBlue()) / 3;
                int newGrayscaleValue = cumulativeProbabilities[grayscaleValue];
                int newColor = new Color(newGrayscaleValue, newGrayscaleValue, newGrayscaleValue).getRGB();
                newImage.setRGB(i, j, newColor);
            }
        }

        // Сохранение эквализованного изображения

        return newImage;

    }
}
