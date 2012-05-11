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
  
}




//HECHO POR TEAM MECCA

mecca: configuration adventure;

configuration: BEGIN_CONF {mode = CONFIGURATION_MODE;}(instruction)+ END_CONF;

adventure: BEGIN_ADV {if(board.initGame()) mode = ADVENTURE_MODE;} (instruction)+ END_ADV;

instruction{int param1, param2;
				String info;}: 
				
				FUNC_LEER PARENT_IZ PARENT_DE PUNTO_COMA 
					{
						System.out.println("Reading");System.out.println(board.toString());
					}
					
				| FUNC_SETBOARDSIZE PARENT_IZ param1=entero COMA param2=entero PARENT_DE PUNTO_COMA 
					{
						if(mode == CONFIGURATION_MODE) {
							Size newSize = new Size(param1,param2);
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
					
				| FUNC_SETTREASURE PARENT_IZ param1=entero COMA param2=entero PARENT_DE PUNTO_COMA 
					{
						if(mode == CONFIGURATION_MODE) {
							Position newTreasure = new Position(param1,param2);
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
				| FUNC_REMOVETREASURE PARENT_IZ param1=entero PARENT_DE PUNTO_COMA
					{						
						if(board.removeTreasure(param1)) {
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
					
				| FUNC_GETTREASURE PARENT_IZ param1=entero PARENT_DE PUNTO_COMA 
					{
						if(mode == CONFIGURATION_MODE) {						
							//Check if that treasure exists
							if(board.getTotalTreasures()>=param1&&param1>0) {
								System.out.println("Treasure "+(param1)+" set on column "+board.getTreasurePos(param1-1).getX()+" row "+board.getTreasurePos(param1-1).getY());
							} else {
								System.out.println("There is no treasure with that number");
							}
						} else {
							System.out.println("This instruction has to be called in Configuration Mode");
						}
					}
				
				| FUNC_SETHOLE PARENT_IZ param1=entero COMA param2=entero PARENT_DE PUNTO_COMA 
					{
						if(mode == CONFIGURATION_MODE) {
							Position newHole = new Position(param1,param2);
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
				
				| FUNC_REMOVEHOLE PARENT_IZ param1=entero PARENT_DE PUNTO_COMA
					{						
						if(board.removeHole(param1)) {
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
					
				| FUNC_GETHOLE PARENT_IZ param1=entero PARENT_DE PUNTO_COMA 
					{
						if(mode == CONFIGURATION_MODE) {
							//Check if that hole exists
							if(board.getNumberOfHoles()>=param1&&param1>0) {
								System.out.println("Hole "+(param1)+" set on column "+board.getHolePos(param1-1).getX()+" row "+board.getHolePos(param1-1).getY());
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
					
				| FUNC_SETWUMPUS PARENT_IZ param1=entero COMA param2=entero PARENT_DE PUNTO_COMA 
					{
						if(mode == CONFIGURATION_MODE) {
							Position newWumpus = new Position(param1,param2);
							
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
					
				| FUNC_SETSTART PARENT_IZ param1=entero COMA param2=entero PARENT_DE PUNTO_COMA 
					{
						if(mode == CONFIGURATION_MODE) {
							Position newStart = new Position(param1,param2);
							
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
				
				| FUNC_SETEXIT PARENT_IZ param1=entero COMA param2=entero PARENT_DE PUNTO_COMA 
					{
						if(mode == CONFIGURATION_MODE) {
							Position newExit = new Position(param1,param2);
							
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
					
				| FUNC_SETARROWS PARENT_IZ param1=entero PARENT_DE PUNTO_COMA 
					{
						if(mode == CONFIGURATION_MODE) {
							board.setMeccaNArrows(param1);
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
					
				| FUNC_INCARROWS PARENT_IZ param1=entero PARENT_DE PUNTO_COMA 
					{
						if(mode == CONFIGURATION_MODE) {
							if(param1>=0) {
								board.incMeccaNArrows(param1);
								System.out.println("Arrows incremented in "+param1+", Mecca has now "+board.getMeccaNArrows()+" arrows");	
							}
							else {
								System.out.println("You have to enter an integer bigger than 0");
							}
						} else {
							System.out.println("This instruction has to be called in Configuration Mode");
						}	
					}
					
				| FUNC_DECARROWS PARENT_IZ param1=entero PARENT_DE PUNTO_COMA 
					{
						if(mode == CONFIGURATION_MODE) {						
							if(param1>=0) {
								board.decMeccaNArrows(param1);
								System.out.println("Arrows decremented in "+param1+", Mecca has now "+board.getMeccaNArrows()+" arrows");	
							}
							else {
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
							System.out.println("Mecca shot left");
						} else {
							System.out.println("This instruction has to be called in Adventure Mode");
						}
					}
					
				| FUNC_SHOOTRIGHT PARENT_IZ PARENT_DE PUNTO_COMA 
					{
						if(mode == ADVENTURE_MODE) {
							System.out.println("Mecca shot right");
						} else {
							System.out.println("This instruction has to be called in Adventure Mode");
						}
					}
					
				| FUNC_SHOOTUP PARENT_IZ PARENT_DE PUNTO_COMA 
					{
						if(mode == ADVENTURE_MODE) {
							System.out.println("Mecca shot up");
						} else {
							System.out.println("This instruction has to be called in Adventure Mode");
						}
					}
					
				| FUNC_SHOOTDOWN PARENT_IZ PARENT_DE PUNTO_COMA 
					{
						if(mode == ADVENTURE_MODE) {
							System.out.println("Mecca shot down");
						} else {
							System.out.println("This instruction has to be called in Adventure Mode");
						}
					}
					
				| FUNC_GOLEFT PARENT_IZ PARENT_DE PUNTO_COMA 
					{
						if(mode == ADVENTURE_MODE) {
							System.out.println("Mecca went left");
						} else {
							System.out.println("This instruction has to be called in Adventure Mode");
						}
					}
					
				| FUNC_GORIGHT PARENT_IZ PARENT_DE PUNTO_COMA 
					{
						if(mode == ADVENTURE_MODE) {
							System.out.println("Mecca went right");
						} else {
							System.out.println("This instruction has to be called in Adventure Mode");
						}
					}
					
				| FUNC_GOUP PARENT_IZ PARENT_DE PUNTO_COMA 
					{
						if(mode == ADVENTURE_MODE) {
							System.out.println("Mecca went up");
						} else {
							System.out.println("This instruction has to be called in Adventure Mode");
						}
					}
					
				| FUNC_GODOWN PARENT_IZ PARENT_DE PUNTO_COMA 
					{
						if(mode == ADVENTURE_MODE) {
							System.out.println("Mecca went down");
						} else {
							System.out.println("This instruction has to be called in Adventure Mode");
						}
					}

               ;
               
parametros: valorparametro (parametros_prima)*;

parametros_prima: COMA valorparametro;

nombrefuncion: IDENT;

valorparametro: IDENT;

entero returns [int value=0]: 
		i1:LIT_ENTERO				{value = Integer.parseInt(i1.getText());}
		
;


//HECHO POR TEAM MECCA
