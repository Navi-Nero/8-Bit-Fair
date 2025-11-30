package Games.Monopoly_Assets.Special_Tiles;

// Represents a single special tile (Go, Jail, Taxes, etc)
public class SpecialTileData 
{

    private int tileIndex;
    private String tileName;
    private String tileDescription;

    // Create a special tile with a name and what it does
    SpecialTileData(int index, String name, String description)
    {

        this.tileIndex = index;
        this.tileName = name;
        this.tileDescription = description;
        
    }

    public String getTileName()
    {
        return tileName;
    }

    public String getTileDescription()
    {
        return tileDescription;
    }
}
