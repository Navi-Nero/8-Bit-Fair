

package Games.Wordle_Assets;
import Games.Input_Handling;

// Handles all the game logic for Wordle
// Contains static inner classes for verification, game flow, and word picking
public class Process_Wordle {
    private final static Input_Handling input = new Input_Handling();

    // Verifies guesses and checks if they're valid words
    public static class Verify 
    {

        public static boolean checkString (String verify, String guess, boolean is_User_Input) 
        {

            int length = guess.length();

            if (!is_User_Input)
            {
                boolean is_Alpha_Check = Process.checkAlpha(verify);
                
                if (is_Alpha_Check)
                {
                    return false;
                }
            }
            
            if (verify.length() != length) 
            {

                System.out.println("\nError! Word is not " + length + " letters! ");

            } else if (!verify.matches("^[A-Za-z]+$")) 
            {

                System.out.println("\nError! Word contains non letter characters!");

            } else {

                return true;

            }

            return false;   
        }

        public static void isCorrect (String word, String answer) 
        {

            final String green = "\u001B[32m\u25A0\u001B[0m ";   // correct letter, correct position
            final String yellow = "\u001B[33m\u25A0\u001B[0m ";  // correct letter, wrong position
            final String empty = "\u001B[30m\u25A0\u001B[0m ";   // letter not in word

            int length = word.length();
            String[] result = new String[length];
            boolean[] correctPos = new boolean[length];
            boolean[] correctLet = new boolean[length];

            for (int i = 0; i < length; i++) 
            {
                if (answer.charAt(i) == word.charAt(i)) 
                {

                    result[i] = green;
                    correctPos[i] = true;
                    correctLet[i] = true;

                }
            }

            for (int i = 0; i < length; i++) 
            {

                if (!correctLet[i]) 
                {

                    for (int j = 0; j < length; j++) 
                    {

                        if (!correctPos[j] && answer.charAt(i) == word.charAt(j))
                        {

                            result[i] = yellow;
                            correctPos[j] = true;
                            correctLet[i] = true;
                            break;

                        }
                    }
                }
            }

            for (int i = 0; i < length; i++) 
            {
                
                if (!correctLet[i]) 
                {
                    result[i] = empty;
                }

            }
            
            for (int i = 0; i < length; i++) {
                System.out.print(result[i]);
            }

        System.out.println();
        }

        public static int gameState (String answer, String word, int tries) {
        if (answer.contentEquals(word)) 
            {

                System.out.println("\nWINNER WINNER CHICKEN DINNER! ");
                
            } else if (tries == 0) 
            {

                System.out.println("\nClose, but no cigar! The correct word was \"" + word + "\"!"); 

            } else {

                System.out.print("\nGuess the word! You got " + tries + " tries left!: ");
                isCorrect(word, answer);
                return tries;
            }

            return 0;
        }
    
        // Ask if the player wants to play another round
        public static boolean restartGame () 
        {
            System.out.println("\nSplendid! Wanna play again? <yes/no>");

            while (true) 
            {

                String response = input.getStr("").trim();
                response = response.toUpperCase();

                switch (response) 
                {

                    case "YES":

                        System.out.println("\nWOHOOO! How do you wanna start the game this time?");

                    return true;

                    case "NO":

                        System.out.println("\nAww! Until next time!");
                        input.close();

                    return false;

                    default:

                        System.out.println("Invalid Input!");

                    break;
                }
            }
        }
    }

    // Handles game setup and main game loop
    public static class Process 
    {

        private static final Words word = new Words();
        private static final String[] alphabet = {"A","B","C","D","E","F","G","H","I","J","K","L","M","N","O","P","Q","R","S","T","U","V","W","X","Y","Z"};
        private static String[] unUsedLetters = alphabet.clone();  // Track which letters player has used

        // Player picks how to start - enter their own word or use a random one
        public static String chooseMode () 
        {
            int ans = input.getInt("");

            String chosenWord = "";

            switch (ans)
            {
                case 1:

                do {
                    chosenWord = input.getStr("\nWhat do you want your friend to guess?\n");
                    chosenWord = chosenWord.toUpperCase().trim();
                    System.out.print("\033[1A\033[2K");

                } while (Verify.checkString(chosenWord, chosenWord, true) != true);

                System.out.println("Aight, got it!");
                break;

                case 2:
                chosenWord = word.getword();
                break;

                default:
                System.out.println("Invalid Input!");
            }

            return chosenWord;
        }

        public static void prepareGame(String guessWord) 
        {
            int attempts = input.getInt("\nHow many guesses you want to have?\n");
            String placeHolder = "a".repeat(guessWord.length());

            String answer = placeHolder;

            System.out.print("\nGuess the word! You have " + attempts + " tries left!: ");
            Verify.isCorrect(guessWord, answer);
            System.out.print("You can type the word \"ALPHABET\" anytime to see what letters you haven't used yet. \n");
            System.arraycopy(alphabet, 0, unUsedLetters, 0, 26);


            while (attempts != 0) 
            {

                answer = input.getStr("").trim();
                answer = answer.toUpperCase();

                if (Verify.checkString(answer, guessWord, false) == true) 
                {

                    attempts--;

                } else {

                    answer = placeHolder;

                }

                attempts = Verify.gameState(answer, guessWord, attempts);

            }
        }

        //Checks if the input was "ALPHABET" and if it is, prints all unused letters
        // Player can type "ALPHABET" to see which letters they haven't used yet
        public static boolean checkAlpha (String keyword) 
        {

            if(keyword.equals("ALPHABET"))
            {

                System.out.print("\nLetters you have not used are: \n");

                for (int i = 0; i < 26; i++)
                {
                    System.out.print(unUsedLetters[i] + " ");
                }

                System.out.println();
                return true;

            } else {

                char[] newLetters = keyword.toCharArray();

                for (char c : newLetters) 
                {

                    for (int j = 0; j < 26; j++) 
                    {

                        if (c == alphabet[j].charAt(0)) 
                        {
                            unUsedLetters[j] = "-";
                        }
                    }
                }

                return false;

            }
        }
    }
}