# Systems Exploration Journal

Two jobs, in this order:

1. **Record what happened** — so I can write about it later without re-reading diffs.
2. **Record how it felt** — first instinct, honest. This is how I pick my subfield.

The old version only did job 2. That's why nothing was ever postable.

> **The one hard rule: never leave `Built` and `The snag` empty.** If a session produces nothing
> else, it produces those two. Ten minutes, while it's still warm. Everything else is optional.
>
> **One entry per sitting**, not per week. Three days combined into one entry loses three snags.
>
> Missed sessions are fine — entries are backfillable from `git log`. A gap doesn't invalidate this.

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

**Built:** Token types, basic scanning + error handling, the `Expr` class + AST generator, a printer
to test the generated tree. (Commits `5045986`..`21df857`.)

**The snag:** — *nothing to record. These were the intro chapters and I mostly typed along with the
book, because I was too clueless about all of it to build anything myself yet. That's what those
chapters are for. Not backfilling this retroactively.*

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

**Built:** Hand-rolled the whole expression parser myself *before* reading the book's version. AST
printing for expressions. Fixed a `parseUnary` check that bypassed `!` and `-` (`7d72cb0`). Then
refactored to match the book's design (`17a353e`). Commits `f192ec7`..`9fc6a97`.

**The snag:** My `peek()` returned `null` once the token list ran out. Consequence: every single
rule needed `if (token == null) return expr;` at the top, and every while-loop needed a
`token != null` in its condition. I wrote that same guard four times and never questioned it — it
felt like just what parsing was.

**The click:** The book never has a null because **the scanner always appends an EOF token.** One
fake element at the end of the data, and `isAtEnd()` is just `peek().type == EOF`. Every null check
in every rule disappears. The general lesson has nothing to do with compilers: *a sentinel at the
end of your data removes a special case from every consumer of it.*

**Decision I made:** Built it my own way first, then read the book and refactored (`17a353e`,
51+/61−). Worth recording what survived and what didn't:
- **Survived — the architecture.** I'd independently arrived at the precedence cascade
  (equality → comparison → term → factor → unary → primary) with left-associative while-loops.
  That's the actual insight of recursive descent, and I got it without being told.
- **Didn't survive — the vocabulary.** No `match()` / `check()` / `previous()` / `isAtEnd()`, so
  each binary rule was ~11 lines of peek/null-check/while/advance/copy/re-peek. The book's are ~5.
  Identical algorithm, a quarter less ceremony.
- **Didn't survive — `new Token(token)`.** I was defensively copying the operator token every time.
  The book just aliases it. I was copying because I didn't trust my own mutation model.
- **Didn't survive — `parsePrimary`.** Mine silently accepted a missing `)` and didn't handle
  `true` / `false` / `nil` at all. Silently accepting malformed input is a real bug class, not a
  style difference.

**Question I couldn't answer:** Why did I reach for `null` as the end-of-input signal so
automatically? Is the sentinel trick something I'd now spot in non-parser code, or only here
because someone showed me?

### How it felt

**Pull:** This time I built everything by myself, then consulted the book. It
was very fun.

**Frustration type:** Once again, it was fun-hard.

**Curiosity tail:** Again I did not want to stop, but I had to. It's incredible.
There were some extra challenges at the end, I will definitely be returning to
these later in the future.

**One line:** Very, very fun.

### Output

**Post seed:** **← POST #1.** *"My parser worked. Then I added one fake token to the end of the list
and deleted a null check from every function I'd written."* Format: **the click**. The sentinel
lesson is transferable well beyond compilers, which is what makes it postable rather than just a
diary entry. Open on the four repeated null guards, land on EOF-as-sentinel.

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

Scan the `Post seed:` lines above on posting day. Move them here once drafted, so I can see the
history and rotate formats (snag / click / decision / artifact / question — don't run the same
format twice in a row).

| Date | Format | From | Where | Link |
|------|--------|------|-------|------|
|      |        |      |       |      |

**Cadence: biweekly-ish. Skipping a cycle is free.**
