# OS — Minimal kernel in Rust (QEMU)

A tiny operating system kernel that boots in **QEMU** (no hardware needed), following
**[Writing an OS in Rust](https://os.phil-opp.com)** by Philipp Oppermann (free).

## Setup (budget the first session mostly for this — it's the fiddliest)
1. Install Rust: [rustup.rs](https://rustup.rs).
2. Add the nightly toolchain + components (the posts give exact commands):
   ```sh
   rustup toolchain install nightly
   rustup component add rust-src llvm-tools-preview
   cargo install bootimage
   ```
3. Install **QEMU** (`qemu-system-x86_64`).
4. Start at the first post: **"A Freestanding Rust Binary."**

## Plan (3 weeks, ~6–10 hrs/wk)
| Week | phil-opp posts | Deliverable |
|------|----------------|-------------|
| 1 | Freestanding Binary → Minimal Rust Kernel → VGA Text Mode | **MINIMUM DONE: "Hello from my kernel" printed in QEMU** |
| 2 | CPU Exceptions → Double Faults → Hardware Interrupts | Handles a keyboard interrupt / exception |
| 3 | Introduction to Paging → Heap Allocation | Allocate on a heap I built |

Stretch: Async/Await post (cooperative multitasking — two tasks yielding).

## How to run (fill in once it exists)
```sh
# e.g. cargo run   (boots the kernel in QEMU)
```

## What I learned / would do next
- (write at the end)
