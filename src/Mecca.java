
public class Mecca {
	private int nArrows;
	private Position position;
	
	Mecca(){
		position = new Position();
		nArrows = 0;
	}
	
	public int getNArrows(){
		return nArrows;
	}
	
	public void setNArrows(int newArrows){
		nArrows = newArrows;
	}
	
	public void incNarrows(int inc){
		nArrows+=inc;
	}
	
	public void decNarrows(int dec){
		nArrows-=dec;
	}
	
	public Position getPos(){
		return position;
	}
	
	public void setPos(Position newPos){
		position.setX(newPos.getX());
		position.setY(newPos.getY());
	}
}
