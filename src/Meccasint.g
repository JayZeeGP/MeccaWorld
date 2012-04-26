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
  // Atributo de Anasint para contar las instrucciones reconocidas
  int contador = 0;
  Board board = new Board();
  
}




//HECHO POR TEAM MECCA

mecca: configuration adventure;

configuration: BEGIN_CONF (instructionConf)+ END_CONF;

adventure: BEGIN_ADV (instructionAd)+ END_ADV;

instructionConf{int param1, param2;}: FUNC_LEER PARENT_IZ PARENT_DE PUNTO_COMA 
					{
						System.out.println("Reading");System.out.println(board.toString());
					}
					
				| FUNC_SETBOARDSIZE PARENT_IZ param1=entero COMA param2=entero PARENT_DE PUNTO_COMA 
					{
						Size newSize = new Size(param1,param2);
						board.setSize(newSize);
						System.out.println("Board has now "+board.getSize().getWidth()+" columns and "+board.getSize().getHeight()+" rows");
					}
					
				| FUNC_GETBOARDROWS PARENT_IZ PARENT_DE PUNTO_COMA 
					{
						System.out.println("Board has "+board.getSize().getHeight()+" rows");
					}
					
				| FUNC_GETBOARDCOLUMNS PARENT_IZ PARENT_DE PUNTO_COMA 
					{
						System.out.println("Board has "+board.getSize().getWidth()+" columns");
					}
					
				| FUNC_GETBOARDSIZE PARENT_IZ PARENT_DE PUNTO_COMA 
					{
						System.out.println("Board size is "+board.getSize().getHeight()+" rows and "+board.getSize().getWidth()+" columns");
					}
					
				| FUNC_SETTREASURE PARENT_IZ param1=entero COMA param2=entero PARENT_DE PUNTO_COMA 
					{
						Position newTreasure = new Position(param1,param2);
						int position = board.setTreasurePos(newTreasure);
						System.out.println("Treasure "+(position+1)+" set on column "+board.getTreasurePos(position).getX()+" row "+board.getTreasurePos(position).getY());
					}
					
				| FUNC_GETTOTALTREASURES PARENT_IZ PARENT_DE PUNTO_COMA 
					{
						System.out.println("The number of total treasures is: "+board.getTotalTreasures());
					}
					
				| FUNC_GETTREASURE PARENT_IZ param1=entero PARENT_DE PUNTO_COMA 
					{
						//Check if that treasure exists
						if(board.getTotalTreasures()>=param1&&param1>0)
							System.out.println("Treasure "+(param1)+" set on column "+board.getTreasurePos(param1-1).getX()+" row "+board.getTreasurePos(param1-1).getY());
						else
							System.out.println("There is no treasure with that number");
					}
				
				| FUNC_SETHOLE PARENT_IZ param1=entero COMA param2=entero PARENT_DE PUNTO_COMA 
					{
						Position newHole = new Position(param1,param2);
						int position = board.setTreasurePos(newHole);
						System.out.println("Hole "+(position+1)+" set on column "+board.getHolePos(position).getX()+" row "+board.getHolePos(position).getY());
					}
					
				| FUNC_GETNUMBEROFHOLES PARENT_IZ PARENT_DE PUNTO_COMA 
					{
						System.out.println("The number of holes is: "+board.getNumberOfHoles());
					}
					
				| FUNC_GETHOLE PARENT_IZ param1=entero PARENT_DE PUNTO_COMA 
					{
						//Check if that hole exists
						if(board.getNumberOfHoles()>=param1&&param1>0)
							System.out.println("Treasure "+(param1)+" set on column "+board.getHolePos(param1-1).getX()+" row "+board.getHolePos(param1-1).getY());
						else
							System.out.println("There is no treasure with that number");
					}
					
				| FUNC_SETWUMPUS PARENT_IZ param1=entero COMA param2=entero PARENT_DE PUNTO_COMA 
					{
						Position newWumpus = new Position(param1,param2);
						board.setWumpusPos(newWumpus);
						System.out.println("Wumpus set on column "+board.getWumpusPos().getX()+" row "+board.getWumpusPos().getY());
					}
					
				| FUNC_GETWUMPUS PARENT_IZ PARENT_DE PUNTO_COMA 
					{
						System.out.println("The Wumpus is on column "+ board.getWumpusPos().getX()+" row " + board.getWumpusPos().getY());
					}
					
				| FUNC_SETSTART PARENT_IZ param1=entero COMA param2=entero PARENT_DE PUNTO_COMA 
					{
						Position newStart = new Position(param1,param2);
						board.setStartPos(newStart);
						System.out.println("Start set on column "+board.getStartPos().getX()+" row "+board.getStartPos().getY());
					}
					
				| FUNC_GETSTART PARENT_IZ PARENT_DE PUNTO_COMA 
					{
						System.out.println("Start is on column "+board.getStartPos().getX()+" row "+board.getStartPos().getY());	
					}
					
				| FUNC_SETEXIT PARENT_IZ param1=entero COMA param2=entero PARENT_DE PUNTO_COMA 
					{
						Position newExit = new Position(param1,param2);
						board.setExitPos(newExit);
						System.out.println("Exit set on column "+board.getExitPos().getX()+" row "+board.getExitPos().getY());
					}
					
				| FUNC_GETEXIT PARENT_IZ  PARENT_DE PUNTO_COMA 
					{
						System.out.println("Exit is on column "+board.getExitPos().getX()+" row "+board.getExitPos().getY());
					}
					
					//Esto en realidad no tiene pinta de que se vaya a utilizar en el conf mode
				| FUNC_GETMECCA PARENT_IZ PARENT_DE PUNTO_COMA 
					{
						System.out.println("Mecca is on row "+ board.getMeccaPos().getY()+" column " + board.getMeccaPos().getX());
					}
					
				| FUNC_SETARROWS PARENT_IZ param1=entero PARENT_DE PUNTO_COMA 
					{
						board.setMeccaNArrows(param1);
						System.out.println("Mecca has now "+board.getMeccaNArrows()+" arrows"); 				
					}
					
				| FUNC_GETARROWS PARENT_IZ PARENT_DE PUNTO_COMA 
					{
						System.out.println("Mecca has "+board.getMeccaNArrows()+" arrows");
					}
					
				| FUNC_INCARROWS PARENT_IZ param1=entero PARENT_DE PUNTO_COMA 
					{	
						if(param1>=0){
							board.incMeccaNArrows(param1);
							System.out.println("Arrows incremented in "+param1+", Mecca has now "+board.getMeccaNArrows()+" arrows");	
						}
						else
							System.out.println("You have to enter an integer bigger than 0");
					}
					
				| FUNC_DECARROWS PARENT_IZ param1=entero PARENT_DE PUNTO_COMA 
					{
						if(param1>=0){
							board.decMeccaNArrows(param1);
							System.out.println("Arrows decremented in "+param1+", Mecca has now "+board.getMeccaNArrows()+" arrows");	
						}
						else
							System.out.println("You have to enter an integer bigger than 0");
					}
               ;

instructionAd: FUNC_LEER PARENT_IZ (parametros)* PARENT_DE PUNTO_COMA
				| FUNC_GETBOARDROWS PARENT_IZ PARENT_DE PUNTO_COMA {System.out.println("Board has X rows");}
				| FUNC_GETBOARDCOLUMNS PARENT_IZ PARENT_DE PUNTO_COMA {System.out.println("Board has X columns");}
				| FUNC_GETBOARDSIZE PARENT_IZ PARENT_DE PUNTO_COMA {System.out.println("Board size is X rows and Y columns");}
				| FUNC_GETTOTALTREASURES PARENT_IZ PARENT_DE PUNTO_COMA {System.out.println("Treasures are:");}
				| FUNC_GETMECCA PARENT_IZ PARENT_DE PUNTO_COMA {System.out.println("Mecca is on row X column Y");}
				| FUNC_GETREMAININGTREASURES PARENT_IZ PARENT_DE PUNTO_COMA {System.out.println("X Treasures remaining");}
				| FUNC_GETARROWS PARENT_IZ PARENT_DE PUNTO_COMA {System.out.println("Mecca has X arrows");}
				| FUNC_SHOOTLEFT PARENT_IZ PARENT_DE PUNTO_COMA {System.out.println("Mecca shot left");}
				| FUNC_SHOOTRIGHT PARENT_IZ PARENT_DE PUNTO_COMA {System.out.println("Mecca shot right");}
				| FUNC_SHOOTUP PARENT_IZ PARENT_DE PUNTO_COMA {System.out.println("Mecca shot up");}
				| FUNC_SHOOTDOWN PARENT_IZ PARENT_DE PUNTO_COMA {System.out.println("Mecca shot down");}
				| FUNC_GOLEFT PARENT_IZ PARENT_DE PUNTO_COMA {System.out.println("Mecca went left");}
				| FUNC_GORIGHT PARENT_IZ PARENT_DE PUNTO_COMA {System.out.println("Mecca went right");}
				| FUNC_GOUP PARENT_IZ PARENT_DE PUNTO_COMA {System.out.println("Mecca went up");}
				| FUNC_GODOWN PARENT_IZ PARENT_DE PUNTO_COMA {System.out.println("Mecca went down");}

               ;
               
parametros: valorparametro (parametros_prima)*;

parametros_prima: COMA valorparametro;

nombrefuncion: IDENT;

valorparametro: IDENT;

entero returns [int value=0]: 
		i1:LIT_ENTERO				{value = Integer.parseInt(i1.getText());}
		
;


//HECHO POR TEAM MECCA
