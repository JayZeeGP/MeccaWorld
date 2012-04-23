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
				| FUNC_GETHOLE PARENT_IZ PARENT_DE PUNTO_COMA {System.out.println("The number of holes is:");}
				
               ;

instructionAd: FUNC_LEER PARENT_IZ (parametros)* PARENT_DE PUNTO_COMA 
               ;
               
parametros: valorparametro (parametros_prima)*;

parametros_prima: COMA valorparametro;

nombrefuncion: IDENT;

valorparametro: IDENT;

//HECHO POR TEAM MECCA
