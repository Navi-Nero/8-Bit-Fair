package Games.Monopoly_Assets.Players;

import java.util.ArrayList;

public class Players_Constructor 
{

    // Initialized Parameters
    private int player_Index;
    private String player_Name;
    private int player_Money;
    private ArrayList<String> player_Assets;
    private boolean player_Turn;
    private boolean player_Bankrupt;

    public Players_Constructor(int index, String name, int money, boolean turn, boolean bankrupt)
    {

        this.player_Index = index;
        this.player_Name = name;
        this.player_Money = money;
        this.player_Assets = new ArrayList<>();
        this.player_Turn = turn;
        this.player_Bankrupt = bankrupt;

    }
    
    public int get_Player_Index()
    {
        return player_Index;
    }

    public String get_Player_Name()
    {
        return player_Name;
    }

    public int get_Player_Money()
    {
        return player_Money;
    }

    public void add_Asset(String asset) 
    {
        player_Assets.add(asset);
    }

    public void remove_Asset(String asset) 
    {
        player_Assets.remove(asset);
    }

    public ArrayList<String> get_Player_Assets() 
    {
        return player_Assets;
    }

    public boolean players_Turn()
    {
        return player_Turn = true;
    }

    public boolean players_Turn_Done()
    {
        return player_Turn = false;
    }

    public boolean get_Player_Bankrupt()
    {
        return player_Bankrupt;
    }

}