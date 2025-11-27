package Games.Poker_Assets;

// Handles all the money stuff - payouts, hand names, multipliers
public class PayoutManager {

    private static final int[] multipliers = {1, 2, 3, 5, 6, 9, 25, 50, 250};
    private static final String[] handTypes = {
        "Royal Pair", "Two Pairs", "Three of a Kind", "Straight", "Flush",
        "Full House", "Four of a Kind", "Straight Flush", "Royal Flush", "Sorry, you lost."
    };

    // Get multiplier for a hand (how much you win)
    public int getMultiplier(int handType) {
        return multipliers[handType];
    }

    // Get the name of a hand (like "Royal Flush", "Pair", etc)
    public String getHandTypeName(int handType) {
        return handTypes[handType];
    }

    // Returns all the multipliers
    public int[] getMultipliers() {
        return multipliers.clone();
    }

    // Returns all hand type names
    public String[] getHandTypes() {
        return handTypes.clone();
    }

    // Calculate how much money you win or lose
    // bet = amount you bet
    // handType = what hand you got (0-9)
    // returns positive for win, negative for loss
    public int calculatePayout(int bet, int handType) {
        // lost the hand
        if (handType == 9) {
            return -bet;
        }
        return bet * multipliers[handType];
    }

    // Shows the payout table on screen
    public void displayPayoutTable() {
        System.out.println("\n\n");
        System.out.println("Payout Table            Multiplier   ");
        System.out.println("=======================================");
        for (int i = handTypes.length - 1; i >= 0; i--) {
            System.out.println(String.format("%-20s |\t%d", handTypes[i], multipliers[i]));
        }
        System.out.println("\n-----------------------------------");
    }
}
