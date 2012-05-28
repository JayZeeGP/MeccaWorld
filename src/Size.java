/**
 * Clase simple que sirve para almacenar el tamaño de algún elemento del tablero (o del propio tablero).
 * @author Rubén Salado y José Carlos Garrido
 *
 */
public class Size {
	/*
	 * Variable privada de tipo entero entero que almacena la anchura de una instancia de esta clase.
	 */
	private int width;
	/*
	 * Variable privada de tipo entero entero que almacena la altura de una instancia de esta clase.
	 */	
	private int height;
	
	/*
	 * Constructor vacío de la clase que pondrá el tamaño a (0,0).
	 */
	Size(){
		width=0;
		height=0;
	}

	/*
	 * Constructor parametrizado que pondrá el tamaño en los valores que reciba como parámetros.
	 */
	Size(int newWidth, int newHeight){
		width=newWidth;
		height=newHeight;
	}
	
	/*
	 * Constructor de copia de la clase Size.
	 */
	Size(Size copyPos){
		width=copyPos.getWidth();
		height=copyPos.getHeight();
	}
	
	/*
	 * Función que recibe un valor entero y lo almacena en la variable width.
	 */
	public void setWidth(int newWidth){
		width=newWidth;
	}
	
	/*
	 * Función que recibe un valor entero y lo almacena en la variable height.
	 */
	public void setHeight(int newHeight){
		height=newHeight;
	}
	
	/*
	 * Función que devuelve un entero con el valor guardado para la variable width.
	 */
	public int getWidth(){
		return width;
	}
	
	/*
	 * Función que devuelve un entero con el valor guardado para la variable height.
	 */
	public int getHeight(){
		return height;
	}
	
	/*
	 * Función que devuelve una cadena de texto que muestra 
	 * la información de tamaños almacenada en esta instancia.
	 */
	public String toString(){
		return new String("Size:\n\tWidth="+width+" \tHeight="+height);
	}
}
