package Games;
import Games.Wordle_Assets.Process_Wordle;

public class Wordle extends Process_Wordle {

    public static void main(String[] args) {

        System.out.println("\nWelcome to wordle! How would you like to start the game?");

        boolean restart = true;
        
        while (restart == true) {

            System.out.println("1. Have another player enter the word to guess.");
            System.out.println("2. Generate a random word.");
            
            String guessWord = Process.chooseMode();
            
            Process.prepareGame(guessWord);

            restart = Verify.restartGame();
        }
    }
}