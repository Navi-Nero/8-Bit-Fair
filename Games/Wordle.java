package Games;

import Games.Wordle_Assets.Process_Wordle;

// Entry point for the Wordle game
// Extends Process_Wordle to access all the game logic
public class Wordle extends Process_Wordle 
{
    public static void main(String[] args) 
    {

        System.out.println("\nWelcome to Wordle! How would you like to start?");

        boolean restart = true;
        
        // Keep playing until player quits
        while (restart) 
        {

            System.out.println("1. Have another player enter the word to guess.");
            System.out.println("2. Generate a random word.");
            
            // Let player pick the word source
            String guessWord = Process_Wordle.Process.chooseMode();
            
            // Run the actual game
            Process_Wordle.Process.prepareGame(guessWord);

            // Ask if they want another round
            restart = Process_Wordle.Verify.restartGame();
            
        }
    }
}