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
	
	private ArrayList <Position> treasuresPos;
	private ArrayList <Position> holesPos;
	
	boolean isWumpusAlive;
	
	Board(){
		boardSize = new Size(); //(0,0) by default
		this.restartBoard();		//Con respecto a lo de arriba, mi idea es hacer un inicializador de matrices
		//que "bebiendo" de todos los datos de los atributos de Board lo cree.	 
		
		wumpusPos = new Position();  //(-1,-1) by default
		startPos = new Position();
		exitPos = new Position();
		
		isWumpusAlive=true;
		
		
		treasuresPos = new ArrayList<Position>();
		holesPos = new ArrayList<Position>();

	}
	
	/*
	 * Initializes an empty board
	 */
	public void restartBoard(){
		boardMatrix = new ArrayList [boardSize.getWidth()][boardSize.getHeight()];
		
		for(int x=0 ; x<boardSize.getWidth() ; x++)
			for(int y=0; y<boardSize.getHeight() ; y++){
				boardMatrix[x][y] = new ArrayList<String>();
				boardMatrix[x][y].add(new String("Empty"));
			}
	}
	
	/*
	 * Returns true if a cell is empty,
	 * false otherways
	 * The position it receives must be VALID in the board
	 */
	public boolean isEmpty(Position pos){
		boolean retorno = false;
		
		if(boardMatrix[pos.getX()][pos.getY()].get(0).toString().contentEquals("Empty"))
			retorno = true;
		
		return retorno;
	}
	
	/*
	 * This function returns true if the position is inside the board
	 * and false otherways
	 */
	public boolean isInsideBoard(Position pos){
		boolean retorno = false;
		
		if (pos.getX()<this.getSize().getWidth() && pos.getX()>=0 &&
				pos.getY()<this.getSize().getHeight() && pos.getY()>=0)
			retorno=true;
		
		return retorno;
		
	}
	
	/*
	 * Writes a position in the board with the desired element
	 * It returns true if everything goes well
	 */
	public boolean writeOnBoard(Position pos, String element){
		boolean retorno = false;
		
		boardMatrix[pos.getX()][pos.getY()].set(0, element);
		retorno = true;
	
		return retorno;
	}
	
	
	/*
	 * Reads a position from the board in the desired position
	 */
	public String readFromBoard(Position pos){
		String element = new String (boardMatrix[pos.getX()][pos.getY()].get(0).toString());
	
		return element;
	}
	
	public Size getSize(){
		return boardSize;
	}
	
	public void setSize(Size newSize){
		boardSize.setWidth(newSize.getWidth());
		boardSize.setHeight(newSize.getHeight());
		this.restartBoard();
	}
	
	public Position getWumpusPos(){
		return wumpusPos;
	}
	
	public void setWumpusPos(Position newPos){
		if(!newPos.equals(wumpusPos)){	//Checks if wumpus is already there
			if(this.isInsideBoard(newPos))
				if(this.isEmpty(newPos)){
					if(writeOnBoard(newPos,"Wumpus")){
						wumpusPos.setX(newPos.getX());
						wumpusPos.setY(newPos.getY());
					}
				}else{
					System.out.println("That position is occupied by: "+this.readFromBoard(newPos));
				}
			else
				System.out.println("That position is out of the board");
		}
	}
	
	public Position getStartPos(){
		return startPos;
	}
	
	public void setStartPos(Position newPos){
		if(!newPos.equals(startPos)){	//Checks if startPos is already there
			if(this.isInsideBoard(newPos))
				if(this.isEmpty(newPos)){
					if(writeOnBoard(newPos,"Start")){
						startPos.setX(newPos.getX());
						startPos.setY(newPos.getY());
					}
				}else{
					System.out.println("That position is occupied by: "+this.readFromBoard(newPos));
				}
			else
				System.out.println("That position is out of the board");
		}
	}
	
	public Position getExitPos(){
		return exitPos;
	}
	
	public void setExitPos(Position newPos){
		exitPos.setX(newPos.getX());
		exitPos.setY(newPos.getY());
	}
	
	//It may be good to change this name
	//It returns the position where the treasure was storaged
	public int setTreasurePos(Position newPos){
		Position treasure = new Position(newPos.getX(),newPos.getY());
		treasuresPos.add(treasure);
		return treasuresPos.size()-1;
	}
	
	public Position getTreasurePos(int treasureNo){
		return treasuresPos.get(treasureNo);
	}
	
	//We still didn't decide to put this, but I think it's good
	public void editTreasurePos(int treasureNo, Position newPos){
		Position treasure = new Position(newPos.getX(),newPos.getY());
		treasuresPos.set(treasureNo, treasure);
	}
	
	//It may be good to change this name
	//It returns the position where the treasure was storaged
	public int setHolePos(Position newPos){
		Position hole = new Position(newPos.getX(),newPos.getY());
		holesPos.add(hole);
		return holesPos.size()-1;
	}
	
	public Position getHolePos(int holeNo){
		return holesPos.get(holeNo);
	}
	
	//We still didn't decide to put this, but I think it's good
	public void editHolePos(int holeNo, Position newPos){
		Position hole = new Position(newPos.getX(),newPos.getY());
		holesPos.set(holeNo, hole);
	}
	
	public int getTotalTreasures(){
		return treasuresPos.size();
	}
	
	public int getNumberOfHoles(){
		return holesPos.size();
	}
	
	public Position getMeccaPos(){
		return mecca.getPos();
	}
	
	public void setMeccaPos(Position newPos){
		mecca.setPos(newPos);
	}
	
	public int getMeccaNArrows(){
		return mecca.getNArrows();
	}
	
	public void setMeccaNArrows(int nArrows){
		mecca.setNArrows(nArrows);
	}
	
	public void incMeccaNArrows(int inc){
		mecca.incNarrows(inc);
	}
	
	public void decMeccaNArrows(int dec){
		mecca.decNarrows(dec);
	}
	
	public String meccaGoUp(){
		String retorno = new String();
		//Check if Mecca Can go up
		Position newPos = mecca.getPos();
		newPos.setY(newPos.getY()+1);
		mecca.setPos(newPos);
		//Return message with information
		return retorno;
	}
	
	public String meccaGoDown(){
		String retorno = new String();
		//Check if Mecca Can go down
		Position newPos = mecca.getPos();
		newPos.setY(newPos.getY()-1);
		mecca.setPos(newPos);
		//Return message with information
		return retorno;
	}
	
	public String meccaGoLeft(){
		String retorno = new String();
		//Check if Mecca Can go left
		Position newPos = mecca.getPos();
		newPos.setX(newPos.getX()-1);
		mecca.setPos(newPos);
		//Return message with information
		return retorno;
	}
	
	public String meccaGoRight(){
		String retorno = new String();
		//Check if Mecca Can go right
		Position newPos = mecca.getPos();
		newPos.setX(newPos.getX()+1);
		mecca.setPos(newPos);
		//Return message with information
		return retorno;
	}
	
	public String meccaShoot(int direction){ //1 UP 2 RIGHT 3 LEFT 4 DOWN
		String retorno = new String();
		//Check if Mecca Can shoot
		if(mecca.getNArrows()>0){
			//Check shooting result
			mecca.decNarrows();
		}
		//Return message with information
		return retorno;
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
