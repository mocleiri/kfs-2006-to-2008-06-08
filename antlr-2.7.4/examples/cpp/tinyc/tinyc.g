/*
 * Make sure to run antlr.Tool on the lexer.g file first!
 */
options {
	mangleLiteralPrefix = "TK_";
	language="Cpp";
}

class TinyCParser extends Parser;
options {
	importVocab=TinyC;	// use vocab generated by lexer
}

program
	:	( declaration )* EOF
	;

declaration
	:	(variable) => variable
	|	function
	;

declarator
	:	id:ID
	|	STAR id2:ID
	;

variable
	:	type declarator SEMI
	;

function
	:	type id:ID LPAREN
		(formalParameter (COMMA formalParameter)*)?
		RPAREN
		block
	;

formalParameter
	:	type declarator
	;

type:	
	(
		TK_int
	|	TK_char
	|	id:ID
	)
	;

block
	:	LCURLY ( statement )* RCURLY
	;

statement
	:	(declaration) => declaration
	|	expr SEMI
	|	TK_if LPAREN expr RPAREN statement
		( TK_else statement )?
	|	TK_while LPAREN expr RPAREN statement
	|	block
	;

expr:	assignExpr
	;

assignExpr
	:	aexpr (ASSIGN assignExpr)?
	;

aexpr
	:	mexpr (PLUS mexpr)*
	;

mexpr
	:	atom (STAR atom)*
	;

atom:	ID
	|	INT
	|	CHAR_LITERAL
	|	STRING_LITERAL
	;

