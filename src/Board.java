import java.util.ArrayList;


public class Board {
	//W = Wumpus, T = Treasure, H = Hole, B = Breeze, S = Smell, + = StartPos, - = endPos
	//private ArrayList <Character> [][] boardMatrix;
	private ArrayList [][] boardMatrix;
	private Size boardSize;
	
	private Position wumpusPos;
	private Position startPos;
	private Position endPos;
	
	private ArrayList <Position> treasuresPos;
	private ArrayList <Position> holesPos;
	
	boolean isWumpusAlive;
	
	Board(){
		boardSize = new Size(); //(0,0) by default
		boardMatrix = new ArrayList [boardSize.getWidth()][boardSize.getHeight()];
		//Con respecto a lo de arriba, mi idea es hacer un inicializador de matrices
		//que "bebiendo" de todos los datos de los atributos de Board lo cree. 
		
		wumpusPos=new Position();  //(1,1) by default
		startPos=new Position();
		endPos=new Position();
		
		isWumpusAlive=true;
		
		
		treasuresPos = new ArrayList<Position>();
		holesPos = new ArrayList<Position>();

	}
	
	

}
