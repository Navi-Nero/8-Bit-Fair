package Games.Poker_Assets;
import java.util.*;

// Main poker game
// Coordinates the game flow - deals cards, manages turns, checks wins
public class MyPokerGame {

    // default constant values
    private static final int startingBalance = 500;
    private static final int numberOfCards = 5;

    // Game components
    private Decks deck;
    private PlayerState player;
    private HandChecker handChecker;
    private PayoutManager payoutManager;
    private UserInterface ui;
    
    private boolean playing;

    /** default constructor, set balance = startingBalance */
    public MyPokerGame() {
        this(startingBalance);
    }

    /** constructor, set given balance */
    public MyPokerGame(int balance) {
        this.player = new PlayerState(balance);
        this.handChecker = new HandChecker();
        this.payoutManager = new PayoutManager();
        this.ui = new UserInterface(payoutManager);
        this.deck = new Decks(1);
        this.playing = true;
    }

    // Show the payout table
    private void showPayoutTable() {
        ui.showPayoutTable();
    }

    private void askShowPayoutTable() {
        if (ui.askShowPayoutTable()) {
            showPayoutTable();
        }
    }

    // Check what hand the player has and print it
    private int checkHands(List<Card> hand) {
        int result = handChecker.evaluateHand(hand);
        ui.displayHandResult(result);
        return result;
    }

    private void payOut(int payOutType) {
        int payout = payoutManager.calculatePayout(player.getBet(), payOutType);
        player.adjustBalance(payout);
    }

    // Main game loop
    public void play() {
        showPayoutTable();
        Decks gameDeck = new Decks(1);
        
        while (playing) {
            ui.displayBalance(player.getBalance());
            
            int bet = ui.placeBet(player.getBalance());
            player.setBet(bet);
            
            gameDeck.shuffle();
            
            // deal 5 cards
            try {
                List<Card> hand = new ArrayList<Card>(gameDeck.deal(5));
                player.setCurrentHand(hand);
            } catch (PlayingCardException e) {
                e.printStackTrace();
            }
            
            ui.displayHand(player.getCurrentHand());
            
            // player picks cards to swap out
            List<Card> newHand = discardAndDeal(gameDeck);
            player.setCurrentHand(newHand);
            
            ui.displayNewHand(player.getCurrentHand());
            
            // check hand and handle winnings
            payOut(checkHands(player.getCurrentHand()));
            
            player.clearHand();
            ui.displayUpdatedBalance(player.getBalance());
            
            // end of round, play again?
            if (player.canContinue() && ui.playAgain()) {
                if (ui.askShowPayoutTable()) {
                    showPayoutTable();
                }
            } else {
                ui.displayGoodbye(player.getBalance());
                playing = false;
            }
        }
    }

    // Handle card swap - player keeps some, gets new ones
    private List<Card> discardAndDeal(Decks gameDeck) {
        List<Card> heldCards = new ArrayList<>();
        List<Integer> cardsToKeep = ui.selectCardsToKeep();
        
        if (cardsToKeep.isEmpty()) {
            // threw away everything
            ui.displayNoCardsHeld();
            try {
                heldCards.addAll(gameDeck.deal(5));
            } catch (PlayingCardException e) {
                e.printStackTrace();
            }
        } else {
            // keep these cards
            for (int position : cardsToKeep) {
                heldCards.add(player.getCardFromHand(position - 1));
            }
            ui.displayHeldCards(heldCards);
            
            // get new cards for the ones we threw away
            try {
                heldCards.addAll(gameDeck.deal(5 - cardsToKeep.size()));
            } catch (PlayingCardException e) {
                e.printStackTrace();
            }
        }
        
        return heldCards;
        
    }     
}
