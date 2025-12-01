import Games.Input_Handling;

public class Eight_Bit_Fare
{
    public static void main(String[] args)
    {

        int gameChoice = 0;

        System.out.println("\n╔══════════════════════════════╗");
        System.out.println("║     WELCOME TO 8-BIT FAIR    ║");
        System.out.println("╚══════════════════════════════╝");

        while (gameChoice != 4)
        {

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
                    Games.Poker.main(args);     // Starts Poker
                    break;

                case 2:

                    System.out.println("\nStarting Monopoly...");
                    System.out.println("Welcome to Monopoly! A game which simulates capitalism! (Requires atleast 2 players)");
                    Games.Monopoly.main(args);  // Starts Monopoly
                    break;

                case 3:

                    System.out.println("\nStarting Wordle...\n");
                    Games.Wordle.main(args);    // Starts Wordle
                    break;

                case 4:

                    System.out.println("\nThanks for playing! See you next time!");
                    menuInput.close(); // Exits
                    break;

                default:
                    System.out.println("Invalid choice. Try again.");
            }
        }
    }
}