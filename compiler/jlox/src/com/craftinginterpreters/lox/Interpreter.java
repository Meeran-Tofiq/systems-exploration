package com.craftinginterpreters.lox;

import static com.craftinginterpreters.lox.TokenType.*;

import com.craftinginterpreters.lox.Expr.Binary;
import com.craftinginterpreters.lox.Expr.Grouping;
import com.craftinginterpreters.lox.Expr.Literal;
import com.craftinginterpreters.lox.Expr.Unary;

public class Interpreter implements Expr.Visitor<Object> {
  static class InterpreterError extends RuntimeException {
    Token token;

    InterpreterError(String m, Token t) {
      super(m);
      this.token = t;
    }
  }

  @Override
  public Object visitBinaryExpr(Binary expr) {
    // EQUAL_EQUAL, BANG_EQUAL
    // GREATER, GREATER_EQUAL, LESS, LESS_EQUAL
    Object left = expr.left.accept(this);
    Object right = expr.right.accept(this);

    switch (expr.operator.type) {
      case PLUS:
        if (isLeftRightMatch(left, right, String.class)) {
          return (String) left + (String) right;
        } else if (isLeftRightMatch(left, right, Double.class)) {
          return (Double) left + (Double) right;
        }
        break;
      case MINUS:
        if (isLeftRightMatch(left, right, Double.class)) return (Double) left - (Double) right;
        break;
      case STAR:
        if (isLeftRightMatch(left, right, Double.class)) return (Double) left * (Double) right;
        break;
      case SLASH:
        if (isLeftRightMatch(left, right, Double.class)) return (Double) left / (Double) right;
        break;
      case EQUAL_EQUAL:
        if (isLeftRightMatch(left, right, String.class))
          return ((String) left).equals((String) right);
        if (isLeftRightMatch(left, right, Double.class))
          return ((Double) left).equals((Double) right);
        if (left == null && right != null) return false;
        return left == right;
      case BANG_EQUAL:
        if (isLeftRightMatch(left, right, String.class))
          return !((String) left).equals((String) right);
        if (isLeftRightMatch(left, right, Double.class))
          return !((Double) left).equals((Double) right);
        if (left == null && right != null) return true;
        return left != right;
      case GREATER:
        if (isLeftRightMatch(left, right, Double.class)) return (Double) left > (Double) right;
        break;
      case GREATER_EQUAL:
        if (isLeftRightMatch(left, right, Double.class)) return (Double) left >= (Double) right;
        break;
      case LESS:
        if (isLeftRightMatch(left, right, Double.class)) return (Double) left < (Double) right;
        break;
      case LESS_EQUAL:
        if (isLeftRightMatch(left, right, Double.class)) return (Double) left <= (Double) right;
        break;
      default:
        // Cannot be reached because the parser would not generate
        // a binary expr with any other token type. But java forces
        // us to handle the cases of all the token types.
        return null;
    }

    throw new InterpreterError(
        "Trying to perform an impossible operation: "
            + expr.left.toString()
            + " and "
            + expr.right.toString()
            + " can't be performed. ",
        expr.operator);
  }

  @Override
  public Object visitGroupingExpr(Grouping expr) {
    Object value = expr.expression.accept(this);
    return value;
  }

  @Override
  public Object visitLiteralExpr(Literal expr) {
    // false, true, nil
    // number, string
    Object value = expr.value;

    if (value == null) return null;
    if (isInstanceOf(value, Double.class)) return (Double) value;
    if (isInstanceOf(value, String.class)) return (String) value;
    if (isInstanceOf(value, Boolean.class)) return (Boolean) value;

    throw new InterpreterError(
        "This token is none of the accepted types: Boolean, String, Decimal", null);
  }

  @Override
  public Object visitUnaryExpr(Unary expr) {
    // BANG, MINUS
    // expr.accept(Visitor<R> visitor)
    Object value = expr.right.accept(this);

    if (expr.operator.type == BANG) {
      if (isInstanceOf(value, Boolean.class)) {
        return !(Boolean) value;
      } else if (value == null) return true;
      else return false;
    } else if (expr.operator.type == MINUS) {
      if (isInstanceOf(value, Double.class)) {
        return -(Double) value;
      }
    }

    throw new InterpreterError(
        "Trying to perform an impossible operation: " + expr.operator + " on " + value.getClass(),
        expr.operator);
  }

  boolean isInstanceOf(Object obj, Class<?>... classes) {
    for (Class<?> clazz : classes) {
      if (clazz.isInstance(obj)) {
        return true;
      }
    }
    return false;
  }

  boolean isLeftRightMatch(Object left, Object right, Class<?> clazz) {
    if (clazz.isInstance(left) && clazz.isInstance(right)) return true;
    return false;
  }
}
