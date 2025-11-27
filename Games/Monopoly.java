package Games;

import Games.Monopoly_Assets.Board;

// Entry point for the Monopoly game
// Just kicks off the main board game loop
public class Monopoly 
{
    // Console color codes for the property colors
    final static String RED = "\u001B[31m\u25A0\u001B[0m";
    final static String CYAN = "\u001B[96m\u25A0\u001B[0m";
    final static String MAGENTA = "\u001B[95m\u25A0\u001B[0m";
    final static String YELLOW = "\u001B[33m\u25A0\u001B[0m";
    final static String LIGHT_RED = "\u001B[91m\u25A0\u001B[0m";
    final static String LIGHT_YELLOW = "\u001B[93m\u25A0\u001B[0m";
    final static String GREEN = "\u001B[92m\u25A0\u001B[0m";
    final static String BLUE = "\u001B[94m\u25A0\u001B[0m";

    // Background colors for properties
    final static String RED_BG = "\u001B[41m\u25A0\u001B[0m";
    final static String CYAN_BG = "\u001B[106m\u25A0\u001B[0m";
    final static String MAGENTA_BG = "\u001B[105m\u25A0\u001B[0m";
    final static String YELLOW_BG = "\u001B[43m\u25A0\u001B[0m";
    final static String LIGHT_RED_BG = "\u001B[101m\u25A0\u001B[0m";
    final static String LIGHT_YELLOW_BG = "\u001B[103m\u25A0\u001B[0m";
    final static String GREEN_BG = "\u001B[102m\u25A0\u001B[0m";
    final static String BLUE_BG = "\u001B[104m\u25A0\u001B[0m";

    // Start the monopoly game
    public static void main(String[] args) 
    {
        // Show the colors available for properties
        System.out.println("=== MONOPOLY ===");
        System.out.println("Available property colors:");
        System.out.println(RED + CYAN + MAGENTA + YELLOW + LIGHT_RED + LIGHT_YELLOW + GREEN + BLUE);
        System.out.println();

        // Start the actual game
        Board.main(args);
    }
}
