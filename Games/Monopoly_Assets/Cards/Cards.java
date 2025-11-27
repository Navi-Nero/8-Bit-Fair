package Games.Monopoly_Assets.Cards;

import java.util.Collections;
import java.util.LinkedList;

/********************************************************************************************
 * 
 * import java.util.Collections allow us to use method such as:
 * 
 * sort(List) – Sorts in ascending order.
 * reverse(List) – Reverses the list order.
 * shuffle(List) – Randomizes order.
 * max(Collection) – Finds the largest element.
 * min(Collection) – Finds the smallest element.
 * frequency(Collection, Object) – Counts occurrences.
 * replaceAll(List, oldVal, newVal) – Replaces all matching elements.
 * swap(List, i, j) – Swaps two elements.
 * rotate(List, distance) – Rotates elements.
 * unmodifiableList(List) – Creates a read-only list.
 * 
 * import java.util.LinkedList allow us to use method such as:
 * 
 * add(index, object) – Adds at index.
 * addFirst(object) Adds at the beginning.
 * addLast(object) – Adds at the end.
 * remove(index) – Removes element at index.
 * removeFirst() – Removes first element.
 * removeLast() – Removes last element.
 * get(index) – Returns element at index.
 * getFirst(object) – Returns first element.
 * getLast(object) – Returns last element.
 * contains(object) – Checks if element exist.
 * size() – Returns the size of the list.
 * set(index, object) – Updates element at index
 * indexOf(object) – Finds index of element
 * 
 ********************************************************************************************/ 

public class Cards 
{
    /*******************************************************
     * We use LinkedList since we need to shuffle the cards
     * Which falls under data manipulation
     * If that wasnt the case then we wouldve use ArrayList
     * Since that only deals with storing and displaying
    *******************************************************/

    private LinkedList<Cards_Constructor> chance_Card_Deck;
    private LinkedList<Cards_Constructor> community_Chest_Card_Deck;

    public Cards() 
    {

        chance_Card_Deck = new LinkedList<>();
        community_Chest_Card_Deck = new LinkedList<>();

        // Chance Cards
        chance_Card_Deck.add(new Cards_Constructor("Advance to Boardwalk", false));
        chance_Card_Deck.add(new Cards_Constructor("Advance to Go (Collect $200)", false));
        chance_Card_Deck.add(new Cards_Constructor("Advance to Illinois Avenue", false));
        chance_Card_Deck.add(new Cards_Constructor("Advance to St. Charles Place", false));
        chance_Card_Deck.add(new Cards_Constructor("Advance to nearest Railroad", false));
        chance_Card_Deck.add(new Cards_Constructor("Advance to nearest Utility", false));
        chance_Card_Deck.add(new Cards_Constructor("Bank pays you dividend of $50", false));  
        chance_Card_Deck.add(new Cards_Constructor("Go back 3 spaces", false));
        chance_Card_Deck.add(new Cards_Constructor("Go to Jail", false));
        chance_Card_Deck.add(new Cards_Constructor("Make general repairs on all your property", false));
        chance_Card_Deck.add(new Cards_Constructor("Speeding fine $15", false));
        chance_Card_Deck.add(new Cards_Constructor("Take a trip to Reading Railroad", false));
        chance_Card_Deck.add(new Cards_Constructor("Take a walk on the Boardwalk", false));
        chance_Card_Deck.add(new Cards_Constructor("You have been elected Chairman of the Board", false));
        chance_Card_Deck.add(new Cards_Constructor("Your building loan matures (Collect $150)", false));

        chance_Card_Deck.add(new Cards_Constructor("Get out of Jail Free", true));


        // Community Chest Cards
        community_Chest_Card_Deck.add(new Cards_Constructor("Advance to Go", false));
        community_Chest_Card_Deck.add(new Cards_Constructor("Bank error in your favor (Collect $200)", false));
        community_Chest_Card_Deck.add(new Cards_Constructor("Doctor's fees (Pay $50)", false));
        community_Chest_Card_Deck.add(new Cards_Constructor("From sale of stock you get $50", false));
        community_Chest_Card_Deck.add(new Cards_Constructor("Go to Jail", false));
        community_Chest_Card_Deck.add(new Cards_Constructor("Grand Opera Night (Collect $50 from every player)", false));
        community_Chest_Card_Deck.add(new Cards_Constructor("Holiday Fund matures (Collect $100)", false));
        community_Chest_Card_Deck.add(new Cards_Constructor("Income tax refund (Collect $20)", false));
        community_Chest_Card_Deck.add(new Cards_Constructor("It is your birthday (Collect $10 from every player)", false));
        community_Chest_Card_Deck.add(new Cards_Constructor("Life insurance matures (Collect $100)", false));
        community_Chest_Card_Deck.add(new Cards_Constructor("Pay hospital fees of $100", false));
        community_Chest_Card_Deck.add(new Cards_Constructor("Pay school fees of $50", false));
        community_Chest_Card_Deck.add(new Cards_Constructor("Receive $25 consultancy fee", false));
        community_Chest_Card_Deck.add(new Cards_Constructor("You are assessed for street repairs", false));
        community_Chest_Card_Deck.add(new Cards_Constructor("You have won second prize in a beauty contest (Collect $10)", false));
        community_Chest_Card_Deck.add(new Cards_Constructor("You inherit $100", false));

        community_Chest_Card_Deck.add(new Cards_Constructor("Get out of Jail Free", true));


    }

    public void shuffle() 
    {

        Collections.shuffle(chance_Card_Deck);
        Collections.shuffle(community_Chest_Card_Deck);

    }

    public Cards_Constructor draw_Chance_Card() 
    {

        Cards_Constructor card = chance_Card_Deck.removeFirst();

        if (!card.isKeepable()) 
        {
            chance_Card_Deck.addLast(card); 
        }

        return card;

    }

    public Cards_Constructor draw_Community_Chest_Card() 
    {

        Cards_Constructor card = community_Chest_Card_Deck.removeFirst();

        if (!card.isKeepable()) 
        {
            community_Chest_Card_Deck.addLast(card);
        }
        
    return card;

    }

    public void return_Chance_Card(Cards_Constructor card) 
    {
        chance_Card_Deck.addLast(card);
    }

    public void return_Community_Chest_Card(Cards_Constructor card) 
    {
        community_Chest_Card_Deck.addLast(card);
    }
}