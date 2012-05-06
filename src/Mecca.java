import java.util.ArrayList;


public class Mecca {
	private int nArrows;
	private Position position;
	private ArrayList<Integer> treasuresWon;
	
	public Mecca(){
		position = new Position();
		nArrows = 0;
		treasuresWon = new ArrayList<Integer>();
	}
	
	public int getNArrows(){
		return nArrows;
	}
	
	public void setNArrows(int newArrows){
		if(newArrows >= 0) {
			nArrows = newArrows;
		} else {
			nArrows = 0;
		}
	}
	
	public void incNarrows(int inc){
		nArrows+=inc;
	}
	
	public void decNarrows(int dec){
		if(nArrows-dec >= 0) {
			nArrows-=dec;
		} else {
			nArrows = 0;
		}
	}
	
	public void incNarrows(){
		nArrows++;
	}
	
	public void decNarrows(){
		if(nArrows > 0) {
			nArrows--;
		}
	}
	
	public Position getPos(){
		return position;
	}
	
	public void setPos(Position newPos){
		position.setX(newPos.getX());
		position.setY(newPos.getY());
	}
	

}
