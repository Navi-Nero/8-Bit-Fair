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
        if (index == 0) {
            System.out.println();

            // Print everyone's stats
            for (PlayerData p : players) {
                System.out.println("Player " + p.getPlayerIndex() + ": " + p.getPlayerName() +
                                " | Money: $" + p.getPlayerMoney() +
                                " | Properties: " + p.getPlayerAssets());
            }

        } else {
            // Find and print specific player
            PlayerData found = getPlayer(index);
            
            if (found != null) {
                System.out.println();
                System.out.println("Player " + found.getPlayerIndex() + ": " + found.getPlayerName() +
                                " | Money: $" + found.getPlayerMoney() +
                                " | Properties: " + found.getPlayerAssets().size());
            } else {
                System.out.println("No player found with index " + index);
            }
        }
    }

    // Get how many players are still in the game
    public int getActivePlayerCount() {
        int count = 0;
        for (PlayerData p : players) {
            if (!p.isBankrupt()) {
                count++;
            }
        }
        return count;
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
}