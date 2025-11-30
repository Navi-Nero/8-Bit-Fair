package Games;

import Games.Wordle_Assets.WordleBackEnd;

// Entry point for the Wordle game
// Extends WordleBackEnd to access all the game logic
public class Wordle extends WordleBackEnd 
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
            
            // Let player game mode
            String guessWord = WordleBackEnd.Process.chooseMode();
            
            // Run the actual game
            WordleBackEnd.Process.prepareGame(guessWord);

            // Ask if they want another round
            restart = WordleBackEnd.Verify.restartGame();
            
        }
    }
}