package Games.Monopoly_Assets.Properties;

import Games.Monopoly_Assets.Board.BoardTilesData;
import Games.Monopoly_Assets.Players.PlayerData;

public class PropertyData implements BoardTilesData {

    // Fixed details
    private final int propertyIndex;
    private final String propertyName;
    private final String propertyType;
    private final int propertyPrice;
    private final int[] propertyRent;

    // Houses: 0–4, 5 = hotel
    private int houseCount;

    // owner (null = bank/unowned)
    private PlayerData propertyOwner;

    public PropertyData(int index, String name, String type, int price, int[] rent, PlayerData owner) 
    {

        this.propertyIndex = index;
        this.propertyName = name;
        this.propertyType = type;
        this.propertyPrice = price;
        this.propertyRent = rent;
        this.propertyOwner = owner;
        this.houseCount = 0;

    }

    // --- BoardTilesData Interface ---
    @Override
    public int getBoardIndex() 
    {
        return propertyIndex;
    }

    @Override
    public String getName() 
    {
        return propertyName;
    }

    @Override
    public String getDescription() 
    {
        return propertyType + " costing $" + propertyPrice;
    }

    // --- Property Methods ---
    public boolean isOwned() 
    {
        return propertyOwner != null;
    }

    public PlayerData getPropertyOwner() 
    {
        return propertyOwner;
    }

    public void setPropertyOwner(PlayerData owner) 
    {
        this.propertyOwner = owner;
    }

    public void clearOwnership() 
    {
        this.propertyOwner = null;
        this.houseCount = 0;
    }

    public String getPropertyName()
    {
        return propertyName;
    }

    public int getPropertyPrice() 
    {
        return propertyPrice;
    }

    public String getPropertyType() 
    {
        return propertyType;
    }

    public int getHouseCount() 
    {
        return houseCount;
    }

    public void addHouseOrHotel() 
    {
        if (houseCount < 5) 
        {
            houseCount++;
        }
    }

    public boolean sellHouseOrHotel() 
    {
        if (houseCount > 0) {
            houseCount--;
            return true;
        }
        return false;
    }

    public boolean hasHotel() 
    {
        return houseCount == 5;
    }

    public int getRent(int houses) 
    {
        return propertyRent[houses];
    }
}