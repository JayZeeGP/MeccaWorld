/**
 * Clase que representa al aventurero del juego. Permite guardar y modificar información sobre 
 * el número de flechas que tiene, su posición, y una lista de los tesoros ganados.
 * @author Rubén Salado y José Carlos Garrido
 */

public class Mecca {
	/*
	 * Variable privada de tipo entero que almacena el número de flechas que tiene Mecca.
	 */
	private int nArrows;
	/*
	 * Variable privada de tipo Position que almacena la posición de Mecca en el tablero. 
	 */
	private Position position;
	
	/*
	 * Constructor vacío de la clase. Inicializará la posición con el constructor vacío de Position y
	 * el número de flechas a 0, así como los tesoros conseguidos.
	 */
	public Mecca(){
		position = new Position();
		nArrows = 0;
	}
	
	/*
	 * Función que devuelve un entero que contiene el número de flechas que le quedan a Mecca.
	 */
	public int getNArrows(){
		return nArrows;
	}
	
	/*
	 * Función que recibe un entero que actualizará el valor de la variable nArrows.
	 */
	public void setNArrows(int newArrows){
		if(newArrows >= 0) {
			nArrows = newArrows;
		} else {
			nArrows = 0;
		}
	}
	
	/*
	 * Función que incrementa, en el valor entero que reciba como parámetro, el valor de nArrows.
	 */
	public void incNarrows(int inc){
		nArrows+=inc;
	}
	
	/*
	 * Función que decrementa, en el valor entero que reciba como parámetro, el valor de nArrows.
	 */
	public void decNarrows(int dec){
		if(nArrows-dec >= 0) {
			nArrows-=dec;
		} else {
			nArrows = 0;
		}
	}
	
	/*
	 * Función que incrementa nArrows una unidad.
	 */
	public void incNarrows(){
		nArrows++;
	}
	
	/*
	 * Función que decrementa nArrows una unidad.
	 */
	public void decNarrows(){
		if(nArrows > 0) {
			nArrows--;
		}
	}
	
	/*
	 * Función que devuelve un objeto de tipo Position que contiene la posición de Mecca.
	 */
	public Position getPos(){
		return position;
	}
	
	/*
	 * Función que recibe un objeto de tipo Position que será la nueva posición de Mecca.
	 */
	public void setPos(Position newPos){
		position.setX(newPos.getX());
		position.setY(newPos.getY());
	}
	

}
