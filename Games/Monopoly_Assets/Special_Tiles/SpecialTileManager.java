package Games.Monopoly_Assets.Special_Tiles;

import java.util.ArrayList;
import java.util.List;

// Manages all 8 special tiles on the board
// These tiles trigger special effects when you land on them
public class SpecialTileManager
{
    private List<SpecialTileData> specialTiles;

    // Create all the special tiles with their names and descriptions
    public SpecialTileManager()
    {
        specialTiles = new ArrayList<>();

        specialTiles.add(new SpecialTileData(0, "Go", "Collect $200 when you pass or land here."));
        specialTiles.add(new SpecialTileData(30,"Go to Jail", "Move directly to Jail. Do not pass Go, do not collect $200."));
        specialTiles.add(new SpecialTileData(10,"Jail", "Stay here until player rolls doubles on dice or pays $50"));
        specialTiles.add(new SpecialTileData(7,"Chance", "Draw a Chance card."));
        specialTiles.add(new SpecialTileData(2,"Community Chest", "Draw a Community Chest card."));
        specialTiles.add(new SpecialTileData(22,"Chance", "Draw a Chance card."));
        specialTiles.add(new SpecialTileData(17,"Community Chest", "Draw a Community Chest card."));
        specialTiles.add(new SpecialTileData(36,"Chance", "Draw a Chance card."));
        specialTiles.add(new SpecialTileData(33,"Community Chest", "Draw a Community Chest card."));
        specialTiles.add(new SpecialTileData(4,"Income Tax", "Pay $200 or 10% of your total worth."));
        specialTiles.add(new SpecialTileData(38,"Luxury Tax", "Pay $100."));
        specialTiles.add(new SpecialTileData(20,"Free Parking", "No effect."));
    }

    // Get all special tiles
    public List<SpecialTileData> getSpecialTiles()
    {
        return specialTiles;
    }

    // Get a specific special tile by name
    public SpecialTileData getTileByName(String name)
    {
        for (SpecialTileData tile : specialTiles)
        {
            if (tile.getTileName().equals(name))
                return tile;
        }
        return null;
    }
}
