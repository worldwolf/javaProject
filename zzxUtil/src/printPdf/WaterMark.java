package printPdf;

import java.io.File;
import java.io.FileOutputStream;

import com.lowagie.text.Image;
import com.lowagie.text.pdf.PdfContentByte;
import com.lowagie.text.pdf.PdfReader;
import com.lowagie.text.pdf.PdfStamper;

public class WaterMark {

	public static void main(String[] args) throws Exception {
		new WaterMark().addPdfMark("c://44.pdf", "c://13.pdf", "D:\\cacheFile\\aa\\1449751184883.jpg", 10);
	}
	
	
	 /**
	 * ��pdf�ļ����ˮӡ
	 * @param InPdfFile Ҫ��ˮӡ��ԭpdf�ļ�·��
	 * @param outPdfFile ����ˮӡ��Ҫ�����·��
	 * @param markImagePath ˮӡͼƬ·��
	 * @param pageSize ԭpdf�ļ�����ҳ�����÷������ҵ��������ݵ���excel��Ȼ����ת����pdf�����������ֵ����excel��������������ģ�������������ֿ���	ֱ����reader.getNumberOfPages()��ȡpdf����ҳ����
	 * @throws Exception
	 */
	 public static void addPdfMark(String InPdfFile, String outPdfFile, String markImagePath, int pageSize) throws Exception {

	 PdfReader reader = new PdfReader(InPdfFile, "PDF".getBytes());
	 pageSize = reader.getNumberOfPages();
	 
	 PdfStamper stamp = new PdfStamper(reader, new FileOutputStream(outPdfFile));
	 
	 Image img = Image.getInstance(markImagePath);// ����ˮӡ 

	 img.setAbsolutePosition(0, 0);
	 
	 for(int i = 1; i <= pageSize; i++) {
	 
	 PdfContentByte under = stamp.getUnderContent(i);
	 
	 under.addImage(img);
	 }
	 
	 stamp.close();// �ر� 
	 
	 File tempfile = new File(InPdfFile);
	 
	 if(tempfile.exists()) {
	 
	 tempfile.delete();
	 }
	 
	 }
	 
}
