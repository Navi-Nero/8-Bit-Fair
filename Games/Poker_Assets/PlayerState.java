package Games.Poker_Assets;
import java.util.*;

// Keeps track of player info - money, bet, cards in hand
public class PlayerState {

    private int balance;
    private int bet;
    private List<Card> currentHand;

    public PlayerState(int initialBalance) {
        this.balance = initialBalance;
        this.bet = 0;
        this.currentHand = new ArrayList<>();
    }

    // Get how much money player has
    public int getBalance() {
        return balance;
    }

    // Set the balance to a specific amount
    public void setBalance(int balance) {
        this.balance = balance;
    }

    // Add or subtract money from balance
    // amount can be negative to subtract
    public void adjustBalance(int amount) {
        this.balance += amount;
    }

    // Get the current bet
    public int getBet() {
        return bet;
    }

    // Set the bet amount
    public void setBet(int bet) {
        this.bet = bet;
    }

    // Get the 5 cards in hand
    public List<Card> getCurrentHand() {
        return currentHand;
    }

    // Set what cards the player has
    public void setCurrentHand(List<Card> hand) {
        this.currentHand = new ArrayList<>(hand);
    }

    // Throw away all cards
    public void clearHand() {
        this.currentHand.clear();
    }

    // Get a specific card from the hand
    public Card getCardFromHand(int index) {
        return currentHand.get(index);
    }

    // Check if player has enough money for the bet
    public boolean canAffordBet(int betAmount) {
        return betAmount > 0 && betAmount <= balance;
    }

    // Check if player still has money to play
    public boolean canContinue() {
        return balance > 0;
    }
}
