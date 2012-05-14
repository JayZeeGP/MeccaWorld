import java.util.ArrayList;


public class Board {
	public static final String WUMPUS = "Wumpus";
	public static final String TREASURE = "Treasure";
	public static final String HOLE = "Hole";
	public static final String BREEZE = "Breeze";
	public static final String SMELL = "Smell";
	public static final String START = "Start";
	public static final String EXIT = "Exit";
	public static final String EMPTY = "Empty"; // No Wumpus, no hole, no treasure, no start, no exit
	
	//private ArrayList <Character> [][] boardMatrix;	
	private ArrayList<String> [][] boardMatrix;
	private boolean[][] boardMatrixVisited;
	private Size boardSize;
	
	public Mecca mecca = new Mecca();
	
	private Position wumpusPos;
	private Position startPos;
	private Position exitPos;
	
	private ArrayList <Position> treasuresPos;
	private ArrayList <Position> holesPos;
	
	private boolean isWumpusAlive;
	private boolean endOfGame;
	
	public Board(){
		boardSize = new Size(); //(0,0) by default
		this.restartBoard(); 
		
		wumpusPos = new Position();  //(-1,-1) by default
		startPos = new Position();
		exitPos = new Position();
		
		setWumpusAlive(false);
		
		treasuresPos = new ArrayList<Position>();
		holesPos = new ArrayList<Position>();

	}
	
	/*
	 * Initializes an empty board
	 */
	@SuppressWarnings("unchecked")
	public void restartBoard(){
		boardMatrix = new ArrayList [getSize().getWidth()][getSize().getHeight()];
		boardMatrixVisited = new boolean [getSize().getWidth()][getSize().getHeight()];
		
		for(int x=0 ; x<getSize().getWidth() ; x++) {
			for(int y=0; y<getSize().getHeight() ; y++){
				boardMatrix[x][y] = new ArrayList<String>();
				boardMatrix[x][y].add(new String(EMPTY));
				
				boardMatrixVisited[x][y] = false;
			}
		}
	}
	
	/*
	 * Returns true if a square is empty,
	 * false otherways
	 * The position it receives must be VALID in the board
	 */
	public boolean isEmpty(Position pos){
		boolean empty = false;
		
		if(readFromBoard(pos).contains(EMPTY))
			empty = true;
		
		return empty;
	}
	
	/*
	 * This function returns true if the position is inside the board
	 * and false otherways
	 */
	public boolean isInsideBoard(Position pos){
		boolean inside = false;
		
		if (pos.getX()<this.getSize().getWidth() && pos.getX()>=0 &&
				pos.getY()<this.getSize().getHeight() && pos.getY()>=0)
			inside=true;
		
		return inside;
		
	}
	
	/*
	 * Writes a position in the board with the desired element
	 * It returns true if everything goes well
	 */
	public boolean writeOnBoard(Position pos, String element){
		boolean write = false;
		
		readFromBoard(pos).add(element);
		write = true;
	
		return write;
	}
	
	
	/*
	 * Reads a position from the board in the desired position
	 */
	public ArrayList<String> readFromBoard(Position pos){
		return boardMatrix[pos.getX()][pos.getY()];
	}
	
	/*
	 * Removes from board the given element in the given position
	 */
	private boolean removeFromBoard(String element, Position position) {
		boolean remove = false;
		
		if(position.getX() >= 0 && position.getY() >= 0) {
			ArrayList<String> elements = readFromBoard(position);
			
			if(elements.contains(element)) {
				if(elements.remove(element)) {
					remove = true;
					
					if(!element.equals(EMPTY) && !element.equals(BREEZE) && !element.equals(SMELL)) {
						writeOnBoard(position, EMPTY);
					}
				}
			}
		}
		
		return remove;
	}
	
	private boolean readFromBoardVisited(Position pos) {
		return boardMatrixVisited[pos.getX()][pos.getY()];
	}
	
	private void writeOnBoardVisited(Position pos, boolean element) {
		boardMatrixVisited[pos.getX()][pos.getY()] = element;
	}
	
	public Size getSize(){
		return boardSize;
	}
	
	public void setSize(Size newSize){
		getSize().setWidth(newSize.getWidth());
		getSize().setHeight(newSize.getHeight());
		this.restartBoard();
	}
	
	public Position getWumpusPos(){
		return wumpusPos;
	}
	
	public boolean setWumpusPos(Position newPos){
		boolean success = false;
		
		if(getWumpusPos().getX() != -1 && getWumpusPos().getY() != -1) { // Wumpus already exists
			removeWumpus();
		}
		if(this.isInsideBoard(newPos)) {
			if(this.isEmpty(newPos)) {
				// Set Wumpus
				if(writeOnBoard(newPos,WUMPUS)){
					getWumpusPos().setX(newPos.getX());
					getWumpusPos().setY(newPos.getY());
					
					removeFromBoard(EMPTY, newPos);
					setWumpusAlive(true);
					
					// Set Smell
					Position smell = new Position();
					for(int i=-1; i<2; i++) {
						smell.setX(newPos.getX());						
						smell.setX(smell.getX()+i);
						for(int j=-1; j<2; j++) {
							smell.setY(newPos.getY());
							smell.setY(smell.getY()+j);
							
							if(isInsideBoard(smell)) {
								writeOnBoard(smell, SMELL);
							}
						}
					}
					
					success = true;
				}
			}else{
				System.out.println("That position is occupied by: "+this.readFromBoard(newPos));
			}
		} else {
			System.out.println("That position is out of the board");
		}
		
		return success;
	}
	
	private boolean removeWumpus() {
		boolean success = false;
		
		if(getWumpusPos().getX() != -1 && getWumpusPos().getY() != -1) {
			if(removeFromBoard(WUMPUS, getWumpusPos())) {
				// Remove Smell
				Position smell = new Position();
				for(int i=-1; i<2; i++) {
					smell.setX(getWumpusPos().getX());						
					smell.setX(smell.getX()+i);
					for(int j=-1; j<2; j++) {
						smell.setY(getWumpusPos().getY());
						smell.setY(smell.getY()+j);
						
						if(isInsideBoard(smell)) {
							removeFromBoard(SMELL, smell);
						}
					}
				}
				
				// Remove Wumpus
				getWumpusPos().setX(-1);
				getWumpusPos().setY(-1);
				
				success = true;
			}
		}
		
		return success;
	}
	
	public Position getStartPos(){
		return startPos;
	}
	
	public boolean setStartPos(Position newPos){
		boolean success = false;
		
		if(getStartPos().getX() != -1 && getStartPos().getY() != -1) { // Start already exists
			removeFromBoard(START, getStartPos());
		}
		
		if(this.isInsideBoard(newPos)) {
			if(this.isEmpty(newPos)){
				if(writeOnBoard(newPos,START)){
					getStartPos().setX(newPos.getX());
					getStartPos().setY(newPos.getY());
					
					removeFromBoard(EMPTY, newPos);
					
					success = true;
				}
			}else{
				System.out.println("That position is occupied by: "+this.readFromBoard(newPos));
			}
		} else {
			System.out.println("That position is out of the board");
		}
		
		return success;
	}
	
	public Position getExitPos(){
		return exitPos;
	}
	
	public boolean setExitPos(Position newPos) {
		boolean success = false;
		
		if(getExitPos().getX() != -1 && getExitPos().getY() != -1) { // Exit already exists
			removeFromBoard(EXIT, getExitPos());
		}
		
		if(isInsideBoard(newPos)) {
			if(isEmpty(newPos)) {
				if(writeOnBoard(newPos, EXIT)) {
					getExitPos().setX(newPos.getX());
					getExitPos().setY(newPos.getY());
					
					removeFromBoard(EMPTY, newPos);
					
					success = true;
				}
			}
		}
		
		return success;
	}
	
	//It may be good to change this name
	public int setTreasurePos(Position newPos) {
		int nTreasure = -1;
	
		if(isInsideBoard(newPos)) {
			if(isEmpty(newPos)) {
				if(writeOnBoard(newPos, TREASURE)) {
					Position treasure = new Position(newPos.getX(),newPos.getY());
					getTreasuresPos().add(treasure);
					
					removeFromBoard(EMPTY, treasure);
					
					nTreasure = getTotalTreasures()-1;
				}
			}				
		}
			
		return nTreasure;
	}
	
	public Position getTreasurePos(int treasureNo){
		return getTreasuresPos().get(treasureNo);
	}
	
	public ArrayList<Position> getTreasuresPos() {
		return treasuresPos;
	}
	
	//Displays a message with the position of every hole
	public void showTreasures() {
		if(treasuresPos.size()!=0) {
			for(int i = 0; i< treasuresPos.size() ; i++) {
				System.out.println("Treasure: "+(i+1)+" - "+treasuresPos.get(i).toString());
			}
		} else {
			System.out.println("No treasures defined yet");
		}
	}
	
	//We still didn't decide to put this, but I think it's good
	public void editTreasurePos(int treasureNo, Position newPos) {
		Position treasure = new Position(newPos.getX(),newPos.getY());
		getTreasuresPos().set(treasureNo, treasure);
	}
	
	public boolean removeTreasure(int treasureNumber) {
		boolean success = false;
		
		if(treasureNumber <= getTreasuresPos().size()) {	
			removeFromBoard(TREASURE, getHolesPos().get(treasureNumber-1));
			getTreasuresPos().remove(treasureNumber-1);
			
			success = true;
		}
		
		return success;
	}
	
	public boolean removeTreasure(Position position) {
		boolean success = false;
		
		if(isInsideBoard(position)) {
			removeFromBoard(TREASURE, position);
			
			for(int i=0; i<getTreasuresPos().size(); i++) {
				if(position.getX() == getTreasuresPos().get(i).getX() &&
						position.getY() == getTreasuresPos().get(i).getY()) {
					getTreasuresPos().remove(i);
					
					success = true;
				}
			}
		}
		
		return success;
	}
	
	//It may be good to change this name
	public int setHolePos(Position newPos){
		int nHole = -1;
		
		if(isInsideBoard(newPos)) {
			if(isEmpty(newPos)) {
				// Set Hole
				if(writeOnBoard(newPos, HOLE)) {
					Position hole = new Position(newPos.getX(),newPos.getY());
					getHolesPos().add(hole);
					
					removeFromBoard(EMPTY, hole);
					
					nHole = getHolesPos().size()-1;
					
					// Set Breeze
					Position breeze = new Position();
					for(int i=-1; i<2; i++) {
						breeze.setX(newPos.getX());						
						breeze.setX(breeze.getX()+i);
						for(int j=-1; j<2; j++) {
							breeze.setY(newPos.getY());
							breeze.setY(breeze.getY()+j);
							
							if(isInsideBoard(breeze)) {
								writeOnBoard(breeze, BREEZE);
							}
						}
					}
				}
			}
		}
		
		return nHole;
	}
	
	public Position getHolePos(int holeNo){
		return getHolesPos().get(holeNo);
	}
	
	public ArrayList<Position> getHolesPos() {
		return holesPos;
	}
	
	//We still didn't decide to put this, but I think it's good
	public void editHolePos(int holeNo, Position newPos){
		Position hole = new Position(newPos.getX(),newPos.getY());
		getHolesPos().set(holeNo, hole);
	}
	
	public boolean removeHole(int holeNumber) {
		boolean success = false;
		
		if(holeNumber <= getHolesPos().size()) {
			// Remove Breeze
			Position hole = new Position();
			for(int i=-1; i<2; i++) {
				hole.setX(getHolePos(holeNumber-1).getX());						
				hole.setX(hole.getX()+i);
				for(int j=-1; j<2; j++) {
					hole.setY(getHolePos(holeNumber-1).getY());
					hole.setY(hole.getY()+j);
					
					if(isInsideBoard(hole)) {
						removeFromBoard(BREEZE, hole);
					}
				}
			}
			
			// Remove Hole
			removeFromBoard(HOLE, getHolesPos().get(holeNumber-1));
			getHolesPos().remove(holeNumber-1);
			
			success = true;
		}
		
		return success;
	}
	
	
	//Displays a message with the position of every hole
	public void showHoles() {
		if(holesPos.size()!=0) {
			for(int i = 0; i< holesPos.size() ; i++) {
				System.out.println("Hole: "+(i+1)+" - "+holesPos.get(i).toString());
			} 
		} else {
				System.out.println("No holes defined yet");
		}
	}

	public int getTotalTreasures(){
		return getTreasuresPos().size();
	}

	public int getNumberOfHoles(){
		return getHolesPos().size();
	}
	
	public Mecca getMecca() {
		return mecca;
	}

	public Position getMeccaPos(){
		return getMecca().getPos();
	}
	
	public void setMeccaPos(Position newPos){
		getMecca().setPos(newPos);
	}
	
	public int getMeccaNArrows(){
		return getMecca().getNArrows();
	}
	
	public void setMeccaNArrows(int nArrows) {
		getMecca().setNArrows(nArrows);
	}
	
	public void incMeccaNArrows(int inc) {
		getMecca().incNarrows(inc);
	}
	
	public void decMeccaNArrows(int dec) {
		getMecca().decNarrows(dec);
	}
	
	public void meccaGoUp() {
		Position pos = new Position(getMeccaPos().getX(), getMeccaPos().getY()+1);
		
		if(meccaGoPosition(pos)) {
			writeOnBoardVisited(pos, true);
		}
	}
	
	public void meccaGoDown() {
		Position pos = new Position(getMeccaPos().getX(), getMeccaPos().getY()-1);
		
		if(meccaGoPosition(pos)) {
			writeOnBoardVisited(pos, true);			
		}
	}
	
	public void meccaGoLeft() {
		Position pos = new Position(getMeccaPos().getX()-1, getMeccaPos().getY());
		
		if(meccaGoPosition(pos)) {
			writeOnBoardVisited(pos, true);
		}
	}
	
	public void meccaGoRight() {
		Position pos = new  Position(getMeccaPos().getX()+1, getMeccaPos().getY());
		
		if(meccaGoPosition(pos)) {
			writeOnBoardVisited(pos, true);
		}
	}
	
	public boolean meccaShoot(int direction){ //1 UP 2 RIGHT 3 LEFT 4 DOWN
		boolean shoot = false;
		boolean kill = false; 
		
		//Check if Mecca can shoot
		if(getMecca().getNArrows() > 0){
			shoot = true;
			getMecca().decNarrows();
			
			if(direction == 1) {
				if(getMeccaPos().getX() == getWumpusPos().getX()) {
					if(getMeccaPos().getY() < getWumpusPos().getY()) {
						kill = true;
					}
				}
			} else if(direction == 2) {
				if(getMeccaPos().getY() == getWumpusPos().getY()) {
					if(getMeccaPos().getX() < getWumpusPos().getX()) {
						kill = true;
					}
				}
			} else if(direction == 3) {
				if(getMeccaPos().getY() == getWumpusPos().getY()) {
					if(getMeccaPos().getY() > getWumpusPos().getY()) {
						kill = true;
					}
				}
			} else if(direction == 4) {
				if(getMeccaPos().getX() == getWumpusPos().getX()) {
					if(getMeccaPos().getY() > getWumpusPos().getY()) {
						kill = true;
					}
				}
			}
			
			if(kill) {
				setWumpusAlive(false);
				System.out.println("You hear the Wumpus crying, he fainted!");
			} else {
				System.out.println("Ohh! Bad luck! You hit nothing (" + getMeccaNArrows() + " arrows remaining)");
			}
		}
		
		return shoot;
	}
	
	private boolean meccaGoPosition(Position position) {
		boolean success = false;
		
		if(checkMovement(position)) {
			getMeccaPos().setX(position.getX());
			getMeccaPos().setY(position.getY());
			
			success = true;
		}
		
		return success;
	}
	
	private boolean checkMovement(Position position) {
		boolean success = true;
		
		if(isInsideBoard(position)) {
			ArrayList<String> elements = readFromBoard(position);
			
			if(elements.contains(WUMPUS)) {
				success = false;				
				System.out.println("Wumpus killed you... GAME OVER");
				finishGame();
			} else if(elements.contains(HOLE)) {
				success = false;
				System.out.println("You've fallen into a hole... GAME OVER");
				finishGame();
			} else if(elements.contains(TREASURE)) {
				String msg = new String("You found a treasure! (");
				removeTreasure(position);
				
				if(getTotalTreasures() > 1) {
					msg += getTotalTreasures() + " treasures remaining)";
				} else if(getTotalTreasures() == 1) {
					msg += getTotalTreasures() + " treasure remaining)";
				} else {
					msg += "All treasures found!)";
				}
				
				System.out.println(msg);
			}  else if(elements.contains(EXIT)) {
				if(getTotalTreasures() == 0) {
					System.out.println("You win! Congratulations!");
					finishGame();
				} else {
					System.out.println("Treasures are still on the board!");
				}
			} else {
				if(elements.contains(SMELL)) {
					System.out.println("It smells bad... What could it be!?");
				}
				
				if(elements.contains(BREEZE)) {
					System.out.println("You feel a gentle breeze...");
				}
			}
		} else {
			success = false;
			
			System.out.println("You hit a wall.");
		}
		
		return success;
	}
	
	public Size getBoardSize() {
		return boardSize;
	}
	
	public String toString(){
		String returnString = new String();
		
		if(getBoardSize().getHeight() != 0 && getBoardSize().getWidth() != 0) {
			for(int i=getBoardSize().getHeight()-1; i>=0; i--) {
				returnString += "\n";
				for(int j=0; j<getBoardSize().getWidth(); j++) {
					ArrayList<String> square = readFromBoard(new Position(j, i));
					
					returnString += "\t";
					
					if(getMeccaPos().getX() == j && getMeccaPos().getY() == i) {
						returnString += "M";
					} else {
						String element = new String("");
						
						if(square.contains(WUMPUS)) {
							element = "W";
						} else if(square.contains(HOLE)) {
							element = "H";
						} else if(square.contains(TREASURE)) {
							element = "T";
						} else if(square.contains(START)) {
							element = "+";
						} else if(square.contains(EXIT)) {
							element = "X";
						} else if(square.contains(BREEZE) && !square.contains(HOLE) && !square.contains(WUMPUS)
								&& !square.contains(TREASURE) && !square.contains(START) && !square.contains(EXIT)) {
							element = "~";
						} else if(square.contains(SMELL) && !square.contains(HOLE) && !square.contains(WUMPUS)
								&& !square.contains(TREASURE) && !square.contains(START) && !square.contains(EXIT)) {
							element = "=";
						} else {
							element = "-";
						}
						
						returnString += element;
					}
				}
			}
		} else {
			returnString += "There is no board";
		}
		
		return returnString;
	}

	public boolean isWumpusAlive() {
		return isWumpusAlive;
	}

	public void setWumpusAlive(boolean isWumpusAlive) {
		this.isWumpusAlive = isWumpusAlive;
		
		if(!isWumpusAlive) {
			removeWumpus();
		}
	}
	
	public boolean initGame() {
		boolean init = true;
		
		if(getBoardSize().getHeight() == 0 || getBoardSize().getWidth() == 0) {			
			System.out.println("ERROR: Board not defined");
			init = false;
		} else {
			if(!checkStart()) {
				init = false;
				System.out.println("ERROR: Start not defined");
			}
			
			if(!checkExit()) {
				init = false;
				System.out.println("ERROR: Exit not defined");
			}
			
			if(!checkWumpus()) {
				init = false;
				System.out.println("ERROR: Wumpus not defined");
			}
			
			if(init) {
				// Set Mecca initial position
				getMeccaPos().setX(getStartPos().getX());
				getMeccaPos().setY(getStartPos().getY());
				
				writeOnBoardVisited(getStartPos(), true);
			}
		}
		
		return init;
	}
	
	public void finishGame() {
		endOfGame = true;
	}
	
	public boolean isGameFinished() {
		return endOfGame;
	}
	
	private boolean checkStart() {
		boolean start = true;
		
		if(getStartPos().getX() == -1 && getStartPos().getY() == -1) {
			start = false;
		}
		
		return start;
	}
	
	private boolean checkExit() {
		boolean exit = true;
		
		if(getExitPos().getX() == -1 && getExitPos().getY() == -1) {
			exit = false;
		}
		
		return exit;
	}
	
	private boolean checkWumpus() {
		boolean wumpus = true;
		
		if(getWumpusPos().getX() == -1 && getWumpusPos().getY() == -1) {
			wumpus = false;
		}
		
		return wumpus;
	}
	
	public void showAdventureState() {
		String returnString = new String();
		
		if(getBoardSize().getHeight() != 0 && getBoardSize().getWidth() != 0) {
			for(int i=getBoardSize().getHeight()-1; i>=0; i--) {
				returnString += "\n";
				for(int j=0; j<getBoardSize().getWidth(); j++) {
					ArrayList<String> square = readFromBoard(new Position(j, i));
					
					returnString += "\t";
					
					if(getMeccaPos().getX() == j && getMeccaPos().getY() == i) {
						returnString += "M";
					} else {
						String element = new String("");
						
						if(readFromBoardVisited(new Position(j, i))) {
							if(square.contains(WUMPUS)) {
								element = "W";
							} else if(square.contains(HOLE)) {
								element = "H";
							} else if(square.contains(TREASURE)) {
								element = "T";
							} else if(square.contains(START)) {
								element = "+";
							} else if(square.contains(EXIT)) {
								element = "X";
							} else if(square.contains(BREEZE) && !square.contains(HOLE) && !square.contains(WUMPUS)
									&& !square.contains(TREASURE) && !square.contains(START) && !square.contains(EXIT)) {
								element = "~";
							} else if(square.contains(SMELL) && !square.contains(HOLE) && !square.contains(WUMPUS)
									&& !square.contains(TREASURE) && !square.contains(START) && !square.contains(EXIT)) {
								element = "=";
							} else {
								element = "-";
							}
						} else {
							element = "?";
						}
						
						returnString += element;
					}
				}
			}
			

			returnString += "\n\nArrows: " + getMeccaNArrows();
			returnString += "\nWumpus: ";
			
			if(isWumpusAlive()) {
				returnString += "Alive";
			} else {
				returnString += "Dead";
			}
			
			returnString += "\nTreasures remaining: " + getTotalTreasures();
		} else {
			returnString += "There is no board";
		}
		
		System.out.println(returnString);
	}

}
