# Compiler — Lox (jlox interpreter → clox compiler)

Following **[Crafting Interpreters](https://craftinginterpreters.com)** to test whether I love
compilers. **Two phases**, because interpreters and compilers share ~80% (the front-end), and the
distinctly-compiler part is the back-end:

- **Phase 1 — jlox** (Part II, Java): a tree-walking interpreter. Tests *"do I love language
  implementation?"* (lexer/parser/AST — the shared front-end). ← the interest **filter**.
- **Phase 2 — clox** (Part III, C): a bytecode **compiler** + VM. Tests *"is it **compilers**
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

Don't need to finish clox; even a week in, I'll feel whether codegen/back-end excites me.
This jlox→clox arc mirrors TU Delft's **CS4200** (front-end → back-end/codegen).

## How to run (fill in once it exists)
```sh
# e.g. javac *.java && java Lox script.lox
```

## What I learned / would do next
- (write this at the end — recruiters/admissions read the README)
