# Compiler — Lox (jlox interpreter → clox compiler)

Following **[Crafting Interpreters](https://craftinginterpreters.com)** to test whether I love
compilers. **Two phases**, because interpreters and compilers share ~80% (the front-end), and the
distinctly-compiler part is the back-end:

- **Phase 1 — jlox** (Part II, Java): a tree-walking interpreter. Answers *"do I love language
  implementation?"* (lexer/parser/AST — the shared front-end). ← the interest **filter**.
- **Phase 2 — clox** (Part III, C): a bytecode **compiler** + VM. Answers *"is it **compilers**
  specifically — codegen/back-end — that I love?"* ← the **confirmation**, do this if jlox grips me.

## What this is
- **Crafting Interpreters** = a free online book (read in browser, no download). craftinginterpreters.com
- **Lox** = the small language I implement. **jlox** = Java interpreter. **clox** = C bytecode compiler+VM.

## Setup
- **Phase 1:** JDK 21+ (Temurin/OpenJDK) → `java -version`, `javac -version`. VS Code + Java ext.
- **Phase 2:** a C compiler (`gcc`/`clang`).
- Read intro (ch 1–3), then start **Chapter 4 "Scanning"** and type the code here.

## Plan (~5 weeks, ~6–10 hrs/wk)
**Phase 1 — jlox (the filter):**
| Week | Chapters | Deliverable |
|------|----------|-------------|
| 1 | 1–4 (intro, scanning) | Lexer: tokenizes a `.lox` file |
| 2 | 5–7 (AST, parsing, evaluating) | Computes `(3 + 4) * 2` |
| 3 | 8–10 (statements, state, control flow, functions) | **MINIMUM DONE: runs recursive `fib(n)`** |

**Phase 2 — clox (the compiler confirmation — only if jlox gripped me):**
| Week | Chapters | Deliverable |
|------|----------|-------------|
| 4 | 14–17 (bytecode, the VM, compiling expressions) | VM executes compiled bytecode for an expression |
| 5 | 18–21+ as far as interest carries | Real codegen — judge the *compiler* love |

Don't need to finish clox; even a week in, I'll feel whether codegen/back-end excites me. The
jlox→clox arc is the same front-end → back-end progression a compilers course follows.

## Layout
Standard Java `src/ → bin/` layout, so editors/LSPs (jdtls, etc.) and a future
build tool resolve the `com.craftinginterpreters.lox` package without config:
```
jlox/
├── .classpath   ← source root = src/, output = bin/
├── .project     ← marks jlox/ as the project root
├── hello.lox
└── src/com/craftinginterpreters/lox/Lox.java
```

## How to run
```sh
cd jlox
javac -d bin src/com/craftinginterpreters/lox/*.java   # compile src -> bin
java -cp bin com.craftinginterpreters.lox.Lox hello.lox # run a script
java -cp bin com.craftinginterpreters.lox.Lox           # REPL (Ctrl-D to exit)
```
Right now `run()` just echoes input — replace it with a real Scanner as you work
through Chapter 4 (add `Token.java`, `TokenType.java`, `Scanner.java` in the same
`src/com/craftinginterpreters/lox/` folder).

## What I learned so far

**Write the parser yourself first, then read the book.** For the expression parser I did it in that
order, and the comparison taught me far more than following along would have. The structure I
arrived at independently was right — the precedence cascade (`equality → comparison → term → factor
→ unary → primary`, each level looping to stay left-associative) really is the core idea of
recursive descent, and it's derivable if you sit with the grammar long enough.

**Where mine was wrong was more interesting than where it was right.** My `peek()` returned `null`
once the token list ran out. That one decision forced `if (token == null) return expr;` at the top of
every rule and a `token != null` in every loop condition — a guard I wrote four times without ever
questioning it, because I assumed defensive code was just what parsing looked like.

The book never has a null, because the scanner appends an **EOF token** to the end of the list.
There's always a token, so "am I at the end?" becomes an ordinary comparison rather than a special
case, and all four guards disappear. The general form of that has nothing to do with parsers:
**a sentinel at the end of your data removes a special case from every consumer of it.**

Two smaller things the comparison surfaced: I was defensively copying each operator token with
`new Token(token)` because I didn't trust my own mutation model, and my `parsePrimary` silently
accepted a missing `)` instead of reporting it — accepting malformed input quietly is a bug class,
not a style preference.

## What I'd do next
- Finish jlox to **MINIMUM DONE**: recursive `fib(n)`.
- Then clox (Part III) — the actual compiler, in C — to find out whether it's *codegen* I like or
  just language implementation generally.
- Revisit the optimization ideas I've been collecting in
  [`Stuff to Research Later.md`](Stuff%20to%20Research%20Later.md).
