
public class Position {
	private int x;
	private int y;
	
	Position(){
		x=-1;
		y=-1;
	}

	Position(int newX, int newY){
		x=newX;
		y=newY;
	}
	
	Position(Position copyPos){
		x=copyPos.getX();
		y=copyPos.getY();
	}
	
	public void setX(int newX){
		x=newX;
	}
	
	public void setY(int newY){
		y=newY;
	}
	
	public int getX(){
		return x;
	}
	
	public int getY(){
		return y;
	}
	
	public String toString(){
		return new String("Position:\tx="+x+" \ty="+y);
	}
	
	public boolean equals(Position newPos){
		boolean retorno = false;
		
		if(newPos.getX()==x && newPos.getY()==y)
			retorno = true;
		
		return retorno;
	}

}
