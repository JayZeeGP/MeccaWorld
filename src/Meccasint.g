/*
 Ejemplo 5
	Incluye 
	Se ha incluido un contador de instrucciones reconocidas

	Gramatica de las expresiones aritmeticas.

	El analizador lexico y el analizador sintactico 
	estan definidos en ficheros diferentes

	Este fichero contiene la definicion del analizador sintactico
*/


// Analizador sintáctico

class Meccasint extends Parser;
options
{
  // Para diferenciar si una sentencia es una asignación o una expresión que comienza con ID
   k = 2;
   exportVocab = Meccasint;
}
// Nuevos atributos de la clase Anasint
{
	public static final String NO_MODE = "NoMode";
	public static final String CONFIGURATION_MODE = "ConfigurationMode";
	public static final String ADVENTURE_MODE = "AdventureMode";
	// Atributo de Anasint para contar las instrucciones reconocidas
	int contador = 0;
	Board board = new Board();
	String mode = new String(NO_MODE);
 	TablaSimbolos symbolsTable = new TablaSimbolos(); 
 	
 	//Para los bucles for. DESEO BORRARLA
 	boolean firstTime = true;
 	
 	/* Método para acceder a la tabla de símbolos desde fuera de la clase */
	public TablaSimbolos getTablaSimbolos()
	{
		return symbolsTable;
	}


	/* Método para insertar un identificador en la tabla de símbolos con un valor */
	private void insertarIdentificador(String nombre, String tipo, String valorCadena)
		{
			
			// Busca el identificador en la tabla de símbolos
			int indice = symbolsTable.existeSimbolo(nombre);

			// Si encuentra el identificador, le modifica su valor
			if (indice >= 0)
			{
				symbolsTable.getSimbolo(indice).setValor(valorCadena);
			}
			// Si no lo encuentra, lo inserta en la tabla de símbolos
			else
			{
				// Se crea la variable
				Variable v = new Variable (nombre,"float",valorCadena);

				// Se inserta la variable en la tabla de símbolos
				symbolsTable.insertarSimbolo(v);
			}
		}

	// Función para mostrar un mensaje de error
	private	void mostrarExcepcion(RecognitionException re)
	{
		System.out.println("Error en la línea " + re.getLine() + " --> " + re.getMessage());
		//reportError(re);
		try {
			//Consume the token problem
			consume(); 
    			consumeUntil(PUNTO_COMA);
			} 
		catch (Exception e) 
			{
			}
	}
}




//HECHO POR TEAM MECCA

mecca: (instruction)* configuration (instruction)* adventure (instruction)*;

configuration:  BEGIN_CONF {mode = CONFIGURATION_MODE;}(instruction)+ END_CONF;

adventure: BEGIN_ADV {if(board.initGame()) mode = ADVENTURE_MODE;} (instruction)+ END_ADV;

instruction
{float param1, param2;
String info;}
	: 
	
		FUNC_LEER PARENT_IZ i2:IDENT PARENT_DE PUNTO_COMA 
			{	
				//It searches the identifier in the symbols table
				int index = symbolsTable.existeSimbolo(i2.getText());
					
				if ( index >= 0 ) {
					String stringValue = symbolsTable.getSimbolo(index).getValor();
					System.out.println(stringValue);
					}					
				}
				
		| FUNC_SHOWBOARD PARENT_IZ PARENT_DE PUNTO_COMA 
			{
							if(mode == CONFIGURATION_MODE) {
								System.out.println(board.toString());
							} else {
								System.out.println("This instruction has to be called in Configuration Mode");
							}
			}
			
		| FUNC_SHOWADVENTURESTATE PARENT_IZ PARENT_DE PUNTO_COMA 
			{
							if(mode == ADVENTURE_MODE) {
								board.showAdventureState();
							} else {
								System.out.println("This instruction has to be called in Configuration Mode");
							}
			}
					
		| FUNC_SETBOARDSIZE PARENT_IZ param1=expression COMA param2=expression PARENT_DE PUNTO_COMA 
			{
				if(mode == CONFIGURATION_MODE) {
					Size newSize = new Size((int)param1,(int)param2);
					board.setSize(newSize);
					System.out.println("Board has now "+board.getSize().getWidth()+" columns and "+board.getSize().getHeight()+" rows");
				} else {
					System.out.println("This instruction has to be called in Configuration Mode");
				}
			}
			
		| FUNC_GETBOARDROWS PARENT_IZ PARENT_DE PUNTO_COMA 
			{
				if(mode == CONFIGURATION_MODE || mode == ADVENTURE_MODE) {					
					System.out.println("Board has "+board.getSize().getHeight()+" rows");
				} else {
					System.out.println("This instruction has to be called in Configuration Mode or Adventure Mode");							
				}
			}
			
		| FUNC_GETBOARDCOLUMNS PARENT_IZ PARENT_DE PUNTO_COMA 
			{
				if(mode == CONFIGURATION_MODE || mode == ADVENTURE_MODE) {	
					System.out.println("Board has "+board.getSize().getWidth()+" columns");
				} else {
					System.out.println("This instruction has to be called in Configuration Mode or Adventure Mode");							
				}
			}
			
		| FUNC_GETBOARDSIZE PARENT_IZ PARENT_DE PUNTO_COMA 
			{
				if(mode == CONFIGURATION_MODE || mode == ADVENTURE_MODE) {	
					System.out.println("Board size is "+board.getSize().getHeight()+" rows and "+board.getSize().getWidth()+" columns");
				} else {
					System.out.println("This instruction has to be called in Configuration Mode or Adventure Mode");													
				}
			}
			
		| FUNC_SETTREASURE PARENT_IZ param1=expression COMA param2=expression PARENT_DE PUNTO_COMA 
			{
				if(mode == CONFIGURATION_MODE) {
					Position newTreasure = new Position((int)param1,(int)param2);
					int position = board.setTreasurePos(newTreasure);
					
					if(position != -1) {
						System.out.println("Treasure "+(position+1)+" set on column "+board.getTreasurePos(position).getX()+" row "+board.getTreasurePos(position).getY());
					} else {
						System.out.println("The given board position is not empty or not exists");
					}
				} else {
					System.out.println("This instruction has to be called in Configuration Mode");
				}
			}
			
		| FUNC_REMOVETREASURE PARENT_IZ param1=expression PARENT_DE PUNTO_COMA
			{						
				if(board.removeTreasure((int)param1)) {
					System.out.println("Treasure " + param1 + " has been removed");
				} else {
					System.out.println("Treasure " + param1 + " does not exist");
				}	
			}					
				
		| FUNC_GETTOTALTREASURES PARENT_IZ PARENT_DE PUNTO_COMA 
			{
				System.out.println("The number of total treasures is: "+board.getTotalTreasures());
			}
		
		| FUNC_SHOWTREASURES PARENT_IZ  PARENT_DE PUNTO_COMA 
			{
				if(mode == CONFIGURATION_MODE) {
					board.showTreasures();
				} else {
					System.out.println("This instruction has to be called in Configuration Mode");							
				}
			}					
					
		| FUNC_GETTREASURE PARENT_IZ param1=expression PARENT_DE PUNTO_COMA 
			{
				if(mode == CONFIGURATION_MODE) {						
					//Check if that treasure exists
					if(board.getTotalTreasures()>=param1&&param1>0) {
						System.out.println("Treasure "+((int)param1)+" set on column "+board.getTreasurePos((int)param1-1).getX()+" row "+board.getTreasurePos((int)param1-1).getY());
					} else {
						System.out.println("There is no treasure with that number");
					}
				} else {
					System.out.println("This instruction has to be called in Configuration Mode");
				}
			}
		
		| FUNC_SETHOLE PARENT_IZ param1=expression COMA param2=expression PARENT_DE PUNTO_COMA 
			{
				if(mode == CONFIGURATION_MODE) {
					Position newHole = new Position((int)param1,(int)param2);
					int position = board.setHolePos(newHole);
					
					if(position != -1) {
						System.out.println("Hole "+(position+1)+" set on column "+board.getHolePos(position).getX()+" row "+board.getHolePos(position).getY());
					} else {
						System.out.println("The given board position is not empty or not exists");
					}
				} else {
					System.out.println("This instruction has to be called in Configuration Mode");
				}
			}
		
		| FUNC_REMOVEHOLE PARENT_IZ param1=expression PARENT_DE PUNTO_COMA
			{						
				if(board.removeHole((int)param1)) {
					System.out.println("Hole " + param1 + " has been removed");
				} else {
					System.out.println("Hole " + param1 + " does not exist");
				}	
			}
			
		| FUNC_GETNUMBEROFHOLES PARENT_IZ PARENT_DE PUNTO_COMA 
			{
				if(mode == CONFIGURATION_MODE) {
					System.out.println("The number of holes is: "+board.getNumberOfHoles());
				} else {
					System.out.println("This instruction has to be called in Configuration Mode");							
				}
			}
			
		| FUNC_GETHOLE PARENT_IZ param1=expression PARENT_DE PUNTO_COMA 
			{
				if(mode == CONFIGURATION_MODE) {
					//Check if that hole exists
					if(board.getNumberOfHoles()>=param1&&param1>0) {
						System.out.println("Hole "+(param1)+" set on column "+board.getHolePos((int)param1-1).getX()+" row "+board.getHolePos((int)param1-1).getY());
					} else {
						System.out.println("There is no hole with that number");
					}
				} else {
					System.out.println("This instruction has to be called in Configuration Mode");							
				}
			}
			
		| FUNC_SHOWHOLES PARENT_IZ  PARENT_DE PUNTO_COMA 
			{
				if(mode == CONFIGURATION_MODE) {
					board.showHoles();
				} else {
					System.out.println("This instruction has to be called in Configuration Mode");							
				}
			}
				
		| FUNC_SETWUMPUS PARENT_IZ param1=expression COMA param2=expression PARENT_DE PUNTO_COMA 
			{
				if(mode == CONFIGURATION_MODE) {
					Position newWumpus = new Position((int)param1,(int)param2);
					
					if(board.setWumpusPos(newWumpus)) {
						System.out.println("Wumpus set on column "+board.getWumpusPos().getX()+" row "+board.getWumpusPos().getY());
					}
				} else {
					System.out.println("This instruction has to be called in Configuration Mode");
				}
			}
				
		| FUNC_GETWUMPUS PARENT_IZ PARENT_DE PUNTO_COMA 
			{
				if(mode == CONFIGURATION_MODE) {		
					System.out.println("The Wumpus is on column "+ board.getWumpusPos().getX()+" row " + board.getWumpusPos().getY());
				} else {
					System.out.println("This instruction has to be called in Configuration Mode");
				}
			}
			
		| FUNC_SETSTART PARENT_IZ param1=expression COMA param2=expression PARENT_DE PUNTO_COMA 
			{
				if(mode == CONFIGURATION_MODE) {
					Position newStart = new Position((int)param1,(int)param2);
					
					if(board.setStartPos(newStart)) {
						System.out.println("Start set on column "+board.getStartPos().getX()+" row "+board.getStartPos().getY());
					} else {
						System.out.println("The given board position is not empty or not exists");
					}
				} else {
					System.out.println("This instruction has to be called in Configuration Mode");
				}
			}
			
		| FUNC_GETSTART PARENT_IZ PARENT_DE PUNTO_COMA 
			{
				if(mode == CONFIGURATION_MODE) {
					System.out.println("Start is on column "+board.getStartPos().getX()+" row "+board.getStartPos().getY());
				} else {
					System.out.println("This instruction has to be called in Configuration Mode");
				}	
			}
			
		| FUNC_SETEXIT PARENT_IZ param1=expression COMA param2=expression PARENT_DE PUNTO_COMA 
			{
				if(mode == CONFIGURATION_MODE) {
					Position newExit = new Position((int)param1,(int)param2);
					
					if(board.setExitPos(newExit)) {
						System.out.println("Exit set on column "+board.getExitPos().getX()+" row "+board.getExitPos().getY());
					} else {
						System.out.println("The given board position is not empty or not exists");
					}
				} else {
					System.out.println("This instruction has to be called in Configuration Mode");
				}
			}
				
		| FUNC_GETEXIT PARENT_IZ  PARENT_DE PUNTO_COMA 
			{
				if(mode == CONFIGURATION_MODE) {
					System.out.println("Exit is on column "+board.getExitPos().getX()+" row "+board.getExitPos().getY());
				} else {
					System.out.println("This instruction has to be called in Configuration Mode");
				}
			}
				
			//Esto en realidad no tiene pinta de que se vaya a utilizar en el conf mode
		| FUNC_GETMECCA PARENT_IZ PARENT_DE PUNTO_COMA 
			{
				if(mode == CONFIGURATION_MODE || mode == ADVENTURE_MODE) {
					System.out.println("Mecca is on row "+ board.getMeccaPos().getY()+" column " + board.getMeccaPos().getX());
				} else {
					System.out.println("This instruction has to be called in Configuration Mode or Adventure Mode");
				}
			}
					
		| FUNC_SETARROWS PARENT_IZ param1=expression PARENT_DE PUNTO_COMA 
			{
				if(mode == CONFIGURATION_MODE) {
					board.setMeccaNArrows((int)param1);
					System.out.println("Mecca has now "+board.getMeccaNArrows()+" arrows");
				} else {
					System.out.println("This instruction has to be called in Configuration Mode");
				} 				
			}
			
		| FUNC_GETARROWS PARENT_IZ PARENT_DE PUNTO_COMA 
			{
				if(mode == CONFIGURATION_MODE || mode == ADVENTURE_MODE) {
					System.out.println("Mecca has "+board.getMeccaNArrows()+" arrows");
				} else {
					System.out.println("This instruction has to be called in Configuration Mode or Adventure Mode");
				}						
			}
					
		| FUNC_INCARROWS PARENT_IZ param1=expression PARENT_DE PUNTO_COMA 
			{
				if(mode == CONFIGURATION_MODE) {
					if(param1>=0) {
						board.incMeccaNArrows((int)param1);
						System.out.println("Arrows incremented in "+param1+", Mecca has now "+board.getMeccaNArrows()+" arrows");	
					} else {
						System.out.println("You have to enter an integer bigger than 0");
					}
				} else {
					System.out.println("This instruction has to be called in Configuration Mode");
				}	
			}
					
		| FUNC_DECARROWS PARENT_IZ param1=expression PARENT_DE PUNTO_COMA 
			{
				if(mode == CONFIGURATION_MODE) {						
					if(param1>=0) {
						board.decMeccaNArrows((int)param1);
						System.out.println("Arrows decremented in "+param1+", Mecca has now "+board.getMeccaNArrows()+" arrows");	
					} else {
						System.out.println("The number of arrows has to be positive");
					}
				} else {
					System.out.println("This instruction has to be called in Configuration Mode");
				}	
			}
					
		| FUNC_GETREMAININGTREASURES PARENT_IZ PARENT_DE PUNTO_COMA 
			{
				System.out.println("X Treasures remaining");
			}
					
		| FUNC_SHOOTLEFT PARENT_IZ PARENT_DE PUNTO_COMA 
			{
				if(mode == ADVENTURE_MODE) {
					if(!board.isGameFinished()) {
						if(!board.meccaShoot(3)) {
							System.out.println("No arrows remaining!");	
						}
					} else {
						System.out.println("The game has finished!");	
					}
				} else {
					System.out.println("This instruction has to be called in Adventure Mode");
				}
			}
					
		| FUNC_SHOOTRIGHT PARENT_IZ PARENT_DE PUNTO_COMA 
			{
				if(mode == ADVENTURE_MODE) {
					if(!board.isGameFinished()) {
						if(!board.meccaShoot(2)) {
							System.out.println("No arrows remaining!");	
						}
					} else {
						System.out.println("The game has finished!");	
					}
				} else {
					System.out.println("This instruction has to be called in Adventure Mode");
				}
			}
					
		| FUNC_SHOOTUP PARENT_IZ PARENT_DE PUNTO_COMA 
			{
				if(mode == ADVENTURE_MODE) {
					if(!board.isGameFinished()) {
						if(!board.meccaShoot(1)) {
							System.out.println("No arrows remaining!");	
						}
					} else {
						System.out.println("The game has finished!");	
					}
				} else {
					System.out.println("This instruction has to be called in Adventure Mode");
				}
			}
					
		| FUNC_SHOOTDOWN PARENT_IZ PARENT_DE PUNTO_COMA 
			{
				if(mode == ADVENTURE_MODE) {
					if(!board.isGameFinished()) {
						if(!board.meccaShoot(4)) {
							System.out.println("No arrows remaining!");	
						}
					} else {
						System.out.println("The game has finished!");	
					}
				} else {
					System.out.println("This instruction has to be called in Adventure Mode");
				}
			}
					
		| FUNC_GOLEFT PARENT_IZ PARENT_DE PUNTO_COMA 
			{
				if(mode == ADVENTURE_MODE) {
					if(!board.isGameFinished()) {
						board.meccaGoLeft();
					} else {
						System.out.println("The game has finished!");	
					}
				} else {
					System.out.println("This instruction has to be called in Adventure Mode");
				}
			}
			
		| FUNC_GORIGHT PARENT_IZ PARENT_DE PUNTO_COMA 
			{
				if(mode == ADVENTURE_MODE) {
					if(!board.isGameFinished()) {
						board.meccaGoRight();
					} else {
						System.out.println("The game has finished!");	
					}
				} else {
					System.out.println("This instruction has to be called in Adventure Mode");
				}
			}
				
		| FUNC_GOUP PARENT_IZ PARENT_DE PUNTO_COMA 
			{
				if(mode == ADVENTURE_MODE) {
					if(!board.isGameFinished()) {
						board.meccaGoUp();
					} else {
						System.out.println("The game has finished!");	
					}
				} else {
					System.out.println("This instruction has to be called in Adventure Mode");
				}
			}
				
		| FUNC_GODOWN PARENT_IZ PARENT_DE PUNTO_COMA 
			{
				if(mode == ADVENTURE_MODE) {
					if(!board.isGameFinished()) {
						board.meccaGoDown();
					} else {
						System.out.println("The game has finished!");	
					}
				} else {
					System.out.println("This instruction has to be called in Adventure Mode");
				}
			}
				
		| asignation	
		
		| conditional_sentence			
		
		| while_loop
		
		| do_until_loop
		
		| for_loop {firstTime=true;}
        ;
               
asignation
	// Variable local
	{float e; String e2;}
	: TIPO_NUMERO i:IDENT OP_ASIG e=expression PUNTO_COMA  //Añadido por mí el ;
	 	{
			// Se toma el nombre del identificador
			String nombre = i.getText();

			// El número se convierte en cadena
			String valorCadena = String.valueOf(e);

			// Se inserta en la tabla de Símbolos
			insertarIdentificador(nombre,"float",valorCadena);
	
			// Se muestra por pantalla: depuración
			// System.out.println(" Asignación => " + nombre + " := " + e);
		}
		
		| TIPO_CADENA i2:IDENT OP_ASIG e2=expression_string PUNTO_COMA
	 	{
			// Se toma el nombre del identificador
			String nombre = i2.getText();

			// El número se convierte en cadena
			String valorCadena = String.valueOf(e2);

			// Se inserta en la tabla de Símbolos
			insertarIdentificador(nombre,"string",valorCadena);
	
			// Se muestra por pantalla: depuración
			// System.out.println(" Asignación => " + nombre + " := " + e);
		}
 	 ;
  	exception
 		catch [RecognitionException re] {
			mostrarExcepcion(re);
		 }

expression 
	// Valor que devuelve
	returns [float result = (float) 0.0;]
	// Variables locales
	{float e1,e2;} 
	:
	 	e1=addend {result = e1;} 
		(
			// Suma
			(
			 OP_MAS e2 = addend
				{
				 result = result + e2;
				}
			)
			 // Resta
			| (
			 OP_MENOS e2 = addend
				{
				 result = result - e2;
				}
			 )
		)*
	| e1=negative {result = e1;} 
		(
			// Suma
			(
			 OP_MAS e2 = addend
				{
				 result = result + e2;
				}
			)
			 // Resta
			| (
			 OP_MENOS e2 = addend
				{
				 result = result - e2;
				}
			 )
		)*

	;
	exception
 		catch [RecognitionException re] {
 			System.out.println("Traza: expression");
			mostrarExcepcion(re);
		 }
		
expression_string 
	// Valor que devuelve
	returns [String result = new String();]
	// Variables locales
	{String e1,e2;} 
	:
	 	e1=factor_string {result = e1;} 
		(
			// Suma
			(
			 OP_MAS e2 = factor_string
				{
				 result = result + e2;
				}
			)
		)*
		|

	;
	exception
 		catch [RecognitionException re] {
 			System.out.println("Traza: expression");
			mostrarExcepcion(re);
		 }

addend 
	// Valor que devuelve
	returns [float result = (float) 0.0;]

	// Variables locales
	{float e1,e2;} 
	:
		e1=factor {result = e1;} 
		(
			// Producto
			(
			 OP_PRODUCTO e2=factor
				{
					result = result * e2;
				}
			)
		
			// Division
			| (
			 OP_DIVISION e2=factor
				{
					result = result / e2;
				}
			 )
		)*
	;
	exception
 		catch [RecognitionException re] {
			mostrarExcepcion(re);
		 }

factor 
	// Valor que devuelve
	returns [float result = (float) 0.0;] 

	// Variables locales
	{float e;} 
	:
	  i:IDENT 
		{
			// Busca el identificador en la tabla de símbolos
			int indice = symbolsTable.existeSimbolo(i.getText());

			// Si encuentra el identificador, devuelve su valor
			if (indice >= 0)
			{
				// Se recupera el valor almacenado como cadena
				String valorCadena = symbolsTable.getSimbolo(indice).getValor();

				// La cadena se convierte a número real
				result = Float.parseFloat(valorCadena);
			}
			else
				System.err.println("Error: el identificador " + i.getText() + " está indefinido");
		}

	| n:LIT_NUMERO  //Ponía número aquí
			{result = new Float(n.getText()).floatValue();}

	| PARENTESIS_IZ e=expression PARENTESIS_DE
			{result = e;}
	;
	exception
 		catch [RecognitionException re] {
			mostrarExcepcion(re);
		 }
		
factor_string
	// Valor que devuelve
	returns [String result = new String();] 

	// Variables locales
	{String e;} 
	:
	  i:IDENT 
		{
			// Busca el identificador en la tabla de símbolos
			int indice = symbolsTable.existeSimbolo(i.getText());

			// Si encuentra el identificador, devuelve su valor
			if (indice >= 0)
			{
				// Se recupera el valor almacenado como cadena
				String valorCadena = symbolsTable.getSimbolo(indice).getValor();

				// La cadena se convierte a número real
				result = valorCadena;
			}
			else
				System.err.println("Error: el identificador " + i.getText() + " está indefinido");
		}

	| n:LIT_CADENA  //Ponía número aquí
			{result = n.getText();}

	| PARENTESIS_IZ e=expression_string PARENTESIS_DE
			{result = e;}
	;
	exception
 		catch [RecognitionException re] {
			mostrarExcepcion(re);
		 }

	    
// Expresiones que empiezan por el signo menos
negative
	// Valor que devuelve
	returns [float result = (float) 0.0;] 

	// Variables locales
	{float e;} 
	:  OP_MENOS e = factor {result = -e;}
        ;
		    
conditional_sentence
	// Variable local
	 {boolean valor;}
	 : RES_SI valor=condition  
	   RES_ENTONCES 
		(
		 // Si la condición es verdadera, se ejecuta el consecuente
		 {valor==true}? (instruction)+

		 // Si hay parte alternativa, se omite
			(
			  RES_SI_NO
				 (options {greedy=false;}:.)+
			)?
		|
		 // Si la condición es false, se omite el consecuente
  		  {valor==false}? (options {greedy=false;}:.)+

		 // Si hay parte alternativa, se ejecuta
			(
			 RES_SI_NO
				(instruction)+
			)?
			
		)
		RES_FIN_SI
	;
	exception
 		catch [RecognitionException re] {
			mostrarExcepcion(re);
		 }

condition
	// Valor que devuelve
	returns [boolean ressult = false]

	// Variables locales
	{float e1, e2;}
	: e1=expression
		// Comienzo de las alternativas
		(
		 OP_IGUAL e2=expression
			{
				if (e1 == e2)
					ressult = true;
				else 
					ressult = false;
			}
        	 | OP_DISTINTO e2=expression
			{
				if (e1 != e2)
					ressult = true;
				else 
					ressult = false;
			}
        	 | OP_MENOR e2=expression
			{
				if (e1 < e2)
					ressult = true;
				else 
					ressult = false;
			}
        	 | OP_MENOR_IGUAL e2=expression
			{
				if (e1 <= e2)
					ressult = true;
				else 
					ressult = false;
			}
        	 | OP_MAYOR_IGUAL e2=expression
			{
			if (e1 >= e2)
				ressult = true;
			else 
				ressult = false;
			}
        	 | OP_MAYOR e2=expression
			{
			if (e1 > e2)
				ressult = true;
			else 
				ressult = false;
			}
		) // Fin de las alternativas
	;
	exception
 		catch [RecognitionException re] {
			mostrarExcepcion(re);
		 }

while_loop
		// Variables locales
		{boolean valor; int marca=-1;}
		:
		 // Se establece una marca para indicar el punto de inicio del bucle
		{marca = mark();}
		 RES_MIENTRAS valor=condition  
		 RES_HACER

			( // Comienzo de las alternativas

			  // Si la condición es falsa, se omite el cuerpo del bucle
			 {valor == false}? (options {greedy=false;}:.)*  RES_FIN_MIENTRAS PUNTO_COMA

			  // Si la condición es verdadera, se ejecutan las instrucciones del bucle
			| {valor == true}? (instruction)+  RES_FIN_MIENTRAS PUNTO_COMA
				// Se indica que se repita la ejecución del bucle_mientras
				{
				rewind(marca); 
				this.while_loop();
				}
			) // Fin de las alternativas	
		;
			   
do_until_loop
		// Variables locales, valor es true para que entre la primera vez
		{boolean valor=false; int marca=-1;}
		:
		 // Se establece una marca para indicar el punto de inicio del bucle
		{marca = mark();}
		 RES_REPETIR 			( // Comienzo de las alternativas

			  // Si la condición es falsa, se omite el cuerpo del bucle
			 {valor == true}? (options {greedy=false;}:.)*  RES_HASTA valor=condition PUNTO_COMA	

			  // Si la condición es verdadera, se ejecutan las instrucciones del bucle
			| {valor == false}? (instruction)+  RES_HASTA valor=condition PUNTO_COMA	
				// Se indica que se repita la ejecución del bucle_mientras
				{
					if(valor==false){
						rewind(marca); 
						this.do_until_loop();
					}
				}
			) // Fin de las alternativas
		;
		
for_loop
		// Variables locales
		{int marca=-1;float initValue=-1, endValue=-1, inc=-1; int index=-1; String id; boolean firstTimeTest=true;}
		:
		// Se establece una marca para indicar el punto de inicio del bucle
		{marca = mark();}
		
		 RES_PARA i:IDENT  RES_DESDE initValue=expression RES_HASTA endValue=expression
		 RES_PASO inc=expression RES_HACER
		 
		 {		 	
		 	// Se toma el nombre del identificador
			String name = i.getText();
		 	
		 	if(firstTimeTest) {
	
				// El número se convierte en cadena
				String stringValue = String.valueOf(initValue);
			
				// Se inserta en la tabla de Símbolos
				insertarIdentificador(name,"float",stringValue);
		 	}
			//Para tener el índice
			index=symbolsTable.existeSimbolo(name);
			// Se muestra por pantalla: depuración
			// System.out.println(" Asignación => " + nombre + " := " + e);
			//Preparar las variables auxiliares
							
			firstTimeTest=false;
		 	
		}
		

			( // Comienzo de las alternativas

			  // Si la condición es falsa, se omite el cuerpo del bucle
			 {Float.parseFloat(symbolsTable.getSimbolo(index).getValor()) >= endValue}? (options {greedy=false;}:.)*  RES_FIN_PARA PUNTO_COMA

			  // Si la condición es verdadera, se ejecutan las instrucciones del bucle
			| {Float.parseFloat(symbolsTable.getSimbolo(index).getValor()) < endValue}? (instruction)+ {symbolsTable.getSimbolo(index).setValor(String.valueOf(Float.parseFloat(symbolsTable.getSimbolo(index).getValor())+inc));} RES_FIN_PARA PUNTO_COMA
				// Se indica que se repita la ejecución del bucle_mientras
				{
				rewind(marca); 
				this.for_loop();
				}
			) // Fin de las alternativas	
		;
		   
parametros: valorparametro (parametros_prima)*;

parametros_prima: COMA valorparametro;

nombrefuncion: IDENT;

valorparametro: IDENT;

variable: IDENT;

//entero returns [int value=0]: 
		//i1:LIT_ENTERO				{value = Integer.parseInt(i1.getText());}
		
//;


//HECHO POR TEAM MECCA
