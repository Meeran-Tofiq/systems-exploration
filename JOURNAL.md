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

**Built:** _(BACKFILL from git log — commits `5045986`..`21df857`: token types, basic scanning,
error handling, the `Expr` class + AST generator, a printer to test the generated tree.)_

**The snag:** _(backfill if I can remember one)_

**The click:** _(backfill)_

**Decision I made:** _(backfill)_

**Question I couldn't answer:** _(backfill)_

### How it felt

**Pull:** I truly felt so excited during the entire process. Every piece of the
puzzle would click, and it would motivate me to keep going.

**Frustration type:** It was fun-hard. I felt compelled to understand as deeply
as I could, just because of how much fun I was having.

**Curiosity tail:** I had to log off because it was midnight and I had an early
day the next day(s). Otherwise I would not have stopped.

**One line:** Very, very fun.

### Output

**Post seed:** _(backfill)_

---

## Compiler — Session 2 — 03/07/2026 — 9.5 hours

### What happened

**Built:** _(BACKFILL from git log — commits `f192ec7`..`9fc6a97`: hand-rolled the parser before
reading the book's version, AST printing for expressions, fixed a `parseUnary` check that bypassed
`!` and `-`, then refactored to match the book's design.)_

**The snag:** _(BACKFILL — commit `7d72cb0`, "fix check in parse unary that bypassed ! and -".
This is a complete post on its own: what I got wrong about unary parsing and how the bug showed up.)_

**The click:** _(backfill)_

**Decision I made:** _(BACKFILL — I wrote the parser my own way first, then refactored to the book's
design (`17a353e`). Why did I change it? What did the book's version do better? That comparison is
the most interesting thing in this repo and it's currently unwritten.)_

**Question I couldn't answer:** _(backfill)_

### How it felt

**Pull:** This time I built everything by myself, then consulted the book. It
was very fun.

**Frustration type:** Once again, it was fun-hard.

**Curiosity tail:** Again I did not want to stop, but I had to. It's incredible.
There were some extra challenges at the end, I will definitely be returning to
these later in the future.

**One line:** Very, very fun.

### Output

**Post seed:** "I wrote the parser myself before reading how the book does it" — what I got right,
what I got wrong, and what the refactor taught me. **← strong candidate for the first LinkedIn post.**

---

# Final comparison (after all three)

| Project  | Pull /5 | Fun-hard /5 | Curiosity tail /5 | Total /15 |
| -------- | ------- | ----------- | ----------------- | --------- |
| Compiler |         |             |                   |           |
| Embedded |         |             |                   |           |
| OS       |         |             |                   |           |

**Winner:**

**Why:**

**→ Subfield decision feeds into the `masters` repo (docs 02 / 03 / 06).**

---

## Post queue

Scan the `Post seed:` lines above on posting day. Move them here once drafted, so I can see the
history and rotate formats (snag / click / decision / artifact / question — don't run the same
format twice in a row).

| Date | Format | From | Where | Link |
|------|--------|------|-------|------|
|      |        |      |       |      |

**Cadence: biweekly. Skipping a cycle is free. See `masters/07-visibility-and-career.md` §4.**
