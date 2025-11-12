/* Handles the complex proccesses
   Static methods are utilized */

package Games.Wordle_Assets;
import Games.Input_Handling;

public class Process_Wordle {
    private final static Input_Handling input = new Input_Handling();

    protected class Verify {

        public static boolean checkString (String verify, String guess) {
            boolean boolResult = false;
            int length = guess.length();
            
            if (verify.length() != length) {
                System.out.println("\nError! Word is not " + length + " letters! ");

            } else if (verify.matches("^[A-Za-z]+$") == false) {
                System.out.println("\nError! Word contains non letter characters!");

            } else {
                boolResult = true;
            }

            return boolResult;
        }

        public static void isCorrect (String word, String answer) {
            final String green = "\u001B[32m\u25A0\u001B[0m ";
            final String yellow = "\u001B[33m\u25A0\u001B[0m ";
            final String empty = "\u001B[30m\u25A0\u001B[0m ";

            int length = word.length();
            String[] result = new String[length];
            boolean[] correctPos = new boolean[length];
            boolean[] correctLet = new boolean[length];

            for (int i = 0; i < length; i++) {
                if (answer.charAt(i) == word.charAt(i)) {
                    result[i] = green;
                    correctPos[i] = true;
                    correctLet[i] = true;
                }
            }

            for (int i = 0; i < length; i++) {
                if (!correctLet[i]) {
                    for (int j = 0; j < length; j++) {
                        if (!correctPos[j] && answer.charAt(i) == word.charAt(j)){
                            result[i] = yellow;
                            correctPos[j] = true;
                            correctLet[i] = true;
                            break;
                        }
                    }
                }
            }

            for (int i = 0; i < length; i++) {
                if (!correctLet[i]) {
                    result[i] = empty;
                }
            }
            
            for (int i = 0; i < length; i++) {
                System.out.print(result[i]);
            }

        System.out.println();
        }

        public static int gameState (String answer, String word, int tries) {
        if (answer.contentEquals(word)) {
                System.out.println("\nWINNER WINNER CHICKEN DINNER! ");

            } else if (tries == 0) {
                System.out.println("\nClose, but no cigar! The correct word was \"" + word + "\"!"); 

            } else {
                System.out.print("\nGuess the word! You got " + tries + " tries left!: ");
                isCorrect(word, answer);
                return tries;
            }

            return 0;
        }
    
    public static boolean restartGame () {
            System.out.println("\nSplendid! Wanna play again? <yes/no>");

            while (true) {
                String response = input.getStr("").trim();
                response = response.toUpperCase();

                switch (response) {
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

    protected class Proccess {
        private final static Words word = new Words();
        
        public static String chooseMode () {
            int ans = input.getInt("");

            String chosenWord = "";

            switch (ans){
                case 1:

                do {
                    chosenWord = input.getStr("\nWhat do you want your friend to guess?\n");
                    chosenWord = chosenWord.toUpperCase().trim();
                    System.out.print("\033[1A\033[2K");

                } while (Verify.checkString(chosenWord, chosenWord) != true);

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

        public static void prepareGame(String guessWord) {
            int attempts = input.getInt("\nHow many guesses you want to have?\n");
            String placeHolder = "";

            for (int i = 0; i <= guessWord.length(); i++){
                placeHolder = placeHolder.concat("a");
            }

            String answer = placeHolder;

            System.out.print("\nGuess the word! You have " + attempts + " tries left!: ");
            Verify.isCorrect(guessWord, answer);

            while (attempts != 0) {

                answer = input.getStr("").trim();
                answer = answer.toUpperCase();

                if (Verify.checkString(answer, guessWord) == true) {
                    attempts--;

                } else {
                    answer = placeHolder;
                }

                attempts = Verify.gameState(answer, guessWord, attempts);
            }
        }

        public static void checkAlpha (String keyword) {
        }
    }
}