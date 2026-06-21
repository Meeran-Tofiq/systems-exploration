# Compiler — Lox interpreter (jlox)

Building a tree-walking interpreter for the "Lox" language, following
**[Crafting Interpreters](https://craftinginterpreters.com)** Part II (the **jlox** = Java version).

## What this is
- **Crafting Interpreters** = a free online book (read in browser, no download). craftinginterpreters.com
- **Lox** = the small language I'm implementing.
- **jlox** = the Java implementation (Part II), a *tree-walking interpreter*. ← I'm doing this.
- **clox** = the C bytecode VM (Part III). Optional stretch later.

## Setup
1. Install a JDK 21+ (Temurin / OpenJDK). Verify:
   ```sh
   java -version
   javac -version
   ```
2. Editor: VS Code + the Java extension, or anything.
3. Read the book's intro (ch 1–3), then start **Chapter 4 "Scanning"** and type the code here.

## Plan (3 weeks, ~6–10 hrs/wk)
| Week | Chapters | Deliverable |
|------|----------|-------------|
| 1 | 1–4 (intro, scanning) | Lexer: tokenizes a `.lox` file |
| 2 | 5–7 (AST, parsing, evaluating) | Computes `(3 + 4) * 2` |
| 3 | 8–10 (statements, state, control flow, functions) | **MINIMUM DONE: runs recursive `fib(n)`** |

Stretch: ch 11–13 (resolving, classes), or jump to Part III "clox" (C bytecode VM).

## How to run (fill in once it exists)
```sh
# e.g. javac *.java && java Lox script.lox
```

## What I learned / would do next
- (write this at the end — recruiters/admissions read the README)
