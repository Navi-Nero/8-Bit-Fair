package Games.Monopoly_Assets.Properties;

// Represents a single property on the board (street, railroad, utility, etc)
public class PropertyData 
{
    // Property details that don't change
    private int propertyIndex;
    private String propertyName;
    private String propertyType;
    private int propertyPrice;
    private int[] propertyRent;
    
    // Mutable property state
    private String propertyOwner;

    // Create a property with all its details
    PropertyData(int index, String name, String type, int price, int[] rent, String owner)
    {

        this.propertyIndex = index;
        this.propertyName = name;
        this.propertyType = type;
        this.propertyPrice = price;
        this.propertyRent = rent;
        this.propertyOwner = owner;
        
    }

    public int getPropertyIndex()
    {
        return propertyIndex;
    }

    public String getPropertyName()
    {
        return propertyName;
    }

    public String getPropertyType()
    {
        return propertyType;
    }

    public int getPropertyPrice()
    {
        return propertyPrice;
    }

    public String getPropertyOwner()
    {
        return propertyOwner;
    }

    // Rent depends on how many properties of the same type the owner has
    public int getRent(int houses) 
    {
        if (houses < 0 || houses >= propertyRent.length) 
        {
            throw new IllegalArgumentException("Invalid number of houses/hotel");
        }
        return propertyRent[houses];
    }

    // When someone buys the property, set the owner
    public void setPropertyOwner(String newOwner)
    {
        this.propertyOwner = newOwner;
    }
}