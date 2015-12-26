package entity;

import java.io.File;

/**
 * @author zzx
 * 水印图片 icon
 */
public class ImgIconDTO {
	/**文件路径*/
	private File file;
	/**x偏移坐标*/
	private int deviationX;
	/**y偏移坐标*/
	private int deviationY;
	
	
	public ImgIconDTO(){
		
	}
	
	public ImgIconDTO(File file, int deviationX, int deviationY){
		this.file = file;
		this.deviationX = deviationX;
		this.deviationY = deviationY;
	}
	
	
	public File getFile() {
		return file;
	}
	public void setFile(File file) {
		this.file = file;
	}
	public int getDeviationX() {
		return deviationX;
	}
	public void setDeviationX(int deviationX) {
		this.deviationX = deviationX;
	}
	public int getDeviationY() {
		return deviationY;
	}
	public void setDeviationY(int deviationY) {
		this.deviationY = deviationY;
	}
}
