# Stuff to Add Later

This is just the stuff that I found interesting, that I thought I would add
later.

## Control Flow

This is a concession I made because of how the implementation is split across
chapters. A for-in loop needs some sort of dynamic dispatch in the iterator
protocol to handle different kinds of sequences, but we don’t get that until
after we’re done with control flow. We could circle back and add for-in loops
later, but I didn’t think doing so would teach you anything super interesting.

## Types

"Primitive Types don’t have methods or properties. If I were trying to make Lox
a real language for real users, I would fix that."

## Standards Library

Later, when we start optimizing, we’ll write some benchmarks and see how long
it takes to execute code. That means we need to track time, so we’ll define one
built-in function, clock(), that returns the number of seconds since the
program started.

And . . . that’s it. I know, right? It’s embarrassing.

If you wanted to turn Lox into an actual useful language, the very first thing
you should do is flesh this out. String manipulation, trigonometric functions,
file I/O, networking, heck, even reading input from the user would help.

## Debugging

Having said all that, for this interpreter, what we’ll build is pretty bare
bones. I’d love to talk about interactive debuggers, static analyzers, and
other fun stuff, but there’s only so much ink in the pen.
