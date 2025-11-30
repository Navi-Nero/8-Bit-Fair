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

Here’s a simplified version of **8-Bit Fare** showing the arcade menu and sample gameplay for Poker, Monopoly, and Wordle:

import java.util.*;

public class EightBitFare {
    static Scanner sc = new Scanner(System.in);
    static Random rand = new Random();

    public static void main(String[] args) {
        while (true) {
            System.out.println("====================================");
            System.out.println("        WELCOME TO 8-BIT FARE");
            System.out.println("====================================");
            System.out.println("Choose a game to play:");
            System.out.println("1. Poker");
            System.out.println("2. Monopoly");
            System.out.println("3. Wordle");
            System.out.println("4. Exit");
            System.out.print("Enter your choice: ");
            int choice = sc.nextInt();
            sc.nextLine(); // consume newline

            switch (choice) {
                case 1:
                    playPoker();
                    break;
                case 2:
                    playMonopoly();
                    break;
                case 3:
                    playWordle();
                    break;
                case 4:
                    System.out.println("Thanks for playing! Goodbye!");
                    return;
                default:
                    System.out.println("Invalid choice! Try again.");
            }
        }
    }

    // ---------------- POKER ----------------
    static void playPoker() {
        System.out.println("\n====================================");
        System.out.println("            POKER TABLE");
        System.out.println("====================================");
        int balance = 500;
        int pot = 50;
        System.out.println("Player Balance: $" + balance);
        System.out.println("Pot: $" + pot);
        String[] cards = {"A♠", "10♦"};
        System.out.println("Your cards: " + Arrays.toString(cards));
        System.out.println("Dealer shows: [K♣, ?]");
        System.out.println("\nChoose an action:");
        System.out.println("1. Call");
        System.out.println("2. Raise");
        System.out.println("3. Fold");
        System.out.print("Enter your choice: ");
        int action = sc.nextInt();
        sc.nextLine();
        if(action == 2){
            System.out.print("Enter raise amount: ");
            int raise = sc.nextInt();
            sc.nextLine();
            pot += raise;
            balance -= raise;
            System.out.println("You raised by $" + raise + ". New pot: $" + pot);
        } else if(action == 1){
            System.out.println("You called. Pot: $" + pot);
        } else {
            System.out.println("You folded. Pot: $" + pot);
        }
        System.out.println("Returning to Arcade Menu...\n");
    }

    // ---------------- MONOPOLY ----------------
    static void playMonopoly() {
        System.out.println("\n------------------------------------");
        System.out.println("        MONOPOLY BOARD");
        System.out.println("------------------------------------");
        int player1 = 1500, player2 = 1500;
        System.out.println("Player 1: $" + player1);
        System.out.println("Player 2: $" + player2);
        System.out.println("\nPlayer 1's turn!");
        int dice = rand.nextInt(6)+1 + rand.nextInt(6)+1;
        System.out.println("Rolling dice... You rolled " + dice);
        System.out.println("Moved to Boardwalk");
        System.out.print("This property is unowned. Do you want to buy it? (Y/N): ");
        String buy = sc.nextLine().toUpperCase();
        if(buy.equals("Y")){
            player1 -= 400;
            System.out.println("You bought Boardwalk for $400. Balance: $" + player1);
        } else {
            System.out.println("You did not buy the property.");
        }
        System.out.println("End of turn. Press Enter to continue...");
        sc.nextLine();
    }

    // ---------------- WORDLE ----------------
    static void playWordle() {
        System.out.println("\n====================================");
        System.out.println("             WORDLE");
        System.out.println("====================================");
        String[] wordList = {"PLATE", "CRANE", "SLATE", "GLOVE", "BRICK"};
        String secret = wordList[rand.nextInt(wordList.length)];
        int attempts = 6;
        for(int i=1;i<=attempts;i++){
            System.out.print("Attempt " + i + ": ");
            String guess = sc.nextLine().toUpperCase();
            if(guess.equals(secret)){
                System.out.println("Congratulations! You guessed the word '" + secret + "' in " + i + " attempts!");
                break;
            } else {
                // simple feedback
                StringBuilder fb = new StringBuilder();
                for(int j=0;j<5;j++){
                    if(j<guess.length()){
                        if(guess.charAt(j) == secret.charAt(j)){
                            fb.append(guess.charAt(j) + "(G) "); // Green
                        } else if(secret.contains("" + guess.charAt(j))){
                            fb.append(guess.charAt(j) + "(Y) "); // Yellow
                        } else {
                            fb.append(guess.charAt(j) + "(X) "); // Gray
                        }
                    }
                }
                System.out.println(fb.toString());
            }
            if(i==attempts){
                System.out.println("Out of attempts! The word was: " + secret);
            }
        }
        System.out.print("Play again? (Y/N): ");
        String again = sc.nextLine().toUpperCase();
        if(again.equals("Y")) playWordle();
        else System.out.println("Returning to Arcade Menu...\n");
    }



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
