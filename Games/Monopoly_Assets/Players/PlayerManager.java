package Games.Monopoly_Assets.Players;

import Games.Input_Handling;
import Games.Monopoly_Assets.Properties.PropertyData;
import java.util.LinkedList;

// Manages all players in the game
public class PlayerManager {

    private final Input_Handling input;   // injected from MonopolyBackEnd
    private LinkedList<PlayerData> players;
    private int currentPlayerIndex;

    // Constructor now requires Input_Handling
    public PlayerManager(Input_Handling input) {
        this.input = input;
        this.players = new LinkedList<>();

        int numberOfPlayers = getNumberOfPlayers();

        for (int i = 0; i < numberOfPlayers; i++) {
            players.add(new PlayerData(i + 1, inputPlayerName(i + 1), 1500, false, false));
        }

        currentPlayerIndex = 0;
    }

    // Ask how many people are playing
    private int getNumberOfPlayers() {
        int numberOfPlayers;
        do {
            numberOfPlayers = input.getInt("How many players are there? (2 - 6): ");
        } while (numberOfPlayers < 2 || numberOfPlayers > 6);
        return numberOfPlayers;
    }

    // Get player name from user input
    private String inputPlayerName(int index) {
        return input.getStr("Enter the name for player [" + index + "]: ");
    }

    // Get a specific player by index
    public PlayerData getPlayer(int index) {
        for (PlayerData p : players) {
            if (p.getPlayerIndex() == index) {
                return p;
            }
        }
        return null;
    }

    public void setToBankrupt(PlayerData player) 
    {

        player.setBankrupt(true);
        for (PropertyData p : player.getPlayerAssets()) 
        {

        p.setPropertyOwner(null);  // or BANK

        }

        player.getPlayerAssets().clear();
        System.out.println(player.getPlayerName() + " is now bankrupt! " + getActivePlayerCount() + " player/s remain!");
    }

    public PlayerData getCurrentPlayer() 
    {
        if (players.isEmpty()) {
            System.out.println("No players initialized!");
            return null;
        }
        return players.get(currentPlayerIndex);
    }

    public void nextTurn() 
    {
        do {

            currentPlayerIndex = (currentPlayerIndex + 1) % players.size();

        } while (players.get(currentPlayerIndex).isBankrupt());

    }

    public void printPlayersStats(int index) 
    {
        if (index == 0) {
            System.out.println();
            for (PlayerData p : players) {
                System.out.println(p.toString());
            }
        } else {
            PlayerData found = getPlayer(index);
            if (found != null) {
                System.out.println();
                System.out.println(found.toString());
            } else {
                System.out.println("No player found with index " + index);
            }
        }
    }

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

    public int countOwnedInGroup(String ownerName, String groupType) 
    {
        PlayerData owner = getPlayerByName(ownerName);
        if (owner == null) return 0;

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

    public int countOwnedInColor(String ownerName, String color) 
    {
        PlayerData owner = getPlayerByName(ownerName);
        if (owner == null) return 0;

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

    public boolean ownsFullColorSet(String ownerName, String color, int totalInSet) 
    {
        return countOwnedInColor(ownerName, color) == totalInSet;
    }

    public boolean isGameOver() 
    {
        if (players.isEmpty()) return true;
        return getActivePlayerCount() <= 1;
    }

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
