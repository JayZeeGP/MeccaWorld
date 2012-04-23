// Fichero Test.java del Ejemplo 1.1

/*
 + Modo de ejecución
    - Interactivo 
         java Test

    - Redirigiendo un fichero de entrada
	 java Test < fichero_entrada.txt
*/

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.StringReader;

import antlr.collections.AST;

import antlr.ANTLRException;
import antlr.Token;


public class Test {
	public static void main(String[] args) throws Exception{

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

	/*Token tok = lexer.nextToken();
	
	while(tok.getType() != Token.EOF_TYPE)
	{ 
		System.out.println(
				    "\n Lexema: " + tok.getText()  
				  + "\n Tipo de token:" + tok.getType() 
				  + "\n Línea: " + tok.getLine()   
				  + "   Columna: " + tok.getColumn()  
				  );

		System.out.println(tok);

		tok = lexer.nextToken();
	}*/
	
	// Comienza la ejecución de la función asociada al símbolo inicial: prog
	parser.mecca();
	System.out.println("HOLA AMIGOS Somos la leche");
	}
}
