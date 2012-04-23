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

  // Atributo de Anasint para contar las instrucciones reconocidas
  int contador = 0;

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
			{
			int _cnt181=0;
			_loop181:
			do {
				if ((_tokenSet_1.member(LA(1)))) {
					instructionConf();
				}
				else {
					if ( _cnt181>=1 ) { break _loop181; } else {throw new NoViableAltException(LT(1), getFilename());}
				}
				
				_cnt181++;
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
			{
			int _cnt184=0;
			_loop184:
			do {
				if ((_tokenSet_3.member(LA(1)))) {
					instructionAd();
				}
				else {
					if ( _cnt184>=1 ) { break _loop184; } else {throw new NoViableAltException(LT(1), getFilename());}
				}
				
				_cnt184++;
			} while (true);
			}
			match(END_ADV);
		}
		catch (RecognitionException ex) {
			reportError(ex);
			recover(ex,_tokenSet_0);
		}
	}
	
	public final void instructionConf() throws RecognitionException, TokenStreamException {
		
		
		try {      // for error handling
			switch ( LA(1)) {
			case FUNC_LEER:
			{
				match(FUNC_LEER);
				match(PARENT_IZ);
				match(PARENT_DE);
				match(PUNTO_COMA);
				System.out.println("Reading");
				break;
			}
			case FUNC_SETBOARDSIZE:
			{
				match(FUNC_SETBOARDSIZE);
				match(PARENT_IZ);
				match(LIT_ENTERO);
				match(COMA);
				match(LIT_ENTERO);
				match(PARENT_DE);
				match(PUNTO_COMA);
				System.out.println("Board size saved");
				break;
			}
			case FUNC_GETBOARDROWS:
			{
				match(FUNC_GETBOARDROWS);
				match(PARENT_IZ);
				match(PARENT_DE);
				match(PUNTO_COMA);
				System.out.println("Board has X rows");
				break;
			}
			case FUNC_GETBOARDCOLUMNS:
			{
				match(FUNC_GETBOARDCOLUMNS);
				match(PARENT_IZ);
				match(PARENT_DE);
				match(PUNTO_COMA);
				System.out.println("Board has X columns");
				break;
			}
			case FUNC_GETBOARDSIZE:
			{
				match(FUNC_GETBOARDSIZE);
				match(PARENT_IZ);
				match(PARENT_DE);
				match(PUNTO_COMA);
				System.out.println("Board size is X rows and Y columns");
				break;
			}
			case FUNC_SETTREASURE:
			{
				match(FUNC_SETTREASURE);
				match(PARENT_IZ);
				match(LIT_ENTERO);
				match(COMA);
				match(LIT_ENTERO);
				match(PARENT_DE);
				match(PUNTO_COMA);
				System.out.println("Treasure position saved");
				break;
			}
			case FUNC_GETTOTALTREASURES:
			{
				match(FUNC_GETTOTALTREASURES);
				match(PARENT_IZ);
				match(PARENT_DE);
				match(PUNTO_COMA);
				System.out.println("Treasures are:");
				break;
			}
			case FUNC_GETTREASURE:
			{
				match(FUNC_GETTREASURE);
				match(PARENT_IZ);
				match(LIT_ENTERO);
				match(PARENT_DE);
				match(PUNTO_COMA);
				System.out.println("Treasure X is on row Y column Z:");
				break;
			}
			case FUNC_SETHOLE:
			{
				match(FUNC_SETHOLE);
				match(PARENT_IZ);
				match(LIT_ENTERO);
				match(COMA);
				match(LIT_ENTERO);
				match(PARENT_DE);
				match(PUNTO_COMA);
				System.out.println("Hole set on row X column Y");
				break;
			}
			case FUNC_GETNUMBEROFHOLES:
			{
				match(FUNC_GETNUMBEROFHOLES);
				match(PARENT_IZ);
				match(PARENT_DE);
				match(PUNTO_COMA);
				System.out.println("The number of holes is:");
				break;
			}
			case FUNC_GETHOLE:
			{
				match(FUNC_GETHOLE);
				match(PARENT_IZ);
				match(PARENT_DE);
				match(PUNTO_COMA);
				System.out.println("The hole number X is on row Y column Z");
				break;
			}
			case FUNC_SETWUMPUS:
			{
				match(FUNC_SETWUMPUS);
				match(PARENT_IZ);
				match(LIT_ENTERO);
				match(COMA);
				match(LIT_ENTERO);
				match(PARENT_DE);
				match(PUNTO_COMA);
				System.out.println("Wumpus set on row X column Y");
				break;
			}
			case FUNC_GETWUMPUS:
			{
				match(FUNC_GETWUMPUS);
				match(PARENT_IZ);
				match(PARENT_DE);
				match(PUNTO_COMA);
				System.out.println("The Wumpus is on row Y column Z");
				break;
			}
			case FUNC_SETSTART:
			{
				match(FUNC_SETSTART);
				match(PARENT_IZ);
				match(LIT_ENTERO);
				match(COMA);
				match(LIT_ENTERO);
				match(PARENT_DE);
				match(PUNTO_COMA);
				System.out.println("Start set on row X column Y");
				break;
			}
			case FUNC_GETSTART:
			{
				match(FUNC_GETSTART);
				match(PARENT_IZ);
				match(PARENT_DE);
				match(PUNTO_COMA);
				System.out.println("Start is on row X column Y");
				break;
			}
			case FUNC_SETEXIT:
			{
				match(FUNC_SETEXIT);
				match(PARENT_IZ);
				match(LIT_ENTERO);
				match(COMA);
				match(LIT_ENTERO);
				match(PARENT_DE);
				match(PUNTO_COMA);
				System.out.println("Exit set on row X column Y");
				break;
			}
			case FUNC_GETEXIT:
			{
				match(FUNC_GETEXIT);
				match(PARENT_IZ);
				match(PARENT_DE);
				match(PUNTO_COMA);
				System.out.println("Exit is on row X column Y");
				break;
			}
			case FUNC_GETMECCA:
			{
				match(FUNC_GETMECCA);
				match(PARENT_IZ);
				match(PARENT_DE);
				match(PUNTO_COMA);
				System.out.println("Mecca is on row X column Y");
				break;
			}
			case FUNC_SETARROWS:
			{
				match(FUNC_SETARROWS);
				match(PARENT_IZ);
				match(LIT_ENTERO);
				match(PARENT_DE);
				match(PUNTO_COMA);
				System.out.println("Mecca has now X arrows");
				break;
			}
			case FUNC_GETARROWS:
			{
				match(FUNC_GETARROWS);
				match(PARENT_IZ);
				match(PARENT_DE);
				match(PUNTO_COMA);
				System.out.println("Mecca has X arrows");
				break;
			}
			case FUNC_INCARROWS:
			{
				match(FUNC_INCARROWS);
				match(PARENT_IZ);
				match(LIT_ENTERO);
				match(PARENT_DE);
				match(PUNTO_COMA);
				System.out.println("Arrows incremented in X, Mecca has now Y arrows");
				break;
			}
			case FUNC_DECARROWS:
			{
				match(FUNC_DECARROWS);
				match(PARENT_IZ);
				match(LIT_ENTERO);
				match(PARENT_DE);
				match(PUNTO_COMA);
				System.out.println("Arrows decremented in X, Mecca has now Y arrows");
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
			recover(ex,_tokenSet_4);
		}
	}
	
	public final void instructionAd() throws RecognitionException, TokenStreamException {
		
		
		try {      // for error handling
			switch ( LA(1)) {
			case FUNC_LEER:
			{
				match(FUNC_LEER);
				match(PARENT_IZ);
				{
				_loop188:
				do {
					if ((LA(1)==IDENT)) {
						parametros();
					}
					else {
						break _loop188;
					}
					
				} while (true);
				}
				match(PARENT_DE);
				match(PUNTO_COMA);
				break;
			}
			case FUNC_GETBOARDROWS:
			{
				match(FUNC_GETBOARDROWS);
				match(PARENT_IZ);
				match(PARENT_DE);
				match(PUNTO_COMA);
				System.out.println("Board has X rows");
				break;
			}
			case FUNC_GETBOARDCOLUMNS:
			{
				match(FUNC_GETBOARDCOLUMNS);
				match(PARENT_IZ);
				match(PARENT_DE);
				match(PUNTO_COMA);
				System.out.println("Board has X columns");
				break;
			}
			case FUNC_GETBOARDSIZE:
			{
				match(FUNC_GETBOARDSIZE);
				match(PARENT_IZ);
				match(PARENT_DE);
				match(PUNTO_COMA);
				System.out.println("Board size is X rows and Y columns");
				break;
			}
			case FUNC_GETTOTALTREASURES:
			{
				match(FUNC_GETTOTALTREASURES);
				match(PARENT_IZ);
				match(PARENT_DE);
				match(PUNTO_COMA);
				System.out.println("Treasures are:");
				break;
			}
			case FUNC_GETMECCA:
			{
				match(FUNC_GETMECCA);
				match(PARENT_IZ);
				match(PARENT_DE);
				match(PUNTO_COMA);
				System.out.println("Mecca is on row X column Y");
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
			case FUNC_GETARROWS:
			{
				match(FUNC_GETARROWS);
				match(PARENT_IZ);
				match(PARENT_DE);
				match(PUNTO_COMA);
				System.out.println("Mecca has X arrows");
				break;
			}
			case FUNC_SHOOTLEFT:
			{
				match(FUNC_SHOOTLEFT);
				match(PARENT_IZ);
				match(PARENT_DE);
				match(PUNTO_COMA);
				System.out.println("Mecca shot left");
				break;
			}
			case FUNC_SHOOTRIGHT:
			{
				match(FUNC_SHOOTRIGHT);
				match(PARENT_IZ);
				match(PARENT_DE);
				match(PUNTO_COMA);
				System.out.println("Mecca shot right");
				break;
			}
			case FUNC_SHOOTUP:
			{
				match(FUNC_SHOOTUP);
				match(PARENT_IZ);
				match(PARENT_DE);
				match(PUNTO_COMA);
				System.out.println("Mecca shot up");
				break;
			}
			case FUNC_SHOOTDOWN:
			{
				match(FUNC_SHOOTDOWN);
				match(PARENT_IZ);
				match(PARENT_DE);
				match(PUNTO_COMA);
				System.out.println("Mecca shot down");
				break;
			}
			case FUNC_GOLEFT:
			{
				match(FUNC_GOLEFT);
				match(PARENT_IZ);
				match(PARENT_DE);
				match(PUNTO_COMA);
				System.out.println("Mecca went left");
				break;
			}
			case FUNC_GORIGHT:
			{
				match(FUNC_GORIGHT);
				match(PARENT_IZ);
				match(PARENT_DE);
				match(PUNTO_COMA);
				System.out.println("Mecca went right");
				break;
			}
			case FUNC_GOUP:
			{
				match(FUNC_GOUP);
				match(PARENT_IZ);
				match(PARENT_DE);
				match(PUNTO_COMA);
				System.out.println("Mecca went up");
				break;
			}
			case FUNC_GODOWN:
			{
				match(FUNC_GODOWN);
				match(PARENT_IZ);
				match(PARENT_DE);
				match(PUNTO_COMA);
				System.out.println("Mecca went down");
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
			recover(ex,_tokenSet_5);
		}
	}
	
	public final void parametros() throws RecognitionException, TokenStreamException {
		
		
		try {      // for error handling
			valorparametro();
			{
			_loop191:
			do {
				if ((LA(1)==COMA)) {
					parametros_prima();
				}
				else {
					break _loop191;
				}
				
			} while (true);
			}
		}
		catch (RecognitionException ex) {
			reportError(ex);
			recover(ex,_tokenSet_6);
		}
	}
	
	public final void valorparametro() throws RecognitionException, TokenStreamException {
		
		
		try {      // for error handling
			match(IDENT);
		}
		catch (RecognitionException ex) {
			reportError(ex);
			recover(ex,_tokenSet_7);
		}
	}
	
	public final void parametros_prima() throws RecognitionException, TokenStreamException {
		
		
		try {      // for error handling
			match(COMA);
			valorparametro();
		}
		catch (RecognitionException ex) {
			reportError(ex);
			recover(ex,_tokenSet_7);
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
		"PARENT_DE",
		"PUNTO_COMA",
		"FUNC_SETBOARDSIZE",
		"LIT_ENTERO",
		"COMA",
		"FUNC_GETBOARDROWS",
		"FUNC_GETBOARDCOLUMNS",
		"FUNC_GETBOARDSIZE",
		"FUNC_SETTREASURE",
		"FUNC_GETTOTALTREASURES",
		"FUNC_GETTREASURE",
		"FUNC_SETHOLE",
		"FUNC_GETNUMBEROFHOLES",
		"FUNC_GETHOLE",
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
		"IDENT"
	};
	
	private static final long[] mk_tokenSet_0() {
		long[] data = { 2L, 0L};
		return data;
	}
	public static final BitSet _tokenSet_0 = new BitSet(mk_tokenSet_0());
	private static final long[] mk_tokenSet_1() {
		long[] data = { 34359709952L, 0L};
		return data;
	}
	public static final BitSet _tokenSet_1 = new BitSet(mk_tokenSet_1());
	private static final long[] mk_tokenSet_2() {
		long[] data = { 64L, 0L};
		return data;
	}
	public static final BitSet _tokenSet_2 = new BitSet(mk_tokenSet_2());
	private static final long[] mk_tokenSet_3() {
		long[] data = { 17563195769088L, 0L};
		return data;
	}
	public static final BitSet _tokenSet_3 = new BitSet(mk_tokenSet_3());
	private static final long[] mk_tokenSet_4() {
		long[] data = { 34359709984L, 0L};
		return data;
	}
	public static final BitSet _tokenSet_4 = new BitSet(mk_tokenSet_4());
	private static final long[] mk_tokenSet_5() {
		long[] data = { 17563195769216L, 0L};
		return data;
	}
	public static final BitSet _tokenSet_5 = new BitSet(mk_tokenSet_5());
	private static final long[] mk_tokenSet_6() {
		long[] data = { 17592186045440L, 0L};
		return data;
	}
	public static final BitSet _tokenSet_6 = new BitSet(mk_tokenSet_6());
	private static final long[] mk_tokenSet_7() {
		long[] data = { 17592186061824L, 0L};
		return data;
	}
	public static final BitSet _tokenSet_7 = new BitSet(mk_tokenSet_7());
	
	}
