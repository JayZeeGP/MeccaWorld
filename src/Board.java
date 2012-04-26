import java.util.ArrayList;


public class Board {
	//W = Wumpus, T = Treasure, H = Hole, B = Breeze, S = Smell, + = StartPos, - = endPos
	//private ArrayList <Character> [][] boardMatrix;	
	private ArrayList [][] boardMatrix;
	private Size boardSize;
	
	public   Mecca mecca = new Mecca();
	
	private Position wumpusPos;
	private Position startPos;
	private Position exitPos;
	private Position meccaPos;
	
	private ArrayList <Position> treasuresPos;
	private ArrayList <Position> holesPos;
	
	boolean isWumpusAlive;
	
	Board(){
		boardSize = new Size(); //(0,0) by default
		boardMatrix = new ArrayList [boardSize.getWidth()][boardSize.getHeight()];
		//Con respecto a lo de arriba, mi idea es hacer un inicializador de matrices
		//que "bebiendo" de todos los datos de los atributos de Board lo cree.	 
		
		wumpusPos = new Position();  //(1,1) by default
		startPos = new Position();
		exitPos = new Position();
		meccaPos = new Position();
		
		isWumpusAlive=true;
		
		
		treasuresPos = new ArrayList<Position>();
		holesPos = new ArrayList<Position>();

	}
	
	public Size getSize(){
		return boardSize;
	}
	
	public void setSize(Size newSize){
		boardSize.setWidth(newSize.getWidth());
		boardSize.setHeight(newSize.getHeight());
	}
	
	public Position getWumpusPos(){
		return wumpusPos;
	}
	
	public void setWumpusPos(Position newPos){
		wumpusPos.setX(newPos.getX());
		wumpusPos.setY(newPos.getY());
	}
	
	public Position getStartPos(){
		return startPos;
	}
	
	public void setStartPos(Position newPos){
		startPos.setX(newPos.getX());
		startPos.setY(newPos.getY());
	}
	
	public Position getExitPos(){
		return exitPos;
	}
	
	public void setExitPos(Position newPos){
		exitPos.setX(newPos.getX());
		exitPos.setY(newPos.getY());
	}
	
	public int getTotalTreasures(){
		return treasuresPos.size();
	}
	
	public int getNumberOfHoles(){
		return holesPos.size();
	}
	
	public String toString(){
		String returnString = new String();
		returnString+="Board information\n";
		returnString+="=================\n";
		returnString+=boardSize.toString();
		returnString+="\nWumpus "+wumpusPos.toString();
		returnString+="\nStart "+startPos.toString();
		returnString+="\nEnd "+exitPos.toString();
		return returnString;
	}

}
