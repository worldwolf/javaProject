package printPdf;

import java.awt.*;
import java.io.*;

import com.lowagie.text.*;
import com.lowagie.text.Font;
import com.lowagie.text.Image;
import com.lowagie.text.Rectangle;
import com.lowagie.text.pdf.*;

/**
 * 最近的项目中使用Itext将txt文件转换为PDF文件， 并且实现对文件的一些权限控制。 现实对pdf文件加 密，添加水印等。
 */
public class PDFConvertBL {
	// txt原始文件的路径
	private static final String txtFilePath = "d:/11.txt";

	// 生成的pdf文件路径
	private static final String pdfFilePath = "d:/22.pdf";
	// 添加水印图片路径
	private static final String imageFilePath = "D:/33.jpg";
	// 生成临时文件前缀
	private static final String prefix = "tempFile";
	// 所有者密码
	private static final String OWNERPASSWORD = "12345678";

	/**
	 * txt文件转换为pdf文件
	 * 
	 * @param txtFile
	 *            txt文件路径
	 * @param pdfFile
	 *            pdf文件路径
	 * @param userPassWord
	 *            用户密码
	 * @param waterMarkName
	 *            水印内容
	 * @param permission
	 *            操作权限
	 */
	public static void generatePDFWithTxt(String txtFile, String pdfFile,
			String userPassWord, String waterMarkName, int permission) {

		try {
			// 生成临时文件
			File file = File.createTempFile(prefix, ".pdf");
			// 创建pdf文件到临时文件
			if (createPDFFile(txtFile, file))

			{
				// 增加水印和加密
				waterMark(file.getPath(), pdfFile, userPassWord, OWNERPASSWORD,
						waterMarkName, permission);

			}
		} catch (Exception e) {

			e.printStackTrace();
		}

	}

	/**
	 * 创建PDF文档
	 * 
	 * @param txtFilePath
	 *            txt文件路径（源文件）
	 * @param pdfFilePath
	 *            pdf文件路径(新文件)
	 */
	private static boolean createPDFFile(String txtFilePath, File file) {

		// 设置纸张
		Rectangle rect = new Rectangle(PageSize.A4);
		// 设置页码
		HeaderFooter footer = new HeaderFooter(new Phrase("页码:",
				setChineseFont()), true);

		footer.setBorder(Rectangle.NO_BORDER);
		// step1
		Document doc = new Document(rect, 50, 50, 50, 50);

		doc.setFooter(footer);
		try {
			FileReader fileRead = new FileReader(txtFilePath);
			BufferedReader read = new BufferedReader(fileRead);
			// 设置pdf文件生成路径 step2

			PdfWriter.getInstance(doc, new FileOutputStream(file));
			// 打开pdf文件 step3
			doc.open();
			// 实例化Paragraph 获取写入pdf文件的内容，调用支持中文的方法. step4
			while (read.ready())

			{
				// 添加内容到pdf(这里将会按照txt文件的原始样式输出)

				doc.add(new Paragraph(read.readLine(), setChineseFont()));

			}
			// 关闭pdf文件 step5
			doc.close();

			return true;
		} catch (Exception e)

		{
			e.printStackTrace();
			return false;

		}
	}

	/**
	 * 在pdf文件中添加水印
	 * 
	 * @param inputFile
	 *            原始文件
	 * @param outputFile
	 *            水印输出文件
	 * @param waterMarkName
	 *            水印名字
	 */
	private static void waterMark(String inputFile, String outputFile,
			String userPassWord, String ownerPassWord,

			String waterMarkName, int permission) {
		try

		{
			PdfReader reader = new PdfReader(inputFile);

			PdfStamper stamper = new PdfStamper(reader, new FileOutputStream(
					outputFile));
			// 设置密码

			stamper.setEncryption(userPassWord.getBytes(),
					ownerPassWord.getBytes(), permission, false);
			BaseFont base = BaseFont.createFont("STSong-Light", "UniGB-UCS2-H",
					BaseFont.NOT_EMBEDDED);
			int total = reader.getNumberOfPages() + 1;
			Image image = Image.getInstance(imageFilePath);
			//
			image.setAbsolutePosition(200, 400);
			PdfContentByte under;
			int j = waterMarkName.length();
			char c = 0;
			int rise = 0;
			for (int i = 1; i < total; i++) {
				rise = 500;
				under = stamper.getUnderContent(i);
				// 添加图片
				//
				under.addImage(image);
				under.beginText();

				under.setColorFill(Color.CYAN);
				under.setFontAndSize(base, 30);
				// 设置水印文字字体倾斜 开始
				if (j >= 15) {
					under.setTextMatrix(200, 120);
					for (int k = 0; k < j; k++) {

						under.setTextRise(rise);
						c = waterMarkName.charAt(k);
						under.showText(c + "");
						rise -= 20;

					}
				} else

				{
					under.setTextMatrix(180, 100);

					for (int k = 0; k < j; k++)

					{
						under.setTextRise(rise);

						c = waterMarkName.charAt(k);
						under.showText(c + "");
						rise -= 18;

					}
				}
				// 字体设置结束

				under.endText();
				// 画一个圆
				//
				under.ellipse(250, 450, 350, 550);
				//
				under.setLineWidth(1f);
				// under.stroke();

			}
			stamper.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * 设置中文
	 * 
	 * @return Font
	 */

	private static Font setChineseFont() {
		BaseFont base = null;
		Font fontChinese = null;
		try

		{
			base = BaseFont.createFont("STSong-Light", "UniGB-UCS2-H",
					BaseFont.EMBEDDED);
			fontChinese = new Font(base, 12, Font.NORMAL);
		} catch (DocumentException e)

		{
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}

		return fontChinese;
	}

	public static void main(String[] args) {

		generatePDFWithTxt(txtFilePath, pdfFilePath, "123", "水印文字", 16);

	}
}