<div align="center">
  <h1><b>8-Bit Fare</b></h1>
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

**8-Bit Fare** is a console-based mini-arcade system featuring three classic-inspired games — **Poker**, **Monopoly**, and **Wordle** — all integrated into one unified Java application.  
Built with strong Object-Oriented Programming (OOP) principles, the project demonstrates how multiple independent games can run seamlessly inside one system using modular architecture, reusable classes, and clean code design.

The system provides:
- A centralized arcade menu  
- Independent logic per game  
- Shared utilities for input handling, animations, text styling, and clearing the screen  
- An organized, expandable structure for future game additions  

**8-Bit Fare** highlights teamwork and serves as a practical demonstration of applying OOP in real-world multi-module Java applications.

---

## ⭐ Key Features

### **Included Games**
| Game      | Description | Key Mechanics | Notes |
|-----------|-------------|----------------|--------|
| Poker     | Console card game | Dealing, betting, hand evaluation | Managed by `MyPokerGame` |
| Monopoly  | Board simulation | Dice rolls, rent, buying properties | Uses ANSI color-coded tiles |
| Wordle    | Word guessing game | Player or random secret word | Uses updated `Process_Wordle` |

---

### **OOP-Based Design**
| Principle | Implementation |
|----------|----------------|
| Encapsulation | Game logic & data kept private in classes |
| Abstraction | Simplified game interfaces: `start()`, `play()`, etc. |
| Inheritance | `Wordle` extends `Process_Wordle`; shared game logic |
| Polymorphism | Menu loads different game types at runtime |
| Composition | Games contain objects (Deck, Board, Players, WordList) |

---

## 📁 Program Structure

```
Main/
8-Bit-Fare/
├── Main.java                                 # Application entry point, launches arcade menu
├── Games/
│   ├── ArcadeSystem.java                     # Central game hub: choose Poker, Monopoly, or Wordle
│   ├── Poker/
│   │   ├── Poker.java                        # Poker game launcher
│   │   ├── MyPokerGame.java                  # Handles Poker gameplay logic
│   │   └── Poker_Assets/                     # Additional Poker resources (cards, helpers, etc.)
│   ├── Monopoly/
│   │   ├── Monopoly.java                     # Monopoly game launcher
│   │   ├── Board.java                        # Monopoly board controller + tile management
│   │   ├── Dice.java                         # Dice roller and doubles handling
│   │   └── Bank.java                         # Manages money, rent, and transactions
│   └── Wordle/
│       ├── Wordle.java                       # Wordle game launcher
│       ├── Process_Wordle.java               # UPDATED FILE — Word handling, validation, comparison
│       └── Wordle_Assets/                    # Word lists, helpers, display utils
├── utils/
│   ├── Input_Handling.java                   # User input manager (validation, parsing, safety)
│   ├── Screen.java                           # Screen clearing & formatting utilities
│   └── TextColor.java                        # ANSI color codes for stylized console output
├── out/                                      # Build output (generated during compilation)
└── README.md                                 # Project documentation

```
---

## 🎮 Gameplay Guide

### **Starting the Arcade**
1. Run the arcade system  
2. Choose between:  
   **(1) Poker** • **(2) Monopoly** • **(3) Wordle** • **(4) Exit**

### **Core Utility**
- `Input_Handling` validates all inputs  
- `ArcadeSystem` manages switching between games  
- Shared styles for colors, animations, and screen behavior  

---




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

---

## 📜 Sample Output

====================================
        WELCOME TO 8-BIT FARE
====================================
Choose a game to play:
1. Poker
2. Monopoly
3. Wordle
4. Exit
Enter your choice: 2

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
