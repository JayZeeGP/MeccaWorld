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
}




//HECHO POR TEAM MECCA

mecca: configuration adventure;

configuration: BEGIN_CONF (instructionConf)+ END_CONF;

adventure: BEGIN_ADV (instructionAd)+ END_ADV;

instructionConf: FUNC_LEER PARENT_IZ PARENT_DE PUNTO_COMA {System.out.println("Reading");}
				| FUNC_SETBOARDSIZE PARENT_IZ LIT_ENTERO COMA LIT_ENTERO PARENT_DE PUNTO_COMA {System.out.println("Board size saved");}
				| FUNC_GETBOARDROWS PARENT_IZ PARENT_DE PUNTO_COMA {System.out.println("Board has X rows");}
				| FUNC_GETBOARDCOLUMNS PARENT_IZ PARENT_DE PUNTO_COMA {System.out.println("Board has X columns");}
				| FUNC_GETBOARDSIZE PARENT_IZ PARENT_DE PUNTO_COMA {System.out.println("Board size is X rows and Y columns");}
				| FUNC_SETTREASURE PARENT_IZ LIT_ENTERO COMA LIT_ENTERO PARENT_DE PUNTO_COMA {System.out.println("Treasure position saved");}
				| FUNC_GETTOTALTREASURES PARENT_IZ PARENT_DE PUNTO_COMA {System.out.println("Treasures are:");}
				| FUNC_GETTREASURE PARENT_IZ LIT_ENTERO PARENT_DE PUNTO_COMA {System.out.println("Treasure X is on row Y column Z:");}
				| FUNC_SETHOLE PARENT_IZ LIT_ENTERO COMA LIT_ENTERO PARENT_DE PUNTO_COMA {System.out.println("Hole set on row X column Y");}
				| FUNC_GETNUMBEROFHOLES PARENT_IZ PARENT_DE PUNTO_COMA {System.out.println("The number of holes is:");}
				| FUNC_GETHOLE PARENT_IZ PARENT_DE PUNTO_COMA {System.out.println("The hole number X is on row Y column Z");}
				| FUNC_SETWUMPUS PARENT_IZ LIT_ENTERO COMA LIT_ENTERO PARENT_DE PUNTO_COMA {System.out.println("Wumpus set on row X column Y");}
				| FUNC_GETWUMPUS PARENT_IZ PARENT_DE PUNTO_COMA {System.out.println("The Wumpus is on row Y column Z");}
				| FUNC_SETSTART PARENT_IZ LIT_ENTERO COMA LIT_ENTERO PARENT_DE PUNTO_COMA {System.out.println("Start set on row X column Y");}
				| FUNC_GETSTART PARENT_IZ PARENT_DE PUNTO_COMA {System.out.println("Start is on row X column Y");}
				| FUNC_SETEXIT PARENT_IZ LIT_ENTERO COMA LIT_ENTERO PARENT_DE PUNTO_COMA {System.out.println("Exit set on row X column Y");}
				| FUNC_GETEXIT PARENT_IZ  PARENT_DE PUNTO_COMA {System.out.println("Exit is on row X column Y");}
				| FUNC_GETMECCA PARENT_IZ PARENT_DE PUNTO_COMA {System.out.println("Mecca is on row X column Y");}
				| FUNC_SETARROWS PARENT_IZ LIT_ENTERO PARENT_DE PUNTO_COMA {System.out.println("Mecca has now X arrows");}
				| FUNC_GETARROWS PARENT_IZ PARENT_DE PUNTO_COMA {System.out.println("Mecca has X arrows");}
				| FUNC_INCARROWS PARENT_IZ LIT_ENTERO PARENT_DE PUNTO_COMA {System.out.println("Arrows incremented in X, Mecca has now Y arrows");}
				| FUNC_DECARROWS PARENT_IZ LIT_ENTERO PARENT_DE PUNTO_COMA {System.out.println("Arrows decremented in X, Mecca has now Y arrows");}
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

//HECHO POR TEAM MECCA
