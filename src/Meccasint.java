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
<<<<<<< HEAD
			int _cnt1287=0;
			_loop1287:
=======
			int _cnt427=0;
			_loop427:
>>>>>>> 41341afa7c83f02eed14b847757918f434e6bb17
			do {
				if ((_tokenSet_1.member(LA(1)))) {
					instructionConf();
				}
				else {
<<<<<<< HEAD
					if ( _cnt1287>=1 ) { break _loop1287; } else {throw new NoViableAltException(LT(1), getFilename());}
				}
				
				_cnt1287++;
=======
					if ( _cnt427>=1 ) { break _loop427; } else {throw new NoViableAltException(LT(1), getFilename());}
				}
				
				_cnt427++;
>>>>>>> 41341afa7c83f02eed14b847757918f434e6bb17
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
<<<<<<< HEAD
			int _cnt1290=0;
			_loop1290:
=======
			int _cnt430=0;
			_loop430:
>>>>>>> 41341afa7c83f02eed14b847757918f434e6bb17
			do {
				if ((LA(1)==FUNC_LEER)) {
					instructionAd();
				}
				else {
<<<<<<< HEAD
					if ( _cnt1290>=1 ) { break _loop1290; } else {throw new NoViableAltException(LT(1), getFilename());}
				}
				
				_cnt1290++;
=======
					if ( _cnt430>=1 ) { break _loop430; } else {throw new NoViableAltException(LT(1), getFilename());}
				}
				
				_cnt430++;
>>>>>>> 41341afa7c83f02eed14b847757918f434e6bb17
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
<<<<<<< HEAD
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
				System.out.println("The number of holes is:");
				break;
			}
			default:
			{
				throw new NoViableAltException(LT(1), getFilename());
			}
=======
			int _cnt433=0;
			_loop433:
			do {
				if ((LA(1)==IDENT)) {
					parametros();
				}
				else {
					if ( _cnt433>=1 ) { break _loop433; } else {throw new NoViableAltException(LT(1), getFilename());}
				}
				
				_cnt433++;
			} while (true);
>>>>>>> 41341afa7c83f02eed14b847757918f434e6bb17
			}
		}
		catch (RecognitionException ex) {
			reportError(ex);
			recover(ex,_tokenSet_3);
		}
	}
	
	public final void instructionAd() throws RecognitionException, TokenStreamException {
		
		
		try {      // for error handling
			match(FUNC_LEER);
			match(PARENT_IZ);
			{
<<<<<<< HEAD
			_loop1294:
=======
			_loop436:
>>>>>>> 41341afa7c83f02eed14b847757918f434e6bb17
			do {
				if ((LA(1)==IDENT)) {
					parametros();
				}
				else {
<<<<<<< HEAD
					break _loop1294;
=======
					break _loop436;
>>>>>>> 41341afa7c83f02eed14b847757918f434e6bb17
				}
				
			} while (true);
			}
			match(PARENT_DE);
			match(PUNTO_COMA);
		}
		catch (RecognitionException ex) {
			reportError(ex);
			recover(ex,_tokenSet_4);
		}
	}
	
	public final void parametros() throws RecognitionException, TokenStreamException {
		
		
		try {      // for error handling
			valorparametro();
			{
<<<<<<< HEAD
			_loop1297:
=======
			_loop439:
>>>>>>> 41341afa7c83f02eed14b847757918f434e6bb17
			do {
				if ((LA(1)==COMA)) {
					parametros_prima();
				}
				else {
<<<<<<< HEAD
					break _loop1297;
=======
					break _loop439;
>>>>>>> 41341afa7c83f02eed14b847757918f434e6bb17
				}
				
			} while (true);
			}
		}
		catch (RecognitionException ex) {
			reportError(ex);
			recover(ex,_tokenSet_5);
		}
	}
	
	public final void valorparametro() throws RecognitionException, TokenStreamException {
		
		
		try {      // for error handling
			match(IDENT);
		}
		catch (RecognitionException ex) {
			reportError(ex);
			recover(ex,_tokenSet_6);
		}
	}
	
	public final void parametros_prima() throws RecognitionException, TokenStreamException {
		
		
		try {      // for error handling
			match(COMA);
			valorparametro();
		}
		catch (RecognitionException ex) {
			reportError(ex);
			recover(ex,_tokenSet_6);
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
		"IDENT"
	};
	
	private static final long[] mk_tokenSet_0() {
		long[] data = { 2L, 0L};
		return data;
	}
	public static final BitSet _tokenSet_0 = new BitSet(mk_tokenSet_0());
	private static final long[] mk_tokenSet_1() {
		long[] data = { 16748800L, 0L};
		return data;
	}
	public static final BitSet _tokenSet_1 = new BitSet(mk_tokenSet_1());
	private static final long[] mk_tokenSet_2() {
		long[] data = { 64L, 0L};
		return data;
	}
	public static final BitSet _tokenSet_2 = new BitSet(mk_tokenSet_2());
	private static final long[] mk_tokenSet_3() {
		long[] data = { 16748832L, 0L};
		return data;
	}
	public static final BitSet _tokenSet_3 = new BitSet(mk_tokenSet_3());
	private static final long[] mk_tokenSet_4() {
		long[] data = { 384L, 0L};
		return data;
	}
	public static final BitSet _tokenSet_4 = new BitSet(mk_tokenSet_4());
	private static final long[] mk_tokenSet_5() {
		long[] data = { 16778240L, 0L};
		return data;
	}
	public static final BitSet _tokenSet_5 = new BitSet(mk_tokenSet_5());
	private static final long[] mk_tokenSet_6() {
		long[] data = { 16794624L, 0L};
		return data;
	}
	public static final BitSet _tokenSet_6 = new BitSet(mk_tokenSet_6());
	
	}
