package Games.Monopoly_Assets.Special_Tiles;

import Games.Monopoly_Assets.Board.BoardTilesData;


// Represents a single special tile (Go, Jail, Taxes, etc)
public class SpecialTileData implements BoardTilesData 
{
    private int tileIndex;
    private String tileName;  
    private String tileDescription; 

    // Create a special tile with a name and what it does
    public SpecialTileData(int index, String name, String description)
    {
        this.tileIndex = index;
        this.tileName = name;
        this.tileDescription = description;
    }

    // --- BoardData interface methods ---
     @Override
    public int getBoardIndex() 
    {
        return this.tileIndex;
    }

    @Override
    public String getName() 
    {
        return this.getTileName();
    }

    @Override
    public String getDescription() 
    {
        return this.getTileDescription();
    }

    // --- Extra getters ---
    public String getTileName()
    {
        return tileName;
    }

    public String getTileDescription()
    {
        return tileDescription;
    }
}
