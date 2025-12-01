package Games.Poker_Assets;
import java.util.*;

// Checks what poker hand you got
// Returns a number representing the hand type
public class HandChecker {

    /*******************************
     * 
        High card: The lowest hand. Just whatever your highest card is (ace kigh, king high)

        One pair: You have two of the same card (two 2s, two aces, etc)

        Two pair: You have two different pairs (two 2s and two aces)

        Three of a Kind: You have three of the same card (three kings, three 2s, etc)

        Straight: All cards are in order (2,3,4,5,6 or 8,9,10,J,Q)

        Flush: All cards are the same suit (All hearts, all spades)

        Full House: A three of a kind along with a pair (three kings and two 9s, three aces and two queens)

        Four of a Kind: Four of the same card (four 7s, four kings)

        Straight Flush: Cards are sequential, and are all the same suit (7,8,9,10,J all hearts)

        Royal Flush: A straight flush that consists of 10,J,Q,K,A. Not really a separate hand, but it still trumps other straight flushes.
        
     * 
     *******************************/

    // Figures out what hand type the player has
    // hand - the 5 cards to check
    // returns 0-9, where 9 = losing hand
    public int evaluateHand(List<Card> hand) {
        if (isRoyalPair(hand)) return 0;
        if (isTwoPair(hand)) return 1;
        if (isThreeOfAKind(hand)) return 2;
        if (isStraight(hand)) return 3;
        if (isFlush(hand)) return 4;
        if (isFullHouse(hand)) return 5;
        if (isFourOfAKind(hand)) return 6;
        if (isStraightFlush(hand)) return 7;
        if (isRoyalFlush(hand)) return 8;
        return 9; // Losing hand
    }

    // Sorts the hand by card rank (lowest to highest)
    private void sortByNumber(List<Card> hand) {
        Card[] anArray = new Card[hand.size()];
        
        for (int i = 0; i < anArray.length; i++) {
            anArray[i] = (Card) hand.get(i);
        }

        int n, j, min_j;
        for (n = 0; n < anArray.length; n++) {
            min_j = n;
            for (j = n + 1; j < anArray.length; j++) {
                if (anArray[j].getRank() < anArray[min_j].getRank())
                    min_j = j;
            }
            Card tempCard = anArray[n];
            anArray[n] = anArray[min_j];
            anArray[min_j] = tempCard;
        }

        hand.clear();
        for (int k = 0; k < anArray.length; k++) {
            hand.add(anArray[k]);
        }
    }

    // Sorts the hand by suit (lowest to highest)
    private void sortBySuit(List<Card> hand) {
        Card[] anArray = new Card[hand.size()];
        
        for (int i = 0; i < anArray.length; i++) {
            anArray[i] = (Card) hand.get(i);
        }

        int n, j, min_j;
        for (n = 0; n < anArray.length; n++) {
            min_j = n;
            for (j = n + 1; j < anArray.length; j++) {
                if (anArray[j].getSuit() < anArray[min_j].getSuit())
                    min_j = j;
            }
            Card tempCard = anArray[n];
            anArray[n] = anArray[min_j];
            anArray[min_j] = tempCard;
        }

        hand.clear();
        for (int i = 0; i < anArray.length; i++) {
            hand.add(anArray[i]);
        }
    }

    private boolean isOnePair(List<Card> hand) {
        sortByNumber(hand);
        Card[] anArray = new Card[hand.size()];
        
        for (int i = 0; i < anArray.length; i++) {
            anArray[i] = (Card) hand.get(i);
        }
        
        // count pairs
        int pairs = 0;
        int count = 0;

        for (int j = 1; j < 13; j++) {
            for (int i = 0; i < 5; i++) {
                if (anArray[i].getRank() == j) {
                    count++;
                }
            }
            if (count == 2) pairs++;
            count = 0;
        }
        return pairs == 1;
    }

    private boolean isRoyalPair(List<Card> hand) {
        sortByNumber(hand);
        Card[] anArray = new Card[hand.size()];
        
        for (int i = 0; i < anArray.length; i++) {
            anArray[i] = (Card) hand.get(i);
        }
        
        // count pairs in high cards (J, Q, K)
        int pairs = 0;
        int count = 0;

        for (int j = 11; j < 14; j++) {
            for (int i = 0; i < 5; i++) {
                if (anArray[i].getRank() == j) {
                    count++;
                }
            }
            if (count == 2) pairs++;
            count = 0;
        }
        return pairs == 1;
    }

    private boolean isTwoPair(List<Card> hand) {
        sortByNumber(hand);
        Card[] anArray = new Card[hand.size()];
        
        for (int i = 0; i < anArray.length; i++) {
            anArray[i] = (Card) hand.get(i);
        }
        
        if (anArray.length != 5)
            return false;
        
        // check if there's exactly 2 pairs
        boolean a1, a2, a3;

        // pattern: pair, pair, single
        a1 = anArray[0].getRank() == anArray[1].getRank() &&
             anArray[2].getRank() == anArray[3].getRank() &&
             anArray[4].getRank() != anArray[0].getRank() &&
             anArray[4].getRank() != anArray[2].getRank();

        // pattern: pair, single, pair
        a2 = anArray[0].getRank() == anArray[1].getRank() &&
             anArray[3].getRank() == anArray[4].getRank() &&
             anArray[2].getRank() != anArray[1].getRank() &&
             anArray[2].getRank() != anArray[3].getRank();

        // pattern: single, pair, pair
        a3 = anArray[1].getRank() == anArray[2].getRank() &&
             anArray[3].getRank() == anArray[4].getRank() &&
             anArray[0].getRank() != anArray[1].getRank() &&
             anArray[0].getRank() != anArray[3].getRank();
        
        return (a1 || a2 || a3);
    }

    private boolean isThreeOfAKind(List<Card> hand) {
        sortByNumber(hand);
        Card[] anArray = new Card[hand.size()];
        
        for (int i = 0; i < anArray.length; i++) {
            anArray[i] = (Card) hand.get(i);
        }
        
        // check for pairs   
        boolean a1, a2;

        if (anArray.length != 5)
            return false;

        // three in a row at start
        a1 = anArray[0].getRank() == anArray[1].getRank() &&
             anArray[1].getRank() == anArray[2].getRank();

        // three in a row at end
        a2 = anArray[2].getRank() == anArray[3].getRank() &&
             anArray[3].getRank() == anArray[4].getRank();

        return (a1 || a2);
    }

    private boolean isStraight(List<Card> hand) {
        sortByNumber(hand);
        Card[] anArray = new Card[hand.size()];
        
        for (int i = 0; i < anArray.length; i++) {
            anArray[i] = (Card) hand.get(i);
        }

        // check if each card is 1 higher than the previous
        int testRank = anArray[0].getRank() + 1;

        for (int i = 1; i < 5; i++) {
            if (anArray[i].getRank() != testRank)
                return false;
            testRank++;
        }

        return true;
    }

    private boolean isFlush(List<Card> hand) {
        Card[] anArray = new Card[hand.size()];
        
        for (int i = 0; i < anArray.length; i++) {
            anArray[i] = (Card) hand.get(i);
        }
        
        // count matching suits - 10 matches = all 5 cards same suit
        int count = 0;
        for (int i = 0; i < anArray.length; i++) {
            for (int j = i + 1; j < anArray.length; j++) {
                if (anArray[i].getSuit() == (anArray[j].getSuit())) {
                    count++;
                }
            }
        }
        return count == 10;
    }

    private boolean isFullHouse(List<Card> hand) {
        return isOnePair(hand) && isThreeOfAKind(hand);
    }

    private boolean isFourOfAKind(List<Card> hand) {
        sortByNumber(hand);
        Card[] anArray = new Card[hand.size()];
        
        for (int i = 0; i < anArray.length; i++) {
            anArray[i] = (Card) hand.get(i);
        }
        
        // check for pairs   
        boolean a1, a2;

        if (anArray.length != 5)
            return false;

        // 4 in a row at start
        a1 = anArray[0].getRank() == anArray[1].getRank() &&
             anArray[1].getRank() == anArray[2].getRank() &&
             anArray[2].getRank() == anArray[3].getRank();

        // 4 in a row at end
        a2 = anArray[1].getRank() == anArray[2].getRank() &&
             anArray[2].getRank() == anArray[3].getRank() &&
             anArray[3].getRank() == anArray[4].getRank();

        return (a1 || a2);
    }

    // straight AND flush = straight flush
    private boolean isStraightFlush(List<Card> hand) {
        if (hand.size() != 5)
            return false;
        return isStraight(hand) && isFlush(hand);
    }

    // Royal flush = A K Q J 10 all same suit (the best hand)
    private boolean isRoyalFlush(List<Card> hand) {
        if (hand.size() != 5)
            return false;
        Card[] anArray = new Card[hand.size()];
        
        for (int i = 0; i < anArray.length; i++) {
            anArray[i] = (Card) hand.get(i);
        }
        
        // check for A K Q J 10
        if (anArray[0].getRank() == 1 && anArray[1].getRank() == 10 && 
            anArray[2].getRank() == 11 && anArray[3].getRank() == 12 && 
            anArray[4].getRank() == 13) 
            return true;
        return false;
    }
}
