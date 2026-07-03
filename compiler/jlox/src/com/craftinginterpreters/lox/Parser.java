package com.craftinginterpreters.lox;

import static com.craftinginterpreters.lox.TokenType.*;

import com.craftinginterpreters.lox.Expr.*;
import java.util.List;

public class Parser {
  private final List<Token> tokens;
  private int current = 0;

  Parser(List<Token> tokens) {
    this.tokens = tokens;
  }

  public Expr parse() {
    return this.parseExpr();
  }

  private boolean isAtEnd() {
    return peek().type == EOF;
  }

  private Token peek() {
    return tokens.get(current);
  }

  private Token previous() {
    return tokens.get(current - 1);
  }

  private Token advance() {
    if (!isAtEnd()) current++;
    return previous();
  }

  private boolean check(TokenType type) {
    if (isAtEnd()) return false;
    return peek().type == type;
  }

  private boolean match(TokenType... types) {
    for (TokenType type : types) {
      if (check(type)) {
        advance();
        return true;
      }
    }

    return false;
  }

  private Expr parseExpr() {
    return parseEquality();
  }

  private Expr parseEquality() {
    Token operator;
    Expr expr = parseComparison();

    while (match(EQUAL_EQUAL, BANG_EQUAL)) {
      operator = previous();
      expr = new Binary(expr, operator, parseComparison());
    }

    return expr;
  }

  private Expr parseComparison() {
    Token operator;
    Expr expr = parseTerm();

    while (match(GREATER, GREATER_EQUAL, LESS, LESS_EQUAL)) {
      operator = previous();
      expr = new Binary(expr, operator, parseTerm());
    }

    return expr;
  }

  private Expr parseTerm() {
    Token operator;
    Expr expr = parseFactor();

    while (match(PLUS, MINUS)) {
      operator = previous();
      expr = new Binary(expr, operator, parseFactor());
    }

    return expr;
  }

  private Expr parseFactor() {
    Token operator;
    Expr expr = parseUnary();

    while (match(STAR, SLASH)) {
      operator = previous();
      expr = new Binary(expr, operator, parseUnary());
    }

    return expr;
  }

  private Expr parseUnary() {
    if (match(BANG, MINUS)) {
      Token operator = previous();
      Expr expr = parseUnary();
      return new Unary(operator, expr);
    }

    return parsePrimary();
  }

  private Expr parsePrimary() {
    if (match(FALSE)) return new Literal(false);
    if (match(TRUE)) return new Literal(true);
    if (match(NIL)) return new Literal(null);

    if (match(NUMBER, STRING)) {
      return new Literal(previous().literal);
    }

    if (match(LEFT_PAREN)) {
      Expr expr = parseExpr();
      // consume(RIGHT_PAREN, "Expect ')' after expression.");
      return new Grouping(expr);
    }
    return null;
  }
}
