# Systems Exploration

Three small low-level projects, built to find out which one I actually love.

I've spent my career above the application layer, and I want to know what's underneath it. So rather
than read about compilers, embedded systems and kernels, I'm building a small version of each and
paying attention to which one I reach for on a weekend.

**The goal isn't three polished projects — it's discovering which one I can't stop thinking about.**
Hit each project's **MINIMUM DONE**, then judge it honestly in [`JOURNAL.md`](JOURNAL.md), and
compare at the end.

## The three projects

| Folder | Project | Resource | MINIMUM DONE |
|--------|---------|----------|--------------|
| [`compiler/`](compiler/) | Tree-walking interpreter for "Lox" | [Crafting Interpreters](https://craftinginterpreters.com) (free, Part II "jlox", Java) | Runs recursive `fib(n)` |
| [`embedded/`](embedded/) | ESP32 firmware (simulated) | [Wokwi](https://wokwi.com) (free, in-browser) | A sensor controls an output in real time |
| [`os/`](os/) | Minimal kernel in QEMU | [Writing an OS in Rust](https://os.phil-opp.com) (free) | "Hello from my kernel" on screen |

**Order:** Compiler → Embedded → OS. ~6–10 hrs/week, ~3 weeks each.

## Judge each session (in JOURNAL.md)
1. **Pull** — did I want to keep going? did time disappear?
2. **Fun-hard vs drain-hard** — was the hard part a puzzle I enjoyed, or a slog I forced?
3. **Curiosity tail** — afterward, did I want to go deeper, or was I relieved it ended?

The winner is the one I go deep on next.
