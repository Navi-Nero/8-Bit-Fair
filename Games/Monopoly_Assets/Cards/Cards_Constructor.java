package Games.Monopoly_Assets.Cards;

public class Cards_Constructor 
{
    // Initialized Parameters
    private String card_Description;
    private boolean card_Can_Keep;

    // Constructor 
    Cards_Constructor(String description, boolean keep)
    {

        this.card_Description = description;
        this.card_Can_Keep = keep;

    }
    
    public String getDescription() 
    {
        return card_Description;
    }

    public boolean isKeepable()
    {
        return card_Can_Keep;
    }
}