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

// Nuevos atributos de la clase Anasint
{
  // Atributo de Anasint para contar las instrucciones reconocidas
  int contador = 0;
}




//HECHO POR TEAM MECCA

mecca: configuration adventure;

configuration: BEGIN_CONF expr END_CONF;

adventure: BEGIN_ADV expr END_ADV;

expr: nombrefuncion PARENT_IZ (parametros)* PARENT_DE PUNTO_COMA (expr)*;

parametros: valorparametro (parametros_prima)*;

parametros_prima: COMA valorparametro;

nombrefuncion: LIT_CADENA;

valorparametro: LIT_CADENA;

//HECHO POR TEAM MECCA
