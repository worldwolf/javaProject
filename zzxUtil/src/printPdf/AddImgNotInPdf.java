package printPdf;

import java.io.FileNotFoundException;
import java.io.FileOutputStream;

import com.lowagie.text.Document;
import com.lowagie.text.Image;
import com.lowagie.text.Rectangle;
import com.lowagie.text.pdf.PdfReader;
import com.lowagie.text.pdf.PdfWriter;

public class AddImgNotInPdf {
	public static void main(String[] args) throws Exception {
		String imagePath ="D:\\cacheFile\\aa\\1449751184883.jpg";  //要插入的图片
		PdfReader reader = new PdfReader("C:\\1.pdf");     //读取现有的PDF文档
		String filename = "C:\\1a.pdf";
		int n = reader.getNumberOfPages();    //获取页码        
		Rectangle psize = reader.getPageSizeWithRotation(1);      //获取第一页      
		float width = psize.getWidth();            
		float height = psize.getHeight();     
		Document document = new Document(psize, 50, 50, 50, 50);     //设置位置       
		PdfWriter writer = PdfWriter.getInstance(document,new FileOutputStream(filename));            
		document.open();            
		Image img = Image.getInstance(imagePath);           //插入图片
		img.setAbsolutePosition(440, 800);            
		writer.add(img);         //添加图片   
		document.close();
	}
}
