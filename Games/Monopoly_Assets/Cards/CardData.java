package Games.Monopoly_Assets.Cards;

// Represents a single card in Monopoly (Chance or Community Chest)
public class CardData {
    private String description;
    private boolean keepable;

    // Create a new card
    CardData(String description, boolean keepable) {
        this.description = description;
        this.keepable = keepable;
    }
    
    // Get what this card does
    public String getDescription() {
        return description;
    }

    // Check if player can hold onto this card
    public boolean isKeepable() {
        return keepable;
    }
}