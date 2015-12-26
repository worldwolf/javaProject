package readImg;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.Arrays;

import javax.imageio.ImageIO;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;


/**
 * <p>Title: ImageUtil </p>
 * <p>Description: ʹ��JDKԭ��̬������ͼƬ����ͼ�Ͳü�ͼƬ </p>
 * <p>Email: icerainsoft@hotmail.com </p> 
 * @author Ares
 * @date 2014��10��28�� ����10:24:26
 */
public class ImageUtil {

    private Logger log = LoggerFactory.getLogger(getClass());
    
    private static String DEFAULT_PREVFIX = "thumb_";
    private static Boolean DEFAULT_FORCE = false;
    
    /**
     * <p>Title: thumbnailImage</p>
     * <p>Description: ����ͼƬ·����������ͼ </p>
     * @param imagePath    ԭͼƬ·��
     * @param w            ����ͼ��
     * @param h            ����ͼ��
     * @param prevfix    ��������ͼ��ǰ׺
     * @param force        �Ƿ�ǿ�ư��տ����������ͼ(���Ϊfalse����������ѱ�������ͼ)
     */
    public void thumbnailImage(File imgFile, int w, int h, String prevfix, boolean force){
        if(imgFile.exists()){
            try {
                // ImageIO ֧�ֵ�ͼƬ���� : [BMP, bmp, jpg, JPG, wbmp, jpeg, png, PNG, JPEG, WBMP, GIF, gif]
                String types = Arrays.toString(ImageIO.getReaderFormatNames());
                String suffix = null;
                // ��ȡͼƬ��׺
                if(imgFile.getName().indexOf(".") > -1) {
                    suffix = imgFile.getName().substring(imgFile.getName().lastIndexOf(".") + 1);
                }// ���ͺ�ͼƬ��׺ȫ��Сд��Ȼ���жϺ�׺�Ƿ�Ϸ�
                if(suffix == null || types.toLowerCase().indexOf(suffix.toLowerCase()) < 0){
                    log.error("Sorry, the image suffix is illegal. the standard image suffix is {}." + types);
                    return ;
                }
                log.debug("target image's size, width:{}, height:{}.",w,h);
                Image img = ImageIO.read(imgFile);
                if(!force){
                    // ����ԭͼ��Ҫ�������ͼ�������ҵ�����ʵ�����ͼ����
                    int width = img.getWidth(null);
                    int height = img.getHeight(null);
                    if((width*1.0)/w < (height*1.0)/h){
                        if(width > w){
                            h = Integer.parseInt(new java.text.DecimalFormat("0").format(height * w/(width*1.0)));
                            log.debug("change image's height, width:{}, height:{}.",w,h);
                        }
                    } else {
                        if(height > h){
                            w = Integer.parseInt(new java.text.DecimalFormat("0").format(width * h/(height*1.0)));
                            log.debug("change image's width, width:{}, height:{}.",w,h);
                        }
                    }
                }
                BufferedImage bi = new BufferedImage(w, h, BufferedImage.TYPE_INT_RGB);
                Graphics g = bi.getGraphics();
                g.drawImage(img, 0, 0, w, h, Color.LIGHT_GRAY, null);
                g.dispose();
                String p = imgFile.getPath();
                // ��ͼƬ������ԭĿ¼������ǰ׺
                ImageIO.write(bi, suffix, new File(p.substring(0,p.lastIndexOf(File.separator)) + File.separator + prevfix +imgFile.getName()));
            } catch (IOException e) {
               log.error("generate thumbnail image failed.",e);
            }
        }else{
            log.warn("the image is not exist.");
        }
    }
    
    public void thumbnailImage(String imagePath, int w, int h, String prevfix, boolean force){
        File imgFile = new File(imagePath);
        thumbnailImage(imgFile, w, h, prevfix, force);
    }
    
    public void thumbnailImage(String imagePath, int w, int h, boolean force){
        thumbnailImage(imagePath, w, h, DEFAULT_PREVFIX, force);
    }
    
    public void thumbnailImage(String imagePath, int w, int h){
        thumbnailImage(imagePath, w, h, DEFAULT_FORCE);
    }
    
    public static void main(String[] args) {
        new ImageUtil().thumbnailImage("imgs/Tulips.jpg", 100, 150);
    }

}