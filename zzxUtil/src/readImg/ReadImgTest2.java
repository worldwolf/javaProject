package readImg;

import java.awt.Image;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;

public class ReadImgTest2 {
	/**
	 * ªÒ»°Õº∆¨
	 * @param originalImage
	 * @return
	 */
	public BufferedImage getGrayPictureDrawImg(BufferedImage originalImage,BufferedImage img2) {
		BufferedImage result = new BufferedImage(originalImage.getWidth(), originalImage.getHeight(),
                BufferedImage.TYPE_INT_RGB);
			

		for(int i = originalImage.getMinX(); i < originalImage.getWidth() ; i++){
			for(int j = originalImage.getMinY() ; j < originalImage.getHeight() ; j++){
				result.setRGB( i, j, originalImage.getRGB(i, j));
			}
		}
		
		
		
		int beginX = originalImage.getWidth() - img2.getWidth(),
				beginY = originalImage.getHeight() - img2.getHeight();
			for(int i = img2.getMinX(); i < img2.getWidth() ; i++){
				for(int j = img2.getMinY() ; j < img2.getHeight() ; j++){
					result.setRGB( beginX+i, beginY+j, img2.getRGB(i, j));
				}
			}
			return result;
	}
	
	public static void main(String[] args) throws IOException {
		BufferedImage bufferedImage = ImageIO.read(new File("d://3.jpg")); 
		int width = 200,height = 200;
		BufferedImage tagImg = new BufferedImage(width, height,
                   BufferedImage.TYPE_INT_RGB);
		   tagImg.getGraphics().drawImage(
				bufferedImage.getScaledInstance(width, height,
                        Image.SCALE_SMOOTH), 0, 0, null);  
		   
		   
		BufferedImage bufferedBackgroundImg = ImageIO.read(new File("d://4.gif")); 
		BufferedImage image = new ReadImgTest2().getGrayPictureDrawImg(bufferedBackgroundImg,tagImg);
	 File file=new File("d://5s.jpg");
	 try {
            ImageIO.write(image, "png",file);
            image.flush();
        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
	}
}
