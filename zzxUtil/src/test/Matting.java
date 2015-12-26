package test;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.io.File;

import javax.imageio.ImageIO;
import javax.swing.JButton;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;

public class Matting {
	JFrame frame;
	JTextField Image0Path , Image1Path ;

	public Matting() {
		frame = new JFrame("图片合成");
		frame.setLayout(null);
		frame.setVisible(true);
		frame.setBounds(500, 300, 500, 250);
		JLabel lb0=new JLabel("请选择一张白底原图：");
		lb0.setBounds(80, 30, 200, 20);
		frame.add(lb0);
		Image0Path=new JTextField();
		Image0Path.setBounds(80, 50, 200, 20);
		frame.add(Image0Path);
		JButton Image0Button = new JButton("选择原图");
		Image0Button.setBounds(300, 50, 100, 20);
		frame.add(Image0Button);
		JLabel lb1=new JLabel("请选择一张背景图片：");
		lb1.setBounds(80, 80, 200, 20);
		frame.add(lb1);
		Image1Path=new JTextField();
		Image1Path.setBounds(80, 100, 200, 20);
		frame.add(Image1Path);
		JButton Image1Button = new JButton("选择背景图");
		Image1Button.setBounds(300, 100, 100, 20);
		frame.add(Image1Button);
		JButton BeginButton = new JButton("开始合成图片");
		BeginButton.setBounds(170, 150, 150, 20);
		frame.add(BeginButton);
		Image0Button.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				JFileChooser fc = new JFileChooser();
				int returnVal = fc.showOpenDialog(frame);
				if (returnVal == JFileChooser.APPROVE_OPTION) {
					File file = fc.getSelectedFile();
					Image0Path.setText(file.getPath());
				}
			}
		});
		Image1Button.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				JFileChooser fc = new JFileChooser();
				int returnVal = fc.showOpenDialog(frame);
				if (returnVal == JFileChooser.APPROVE_OPTION) {
					File file = fc.getSelectedFile();
					Image1Path.setText(file.getPath());
				}
			}
		});
		BeginButton.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				if (Image0Path.getText() != "" || Image1Path.getText() != "") {
					BufferedImage image0 = null;
					BufferedImage image1 = null;
					try {
						image0 = ImageIO.read(new File(Image0Path.getText()));
						image1 = ImageIO.read(new File(Image1Path.getText()));
					} catch (Exception ee) {
						ee.printStackTrace();
					}
					BufferedImage image = new BufferedImage(image0.getWidth(), image0.getHeight(),
							BufferedImage.TYPE_INT_RGB);
					for (int i = image0.getMinX(); i < image0.getWidth(); i++) {
						for (int j = image0.getMinY(); j < image0.getHeight(); j++) {
							int rgb = image0.getRGB(i, j);
							int r = (rgb & 0xff0000) >> 16;
							int g = (rgb & 0xff00) >> 8;
							int b = (rgb & 0xff);
							if (Math.abs(r - 255) < 50 & Math.abs(g - 255) < 50
									& Math.abs(b - 255) < 50) {
								image.setRGB(i, j, image1.getRGB(i, j));
							} else {
								image.setRGB(i, j, image0.getRGB(i, j));
							}
						}
					}
					try {
						ImageIO.write(image, "jpg", new File("e:\\4.jpg"));
						JOptionPane.showMessageDialog(null,
								"图片合成成功，已保存在f:\\image.jpg", "提示消息",
								JOptionPane.INFORMATION_MESSAGE);
					} catch (Exception ee) {
						ee.printStackTrace();
					}
				}else{
					JOptionPane.showMessageDialog(null,
							"图片合成失败", "提示消息",
							JOptionPane.INFORMATION_MESSAGE);
				}
			}
		});
	}

	public static void main(String args[]) {
		new Matting();
	}
}
