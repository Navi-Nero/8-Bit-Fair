<div align="center">
  <h1><b>8-Bit Fare</b></h1>
  <h3><b>Multi-Game Java Console Arcade Showcasing OOP Principles</b></h3>

  <b>IT-2108:</b><br>
  Quinto, Isaac<br>
  Jasareno, Andrei<br>
  Casilao, Lenard<br>
</div>

<p align="center">
  <a href="#-overview">
    <img src="https://img.shields.io/badge/📖_Overview-1E90FF?style=for-the-badge">
  </a>
  <a href="#-key-features">
    <img src="https://img.shields.io/badge/⭐_Features-4682B4?style=for-the-badge">
  </a>
  <a href="#-gameplay-guide">
    <img src="https://img.shields.io/badge/🎮_Gameplay_Guide-5F9EA0?style=for-the-badge">
  </a>
  <a href="#-program-structure">
    <img src="https://img.shields.io/badge/🏗️_Program_Structure-6495ED?style=for-the-badge">
  </a>
  <a href="#-how-to-run">
    <img src="https://img.shields.io/badge/🚀_How_to_Run-87CEFA?style=for-the-badge">
  </a>
  <a href="#-sample-output">
    <img src="https://img.shields.io/badge/📜_Sample_Output-ADD8E6?style=for-the-badge">
  </a>
  <a href="#-author--acknowledgement">
    <img src="https://img.shields.io/badge/👤_Author_Acknowledgement-1E90FF?style=for-the-badge">
  </a>
</p>

## 📖 Overview

This Java-based console application serves as a central arcade hub featuring three complete, fully-functional games: **Poker**, **Monopoly**, and **Wordle**.

The project's primary objective is to demonstrate robust **Object-Oriented Programming (OOP)** principles, including:
* **Encapsulation**
* **Abstraction**
* **Inheritance**
* **Polymorphism**

This is achieved through a modular and scalable design, where each game is developed in separate classes and packages.

🎯 **Key Features**

**Games Included**  

| Game      | Description                 | Key Mechanics                               | Notes                                      |
|-----------|----------------------------|--------------------------------------------|-------------------------------------------|
| Poker     | Console-based card game    | Card dealing, player hands, gameplay loop  | Configurable settings, default or custom arguments |
| Monopoly  | Board game simulation      | Color-coded properties, board navigation  | Organized via `Board` class, ANSI color codes for visuals |
| Wordle    | Word guessing game         | Player-entered or random words, validation, restart option | Inherits logic from `Process_Wordle` |

**OOP-Based Design**  

| Principle      | Implementation                                               |
|----------------|-------------------------------------------------------------|
| Encapsulation  | Each game manages its own input and logic internally        |
| Abstraction    | Complex operations (cards, board, word logic) hidden behind method calls |
| Inheritance    | Wordle extends `Process_Wordle` to reuse shared logic      |
| Polymorphism   | Input handling and game methods adapt based on user choices|

**Input & Menu System**  
- Centralized `Input_Handling` class validates user inputs  
- Main menu allows switching between games without restarting  
- Clear prompts for easy navigation  

**Exit System**  
- User-friendly exit option from the main menu  
- Graceful shutdown of input handler

🏗️ **OOP Concepts Applied in 8-Bit Fare**

**1. Inheritance**  
- `Game` (abstract) → `Poker`, `Monopoly`, `Wordle` (concrete classes)  
- Shared components like `Board`, `Process_Wordle`, and `MyPokerGame` inherit from base classes for reusable logic  

**2. Polymorphism**  
- Abstract/game interface methods implemented per game: `start()`, `playTurn()`, `processInput()`  
- Main menu calls methods on abstract `Game` type; runtime selects the chosen game’s implementation  
- Input handling adapts to different game rules  

**3. Encapsulation**  
- Game data (e.g., player hands, board positions, guessed words) kept private inside each class  
- Public getter/setter methods for accessing/modifying game state  
- Input_Handling class encapsulates all user input validation  

**4. Abstraction**  
- Abstract classes and interfaces hide game logic details, exposing simple methods like `start()`, `play()`, or `restart()`  
- Complex operations (card dealing, board movement, word checking) hidden behind simple method calls  
- Reusable utility packages for text styling and input management  

**5. Composition**  
- `Game` objects contain other components:  
  - `Poker` contains `Deck` and `Player` objects  
  - `Monopoly` contains `Board`, `Property`, and `Player` objects  
  - `Wordle` contains `WordList` and `PlayerGuess` objects  
- Main arcade menu combines multiple `Game` objects to provide a unified experience

 ## 📁 Program Structure
I understand. You want the Program Structure for your Quezon Chronicles RPG to be finalized in the exact hierarchical Markdown format you provided, including the file names, indentation, and comments, with all the utility classes properly nested under the styles/ directory.

Here is the requested code for the README.md file:

Markdown

## 📁 Program Structure

Main/ ├── Main.java # Application entry point ├── game/ │ ├── Game.java # Main menu, title animation, game flow │ ├── GameMenu.java # Character creation, path selection │ └── BattleSystem.java # Turn-based combat loop engine ├── worldBuilder/ │ ├── WorldMap.java # Builds West & East paths with towns │ └── Town.java # Town logic, enemy encounters, progression ├── character/ │ ├── Character.java # Abstract base class (stats, debuffs) │ ├── player/ │ │ ├── Player.java # Abstract player class (inventory, leveling) │ │ └── classes/ # 5 concrete player classes │ │ ├── Warrior.java # Tank class with stamina │ │ ├── Bruid.java # Balanced fighter │ │ ├── Mage.java # Glass cannon with MP │ │ ├── TagalogMonk.java # Support/hybrid class │ │ └── Thief.java # High-damage, high-speed assassin │ └── enemy/ │ ├── Enemy.java # Abstract enemy class (loot, XP) │ └── subclasses/ # 31 concrete enemy types │ ├── TanimGuardian.java │ ├── HabhabBandit.java │ ├── LucenaPirate.java │ ├── PortRat.java │ ├── HarborSentinel.java # Miniboss │ ├── BakeryGremlin.java │ ├── HeritageWraith.java │ ├── DonMariano.java # West path final boss │ ├── QueenAmihan.java # East path final boss │ └── [21 more...] ├── item/ │ ├── Item.java # Abstract item base class │ └── [23 concrete items] │ ├── Bibingka.java # +25 HP, remove debuffs │ ├── Lambanog.java # Spirit drink │ ├── PugonCoffee.java # Coffee item │ └── [20 more...] └── styles/ ├── animationHub/ │ ├── TypeWriter.java # Typewriter text animation │ └── LoadingDots.java # Loading animation ├── textColor/ │ └── TextColorHub.java # ANSI color codes (RED, GREEN, YELLOW, etc.) ├── printAlignmentHub/ │ └── CenterHub.java # Text centering & right alignment └── clearScreen/ └── ClearScreen.java # Cross-platform terminal clearing
