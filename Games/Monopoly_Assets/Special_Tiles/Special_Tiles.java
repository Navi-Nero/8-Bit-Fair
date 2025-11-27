package Games.Monopoly_Assets.Special_Tiles;

import java.util.ArrayList;
import java.util.List;

public class Special_Tiles 
{

    private List<Special_Tiles_Constructor> special_Tile;

    //Create the Special Tiles
    public Special_Tiles()
    {

        special_Tile = new ArrayList<>();

        special_Tile.add( new Special_Tiles_Constructor("Go","Collect P200 when you pass or land here."));
        special_Tile.add( new Special_Tiles_Constructor("Go to Jail","Move directly to Jail. Do not pass Go, do not collect $200."));
        special_Tile.add( new Special_Tiles_Constructor("Jail","Stay here until player rolls doubles on dices or pays P50"));
        special_Tile.add( new Special_Tiles_Constructor("Chance","Draw a Chance card."));
        special_Tile.add( new Special_Tiles_Constructor("Community Chest","Draw a Community Chest card."));
        special_Tile.add( new Special_Tiles_Constructor("Income Tax","Pay $200 or 10% of your total worth."));
        special_Tile.add( new Special_Tiles_Constructor("Luxury Tax","Pay $100."));
        special_Tile.add( new Special_Tiles_Constructor("Free Parking","No effect."));

    }   

    // add methods on what happens when you get those tiles, and do the same for the cards
    
}
