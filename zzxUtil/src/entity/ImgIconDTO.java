package entity;

import java.io.File;

/**
 * @author zzx
 * ˮӡͼƬ icon
 */
public class ImgIconDTO {
	/**�ļ�·��*/
	private File file;
	/**xƫ������*/
	private int deviationX;
	/**yƫ������*/
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
