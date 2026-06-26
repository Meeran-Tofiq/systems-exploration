package com.craftinginterpreters.lox;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.nio.charset.Charset;
import java.nio.file.Files;
import java.nio.file.Paths;

// jlox entry point. This is the harness from Crafting Interpreters, Chapter 4.
// It compiles and runs as-is. As you read Chapter 4, replace the TODO in run()
// with a real Scanner: add Token.java, TokenType.java, Scanner.java alongside
// this file (same package) and tokenize `source` here.
public class Lox {
  public static void main(String[] args) throws IOException {
    if (args.length > 1) {
      System.out.println("Usage: jlox [script]");
      System.exit(64);
    } else if (args.length == 1) {
      runFile(args[0]);
    } else {
      runPrompt();
    }
  }

  private static void runFile(String path) throws IOException {
    byte[] bytes = Files.readAllBytes(Paths.get(path));
    run(new String(bytes, Charset.defaultCharset()));
  }

  private static void runPrompt() throws IOException {
    InputStreamReader input = new InputStreamReader(System.in);
    BufferedReader reader = new BufferedReader(input);

    for (;;) {
      System.out.print("> ");
      String line = reader.readLine();
      if (line == null) break; // Ctrl-D to exit
      run(line);
    }
  }

  private static void run(String source) {
    // TODO (Chapter 4 — Scanning): tokenize and print the tokens.
    //   Scanner scanner = new Scanner(source);
    //   for (Token token : scanner.scanTokens()) System.out.println(token);
    // For now, echo the input so you can confirm the project builds and runs.
    System.out.println(source);
  }
}
