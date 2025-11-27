package Games.Monopoly_Assets.Special_Tiles;

public class Special_Tiles_Constructor 
{
    
    private String special_Tile_Name;
    private String special_Tile_Description;

    Special_Tiles_Constructor (String name, String description)
    {

        this.special_Tile_Name = name;
        this.special_Tile_Description = description;

    }

    public String get_Special_Tile_Name()
    {
        return special_Tile_Name;
    }

    public String get_Special_Tile_Description()
    {
        return special_Tile_Description;
    }

}
