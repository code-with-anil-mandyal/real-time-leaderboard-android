# Architecture & Design Document

Real-Time Leaderboard Android

---

# 📌 Objective

This project demonstrates how a real-time leaderboard system can be designed for a mobile gaming platform.

Instead of focusing only on UI, the goal was to design a system that handles:

* Real-time score updates  
* Scalable architecture  
* Clean separation of responsibilities  
* Lifecycle-safe UI updates  
* Testable business logic  

The overall approach prioritizes maintainability, performance, and real-world production design.

---

# 🧩 System Design Overview

The system is designed with **two logical components**:

1. Score Generator (Game Engine)
2. Leaderboard Module (Consumer)

These are kept logically separate to simulate **real production architecture**.

---

# 🧩 Why This Design

In a real gaming system, score updates typically come from a backend or match engine.  
To simulate this behavior, the ScoreGenerator acts as a game engine while the leaderboard module consumes updates and builds UI state.

This separation keeps:

* Business logic independent
* UI reactive and lightweight
* System easy to scale and maintain

---

# 🎮 Module 1 — Score Generator (Game Engine)

This module simulates a **game backend or match engine**.

Instead of calling a real backend, this module generates **live score updates**.

### Responsibilities

* Maintain list of players
* Generate random score updates
* Emit updates continuously
* Stay UI independent

### Implementation

```
ScoreGenerator
 └── Emits Flow<Pair<PlayerId, Score>>
```

### Key Characteristics

* Built using Kotlin Coroutines + Flow
* Random update interval (500ms–2000ms)
* Score only increases
* Deterministic within session
* Completely UI-agnostic

This makes the module reusable and easily replaceable with a real backend later.

---

# 🧩 Module 2 — Leaderboard Module

This module consumes score updates and builds leaderboard state.

### Responsibilities

* Listen to score updates
* Maintain leaderboard state
* Apply ranking rules
* Emit updated leaderboard

### Data Flow

```
ScoreGenerator
      ↓
LeaderboardRepository
      ↓
ObserveLeaderboardUseCase
      ↓
ViewModel
      ↓
UI
```

This ensures a **reactive, real-time UI**.

---

# 🧠 Ranking Logic

The leaderboard follows standard gaming ranking rules:

* Sorted by score (Descending)
* Same score → Same rank
* Rank skipping supported

Example:

```
Player A - 100 → Rank 1
Player B - 100 → Rank 1
Player C - 90  → Rank 3
```

Ranking logic is implemented inside:

```
LeaderboardRepositoryImpl
```

This decision keeps:

* Business logic outside UI
* Code easily testable
* Logic reusable

---

# 🏗 Architecture

This project uses Clean Architecture combined with MVVM to keep responsibilities clearly separated.

Each layer focuses on a specific responsibility:

* Clean Architecture
* MVVM
* Reactive Flow based updates

Layers used:

```
Data Layer
Domain Layer
Presentation Layer
```

Each layer has clear responsibility which keeps code clean and maintainable.

---

# 🔄 Real-Time Data Flow

```
Score Generator
      ↓
Repository
      ↓
UseCase
      ↓
ViewModel
      ↓
Compose UI
```

This architecture ensures:

* Smooth UI updates
* Minimal recomposition
* Reactive data handling

---

# ⚙️ Performance Considerations

## Avoid Blocking UI Thread

* Score updates generated using coroutines
* Flow used for async updates
* ViewModel collects updates safely

No heavy work happens on UI thread.

---

## Avoid Unnecessary Recompositions

* LazyColumn with stable keys
* Incremental updates using Flow
* Minimal UI state updates

Example:

```
key = { player.id }
```

---

## Memory Leak Prevention

* ViewModel scoped coroutines
* Lifecycle aware collection

Used:

```
collectAsStateWithLifecycle()
```

This ensures lifecycle safe updates.

---

# 🔄 Lifecycle Handling

## Screen Rotation

* ViewModel survives configuration change
* Flow continues emitting
* UI restores automatically

No data loss during rotation.

---

## App Background

* Coroutine continues in ViewModel
* UI resumes safely
* No crashes or leaks

---

# 📈 Scalability Design

## 1K Users

Handled using:

* LazyColumn virtualization
* Efficient ranking logic
* Flow based updates

UI remains smooth.

---

## 100K Users

For very large leaderboards, ranking calculations can be moved to the backend to reduce device workload and improve performance.

Possible improvements:

* Pagination for large datasets
* Backend-driven ranking service
* Diff-based UI updates
* WebSocket or streaming updates

---

# 🎮 UI Design Decisions

UI focuses on smooth real-time updates:

Features added:

* Collapsing leaderboard header
* Rank change animation
* Score update animation
* Highlight current user

UI is intentionally lightweight but reactive.

---

# 🧪 Unit Testing

Unit tests added for:

* Initial leaderboard
* Ranking logic
* Same score ranking
* Rank skipping logic

Location:

```
test/
 └── LeaderboardRepositoryTest
```

This ensures correctness of business logic.

---

# 🧠 Architecture Decisions

## Why Separate Score Generator

* Simulates backend behavior
* UI independent
* Testable
* Reusable

---

## Why Ranking Logic in Repository

* Business logic belongs in data/domain layer
* UI stays simple
* Easy to test

---

## Tradeoffs Made

### Chosen

* Single module architecture
* Flow based updates
* Simple UI focus

### Deferred

* Multi-module architecture
* Backend integration
* Advanced UI polish

These were deferred to keep focus on architecture.

---

# 👨‍💻 Code Review Simulation

### Must Fix

* Keep ranking logic outside UI
* Avoid recomputing entire list
* Avoid UI timers

### Improvements

* Add pagination support
* Extract ranking into use case
* Optimize diff updates

### Tech Debt

* Multi-module setup
* Backend integration

---

# 📅 7-Day Delivery Planning

## Non-Negotiable

* Real-time leaderboard
* Ranking logic
* Clean architecture

## Deferred

* UI polish
* Backend integration
* Advanced animations

---

# Team Division

### Junior Developer

* UI components
* Layout improvements

### Mid-Level Developer

* Repository logic
* ViewModel handling

### Lead (My Role)

* Architecture design
* Performance optimization
* Code review

---

# ⭐ Production Improvements

Possible future enhancements:

* WebSocket integration
* Backend leaderboard
* Anti-cheat logic
* Multi-module architecture
* Tournament system

---

# ✅ Summary

This project focuses on building a real-time leaderboard with clean architecture and scalable design.

The system demonstrates:

* Real-time data handling using Flow
* Clear separation of business logic
* Lifecycle-safe UI updates
* Performance-aware architecture
* Testable and maintainable code

The architecture is designed to be easily extendable to real backend systems and large-scale leaderboards.

---
