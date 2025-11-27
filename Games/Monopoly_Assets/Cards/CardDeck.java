package Games.Monopoly_Assets.Cards;

import java.util.Collections;
import java.util.LinkedList;

// Manages the card decks for Chance and Community Chest
// We use LinkedList because we need to shuffle and pop cards frequently
public class CardDeck {
    private LinkedList<CardData> chanceDeck;
    private LinkedList<CardData> communityChestDeck;

    public CardDeck() {
        chanceDeck = new LinkedList<>();
        communityChestDeck = new LinkedList<>();

        // Chance cards
        chanceDeck.add(new CardData("Advance to Boardwalk", false));
        chanceDeck.add(new CardData("Advance to Go (Collect $200)", false));
        chanceDeck.add(new CardData("Advance to Illinois Avenue", false));
        chanceDeck.add(new CardData("Advance to St. Charles Place", false));
        chanceDeck.add(new CardData("Advance to nearest Railroad", false));
        chanceDeck.add(new CardData("Advance to nearest Utility", false));
        chanceDeck.add(new CardData("Bank pays you dividend of $50", false));  
        chanceDeck.add(new CardData("Go back 3 spaces", false));
        chanceDeck.add(new CardData("Go to Jail", false));
        chanceDeck.add(new CardData("Make general repairs on all your property", false));
        chanceDeck.add(new CardData("Speeding fine $15", false));
        chanceDeck.add(new CardData("Take a trip to Reading Railroad", false));
        chanceDeck.add(new CardData("Take a walk on the Boardwalk", false));
        chanceDeck.add(new CardData("You have been elected Chairman of the Board", false));
        chanceDeck.add(new CardData("Your building loan matures (Collect $150)", false));
        chanceDeck.add(new CardData("Get out of Jail Free", true));

        // Community chest cards
        communityChestDeck.add(new CardData("Advance to Go", false));
        communityChestDeck.add(new CardData("Bank error in your favor (Collect $200)", false));
        communityChestDeck.add(new CardData("Doctor's fees (Pay $50)", false));
        communityChestDeck.add(new CardData("From sale of stock you get $50", false));
        communityChestDeck.add(new CardData("Go to Jail", false));
        communityChestDeck.add(new CardData("Grand Opera Night (Collect $50 from every player)", false));
        communityChestDeck.add(new CardData("Holiday Fund matures (Collect $100)", false));
        communityChestDeck.add(new CardData("Income tax refund (Collect $20)", false));
        communityChestDeck.add(new CardData("It is your birthday (Collect $10 from every player)", false));
        communityChestDeck.add(new CardData("Life insurance matures (Collect $100)", false));
        communityChestDeck.add(new CardData("Pay hospital fees of $100", false));
        communityChestDeck.add(new CardData("Pay school fees of $50", false));
        communityChestDeck.add(new CardData("Receive $25 consultancy fee", false));
        communityChestDeck.add(new CardData("You are assessed for street repairs", false));
        communityChestDeck.add(new CardData("You have won second prize in a beauty contest (Collect $10)", false));
        communityChestDeck.add(new CardData("You inherit $100", false));
        communityChestDeck.add(new CardData("Get out of Jail Free", true));
    }

    // Randomize both decks
    public void shuffle() {
        Collections.shuffle(chanceDeck);
        Collections.shuffle(communityChestDeck);
    }

    // Draw from chance deck, put back at end if not keepable
    public CardData drawChanceCard() {
        CardData card = chanceDeck.removeFirst();

        if (!card.isKeepable()) {
            chanceDeck.addLast(card); 
        }

        return card;
    }

    // Draw from community chest deck, put back at end if not keepable
    public CardData drawCommunityChestCard() {
        CardData card = communityChestDeck.removeFirst();

        if (!card.isKeepable()) {
            communityChestDeck.addLast(card);
        }
        
        return card;
    }

    // Player returns a keepable card (like Get Out of Jail Free)
    public void returnChanceCard(CardData card) {
        chanceDeck.addLast(card);
    }

    // Player returns a keepable community chest card
    public void returnCommunityChestCard(CardData card) {
        communityChestDeck.addLast(card);
    }
}