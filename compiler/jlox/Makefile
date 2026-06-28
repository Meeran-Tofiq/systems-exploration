# jlox build recipes. Run these from the jlox/ folder.
#
#   make          -> compile (same as `make build`)
#   make run      -> compile, then run hello.lox
#   make run FILE=foo.lox  -> compile, then run a different script
#   make repl     -> compile, then start the interactive prompt (Ctrl-D to quit)
#   make generate-ast -> compile, then regenerate the AST classes in src/.../lox
#   make clean    -> delete compiled output (bin/)
#
# How it works: `javac` compiles every .java under src/ into bin/, then `java`
# runs the Lox class from bin/. That's the same two commands you'd type by hand.

SRC := $(shell find src -name '*.java')   # every .java file under src/
MAIN := com.craftinginterpreters.lox.Lox  # the class with main()
TOOL := com.craftinginterpreters.tool.GenerateAst  # AST source generator
ASTDIR := src/com/craftinginterpreters/lox  # where generated AST classes land
FILE ?= hello.lox                         # default script for `make run`

# Default target (just `make`): compile.
build:
	javac -d bin $(SRC)

run: build
	java -cp bin $(MAIN) $(FILE)

repl: build
	java -cp bin $(MAIN)

# Run the AST generator. It writes Expr.java (etc.) into the lox package, so
# the class must be named in full and the output dir passed as its argument.
generate-ast: build
	java -cp bin $(TOOL) $(ASTDIR)

clean:
	rm -rf bin

# Tells make these are commands, not real files named build/run/etc.
.PHONY: build run repl generate-ast clean
