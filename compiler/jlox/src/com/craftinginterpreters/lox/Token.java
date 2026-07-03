package com.craftinginterpreters.lox;

public class Token {
  final TokenType type;
  final String lexeme;
  final Object literal;
  final int line;

  Token(TokenType type, String lexeme, Object literal, int line) {
    this.type = type;
    this.lexeme = lexeme;
    this.literal = literal;
    this.line = line;
  }

  Token(Token token) {
    this.type = token.type;
    this.lexeme = token.lexeme;
    this.literal = token.literal;
    this.line = token.line;
  }

  public String toString() {
    return type + " " + lexeme + " " + literal;
  }
}
