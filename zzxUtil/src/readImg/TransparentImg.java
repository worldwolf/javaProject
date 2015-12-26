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
		// ����BufferedImage����
		BufferedImage image = new BufferedImage(width, height,     BufferedImage.TYPE_INT_RGB);
		// ��ȡGraphics2D
		Graphics2D g2d = image.createGraphics();
		// ----------  ��������Ĵ���ʹ�ñ���͸��  -----------------
		image = g2d.getDeviceConfiguration().createCompatibleImage(width, height, Transparency.TRANSLUCENT);
		g2d.dispose();
		g2d = image.createGraphics();
		// ----------  ����͸���������  -----------------
		// ��ͼ
		g2d.setColor(new Color(255,0,0));
		g2d.setStroke(new BasicStroke(1));
		//�ͷŶ���
		g2d.dispose();
		// �����ļ�
		ImageIO.write(image, "png", new File("c:/test.png"));
	}
}
