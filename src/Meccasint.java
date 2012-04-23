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
			int _cnt427=0;
			_loop427:
			do {
				if ((LA(1)==FUNC_LEER)) {
					instructionConf();
				}
				else {
					if ( _cnt427>=1 ) { break _loop427; } else {throw new NoViableAltException(LT(1), getFilename());}
				}
				
				_cnt427++;
			} while (true);
			}
			match(END_CONF);
		}
		catch (RecognitionException ex) {
			reportError(ex);
			recover(ex,_tokenSet_1);
		}
	}
	
	public final void adventure() throws RecognitionException, TokenStreamException {
		
		
		try {      // for error handling
			match(BEGIN_ADV);
			{
			int _cnt430=0;
			_loop430:
			do {
				if ((LA(1)==FUNC_LEER)) {
					instructionAd();
				}
				else {
					if ( _cnt430>=1 ) { break _loop430; } else {throw new NoViableAltException(LT(1), getFilename());}
				}
				
				_cnt430++;
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
			match(FUNC_LEER);
			match(PARENT_IZ);
			{
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
			}
			match(PARENT_DE);
			match(PUNTO_COMA);
		}
		catch (RecognitionException ex) {
			reportError(ex);
			recover(ex,_tokenSet_2);
		}
	}
	
	public final void instructionAd() throws RecognitionException, TokenStreamException {
		
		
		try {      // for error handling
			match(FUNC_LEER);
			match(PARENT_IZ);
			{
			_loop436:
			do {
				if ((LA(1)==IDENT)) {
					parametros();
				}
				else {
					break _loop436;
				}
				
			} while (true);
			}
			match(PARENT_DE);
			match(PUNTO_COMA);
		}
		catch (RecognitionException ex) {
			reportError(ex);
			recover(ex,_tokenSet_3);
		}
	}
	
	public final void parametros() throws RecognitionException, TokenStreamException {
		
		
		try {      // for error handling
			valorparametro();
			{
			_loop439:
			do {
				if ((LA(1)==COMA)) {
					parametros_prima();
				}
				else {
					break _loop439;
				}
				
			} while (true);
			}
		}
		catch (RecognitionException ex) {
			reportError(ex);
			recover(ex,_tokenSet_4);
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
		"COMA",
		"IDENT"
	};
	
	private static final long[] mk_tokenSet_0() {
		long[] data = { 2L, 0L};
		return data;
	}
	public static final BitSet _tokenSet_0 = new BitSet(mk_tokenSet_0());
	private static final long[] mk_tokenSet_1() {
		long[] data = { 64L, 0L};
		return data;
	}
	public static final BitSet _tokenSet_1 = new BitSet(mk_tokenSet_1());
	private static final long[] mk_tokenSet_2() {
		long[] data = { 288L, 0L};
		return data;
	}
	public static final BitSet _tokenSet_2 = new BitSet(mk_tokenSet_2());
	private static final long[] mk_tokenSet_3() {
		long[] data = { 384L, 0L};
		return data;
	}
	public static final BitSet _tokenSet_3 = new BitSet(mk_tokenSet_3());
	private static final long[] mk_tokenSet_4() {
		long[] data = { 9216L, 0L};
		return data;
	}
	public static final BitSet _tokenSet_4 = new BitSet(mk_tokenSet_4());
	private static final long[] mk_tokenSet_5() {
		long[] data = { 13312L, 0L};
		return data;
	}
	public static final BitSet _tokenSet_5 = new BitSet(mk_tokenSet_5());
	
	}
