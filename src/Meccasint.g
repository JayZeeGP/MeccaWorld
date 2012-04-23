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

instructionConf: FUNC_LEER PARENT_IZ (parametros)+ PARENT_DE PUNTO_COMA
               ;

instructionAd: FUNC_LEER PARENT_IZ (parametros)* PARENT_DE PUNTO_COMA
               ;
               
parametros: valorparametro (parametros_prima)*;

parametros_prima: COMA valorparametro;

nombrefuncion: IDENT;

valorparametro: IDENT;

//HECHO POR TEAM MECCA
