import Games.Input_Handling;
import Games.Monopoly;
import Games.Poker;
import Games.Wordle;

// Main menu for the 8-Bit Fare arcade
// Lets you choose which game to play: Poker, Monopoly, or Wordle
public class Eight_Bit_Fare
{
    public static void main(String[] args)
    {
        int gameChoice = 0;

        System.out.println("\n╔════════════════════════════════════╗");
        System.out.println("║     WELCOME TO 8-BIT FAIR ARCADE   ║");
        System.out.println("╚════════════════════════════════════╝");

        while (gameChoice != 4)
        {
            // Create a fresh input handler for the menu
            Input_Handling menuInput = new Input_Handling();
            
            gameChoice = menuInput.getInt("\nWhich game do you wanna play?\n" +
                    "[1] Poker\n" +
                    "[2] Monopoly\n" +
                    "[3] Wordle\n" +
                    "[4] Exit\n\n");

            switch (gameChoice)
            {

                case 1:
                    System.out.println("\nStarting Poker...\n");
                    Poker.main(args);
                    break;

                case 2:
                    System.out.println("\nStarting Monopoly...");
                    System.out.println("Welcome to Monopoly! A game which simulates capitalism! (Requires atleast 2 players)");
                    Monopoly.main(args);
                    break;

                case 3:
                    System.out.println("\nStarting Wordle...\n");
                    Wordle.main(args);
                    break;

                case 4:
                    System.out.println("\nThanks for playing! See you next time!");
                    menuInput.close();
                    break;

                default:
                    System.out.println("Invalid choice. Try again.");
                    
            }
        }
    }
}