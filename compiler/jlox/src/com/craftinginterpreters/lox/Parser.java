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

  private Token peek() {
    if (tokens.size() <= current) return null;
    return tokens.get(current);
  }

  private Token advance() {
    if (tokens.size() <= current) return null;
    return tokens.get(current++);
  }

  private Expr parseExpr() {
    return parseEquality();
  }

  private Expr parseEquality() {
    Token operator;
    Expr expr = parseComparison();

    Token token = peek();
    if (token == null) return expr;

    while (token != null && (token.type == EQUAL_EQUAL || token.type == BANG_EQUAL)) {
      token = advance();
      operator = new Token(token);
      expr = new Binary(expr, operator, parseComparison());

      token = peek();
    }

    return expr;
  }

  private Expr parseComparison() {
    Token operator;
    Expr expr = parseTerm();

    Token token = peek();
    if (token == null) return expr;

    while (token != null
        && (token.type == GREATER
            || token.type == GREATER_EQUAL
            || token.type == LESS
            || token.type == LESS_EQUAL)) {
      token = advance();
      operator = new Token(token);
      expr = new Binary(expr, operator, parseTerm());

      token = peek();
    }

    return expr;
  }

  private Expr parseTerm() {
    Token operator;
    Expr expr = parseFactor();

    Token token = peek();
    if (token == null) return expr;

    while (token != null && (token.type == PLUS || token.type == MINUS)) {
      token = advance();
      operator = new Token(token);
      expr = new Binary(expr, operator, parseFactor());

      token = peek();
    }

    return expr;
  }

  private Expr parseFactor() {
    Token operator;
    Expr expr = parseUnary();

    Token token = peek();
    if (token == null) return expr;

    while (token != null && (token.type == STAR || token.type == SLASH)) {
      token = advance();
      operator = new Token(token);
      expr = new Binary(expr, operator, parseUnary());

      token = peek();
    }

    return expr;
  }

  private Expr parseUnary() {
    Token operator;
    Token token = peek();

    if (token.type != BANG || token.type != MINUS) return parsePrimary();

    operator = new Token(token);
    advance();
    return new Unary(operator, parseUnary());
  }

  private Expr parsePrimary() {
    Token token = peek();
    if (token.type == LEFT_PAREN) return parseGrouping();
    token = advance();
    return new Literal(token.literal);
  }

  private Expr parseGrouping() {
    Token token = advance();
    Expr expr = null;

    if (token.type == LEFT_PAREN) {
      expr = parseExpr();
    }

    if (peek().type == RIGHT_PAREN) expr = new Grouping(expr);
    advance();

    return expr;
  }
}
