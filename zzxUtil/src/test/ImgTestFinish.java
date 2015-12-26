package test;

import java.awt.Graphics2D;
import java.awt.Toolkit;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Date;

import javax.imageio.ImageIO;
import javax.swing.ImageIcon;

import com.lowagie.text.DocumentException;
import com.lowagie.text.pdf.PdfContentByte;
import com.lowagie.text.pdf.PdfReader;
import com.lowagie.text.pdf.PdfStamper;

public class ImgTestFinish {
	public static void main(String[] args) {
		try {
			new ImgTestFinish().dealImgPdf(new File("c:/111.pdf"),new File("d:/1.jpg"), new File("d://55.pdf"),10,20);
		} catch (IOException | DocumentException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public File dealImgPdf(File pdfFinishedFile, File imgFile, File pdfFile, int deviationX, int deviationY) throws IOException, DocumentException {

            //处理图片
            java.awt.Image image = Toolkit.getDefaultToolkit().getImage(imgFile.getAbsolutePath());
            ImageIcon imageIcon = new ImageIcon(image);
             BufferedImage  bufferedImage = new BufferedImage(imageIcon
                    .getIconWidth(), imageIcon.getIconHeight(),
                    BufferedImage.TYPE_4BYTE_ABGR);
            Graphics2D g2D = (Graphics2D) bufferedImage.getGraphics();
            g2D.drawImage(imageIcon.getImage(), 0, 0, imageIcon
                    .getImageObserver());
            int alpha = 0;
            for (int j1 = bufferedImage.getMinY(); j1 < bufferedImage
                    .getHeight(); j1++) {
                for (int j2 = bufferedImage.getMinX(); j2 < bufferedImage
                        .getWidth(); j2++) {
                    int rgb = bufferedImage.getRGB(j2, j1);

                    int R =(rgb & 0xff0000 ) >> 16 ;
                    int G= (rgb & 0xff00 ) >> 8 ;
                    int B= (rgb & 0xff );
                    if(((255-R)<30) && ((255-G)<30) && ((255-B)<30)){
                        rgb = ((alpha + 1) << 24) | (rgb & 0x00ffffff);
                    }
                    bufferedImage.setRGB(j2, j1, rgb);
                }
            }

            g2D.drawImage(bufferedImage, 0, 0, imageIcon.getImageObserver());
            File tempFile = new File(imgFile.getParent()+new Date().getTime()+(int)(Math.random()*1000)+".png");
            ImageIO.write(bufferedImage, "png", tempFile);
            //将图片转成合成到已有的pdf中
            com.lowagie.text.Image img = com.lowagie.text.Image.getInstance(tempFile.getAbsolutePath());
            img.setAbsolutePosition(deviationX, deviationY);

            PdfReader sourcePdf = new PdfReader(pdfFile.getAbsolutePath(), "PDF".getBytes());
            int pageSize = sourcePdf.getNumberOfPages();
            PdfStamper stamp = new PdfStamper(sourcePdf, new FileOutputStream(pdfFinishedFile));
            for (int i = 1; i <= pageSize; i++) {
                PdfContentByte under = stamp.getUnderContent(i);
                under.addImage(img);
            }
            stamp.close();// 关闭
        return pdfFinishedFile;
    }
}
