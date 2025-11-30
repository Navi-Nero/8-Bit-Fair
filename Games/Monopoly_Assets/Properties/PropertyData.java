package Games.Monopoly_Assets.Properties;

import Games.Monopoly_Assets.Board.BoardTilesData;

// Represents a single property on the board (street, railroad, utility, etc)
public class PropertyData implements BoardTilesData
{
    // Property details that don't change
    private int propertyIndex;     // board position (0–39)
    private String propertyName;   // e.g., "Boardwalk"
    private String propertyType;   // e.g., "Street", "Railroad", "Utility"
    private int propertyPrice;     // purchase cost
    private int[] propertyRent;    // rent values depending on houses/hotel
    private int houseCount; // 0–4 houses, 5 = hotel

    
    // Mutable property state
    private String propertyOwner;  // player name or ID

    // Create a property with all its details
    public PropertyData(int index, String name, String type, int price, int[] rent, String owner)
    {
        this.propertyIndex = index;
        this.propertyName = name;
        this.propertyType = type;
        this.propertyPrice = price;
        this.propertyRent = rent;
        this.propertyOwner = owner;
        this.houseCount = 0;
        
    }

    // --- BoardData interface methods ---
     @Override
    public int getBoardIndex() 
    {
        return this.propertyIndex;
    }

    @Override
    public String getName() 
    {
        return this.getPropertyName();
    }

    @Override
    public String getDescription() 
    {
        // You can customize this string as needed
        return this.getPropertyType() + " costing $" + this.getPropertyPrice();
    }

    // --- Property-specific methods ---
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

    public int getHouseCount() 
    {
        return houseCount;
    }

    public void addHouse() 
    {
        if (houseCount < 5) 
        {
            houseCount++;
        }
    }

    public void removeHouse() 
    {
        if (houseCount > 0) 
        {
            houseCount--;
        }
    }

    // Rent depends on how many houses/hotel are built
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

    public boolean hasHotel() 
    {
        return houseCount == 5;
    }

    public void addHouseOrHotel() 
    {
        if (houseCount < 5) 
        {
            houseCount++;
        }
    }
}