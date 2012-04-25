
public class Size {
	private int width;
	private int height;
	
	Size(){
		width=0;
		height=0;
	}

	Size(int newWidth, int newHeight){
		width=newWidth;
		height=newHeight;
	}
	
	Size(Size copyPos){
		width=copyPos.getWidth();
		height=copyPos.getHeight();
	}
	
	public void setWidth(int newWidth){
		width=newWidth;
	}
	
	public void setHeight(int newHeight){
		height=newHeight;
	}
	
	public int getWidth(){
		return width;
	}
	
	public int getHeight(){
		return height;
	}
	
	public String toString(){
		return new String("Size:\n\tWidth="+width+" \tHeight="+height);
	}
}
