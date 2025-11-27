package Games.Monopoly_Assets.Players;

import java.util.ArrayList;

// Represents a single player in the game
public class PlayerData {
    private int playerIndex;
    private String playerName;
    private int playerMoney;
    private ArrayList<String> playerAssets;
    private boolean isTurn;
    private boolean bankrupt;

    // Create a new player with starting money
    public PlayerData(int index, String name, int money, boolean isTurn, boolean bankrupt) {
        this.playerIndex = index;
        this.playerName = name;
        this.playerMoney = money;
        this.playerAssets = new ArrayList<>();
        this.isTurn = isTurn;
        this.bankrupt = bankrupt;
    }
    
    // Get this player's number
    public int getPlayerIndex() {
        return playerIndex;
    }

    // Get player's name
    public String getPlayerName() {
        return playerName;
    }

    // Check how much money they have
    public int getPlayerMoney() {
        return playerMoney;
    }

    // Add cash to player's balance
    public void addMoney(int amount) {
        this.playerMoney += amount;
    }

    // Subtract cash from player
    public void loseMoney(int amount) {
        this.playerMoney -= amount;
    }

    // Add a property to their portfolio
    public void addAsset(String asset) {
        playerAssets.add(asset);
    }

    // Remove a property from their portfolio
    public void removeAsset(String asset) {
        playerAssets.remove(asset);
    }

    // Get all properties owned
    public ArrayList<String> getPlayerAssets() {
        return playerAssets;
    }

    // Start their turn
    public void startTurn() {
        isTurn = true;
    }

    // End their turn
    public void endTurn() {
        isTurn = false;
    }

    // Check if it's their turn
    public boolean isPlayerTurn() {
        return isTurn;
    }

    // Check if player is broke
    public boolean isBankrupt() {
        return bankrupt;
    }

    // Mark player as bankrupt
    public void setBankrupt() {
        this.bankrupt = true;
    }
}