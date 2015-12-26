package printPdf;

import java.awt.*;
import java.io.*;

import com.lowagie.text.*;
import com.lowagie.text.Font;
import com.lowagie.text.Image;
import com.lowagie.text.Rectangle;
import com.lowagie.text.pdf.*;

/**
 * �������Ŀ��ʹ��Itext��txt�ļ�ת��ΪPDF�ļ��� ����ʵ�ֶ��ļ���һЩȨ�޿��ơ� ��ʵ��pdf�ļ��� �ܣ����ˮӡ�ȡ�
 */
public class PDFConvertBL {
	// txtԭʼ�ļ���·��
	private static final String txtFilePath = "d:/11.txt";

	// ���ɵ�pdf�ļ�·��
	private static final String pdfFilePath = "d:/22.pdf";
	// ���ˮӡͼƬ·��
	private static final String imageFilePath = "D:/33.jpg";
	// ������ʱ�ļ�ǰ׺
	private static final String prefix = "tempFile";
	// ����������
	private static final String OWNERPASSWORD = "12345678";

	/**
	 * txt�ļ�ת��Ϊpdf�ļ�
	 * 
	 * @param txtFile
	 *            txt�ļ�·��
	 * @param pdfFile
	 *            pdf�ļ�·��
	 * @param userPassWord
	 *            �û�����
	 * @param waterMarkName
	 *            ˮӡ����
	 * @param permission
	 *            ����Ȩ��
	 */
	public static void generatePDFWithTxt(String txtFile, String pdfFile,
			String userPassWord, String waterMarkName, int permission) {

		try {
			// ������ʱ�ļ�
			File file = File.createTempFile(prefix, ".pdf");
			// ����pdf�ļ�����ʱ�ļ�
			if (createPDFFile(txtFile, file))

			{
				// ����ˮӡ�ͼ���
				waterMark(file.getPath(), pdfFile, userPassWord, OWNERPASSWORD,
						waterMarkName, permission);

			}
		} catch (Exception e) {

			e.printStackTrace();
		}

	}

	/**
	 * ����PDF�ĵ�
	 * 
	 * @param txtFilePath
	 *            txt�ļ�·����Դ�ļ���
	 * @param pdfFilePath
	 *            pdf�ļ�·��(���ļ�)
	 */
	private static boolean createPDFFile(String txtFilePath, File file) {

		// ����ֽ��
		Rectangle rect = new Rectangle(PageSize.A4);
		// ����ҳ��
		HeaderFooter footer = new HeaderFooter(new Phrase("ҳ��:",
				setChineseFont()), true);

		footer.setBorder(Rectangle.NO_BORDER);
		// step1
		Document doc = new Document(rect, 50, 50, 50, 50);

		doc.setFooter(footer);
		try {
			FileReader fileRead = new FileReader(txtFilePath);
			BufferedReader read = new BufferedReader(fileRead);
			// ����pdf�ļ�����·�� step2

			PdfWriter.getInstance(doc, new FileOutputStream(file));
			// ��pdf�ļ� step3
			doc.open();
			// ʵ����Paragraph ��ȡд��pdf�ļ������ݣ�����֧�����ĵķ���. step4
			while (read.ready())

			{
				// ������ݵ�pdf(���ｫ�ᰴ��txt�ļ���ԭʼ��ʽ���)

				doc.add(new Paragraph(read.readLine(), setChineseFont()));

			}
			// �ر�pdf�ļ� step5
			doc.close();

			return true;
		} catch (Exception e)

		{
			e.printStackTrace();
			return false;

		}
	}

	/**
	 * ��pdf�ļ������ˮӡ
	 * 
	 * @param inputFile
	 *            ԭʼ�ļ�
	 * @param outputFile
	 *            ˮӡ����ļ�
	 * @param waterMarkName
	 *            ˮӡ����
	 */
	private static void waterMark(String inputFile, String outputFile,
			String userPassWord, String ownerPassWord,

			String waterMarkName, int permission) {
		try

		{
			PdfReader reader = new PdfReader(inputFile);

			PdfStamper stamper = new PdfStamper(reader, new FileOutputStream(
					outputFile));
			// ��������

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
				// ���ͼƬ
				//
				under.addImage(image);
				under.beginText();

				under.setColorFill(Color.CYAN);
				under.setFontAndSize(base, 30);
				// ����ˮӡ����������б ��ʼ
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
				// �������ý���

				under.endText();
				// ��һ��Բ
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
	 * ��������
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

		generatePDFWithTxt(txtFilePath, pdfFilePath, "123", "ˮӡ����", 16);

	}
}