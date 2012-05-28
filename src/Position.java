/**
 * Clase simple que sirve para almacenar una posición en el tablero.
 * @author Rubén Salado y José Carlos Garrido
 *
 */
public class Position {
	/*
	 * Variable privada de tipo entero entero que almacena la posición 
	 * x de una instancia de esta clase.
	 */
	private int x;
	/*
	 * Variable privada de tipo entero entero que almacena la posición 
	 * y de una instancia de esta clase.
	 */	
	private int y;
	
	/*
	 * Constructor vacío de la clase que pondrá la posición a (-1,-1).
	 */
	Position(){
		x=-1;
		y=-1;
	}

	/*
	 * Constructor parametrizado que pondrá la posición en los valores que reciba como parámetros.
	 */
	Position(int newX, int newY){
		x=newX;
		y=newY;
	}
	
	/*
	 * Constructor de copia de la clase Position
	 */
	Position(Position copyPos){
		x=copyPos.getX();
		y=copyPos.getY();
	}
	
	/*
	 * Función que recibe un valor entero y lo almacena en la variable x.
	 */
	public void setX(int newX){
		x=newX;
	}
	
	/*
	 * Función que recibe un valor entero y lo almacena en la variable y.
	 */
	public void setY(int newY){
		y=newY;
	}
	
	/*
	 * Función que devuelve un entero con el valor guardado para la posición x.
	 */
	public int getX(){
		return x;
	}
	
	/*
	 * Función que devuelve un entero con el valor guardado para la posición y.
	 */
	public int getY(){
		return y;
	}
	
	/*
	 * Función que devuelve una cadena de texto que muestra la posición almacenada en esta instancia.
	 */
	public String toString(){
		return new String("Position:\tx="+x+" \ty="+y);
	}
	
	/*
	 * Función que devuelve true si la posición que se le pasa como parámetro es igual a esta instancia 
	 * y false en caso contrario.
	 */
	public boolean equals(Position newPos){
		boolean retorno = false;
		
		if(newPos.getX()==x && newPos.getY()==y)
			retorno = true;
		
		return retorno;
	}

}
