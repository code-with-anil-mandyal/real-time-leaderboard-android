# Real-Time Leaderboard Android (Clean Architecture + Kotlin Flow)

![Kotlin](https://img.shields.io/badge/Kotlin-Android-blue)
![Architecture](https://img.shields.io/badge/Architecture-Clean%20Architecture-green)
![UI](https://img.shields.io/badge/UI-Jetpack%20Compose-orange)

A real-time leaderboard system built using **Kotlin, Coroutines, Flow, and Clean Architecture**.
This project simulates a **live gaming leaderboard** where player scores update dynamically and rankings adjust in real time.

---

# ✨ Features

* Real-time score updates using Kotlin Flow
* Live ranking updates with tie handling
* Smooth UI updates with animations
* Collapsing leaderboard header
* Clean Architecture (Data / Domain / Presentation)
* MVVM Architecture
* Unit Tests for ranking logic
* Scalable real-time system design

---

# 📱 Demo

<table>
<tr>
<td align="center"><b>Expanded</b></td>
<td align="center"><b>Collapsed</b></td>
</tr>

<tr>
<td><img src="https://github.com/user-attachments/assets/7b3a1626-f076-429f-9bd8-a10eecb8a1f5" width="250"/></td>
<td><img src="https://github.com/user-attachments/assets/f64c95c6-a459-43ed-a882-a1de0915c585" width="250"/></td>
</tr>

</table>

---

# 🏗 Architecture

The project follows **Clean Architecture + MVVM**

```
data
 ├── engine
 │     └── ScoreGenerator
 ├── repository
 │     └── LeaderboardRepositoryImpl

domain
 ├── model
 │     └── LeaderboardPlayer
 ├── repository
 └── usecase
       └── ObserveLeaderboardUseCase

presentation
 ├── leaderboard
 │     ├── LeaderboardScreen
 │     ├── LeaderboardViewModel
 │     └── components
 └── common
       ├── RankBadge
       ├── TrophyBadge
       └── AvatarUtils
```

# 🧩 Architecture Highlights

* UI-agnostic score generator module
* Reactive leaderboard updates using Kotlin Flow
* Ranking logic isolated in repository layer
* Testable domain and business logic
* Clear separation of concerns (Data / Domain / Presentation)
* Real-time event driven architecture
  
---

# ⚙️ Tech Stack

* Kotlin
* Jetpack Compose
* Coroutines
* Flow / StateFlow
* MVVM
* Clean Architecture
* Hilt Dependency Injection
* Material3

---

# 🔄 Real-Time Flow

```
ScoreGenerator
      ↓
Repository
      ↓
UseCase
      ↓
ViewModel
      ↓
Compose UI
```

* Scores update randomly
* Ranking recalculated
* UI updates automatically

---

# 🧠 Ranking Rules

* Sorted by score (Descending)
* Same score → Same rank
* Rank skipping supported

Example:

```
Player A - 100 → Rank 1
Player B - 100 → Rank 1
Player C - 90  → Rank 3
```

---

# 🎮 UI Features

* Collapsing leaderboard header
* Live score animation
* Rank change animation
* Highlight current user
* Smooth LazyColumn updates

---

# 🧪 Unit Tests

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

---

# 🚀 How to Run

1. Clone repository

```
git clone https://github.com/code-with-anil-mandyal/real-time-leaderboard-android.git
```

2. Open in Android Studio

3. Sync Gradle

4. Run App

---

# 📦 Modules (Logical Separation)

Single-module project with clean architectural separation:

* Data Layer
* Domain Layer
* Presentation Layer

This keeps system:

* Testable
* Scalable
* Maintainable

---

# ⚡ Performance Considerations

* Flow based updates
* LazyColumn with stable keys
* Minimal recompositions
* No UI thread blocking

---

# 🔮 Future Improvements

* Multi-module architecture
* WebSocket integration
* Real backend support
* Pagination for large leaderboards
* Player profile screen
* Live tournament support

---

# 👨‍💻 Author

**Anil Kumar**
Android Developer

GitHub:
https://github.com/code-with-anil-mandyal

---

# 📌 Project Purpose

This project demonstrates real-time leaderboard architecture for mobile gaming systems, focusing on:

* Real-time systems
* Architecture design
* Kotlin Flow expertise
* Performance awareness
* Leadership thinking

---

# ⭐ If You Like This Project

Give it a ⭐ on GitHub
