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
			configuration();
			adventure();
		}
		catch (RecognitionException ex) {
			reportError(ex);
			recover(ex,_tokenSet_0);
		}
	}
	
	public final void configuration() throws RecognitionException, TokenStreamException {
		
		
		try {      // for error handling
			match(BEGIN_CONF);
			mode = CONFIGURATION_MODE;
			{
			int _cnt81=0;
			_loop81:
			do {
				if ((_tokenSet_1.member(LA(1)))) {
					instruction();
				}
				else {
					if ( _cnt81>=1 ) { break _loop81; } else {throw new NoViableAltException(LT(1), getFilename());}
				}
				
				_cnt81++;
			} while (true);
			}
			match(END_CONF);
		}
		catch (RecognitionException ex) {
			reportError(ex);
			recover(ex,_tokenSet_2);
		}
	}
	
	public final void adventure() throws RecognitionException, TokenStreamException {
		
		
		try {      // for error handling
			match(BEGIN_ADV);
			if(board.initGame()) mode = ADVENTURE_MODE;
			{
			int _cnt84=0;
			_loop84:
			do {
				if ((_tokenSet_1.member(LA(1)))) {
					instruction();
				}
				else {
					if ( _cnt84>=1 ) { break _loop84; } else {throw new NoViableAltException(LT(1), getFilename());}
				}
				
				_cnt84++;
			} while (true);
			}
			match(END_ADV);
		}
		catch (RecognitionException ex) {
			reportError(ex);
			recover(ex,_tokenSet_0);
		}
	}
	
	public final void instruction() throws RecognitionException, TokenStreamException {
		
		Token  i2 = null;
		Token  i = null;
		int param1, param2;
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
			case FUNC_SETBOARDSIZE:
			{
				match(FUNC_SETBOARDSIZE);
				match(PARENT_IZ);
				param1=entero();
				match(COMA);
				param2=entero();
				match(PARENT_DE);
				match(PUNTO_COMA);
				
										if(mode == CONFIGURATION_MODE) {
											Size newSize = new Size(param1,param2);
											board.setSize(newSize);
											System.out.println("Board has now "+board.getSize().getWidth()+" columns and "+board.getSize().getHeight()+" rows");
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
				param1=entero();
				match(COMA);
				param2=entero();
				match(PARENT_DE);
				match(PUNTO_COMA);
				
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
									
				break;
			}
			case FUNC_REMOVETREASURE:
			{
				match(FUNC_REMOVETREASURE);
				match(PARENT_IZ);
				param1=entero();
				match(PARENT_DE);
				match(PUNTO_COMA);
										
										if(board.removeTreasure(param1)) {
											System.out.println("Treasure " + param1 + " has been removed");
										} else {
											System.out.println("Treasure " + param1 + " does not exist");
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
				param1=entero();
				match(PARENT_DE);
				match(PUNTO_COMA);
				
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
									
				break;
			}
			case FUNC_SETHOLE:
			{
				match(FUNC_SETHOLE);
				match(PARENT_IZ);
				param1=entero();
				match(COMA);
				param2=entero();
				match(PARENT_DE);
				match(PUNTO_COMA);
				
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
									
				break;
			}
			case FUNC_REMOVEHOLE:
			{
				match(FUNC_REMOVEHOLE);
				match(PARENT_IZ);
				param1=entero();
				match(PARENT_DE);
				match(PUNTO_COMA);
										
										if(board.removeHole(param1)) {
											System.out.println("Hole " + param1 + " has been removed");
										} else {
											System.out.println("Hole " + param1 + " does not exist");
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
				param1=entero();
				match(PARENT_DE);
				match(PUNTO_COMA);
				
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
				param1=entero();
				match(COMA);
				param2=entero();
				match(PARENT_DE);
				match(PUNTO_COMA);
				
										if(mode == CONFIGURATION_MODE) {
											Position newWumpus = new Position(param1,param2);
											
											if(board.setWumpusPos(newWumpus)) {
												System.out.println("Wumpus set on column "+board.getWumpusPos().getX()+" row "+board.getWumpusPos().getY());
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
				param1=entero();
				match(COMA);
				param2=entero();
				match(PARENT_DE);
				match(PUNTO_COMA);
				
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
				param1=entero();
				match(COMA);
				param2=entero();
				match(PARENT_DE);
				match(PUNTO_COMA);
				
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
				param1=entero();
				match(PARENT_DE);
				match(PUNTO_COMA);
				
										if(mode == CONFIGURATION_MODE) {
											board.setMeccaNArrows(param1);
											System.out.println("Mecca has now "+board.getMeccaNArrows()+" arrows");
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
				param1=entero();
				match(PARENT_DE);
				match(PUNTO_COMA);
				
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
									
				break;
			}
			case FUNC_DECARROWS:
			{
				match(FUNC_DECARROWS);
				match(PARENT_IZ);
				param1=entero();
				match(PARENT_DE);
				match(PUNTO_COMA);
				
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
			{
				i = LT(1);
				match(IDENT);
				match(OP_ASIG);
				param1=entero();
				match(PUNTO_COMA);
				
										//Get identifier name
										String idName = i.getText();
										
										//Convert the number value into string
										String nValue = String.valueOf(param1);
										
										//Insert it into the symbols table
										symbolsTable.insertarSimbolo(new Variable(idName,"int",nValue));	
									
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
			recover(ex,_tokenSet_3);
		}
	}
	
	public final int  entero() throws RecognitionException, TokenStreamException {
		int value=0;
		
		Token  i1 = null;
		
		try {      // for error handling
			i1 = LT(1);
			match(LIT_ENTERO);
			value = Integer.parseInt(i1.getText());
		}
		catch (RecognitionException ex) {
			reportError(ex);
			recover(ex,_tokenSet_4);
		}
		return value;
	}
	
	public final void parametros() throws RecognitionException, TokenStreamException {
		
		
		try {      // for error handling
			valorparametro();
			{
			_loop88:
			do {
				if ((LA(1)==COMA)) {
					parametros_prima();
				}
				else {
					break _loop88;
				}
				
			} while (true);
			}
		}
		catch (RecognitionException ex) {
			reportError(ex);
			recover(ex,_tokenSet_0);
		}
	}
	
	public final void valorparametro() throws RecognitionException, TokenStreamException {
		
		
		try {      // for error handling
			match(IDENT);
		}
		catch (RecognitionException ex) {
			reportError(ex);
			recover(ex,_tokenSet_5);
		}
	}
	
	public final void parametros_prima() throws RecognitionException, TokenStreamException {
		
		
		try {      // for error handling
			match(COMA);
			valorparametro();
		}
		catch (RecognitionException ex) {
			reportError(ex);
			recover(ex,_tokenSet_5);
		}
	}
	
	public final void nombrefuncion() throws RecognitionException, TokenStreamException {
		
		
		try {      // for error handling
			match(IDENT);
		}
		catch (RecognitionException ex) {
			reportError(ex);
			recover(ex,_tokenSet_0);
		}
	}
	
	public final void variable() throws RecognitionException, TokenStreamException {
		
		
		try {      // for error handling
			match(IDENT);
		}
		catch (RecognitionException ex) {
			reportError(ex);
			recover(ex,_tokenSet_0);
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
		"OP_ASIG",
		"LIT_ENTERO"
	};
	
	private static final long[] mk_tokenSet_0() {
		long[] data = { 2L, 0L};
		return data;
	}
	public static final BitSet _tokenSet_0 = new BitSet(mk_tokenSet_0());
	private static final long[] mk_tokenSet_1() {
		long[] data = { 562949953381632L, 0L};
		return data;
	}
	public static final BitSet _tokenSet_1 = new BitSet(mk_tokenSet_1());
	private static final long[] mk_tokenSet_2() {
		long[] data = { 64L, 0L};
		return data;
	}
	public static final BitSet _tokenSet_2 = new BitSet(mk_tokenSet_2());
	private static final long[] mk_tokenSet_3() {
		long[] data = { 562949953381792L, 0L};
		return data;
	}
	public static final BitSet _tokenSet_3 = new BitSet(mk_tokenSet_3());
	private static final long[] mk_tokenSet_4() {
		long[] data = { 38912L, 0L};
		return data;
	}
	public static final BitSet _tokenSet_4 = new BitSet(mk_tokenSet_4());
	private static final long[] mk_tokenSet_5() {
		long[] data = { 32770L, 0L};
		return data;
	}
	public static final BitSet _tokenSet_5 = new BitSet(mk_tokenSet_5());
	
	}
