package readImg;

import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.Transparency;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;

public class TransparentImg {

	public static void main(String[] args) throws IOException {
		int width = 400;
		int height = 300;
		// 创建BufferedImage对象
		BufferedImage image = new BufferedImage(width, height,     BufferedImage.TYPE_INT_RGB);
		// 获取Graphics2D
		Graphics2D g2d = image.createGraphics();
		// ----------  增加下面的代码使得背景透明  -----------------
		image = g2d.getDeviceConfiguration().createCompatibleImage(width, height, Transparency.TRANSLUCENT);
		g2d.dispose();
		g2d = image.createGraphics();
		// ----------  背景透明代码结束  -----------------
		// 画图
		g2d.setColor(new Color(255,0,0));
		g2d.setStroke(new BasicStroke(1));
		//释放对象
		g2d.dispose();
		// 保存文件
		ImageIO.write(image, "png", new File("c:/test.png"));
	}
}
