package Games.Monopoly_Assets.Board;

import Games.Monopoly_Assets.Properties.*;
import Games.Monopoly_Assets.Special_Tiles.*;
import java.util.ArrayList;
import java.util.List;

public class BoardTilesManager 
{

    private PropertyManager propertyManager;
    private SpecialTileManager specialTileManager;
    private List<BoardTilesData> tiles; // ordered list of all 40 tiles

    public BoardTilesManager() 
    {
        propertyManager = new PropertyManager();
        specialTileManager = new SpecialTileManager();
        tiles = new ArrayList<>(40);

        for (int i = 0; i < 40; i++) 
        {
            BoardTilesData tile = propertyManager.getPropertyByIndex(i);

            if (tile == null) 
            {
                tile = specialTileManager.getTileByIndex(i);
            }

            // If null create a blank placeholder tile
            if (tile == null)
            {
                tile = new EmptyTile(i);
            }

            tiles.add(tile);
        }
    }

    // Get tile by board index
    public BoardTilesData getTileByBoardIndex(int boardIndex) 
    {
        if (boardIndex < 0 || boardIndex >= tiles.size()) 
        {
            // defensive: return an EmptyTile for out-of-range requests
            return new EmptyTile(boardIndex < 0 ? 0 : 39);
        }
        return tiles.get(boardIndex);
    }

    // Get full ordered list of tiles
    public List<BoardTilesData> getAllTiles() 
    {
        return tiles;
    }

    public PropertyManager getPropertyManager() 
    {
        return propertyManager; 
    }

    public SpecialTileManager getSpecialTileManager() 
    {
        return specialTileManager; 
    }
}
