package Games.Monopoly_Assets.Players;

import Games.Input_Handling;
import java.util.LinkedList;

// Manages all players in the game
public class PlayerManager 
{

    private final static Input_Handling input = new Input_Handling();
    private LinkedList<PlayerData> players;
    private int currentPlayerIndex;

    public PlayerManager() 

    {

        players = new LinkedList<>();
        int numberOfPlayers = getNumberOfPlayers();

        for (int i = 0; i < numberOfPlayers; i++) 
        {
            players.add(new PlayerData(i + 1, inputPlayerName(i + 1), 1500, false, false));
        }
        
        currentPlayerIndex = 0;
    }

    // Ask how many people are playing
    private int getNumberOfPlayers() 
    {

        int numberOfPlayers = input.getInt("How many players are there? ");
        return numberOfPlayers;

    }

    // Get player name from user input
    private String inputPlayerName(int index) 
    {

        String playerName = input.getStr("Enter the name for player [" + (index) + "]: ");
        return playerName;

    }

    // Get a specific player by index
    public PlayerData getPlayer(int index) 
    {

        for (PlayerData p : players) 
        {

            if (p.getPlayerIndex() == index) 
            {
                return p;
            }

        }

        return null;

    }

    public void setToBankrupt(PlayerData player) 
    {

        player.setBankrupt(true);
        player.getPlayerAssets().clear(); // release properties
        System.out.println(player.getPlayerName() + " is now bankrupt! " + getActivePlayerCount() + " player/s remain!");

    }


    // Get the current player whose turn it is
    public PlayerData getCurrentPlayer() 
    {
        return players.get(currentPlayerIndex);
    }

    // Move to next player's turn
    public void nextTurn() 
    {
        currentPlayerIndex = (currentPlayerIndex + 1) % players.size();
    }

    // Print stats for one player or all
    public void printPlayersStats(int index) 
    {

        if (index == 0) 
        {

            System.out.println();

            // Print everyone's stats
            for (PlayerData p : players) 
            {

                System.out.println(p.toString());

            }

        } else {
            
            // Find and print specific player
            PlayerData found = getPlayer(index);
            
            if (found != null) 
            {

                System.out.println();
                System.out.println(found.toString());

            } else {

                System.out.println("No player found with index " + index);

            }
        }
    }

    // Get how many players are still in the game
    public int getActivePlayerCount() 
    {

        int count = 0;
        for (PlayerData p : players) 
        {

            if (!p.isBankrupt()) 
            {
                count++;
            }
        }

        return count;
    }

    public PlayerData getPlayerByName(String name) 
    {

        for (PlayerData p : players) 
        {
            if (p.getPlayerName().equalsIgnoreCase(name)) 
            {
                return p;
            }
        }

        return null;

    }

    // Count how many properties of a given type (Railroad, Utility, Color) a player owns
public int countOwnedInGroup(String ownerName, String groupType) 
    {

        PlayerData owner = getPlayerByName(ownerName);
        if (owner == null) 
        {
            return 0;
        }

        int count = 0;
        for (Games.Monopoly_Assets.Properties.PropertyData property : owner.getPlayerAssets()) 
        {
            if (property.getPropertyType().equalsIgnoreCase(groupType)) 
            {
                count++;
            }
        }
        return count;

    }

// Count how many properties of a given color group a player owns
public int countOwnedInColor(String ownerName, String color) 
    {

        PlayerData owner = getPlayerByName(ownerName);
        if (owner == null) 
        {
            return 0;
        }

        int count = 0;
        for (Games.Monopoly_Assets.Properties.PropertyData property : owner.getPlayerAssets()) 
        {
            if (property.getPropertyType().equalsIgnoreCase(color)) 
            {
                count++;
            }
        }
        return count;

    }

// Check if a player owns all properties in a color group (monopoly)
public boolean ownsFullColorSet(String ownerName, String color, int totalInSet) 
    {
        return countOwnedInColor(ownerName, color) == totalInSet;
    }


    // Check if game is over (only 1 player left)
    public boolean isGameOver() 
    {
        return getActivePlayerCount() <= 1;
    }

    // Get list of all players
    public LinkedList<PlayerData> getAllPlayers() 
    {
        return players;
    }

    public String getWinner()
    {

        for (PlayerData p : players) 
        {

            if (!p.isBankrupt())
            {
                return p.getPlayerName();
            }
            
        }
        return null;
    }
}