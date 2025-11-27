package Games.Poker_Assets;
import java.util.*;

// Takes care of all the console stuff - printing to screen, getting input
public class UserInterface {

    private Scanner scanner;
    private PayoutManager payoutManager;

    public UserInterface(PayoutManager payoutManager) {
        this.scanner = new Scanner(System.in);
        this.payoutManager = payoutManager;
    }

    // Show the payout table
    public void showPayoutTable() {
        payoutManager.displayPayoutTable();
    }

    // Ask if player wants to see the payout table
    public boolean askShowPayoutTable() {
        boolean loop = true;
        boolean again = true;
        
        while (loop) {
            System.out.println("Show payout table? (y or n)?");
            String response = scanner.next();

            if (response.equalsIgnoreCase("y")) {
                again = true;
                loop = false;
            } else if (response.equalsIgnoreCase("n")) {
                again = false;
                loop = false;
            } else {
                System.out.println("Sorry, I didn't catch that. Please answer y/n");
            }
        }
        return again;
    }

    // Ask player how much they want to bet
    public int placeBet(int maxBet) {
        boolean loop = true;
        int bet = 0;
        
        while (loop) {
            System.out.print("Enter bet: ");
            try {
                bet = scanner.nextInt();
                if (bet > 0 && bet <= maxBet) {
                    loop = false;
                } else {
                    System.out.println("Insufficient funds. Enter a smaller amount.");
                }
            } catch (InputMismatchException e) {
                System.out.println("Invalid input. Please enter a number.");
                scanner.nextLine(); // Clear the invalid input
            }
        }
        return bet;
    }

    // Print the hand to screen
    public void displayHand(List<Card> hand) {
        System.out.println("HAND: " + hand);
    }

    // Show the new hand after swap
    public void displayNewHand(List<Card> hand) {
        System.out.println("NEW HAND: " + hand);
    }

    // Show how much money the player has
    public void displayBalance(int balance) {
        System.out.println("Balance: $" + balance);
    }

    // Print what hand type the player got
    public void displayHandResult(int handType) {
        System.out.println(payoutManager.getHandTypeName(handType));
    }

    // Say goodbye and show final balance
    public void displayGoodbye(int balance) {
        System.out.println("Your balance: $" + balance + " Good bye!");
    }

    // Show updated balance after the round
    public void displayUpdatedBalance(int balance) {
        System.out.println("\nYour balance: $" + balance);
    }

    // Ask if player wants another round
    public boolean playAgain() {
        boolean loop = true;
        boolean again = true;
        
        while (loop) {
            System.out.println("One more game (y or n)?");
            String response = scanner.next();

            if (response.equalsIgnoreCase("y")) {
                again = true;
                loop = false;
            } else if (response.equalsIgnoreCase("n")) {
                again = false;
                loop = false;
            } else {
                System.out.println("Sorry, I didn't catch that. Please answer y/n");
            }
        }
        return again;
    }

    // Ask which cards to keep for the swap
    public List<Integer> selectCardsToKeep() {
        List<Integer> cardsToKeep = new ArrayList<>();
        boolean validInput = false;
        
        while (!validInput) {
            System.out.println("Enter positions of cards to keep (e.g. 1 4 5): ");
            scanner.nextLine(); // Clear the buffer
            String input = scanner.nextLine();
            
            if (input.isEmpty()) {
                return cardsToKeep; // empty = throw away all cards
            }
            
            String[] positions = input.split(" ");
            
            if (positions.length > 5) {
                System.out.println("Error, too many values. Choose up to 5 cards only.");
                continue;
            }
            
            boolean error = false;
            for (String pos : positions) {
                try {
                    int cardPos = Integer.parseInt(pos);
                    if (cardPos < 1 || cardPos > 5) {
                        System.out.println("Error, position number out of range. Choose 1 - 5");
                        error = true;
                        break;
                    }
                    cardsToKeep.add(cardPos);
                } catch (NumberFormatException e) {
                    System.out.println("Invalid input. Please enter numbers only.");
                    error = true;
                    break;
                }
            }
            
            if (!error) {
                validInput = true;
            }
        }
        
        return cardsToKeep;
    }

    // Show which cards are being kept
    public void displayHeldCards(List<Card> heldCards) {
        System.out.println("Held Cards: " + heldCards);
    }

    // Message when player threw away everything
    public void displayNoCardsHeld() {
        System.out.println("No cards held.");
    }

    // Clean up the scanner
    public void close() {
        scanner.close();
    }
}
