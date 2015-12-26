package readImg;

import java.awt.Graphics;
import java.awt.GraphicsConfiguration;
import java.awt.GraphicsDevice;
import java.awt.GraphicsEnvironment;
import java.awt.HeadlessException;
import java.awt.Image;
import java.awt.Transparency;
import java.awt.image.BufferedImage;

import javax.swing.ImageIcon;

public class ReadImgPointTest {
	
	

	/**
	 * 将img转BufferedImage
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
	 * 获取图片
	 * @param originalImage
	 * @return
	 */
	public BufferedImage getGrayPicture(BufferedImage originalImage) {

		int green = 0, red = 0, blue = 0, rgb;
		int imageWidth = originalImage.getWidth();
		int imageHeight = originalImage.getHeight();
		for (int i = originalImage.getMinX(); i < imageWidth; i++) {
			for (int j = originalImage.getMinY(); j < imageHeight; j++) {
				// 图片的像素点其实是个矩阵，这里利用两个for循环来对每个像素进行操作
				Object data = originalImage.getRaster()
						.getDataElements(i, j, null);// 获取该点像素，并以object类型表示
				red = originalImage.getColorModel().getRed(data);
				blue = originalImage.getColorModel().getBlue(data);
				green = originalImage.getColorModel().getGreen(data);
				System.out.println(red+"====="+green+"====="+blue);

				rgb = (red * 256 + green) * 256 + blue;
				System.out.println(rgb);
				if (rgb > 8388608) {
					rgb = rgb - 16777216;
				}
				// 将rgb值写回图片
				originalImage.setRGB(i, j, rgb);
			}

		}

		return originalImage;
	}
	
}
