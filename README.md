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

## 📌 Overview

**8-Bit Fare** is a console-based mini-arcade system that brings together three classic-inspired games—**Poker**, **Monopoly**, and **Wordle**—into one unified program.  
Designed with a strong emphasis on Object-Oriented Programming (OOP), the system showcases how multiple independent games can coexist, interact, and operate smoothly within a single application structure.

The program features a centralized main menu where players can easily switch between games without restarting the system.  
Each game is built in its own package, following proper OOP design principles such as encapsulation, abstraction, inheritance, and polymorphism.  
This allows each game to maintain its own logic and assets while still connecting seamlessly to the overall arcade system.

To ensure consistent behavior and user experience across all games, **8-Bit Fare** includes shared utility classes for input handling, animations, text styling, and screen management.  
This not only simplifies development but also keeps the entire project clean, modular, and easy to maintain.

Overall, **8-Bit Fare** highlights teamwork, clean code architecture, and the power of OOP in building a multi-game environment.  
It serves as a practical demonstration of how structured programming and creative design can merge to create an engaging, retro-style console gaming experience.

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

## 🎮 Gameplay Guide

### Starting the Arcade

Run `java -cp out Games.ArcadeSystem`
View the central menu with choices for three games
Choose: (1) Poker, (2) Monopoly, (3) Wordle, (4) Exit

### Core Utilities and Input

Centralized Input: `Input_Handling.java` ensures all inputs are validated
Game Flow: `ArcadeSystem` manages the overall loop; individual game classes control their flow

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

### 📊 Example Monopoly Scenario

Warrior enters Lucena Town
    └─ Encounters: Port Rat, Lucena Pirate (miniboss)

## 🎮 Gameplay Guide

### Starting the Arcade

Run `java -cp out Games.ArcadeSystem`
View the central menu with choices for three games
Choose: (1) Poker, (2) Monopoly, (3) Wordle, (4) Exit

### Core Utilities and Input

Centralized Input: `Input_Handling.java` ensures all inputs are validated
Game Flow: `ArcadeSystem` manages the overall loop; individual game classes control their flow

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

Markdown

## 🃏 Poker Game Guide (`Poker.java`)

The Poker game in the 8-Bit-Fair system is managed primarily by the **`MyPokerGame`** class, handling the core logic for card dealing, betting, and hand evaluation.

### 1. Game Setup and Start

* **Entry Point:** The game is launched via the static `main(String args[])` method in the **`Poker.java`** class.
* **Initialization:** The program creates an instance of **`MyPokerGame`**. This class handles the creation of the card deck and initialization of player balances.
* **Configuration:** The game can accept command-line arguments to set specific parameters (e.g., number of players) when starting `MyPokerGame`.

### 2. Game Flow (`MyPokerGame.play()`)

The entire game loop is driven by the central `play()` method, which executes the standard sequence of a poker hand:

1.  **Ante/Betting:** Players place their initial bets into the pot.
2.  **Dealing:** Initial cards are dealt to all players (handled by **`Poker_Assets/Card.java`** and **`Poker_Assets/Hand.java`**).
3.  **Player Action:** Players choose to Call, Raise, or Fold.
4.  **Discard/Draw:** (If applicable to the poker variant) Players choose which cards to discard and draw new cards to improve their hand.
5.  **Final Bet:** A final round of betting occurs.
6.  **Showdown:** Remaining players reveal their hands.

### 3. Hand Evaluation

* The **`Hand.java`** class contains the core logic for determining the rank of a player's five-card hand (Pair, Two Pair, Flush, Full House, etc.). 

[Image of poker hand rankings]

* The system compares the final hands of all players to determine the winner and award the pot.

### 4. Player Management

* The system tracks player balances and manages the pot (central pool of bets).
* **Winnings:** The winning player receives the full pot, and their balance is updated accordingly.
* **Restart:** After a hand is completed, the game prompts the player to play another round or exit back to the **`ArcadeSystem`** menu.

### 📊 Example Poker Scenario (MyPokerGame)

Player 'Andrei' enters Poker Game with a starting balance of $1000

Round 1: Initial Bet
    `MyPokerGame.play()` method initializes hand

    Prompt: Enter your ante bet (Minimum $20)
    User Input: 50

    Betting Logic:
        Andrei's Money: $1000 -> $950

    MyPokerGame: Player receives initial 5-card hand: [Ace of Spades, Ace of Hearts, 8 of Diamonds, 3 of Clubs, King of Spades]

Round 2: Discard and Draw
    Andrei's Hand Status: Pair of Aces

    Prompt: Which cards to discard? (e.g., 3, 4, 5 for 8, 3, K)
    User Input: 3, 4, 5

    MyPokerGame: Discards (8D, 3C, KS) and draws three new cards: [Ace of Clubs, 7 of Hearts, 7 of Diamonds]

Round 3: Final Hand and Payout
    Andrei's Final Hand: [Ace of Spades, Ace of Hearts, Ace of Clubs, 7 of Hearts, 7 of Diamonds]
    Hand Rank: **Full House** (Aces full of Sevens)

    MyPokerGame: Payout calculated based on Full House odds (e.g., 9:1)

    Andrei's Winnings: +$450
    Final Balance: $950 + $450 = $1400

    [Player is prompted to play another round or return to ArcadeSystem...]

## 🏠 Monopoly Game Guide (`Monopoly.java`)

The Monopoly game in the 8-Bit-Fair system simulates the classic board game, emphasizing the roles of the **`Board`**, **`Dice`**, and **`Bank`** classes.

### 1. Game Setup and Start

* **Entry Point:** The game is launched via the static `main(String[] args)` method in the **`Monopoly.java`** class, which then initiates the **`Board`** controller.
* **Visuals:** Property colors are displayed in the console using **ANSI color codes** defined in `Monopoly.java`.

### 2. Player Turn Sequence

Each turn is managed by the **`Board.playerTurn()`** method and follows these steps:

1.  **Dice Roll:** The **`Dice.rollDice()`** method is called to generate a random number (2-12).
2.  **Movement:** The current player's position is updated by the roll amount (`Board.currentTile()`).
3.  **Doubles Check:** If **`Dice.isDoubles()`** is true, the player rolls again. Rolling doubles **three times in a row** sends the player to Jail.
4.  **Tile Action:** The player interacts with the property manager, special tiles, or card decks based on the new position.

### 3. Core Transactions (Handled by `Bank.java`)

All financial interactions are managed by the **`Bank`** class:

| Method | Role | Example Scenario |
| :--- | :--- | :--- |
| `playerCanBuyProperty()` | Checks if the player has sufficient funds for a purchase. | Player attempts to buy Boardwalk ($400). |
| `playerBuyProperty()` | Transfers money from the player to the bank. | `Player.loseMoney(price)` $\rightarrow$ `Bank.addMoney(price)`. |
| `playerPayRent()` | Facilitates direct player-to-player transfers for rent payments. | **Payer.loseMoney(rent)** $\rightarrow$ **Receiver.addMoney(rent)**. |
| `playerPayTax()` | Transfers tax payments (e.g., Income Tax) from the player to the bank. | Player lands on Income Tax and pays $200. |
| `isPlayerBankrupt()` | Checks if a player's money is $\le 0$ to trigger elimination. | Player reaches $0 and is removed from the game. |

### 4. End of Turn Menu

After the player completes their movement and mandatory tile actions, the **Monopoly Menu** appears, allowing the player to manage assets before ending their turn:

| Option | Action | Notes |
| :--- | :--- | :--- |
| **[1] Check Player Stats** | Displays players' money, properties, and current position. | Handled by `PlayerManager.printPlayersStats()`. |
| **[2] Upgrade Property** | Allows buying houses or hotels on owned, complete color groups. | Involves a `Bank.playerBuyProperty()` transaction. |
| **[3] End Turn** | Passes control to the next player (`PlayerManager.nextTurn()`). | The standard way to end the turn. |

### 📊 Example Scenario: Tax & Upgrade

**Scenario:** Player 'Isaac' lands on Income Tax and then upgrades a property.

Round 1: Income Tax Payment
    Isaac's turn!
    Isaac rolled: 5
    Isaac moved to **Income Tax** (CurrentTile: 4). Tax Amount: $200.

**Decision Point (Tax Payment):**
Bank Check (in `Bank.java`): `playerPayTax(Isaac, 200)` is called.

Transaction:
    Isaac's Money: $1400 $\rightarrow$ $1200
    Bank Money increases by $200

Output: "Isaac pays $200 to the Bank for Income Tax."

Round 2: Property Upgrade
    [Monopoly Menu appears, prompting the player for their next action...]

    Prompt: Enter choice (1-4)
    User Input: 2

    Upgrade System: Isaac chooses to upgrade **Boardwalk** (Cost: $200 per house)

    Bank Check: Isaac has $1200. Cost is $200. Transaction proceeds.

    Output: "Boardwalk upgraded! Cost: $200. Isaac's new balance: $1000."

    Prompt: Enter choice (1-4)
    User Input: 3

    Output: "Isaac ends turn. Next player's turn."

## 🟩 Wordle Game Guide (`Wordle.java`)

The Wordle game in the 8-Bit-Fair system provides the classic five-letter word-guessing experience, managed primarily by the **`Wordle`** class extending **`Process_Wordle`**.

### 1. Game Setup and Start

* **Entry Point:** The game is launched via the **`Wordle.main(String[] args)`** method.
* **Mode Selection:** The game loop prompts the player to choose how the secret word is determined:
    1.  **Player-Entered Word:** Another user manually enters the five-letter word to be guessed (handled by `Process_Wordle.Process.chooseMode()`).
    2.  **Random Word:** The system selects a random word from the dictionary (handled by `Wordle_Assets.WordList.java`).
* **Initialization:** The `Process_Wordle.Process.prepareGame()` method starts the round based on the chosen word.

### 2. Guessing and Validation

* **Input Handling:** User guesses are captured and validated using the **`Input_Handling`** utility to ensure the input is exactly **five letters long** and contains only alphabetic characters.
* **Attempts:** The player is given a maximum of **six attempts** to guess the word.

### 3. Feedback System (The Core Logic)

After each guess, the **`Process_Wordle`** class provides feedback using **ANSI color codes** (from `styles/textColor/TextColor.java`) to guide the player: 

| Feedback Color | Condition | Code Logic |
| :--- | :--- | :--- |
| **Green** | The letter is **correct** and in the **correct position**. | Matches the letter at the same index in the secret word. |
| **Yellow** | The letter is **correct** but in the **wrong position**. | The letter exists elsewhere in the secret word. |
| **Gray** | The letter is **not in the word** at all. | The letter is not found in the secret word. |

### 4. Continuous Play

* The game loop continues until the word is guessed or the player runs out of attempts (6 total).
* **Restart Option:** After a game ends (win or lose), the **`Process_Wordle.Verify.restartGame()`** method prompts the player to either play another round or return to the **`ArcadeSystem`** menu.

### 📊 Example Wordle Scenario

**Secret Word:** C A M P S

Round 1: First Guess
    Prompt: Enter Guess (Attempt 1/6)
    User Input: **P A N T S**
    
    Feedback (Processed by `Process_Wordle`):
    P: Yellow (Correct letter, wrong spot)
    A: Green (Correct letter, correct spot)
    N: Gray (Not in the word)
    T: Gray (Not in the word)
    S: Green (Correct letter, correct spot)

Round 2: Second Guess
    Prompt: Enter Guess (Attempt 2/6)
    User Input: **C H A N G**

    Feedback:
    C: Green (Correct letter, correct spot)
    H: Gray (Not in the word)
    A: Yellow (Correct letter, wrong spot)
    N: Gray (Not in the word)
    G: Gray (Not in the word)
    
Round 3: Winning Guess
    Prompt: Enter Guess (Attempt 3/6)
    User Input: **C A M P S**
    
    Output: "Congratulations! You guessed the word in 3 attempts!"
    [System then prompts if player wants to restart or exit]

## 👤 Author & Acknowledgement

### Development
Developed as a comprehensive Java educational project demonstrating OOP principles, design patterns, and game architecture.

| Name | Role |
|----------|----------|
| Quinto, Isaac | Lead Developer |
| Jasareno, Andrei | Developer / System Designer |
| Casilao, Lenard | Developer / Tester |

# 📞 Support & Contribution

For bug reports, feature requests, or contributions, refer to the project repository on GitHub. 

---

## 🙏 Acknowledgements

### Special Thanks to Our Instructors
- **Ma'am Fatima** - For providing comprehensive lessons, templates, and resources shared through her Discord.
- **Sir Emmanuel** - For his dedicated lessons and continuous effort in teaching us whenever possible. His support and expertise greatly contributed to our understanding and development

### Learning Resources & Inspiration
- **YouTube Channels**: 
  - Bro Code - For comprehensive Java tutorials and best practices
  - Coding with Mosh - For clear programming concepts and design patterns
  - Other Indian Coding Tutorials - For diverse perspectives and innovative solutions
- **Stack Overflow** - For community support and solving countless technical challenges
- **Open-Source Community** - For shared knowledge and programming resources

### Personal Support
- **Our Parents** - For their unwavering support, encouragement, and belief in this project. This wouldn't have been possible without their sacrifices and dedication
- **Our Peers & Friends** - For playtesting, constructive feedback, and motivation throughout development

---
