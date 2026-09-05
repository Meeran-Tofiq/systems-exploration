# Systems Exploration Journal

Two jobs, in this order:

1. **Record what happened** — so I can write about it later without re-reading
   diffs.
2. **Record how it felt** — first instinct, honest. This is how I pick my
   subfield.

The old version only did job 2. That's why nothing was ever postable.

> **The one hard rule: never leave `Built` and `The snag` empty.** If a session
> produces nothing else, it produces those two. Ten minutes, while it's still
> warm. Everything else is optional.
>
> **One entry per sitting**, not per week. Three days combined into one entry
> loses three snags.
>
> Missed sessions are fine — entries are backfillable from `git log`. A gap
> doesn't invalidate this.

---

<!-- ============ COPY THIS BLOCK FOR EACH SESSION ============

## [Project] — Session N — [date] — [hours]

### What happened

**Built:** (2–4 bullets, plain language, link commits where useful)
-

**The snag:** (the one thing that didn't work, and *why my mental model was wrong*.
This is the single most postable field. Be specific — the actual bug, not "had some issues".)

**The click:** (the concept that finally landed — explained in my own words, no jargon,
as if to someone who's never seen a compiler. If I can't do that, it hasn't clicked yet.)

**Decision I made:** (a design choice + what I rejected and why. Even small ones count.)

**Question I couldn't answer:** (something I still don't understand. Genuinely the best
post format — people answer questions.)

### How it felt

**Pull** (did I want to keep going? did time disappear?): _/5

**Frustration type** (fun-hard = a puzzle I wanted to crack / drain-hard = forced myself):

**Curiosity tail** (afterward: want to go deeper, or relieved it ended?):

**One line — how did it *feel*:**

### Output

**Post seed:** (one sentence: which field above is postable, and roughly the angle.
On posting day I scan for these lines instead of staring at a blank page.)

============================================================ -->

## Compiler — Session 1 — 26/06/2026–28/06/2026 — 9 hours (combined)

### What happened

**Built:** Token types, basic scanning + error handling, the `Expr` class + AST
generator, a printer to test the generated tree. (Commits `5045986`..`21df857`.)

**The snag:** — _nothing to record. These were the intro chapters and I mostly
typed along with the book, because I was too clueless about all of it to build
anything myself yet. That's what those chapters are for. Not backfilling this
retroactively._

**The click:** —

**Decision I made:** —

**Question I couldn't answer:** —

### How it felt

**Pull:** I truly felt so excited during the entire process. Every piece of the
puzzle would click, and it would motivate me to keep going.

**Frustration type:** It was fun-hard. I felt compelled to understand as deeply
as I could, just because of how much fun I was having.

**Curiosity tail:** I had to log off because it was midnight and I had an early
day the next day(s). Otherwise I would not have stopped.

**One line:** Very, very fun.

### Output

**Post seed:** — nothing here; transcription chapters.

---

## Compiler — Session 2 — 03/07/2026 — 9.5 hours

### What happened

**Built:** Hand-rolled the whole expression parser myself _before_ reading the
book's version. AST printing for expressions. Fixed a `parseUnary` check that
bypassed `!` and `-` (`7d72cb0`). Then refactored to match the book's design
(`17a353e`). Commits `f192ec7`..`9fc6a97`.

**The snag:** My `peek()` returned `null` once the token list ran out.
Consequence: every single rule needed `if (token == null) return expr;` at the
top, and every while-loop needed a `token != null` in its condition. I wrote
that same guard four times and never questioned it — it felt like just what
parsing was.

**The click:** The book never has a null because **the scanner always appends an
EOF token.** One fake element at the end of the data, and `isAtEnd()` is just
`peek().type == EOF`. Every null check in every rule disappears. The general
lesson has nothing to do with compilers: _a sentinel at the end of your data
removes a special case from every consumer of it._

**Decision I made:** Built it my own way first, then read the book and
refactored (`17a353e`, 51+/61−). Worth recording what survived and what didn't:

- **Survived — the architecture.** I'd independently arrived at the precedence
  cascade (equality → comparison → term → factor → unary → primary) with
  left-associative while-loops. That's the actual insight of recursive descent,
  and I got it without being told.
- **Didn't survive — the vocabulary.** No `match()` / `check()` / `previous()` /
  `isAtEnd()`, so each binary rule was ~11 lines of
  peek/null-check/while/advance/copy/re-peek. The book's are ~5. Identical
  algorithm, a quarter less ceremony.
- **Didn't survive — `new Token(token)`.** I was defensively copying the
  operator token every time. The book just aliases it. I was copying because I
  didn't trust my own mutation model.
- **Didn't survive — `parsePrimary`.** Mine silently accepted a missing `)` and
  didn't handle `true` / `false` / `nil` at all. Silently accepting malformed
  input is a real bug class, not a style difference.

**Question I couldn't answer:** Why did I reach for `null` as the end-of-input
signal so automatically? Is the sentinel trick something I'd now spot in
non-parser code, or only here because someone showed me?

### How it felt

**Pull:** This time I built everything by myself, then consulted the book. It
was very fun.

**Frustration type:** Once again, it was fun-hard.

**Curiosity tail:** Again I did not want to stop, but I had to. It's incredible.
There were some extra challenges at the end, I will definitely be returning to
these later in the future.

**One line:** Very, very fun.

### Output

**Post seed:** **← POST #1.** _"My parser worked. Then I added one fake token to
the end of the list and deleted a null check from every function I'd written."_
Format: **the click**. The sentinel lesson is transferable well beyond
compilers, which is what makes it postable rather than just a diary entry. Open
on the four repeated null guards, land on EOF-as-sentinel.

---

## Compiler — Session 3 — 04/09/2026-05/09/2026 — 5.5 hours

### What happened

**Built:**

- This session was all about the interpreter
- It handles all literal, unary, binary, and grouping expressions
- Wrote my own version first (`0aba8de`), then rewrote it to match the book's
  error handling (`0a4fec8`), then did two of the chapter challenges (`cccc4a5`)

**The snag:** It was incredibly difficult to make the error handling. I had it
in my mind to make each function throw different errors. Then I thought they are
all runtime errors, but had no idea how to generalize the way to throw an
exception so that the already massive switch statements, especially the one in
binary expression handling, to become even bigger. I also wanted to make the
code showcase the token and line where the issue happened, not realizing that
the only token i had access to at interpretation time was the operator. This
meant that I could not showcase the exact token, so I instead opted for the line
that the error happened in.

What I had done initially was write a simple `return new Object()`. I had done
this not because I was confused about what to return, but rather didn't know how
to tackle errors and error handling. I had three primary design decisions that I
could not wrap my head around:

- how do I point at the operand's token?
- where do the type checks actually belong?
- how do I throw without a throw in every branch?

**The click:** First, the operand's token. I really wanted to pass the operand's
token to the error handler, which would give it the specific variable/value that
was wrong or didn't match. But this was restricted to me due to the
implementation of the earlier parser and the resulting tree structure. There was
no way for me to get the operand's token. The book just uses the operator's
token, which includes the line that threw the error. This was better than my
lack of any implementation at all.

Then, for the checks. Where do I put them, and how do I limit duplication? I had
an implementation that was super repetitive and had the same checks of the
object's class everywhere, especially inside the binary visitor. After reading
the book's implementation, I realized two things: the checks inside the literal
were completely useless, and the majority were checking that both operands were
numbers. First, the literal visitor. It had branches to check whether the
expression's value was a boolean, string, or number, after which it would return
the value type cast to whatever the value was:
`if (value instanceof String) return (String) value;`. But the thing is, that
same check was _already happening_ inside the unary and binary visitors, because
that's where it actually matters. So the literal's copy was the redundant one:
the type was being checked both IN the literal visitor, AND the unary/binary
one. So I removed it, and it became one line. Then second, most of the
operations inside the binary visitor, checked that both sides of the operator
were numbers. Only additions and the two equalities required extra checks.
Extract that, and half the bloat of that function was removed.

As for the throwing in every branch, it was sort of solved by the extracted
checks in binary. It was now a lot easier to throw an exception in a few places,
that also covered all the cases. The book had a very elegant way of handling it,
which terminated a file run, but not the REPL when actually interpreting.
Combined with the operator's token being passed in, all of a sudden my
interpreter had a robust error handling framework.

**Decision I made:**

- **A string on either side of `+` concatenates** (`1 + "hello"` → `1hello`).
  Rejected: the book's rule, which requires two numbers or two strings and
  errors otherwise. No deeper reason than that it was one of the chapter's
  challenges and I wanted to do it.
- **Dividing by zero is a runtime error.** Rejected: the book's behaviour, which
  lets IEEE floats return `Infinity`. I did this because I am more personally
  used to errors being thrown when trying to divide a number by zero, rather
  than passing infinity. I also think it's very rare someone would want their
  program to continue working when one of their variables is now storing
  infinity.

**Question I couldn't answer:** — nothing this time. The three things I was
stuck on all got answered by the end of the session.

### How it felt

**Pull:** 4/5

**Frustration type:** fun-hard

**Curiosity tail:** wanted to go deeper

**One line:** I feel a little overwhelmed, but in a good
way. I knew making my own language would come with a lot of language design
decisions, but I did not understand just how much that would be. I initially
felt a bit upset that there wasn't going to be static type checking in the Lox
language. Now, I know how much it would've taken to accomodate the checks and
error handling for static typing. I'm not even at classes, functions, and
variables yet, lol.

### Output

**Post seed:** **← POST #2.** *"I had the same type check in three places, and
none of them had to agree with each other."* Format: **the snag**. Open on the
three questions I couldn't order (which token do I point at / where do the
checks go / how do I throw without a throw in every branch), land on the literal
visitor: I deleted its type checks not just because they were duplicated, but
because the operator catches the same problem and can say *more* about it. "Check
where you know the most" transfers well past interpreters, which is what makes
it postable. Session 2 was **the click**, so don't run that format again here.

*Second angle, if this one doesn't land:* the static typing line from **One
line** — I was annoyed Lox is dynamically typed, then wrote the runtime error
handling by hand and understood what static checking actually buys. Format:
**the decision**.

---

# Final comparison (after all three)

| Project  | Pull /5 | Fun-hard /5 | Curiosity tail /5 | Total /15 |
| -------- | ------- | ----------- | ----------------- | --------- |
| Compiler |         |             |                   |           |
| Embedded |         |             |                   |           |
| OS       |         |             |                   |           |

**Winner:**

**Why:**

**→ The winner is the one I go deep on next.**

---

## Post queue

Scan the `Post seed:` lines above on posting day. Move them here once drafted,
so I can see the history and rotate formats (snag / click / decision / artifact
/ question — don't run the same format twice in a row).

| Date | Format | From | Where | Link |
| ---- | ------ | ---- | ----- | ---- |
|      |        |      |       |      |

**Cadence: biweekly-ish. Skipping a cycle is free.**
