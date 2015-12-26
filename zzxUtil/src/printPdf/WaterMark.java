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
	 * 给pdf文件添加水印
	 * @param InPdfFile 要加水印的原pdf文件路径
	 * @param outPdfFile 加了水印后要输出的路径
	 * @param markImagePath 水印图片路径
	 * @param pageSize 原pdf文件的总页数（该方法是我当初将数据导入excel中然后再转换成pdf所以我这里的值是用excel的行数计算出来的，如果不是我这种可以	直接用reader.getNumberOfPages()获取pdf的总页数）
	 * @throws Exception
	 */
	 public static void addPdfMark(String InPdfFile, String outPdfFile, String markImagePath, int pageSize) throws Exception {

	 PdfReader reader = new PdfReader(InPdfFile, "PDF".getBytes());
	 pageSize = reader.getNumberOfPages();
	 
	 PdfStamper stamp = new PdfStamper(reader, new FileOutputStream(outPdfFile));
	 
	 Image img = Image.getInstance(markImagePath);// 插入水印 

	 img.setAbsolutePosition(0, 0);
	 
	 for(int i = 1; i <= pageSize; i++) {
	 
	 PdfContentByte under = stamp.getUnderContent(i);
	 
	 under.addImage(img);
	 }
	 
	 stamp.close();// 关闭 
	 
	 File tempfile = new File(InPdfFile);
	 
	 if(tempfile.exists()) {
	 
	 tempfile.delete();
	 }
	 
	 }
	 
}
