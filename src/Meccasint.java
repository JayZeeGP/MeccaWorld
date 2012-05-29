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
			_loop7103:
			do {
				if ((_tokenSet_0.member(LA(1)))) {
					instruction();
				}
				else {
					break _loop7103;
				}
				
			} while (true);
			}
			configuration();
			{
			_loop7105:
			do {
				if ((_tokenSet_0.member(LA(1)))) {
					instruction();
				}
				else {
					break _loop7105;
				}
				
			} while (true);
			}
			adventure();
			{
			_loop7107:
			do {
				if ((_tokenSet_0.member(LA(1)))) {
					instruction();
				}
				else {
					break _loop7107;
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
		
		Token  e1 = null;
		Token  i2 = null;
		Token  e2 = null;
		Token  e3 = null;
		Token  e4 = null;
		Token  e5 = null;
		Token  e6 = null;
		Token  e7 = null;
		Token  e8 = null;
		Token  e9 = null;
		Token  e10 = null;
		Token  e11 = null;
		Token  e12 = null;
		Token  e13 = null;
		Token  e14 = null;
		Token  e15 = null;
		Token  e16 = null;
		Token  e17 = null;
		Token  e18 = null;
		Token  e19 = null;
		Token  e20 = null;
		Token  e21 = null;
		Token  e22 = null;
		Token  e23 = null;
		Token  e24 = null;
		Token  e25 = null;
		Token  e26 = null;
		Token  e27 = null;
		Token  e28 = null;
		Token  e29 = null;
		Token  e30 = null;
		Token  e31 = null;
		Token  e32 = null;
		Token  e33 = null;
		Token  e34 = null;
		Token  e35 = null;
		Token  e36 = null;
		
				Variable param1, param2;
			
		
		try {      // for error handling
			switch ( LA(1)) {
			case FUNC_LEER:
			{
				e1 = LT(1);
				match(FUNC_LEER);
				match(PARENT_IZ);
				i2 = LT(1);
				match(IDENT);
				match(PARENT_DE);
				match(PUNTO_COMA);
					
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
						
				break;
			}
			case FUNC_ESCRIBIR:
			{
				match(FUNC_ESCRIBIR);
				match(PARENT_IZ);
				param1=expression();
				match(PARENT_DE);
				match(PUNTO_COMA);
				
							System.out.println(param1.getValor());
						
				break;
			}
			case FUNC_SHOWBOARD:
			{
				e2 = LT(1);
				match(FUNC_SHOWBOARD);
				match(PARENT_IZ);
				match(PARENT_DE);
				match(PUNTO_COMA);
				
							if(mode == CONFIGURATION_MODE) {
								System.out.println(board.showBoard());
							} else {
								System.err.println("Line " + e2.getLine() + ":" + e2.getColumn() + " - This instruction has to be called in Configuration Mode");
							}
						
				break;
			}
			case FUNC_SHOWADVENTURESTATE:
			{
				e3 = LT(1);
				match(FUNC_SHOWADVENTURESTATE);
				match(PARENT_IZ);
				match(PARENT_DE);
				match(PUNTO_COMA);
				
							if(mode == ADVENTURE_MODE) {
								board.showAdventureState();
							} else {
								System.err.println("Line " + e3.getLine() + ":" + e3.getColumn() + " - This instruction has to be called in Configuration Mode");
							}
						
				break;
			}
			case FUNC_SETBOARDSIZE:
			{
				e4 = LT(1);
				match(FUNC_SETBOARDSIZE);
				match(PARENT_IZ);
				param1=expression();
				match(COMA);
				param2=expression();
				match(PARENT_DE);
				match(PUNTO_COMA);
				
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
						
				break;
			}
			case FUNC_GETBOARDROWS:
			{
				e5 = LT(1);
				match(FUNC_GETBOARDROWS);
				match(PARENT_IZ);
				match(PARENT_DE);
				match(PUNTO_COMA);
				
							if(mode == CONFIGURATION_MODE || mode == ADVENTURE_MODE) {					
								System.out.println("Board has "+board.getSize().getHeight()+" rows");
							} else {
								System.err.println("Line " + e5.getLine() + ":" + e5.getColumn() + " - " + "This instruction has to be called in Configuration Mode or Adventure Mode");							
							}
						
				break;
			}
			case FUNC_GETBOARDCOLUMNS:
			{
				e6 = LT(1);
				match(FUNC_GETBOARDCOLUMNS);
				match(PARENT_IZ);
				match(PARENT_DE);
				match(PUNTO_COMA);
				
							if(mode == CONFIGURATION_MODE || mode == ADVENTURE_MODE) {	
								System.out.println("Board has "+board.getSize().getWidth()+" columns");
							} else {
								System.err.println("Line " + e6.getLine() + ":" + e6.getColumn() + " - " + "This instruction has to be called in Configuration Mode or Adventure Mode");							
							}
						
				break;
			}
			case FUNC_GETBOARDSIZE:
			{
				e7 = LT(1);
				match(FUNC_GETBOARDSIZE);
				match(PARENT_IZ);
				match(PARENT_DE);
				match(PUNTO_COMA);
				
							if(mode == CONFIGURATION_MODE || mode == ADVENTURE_MODE) {	
								System.out.println("Board size is "+board.getSize().getHeight()+" rows and "+board.getSize().getWidth()+" columns");
							} else {
								System.err.println("Line " + e7.getLine() + ":" + e7.getColumn() + " - " + "This instruction has to be called in Configuration Mode or Adventure Mode");													
							}
						
				break;
			}
			case FUNC_SETTREASURE:
			{
				e8 = LT(1);
				match(FUNC_SETTREASURE);
				match(PARENT_IZ);
				param1=expression();
				match(COMA);
				param2=expression();
				match(PARENT_DE);
				match(PUNTO_COMA);
				
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
						
				break;
			}
			case FUNC_REMOVETREASURE:
			{
				e9 = LT(1);
				match(FUNC_REMOVETREASURE);
				match(PARENT_IZ);
				param1=expression();
				match(PARENT_DE);
				match(PUNTO_COMA);
										
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
				e10 = LT(1);
				match(FUNC_SHOWTREASURES);
				match(PARENT_IZ);
				match(PARENT_DE);
				match(PUNTO_COMA);
				
							if(mode == CONFIGURATION_MODE) {
								board.showTreasures();
							} else {
								System.err.println("Line " + e10.getLine() + ":" + e10.getColumn() + " - " + "This instruction has to be called in Configuration Mode");							
							}
						
				break;
			}
			case FUNC_GETTREASURE:
			{
				e11 = LT(1);
				match(FUNC_GETTREASURE);
				match(PARENT_IZ);
				param1=expression();
				match(PARENT_DE);
				match(PUNTO_COMA);
				
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
						
				break;
			}
			case FUNC_SETHOLE:
			{
				e12 = LT(1);
				match(FUNC_SETHOLE);
				match(PARENT_IZ);
				param1=expression();
				match(COMA);
				param2=expression();
				match(PARENT_DE);
				match(PUNTO_COMA);
				
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
						
				break;
			}
			case FUNC_REMOVEHOLE:
			{
				e13 = LT(1);
				match(FUNC_REMOVEHOLE);
				match(PARENT_IZ);
				param1=expression();
				match(PARENT_DE);
				match(PUNTO_COMA);
				
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
						
				break;
			}
			case FUNC_GETNUMBEROFHOLES:
			{
				e14 = LT(1);
				match(FUNC_GETNUMBEROFHOLES);
				match(PARENT_IZ);
				match(PARENT_DE);
				match(PUNTO_COMA);
				
							if(mode == CONFIGURATION_MODE) {
								System.out.println("The number of holes is: "+board.getNumberOfHoles());
							} else {
								System.err.println("Line " + e14.getLine() + ":" + e14.getColumn() + " - " + "This instruction has to be called in Configuration Mode");							
							}
						
				break;
			}
			case FUNC_GETHOLE:
			{
				e15 = LT(1);
				match(FUNC_GETHOLE);
				match(PARENT_IZ);
				param1=expression();
				match(PARENT_DE);
				match(PUNTO_COMA);
				
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
						
				break;
			}
			case FUNC_SHOWHOLES:
			{
				e16 = LT(1);
				match(FUNC_SHOWHOLES);
				match(PARENT_IZ);
				match(PARENT_DE);
				match(PUNTO_COMA);
				
							if(mode == CONFIGURATION_MODE) {
								board.showHoles();
							} else {
								System.err.println("Line " + e16.getLine() + ":" + e16.getColumn() + " - " + "This instruction has to be called in Configuration Mode");							
							}
						
				break;
			}
			case FUNC_SETWUMPUS:
			{
				e17 = LT(1);
				match(FUNC_SETWUMPUS);
				match(PARENT_IZ);
				param1=expression();
				match(COMA);
				param2=expression();
				match(PARENT_DE);
				match(PUNTO_COMA);
				
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
						
				break;
			}
			case FUNC_GETWUMPUS:
			{
				e18 = LT(1);
				match(FUNC_GETWUMPUS);
				match(PARENT_IZ);
				match(PARENT_DE);
				match(PUNTO_COMA);
				
							if(mode == CONFIGURATION_MODE) {		
								System.out.println("The Wumpus is on column "+ board.getWumpusPos().getX()+" row " + board.getWumpusPos().getY());
							} else {
								System.err.println("Line " + e18.getLine() + ":" + e18.getColumn() + " - " + "This instruction has to be called in Configuration Mode");
							}
						
				break;
			}
			case FUNC_SETSTART:
			{
				e19 = LT(1);
				match(FUNC_SETSTART);
				match(PARENT_IZ);
				param1=expression();
				match(COMA);
				param2=expression();
				match(PARENT_DE);
				match(PUNTO_COMA);
				
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
						
				break;
			}
			case FUNC_GETSTART:
			{
				e20 = LT(1);
				match(FUNC_GETSTART);
				match(PARENT_IZ);
				match(PARENT_DE);
				match(PUNTO_COMA);
				
							if(mode == CONFIGURATION_MODE) {
								System.out.println("Start is on column "+board.getStartPos().getX()+" row "+board.getStartPos().getY());
							} else {
								System.err.println("Line " + e20.getLine() + ":" + e20.getColumn() + " - " + "This instruction has to be called in Configuration Mode");
							}	
						
				break;
			}
			case FUNC_SETEXIT:
			{
				e21 = LT(1);
				match(FUNC_SETEXIT);
				match(PARENT_IZ);
				param1=expression();
				match(COMA);
				param2=expression();
				match(PARENT_DE);
				match(PUNTO_COMA);
				
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
						
				break;
			}
			case FUNC_GETEXIT:
			{
				e22 = LT(1);
				match(FUNC_GETEXIT);
				match(PARENT_IZ);
				match(PARENT_DE);
				match(PUNTO_COMA);
				
							if(mode == CONFIGURATION_MODE) {
								System.out.println("Exit is on column "+board.getExitPos().getX()+" row "+board.getExitPos().getY());
							} else {
								System.err.println("Line " + e22.getLine() + ":" + e22.getColumn() + " - " + "This instruction has to be called in Configuration Mode");
							}
						
				break;
			}
			case FUNC_GETMECCA:
			{
				e23 = LT(1);
				match(FUNC_GETMECCA);
				match(PARENT_IZ);
				match(PARENT_DE);
				match(PUNTO_COMA);
				
							if(mode == CONFIGURATION_MODE || mode == ADVENTURE_MODE) {
								System.out.println("Mecca is on row "+ board.getMeccaPos().getY()+" column " + board.getMeccaPos().getX());
							} else {
								System.err.println("Line " + e23.getLine() + ":" + e23.getColumn() + " - " + "This instruction has to be called in Configuration Mode or Adventure Mode");
							}
						
				break;
			}
			case FUNC_SETARROWS:
			{
				e24 = LT(1);
				match(FUNC_SETARROWS);
				match(PARENT_IZ);
				param1=expression();
				match(PARENT_DE);
				match(PUNTO_COMA);
				
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
						
				break;
			}
			case FUNC_GETARROWS:
			{
				e25 = LT(1);
				match(FUNC_GETARROWS);
				match(PARENT_IZ);
				match(PARENT_DE);
				match(PUNTO_COMA);
				
							if(mode == CONFIGURATION_MODE || mode == ADVENTURE_MODE) {
								System.out.println("Mecca has "+board.getMeccaNArrows()+" arrows");
							} else {
								System.err.println("Line " + e25.getLine() + ":" + e25.getColumn() + " - " + "This instruction has to be called in Configuration Mode or Adventure Mode");
							}						
						
				break;
			}
			case FUNC_INCARROWS:
			{
				e26 = LT(1);
				match(FUNC_INCARROWS);
				match(PARENT_IZ);
				param1=expression();
				match(PARENT_DE);
				match(PUNTO_COMA);
				
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
						
				break;
			}
			case FUNC_DECARROWS:
			{
				e27 = LT(1);
				match(FUNC_DECARROWS);
				match(PARENT_IZ);
				param1=expression();
				match(PARENT_DE);
				match(PUNTO_COMA);
				
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
						
				break;
			}
			case FUNC_GETREMAININGTREASURES:
			{
				e28 = LT(1);
				match(FUNC_GETREMAININGTREASURES);
				match(PARENT_IZ);
				match(PARENT_DE);
				match(PUNTO_COMA);
				
							if(mode == ADVENTURE_MODE) {
								System.out.println(board.getTotalTreasures() + " treasures remaining");
							} else {
								System.err.println("Line " + e28.getLine() + ":" + e28.getColumn() + " - " + "This instruction has to be called in Adventure Mode");
							}
						
				break;
			}
			case FUNC_SHOOTLEFT:
			{
				e29 = LT(1);
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
								System.err.println("Line " + e29.getLine() + ":" + e29.getColumn() + " - " + "This instruction has to be called in Adventure Mode");
							}
						
				break;
			}
			case FUNC_SHOOTRIGHT:
			{
				e30 = LT(1);
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
								System.err.println("Line " + e30.getLine() + ":" + e30.getColumn() + " - " + "This instruction has to be called in Adventure Mode");
							}
						
				break;
			}
			case FUNC_SHOOTUP:
			{
				e31 = LT(1);
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
								System.err.println("Line " + e31.getLine() + ":" + e31.getColumn() + " - " + "This instruction has to be called in Adventure Mode");
							}
						
				break;
			}
			case FUNC_SHOOTDOWN:
			{
				e32 = LT(1);
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
								System.err.println("Line " + e32.getLine() + ":" + e32.getColumn() + " - " + "This instruction has to be called in Adventure Mode");
							}
						
				break;
			}
			case FUNC_GOLEFT:
			{
				e33 = LT(1);
				match(FUNC_GOLEFT);
				match(PARENT_IZ);
				match(PARENT_DE);
				match(PUNTO_COMA);
				
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
						
				break;
			}
			case FUNC_GORIGHT:
			{
				e34 = LT(1);
				match(FUNC_GORIGHT);
				match(PARENT_IZ);
				match(PARENT_DE);
				match(PUNTO_COMA);
				
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
						
				break;
			}
			case FUNC_GOUP:
			{
				e35 = LT(1);
				match(FUNC_GOUP);
				match(PARENT_IZ);
				match(PARENT_DE);
				match(PUNTO_COMA);
				
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
						
				break;
			}
			case FUNC_GODOWN:
			{
				e36 = LT(1);
				match(FUNC_GODOWN);
				match(PARENT_IZ);
				match(PARENT_DE);
				match(PUNTO_COMA);
				
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
			_loop7110:
			do {
				if ((_tokenSet_0.member(LA(1)))) {
					instruction();
				}
				else {
					break _loop7110;
				}
				
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
			_loop7113:
			do {
				if ((_tokenSet_0.member(LA(1)))) {
					instruction();
				}
				else {
					break _loop7113;
				}
				
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
		
		
				Variable e1, e2;
			
		
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
				_loop7122:
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
						break _loop7122;
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
				_loop7126:
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
						break _loop7126;
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
			
						mostrarExcepcion(re);
					
		}
		return result;
	}
	
	public final void asignation() throws RecognitionException, TokenStreamException {
		
		Token  e37 = null;
		Token  i = null;
		Token  e38 = null;
		Token  i2 = null;
		Token  i3 = null;
		
				Variable e = null;
			
		
		try {      // for error handling
			switch ( LA(1)) {
			case TIPO_NUMERO:
			{
				e37 = LT(1);
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
								String valorCadena = e.getValor().toString();
					
								// Se inserta en la tabla de Símbolos
								if(!insertarIdentificador(nombre,"number",valorCadena)) {
									System.err.println("Line " + e37.getLine() + ":" + e37.getColumn() + " - " + "La variable \"" + nombre + "\" ya había sido declarada"); 	
								}
							}
						
				break;
			}
			case TIPO_CADENA:
			{
				e38 = LT(1);
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
								if(!insertarIdentificador(nombre,"number",valorCadena)) {
									System.err.println("Line " + e38.getLine() + ":" + e38.getColumn() + " - " + "La variable \"" + nombre + "\" ya había sido declarada"); 	
								}
							}
						
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
										Float value = Float.parseFloat(e.getValor().toString());
										simbolo.setValor(String.valueOf(value));
										
									} catch(NumberFormatException err) {
										System.err.println("Line " + i3.getLine() + ":" + i3.getColumn() + " - " + "La variable \"" + nombre + "\" es de tipo \"number\"");
									}
								} else {
									simbolo.setValor(e.getValor().toString());
								}
							} else {
								System.err.println("Line " + i3.getLine() + ":" + i3.getColumn() + " - " + "La variable \"" + nombre + "\" no ha sido declarada");
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
			valor=logical_condition();
			match(RES_ENTONCES);
			{
			if (((_tokenSet_0.member(LA(1))) && ((LA(2) >= BEGIN_CONF && LA(2) <= RES_FIN_PARA)))&&(valor==true)) {
				{
				int _cnt7137=0;
				_loop7137:
				do {
					if ((_tokenSet_0.member(LA(1)))) {
						instruction();
					}
					else {
						if ( _cnt7137>=1 ) { break _loop7137; } else {throw new NoViableAltException(LT(1), getFilename());}
					}
					
					_cnt7137++;
				} while (true);
				}
				{
				switch ( LA(1)) {
				case RES_SI_NO:
				{
					match(RES_SI_NO);
					{
					int _cnt7140=0;
					_loop7140:
					do {
						// nongreedy exit test
						if ( _cnt7140>=1 && (LA(1)==RES_FIN_SI) && (LA(2)==PUNTO_COMA)) break _loop7140;
						if (((LA(1) >= BEGIN_CONF && LA(1) <= RES_FIN_PARA)) && ((LA(2) >= BEGIN_CONF && LA(2) <= RES_FIN_PARA))) {
							matchNot(EOF);
						}
						else {
							if ( _cnt7140>=1 ) { break _loop7140; } else {throw new NoViableAltException(LT(1), getFilename());}
						}
						
						_cnt7140++;
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
				int _cnt7142=0;
				_loop7142:
				do {
					// nongreedy exit test
					if ( _cnt7142>=1 && (LA(1)==RES_SI_NO||LA(1)==RES_FIN_SI) && (_tokenSet_5.member(LA(2)))) break _loop7142;
					if (((LA(1) >= BEGIN_CONF && LA(1) <= RES_FIN_PARA)) && ((LA(2) >= BEGIN_CONF && LA(2) <= RES_FIN_PARA))) {
						matchNot(EOF);
					}
					else {
						if ( _cnt7142>=1 ) { break _loop7142; } else {throw new NoViableAltException(LT(1), getFilename());}
					}
					
					_cnt7142++;
				} while (true);
				}
				{
				switch ( LA(1)) {
				case RES_SI_NO:
				{
					match(RES_SI_NO);
					{
					int _cnt7145=0;
					_loop7145:
					do {
						if ((_tokenSet_0.member(LA(1)))) {
							instruction();
						}
						else {
							if ( _cnt7145>=1 ) { break _loop7145; } else {throw new NoViableAltException(LT(1), getFilename());}
						}
						
						_cnt7145++;
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
				_loop7157:
				do {
					// nongreedy exit test
					if ((LA(1)==RES_FIN_MIENTRAS) && (LA(2)==PUNTO_COMA)) break _loop7157;
					if (((LA(1) >= BEGIN_CONF && LA(1) <= RES_FIN_PARA)) && ((LA(2) >= BEGIN_CONF && LA(2) <= RES_FIN_PARA))) {
						matchNot(EOF);
					}
					else {
						break _loop7157;
					}
					
				} while (true);
				}
				match(RES_FIN_MIENTRAS);
				match(PUNTO_COMA);
			}
			else if (((_tokenSet_0.member(LA(1))) && ((LA(2) >= BEGIN_CONF && LA(2) <= RES_FIN_PARA)))&&(valor == true)) {
				{
				int _cnt7159=0;
				_loop7159:
				do {
					if ((_tokenSet_0.member(LA(1)))) {
						instruction();
					}
					else {
						if ( _cnt7159>=1 ) { break _loop7159; } else {throw new NoViableAltException(LT(1), getFilename());}
					}
					
					_cnt7159++;
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
				_loop7163:
				do {
					// nongreedy exit test
					if ((LA(1)==RES_HASTA) && (_tokenSet_6.member(LA(2)))) break _loop7163;
					if (((LA(1) >= BEGIN_CONF && LA(1) <= RES_FIN_PARA)) && ((LA(2) >= BEGIN_CONF && LA(2) <= RES_FIN_PARA))) {
						matchNot(EOF);
					}
					else {
						break _loop7163;
					}
					
				} while (true);
				}
				match(RES_HASTA);
				valor=condition();
				match(PUNTO_COMA);
			}
			else if (((_tokenSet_0.member(LA(1))) && ((LA(2) >= BEGIN_CONF && LA(2) <= RES_FIN_PARA)))&&(valor == false)) {
				{
				int _cnt7165=0;
				_loop7165:
				do {
					if ((_tokenSet_0.member(LA(1)))) {
						instruction();
					}
					else {
						if ( _cnt7165>=1 ) { break _loop7165; } else {throw new NoViableAltException(LT(1), getFilename());}
					}
					
					_cnt7165++;
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
		
				int marca=-1;
				Variable initValue=new Variable("","number","-1"), endValue=new Variable("","number","-1"), inc=new Variable("","number","-1");
				int index=-1;
				String id;
				boolean firstTimeTest=true;
			
		
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
				_loop7169:
				do {
					// nongreedy exit test
					if ((LA(1)==RES_FIN_PARA) && (LA(2)==PUNTO_COMA)) break _loop7169;
					if (((LA(1) >= BEGIN_CONF && LA(1) <= RES_FIN_PARA)) && ((LA(2) >= BEGIN_CONF && LA(2) <= RES_FIN_PARA))) {
						matchNot(EOF);
					}
					else {
						break _loop7169;
					}
					
				} while (true);
				}
				match(RES_FIN_PARA);
				match(PUNTO_COMA);
			}
			else if (((_tokenSet_0.member(LA(1))) && ((LA(2) >= BEGIN_CONF && LA(2) <= RES_FIN_PARA)))&&(Float.parseFloat(symbolsTable.getSimbolo(index).getValor()) < Float.parseFloat(endValue.getValor()))) {
				{
				int _cnt7171=0;
				_loop7171:
				do {
					if ((_tokenSet_0.member(LA(1)))) {
						instruction();
					}
					else {
						if ( _cnt7171>=1 ) { break _loop7171; } else {throw new NoViableAltException(LT(1), getFilename());}
					}
					
					_cnt7171++;
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
			_loop7177:
			do {
				if ((LA(1)==COMA)) {
					parametros_prima_number();
				}
				else {
					break _loop7177;
				}
				
			} while (true);
			}
			
						// Se inserta en la tabla de Símbolos
						insertarIdentificador(param1,"number","0");	
					
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
			_loop7174:
			do {
				if ((LA(1)==COMA)) {
					parametros_prima_string();
				}
				else {
					break _loop7174;
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
		
		
				Variable e1, e2;
			
		
		try {      // for error handling
			e1=factor();
			result = e1;
			{
			_loop7131:
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
					break _loop7131;
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
							if (indice >= 0) {
								// Se recupera el valor almacenado como cadena
								//String valorCadena = symbolsTable.getSimbolo(indice).getValor();
				
								// La cadena se convierte a número real
								
								result = symbolsTable.getSimbolo(indice);
							} else
								System.err.println("Line " + i.getLine() + ":" + i.getColumn() + " - " + "El identificador " + i.getText() + " está indefinido");
						
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
	
	public final boolean  logical_condition() throws RecognitionException, TokenStreamException {
		boolean result = false;
		
		Token  i1 = null;
		Token  i2 = null;
		Token  i3 = null;
		
				boolean e1=false, e2=false;
			
		
		try {      // for error handling
			{
			switch ( LA(1)) {
			case OP_NO:
			{
				i1 = LT(1);
				match(OP_NO);
				break;
			}
			case IDENT:
			case OP_MENOS:
			case LIT_NUMERO:
			case LIT_CADENA:
			case PARENTESIS_IZ:
			{
				break;
			}
			default:
			{
				throw new NoViableAltException(LT(1), getFilename());
			}
			}
			}
			e1=condition();
			
					if(i1!=null)
						result = !e1;
					else
						result = e1;
				
			{
			_loop7151:
			do {
				switch ( LA(1)) {
				case OP_Y:
				{
					match(OP_Y);
					{
					switch ( LA(1)) {
					case OP_NO:
					{
						i2 = LT(1);
						match(OP_NO);
						break;
					}
					case IDENT:
					case OP_MENOS:
					case LIT_NUMERO:
					case LIT_CADENA:
					case PARENTESIS_IZ:
					{
						break;
					}
					default:
					{
						throw new NoViableAltException(LT(1), getFilename());
					}
					}
					}
					e2=condition();
					
									if(i2!=null)
										e2 = !e2;
					
									if(e1 == true && e2 == true)
										result = true;
								
					break;
				}
				case OP_O:
				{
					match(OP_O);
					{
					switch ( LA(1)) {
					case OP_NO:
					{
						i3 = LT(1);
						match(OP_NO);
						break;
					}
					case IDENT:
					case OP_MENOS:
					case LIT_NUMERO:
					case LIT_CADENA:
					case PARENTESIS_IZ:
					{
						break;
					}
					default:
					{
						throw new NoViableAltException(LT(1), getFilename());
					}
					}
					}
					e2=condition();
					
									if(i3!=null)
										e2 = !e2;
										
									if(e1 == true || e2 == true)
										result = true;
								
					break;
				}
				default:
				{
					break _loop7151;
				}
				}
			} while (true);
			}
		}
		catch (RecognitionException ex) {
			reportError(ex);
			recover(ex,_tokenSet_9);
		}
		return result;
	}
	
	public final boolean  condition() throws RecognitionException, TokenStreamException {
		boolean result = false;
		
		
				Variable e1, e2;
				boolean not=false;
			
		
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
										result = true;
									else 
										result = false;
								} else if(e1.getTipo().equals("string") && e2.getTipo().equals("string")) {
									if (e1.getValor().equals(e2.getValor()))
										result = true;
									else 
										result = false;
								}
							
				break;
			}
			case OP_DISTINTO:
			{
				match(OP_DISTINTO);
				e2=expression();
				
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
							
				break;
			}
			case OP_MENOR:
			{
				match(OP_MENOR);
				e2=expression();
				
								if(e1.getTipo().equals("number") && e2.getTipo().equals("number")) {
									if (Float.parseFloat(e1.getValor()) < Float.parseFloat(e2.getValor()))
										result = true;
									else 
										result = false;
								} else if(e1.getTipo().equals("string") && e2.getTipo().equals("string")) {
									result = false;
								}
							
				break;
			}
			case OP_MENOR_IGUAL:
			{
				match(OP_MENOR_IGUAL);
				e2=expression();
				
								if(e1.getTipo().equals("number") && e2.getTipo().equals("number")) {
									if (Float.parseFloat(e1.getValor()) <= Float.parseFloat(e2.getValor()))
										result = true;
									else 
										result = false;
								} else if(e1.getTipo().equals("string") && e2.getTipo().equals("string")) {
									result = false;
								}
							
				break;
			}
			case OP_MAYOR_IGUAL:
			{
				match(OP_MAYOR_IGUAL);
				e2=expression();
				
								if(e1.getTipo().equals("number") && e2.getTipo().equals("number")) {
									if (Float.parseFloat(e1.getValor()) >= Float.parseFloat(e2.getValor()))
										result = true;
									else 
										result = false;
								} else if(e1.getTipo().equals("string") && e2.getTipo().equals("string")) {
									result = false;
								}
							
				break;
			}
			case OP_MAYOR:
			{
				match(OP_MAYOR);
				e2=expression();
				
								if(e1.getTipo().equals("number") && e2.getTipo().equals("number")) {
									if (Float.parseFloat(e1.getValor()) > Float.parseFloat(e2.getValor()))
										result = true;
									else 
										result = false;
								} else if(e1.getTipo().equals("string") && e2.getTipo().equals("string")) {
									result = false;
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
		return result;
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
			recover(ex,_tokenSet_10);
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
						insertarIdentificador(value,"number","0");
					
		}
		catch (RecognitionException ex) {
			reportError(ex);
			recover(ex,_tokenSet_10);
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
		"FUNC_ESCRIBIR",
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
		"OP_NO",
		"OP_Y",
		"OP_O",
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
		long[] data = { 4625196817309361408L, 83968L, 0L, 0L};
		return data;
	}
	public static final BitSet _tokenSet_0 = new BitSet(mk_tokenSet_0());
	private static final long[] mk_tokenSet_1() {
		long[] data = { 2L, 0L};
		return data;
	}
	public static final BitSet _tokenSet_1 = new BitSet(mk_tokenSet_1());
	private static final long[] mk_tokenSet_2() {
		long[] data = { 4625196817309361650L, 649219L, 0L, 0L};
		return data;
	}
	public static final BitSet _tokenSet_2 = new BitSet(mk_tokenSet_2());
	private static final long[] mk_tokenSet_3() {
		long[] data = { 4625196817309361472L, 83968L, 0L, 0L};
		return data;
	}
	public static final BitSet _tokenSet_3 = new BitSet(mk_tokenSet_3());
	private static final long[] mk_tokenSet_4() {
		long[] data = { 4625196817309361410L, 83968L, 0L, 0L};
		return data;
	}
	public static final BitSet _tokenSet_4 = new BitSet(mk_tokenSet_4());
	private static final long[] mk_tokenSet_5() {
		long[] data = { 4625196817309365504L, 83968L, 0L, 0L};
		return data;
	}
	public static final BitSet _tokenSet_5 = new BitSet(mk_tokenSet_5());
	private static final long[] mk_tokenSet_6() {
		long[] data = { 2053641430080947200L, 0L};
		return data;
	}
	public static final BitSet _tokenSet_6 = new BitSet(mk_tokenSet_6());
	private static final long[] mk_tokenSet_7() {
		long[] data = { 4096L, 0L};
		return data;
	}
	public static final BitSet _tokenSet_7 = new BitSet(mk_tokenSet_7());
	private static final long[] mk_tokenSet_8() {
		long[] data = { -6863485832112498688L, 301048L, 0L, 0L};
		return data;
	}
	public static final BitSet _tokenSet_8 = new BitSet(mk_tokenSet_8());
	private static final long[] mk_tokenSet_9() {
		long[] data = { -9223372036854775808L, 0L};
		return data;
	}
	public static final BitSet _tokenSet_9 = new BitSet(mk_tokenSet_9());
	private static final long[] mk_tokenSet_10() {
		long[] data = { 135168L, 0L};
		return data;
	}
	public static final BitSet _tokenSet_10 = new BitSet(mk_tokenSet_10());
	
	}
