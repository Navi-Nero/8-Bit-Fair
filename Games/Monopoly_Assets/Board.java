package Games.Monopoly_Assets;

import Games.Input_Handling;
import Games.Monopoly_Assets.Cards.*;
import Games.Monopoly_Assets.Players.*;
import Games.Monopoly_Assets.Properties.*;
import Games.Monopoly_Assets.Special_Tiles.*;
import java.util.ArrayList;
import java.util.Collections;

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
    private static int currentTile = 0;

    public static void createBoard()
    {

    }

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

    // Find which tile the player landed on after rolling
    public static void currentTile(int roll)
    {
       currentTile += roll;

       if (currentTile >= 40)
       {
        currentTile -= 40;
       }


    }

    // Handle a player's turn: roll dice and move
    public static void playerTurn()
    {
        PlayerData currentPlayer = players.getCurrentPlayer();
        System.out.println("\n" + currentPlayer.getPlayerName() + "'s turn!");
        int doublesCount = 0;

        // Roll the dice
        int rollAmount = dice.rollDice();
        
        //Check if the player rolled doubles 
        if (dice.isDoubles())
        {

            System.out.println(currentPlayer.getPlayerName() + " rolled a doubles! Rolling again." );
            doublesCount++;

            // if player rolls doubles 3x, they get sent to jail
            if (doublesCount == 3)
            {

                System.out.println(currentPlayer.getPlayerName() + " rolled a doubles three times in a row! They get sent to jail." );
                return;

            }

        }

        System.out.println(currentPlayer.getPlayerName() + " rolled: " + rollAmount);

        currentTile(rollAmount);

        // TODO: Move player on board, handle special tiles, etc.
        // For now just show what they rolled
    }

    // Main game loop
    public static void main(String[] args)
    {
        cards.shuffle();
        System.out.println("Welcome to Monopoly!");

        while(!players.isGameOver()) 
        {

            playerTurn();

            int menuChoice = 0;

            while (menuChoice != 3)
            {
                menuChoice = input.getInt("\n=== MONOPOLY MENU ===\n" +
                        "[1] Check Player Stats\n" +
                        "[2] Upgrade Property\n" +
                        "[3] End Turn\n" +
                        "[4] Exit for everyone\n\n");

                switch (menuChoice)
                {

                    case 1:

                        checkPlayerStats();
                        break;

                    case 2:

                        break;

                    case 3:

                        break;

                    case 4:

                        System.out.println("Thanks for playing Monopoly! Bye!");
                        break;

                    default:

                        System.out.println("Invalid choice. Try again.");

                }
            }

            players.nextTurn();

        }
    }
}