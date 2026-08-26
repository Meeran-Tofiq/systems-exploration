# Stuff to Add Later

Things I found interesting while working through **Crafting Interpreters** that I want to explore
when I have the time and the desire.

> **Attribution:** the indented/quoted blocks below are **Robert Nystrom's words**, quoted from
> [Crafting Interpreters](https://craftinginterpreters.com) — they're the passages that caught my
> attention. My own reactions and notes are the un-quoted text under each. Keeping the line visible
> so nothing here accidentally ends up in a blog post or a LinkedIn post as mine.

---

## Control Flow — `for-in` loops

> "This is a concession I made because of how the implementation is split across chapters. A for-in
> loop needs some sort of dynamic dispatch in the iterator protocol to handle different kinds of
> sequences, but we don't get that until after we're done with control flow. We could circle back
> and add for-in loops later, but I didn't think doing so would teach you anything super
> interesting."
> — Nystrom, *Crafting Interpreters*

**My note:** _(why I want to come back to this — write it)_

---

## Types — primitives without methods

> "Primitive Types don't have methods or properties. If I were trying to make Lox a real language
> for real users, I would fix that."
> — Nystrom, *Crafting Interpreters*

**My note:** _(what would I actually do here?)_

---

## Standard library

> "Later, when we start optimizing, we'll write some benchmarks and see how long it takes to execute
> code. That means we need to track time, so we'll define one built-in function, clock(), that
> returns the number of seconds since the program started.
>
> And . . . that's it. I know, right? It's embarrassing.
>
> If you wanted to turn Lox into an actual useful language, the very first thing you should do is
> flesh this out. String manipulation, trigonometric functions, file I/O, networking, heck, even
> reading input from the user would help."
> — Nystrom, *Crafting Interpreters*

**My note:** _(this feels like the most approachable "make it mine" extension)_

---

## Debugging tooling

> "Having said all that, for this interpreter, what we'll build is pretty bare bones. I'd love to
> talk about interactive debuggers, static analyzers, and other fun stuff, but there's only so much
> ink in the pen."
> — Nystrom, *Crafting Interpreters*

**My note:** _(interactive debuggers for my own language sounds genuinely fun — how does one even
start?)_
