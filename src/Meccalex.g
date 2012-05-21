/*
  Ejemplo 11
            
	Reconoce los mismos componentes léxicos que los ejemplos 9 y 10

 	Este ejemplo muestra cómo se puede modificar el tipo de token utilizado por el analizador léxico

	Se ha creado un NuevoToken que incorpora un nuevo atributo: nombreFichero
*/
    
// CLASE QUE PERMITIRÁ CREA EL ANALIZADOR LÉXICO

class Meccalex extends Lexer;

// ZONA DE OPCIONES DEL ANALIZADOR LÉXICO
options{
	// Unicodes usuales
    charVocabulary = '\3'..'\377';
    
	// Se indica que los literales se comprueben localmente
	testLiterals=false;

	// lookahead: número de símbolos de anticipación
	k=2;

	// No se tiene en cuenta la diferencia entre mayúsculas y minúsculas en los identificadores
	caseSensitive=false;

	// No se tiene en cuenta la diferencia entre mayúsculas y minúsculas en los literales (palabras reservadas)
	caseSensitiveLiterals = false;
	
	importVocab = Meccasint;
}
// FIN DE LA ZONA DE OPCIONES DEL ANALIZADOR LÉXICO

// DECLARACIÓN DE TOKENS PREDEFINIDOS

tokens
{
	//HECHO POR TEAM MECCA - THIS WORKS
	// Tipos basicos
	BEGIN_CONF       = "ConfigurationMode";
	END_CONF         = "endConfigurationMode";
    BEGIN_ADV        = "AdventureMode";
    END_ADV          = "endAdventureMode";
    //Funciones
    FUNC_LEER = "leer";
    FUNC_SHOWBOARD = "showBoard";
    FUNC_SHOWADVENTURESTATE = "showAdventureState";
    FUNC_SETBOARDSIZE = "setBoardSize";
    FUNC_GETBOARDROWS = "getBoardRows";
    FUNC_GETBOARDCOLUMNS = "getBoardColumns";
    FUNC_GETBOARDSIZE = "getBoardSize";
    FUNC_SETTREASURE = "setTreasure";
    FUNC_GETTOTALTREASURES = "getTotalTreasures";
    FUNC_GETTREASURE = "getTreasure";
    FUNC_SHOWTREASURES = "showTreasures";    
    FUNC_REMOVETREASURE = "removeTreasure";
    FUNC_SETHOLE = "setHole";
    FUNC_REMOVEHOLE = "removeHole";
    FUNC_GETNUMBEROFHOLES = "getNumberOfHoles";
    FUNC_GETHOLE = "getHole";
    FUNC_SHOWHOLES = "showHoles";
    FUNC_SETWUMPUS = "setWumpus";
    FUNC_GETWUMPUS = "getWumpus";
    FUNC_SETSTART = "setStart";
    FUNC_GETSTART = "getStart";
    FUNC_SETEXIT = "setExit";
    FUNC_GETEXIT = "getExit";
    FUNC_GETMECCA = "getMecca";
    FUNC_GETREMAININGTREASURES = "getRemainingTreasures";
    
    //MECCA
    FUNC_SETARROWS = "setArrows";
    FUNC_GETARROWS = "getArrows";
    FUNC_INCARROWS = "incArrows";
    FUNC_DECARROWS = "decArrows";
    FUNC_SHOOTLEFT = "shootLeft";
    FUNC_SHOOTRIGHT = "shootRighT";
    FUNC_SHOOTUP = "shootUp";
    FUNC_SHOOTDOWN = "shootDown";
    FUNC_GOLEFT = "goLeft";
    FUNC_GORIGHT = "goRight";
    FUNC_GOUP = "goUp";
    FUNC_GODOWN = "goDown";
    
    
	TIPO_CADENA   = "String";
	TIPO_NUMERO = "Number";
    
    
	// Literales lógicos
	LIT_CIERTO = "true" ; 
	LIT_FALSO = "falso" ;
    
	// Literales cadena
	LIT_NL  = "nl" ; 
	LIT_TAB = "tab" ; 
	LIT_COM = "com" ;
    

    RES_MIENTRAS     = "while" ;
	RES_HACER        = "do" ;
	RES_REPETIR		 = "repeat";
	RES_HASTA        = "until";
	RES_FIN_MIENTRAS = "end_while" ;
	RES_SI           = "if" ;
	RES_ENTONCES     = "then" ;
	RES_SI_NO        = "else" ;
	RES_FIN_SI       = "end_if" ;
	RES_PARA         = "for" ;
	RES_PASO		 = "step" ;
	RES_DESDE        = "from" ;
	RES_FIN_PARA     = "end_for" ;


    //ENGLISHFICADO POR TEAM MECCA
	// Operadores que empiezan con letras;
	OP_Y  = "_and" ;
	OP_O  = "_or" ;
	OP_NO = "_not" ;
    //FIN ENGLISHFICADO POR TEAM MECCA

	// Declaración de componentes léxicos "virtuales" 
	// Los literales real y entero son "devueltos" 
	// en las acciones del token "privado" LIT_NUMERO
	LIT_REAL ; 
	LIT_ENTERO;
}
// FIN DE LA DECLARACIÓN DE TOKENS PREDEFINIDOS


/////////////////////////////////////////////////////////////////////////////

// En este lugar se puede incluir código auxiliar escrito en Java (opcional)


////////////////////////////////////////////////////////////////////////////


// ZONA DE REGLAS
// Tipos de retorno de carro o salto de línea
protected NL :
	(
		// MS-DOS
		// Se usa un predicado sintáctico para resolver el conflicto
		 ("\r\n") => "\r\n" 

		// UNIX
		| '\n'

		// MACINTOSH
		| '\r' 	

	)
		{ newline(); }
	;


// Otra forma alternativa
/*
protected NL :
	(
		// MS-DOS
		options{ generateAmbigWarnings=false;}:
		 "\r\n"

		// UNIX
		| '\n'

		// MACINTOSH
		| '\r' 	

	)
		{ newline(); }
	;
*/


// Regla para ignorar los blancos.

BLANCO :
		 ( ' '
		 | '\t'
		 | NL
		 ) 	 { $setType(Token.SKIP); }
		 ;

//  Letras
protected LETRA : 'a'..'z'
		// Se comenta esta línea porque se ha usado la opción caseSensitive=false;
//		| 'A'..'Z'
		;

// Dígitos 
protected DIGITO : '0'..'9';


// Regla para reconocer los literales (y palabras reservadas).
IDENT
	// Se indica que se comprueben las palabras reservadas
	options {testLiterals=true;} 
	: (LETRA) (LETRA|DIGITO|'_'(LETRA|DIGITO))*
	;

// Separadores 
PUNTO_COMA : ';' 
	;
COMA : ',' 
	;
PARENT_IZ : '(' 
	  ; 
PARENT_DE : ')' 
	  ;
CORCHETE_IZ : '[' 
	    ;
CORCHETE_DE : ']' 
	    ;
//LLAVE_IZ : '{' 
//	 ;
//LLAVE_DE : '}' 
//	 ;
PUNTO : '.' 
      ;
BARRA_VERT : '|' 
	;

//Operadores relacionales

// Se necesita que k = 2 para que no tenga conflicto con OP_ASIG
OP_IGUAL : "==" 
	 ;

OP_DISTINTO : "<>" 
	    ;

OP_ASIG : ":=" 
	;

OP_MENOR : '<' 
	 ;

OP_MAYOR : '>' 
	 ;

// Se necesita que k = 2 para que no tenga conflicto con OP_MENOR
OP_MENOR_IGUAL : "<=" 
	       ;

// Se necesita que k = 2 para que no tenga conflicto con OP_MAYOR
OP_MAYOR_IGUAL : ">=" 
	       ;

OP_MAS : '+' 
       ;

OP_MENOS : '-' 
	 ;

OP_PRODUCTO : '*' 
	    ;

OP_DIVISION : '/' 
	    ;


// Se reconocen los números enteros y reales sin generar conflictos.

LIT_NUMERO : 
	     // Se usa un predicado sintáctico para comprobar si el número es real
		( DIGITO )+ ('.' ( DIGITO )*)? ('e' (OP_MENOS | OP_MAS)? (DIGITO)+)?
	   ;


// Comentario de una sola línea

protected COMENTARIO1: "#" (~ ('\n'|'\r') )*	
		     ;

//protected COMENTARIO2 : "/*" 
//			( 	
//				('*' NL) => '*' NL
//				| ('*' ~('/'|'\n'|'\r')) => '*' ~('/'|'\n'|'\r')
//				| NL
//				| ~( '\n' | '\r' | '*' )
//			)*
//			"*/"
//			;

protected COMENTARIO2: 
			"{" 
			   (options {greedy=false;} : . )* 
		        "}"			 
   		      ; 

// Los comentarios se ignoran
COMENTARIO:
	  (
	    COMENTARIO1 
	  | COMENTARIO2 
          )		{ $setType(Token.SKIP); }
	  ;


// Cadenas de caracteres
// LIT_CADENA : '"' !
//		   ( ~('"'|'\n'|'\r') )*
//	     '"' !
//	   ;

// Cadena que permite incluir saltos de línea
LIT_CADENA: '"'! 
                ( options {greedy=false;}: ~('\\')
                 | "\\\"")* 
            '"'!
          ;

LIT_CARACTER : '\''!
		      ( ~('\'') )*
		'\''!
	     ;
	     
	     

// FIN DE LA ZONA DE REGLAS LÉXICAS
