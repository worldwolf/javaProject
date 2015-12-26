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
		String imagePath ="D:\\cacheFile\\aa\\1449751184883.jpg";  //Ҫ�����ͼƬ
		PdfReader reader = new PdfReader("C:\\1.pdf");     //��ȡ���е�PDF�ĵ�
		String filename = "C:\\1a.pdf";
		int n = reader.getNumberOfPages();    //��ȡҳ��        
		Rectangle psize = reader.getPageSizeWithRotation(1);      //��ȡ��һҳ      
		float width = psize.getWidth();            
		float height = psize.getHeight();     
		Document document = new Document(psize, 50, 50, 50, 50);     //����λ��       
		PdfWriter writer = PdfWriter.getInstance(document,new FileOutputStream(filename));            
		document.open();            
		Image img = Image.getInstance(imagePath);           //����ͼƬ
		img.setAbsolutePosition(440, 800);            
		writer.add(img);         //���ͼƬ   
		document.close();
	}
}
