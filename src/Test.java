// Fichero Test.java del Ejemplo 1.1

/*
 + Modo de ejecución
    - Interactivo 
         java Test

    - Redirigiendo un fichero de entrada
	 java Test < fichero_entrada.txt
*/

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.InputStreamReader;


public class Test {
	public static void main(String[] args) throws Exception {

		try {
			if(args.length > 0) {
				FileInputStream file = new FileInputStream(args[0]);
				
				// Se crean los componentes léxicos a partir de la información contenida en el buffer
				Meccalex lexer = new Meccalex(file);
			
				// Se crea el analizador sintáctico a partir de los componentes léxicos
				Meccasint parser = new Meccasint(lexer);
				
				// Comienza la ejecución de la función asociada al símbolo inicial: prog
				parser.mecca();
			} else {
				/*
			       Se crea un buffer que va a almacenar la información proporcionada 
			       desde el dipositivo de entrada estándar.
				   Por defecto: teclado
			        */
				BufferedReader input = new BufferedReader(new InputStreamReader(System.in));
				
				// Se crean los componentes léxicos a partir de la información contenida en el buffer
				Meccalex lexer = new Meccalex(input);
			
				// Se crea el analizador sintáctico a partir de los componentes léxicos
				Meccasint parser = new Meccasint(lexer);
				
				// Comienza la ejecución de la función asociada al símbolo inicial: prog
				parser.mecca();
			}
		} catch(FileNotFoundException e) {
			System.err.println(e.getMessage());
		}
	}
}
