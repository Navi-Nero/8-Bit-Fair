package Games.Monopoly_Assets;

import Games.Input_Handling;
import Games.Monopoly_Assets.Cards.*;
import Games.Monopoly_Assets.Players.*;
import Games.Monopoly_Assets.Properties.*;
import Games.Monopoly_Assets.Special_Tiles.*;

// Main game board and controller for monopoly
// Manages the game flow, player turns, and interactions with the board
public class Board
{
    private static final Input_Handling input = new Input_Handling();
    private static final PlayerManager players = new PlayerManager();
    private static final CardDeck cards = new CardDeck();
    private static final Bank bank = new Bank();
    private static final Dice dice = new Dice();
    private static final PropertyManager properties = new PropertyManager();
    private static final SpecialTileManager specialTiles = new SpecialTileManager();

    // Player draws a chance card
    public static void drawChanceCard()
    {
        CardData chanceCard = cards.drawChanceCard();
        System.out.println("Chance Card: " + chanceCard.getDescription());

        // If it's a keepable card, player keeps it
        if (chanceCard.isKeepable())
        {
            System.out.println("Player keeps this card until used.");
        }
    }

    // Player draws a community chest card
    public static void drawCommunityChestCard()
    {
        CardData chestCard = cards.drawCommunityChestCard();
        System.out.println("Community Chest Card: " + chestCard.getDescription());

        // If it's a keepable card, player keeps it
        if (chestCard.isKeepable())
        {
            System.out.println("Player keeps this card until used.");
        }
    }

    // Check the status of all players
    public static void checkPlayerStats()
    {
        int targetIndex = input.getInt("\nEnter the player number to check (0 to see all):\n");
        players.printPlayersStats(targetIndex);
    }

    // Handle a player's turn: roll dice and move
    public static void playerTurn()
    {
        PlayerData currentPlayer = players.getCurrentPlayer();
        System.out.println("\n" + currentPlayer.getPlayerName() + "'s turn!");

        // Roll the dice
        int rollAmount = dice.rollDice();
        System.out.println(currentPlayer.getPlayerName() + " rolled: " + rollAmount);

        // TODO: Move player on board, handle special tiles, etc.
        // For now just show what they rolled
    }

    // Main game loop
    public static void main(String[] args)
    {
        cards.shuffle();
        System.out.println("Welcome to Monopoly!");

        int menuChoice = 0;

        while (menuChoice != 5)
        {
            menuChoice = input.getInt("\n=== MONOPOLY MENU ===\n" +
                    "[1] Check Player Stats\n" +
                    "[2] Player Turn\n" +
                    "[3] Draw Card\n" +
                    "[4] Game Info\n" +
                    "[5] Exit\n\n");

            switch (menuChoice)
            {
                case 1:
                    checkPlayerStats();
                    break;

                case 2:
                    playerTurn();
                    break;

                case 3:
                    int cardChoice = input.getInt("Draw [1] Chance or [2] Community Chest?\n");
                    if (cardChoice == 1)
                        drawChanceCard();
                    else if (cardChoice == 2)
                        drawCommunityChestCard();
                    break;

                case 4:
                    System.out.println("\nGame Status:");
                    System.out.println("Active players: " + players.getActivePlayerCount());
                    break;

                case 5:
                    System.out.println("Thanks for playing Monopoly! Bye!");
                    break;

                default:
                    System.out.println("Invalid choice. Try again.");
            }
        }
    }
}