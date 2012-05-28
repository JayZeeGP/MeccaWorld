import java.util.ArrayList;

/**
 * Clase que representa al tablero del juego. Permite guardar y modificar información sobre 
 * qué hay en cada casilla y mover a Mecca por todo el tablero. Gestiona todas las acciones que 
 * pueden ocurrir, además de los distintos resultados que puede tener la partida.
 * @author Rubén Salado y José Carlos Garrido
 */

public class Board {
	/*
	 * Variables estáticas que contienen los distintos valores que puede albergar una casilla
	 * para representar lo que en ella hay dentro del juego.
	 */
	public static final String WUMPUS = "Wumpus";
	public static final String TREASURE = "Treasure";
	public static final String HOLE = "Hole";
	public static final String BREEZE = "Breeze";
	public static final String SMELL = "Smell";
	public static final String START = "Start";
	public static final String EXIT = "Exit";
	public static final String EMPTY = "Empty"; // No Wumpus, no hole, no treasure, no start, no exit
	
	/*
	 * Matriz de ArrayLists de cadenas que contiene, para cada casilla, una lista de cadenas con los 
	 * elementos que existen en ella.	
	 */
	private ArrayList<String> [][] boardMatrix;
	/*
	 * Matriz booleana, del mismo tamaño que el tablero, que controla qué casillas han sido visitadas 
	 * y cuáles no. Es utilizada para mostrar por pantalla sólo la información de las casillas ya 
	 * visitadas y que el resto del tablero sea un misterio.
	 */
	private boolean[][] boardMatrixVisited;
	/*
	 * Atributo de tipo Size que guarda el tamaño del tablero.
	 */
	private Size boardSize;
	
	/*
	 * Atributo de tipo Mecca que guarda toda la información del aventurero.
	 */
	public Mecca mecca = new Mecca();
	
	/*
	 * Atributo de tipo Position que guarda información sobre la posición del Wumpus.
	 */
	private Position wumpusPos;
	
	/*
	 * Atributo de tipo Position que guarda información sobre la posición de la casilla de comienzo.
	 */
	private Position startPos;
	
	/*
	 * Atributo de tipo Position que guarda información sobre la posición de la casilla de salida.
	 */
	private Position exitPos;
	
	/*
	 * ArrayList de posiciones que contiene la posición de los tesoros existentes en el tablero.
	 */
	private ArrayList <Position> treasuresPos;
	
	/*
	 * ArrayList de posiciones que contiene la posición de los hoyos existentes en el tablero.
	 */
	private ArrayList <Position> holesPos;
	
	/*
	 * Atributo booleano que será true si el Wumpus está vivo y false en caso contrario.
	 */
	private boolean isWumpusAlive;
	/*
	 * Atributo booleano que será true si el juego ha terminado ya y falso en caso contrario.
	 */
	private boolean endOfGame;
	
	/*
	 * Constructor vacío de la clase Board. Por defecto todos los tamaños y posiciones son 
	 * inicializados con sus respectivos constructores vacíos.
	 */
	public Board(){
		boardSize = new Size(); //(0,0) by default
		this.restartBoard(); 
		endOfGame = false;
		
		wumpusPos = new Position();  //(-1,-1) by default
		startPos = new Position();
		exitPos = new Position();
		
		setWumpusAlive(false);
		
		treasuresPos = new ArrayList<Position>();
		holesPos = new ArrayList<Position>();

	}
	
	/*
	 * Inicializa el tablero vacío
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
	
	/*
	 * Función privada que devuelve verdadero si la posición que se le pasa como parámetro 
	 * ya ha sido visitada por Mecca y falso en caso contrario.
	 */
	private boolean readFromBoardVisited(Position pos) {
		return boardMatrixVisited[pos.getX()][pos.getY()];
	}
	
	/*
	 * Función privada que escribe (en la matriz de casillas visitadas) en la posición 
	 * que se le pasa como primer parámetro el booleano que se le pasa como segundo parámetro.
	 */
	private void writeOnBoardVisited(Position pos, boolean element) {
		boardMatrixVisited[pos.getX()][pos.getY()] = element;
	}
	
	/*
	 * Función que devuelve el tamaño del tablero en una variable de tipo Size.
	 */
	public Size getSize() {
		return boardSize;
	}
	
	/*
	 * Función que recibe como parámetro una variable de tipo Size y hace que este sea el nuevo tamaño 
	 * del tablero, además de reiniciarlo.
	 */
	public void setSize(Size newSize) {
		getSize().setWidth(newSize.getWidth());
		getSize().setHeight(newSize.getHeight());
		this.restartBoard();
	}
	
	/*
	 * Función que devuelve una variable de tipo Position con la posición del Wumpus.
	 */
	public Position getWumpusPos(){
		return wumpusPos;
	}
	
	/*
	 * Función que coloca el Wumpus en la posición que recibe como parámetro. Devolverá true si 
	 * todo funciona y falso en caso contrario.
	 */
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
	
	/*
	 * Función que borra al Wumpus del tablero, eliminado también su olor.
	 */
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
	
	/*
	 * Función que devuelve una variable de tipo Position que contiene la posición de la 
	 * casilla de comienzo.
	 */
	public Position getStartPos(){
		return startPos;
	}
	
	/*
	 * Función que coloca, si es posible, la casilla de comienzo en la posición que se le pasa como 
	 * parámetro. Devolverá true si todo va bien y false en caso contrario.
	 */
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
	
	/*
	 * Función que devuelve una variable de tipo Position que contiene la posición de la 
	 * casilla de salida.
	 */	 
	public Position getExitPos(){
		return exitPos;
	}
	
	/*
	 * Función que coloca, si es posible, la casilla de salida en la posición que se le pasa como 
	 * parámetro. Devolverá true si todo va bien y false en caso contrario.
	 */
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
	
	/*
	 * Función que permite añadir tesoros a la lista de tesoros en la posición que 
	 * se le pase como parámetro. Devuelve un entero que contiene la posición en la que se 
	 * encuentra el tesoro en el vector.
	 */
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
	
	/*
	 * Función que devuelve la posición en el trablero del tesoro que se encuentra en la 
	 * posición de la lista que indica el entero que recibe como parámetro. 
	 */
	public Position getTreasurePos(int treasureNo) {
		Position returnedValue = null;
		
		if(treasureNo<getTreasuresPos().size()) {
			returnedValue = getTreasuresPos().get(treasureNo);
		}
		
		return returnedValue;
	}
	
	/*
	 * Función que devuelve toda la lista de los tesoros con sus posiciones.
	 */
	public ArrayList<Position> getTreasuresPos() {
		return treasuresPos;
	}
	
	/*
	 * Función que muestra un mensaje por consola que indica la posición de cada tesoro.
	 */
	public void showTreasures() {
		if(treasuresPos.size()!=0) {
			for(int i = 0; i< treasuresPos.size() ; i++) {
				System.out.println("Treasure: "+(i+1)+" - "+treasuresPos.get(i).toString());
			}
		} else {
			System.out.println("No treasures defined yet");
		}
	}
	
	/*
	 * Función que permite editar la posición del tesoro cuya posición en el vector se le pasa 
	 * como primer parámetro. La posición que se le pondrá es la indicada por el segundo parámetro.
	 */
	public void editTreasurePos(int treasureNo, Position newPos) {
		Position treasure = new Position(newPos.getX(),newPos.getY());
		getTreasuresPos().set(treasureNo, treasure);
	}
	
	/*
	 * Función que borra de la lista de tesoros aquél cuya posición en esa lista sea la que 
	 * se le pasa como parámetro.
	 */
	public boolean removeTreasure(int treasureNumber) {
		boolean success = false;
		
		if(treasureNumber <= getTreasuresPos().size()) {	
			removeFromBoard(TREASURE, getHolesPos().get(treasureNumber-1));
			getTreasuresPos().remove(treasureNumber-1);
			
			success = true;
		}
		
		return success;
	}
	
	/*
	 * Borra de la lista de tesoros el que se encuentre en la posición del tablero que se le pase
	 * como parámetro.
	 */
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

	/*
	 * función que permite añadir hoyos a la lista de hoyos en la posición que se le pase como 
	 * parámetro. Devuelve un entero que contiene la posición en la que se encuentra el tesoro 
	 * en la lista.
	 */
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
	
	/*
	 * Función que devuelve la posición en el trablero del hoyo que se encuentra 
	 * en la posición de la lista que indica el entero que recibe como parámetro. 
	 */
	public Position getHolePos(int holeNo){
		return getHolesPos().get(holeNo);
	}
	
	/*
	 * Función que devuelve toda la lista de los hoyos con sus posiciones.
	 */
	public ArrayList<Position> getHolesPos() {
		return holesPos;
	}
	
	/*
	 * Función que permite editar la posición del hoyo cuya posición en el vector se le pasa 
	 * como primer parámetro. La posición que se le pondrá es la indicada por el segundo parámetro.
	 */
	public void editHolePos(int holeNo, Position newPos){
		Position hole = new Position(newPos.getX(),newPos.getY());
		getHolesPos().set(holeNo, hole);
	}
	
	/*
	 * Función que borra de la lista de hoyos aquél cuya posición en esa lista sea la que 
	 * se le pasa como parámetro.
	 */
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
	
	
	/*
	 * Función que muestra un mensaje por consola que indica la posición de cada hoyo.
	 */
	public void showHoles() {
		if(holesPos.size()!=0) {
			for(int i = 0; i< holesPos.size() ; i++) {
				System.out.println("Hole: "+(i+1)+" - "+holesPos.get(i).toString());
			} 
		} else {
				System.out.println("No holes defined yet");
		}
	}

	/*
	 * Función que devuelve un entero con la cantidad de tesoros existentes.
	 */
	public int getTotalTreasures(){
		return getTreasuresPos().size();
	}

	/*
	 * Función que devuelve un entero con la cantidad de hoyos existentes.
	 */
	public int getNumberOfHoles(){
		return getHolesPos().size();
	}
	
	/*
	 * Función que devuelve la instancia de la clase Mecca que se contiene en este tablero.
	 */
	public Mecca getMecca() {
		return mecca;
	}
	
	/*
	 * Función que devuelve un objeto de tipo Position que contiene la posición de Mecca.
	 */
	public Position getMeccaPos() {
		return getMecca().getPos();
	}
	
	/*
	 * Función que recibe un objeto de tipo Position que será la nueva posición de Mecca.
	 */
	public void setMeccaPos(Position newPos) {
		getMecca().setPos(newPos);
	}
	
	/*
	 * Función que devuelve un entero que contiene el número de flechas que le quedan a Mecca.
	 */
	public int getMeccaNArrows() {
		return getMecca().getNArrows();
	}
	
	/*
	 * Función que recibe un entero que actualizará el valor del número de flechas que le quedan a Mecca.
	 */
	public void setMeccaNArrows(int nArrows) {
		getMecca().setNArrows(nArrows);
	}
	
	/*
	 * Función que incrementa, en el valor entero que reciba como parámetro, el valor del número de flechas que le quedan a Mecca.
	 */
	public void incMeccaNArrows(int inc) {
		getMecca().incNarrows(inc);
	}

	/*
	 * Función que incrementa, en el valor entero que reciba como parámetro, el valor del número de flechas que le quedan a Mecca.
	 */
	public void decMeccaNArrows(int dec) {
		getMecca().decNarrows(dec);
	}
	
	/*
	 * Función que mueve a Mecca una casilla hacia arriba.
	 */
	public void meccaGoUp() {
		Position pos = new Position(getMeccaPos().getX(), getMeccaPos().getY()+1);
		
		if(meccaGoPosition(pos)) {
			writeOnBoardVisited(pos, true);
		}
	}

	/*
	 * Función que mueve a Mecca una casilla hacia abajo.
	 */
	public void meccaGoDown() {
		Position pos = new Position(getMeccaPos().getX(), getMeccaPos().getY()-1);
		
		if(meccaGoPosition(pos)) {
			writeOnBoardVisited(pos, true);			
		}
	}
	
	/*
	 * Función que mueve a Mecca una casilla hacia la izquierda.
	 */
	public void meccaGoLeft() {
		Position pos = new Position(getMeccaPos().getX()-1, getMeccaPos().getY());
		
		if(meccaGoPosition(pos)) {
			writeOnBoardVisited(pos, true);
		}
	}

	/*
	 * Función que mueve a Mecca una casilla hacia la derecha.
	 */
	public void meccaGoRight() {
		Position pos = new  Position(getMeccaPos().getX()+1, getMeccaPos().getY());
		
		if(meccaGoPosition(pos)) {
			writeOnBoardVisited(pos, true);
		}
	}
	
	/*
	 * Función booleana que permite a Mecca disparar. Devolverá true si acaba con el Wumpus
	 * y false en caso contrario. Recibe un entero que indica la dirección de disparo con los 
	 * siguientes valores:
	 * 		1.- Arriba
	 * 		2.- Derecha
	 * 		3.- Izquierda
	 * 		4.- Abajo
	 */
	public boolean meccaShoot(int direction) { 
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

	/*
	 * Función privada de tipo booleano que coloca a Mecca en la posición que recibe como parámetro.
	 * Devolverá true si el movimiento pudo ser efectuado y falso en caso contrario.
	 */
	private boolean meccaGoPosition(Position position) {
		boolean success = false;
		
		if(checkMovement(position)) {
			getMeccaPos().setX(position.getX());
			getMeccaPos().setY(position.getY());
			
			success = true;
		}
		
		return success;
	}
	
	/*
	 * Función privada de tipo booleano que comprueba el resultado de un movimiento de Mecca.
	 * Devuelve true si puede realizarse y false en caso contrario.
	 * Informa por consola del resultado de ese movimiento.
	 */
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
	
	/*
	 * Función que devuelve un objeto de tipo Size con el tamaño del tablero.
	 */
	public Size getBoardSize() {
		return boardSize;
	}
	
	/*
	 * Función que devuelve una cadena que contiene toda la información relativa al tablero.
	 * (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
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

	/*
	 * Función booleana que devuelve true si el Wumpus está vivo y false en caso contrario.
	 */
	public boolean isWumpusAlive() {
		return isWumpusAlive;
	}

	/*
	 * Función que recibe un booleano que será el que indique si el Wumpus está vivo o no y lo guarda.
	 */
	public void setWumpusAlive(boolean isWumpusAlive) {
		this.isWumpusAlive = isWumpusAlive;
		
		if(!isWumpusAlive) {
			removeWumpus();
		}
	}
	
	/*
	 * Función booleana que comprueba si, al entrar en el modo aventura, el mapa definido 
	 * tiene todo lo necesario para comenzar el juego. En caso afirmativo devuelve true, 
	 * en caso negativo false.
	 */
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
	
	/*
	 * Función booleana que devuelve true si el juego ha terminado y false en caso contrario.
	 */
	public boolean isGameFinished() {
		return endOfGame;
	}
	
	/*
	 * Función booleana que comprueba si la casilla de comienzo ha sido colocada.
	 * Devuelve true en caso de que lo haya sido y false en caso contrario.
	 */
	private boolean checkStart() {
		boolean start = true;
		
		if(getStartPos().getX() == -1 && getStartPos().getY() == -1) {
			start = false;
		}
		
		return start;
	}
	
	/*
	 * Función booleana que comprueba si la casilla de salida ha sido colocada.
	 * Devuelve true en caso de que lo haya sido y false en caso contrario.
	 */
	private boolean checkExit() {
		boolean exit = true;
		
		if(getExitPos().getX() == -1 && getExitPos().getY() == -1) {
			exit = false;
		}
		
		return exit;
	}
	
	/*
	 * Función booleana que comprueba si la casilla del Wupus ha sido colocada.
	 * Devuelve true en caso de que lo haya sido y false en caso contrario.
	 */
	private boolean checkWumpus() {
		boolean wumpus = true;
		
		if(getWumpusPos().getX() == -1 && getWumpusPos().getY() == -1) {
			wumpus = false;
		}
		
		return wumpus;
	}
	
	/*
	 * Función que muestra por consola el estado de la aventura. Es decir, muestra todos los 
	 * elementos que el jugador conoce porque ya ha visitado esas casillas.
	 */
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
