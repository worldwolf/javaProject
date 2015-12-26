package printPdf;
import java.io.File;
import java.io.FileOutputStream;
import com.lowagie.text.Document;
import com.lowagie.text.Font;
import com.lowagie.text.Image;
import com.lowagie.text.Paragraph;
import com.lowagie.text.pdf.BaseFont;
import com.lowagie.text.pdf.PdfWriter;
 
public class WriterPDF3 {
 public static void main(String[] args){
  WriterPDF3 pdf = new WriterPDF3();
  Document document = new Document();
  try{
  PdfWriter.getInstance(document,new FileOutputStream("c:\\two2.pdf"));
  document.open();
  
  pdf.findFiles(document,"D:\\cacheFile\\aa");
  
//写入中文件
  BaseFont bf = BaseFont.createFont("STSong-Light","UniGB-UCS2-H",BaseFont.NOT_EMBEDDED);
  Font fontChine = new Font(bf,12,Font.NORMAL);
  Paragraph pa = new Paragraph("你好呀....",fontChine);
  document.add(pa);
  }catch(Exception e){}
  finally{
   document.close();
  }
  
  
 }
 
/**
  * 遍历目录中的文件
  * @param doc
  * @param dir
  */
 public void findFiles(Document doc,String dir){
  File fileDir = new File(dir);
  if(fileDir.exists()){
   File[] files = fileDir.listFiles();
   for(int i = 0; i < files.length; i++){
    File file = files[i];
    System.out.println("FileName="+dir+"//"+file.getName());
    this.addImage(doc,dir+"//"+file.getName());
   }
  }
 }
 
 /**
  * 出成图片
  * @param path
  * @return
  */
 public Image addImage(Document doc,String path){
  Image image = null;
  try {
   image = Image.getInstance(path);
   //image.scalePercent(50);
   image.scaleAbsolute(200, 300);
   doc.add(image);
   
  } catch (Exception e) {
   e.printStackTrace();
  } 
  return image;
  
 }
 

}
