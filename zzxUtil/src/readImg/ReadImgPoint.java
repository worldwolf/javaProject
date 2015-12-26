package readImg;

import java.awt.Graphics;
import java.awt.GraphicsConfiguration;
import java.awt.GraphicsDevice;
import java.awt.GraphicsEnvironment;
import java.awt.HeadlessException;
import java.awt.Image;
import java.awt.Toolkit;
import java.awt.Transparency;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;

import javax.imageio.ImageIO;
import javax.swing.ImageIcon;

public class ReadImgPoint {
	
	public static void main(String[] args) throws FileNotFoundException, IOException {
//		  Image img = Toolkit.getDefaultToolkit().getImage("d://4.gif");
		BufferedImage bufferedImage = ImageIO.read(new File("d://3.jpg")); 
//		  BufferedImage bufferedImage = toBufferedImage(img);
		  
		bufferedImage = new ReadImgPoint().getGrayPicture(bufferedImage);
		
		
		System.out.println(bufferedImage.getWidth()+"-----"+bufferedImage.getHeight()+"------"+(int)(bufferedImage.getWidth()*0.5)+"+++++++"+(int)(bufferedImage.getWidth()*0.5));

		
		
		int width = 200,height = 200;
		BufferedImage tagImg = new BufferedImage(width, height,
                   BufferedImage.TYPE_INT_RGB);
		   tagImg.getGraphics().drawImage(
				bufferedImage.getScaledInstance(width, height,
                        Image.SCALE_SMOOTH), 0, 0, null);  
				
				
				
				
				
//		 Image backgroundImg = Toolkit.getDefaultToolkit().getImage("d://5.jpg");
//		 BufferedImage bufferedBackgroundImg = toBufferedImage(backgroundImg);
		 BufferedImage bufferedBackgroundImg = ImageIO.read(new File("d://4.gif")); 
		 bufferedBackgroundImg = new ReadImgPoint().getGrayPicture(bufferedBackgroundImg);
			
			
		 bufferedImage = new ReadImgPoint().getGrayPictureDrawImg(bufferedBackgroundImg, tagImg);
//		  bufferedImage =  bufferedImage.getSubimage(165,100,100,100);
		 File file=new File("d://5s.jpg");
		 OutputStream out = new FileOutputStream(file);
		 try {
	            ImageIO.write(bufferedBackgroundImg, "png",out);
	            bufferedBackgroundImg.flush();
	        } catch (IOException e) {
	            // TODO Auto-generated catch block
	            e.printStackTrace();
	        }
	}

	
	/**
	 * ��imgתBufferedImage
	 * @param image
	 * @return
	 */
	 public static BufferedImage toBufferedImage(Image image) {
	        if (image instanceof BufferedImage) {
	            return (BufferedImage)image;
	         }
	     
	        // This code ensures that all the pixels in the image are loaded
	         image = new ImageIcon(image).getImage();
	     
	        // Determine if the image has transparent pixels; for this method's
	        // implementation, see e661 Determining If an Image Has Transparent Pixels
	        //boolean hasAlpha = hasAlpha(image);
	     
	        // Create a buffered image with a format that's compatible with the screen
	         BufferedImage bimage = null;
	         GraphicsEnvironment ge = GraphicsEnvironment.getLocalGraphicsEnvironment();
	        try {
	            // Determine the type of transparency of the new buffered image
	            int transparency = Transparency.OPAQUE;
	           /* if (hasAlpha) {
	                 transparency = Transparency.BITMASK;
	             }*/
	     
	            // Create the buffered image
	             GraphicsDevice gs = ge.getDefaultScreenDevice();
	             GraphicsConfiguration gc = gs.getDefaultConfiguration();
	             bimage = gc.createCompatibleImage(
	                 image.getWidth(null), image.getHeight(null), transparency);
	         } catch (HeadlessException e) {
	            // The system does not have a screen
	         }
	     
	        if (bimage == null) {
	            // Create a buffered image using the default color model
	            int type = BufferedImage.TYPE_INT_RGB;
	            //int type = BufferedImage.TYPE_3BYTE_BGR;//by wang
	            /*if (hasAlpha) {
	                 type = BufferedImage.TYPE_INT_ARGB;
	             }*/
	             bimage = new BufferedImage(image.getWidth(null), image.getHeight(null), type);
	         }
	     
	        // Copy image to buffered image
	         Graphics g = bimage.createGraphics();
	     
	        // Paint the image onto the buffered image
	         g.drawImage(image, 0, 0, null);
	         g.dispose();
	     
	        return bimage;
	     }
	 
	/**
	 * ��ȡͼƬ
	 * @param originalImage
	 * @return
	 */
	public BufferedImage getGrayPicture(BufferedImage originalImage) {

		int green = 0, red = 0, blue = 0, rgb;
		int imageWidth = originalImage.getWidth();
		int imageHeight = originalImage.getHeight();
		int minX = 0, minY = 0;
		int maxX = 0, maxY = 0;
		
		//��ȡ�����С x,y
		for (int i = originalImage.getMinX(); i < imageWidth; i++) {
			for (int j = originalImage.getMinY(); j < imageHeight; j++) {
				// ͼƬ�����ص���ʵ�Ǹ�����������������forѭ������ÿ�����ؽ��в���
				Object data = originalImage.getRaster()
						.getDataElements(i, j, null);// ��ȡ�õ����أ�����object���ͱ�ʾ
				red = originalImage.getColorModel().getRed(data);
				blue = originalImage.getColorModel().getBlue(data);
				green = originalImage.getColorModel().getGreen(data);

				/*
				 * ���ｫr��g��b��ת��Ϊrgbֵ����ΪbufferedImageû���ṩ���õ�����ɫ�ķ�����ֻ������rgb��
				 * rgb���Ϊ8388608�����������ֵʱ��Ӧ��ȥ255*255*255��16777216
				 */
				if(Math.abs(red - blue) >= 50 || Math.abs(red -green) >=50 || Math.abs(blue -green) >= 50){
					if(minX ==0 || minX > i){
						minX = i;
					}
					if(minY == 0 || minY > j){
						minY = j;
					}
					
					if(maxX < i){
						maxX = i;
					}
					if(maxY < j){
						maxY = j;
					}
				} else if(red <= 150 && blue <= 150 && green <=150){
					if(minX ==0 || minX > i){
						minX = i;
					}
					if(minY == 0 || minY > j){
						minY = j;
					}
					
					if(maxX < i){
						maxX = i;
					}
					if(maxY < j){
						maxY = j;
					}
				}else {
					rgb = (255 * 256 + 255) * 256 + 255;
					if (rgb > 8388608) {
						rgb = rgb - 16777216;
					}
					originalImage.setRGB(i, j, rgb);
				}
				
				/*rgb = (red * 256 + green) * 256 + blue;
				if (rgb > 8388608) {
					rgb = rgb - 16777216;
				}
				// ��rgbֵд��ͼƬ
				originalImage.setRGB(i, j, rgb);*/
			}
		}
		originalImage = originalImage.getSubimage(minX < 2 ? 0 :minX-1, minY < 2 ? 0 :minY-1, minX-2 <= 0 ? maxX+1 : maxX-minX+4, maxY-minY+1);
		return originalImage;
	}
	
	
	
	/**
	 * ��ȡͼƬ
	 * @param originalImage
	 * @return
	 */
	public BufferedImage getGrayPictureDrawImg(BufferedImage originalImage,BufferedImage img2) {
		int beginX = originalImage.getWidth() - img2.getWidth(),
			beginY = originalImage.getHeight() - img2.getHeight();
		int green = 0, red = 0, blue = 0, rgb;
		for(int i = img2.getMinX(); i < img2.getWidth() ; i++){
			for(int j = img2.getMinY() ; j < img2.getHeight() ; j++){
				/*Object data = img2.getRaster()
						.getDataElements(i, j, null);// ��ȡ�õ����أ�����object���ͱ�ʾ
				red = img2.getColorModel().getRed(data);
				blue = img2.getColorModel().getBlue(data);
				green = img2.getColorModel().getGreen(data);
				if(!(red == 255 && blue ==255 && green == 255)){*/
					// ��rgbֵд��ͼƬ
				// ��rgbֵд��ͼƬ
					originalImage.setRGB( beginX+i, beginY + j, img2.getRGB(i, j));
				//} 
			}
		}
		return originalImage;
	}
}
