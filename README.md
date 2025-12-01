<div align="center">
  <h1><b>8-Bit Fair</b></h1>
  <h3><b>Multi-Game Java Console Arcade Showcasing OOP Principles</b></h3>

  <b>IT-2108:</b><br>
  Quinto, Isaac<br>
  Jasareno, Andrei<br>
  Casilao, Lenard<br>
</div>

<p align="center">
  <a href="#-overview"><img src="https://img.shields.io/badge/📖_Overview-1E90FF?style=for-the-badge"></a>
  <a href="#-key-features"><img src="https://img.shields.io/badge/⭐_Features-4682B4?style=for-the-badge"></a>
  <a href="#-gameplay-guide"><img src="https://img.shields.io/badge/🎮_Gameplay_Guide-5F9EA0?style=for-the-badge"></a>
  <a href="#-program-structure"><img src="https://img.shields.io/badge/🏗️_Program_Structure-6495ED?style=for-the-badge"></a>
  <a href="#-how-to-run"><img src="https://img.shields.io/badge/🚀_How_to_Run-87CEFA?style=for-the-badge"></a>
  <a href="#-sample-output"><img src="https://img.shields.io/badge/📜_Sample_Output-ADD8E6?style=for-the-badge"></a>
  <a href="#-author--acknowledgement"><img src="https://img.shields.io/badge/👤_Author_Acknowledgement-1E90FF?style=for-the-badge"></a>
</p>

---

## 📖 Overview

**8-Bit Fair** is a console-based mini-arcade system featuring three classic-inspired games — **Poker**, **Monopoly**, and **Wordle** — all integrated into one unified Java application.  
Built with strong Object-Oriented Programming (OOP) principles, the project demonstrates how multiple independent games can run seamlessly inside one system using modular architecture, reusable classes, and clean code design.

The system provides:
- A centralized arcade menu  
- Independent logic per game  
- Shared utilities for input handling, animations, text styling, and clearing the screen  
- An organized, expandable structure for future game additions  

**8-Bit Fair** highlights teamwork and serves as a practical demonstration of applying OOP in real-world multi-module Java applications.

---

## ⭐ Key Features

### **Included Games**
| Game      | Description | Key Mechanics | Notes |
|-----------|-------------|----------------|--------|
| Poker     | Console card game | Dealing, betting, hand evaluation | Managed by `MyPokerGame` |
| Monopoly  | Board simulation | Dice rolls, rent, buying properties | Uses ANSI color-coded tiles |
| Wordle    | Word guessing game | Player or random secret word | Uses updated `Process_Wordle` |

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
- User-friendl y exit option from the main menu  
- Graceful shutdown of input handler

🏗️ **OOP Concepts Applied in 8-Bit Fair**

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

## 🎮 Gameplay Guide

### Game Instructions

#### 🃏 Poker Game

Flow: `MyPokerGame.play()` handles dealing, betting, and hand resolution
Configuration: Starts with default settings but accepts custom configuration

#### 🏠 Monopoly Game

Setup: Requires at least two players
Display: Property colors are shown using **ANSI color codes**
Player Turn:
( 1 ) Roll Dice - `Dice.rollDice()` is called, player moves
( 2 ) Check for Doubles - Three consecutive doubles sends player to jail
( 3 ) Bank Transaction - `Bank.java` handles all purchases, rent, and taxes
( 4 ) Monopoly Menu - Allows player to Check Stats, Upgrade Property, or End Turn

#### 🟩 Wordle Game

Starting Mode: Choose between another player entering the word or generating a random word
Logic: `Wordle` class extends `Process_Wordle` for core game logic
Continuous Play: `Verify.restartGame()` prompts player for another round


## 🃏 Poker Game Guide (`Poker.java`)

### Game Flow  
Managed by `MyPokerGame.play()`:

1. Ante / Initial Bet  
2. Deal Cards  
3. Player Actions (Call/Raise/Fold)  
4. Discard & Draw (if variant allows)  
5. Final Bet  
6. Showdown & Payout  

### Hand Evaluation  
`Hand.java` determines:
- Pair, Two Pair, Straight, Flush, Full House, etc.

### Player Management  
- Tracks balance, pot, winnings  
- Option to continue or return to Arcade  

---
## 🏠 Monopoly Game Guide (`Monopoly.java`)

### Turn Sequence (via `Board.playerTurn()`)
1. Roll Dice (`Dice.rollDice()`)  
2. Move Player  
3. Check for Doubles  
4. Perform Tile Action (rent, tax, buy property, draw card)  

### Bank Operations (`Bank.java`)
| Method | Purpose |
|--------|---------|
| `playerCanBuyProperty()` | Check funds |
| `playerBuyProperty()` | Purchase logic |
| `playerPayRent()` | Player-to-player payment |
| `playerPayTax()` | Pay tax to bank |
| `isPlayerBankrupt()` | Player elimination |

### End Turn Menu
- Check player stats  
- Upgrade property  
- End turn  

---

## 🟩 Wordle Game Guide (`Wordle.java`)

Now updated using the **new `Process_Wordle` file** you mentioned.

### Setup
1. Choose mode:
   - Player-entered word  
   - Random word (via `WordList`)  
2. `Process_Wordle.prepareGame()` initializes everything  

### Guessing Rules
- Must be **5 letters**
- Max **6 attempts**
- Validation via `Input_Handling`

### Feedback Colors (from new `Process_Wordle`)
| Color | Meaning |
|--------|---------|
| **Green** | Correct letter, correct position |
| **Yellow** | Correct letter, wrong position |
| **Gray** | Letter not in word |

### Restart System
`Process_Wordle.Verify.restartGame()`  
Player chooses to retry or return to Arcade.

## 📜 Sample Output

### Main Menu
```
====================================
        WELCOME TO 8-BIT FAIR
====================================
Choose a game to play:
1. Poker
2. Monopoly
3. Wordle
4. Exit
Enter your choice: 2
```
```
------------------------------------
        MONOPOLY BOARD
------------------------------------
Player 1: $1500
Player 2: $1500

Player 1's turn!
Rolling dice... You rolled 7
Moved to Boardwalk
This property is unowned. Do you want to buy it? (Y/N): Y
You bought Boardwalk for $400. Balance: $1100

End of turn. Press Enter to continue...
```
```
====================================
            POKER TABLE
====================================
Player Balance: $500
Pot: $50

Your cards: [A♠, 10♦]
Dealer shows: [K♣, ?]

Choose an action:
1. Call
2. Raise
3. Fold
Enter your choice: 2
Enter raise amount: 50
You raised by $50. New pot: $100

Returning to Arcade Menu...
```
```
====================================
             WORDLE
====================================
Guess the 5-letter word:

Attempt 1: CRANE
C(G) R(X) A(Y) N(X) E(G)

Attempt 2: SLATE
S(X) L(Y) A(Y) T(X) E(G)

Congratulations! You guessed the word 'PLATE' in 4 attempts!
Play again? (Y/N): N

Returning to Arcade Menu...
```


## 👤 Author & Acknowledgement

### **Development Team**
| Name | Role |
|------|------|
| **Quinto, Isaac** | Lead Developer |
| **Jasareno, Andrei** | Developer / System Designer |
| **Casilao, Lenard** | Developer / Tester |

---

## 🙏 Acknowledgements
### **Instructors**
- **Ma'am Fatima** – For materials, templates, and guidance  
- **Sir Emmanuel** – For consistent lessons and support  

### **Learning Resources**
- Bro Code (Java tutorials)  
- Coding with Mosh  
- Indian Programming Channels  
- Stack Overflow  
- Open-Source communities  

### **Personal**
- Our parents  
- Friends & classmates for feedback and testing  

---
