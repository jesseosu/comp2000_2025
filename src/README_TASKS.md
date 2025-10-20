# Tasks 24–27 Implementation Notes

- **Task 24 (Comprehension):** See the Javadoc block at the top of `Client.java` for an annotated walkthrough of how the program works.
- **Task 25 (URL):** `Client.java` now targets `http://13.238.167.130/weather` (note: `http`, not `https`).
- **Task 26 (Explanations):** Each incoming line is interpreted and explained in human‑friendly text via `explainLine(...)`.
- **Task 27 (Streams & Lambdas):** The previous `while` loop is replaced by `reader.lines().map(...).forEach(...)` using the Java Streams API.
