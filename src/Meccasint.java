// $ANTLR : "Meccasint.g" -> "Meccasint.java"$

import antlr.TokenBuffer;
import antlr.TokenStreamException;
import antlr.TokenStreamIOException;
import antlr.ANTLRException;
import antlr.LLkParser;
import antlr.Token;
import antlr.TokenStream;
import antlr.RecognitionException;
import antlr.NoViableAltException;
import antlr.MismatchedTokenException;
import antlr.SemanticException;
import antlr.ParserSharedInputState;
import antlr.collections.impl.BitSet;

public class Meccasint extends antlr.LLkParser       implements MeccasintTokenTypes
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
	private int insertarIdentificador(String nombre, String tipo, String valorCadena)
		{
			int insertado = 1;
			
			// Busca el identificador en la tabla de símbolos
			int indice = symbolsTable.existeSimbolo(nombre);

			// Si encuentra el identificador, le modifica su valor
			if (indice >= 0)
			{
				if(symbolsTable.getSimbolo(indice).getTipo().equals("number")) {
					try {
						Float.parseFloat(valorCadena);
						symbolsTable.getSimbolo(indice).setValor(valorCadena);
						insertado = 1;
					} catch(NumberFormatException e) {
						insertado = -2;
					}
				} else {
					symbolsTable.getSimbolo(indice).setValor(valorCadena);
					insertado = 1;
				}
			}
			// Si no lo encuentra, lo inserta en la tabla de símbolos
			else
			{
				if(!tipo.equals("null")) {
				
					// Se crea la variable
					Variable v = new Variable (nombre,tipo,valorCadena);
	
					// Se inserta la variable en la tabla de símbolos
					symbolsTable.insertarSimbolo(v);
					insertado = 1;
				} else {
					insertado = -1;	
				}
			}
			
			return insertado;
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

protected Meccasint(TokenBuffer tokenBuf, int k) {
  super(tokenBuf,k);
  tokenNames = _tokenNames;
}

public Meccasint(TokenBuffer tokenBuf) {
  this(tokenBuf,2);
}

protected Meccasint(TokenStream lexer, int k) {
  super(lexer,k);
  tokenNames = _tokenNames;
}

public Meccasint(TokenStream lexer) {
  this(lexer,2);
}

public Meccasint(ParserSharedInputState state) {
  super(state,2);
  tokenNames = _tokenNames;
}

	public final void mecca() throws RecognitionException, TokenStreamException {
		
		
		try {      // for error handling
			{
			_loop155:
			do {
				if ((_tokenSet_0.member(LA(1)))) {
					instruction();
				}
				else {
					break _loop155;
				}
				
			} while (true);
			}
			configuration();
			{
			_loop157:
			do {
				if ((_tokenSet_0.member(LA(1)))) {
					instruction();
				}
				else {
					break _loop157;
				}
				
			} while (true);
			}
			adventure();
			{
			_loop159:
			do {
				if ((_tokenSet_0.member(LA(1)))) {
					instruction();
				}
				else {
					break _loop159;
				}
				
			} while (true);
			}
		}
		catch (RecognitionException ex) {
			reportError(ex);
			recover(ex,_tokenSet_1);
		}
	}
	
	public final void instruction() throws RecognitionException, TokenStreamException {
		
		Token  i2 = null;
		Variable param1, param2;
		String info;
		
		try {      // for error handling
			switch ( LA(1)) {
			case FUNC_LEER:
			{
				match(FUNC_LEER);
				match(PARENT_IZ);
				i2 = LT(1);
				match(IDENT);
				match(PARENT_DE);
				match(PUNTO_COMA);
					
								//It searches the identifier in the symbols table
								int index = symbolsTable.existeSimbolo(i2.getText());
									
								if ( index >= 0 ) {
									String stringValue = symbolsTable.getSimbolo(index).getValor();
									System.out.println(stringValue);
									}					
								
				break;
			}
			case FUNC_SHOWBOARD:
			{
				match(FUNC_SHOWBOARD);
				match(PARENT_IZ);
				match(PARENT_DE);
				match(PUNTO_COMA);
				
											if(mode == CONFIGURATION_MODE) {
												System.out.println(board.toString());
											} else {
												System.out.println("This instruction has to be called in Configuration Mode");
											}
							
				break;
			}
			case FUNC_SHOWADVENTURESTATE:
			{
				match(FUNC_SHOWADVENTURESTATE);
				match(PARENT_IZ);
				match(PARENT_DE);
				match(PUNTO_COMA);
				
											if(mode == ADVENTURE_MODE) {
												board.showAdventureState();
											} else {
												System.out.println("This instruction has to be called in Configuration Mode");
											}
							
				break;
			}
			case FUNC_SETBOARDSIZE:
			{
				match(FUNC_SETBOARDSIZE);
				match(PARENT_IZ);
				param1=expression();
				match(COMA);
				param2=expression();
				match(PARENT_DE);
				match(PUNTO_COMA);
				
								if(mode == CONFIGURATION_MODE) {
									if(param1.isNumber()) {
										Size newSize = new Size(Integer.parseInt(param1.getValor()),Integer.parseInt(param2.getValor()));
										board.setSize(newSize);
										System.out.println("Board has now "+board.getSize().getWidth()+" columns and "+board.getSize().getHeight()+" rows");
									} else {
										System.out.println("Parameters must be numbers");
									}
								} else {
									System.out.println("This instruction has to be called in Configuration Mode");
								}
							
				break;
			}
			case FUNC_GETBOARDROWS:
			{
				match(FUNC_GETBOARDROWS);
				match(PARENT_IZ);
				match(PARENT_DE);
				match(PUNTO_COMA);
				
								if(mode == CONFIGURATION_MODE || mode == ADVENTURE_MODE) {					
									System.out.println("Board has "+board.getSize().getHeight()+" rows");
								} else {
									System.out.println("This instruction has to be called in Configuration Mode or Adventure Mode");							
								}
							
				break;
			}
			case FUNC_GETBOARDCOLUMNS:
			{
				match(FUNC_GETBOARDCOLUMNS);
				match(PARENT_IZ);
				match(PARENT_DE);
				match(PUNTO_COMA);
				
								if(mode == CONFIGURATION_MODE || mode == ADVENTURE_MODE) {	
									System.out.println("Board has "+board.getSize().getWidth()+" columns");
								} else {
									System.out.println("This instruction has to be called in Configuration Mode or Adventure Mode");							
								}
							
				break;
			}
			case FUNC_GETBOARDSIZE:
			{
				match(FUNC_GETBOARDSIZE);
				match(PARENT_IZ);
				match(PARENT_DE);
				match(PUNTO_COMA);
				
								if(mode == CONFIGURATION_MODE || mode == ADVENTURE_MODE) {	
									System.out.println("Board size is "+board.getSize().getHeight()+" rows and "+board.getSize().getWidth()+" columns");
								} else {
									System.out.println("This instruction has to be called in Configuration Mode or Adventure Mode");													
								}
							
				break;
			}
			case FUNC_SETTREASURE:
			{
				match(FUNC_SETTREASURE);
				match(PARENT_IZ);
				param1=expression();
				match(COMA);
				param2=expression();
				match(PARENT_DE);
				match(PUNTO_COMA);
				
								if(mode == CONFIGURATION_MODE) {
									if(param1.isNumber() && param2.isNumber()) {
										Position newTreasure = new Position(Integer.parseInt(param1.getValor()), Integer.parseInt(param2.getValor()));
										int position = board.setTreasurePos(newTreasure);
										
										if(position != -1) {
											System.out.println("Treasure "+(position+1)+" set on column "+board.getTreasurePos(position).getX()+" row "+board.getTreasurePos(position).getY());
										} else {
											System.out.println("The given board position is not empty or not exists");
										}
									} else {
										System.out.println("Parameters must be numbers");	
									}
								} else {
									System.out.println("This instruction has to be called in Configuration Mode");
								}
							
				break;
			}
			case FUNC_REMOVETREASURE:
			{
				match(FUNC_REMOVETREASURE);
				match(PARENT_IZ);
				param1=expression();
				match(PARENT_DE);
				match(PUNTO_COMA);
										
								if(mode == CONFIGURATION_MODE) {
									if(param1.isNumber()) {
										if(board.removeTreasure(Integer.parseInt(param1.getValor()))) {
											System.out.println("Treasure " + param1 + " has been removed");
										} else {
											System.out.println("Treasure " + param1 + " does not exist");
										}
									} else {
										System.out.println("Parameter must be number");	
									}
								} else {
									System.out.println("This instruction has to be called in Configuration Mode");
								}
							
				break;
			}
			case FUNC_GETTOTALTREASURES:
			{
				match(FUNC_GETTOTALTREASURES);
				match(PARENT_IZ);
				match(PARENT_DE);
				match(PUNTO_COMA);
				
								System.out.println("The number of total treasures is: "+board.getTotalTreasures());
							
				break;
			}
			case FUNC_SHOWTREASURES:
			{
				match(FUNC_SHOWTREASURES);
				match(PARENT_IZ);
				match(PARENT_DE);
				match(PUNTO_COMA);
				
								if(mode == CONFIGURATION_MODE) {
									board.showTreasures();
								} else {
									System.out.println("This instruction has to be called in Configuration Mode");							
								}
							
				break;
			}
			case FUNC_GETTREASURE:
			{
				match(FUNC_GETTREASURE);
				match(PARENT_IZ);
				param1=expression();
				match(PARENT_DE);
				match(PUNTO_COMA);
				
								if(mode == CONFIGURATION_MODE) {
									if(param1.isNumber()) {					
										//Check if that treasure exists
										if(board.getTotalTreasures() >= Integer.parseInt(param1.getValor()) && Integer.parseInt(param1.getValor()) > 0) {
											System.out.println("Treasure "+ Integer.parseInt(param1.getValor()) +" set on column "+board.getTreasurePos(Integer.parseInt(param1.getValor())-1).getX()+" row "+board.getTreasurePos(Integer.parseInt(param1.getValor())-1).getY());
										} else {
											System.out.println("There is no treasure with that number");
										}
									} else {
										System.out.println("Parameter must be number");	
									}
								} else {
									System.out.println("This instruction has to be called in Configuration Mode");
								}
							
				break;
			}
			case FUNC_SETHOLE:
			{
				match(FUNC_SETHOLE);
				match(PARENT_IZ);
				param1=expression();
				match(COMA);
				param2=expression();
				match(PARENT_DE);
				match(PUNTO_COMA);
				
								if(mode == CONFIGURATION_MODE) {
									if(param1.isNumber() && param2.isNumber()) {
										Position newHole = new Position(Integer.parseInt(param1.getValor()),Integer.parseInt(param2.getValor()));
										int position = board.setHolePos(newHole);
										
										if(position != -1) {
											System.out.println("Hole "+(position+1)+" set on column "+board.getHolePos(position).getX()+" row "+board.getHolePos(position).getY());
										} else {
											System.out.println("The given board position is not empty or not exists");
										}
									} else {
										System.out.println("Parameter must be number");	
									}
								} else {
									System.out.println("This instruction has to be called in Configuration Mode");
								}
							
				break;
			}
			case FUNC_REMOVEHOLE:
			{
				match(FUNC_REMOVEHOLE);
				match(PARENT_IZ);
				param1=expression();
				match(PARENT_DE);
				match(PUNTO_COMA);
				
								if(mode == CONFIGURATION_MODE) {
									if(param1.isNumber()) {
										if(board.removeHole(Integer.parseInt(param1.getValor()))) {
											System.out.println("Hole " + param1 + " has been removed");
										} else {
											System.out.println("Hole " + param1 + " does not exist");
										}
									} else {
										System.out.println("Parameter must be number");	
									}
								} else {
									System.out.println("This instruction has to be called in Configuration Mode");							
								}
							
				break;
			}
			case FUNC_GETNUMBEROFHOLES:
			{
				match(FUNC_GETNUMBEROFHOLES);
				match(PARENT_IZ);
				match(PARENT_DE);
				match(PUNTO_COMA);
				
								if(mode == CONFIGURATION_MODE) {
									System.out.println("The number of holes is: "+board.getNumberOfHoles());
								} else {
									System.out.println("This instruction has to be called in Configuration Mode");							
								}
							
				break;
			}
			case FUNC_GETHOLE:
			{
				match(FUNC_GETHOLE);
				match(PARENT_IZ);
				param1=expression();
				match(PARENT_DE);
				match(PUNTO_COMA);
				
								if(mode == CONFIGURATION_MODE) {
									if(param1.isNumber()) {
										//Check if that hole exists
										if(board.getNumberOfHoles() >= Integer.parseInt(param1.getValor()) && Integer.parseInt(param1.getValor()) > 0) {
											System.out.println("Hole "+(param1)+" set on column "+board.getHolePos(Integer.parseInt(param1.getValor())-1).getX()+" row "+board.getHolePos(Integer.parseInt(param1.getValor())-1).getY());
										} else {
											System.out.println("There is no hole with that number");
										}
									} else {
										System.out.println("Parameter must be number");	
									}
								} else {
									System.out.println("This instruction has to be called in Configuration Mode");							
								}
							
				break;
			}
			case FUNC_SHOWHOLES:
			{
				match(FUNC_SHOWHOLES);
				match(PARENT_IZ);
				match(PARENT_DE);
				match(PUNTO_COMA);
				
								if(mode == CONFIGURATION_MODE) {
									board.showHoles();
								} else {
									System.out.println("This instruction has to be called in Configuration Mode");							
								}
							
				break;
			}
			case FUNC_SETWUMPUS:
			{
				match(FUNC_SETWUMPUS);
				match(PARENT_IZ);
				param1=expression();
				match(COMA);
				param2=expression();
				match(PARENT_DE);
				match(PUNTO_COMA);
				
								if(mode == CONFIGURATION_MODE) {
									if(param1.isNumber() && param2.isNumber()) {
										Position newWumpus = new Position(Integer.parseInt(param1.getValor()),Integer.parseInt(param2.getValor()));
										
										if(board.setWumpusPos(newWumpus)) {
											System.out.println("Wumpus set on column "+board.getWumpusPos().getX()+" row "+board.getWumpusPos().getY());
										}
									} else {
										System.out.println("Parameters must be numbers");	
									}
								} else {
									System.out.println("This instruction has to be called in Configuration Mode");
								}
							
				break;
			}
			case FUNC_GETWUMPUS:
			{
				match(FUNC_GETWUMPUS);
				match(PARENT_IZ);
				match(PARENT_DE);
				match(PUNTO_COMA);
				
								if(mode == CONFIGURATION_MODE) {		
									System.out.println("The Wumpus is on column "+ board.getWumpusPos().getX()+" row " + board.getWumpusPos().getY());
								} else {
									System.out.println("This instruction has to be called in Configuration Mode");
								}
							
				break;
			}
			case FUNC_SETSTART:
			{
				match(FUNC_SETSTART);
				match(PARENT_IZ);
				param1=expression();
				match(COMA);
				param2=expression();
				match(PARENT_DE);
				match(PUNTO_COMA);
				
								if(mode == CONFIGURATION_MODE) {
									if(param1.isNumber() && param2.isNumber()) {
										Position newStart = new Position(Integer.parseInt(param1.getValor()),Integer.parseInt(param2.getValor()));
										
										if(board.setStartPos(newStart)) {
											System.out.println("Start set on column "+board.getStartPos().getX()+" row "+board.getStartPos().getY());
										} else {
											System.out.println("The given board position is not empty or not exists");
										}
									} else {
										System.out.println("Parameters must be numbers");	
									}
								} else {
									System.out.println("This instruction has to be called in Configuration Mode");
								}
							
				break;
			}
			case FUNC_GETSTART:
			{
				match(FUNC_GETSTART);
				match(PARENT_IZ);
				match(PARENT_DE);
				match(PUNTO_COMA);
				
								if(mode == CONFIGURATION_MODE) {
									System.out.println("Start is on column "+board.getStartPos().getX()+" row "+board.getStartPos().getY());
								} else {
									System.out.println("This instruction has to be called in Configuration Mode");
								}	
							
				break;
			}
			case FUNC_SETEXIT:
			{
				match(FUNC_SETEXIT);
				match(PARENT_IZ);
				param1=expression();
				match(COMA);
				param2=expression();
				match(PARENT_DE);
				match(PUNTO_COMA);
				
								if(mode == CONFIGURATION_MODE) {
									if(param1.isNumber() && param2.isNumber()) {
										Position newExit = new Position(Integer.parseInt(param1.getValor()),Integer.parseInt(param2.getValor()));
										
										if(board.setExitPos(newExit)) {
											System.out.println("Exit set on column "+board.getExitPos().getX()+" row "+board.getExitPos().getY());
										} else {
											System.out.println("The given board position is not empty or not exists");
										}
									} else {
										System.out.println("Parameters must be numbers");	
									}
								} else {
									System.out.println("This instruction has to be called in Configuration Mode");
								}
							
				break;
			}
			case FUNC_GETEXIT:
			{
				match(FUNC_GETEXIT);
				match(PARENT_IZ);
				match(PARENT_DE);
				match(PUNTO_COMA);
				
								if(mode == CONFIGURATION_MODE) {
									System.out.println("Exit is on column "+board.getExitPos().getX()+" row "+board.getExitPos().getY());
								} else {
									System.out.println("This instruction has to be called in Configuration Mode");
								}
							
				break;
			}
			case FUNC_GETMECCA:
			{
				match(FUNC_GETMECCA);
				match(PARENT_IZ);
				match(PARENT_DE);
				match(PUNTO_COMA);
				
								if(mode == CONFIGURATION_MODE || mode == ADVENTURE_MODE) {
									System.out.println("Mecca is on row "+ board.getMeccaPos().getY()+" column " + board.getMeccaPos().getX());
								} else {
									System.out.println("This instruction has to be called in Configuration Mode or Adventure Mode");
								}
							
				break;
			}
			case FUNC_SETARROWS:
			{
				match(FUNC_SETARROWS);
				match(PARENT_IZ);
				param1=expression();
				match(PARENT_DE);
				match(PUNTO_COMA);
				
								if(mode == CONFIGURATION_MODE) {
									if(param1.isNumber()) {
										board.setMeccaNArrows(Integer.parseInt(param1.getValor()));
										System.out.println("Mecca has now "+board.getMeccaNArrows()+" arrows");
									} else {
										System.out.println("Parameter must be number");	
									}
								} else {
									System.out.println("This instruction has to be called in Configuration Mode");
								} 				
							
				break;
			}
			case FUNC_GETARROWS:
			{
				match(FUNC_GETARROWS);
				match(PARENT_IZ);
				match(PARENT_DE);
				match(PUNTO_COMA);
				
								if(mode == CONFIGURATION_MODE || mode == ADVENTURE_MODE) {
									System.out.println("Mecca has "+board.getMeccaNArrows()+" arrows");
								} else {
									System.out.println("This instruction has to be called in Configuration Mode or Adventure Mode");
								}						
							
				break;
			}
			case FUNC_INCARROWS:
			{
				match(FUNC_INCARROWS);
				match(PARENT_IZ);
				param1=expression();
				match(PARENT_DE);
				match(PUNTO_COMA);
				
								if(mode == CONFIGURATION_MODE) {
									if(param1.isNumber()) {
										if(Integer.parseInt(param1.getValor()) >= 0) {
											board.incMeccaNArrows(Integer.parseInt(param1.getValor()));
											System.out.println("Arrows incremented in "+param1+", Mecca has now "+board.getMeccaNArrows()+" arrows");	
										} else {
											System.out.println("You have to enter an integer bigger than 0");
										}
									} else {
										System.out.println("Parameter must be number");	
									}
								} else {
									System.out.println("This instruction has to be called in Configuration Mode");
								}	
							
				break;
			}
			case FUNC_DECARROWS:
			{
				match(FUNC_DECARROWS);
				match(PARENT_IZ);
				param1=expression();
				match(PARENT_DE);
				match(PUNTO_COMA);
				
								if(mode == CONFIGURATION_MODE) {
									if(param1.isNumber()) {						
										if(Integer.parseInt(param1.getValor()) >= 0) {
											board.decMeccaNArrows(Integer.parseInt(param1.getValor()));
											System.out.println("Arrows decremented in "+param1+", Mecca has now "+board.getMeccaNArrows()+" arrows");	
										} else {
											System.out.println("The number of arrows has to be positive");
										}
									} else {
										System.out.println("Parameter must be number");	
									}
								} else {
									System.out.println("This instruction has to be called in Configuration Mode");
								}	
							
				break;
			}
			case FUNC_GETREMAININGTREASURES:
			{
				match(FUNC_GETREMAININGTREASURES);
				match(PARENT_IZ);
				match(PARENT_DE);
				match(PUNTO_COMA);
				
								System.out.println("X Treasures remaining");
							
				break;
			}
			case FUNC_SHOOTLEFT:
			{
				match(FUNC_SHOOTLEFT);
				match(PARENT_IZ);
				match(PARENT_DE);
				match(PUNTO_COMA);
				
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
							
				break;
			}
			case FUNC_SHOOTRIGHT:
			{
				match(FUNC_SHOOTRIGHT);
				match(PARENT_IZ);
				match(PARENT_DE);
				match(PUNTO_COMA);
				
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
							
				break;
			}
			case FUNC_SHOOTUP:
			{
				match(FUNC_SHOOTUP);
				match(PARENT_IZ);
				match(PARENT_DE);
				match(PUNTO_COMA);
				
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
							
				break;
			}
			case FUNC_SHOOTDOWN:
			{
				match(FUNC_SHOOTDOWN);
				match(PARENT_IZ);
				match(PARENT_DE);
				match(PUNTO_COMA);
				
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
							
				break;
			}
			case FUNC_GOLEFT:
			{
				match(FUNC_GOLEFT);
				match(PARENT_IZ);
				match(PARENT_DE);
				match(PUNTO_COMA);
				
								if(mode == ADVENTURE_MODE) {
									if(!board.isGameFinished()) {
										board.meccaGoLeft();
									} else {
										System.out.println("The game has finished!");	
									}
								} else {
									System.out.println("This instruction has to be called in Adventure Mode");
								}
							
				break;
			}
			case FUNC_GORIGHT:
			{
				match(FUNC_GORIGHT);
				match(PARENT_IZ);
				match(PARENT_DE);
				match(PUNTO_COMA);
				
								if(mode == ADVENTURE_MODE) {
									if(!board.isGameFinished()) {
										board.meccaGoRight();
									} else {
										System.out.println("The game has finished!");	
									}
								} else {
									System.out.println("This instruction has to be called in Adventure Mode");
								}
							
				break;
			}
			case FUNC_GOUP:
			{
				match(FUNC_GOUP);
				match(PARENT_IZ);
				match(PARENT_DE);
				match(PUNTO_COMA);
				
								if(mode == ADVENTURE_MODE) {
									if(!board.isGameFinished()) {
										board.meccaGoUp();
									} else {
										System.out.println("The game has finished!");	
									}
								} else {
									System.out.println("This instruction has to be called in Adventure Mode");
								}
							
				break;
			}
			case FUNC_GODOWN:
			{
				match(FUNC_GODOWN);
				match(PARENT_IZ);
				match(PARENT_DE);
				match(PUNTO_COMA);
				
								if(mode == ADVENTURE_MODE) {
									if(!board.isGameFinished()) {
										board.meccaGoDown();
									} else {
										System.out.println("The game has finished!");	
									}
								} else {
									System.out.println("This instruction has to be called in Adventure Mode");
								}
							
				break;
			}
			case IDENT:
			case TIPO_NUMERO:
			case TIPO_CADENA:
			{
				asignation();
				break;
			}
			case RES_SI:
			{
				conditional_sentence();
				break;
			}
			case RES_MIENTRAS:
			{
				while_loop();
				break;
			}
			case RES_REPETIR:
			{
				do_until_loop();
				break;
			}
			case RES_PARA:
			{
				for_loop();
				firstTime=true;
				break;
			}
			default:
			{
				throw new NoViableAltException(LT(1), getFilename());
			}
			}
		}
		catch (RecognitionException ex) {
			reportError(ex);
			recover(ex,_tokenSet_2);
		}
	}
	
	public final void configuration() throws RecognitionException, TokenStreamException {
		
		
		try {      // for error handling
			match(BEGIN_CONF);
			mode = CONFIGURATION_MODE;
			{
			int _cnt162=0;
			_loop162:
			do {
				if ((_tokenSet_0.member(LA(1)))) {
					instruction();
				}
				else {
					if ( _cnt162>=1 ) { break _loop162; } else {throw new NoViableAltException(LT(1), getFilename());}
				}
				
				_cnt162++;
			} while (true);
			}
			match(END_CONF);
		}
		catch (RecognitionException ex) {
			reportError(ex);
			recover(ex,_tokenSet_3);
		}
	}
	
	public final void adventure() throws RecognitionException, TokenStreamException {
		
		
		try {      // for error handling
			match(BEGIN_ADV);
			if(board.initGame()) mode = ADVENTURE_MODE;
			{
			int _cnt165=0;
			_loop165:
			do {
				if ((_tokenSet_0.member(LA(1)))) {
					instruction();
				}
				else {
					if ( _cnt165>=1 ) { break _loop165; } else {throw new NoViableAltException(LT(1), getFilename());}
				}
				
				_cnt165++;
			} while (true);
			}
			match(END_ADV);
		}
		catch (RecognitionException ex) {
			reportError(ex);
			recover(ex,_tokenSet_4);
		}
	}
	
	public final Variable  expression() throws RecognitionException, TokenStreamException {
		Variable result = new Variable("","","");;
		
		Variable e1,e2;
		
		try {      // for error handling
			switch ( LA(1)) {
			case IDENT:
			case LIT_NUMERO:
			case LIT_CADENA:
			case PARENTESIS_IZ:
			{
				e1=addend();
				result = e1;
				{
				_loop174:
				do {
					switch ( LA(1)) {
					case OP_MAS:
					{
						{
						match(OP_MAS);
						e2=addend();
						
											if(e2.getTipo().equals("number") && result.getTipo().equals("number")) {
										 		result = new Variable("", "number", String.valueOf(Float.parseFloat(result.getValor()) + Float.parseFloat(e2.getValor())));
											} else if(e2.getTipo().equals("string") && result.getTipo().equals("string") ||
												e2.getTipo().equals("number") && result.getTipo().equals("string") ||
												e2.getTipo().equals("string") && result.getTipo().equals("number")) {
												result = new Variable("", "string", result.getValor() + e2.getValor());
											}
										
						}
						break;
					}
					case OP_MENOS:
					{
						{
						match(OP_MENOS);
						e2=addend();
						
											if(e2.getTipo().equals("number") && result.getTipo().equals("number")) {
										 		result = new Variable("", "number", String.valueOf(Float.parseFloat(result.getValor()) - Float.parseFloat(e2.getValor())));
											}
										
						}
						break;
					}
					default:
					{
						break _loop174;
					}
					}
				} while (true);
				}
				break;
			}
			case OP_MENOS:
			{
				e1=negative();
				result = e1;
				{
				_loop178:
				do {
					switch ( LA(1)) {
					case OP_MAS:
					{
						{
						match(OP_MAS);
						e2=addend();
						
										 	if(e2.getTipo().equals("number") && result.getTipo().equals("number")) {
										 		result = new Variable("", "number", String.valueOf(Float.parseFloat(result.getValor()) + Float.parseFloat(e2.getValor())));
											}
										
						}
						break;
					}
					case OP_MENOS:
					{
						{
						match(OP_MENOS);
						e2=addend();
						
										 	if(e2.getTipo().equals("number") && result.getTipo().equals("number")) {
										 		result = new Variable("", "number", String.valueOf(Float.parseFloat(result.getValor()) - Float.parseFloat(e2.getValor())));
											}
										
						}
						break;
					}
					default:
					{
						break _loop178;
					}
					}
				} while (true);
				}
				break;
			}
			default:
			{
				throw new NoViableAltException(LT(1), getFilename());
			}
			}
		}
		catch (RecognitionException re) {
			
						System.out.println("Traza: expression");
						mostrarExcepcion(re);
					
		}
		return result;
	}
	
	public final void asignation() throws RecognitionException, TokenStreamException {
		
		Token  i = null;
		Token  i2 = null;
		Token  i3 = null;
		Variable e = null;
		
		try {      // for error handling
			switch ( LA(1)) {
			case TIPO_NUMERO:
			{
				match(TIPO_NUMERO);
				{
				if ((LA(1)==IDENT) && (LA(2)==OP_ASIG)) {
					i = LT(1);
					match(IDENT);
					match(OP_ASIG);
					e=expression();
				}
				else if ((LA(1)==IDENT) && (LA(2)==PUNTO_COMA||LA(2)==COMA)) {
					parametros_number();
				}
				else {
					throw new NoViableAltException(LT(1), getFilename());
				}
				
				}
				match(PUNTO_COMA);
				
							if(i != null) {
								// Se toma el nombre del identificador
								String nombre = i.getText();
					
								// El número se convierte en cadena
								String valorCadena = e.getValor();
					
								// Se inserta en la tabla de Símbolos
								insertarIdentificador(nombre,"number",valorCadena);
							}
						
								// Se muestra por pantalla: depuración
								// System.out.println(" Asignación => " + nombre + " := " + e);
						
				break;
			}
			case TIPO_CADENA:
			{
				match(TIPO_CADENA);
				{
				if ((LA(1)==IDENT) && (LA(2)==OP_ASIG)) {
					i2 = LT(1);
					match(IDENT);
					match(OP_ASIG);
					e=expression();
				}
				else if ((LA(1)==IDENT) && (LA(2)==PUNTO_COMA||LA(2)==COMA)) {
					parametros_string();
				}
				else {
					throw new NoViableAltException(LT(1), getFilename());
				}
				
				}
				match(PUNTO_COMA);
				
							if(i2 != null && e != null) {
								// Se toma el nombre del identificador
								String nombre = i2.getText();
								
								String valorCadena = e.getValor();
								
								// Se inserta en la tabla de Símbolos
								insertarIdentificador(nombre,"string",valorCadena);		
							}
							// Se muestra por pantalla: depuración
							// System.out.println(" Asignación => " + nombre + " := " + e);
						
				break;
			}
			case IDENT:
			{
				i3 = LT(1);
				match(IDENT);
				match(OP_ASIG);
				e=expression();
				match(PUNTO_COMA);
				
							// Se toma el nombre del identificador
							String nombre = i3.getText();
							
							int indice = symbolsTable.existeSimbolo(nombre);
							
							if(indice >= 0) {
								Variable simbolo = symbolsTable.getSimbolo(indice);
								
								if(simbolo.getTipo().equals("number")) {
									try {
										Float value = Float.parseFloat(e.getValor());
										simbolo.setValor(String.valueOf(value));
										
									} catch(NumberFormatException err) {
										System.err.println("Error: la variable \"" + nombre + "\" es de tipo \"number\"");
									}
								} else {
									simbolo.setValor(e.getValor());
								}
							} else {
								System.err.println("Error: la variable \"" + nombre + "\" no ha sido declarada");
							}		
						
				break;
			}
			default:
			{
				throw new NoViableAltException(LT(1), getFilename());
			}
			}
		}
		catch (RecognitionException re) {
			
						mostrarExcepcion(re);
					
		}
	}
	
	public final void conditional_sentence() throws RecognitionException, TokenStreamException {
		
		boolean valor;
		
		try {      // for error handling
			match(RES_SI);
			valor=condition();
			match(RES_ENTONCES);
			{
			if (((_tokenSet_0.member(LA(1))) && ((LA(2) >= BEGIN_CONF && LA(2) <= RES_FIN_PARA)))&&(valor==true)) {
				{
				int _cnt189=0;
				_loop189:
				do {
					if ((_tokenSet_0.member(LA(1)))) {
						instruction();
					}
					else {
						if ( _cnt189>=1 ) { break _loop189; } else {throw new NoViableAltException(LT(1), getFilename());}
					}
					
					_cnt189++;
				} while (true);
				}
				{
				switch ( LA(1)) {
				case RES_SI_NO:
				{
					match(RES_SI_NO);
					{
					int _cnt192=0;
					_loop192:
					do {
						// nongreedy exit test
						if ( _cnt192>=1 && (LA(1)==RES_FIN_SI) && (LA(2)==PUNTO_COMA)) break _loop192;
						if (((LA(1) >= BEGIN_CONF && LA(1) <= RES_FIN_PARA)) && ((LA(2) >= BEGIN_CONF && LA(2) <= RES_FIN_PARA))) {
							matchNot(EOF);
						}
						else {
							if ( _cnt192>=1 ) { break _loop192; } else {throw new NoViableAltException(LT(1), getFilename());}
						}
						
						_cnt192++;
					} while (true);
					}
					break;
				}
				case RES_FIN_SI:
				{
					break;
				}
				default:
				{
					throw new NoViableAltException(LT(1), getFilename());
				}
				}
				}
				match(RES_FIN_SI);
				match(PUNTO_COMA);
			}
			else if ((((LA(1) >= BEGIN_CONF && LA(1) <= RES_FIN_PARA)) && ((LA(2) >= BEGIN_CONF && LA(2) <= RES_FIN_PARA)))&&(valor==false)) {
				{
				int _cnt194=0;
				_loop194:
				do {
					// nongreedy exit test
					if ( _cnt194>=1 && (LA(1)==RES_SI_NO||LA(1)==RES_FIN_SI) && (_tokenSet_5.member(LA(2)))) break _loop194;
					if (((LA(1) >= BEGIN_CONF && LA(1) <= RES_FIN_PARA)) && ((LA(2) >= BEGIN_CONF && LA(2) <= RES_FIN_PARA))) {
						matchNot(EOF);
					}
					else {
						if ( _cnt194>=1 ) { break _loop194; } else {throw new NoViableAltException(LT(1), getFilename());}
					}
					
					_cnt194++;
				} while (true);
				}
				{
				switch ( LA(1)) {
				case RES_SI_NO:
				{
					match(RES_SI_NO);
					{
					int _cnt197=0;
					_loop197:
					do {
						if ((_tokenSet_0.member(LA(1)))) {
							instruction();
						}
						else {
							if ( _cnt197>=1 ) { break _loop197; } else {throw new NoViableAltException(LT(1), getFilename());}
						}
						
						_cnt197++;
					} while (true);
					}
					break;
				}
				case RES_FIN_SI:
				{
					break;
				}
				default:
				{
					throw new NoViableAltException(LT(1), getFilename());
				}
				}
				}
				match(RES_FIN_SI);
				match(PUNTO_COMA);
			}
			else {
				throw new NoViableAltException(LT(1), getFilename());
			}
			
			}
		}
		catch (RecognitionException re) {
			
						mostrarExcepcion(re);
					
		}
	}
	
	public final void while_loop() throws RecognitionException, TokenStreamException {
		
		boolean valor; int marca=-1;
		
		try {      // for error handling
			marca = mark();
			match(RES_MIENTRAS);
			valor=condition();
			match(RES_HACER);
			{
			if ((((LA(1) >= BEGIN_CONF && LA(1) <= RES_FIN_PARA)) && ((LA(2) >= BEGIN_CONF && LA(2) <= RES_FIN_PARA)))&&(valor == false)) {
				{
				_loop203:
				do {
					// nongreedy exit test
					if ((LA(1)==RES_FIN_MIENTRAS) && (LA(2)==PUNTO_COMA)) break _loop203;
					if (((LA(1) >= BEGIN_CONF && LA(1) <= RES_FIN_PARA)) && ((LA(2) >= BEGIN_CONF && LA(2) <= RES_FIN_PARA))) {
						matchNot(EOF);
					}
					else {
						break _loop203;
					}
					
				} while (true);
				}
				match(RES_FIN_MIENTRAS);
				match(PUNTO_COMA);
			}
			else if (((_tokenSet_0.member(LA(1))) && ((LA(2) >= BEGIN_CONF && LA(2) <= RES_FIN_PARA)))&&(valor == true)) {
				{
				int _cnt205=0;
				_loop205:
				do {
					if ((_tokenSet_0.member(LA(1)))) {
						instruction();
					}
					else {
						if ( _cnt205>=1 ) { break _loop205; } else {throw new NoViableAltException(LT(1), getFilename());}
					}
					
					_cnt205++;
				} while (true);
				}
				match(RES_FIN_MIENTRAS);
				match(PUNTO_COMA);
				
								rewind(marca); 
								this.while_loop();
								
			}
			else {
				throw new NoViableAltException(LT(1), getFilename());
			}
			
			}
		}
		catch (RecognitionException ex) {
			reportError(ex);
			recover(ex,_tokenSet_2);
		}
	}
	
	public final void do_until_loop() throws RecognitionException, TokenStreamException {
		
		boolean valor=false; int marca=-1;
		
		try {      // for error handling
			marca = mark();
			match(RES_REPETIR);
			{
			if ((((LA(1) >= BEGIN_CONF && LA(1) <= RES_FIN_PARA)) && ((LA(2) >= BEGIN_CONF && LA(2) <= RES_FIN_PARA)))&&(valor == true)) {
				{
				_loop209:
				do {
					// nongreedy exit test
					if ((LA(1)==RES_HASTA) && (_tokenSet_6.member(LA(2)))) break _loop209;
					if (((LA(1) >= BEGIN_CONF && LA(1) <= RES_FIN_PARA)) && ((LA(2) >= BEGIN_CONF && LA(2) <= RES_FIN_PARA))) {
						matchNot(EOF);
					}
					else {
						break _loop209;
					}
					
				} while (true);
				}
				match(RES_HASTA);
				valor=condition();
				match(PUNTO_COMA);
			}
			else if (((_tokenSet_0.member(LA(1))) && ((LA(2) >= BEGIN_CONF && LA(2) <= RES_FIN_PARA)))&&(valor == false)) {
				{
				int _cnt211=0;
				_loop211:
				do {
					if ((_tokenSet_0.member(LA(1)))) {
						instruction();
					}
					else {
						if ( _cnt211>=1 ) { break _loop211; } else {throw new NoViableAltException(LT(1), getFilename());}
					}
					
					_cnt211++;
				} while (true);
				}
				match(RES_HASTA);
				valor=condition();
				match(PUNTO_COMA);
				
									if(valor==false){
										rewind(marca); 
										this.do_until_loop();
									}
								
			}
			else {
				throw new NoViableAltException(LT(1), getFilename());
			}
			
			}
		}
		catch (RecognitionException ex) {
			reportError(ex);
			recover(ex,_tokenSet_2);
		}
	}
	
	public final void for_loop() throws RecognitionException, TokenStreamException {
		
		Token  i = null;
		int marca=-1;Variable initValue=new Variable("","number","-1"), endValue=new Variable("","number","-1"), inc=new Variable("","number","-1"); int index=-1; String id; boolean firstTimeTest=true;
		
		try {      // for error handling
			marca = mark();
			match(RES_PARA);
			i = LT(1);
			match(IDENT);
			match(RES_DESDE);
			initValue=expression();
			match(RES_HASTA);
			endValue=expression();
			match(RES_PASO);
			inc=expression();
			match(RES_HACER);
					 	
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
					 	
					
			{
			if ((((LA(1) >= BEGIN_CONF && LA(1) <= RES_FIN_PARA)) && ((LA(2) >= BEGIN_CONF && LA(2) <= RES_FIN_PARA)))&&(Float.parseFloat(symbolsTable.getSimbolo(index).getValor()) >= Float.parseFloat(endValue.getValor()))) {
				{
				_loop215:
				do {
					// nongreedy exit test
					if ((LA(1)==RES_FIN_PARA) && (LA(2)==PUNTO_COMA)) break _loop215;
					if (((LA(1) >= BEGIN_CONF && LA(1) <= RES_FIN_PARA)) && ((LA(2) >= BEGIN_CONF && LA(2) <= RES_FIN_PARA))) {
						matchNot(EOF);
					}
					else {
						break _loop215;
					}
					
				} while (true);
				}
				match(RES_FIN_PARA);
				match(PUNTO_COMA);
			}
			else if (((_tokenSet_0.member(LA(1))) && ((LA(2) >= BEGIN_CONF && LA(2) <= RES_FIN_PARA)))&&(Float.parseFloat(symbolsTable.getSimbolo(index).getValor()) < Float.parseFloat(endValue.getValor()))) {
				{
				int _cnt217=0;
				_loop217:
				do {
					if ((_tokenSet_0.member(LA(1)))) {
						instruction();
					}
					else {
						if ( _cnt217>=1 ) { break _loop217; } else {throw new NoViableAltException(LT(1), getFilename());}
					}
					
					_cnt217++;
				} while (true);
				}
				symbolsTable.getSimbolo(index).setValor(String.valueOf(Float.parseFloat(symbolsTable.getSimbolo(index).getValor())+Float.parseFloat(inc.getValor())));
				match(RES_FIN_PARA);
				match(PUNTO_COMA);
				
								rewind(marca); 
								this.for_loop();
								
			}
			else {
				throw new NoViableAltException(LT(1), getFilename());
			}
			
			}
		}
		catch (RecognitionException ex) {
			reportError(ex);
			recover(ex,_tokenSet_2);
		}
	}
	
	public final void parametros_number() throws RecognitionException, TokenStreamException {
		
		String param1 = null;
		
		try {      // for error handling
			param1=valorparametro();
			{
			_loop223:
			do {
				if ((LA(1)==COMA)) {
					parametros_prima_string();
				}
				else {
					break _loop223;
				}
				
			} while (true);
			}
			
						// Se inserta en la tabla de Símbolos
						insertarIdentificador(param1,"number","");	
					
		}
		catch (RecognitionException ex) {
			reportError(ex);
			recover(ex,_tokenSet_7);
		}
	}
	
	public final void parametros_string() throws RecognitionException, TokenStreamException {
		
		String param1 = null;
		
		try {      // for error handling
			param1=valorparametro();
			{
			_loop220:
			do {
				if ((LA(1)==COMA)) {
					parametros_prima_string();
				}
				else {
					break _loop220;
				}
				
			} while (true);
			}
			
						// Se inserta en la tabla de Símbolos
						insertarIdentificador(param1,"string","");	
					
		}
		catch (RecognitionException ex) {
			reportError(ex);
			recover(ex,_tokenSet_7);
		}
	}
	
	public final Variable  addend() throws RecognitionException, TokenStreamException {
		Variable result = new Variable("","","");;
		
		Variable e1,e2;
		
		try {      // for error handling
			e1=factor();
			result = e1;
			{
			_loop183:
			do {
				switch ( LA(1)) {
				case OP_PRODUCTO:
				{
					{
					match(OP_PRODUCTO);
					e2=factor();
					
										if(e2.getTipo().equals("number") && result.getTipo().equals("number")) {
											result = new Variable("","number",String.valueOf(Float.parseFloat(result.getValor()) * Float.parseFloat(e2.getValor())));
										}
									
					}
					break;
				}
				case OP_DIVISION:
				{
					{
					match(OP_DIVISION);
					e2=factor();
					
										if(e2.getTipo().equals("number") && result.getTipo().equals("number")) {
											result = new Variable("","number",String.valueOf(Float.parseFloat(result.getValor()) / Float.parseFloat(e2.getValor())));
										}
									
					}
					break;
				}
				default:
				{
					break _loop183;
				}
				}
			} while (true);
			}
		}
		catch (RecognitionException re) {
			
						mostrarExcepcion(re);
					
		}
		return result;
	}
	
	public final Variable  negative() throws RecognitionException, TokenStreamException {
		Variable result = new Variable("","","");;
		
		Variable e;
		
		try {      // for error handling
			match(OP_MENOS);
			e=factor();
			
											if(e.getTipo().equals("number")) {
												result = new Variable("","number",String.valueOf(Float.parseFloat(e.getValor()) * -1));
											}
										
		}
		catch (RecognitionException ex) {
			reportError(ex);
			recover(ex,_tokenSet_8);
		}
		return result;
	}
	
	public final Variable  factor() throws RecognitionException, TokenStreamException {
		Variable result = new Variable("","","");;
		
		Token  i = null;
		Token  n = null;
		Token  n2 = null;
		Variable e;
		
		try {      // for error handling
			switch ( LA(1)) {
			case IDENT:
			{
				i = LT(1);
				match(IDENT);
				
							// Busca el identificador en la tabla de símbolos
							int indice = symbolsTable.existeSimbolo(i.getText());
				
							// Si encuentra el identificador, devuelve su valor
							if (indice >= 0)
							{
								// Se recupera el valor almacenado como cadena
								//String valorCadena = symbolsTable.getSimbolo(indice).getValor();
				
								// La cadena se convierte a número real
								
								result = symbolsTable.getSimbolo(indice);
							}
							else
								System.err.println("Error: el identificador " + i.getText() + " está indefinido");
						
				break;
			}
			case LIT_NUMERO:
			{
				n = LT(1);
				match(LIT_NUMERO);
				result = new Variable("","number",n.getText());
				break;
			}
			case LIT_CADENA:
			{
				n2 = LT(1);
				match(LIT_CADENA);
				result = new Variable("","string",n2.getText());
				break;
			}
			case PARENTESIS_IZ:
			{
				match(PARENTESIS_IZ);
				e=expression();
				match(PARENTESIS_DE);
				result = e;
				break;
			}
			default:
			{
				throw new NoViableAltException(LT(1), getFilename());
			}
			}
		}
		catch (RecognitionException re) {
			
						mostrarExcepcion(re);
					
		}
		return result;
	}
	
	public final boolean  condition() throws RecognitionException, TokenStreamException {
		boolean ressult = false;
		
		Variable e1, e2;
		
		try {      // for error handling
			e1=expression();
			{
			switch ( LA(1)) {
			case OP_IGUAL:
			{
				match(OP_IGUAL);
				e2=expression();
				
								if(e1.getTipo().equals("number") && e2.getTipo().equals("number")) {
									if (Float.parseFloat(e1.getValor()) == Float.parseFloat(e2.getValor()))
										ressult = true;
									else 
										ressult = false;
								} else if(e1.getTipo().equals("string") && e2.getTipo().equals("string")) {
									if (e1.getValor().equals(e2.getValor()))
										ressult = true;
									else 
										ressult = false;
								}
							
				break;
			}
			case OP_DISTINTO:
			{
				match(OP_DISTINTO);
				e2=expression();
				
								if(e1.getTipo().equals("number") && e2.getTipo().equals("number")) {
									if (Float.parseFloat(e1.getValor()) != Float.parseFloat(e2.getValor()))
										ressult = true;
									else 
										ressult = false;
								} else if(e1.getTipo().equals("string") && e2.getTipo().equals("string")) {
									if (!e1.getValor().equals(e2.getValor()))
										ressult = true;
									else 
										ressult = false;
								}
							
				break;
			}
			case OP_MENOR:
			{
				match(OP_MENOR);
				e2=expression();
				
								if(e1.getTipo().equals("number") && e2.getTipo().equals("number")) {
									if (Float.parseFloat(e1.getValor()) < Float.parseFloat(e2.getValor()))
										ressult = true;
									else 
										ressult = false;
								} else if(e1.getTipo().equals("string") && e2.getTipo().equals("string")) {
									ressult = false;
								}
							
				break;
			}
			case OP_MENOR_IGUAL:
			{
				match(OP_MENOR_IGUAL);
				e2=expression();
				
								if(e1.getTipo().equals("number") && e2.getTipo().equals("number")) {
									if (Float.parseFloat(e1.getValor()) <= Float.parseFloat(e2.getValor()))
										ressult = true;
									else 
										ressult = false;
								} else if(e1.getTipo().equals("string") && e2.getTipo().equals("string")) {
									ressult = false;
								}
							
				break;
			}
			case OP_MAYOR_IGUAL:
			{
				match(OP_MAYOR_IGUAL);
				e2=expression();
				
								if(e1.getTipo().equals("number") && e2.getTipo().equals("number")) {
									if (Float.parseFloat(e1.getValor()) >= Float.parseFloat(e2.getValor()))
										ressult = true;
									else 
										ressult = false;
								} else if(e1.getTipo().equals("string") && e2.getTipo().equals("string")) {
									ressult = false;
								}
							
				break;
			}
			case OP_MAYOR:
			{
				match(OP_MAYOR);
				e2=expression();
				
								if(e1.getTipo().equals("number") && e2.getTipo().equals("number")) {
									if (Float.parseFloat(e1.getValor()) > Float.parseFloat(e2.getValor()))
										ressult = true;
									else 
										ressult = false;
								} else if(e1.getTipo().equals("string") && e2.getTipo().equals("string")) {
									ressult = false;
								}
							
				break;
			}
			default:
			{
				throw new NoViableAltException(LT(1), getFilename());
			}
			}
			}
		}
		catch (RecognitionException re) {
			
						mostrarExcepcion(re);
					
		}
		return ressult;
	}
	
	public final String  valorparametro() throws RecognitionException, TokenStreamException {
		String result = new String();;
		
		Token  i = null;
		
		try {      // for error handling
			i = LT(1);
			match(IDENT);
				
						result = i.getText();
					
		}
		catch (RecognitionException ex) {
			reportError(ex);
			recover(ex,_tokenSet_9);
		}
		return result;
	}
	
	public final void parametros_prima_string() throws RecognitionException, TokenStreamException {
		
		String value = null;
		
		try {      // for error handling
			match(COMA);
			value=valorparametro();
			
						// Se inserta en la tabla de Símbolos
						insertarIdentificador(value,"string","");
					
		}
		catch (RecognitionException ex) {
			reportError(ex);
			recover(ex,_tokenSet_10);
		}
	}
	
	public final void parametros_prima_number() throws RecognitionException, TokenStreamException {
		
		String value = null;
		
		try {      // for error handling
			match(COMA);
			value=valorparametro();
			
						// Se inserta en la tabla de Símbolos
						insertarIdentificador(value,"number","");
					
		}
		catch (RecognitionException ex) {
			reportError(ex);
			recover(ex,_tokenSet_1);
		}
	}
	
	public final void nombrefuncion() throws RecognitionException, TokenStreamException {
		
		
		try {      // for error handling
			match(IDENT);
		}
		catch (RecognitionException ex) {
			reportError(ex);
			recover(ex,_tokenSet_1);
		}
	}
	
	public final void variable() throws RecognitionException, TokenStreamException {
		
		
		try {      // for error handling
			match(IDENT);
		}
		catch (RecognitionException ex) {
			reportError(ex);
			recover(ex,_tokenSet_1);
		}
	}
	
	
	public static final String[] _tokenNames = {
		"<0>",
		"EOF",
		"<2>",
		"NULL_TREE_LOOKAHEAD",
		"BEGIN_CONF",
		"END_CONF",
		"BEGIN_ADV",
		"END_ADV",
		"FUNC_LEER",
		"PARENT_IZ",
		"IDENT",
		"PARENT_DE",
		"PUNTO_COMA",
		"FUNC_SHOWBOARD",
		"FUNC_SHOWADVENTURESTATE",
		"FUNC_SETBOARDSIZE",
		"COMA",
		"FUNC_GETBOARDROWS",
		"FUNC_GETBOARDCOLUMNS",
		"FUNC_GETBOARDSIZE",
		"FUNC_SETTREASURE",
		"FUNC_REMOVETREASURE",
		"FUNC_GETTOTALTREASURES",
		"FUNC_SHOWTREASURES",
		"FUNC_GETTREASURE",
		"FUNC_SETHOLE",
		"FUNC_REMOVEHOLE",
		"FUNC_GETNUMBEROFHOLES",
		"FUNC_GETHOLE",
		"FUNC_SHOWHOLES",
		"FUNC_SETWUMPUS",
		"FUNC_GETWUMPUS",
		"FUNC_SETSTART",
		"FUNC_GETSTART",
		"FUNC_SETEXIT",
		"FUNC_GETEXIT",
		"FUNC_GETMECCA",
		"FUNC_SETARROWS",
		"FUNC_GETARROWS",
		"FUNC_INCARROWS",
		"FUNC_DECARROWS",
		"FUNC_GETREMAININGTREASURES",
		"FUNC_SHOOTLEFT",
		"FUNC_SHOOTRIGHT",
		"FUNC_SHOOTUP",
		"FUNC_SHOOTDOWN",
		"FUNC_GOLEFT",
		"FUNC_GORIGHT",
		"FUNC_GOUP",
		"FUNC_GODOWN",
		"TIPO_NUMERO",
		"OP_ASIG",
		"TIPO_CADENA",
		"OP_MAS",
		"OP_MENOS",
		"OP_PRODUCTO",
		"OP_DIVISION",
		"LIT_NUMERO",
		"LIT_CADENA",
		"PARENTESIS_IZ",
		"PARENTESIS_DE",
		"RES_SI",
		"RES_ENTONCES",
		"RES_SI_NO",
		"RES_FIN_SI",
		"OP_IGUAL",
		"OP_DISTINTO",
		"OP_MENOR",
		"OP_MENOR_IGUAL",
		"OP_MAYOR_IGUAL",
		"OP_MAYOR",
		"RES_MIENTRAS",
		"RES_HACER",
		"RES_FIN_MIENTRAS",
		"RES_REPETIR",
		"RES_HASTA",
		"RES_PARA",
		"RES_DESDE",
		"RES_PASO",
		"RES_FIN_PARA"
	};
	
	private static final long[] mk_tokenSet_0() {
		long[] data = { 2312598408654677248L, 5248L, 0L, 0L};
		return data;
	}
	public static final BitSet _tokenSet_0 = new BitSet(mk_tokenSet_0());
	private static final long[] mk_tokenSet_1() {
		long[] data = { 2L, 0L};
		return data;
	}
	public static final BitSet _tokenSet_1 = new BitSet(mk_tokenSet_1());
	private static final long[] mk_tokenSet_2() {
		long[] data = { -6910773628200098318L, 40577L, 0L, 0L};
		return data;
	}
	public static final BitSet _tokenSet_2 = new BitSet(mk_tokenSet_2());
	private static final long[] mk_tokenSet_3() {
		long[] data = { 2312598408654677312L, 5248L, 0L, 0L};
		return data;
	}
	public static final BitSet _tokenSet_3 = new BitSet(mk_tokenSet_3());
	private static final long[] mk_tokenSet_4() {
		long[] data = { 2312598408654677250L, 5248L, 0L, 0L};
		return data;
	}
	public static final BitSet _tokenSet_4 = new BitSet(mk_tokenSet_4());
	private static final long[] mk_tokenSet_5() {
		long[] data = { 2312598408654681344L, 5248L, 0L, 0L};
		return data;
	}
	public static final BitSet _tokenSet_5 = new BitSet(mk_tokenSet_5());
	private static final long[] mk_tokenSet_6() {
		long[] data = { 1026820715040474112L, 0L};
		return data;
	}
	public static final BitSet _tokenSet_6 = new BitSet(mk_tokenSet_6());
	private static final long[] mk_tokenSet_7() {
		long[] data = { 4096L, 0L};
		return data;
	}
	public static final BitSet _tokenSet_7 = new BitSet(mk_tokenSet_7());
	private static final long[] mk_tokenSet_8() {
		long[] data = { 5791629120798529536L, 18814L, 0L, 0L};
		return data;
	}
	public static final BitSet _tokenSet_8 = new BitSet(mk_tokenSet_8());
	private static final long[] mk_tokenSet_9() {
		long[] data = { 69634L, 0L};
		return data;
	}
	public static final BitSet _tokenSet_9 = new BitSet(mk_tokenSet_9());
	private static final long[] mk_tokenSet_10() {
		long[] data = { 69632L, 0L};
		return data;
	}
	public static final BitSet _tokenSet_10 = new BitSet(mk_tokenSet_10());
	
	}
