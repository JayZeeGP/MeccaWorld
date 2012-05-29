// Analizador sintáctico

class Meccasint extends Parser;

options
{
	k = 2;
	exportVocab = Meccasint;
}

// Nuevos atributos de la clase Meccasint
{
	public static final String NO_MODE = "NoMode";
	public static final String CONFIGURATION_MODE = "ConfigurationMode";
	public static final String ADVENTURE_MODE = "AdventureMode";
	
	public static final String NUMBER = "number";
	public static final String STRING = "string";
	
	public int contador = 0;
	
	Board board = new Board();
	String mode = new String(NO_MODE);
 	TablaSimbolos symbolsTable = new TablaSimbolos(); 
 	
 	boolean firstTime = true; // Para el bucle for
 	
 	/* Método para acceder a la tabla de símbolos desde fuera de la clase */
	public TablaSimbolos getTablaSimbolos()
	{
		return symbolsTable;
	}

	/* Método para insertar un identificador en la tabla de símbolos con un valor */
	private boolean insertarIdentificador(String nombre, String tipo, String valorCadena) {
		boolean insertado = true;
			
		// Busca el identificador en la tabla de símbolos
		int indice = symbolsTable.existeSimbolo(nombre);

		if(indice >= 0) { // Identificador ya declarado
			insertado = false;
		} else { // Si no lo encuentra, lo inserta en la tabla de símbolos				
			// Se crea la variable
			Variable v = new Variable (nombre,tipo,valorCadena);
	
			// Se inserta la variable en la tabla de símbolos
			symbolsTable.insertarSimbolo(v);
		}
			
		return insertado;
	}

	// Función para mostrar un mensaje de error
	private	void mostrarExcepcion(RecognitionException re) {
		System.out.println("Error en la línea " + re.getLine() + " --> " + re.getMessage());
		//reportError(re);
		try {
			//Consume the token problem
			consume(); 
    		consumeUntil(PUNTO_COMA);
		} catch (Exception e)  { }
	}
}


//Símbolos no terminales

mecca: (instruction)* configuration (instruction)* adventure (instruction)*;

configuration:  BEGIN_CONF {mode = CONFIGURATION_MODE;} (instruction)* END_CONF;

adventure: BEGIN_ADV {if(board.initGame()) mode = ADVENTURE_MODE;} (instruction)* END_ADV;

instruction
	{
		Variable param1, param2;
	}
	: 
	
		e1:FUNC_LEER PARENT_IZ i2:IDENT PARENT_DE PUNTO_COMA 
		{	
			//It searches the identifier in the symbols table
			int index = symbolsTable.existeSimbolo(i2.getText());
					
			if(index >= 0) {
				Variable variable = symbolsTable.getSimbolo(index);
				
				String read = board.read();
				
				if(variable.getTipo().equals("number")) {
					float valor = Float.parseFloat(read);
					variable.setValor(String.valueOf(valor));
				} else {
					variable.setValor(read);
				}
			} else {
				System.err.println("Line " + e1.getLine() + ":" + e1.getColumn() + " - Variable \"" + i2.getText() + "\" is not declared");	
			}
		}
		
		| FUNC_ESCRIBIR PARENT_IZ param1=expression PARENT_DE PUNTO_COMA
		{
			System.out.println(param1.getValor());
		}
				
		| e2:FUNC_SHOWBOARD PARENT_IZ PARENT_DE PUNTO_COMA 
		{
			if(mode == CONFIGURATION_MODE) {
				System.out.println(board.showBoard());
			} else {
				System.err.println("Line " + e2.getLine() + ":" + e2.getColumn() + " - This instruction has to be called in Configuration Mode");
			}
		}
			
		| e3:FUNC_SHOWADVENTURESTATE PARENT_IZ PARENT_DE PUNTO_COMA 
		{
			if(mode == ADVENTURE_MODE) {
				board.showAdventureState();
			} else {
				System.err.println("Line " + e3.getLine() + ":" + e3.getColumn() + " - This instruction has to be called in Configuration Mode");
			}
		}
					
		| e4:FUNC_SETBOARDSIZE PARENT_IZ param1=expression COMA param2=expression PARENT_DE PUNTO_COMA 
		{
			if(mode == CONFIGURATION_MODE) {
				if(param1.isNumber()) {
					Size newSize = new Size((int)Float.parseFloat(param1.getValor()),(int)Float.parseFloat(param2.getValor()));
					board.setSize(newSize);
					System.out.println("Board has now "+board.getSize().getWidth()+" columns and "+board.getSize().getHeight()+" rows");
				} else {
					System.err.println("Line " + e4.getLine() + ":" + e4.getColumn() + " - " + "Parameters must be numbers");
				}
			} else {
				System.err.println("Line " + e4.getLine() + ":" + e4.getColumn() + " - " + "This instruction has to be called in Configuration Mode");
			}
		}
			
		| e5:FUNC_GETBOARDROWS PARENT_IZ PARENT_DE PUNTO_COMA 
		{
			if(mode == CONFIGURATION_MODE || mode == ADVENTURE_MODE) {					
				System.out.println("Board has "+board.getSize().getHeight()+" rows");
			} else {
				System.err.println("Line " + e5.getLine() + ":" + e5.getColumn() + " - " + "This instruction has to be called in Configuration Mode or Adventure Mode");							
			}
		}
			
		| e6:FUNC_GETBOARDCOLUMNS PARENT_IZ PARENT_DE PUNTO_COMA 
		{
			if(mode == CONFIGURATION_MODE || mode == ADVENTURE_MODE) {	
				System.out.println("Board has "+board.getSize().getWidth()+" columns");
			} else {
				System.err.println("Line " + e6.getLine() + ":" + e6.getColumn() + " - " + "This instruction has to be called in Configuration Mode or Adventure Mode");							
			}
		}
			
		| e7:FUNC_GETBOARDSIZE PARENT_IZ PARENT_DE PUNTO_COMA 
		{
			if(mode == CONFIGURATION_MODE || mode == ADVENTURE_MODE) {	
				System.out.println("Board size is "+board.getSize().getHeight()+" rows and "+board.getSize().getWidth()+" columns");
			} else {
				System.err.println("Line " + e7.getLine() + ":" + e7.getColumn() + " - " + "This instruction has to be called in Configuration Mode or Adventure Mode");													
			}
		}
			
		| e8:FUNC_SETTREASURE PARENT_IZ param1=expression COMA param2=expression PARENT_DE PUNTO_COMA 
		{
			if(mode == CONFIGURATION_MODE) {
				if(param1.isNumber() && param2.isNumber()) {
					Position newTreasure = new Position((int)Float.parseFloat(param1.getValor()), (int)Float.parseFloat(param2.getValor()));
					int position = board.setTreasurePos(newTreasure);
					
					if(position != -1) {
						System.out.println("Treasure "+(position+1)+" set on column "+board.getTreasurePos(position).getX()+" row "+board.getTreasurePos(position).getY());
					} else {
						System.out.println("The given board position is not empty or not exists");
					}
				} else {
					System.err.println("Line " + e8.getLine() + ":" + e8.getColumn() + " - " + "Parameters must be numbers");	
				}
			} else {
				System.err.println("Line " + e8.getLine() + ":" + e8.getColumn() + " - " + "This instruction has to be called in Configuration Mode");
			}
		}
			
		| e9:FUNC_REMOVETREASURE PARENT_IZ param1=expression PARENT_DE PUNTO_COMA
		{						
			if(mode == CONFIGURATION_MODE) {
				if(param1.isNumber()) {
					if(board.removeTreasure((int)Float.parseFloat(param1.getValor()))) {
						System.out.println("Treasure " + param1 + " has been removed");
					} else {
						System.err.println("Treasure " + param1 + " does not exist");
					}
				} else {
					System.err.println("Line " + e9.getLine() + ":" + e9.getColumn() + " - " + "Parameter must be number");	
				}
			} else {
				System.err.println("Line " + e9.getLine() + ":" + e9.getColumn() + " - " + "This instruction has to be called in Configuration Mode");
			}
		}					
				
		| FUNC_GETTOTALTREASURES PARENT_IZ PARENT_DE PUNTO_COMA 
		{
			System.out.println("The number of total treasures is: "+board.getTotalTreasures());
		}
		
		| e10:FUNC_SHOWTREASURES PARENT_IZ  PARENT_DE PUNTO_COMA 
		{
			if(mode == CONFIGURATION_MODE) {
				board.showTreasures();
			} else {
				System.err.println("Line " + e10.getLine() + ":" + e10.getColumn() + " - " + "This instruction has to be called in Configuration Mode");							
			}
		}					
					
		| e11:FUNC_GETTREASURE PARENT_IZ param1=expression PARENT_DE PUNTO_COMA 
		{
			if(mode == CONFIGURATION_MODE) {
				if(param1.isNumber()) {					
					//Check if that treasure exists
					if(board.getTotalTreasures() >= (int)Float.parseFloat(param1.getValor()) && (int)Float.parseFloat(param1.getValor()) > 0) {
						System.out.println("Treasure "+ (int)Float.parseFloat(param1.getValor()) +
								" set on column "+board.getTreasurePos((int)Float.parseFloat(param1.getValor())-1).getX()+
								" row "+board.getTreasurePos((int)Float.parseFloat(param1.getValor())-1).getY());
					} else {
						System.err.println("There is no treasure with that number");
					}
				} else {
					System.err.println("Line " + e11.getLine() + ":" + e11.getColumn() + " - " + "Parameter must be number");	
				}
			} else {
				System.err.println("Line " + e11.getLine() + ":" + e11.getColumn() + " - " + "This instruction has to be called in Configuration Mode");
			}
		}
		
		| e12:FUNC_SETHOLE PARENT_IZ param1=expression COMA param2=expression PARENT_DE PUNTO_COMA 
		{
			if(mode == CONFIGURATION_MODE) {
				if(param1.isNumber() && param2.isNumber()) {
					Position newHole = new Position((int)Float.parseFloat(param1.getValor()),(int)Float.parseFloat(param2.getValor()));
					int position = board.setHolePos(newHole);
					
					if(position != -1) {
						System.out.println("Hole "+(position+1)+" set on column "+board.getHolePos(position).getX()+" row "+board.getHolePos(position).getY());
					} else {
						System.out.println("The given board position is not empty or not exists");
					}
				} else {
					System.err.println("Line " + e12.getLine() + ":" + e12.getColumn() + " - " + "Parameter must be number");	
				}
			} else {
				System.err.println("Line " + e12.getLine() + ":" + e12.getColumn() + " - " + "This instruction has to be called in Configuration Mode");
			}
		}
		
		| e13:FUNC_REMOVEHOLE PARENT_IZ param1=expression PARENT_DE PUNTO_COMA
		{
			if(mode == CONFIGURATION_MODE) {
				if(param1.isNumber()) {
					if(board.removeHole((int)Float.parseFloat(param1.getValor()))) {
						System.out.println("Hole " + param1 + " has been removed");
					} else {
						System.out.println("Hole " + param1 + " does not exist");
					}
				} else {
					System.err.println("Line " + e13.getLine() + ":" + e13.getColumn() + " - " + "Parameter must be number");	
				}
			} else {
				System.err.println("Line " + e13.getLine() + ":" + e13.getColumn() + " - " + "This instruction has to be called in Configuration Mode");							
			}
		}
			
		| e14:FUNC_GETNUMBEROFHOLES PARENT_IZ PARENT_DE PUNTO_COMA 
		{
			if(mode == CONFIGURATION_MODE) {
				System.out.println("The number of holes is: "+board.getNumberOfHoles());
			} else {
				System.err.println("Line " + e14.getLine() + ":" + e14.getColumn() + " - " + "This instruction has to be called in Configuration Mode");							
			}
		}
			
		| e15:FUNC_GETHOLE PARENT_IZ param1=expression PARENT_DE PUNTO_COMA 
		{
			if(mode == CONFIGURATION_MODE) {
				if(param1.isNumber()) {
					//Check if that hole exists
					if(board.getNumberOfHoles() >= (int)Float.parseFloat(param1.getValor()) && (int)Float.parseFloat(param1.getValor()) > 0) {
						System.out.println("Hole "+(param1)+" set on column "+board.getHolePos((int)Float.parseFloat(param1.getValor())-1).getX()+" row "+board.getHolePos((int)Float.parseFloat(param1.getValor())-1).getY());
					} else {
						System.out.println("There is no hole with that number");
					}
				} else {
					System.err.println("Line " + e15.getLine() + ":" + e15.getColumn() + " - " + "Parameter must be number");	
				}
			} else {
				System.out.println("Line " + e15.getLine() + ":" + e15.getColumn() + " - " + "This instruction has to be called in Configuration Mode");							
			}
		}
			
		| e16:FUNC_SHOWHOLES PARENT_IZ  PARENT_DE PUNTO_COMA 
		{
			if(mode == CONFIGURATION_MODE) {
				board.showHoles();
			} else {
				System.err.println("Line " + e16.getLine() + ":" + e16.getColumn() + " - " + "This instruction has to be called in Configuration Mode");							
			}
		}
				
		| e17:FUNC_SETWUMPUS PARENT_IZ param1=expression COMA param2=expression PARENT_DE PUNTO_COMA 
		{
			if(mode == CONFIGURATION_MODE) {
				if(param1.isNumber() && param2.isNumber()) {
					Position newWumpus = new Position((int)Float.parseFloat(param1.getValor()),(int)Float.parseFloat(param2.getValor()));
					
					if(board.setWumpusPos(newWumpus)) {
						System.out.println("Wumpus set on column "+board.getWumpusPos().getX()+" row "+board.getWumpusPos().getY());
					}
				} else {
					System.err.println("Line " + e17.getLine() + ":" + e17.getColumn() + " - " + "Parameters must be numbers");	
				}
			} else {
				System.err.println("Line " + e17.getLine() + ":" + e17.getColumn() + " - " + "This instruction has to be called in Configuration Mode");
			}
		}
				
		| e18:FUNC_GETWUMPUS PARENT_IZ PARENT_DE PUNTO_COMA 
		{
			if(mode == CONFIGURATION_MODE) {		
				System.out.println("The Wumpus is on column "+ board.getWumpusPos().getX()+" row " + board.getWumpusPos().getY());
			} else {
				System.err.println("Line " + e18.getLine() + ":" + e18.getColumn() + " - " + "This instruction has to be called in Configuration Mode");
			}
		}
			
		| e19:FUNC_SETSTART PARENT_IZ param1=expression COMA param2=expression PARENT_DE PUNTO_COMA 
		{
			if(mode == CONFIGURATION_MODE) {
				if(param1.isNumber() && param2.isNumber()) {
					Position newStart = new Position((int)Float.parseFloat(param1.getValor()),(int)Float.parseFloat(param2.getValor()));
					
					if(board.setStartPos(newStart)) {
						System.out.println("Start set on column "+board.getStartPos().getX()+" row "+board.getStartPos().getY());
					} else {
						System.out.println("The given board position is not empty or not exists");
						}
				} else {
					System.err.println("Line " + e19.getLine() + ":" + e19.getColumn() + " - " + "Parameters must be numbers");	
				}
			} else {
				System.err.println("Line " + e19.getLine() + ":" + e19.getColumn() + " - " + "This instruction has to be called in Configuration Mode");
			}
		}
			
		| e20:FUNC_GETSTART PARENT_IZ PARENT_DE PUNTO_COMA 
		{
			if(mode == CONFIGURATION_MODE) {
				System.out.println("Start is on column "+board.getStartPos().getX()+" row "+board.getStartPos().getY());
			} else {
				System.err.println("Line " + e20.getLine() + ":" + e20.getColumn() + " - " + "This instruction has to be called in Configuration Mode");
			}	
		}
			
		| e21:FUNC_SETEXIT PARENT_IZ param1=expression COMA param2=expression PARENT_DE PUNTO_COMA 
		{
			if(mode == CONFIGURATION_MODE) {
				if(param1.isNumber() && param2.isNumber()) {
					Position newExit = new Position((int)Float.parseFloat(param1.getValor()),(int)Float.parseFloat(param2.getValor()));
					
					if(board.setExitPos(newExit)) {
						System.out.println("Exit set on column "+board.getExitPos().getX()+" row "+board.getExitPos().getY());
					} else {
						System.out.println("The given board position is not empty or not exists");
					}
				} else {
					System.err.println("Line " + e21.getLine() + ":" + e21.getColumn() + " - " + "Parameters must be numbers");	
				}
			} else {
				System.err.println("Line " + e21.getLine() + ":" + e21.getColumn() + " - " + "This instruction has to be called in Configuration Mode");
			}
		}
				
		| e22:FUNC_GETEXIT PARENT_IZ  PARENT_DE PUNTO_COMA 
		{
			if(mode == CONFIGURATION_MODE) {
				System.out.println("Exit is on column "+board.getExitPos().getX()+" row "+board.getExitPos().getY());
			} else {
				System.err.println("Line " + e22.getLine() + ":" + e22.getColumn() + " - " + "This instruction has to be called in Configuration Mode");
			}
		}
				
		//Esto en realidad no tiene pinta de que se vaya a utilizar en el conf mode
		| e23:FUNC_GETMECCA PARENT_IZ PARENT_DE PUNTO_COMA 
		{
			if(mode == CONFIGURATION_MODE || mode == ADVENTURE_MODE) {
				System.out.println("Mecca is on row "+ board.getMeccaPos().getY()+" column " + board.getMeccaPos().getX());
			} else {
				System.err.println("Line " + e23.getLine() + ":" + e23.getColumn() + " - " + "This instruction has to be called in Configuration Mode or Adventure Mode");
			}
		}
					
		| e24:FUNC_SETARROWS PARENT_IZ param1=expression PARENT_DE PUNTO_COMA 
		{
			if(mode == CONFIGURATION_MODE) {
				if(param1.isNumber()) {
					board.setMeccaNArrows((int)Float.parseFloat(param1.getValor()));
					System.out.println("Mecca has now "+board.getMeccaNArrows()+" arrows");
				} else {
					System.err.println("Line " + e24.getLine() + ":" + e24.getColumn() + " - " + "Parameter must be number");	
				}
			} else {
				System.err.println("Line " + e24.getLine() + ":" + e24.getColumn() + " - " + "This instruction has to be called in Configuration Mode");
			} 				
		}
		
		| e25:FUNC_GETARROWS PARENT_IZ PARENT_DE PUNTO_COMA 
		{
			if(mode == CONFIGURATION_MODE || mode == ADVENTURE_MODE) {
				System.out.println("Mecca has "+board.getMeccaNArrows()+" arrows");
			} else {
				System.err.println("Line " + e25.getLine() + ":" + e25.getColumn() + " - " + "This instruction has to be called in Configuration Mode or Adventure Mode");
			}						
		}
					
		| e26:FUNC_INCARROWS PARENT_IZ param1=expression PARENT_DE PUNTO_COMA 
		{
			if(mode == CONFIGURATION_MODE) {
				if(param1.isNumber()) {
					if((int)Float.parseFloat(param1.getValor()) >= 0) {
						board.incMeccaNArrows((int)Float.parseFloat(param1.getValor()));
						System.out.println("Arrows incremented in "+param1.getValor()+", Mecca has now "+board.getMeccaNArrows()+" arrows");	
					} else {
						System.out.println("You have to enter an integer bigger than 0");
					}
				} else {
					System.err.println("Line " + e26.getLine() + ":" + e26.getColumn() + " - " + "Parameter must be number");	
				}
			} else {
				System.err.println("Line " + e26.getLine() + ":" + e26.getColumn() + " - " + "This instruction has to be called in Configuration Mode");
			}	
		}
					
		| e27:FUNC_DECARROWS PARENT_IZ param1=expression PARENT_DE PUNTO_COMA 
		{
			if(mode == CONFIGURATION_MODE) {
				if(param1.isNumber()) {						
					if((int)Float.parseFloat(param1.getValor()) >= 0) {
						board.decMeccaNArrows((int)Float.parseFloat(param1.getValor()));
						System.out.println("Arrows decremented in "+param1.getValor()+", Mecca has now "+board.getMeccaNArrows()+" arrows");	
					} else {
						System.out.println("The number of arrows has to be positive");
					}
				} else {
					System.err.println("Line " + e27.getLine() + ":" + e27.getColumn() + " - " + "Parameter must be number");	
				}
			} else {
				System.err.println("Line " + e27.getLine() + ":" + e27.getColumn() + " - " + "This instruction has to be called in Configuration Mode");
			}	
		}
					
		| e28:FUNC_GETREMAININGTREASURES PARENT_IZ PARENT_DE PUNTO_COMA 
		{
			if(mode == ADVENTURE_MODE) {
				System.out.println(board.getTotalTreasures() + " treasures remaining");
			} else {
				System.err.println("Line " + e28.getLine() + ":" + e28.getColumn() + " - " + "This instruction has to be called in Adventure Mode");
			}
		}
					
		| e29:FUNC_SHOOTLEFT PARENT_IZ PARENT_DE PUNTO_COMA 
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
				System.err.println("Line " + e29.getLine() + ":" + e29.getColumn() + " - " + "This instruction has to be called in Adventure Mode");
			}
		}
					
		| e30:FUNC_SHOOTRIGHT PARENT_IZ PARENT_DE PUNTO_COMA 
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
				System.err.println("Line " + e30.getLine() + ":" + e30.getColumn() + " - " + "This instruction has to be called in Adventure Mode");
			}
		}
					
		| e31:FUNC_SHOOTUP PARENT_IZ PARENT_DE PUNTO_COMA 
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
				System.err.println("Line " + e31.getLine() + ":" + e31.getColumn() + " - " + "This instruction has to be called in Adventure Mode");
			}
		}
					
		| e32:FUNC_SHOOTDOWN PARENT_IZ PARENT_DE PUNTO_COMA 
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
				System.err.println("Line " + e32.getLine() + ":" + e32.getColumn() + " - " + "This instruction has to be called in Adventure Mode");
			}
		}
					
		| e33:FUNC_GOLEFT PARENT_IZ PARENT_DE PUNTO_COMA 
		{
			if(mode == ADVENTURE_MODE) {
				if(!board.isGameFinished()) {
					board.meccaGoLeft();
					board.showAdventureState();
				} else {
					System.out.println("The game has finished!");	
				}
			} else {
				System.err.println("Line " + e33.getLine() + ":" + e33.getColumn() + " - " + "This instruction has to be called in Adventure Mode");
			}
		}
			
		| e34:FUNC_GORIGHT PARENT_IZ PARENT_DE PUNTO_COMA 
		{
			if(mode == ADVENTURE_MODE) {
				if(!board.isGameFinished()) {
					board.meccaGoRight();
					board.showAdventureState();
				} else {
					System.out.println("The game has finished!");	
				}
			} else {
				System.err.println("Line " + e34.getLine() + ":" + e34.getColumn() + " - " + "This instruction has to be called in Adventure Mode");
			}
		}
				
		| e35:FUNC_GOUP PARENT_IZ PARENT_DE PUNTO_COMA 
		{
			if(mode == ADVENTURE_MODE) {
				if(!board.isGameFinished()) {
					board.meccaGoUp();
					board.showAdventureState();
				} else {
					System.out.println("The game has finished!");	
				}
			} else {
				System.err.println("Line " + e35.getLine() + ":" + e35.getColumn() + " - " + "This instruction has to be called in Adventure Mode");
			}
		}
			
		| e36:FUNC_GODOWN PARENT_IZ PARENT_DE PUNTO_COMA 
		{
			if(mode == ADVENTURE_MODE) {
				if(!board.isGameFinished()) {
					board.meccaGoDown();
					board.showAdventureState();
				} else {
					System.out.println("The game has finished!");	
				}
			} else {
				System.err.println("Line " + e36.getLine() + ":" + e36.getColumn() + " - " + "This instruction has to be called in Adventure Mode");
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
	{
		Variable e = null;
	}
	:   
	
		e37:TIPO_NUMERO (i:IDENT OP_ASIG e=expression | parametros_number) PUNTO_COMA  //Añadido por mí el ;
		{
			if(i != null) {
				// Se toma el nombre del identificador
				String nombre = i.getText();
	
				// El número se convierte en cadena
				String valorCadena = e.getValor().toString();
				
				// Se inserta en la tabla de Símbolos
				try {
					Float.parseFloat(e.getValor());
					
					if(!insertarIdentificador(nombre,"number",valorCadena)) {
						System.err.println("Line " + e37.getLine() + ":" + e37.getColumn() + " - " + "Variable \"" + nombre + "\" is already declared"); 	
					}
				} catch(NumberFormatException err) {
					System.err.println("Line " + e37.getLine() + ":" + e37.getColumn() + " - " + "Variable \"" + nombre + "\" has not received a number"); 	
				}
			}
		}
		
		| e38:TIPO_CADENA (i2:IDENT OP_ASIG e=expression | parametros_string) PUNTO_COMA
	 	{
			if(i2 != null && e != null) {
				// Se toma el nombre del identificador
				String nombre = i2.getText();
				
				String valorCadena = e.getValor();
				
				try {
					Float.parseFloat(e.getValor());
					
					if(e.getTipo().equals("number")) {
						System.err.println("Line " + e38.getLine() + ":" + e38.getColumn() + " - " + "Variable \"" + nombre + "\" has not received a string");
					} else {
						insertarIdentificador(nombre,"string",valorCadena);
					}
				} catch(NumberFormatException err) {
					// Se inserta en la tabla de Símbolos
					if(!insertarIdentificador(nombre,"string",valorCadena)) {
						System.err.println("Line " + e38.getLine() + ":" + e38.getColumn() + " - " + "Variable \"" + nombre + "\" is already declared"); 	
					}
				}
			}
		}
		
		| i3:IDENT OP_ASIG e=expression PUNTO_COMA
		{
			// Se toma el nombre del identificador
			String nombre = i3.getText();
			
			int indice = symbolsTable.existeSimbolo(nombre);
			
			if(indice >= 0) {
				Variable simbolo = symbolsTable.getSimbolo(indice);
				
				if(simbolo.getTipo().equals("number")) {
					try {
						Float value = Float.parseFloat(e.getValor().toString());
						simbolo.setValor(String.valueOf(value));
						
					} catch(NumberFormatException err) {
						System.err.println("Line " + i3.getLine() + ":" + i3.getColumn() + " - " + "Variable \"" + nombre + "\" es de tipo \"number\"");
					}
				} else {
					simbolo.setValor(e.getValor().toString());
				}
			} else {
				System.err.println("Line " + i3.getLine() + ":" + i3.getColumn() + " - " + "La variable \"" + nombre + "\" no ha sido declarada");
			}		
		}
	;
  	
  	exception
 		catch [RecognitionException re] {
			mostrarExcepcion(re);
		}

expression 
	// Valor que devuelve
	returns [Variable result = new Variable("","","");]
	// Variables locales
	{
		Variable e1, e2;
	} 
	:
		e1=addend {result = e1;} 
		(
			// Suma
			(
			OP_MAS e2 = addend
				{
					
					if(e2.getTipo().equals("number") && result.getTipo().equals("number")) {
				 		result = new Variable("", "number", String.valueOf(Float.parseFloat(result.getValor()) + Float.parseFloat(e2.getValor())));
					} else if(e2.getTipo().equals("string") && result.getTipo().equals("string") ||
						e2.getTipo().equals("number") && result.getTipo().equals("string") ||
						e2.getTipo().equals("string") && result.getTipo().equals("number")) {
						result = new Variable("", "string", result.getValor() + e2.getValor());
					}
				}
			)
			 // Resta
			| (
			OP_MENOS e2 = addend
				{	
					if(e2.getTipo().equals("number") && result.getTipo().equals("number")) {
				 		result = new Variable("", "number", String.valueOf(Float.parseFloat(result.getValor()) - Float.parseFloat(e2.getValor())));
					}
				}
			 )
		)*
		|	e1=negative {result = e1;} 
			(
				// Suma
				(
			 		OP_MAS e2 = addend
					{
						if(e2.getTipo().equals("number") && result.getTipo().equals("number")) {
							result = new Variable("", "number", String.valueOf(Float.parseFloat(result.getValor()) + Float.parseFloat(e2.getValor())));
						}
					}
				)
			 // Resta
			|	(
			 		OP_MENOS e2 = addend
					{
				 		if(e2.getTipo().equals("number") && result.getTipo().equals("number")) {
				 			result = new Variable("", "number", String.valueOf(Float.parseFloat(result.getValor()) - Float.parseFloat(e2.getValor())));
						}
					}
				)
			)*

	;
	
	exception
 		catch [RecognitionException re] {
			mostrarExcepcion(re);
		}

addend 
	// Valor que devuelve
	returns [Variable result = new Variable("","","");]

	// Variables locales
	{
		Variable e1, e2;
	} 
	:
		e1=factor {result = e1;} 
		(
			// Producto
			(
				OP_PRODUCTO e2=factor
				{
					if(e2.getTipo().equals("number") && result.getTipo().equals("number")) {
						result = new Variable("","number",String.valueOf(Float.parseFloat(result.getValor()) * Float.parseFloat(e2.getValor())));
					}
				}
			)
		
			// Division
			|	(
			 	OP_DIVISION e2=factor
				{
					if(e2.getTipo().equals("number") && result.getTipo().equals("number")) {
						result = new Variable("","number",String.valueOf(Float.parseFloat(result.getValor()) / Float.parseFloat(e2.getValor())));
					}
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
	returns [Variable result = new Variable("","","");] 

	// Variables locales
	{
		Variable e;
	} 
	:
		i:IDENT 
		{
			// Busca el identificador en la tabla de símbolos
			int indice = symbolsTable.existeSimbolo(i.getText());

			// Si encuentra el identificador, devuelve su valor
			if (indice >= 0) {
				// Se recupera el valor almacenado como cadena
				//String valorCadena = symbolsTable.getSimbolo(indice).getValor();

				// La cadena se convierte a número real
				
				result = symbolsTable.getSimbolo(indice);
			} else
				System.err.println("Line " + i.getLine() + ":" + i.getColumn() + " - " + "El identificador " + i.getText() + " está indefinido");
		}

		| n:LIT_NUMERO  //Ponía número aquí
		{
			result = new Variable("","number",n.getText());
		}
			
		| n2:LIT_CADENA  
		{
			result = new Variable("","string",n2.getText());
		}

		| PARENTESIS_IZ e=expression PARENTESIS_DE
		{
			result = e;
		}
	;
	
	exception
 		catch [RecognitionException re] {
			mostrarExcepcion(re);
		}
	    
// Expresiones que empiezan por el signo menos
negative
	// Valor que devuelve
	returns [Variable result = new Variable("","","");] 

	// Variables locales
	{
		Variable e;
	} 
	:
		OP_MENOS e = factor
		{
			if(e.getTipo().equals("number")) {
				result = new Variable("","number",String.valueOf(Float.parseFloat(e.getValor()) * -1));
			}
		}
	;
		    
conditional_sentence
	// Variable local
	{
		boolean valor;
	}
	:
		RES_SI valor=logical_condition  
	  	RES_ENTONCES 
		(
			// Si la condición es verdadera, se ejecuta el consecuente
			{valor==true}? (instruction)+
	
			// Si hay parte alternativa, se omite
			(
				RES_SI_NO
				(options {greedy=false;}:.)+
			)?RES_FIN_SI PUNTO_COMA
			|
			// Si la condición es false, se omite el consecuente
	  		{valor==false}? (options {greedy=false;}:.)+
	
			// Si hay parte alternativa, se ejecuta
			(
				RES_SI_NO
					(instruction)+
			)?RES_FIN_SI PUNTO_COMA
			
		)
	;
	
	exception
 		catch [RecognitionException re] {
			mostrarExcepcion(re);
		}

logical_condition
	// Valor que devuelve
	returns [boolean result = false]

	// Variables locales
	{
		boolean e1=false, e2=false;
	}
	:
	(i1:OP_NO)?
	e1=condition
	{
		if(i1!=null)
			result = !e1;
		else
			result = e1;
	}
	// Comienzo de las alternativas
		(
			OP_Y (i2:OP_NO)?e2=condition
			{
				if(i2!=null)
					e2 = !e2;

				if(e1 == true && e2 == true)
					result = true;
			}
		
		|
		
			OP_O (i3:OP_NO)?e2=condition
			{
				if(i3!=null)
					e2 = !e2;
					
				if(e1 == true || e2 == true)
					result = true;
			}

		)*
	;
			
condition
	// Valor que devuelve
	returns [boolean result = false]

	// Variables locales
	{
		Variable e1, e2;
		boolean not=false;
	}
	:
		e1=expression
		// Comienzo de las alternativas
		(
			OP_IGUAL e2=expression
			{
				if(e1.getTipo().equals("number") && e2.getTipo().equals("number")) {
					if (Float.parseFloat(e1.getValor()) == Float.parseFloat(e2.getValor()))
						result = true;
					else 
						result = false;
				} else if(e1.getTipo().equals("string") && e2.getTipo().equals("string")) {
					if (e1.getValor().equals(e2.getValor()))
						result = true;
					else 
						result = false;
				}
			}
			
			| OP_DISTINTO e2=expression
			{
				if(e1.getTipo().equals("number") && e2.getTipo().equals("number")) {
					if (Float.parseFloat(e1.getValor()) != Float.parseFloat(e2.getValor()))
						result = true;
					else 
						result = false;
				} else if(e1.getTipo().equals("string") && e2.getTipo().equals("string")) {
					if (!e1.getValor().equals(e2.getValor()))
						result = true;
					else 
						result = false;
				}
			}
        	
        	| OP_MENOR e2=expression
			{
				if(e1.getTipo().equals("number") && e2.getTipo().equals("number")) {
					if (Float.parseFloat(e1.getValor()) < Float.parseFloat(e2.getValor()))
						result = true;
					else 
						result = false;
				} else if(e1.getTipo().equals("string") && e2.getTipo().equals("string")) {
					result = false;
				}
			}
        	
        	| OP_MENOR_IGUAL e2=expression
			{
				if(e1.getTipo().equals("number") && e2.getTipo().equals("number")) {
					if (Float.parseFloat(e1.getValor()) <= Float.parseFloat(e2.getValor()))
						result = true;
					else 
						result = false;
				} else if(e1.getTipo().equals("string") && e2.getTipo().equals("string")) {
					result = false;
				}
			}
        	
        	| OP_MAYOR_IGUAL e2=expression
			{
				if(e1.getTipo().equals("number") && e2.getTipo().equals("number")) {
					if (Float.parseFloat(e1.getValor()) >= Float.parseFloat(e2.getValor()))
						result = true;
					else 
						result = false;
				} else if(e1.getTipo().equals("string") && e2.getTipo().equals("string")) {
					result = false;
				}
			}
        	
        	| OP_MAYOR e2=expression
			{
				if(e1.getTipo().equals("number") && e2.getTipo().equals("number")) {
					if (Float.parseFloat(e1.getValor()) > Float.parseFloat(e2.getValor()))
						result = true;
					else 
						result = false;
				} else if(e1.getTipo().equals("string") && e2.getTipo().equals("string")) {
					result = false;
				}
			}
		) // Fin de las alternativas
	;
	
	exception
 		catch [RecognitionException re] {
			mostrarExcepcion(re);
		}

while_loop
	// Variables locales
	{
		boolean valor; int marca=-1;
	}
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
	{
		boolean valor=false; int marca=-1;
	}
	:
		// Se establece una marca para indicar el punto de inicio del bucle
		{marca = mark();}
		RES_REPETIR
		( // Comienzo de las alternativas

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
	{
		int marca=-1;
		Variable initValue=new Variable("","number","-1"), endValue=new Variable("","number","-1"), inc=new Variable("","number","-1");
		int index=-1;
		String id;
		boolean firstTimeTest=true;
	}
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
				String stringValue = initValue.getValor();
			
				// Se inserta en la tabla de Símbolos
				insertarIdentificador(name,"number",stringValue);
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
			{Float.parseFloat(symbolsTable.getSimbolo(index).getValor()) >= Float.parseFloat(endValue.getValor())}? (options {greedy=false;}:.)*  RES_FIN_PARA PUNTO_COMA

			// Si la condición es verdadera, se ejecutan las instrucciones del bucle
		| {Float.parseFloat(symbolsTable.getSimbolo(index).getValor()) < Float.parseFloat(endValue.getValor())}? (instruction)+ {symbolsTable.getSimbolo(index).setValor(String.valueOf(Float.parseFloat(symbolsTable.getSimbolo(index).getValor())+Float.parseFloat(inc.getValor())));} RES_FIN_PARA PUNTO_COMA
			// Se indica que se repita la ejecución del bucle_mientras
			{
				rewind(marca); 
				this.for_loop();
			}
		) // Fin de las alternativas	
	;
		   
parametros_string
	{
		String param1 = null;
	}
	:
		param1=valorparametro (parametros_prima_string)*
		{
			// Se inserta en la tabla de Símbolos
			insertarIdentificador(param1,"string","");	
		}
	;
		
parametros_number
	{
		String param1 = null;
	}
	:
		param1=valorparametro (parametros_prima_number)*
		{
			// Se inserta en la tabla de Símbolos
			insertarIdentificador(param1,"number","0");	
		}
	;

parametros_prima_string
	{
		String value = null;
	}
	:
		COMA value = valorparametro
		{
			// Se inserta en la tabla de Símbolos
			insertarIdentificador(value,"string","");
		}
	;

parametros_prima_number
	{
		String value = null;
	}
	:
		COMA value = valorparametro
		{
			// Se inserta en la tabla de Símbolos
			insertarIdentificador(value,"number","0");
		}
	;

nombrefuncion: IDENT;

valorparametro
	returns [String result = new String();]
	:
		i:IDENT
		{	
			result = i.getText();
		}
	;

//variable: IDENT;

//entero returns [int value=0]: 
		//i1:LIT_ENTERO				{value = Integer.parseInt(i1.getText());}
		
//;


//HECHO POR TEAM MECCA
