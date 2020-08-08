import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.*;

public class imgReader {
    private static int[][] imageXY;

    public static void main(String[] args) {
        String image = "x.jpg"; //location of image
        imgRead(image);
        printImage();
        printImageFile();
    }

    public static void imgRead(String inputFile) {
        File file = null;
        int RBGval = 0;
        try {
            file = new File(inputFile);
            BufferedImage readImg = ImageIO.read(file);


            int imgWidth = readImg.getWidth();
            int imgHeight = readImg.getHeight();
            imageXY = new int[imgHeight][imgWidth];
            //readData
            for (int y = 0; y < imgHeight; y++) {
                for (int x = 0; x < imgWidth; x++) {
                    RBGval = readImg.getRGB(x, y);
                    if(drawImage(RBGval))
                    imageXY[y][x] = 0;
                    else imageXY[y][x] = 1;
                }
            }
        } catch (Exception e) {
            System.out.println("Error: " + e);
        }
    }

    public static boolean drawImage(int p) {
        int a, r, g, b;
        a = (p >> 24) & 0xff;
        r = (p >> 16) & 0xff;
        g = (p >> 8) & 0xff;
        b = (p) & 0xff;
        for (int R = 0; R <= 255; R++) {
            if (r == R && g == R && b == R) {
                if(r > 200)
                return true;
            }
           //System.out.println(a +" " +  r +" "+ g +" "+ b);
        }
        return false;
    }

    public static void printImage() {
        System.out.println(imageXY.length + "W And H " + imageXY[0].length);
        for (int x = 0; x < imageXY.length; x++) {
            for (int y = 0; y < imageXY[x].length; y++) {
                if (imageXY[x][y] == 0)
                    System.out.print(".");
                else System.out.print("X");
                //System.out.println(imageXY[x][y]);
            }
            System.out.println("\n");
        }

    }

    public static void printImageFile() {
        try {
            File file = new File("createImage.txt");
            PrintWriter pw = new PrintWriter(new FileWriter(file));
            if(file.createNewFile())
                System.out.print("\n\"[DEBUG]: File created");//TODO DEV MODE
            else System.out.print("\n\"[DEBUG]: File exist");
            for (int x = 0; x < imageXY.length; x++) {
                for (int y = 0; y < imageXY[x].length; y++) {
                    if (imageXY[x][y] == 0)
                        pw.print(" ");
                    else pw.print(".");
                }
                pw.print("\n");
            }
            pw.flush();
            pw.close();
        }catch (Exception e){
            System.out.println(e);
        }
    }
}
