package readImg;
import javax.imageio.ImageIO;
import java.io.File;
import java.io.IOException;
import java.awt.image.BufferedImage;
import java.awt.Image;
import java.awt.Graphics2D;
import java.awt.Transparency; 
 
public class TestPNG{
     
    /**  
     * resizePNG:Resize the PNG file.  
     *   
     * @author lazybone,2010.08.16  
     *   
     * @param fromFile  
     * @param fromW  
     * @param fromH  
     * @param toFile  
     * @param toW  
     * @param toH  
     */ 
    public static void resizePNG(String fromFile, int fromW, int fromH, String toFile, int toW, int toH) {   
        try {   
            BufferedImage to = new BufferedImage(toW, toH,   
                    BufferedImage.TYPE_INT_RGB);   
            Graphics2D g2d = to.createGraphics();   
            to = g2d.getDeviceConfiguration().createCompatibleImage(toW, toH,   
                    Transparency.TRANSLUCENT);   
            g2d.dispose();   
            g2d = to.createGraphics();   
            File f2 = new File(fromFile);   
            BufferedImage bi2 = ImageIO.read(f2);   
            Image from = bi2.getScaledInstance(fromW, fromH, bi2.SCALE_DEFAULT);   
            int a = 128;   
            int b = 128;   
            int tileCount = 8;   
            int offset = 50;   
            for (int i = 0; i < tileCount; i++)   
                for (int j = 0; j < tileCount; j++) {   
                    g2d.drawImage(from, i * a - i * offset - offset / 2, j * a   
                            - j * offset - offset / 2, i * a + a - i * offset   
                            - offset / 2, j * a + a - j * offset - offset / 2,   
                            i * b, j * b, i * b + b, j * b + b, null);   
                }   
            g2d.dispose();   
            ImageIO.write(to, "png", new File(toFile));   
        } catch (IOException e) {   
            e.printStackTrace();   
        }   
    }   
    public static void main(String[] args) throws IOException {   
        System.out.println("Start");   
        int toSize = 624;   
        int fromSize = 1024;   
        resizePNG("d:\\hero.png", fromSize, fromSize, "d:\\hero_ok.png",   
                toSize, toSize);   
        System.out.println("OK");   
    }
}