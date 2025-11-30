package Games.Monopoly_Assets.Players;

import Games.Monopoly_Assets.Cards.CardData;
import Games.Monopoly_Assets.Properties.PropertyData;
import java.util.ArrayList;
import java.util.List;

public class PlayerData 
{

    private int playerIndex;        
    private String playerName;      
    private int playerMoney;        
    private int boardIndex;         
    private boolean inJail;         
    private int jailTurns;          
    private boolean bankrupt;       
    private List<PropertyData> assets; 
    private List<CardData> keepableCards; 

    // Constructor
    public PlayerData(int index, String name, int startingMoney, boolean inJail, boolean bankrupt) 
    {

        this.playerIndex = index;
        this.playerName = name;
        this.playerMoney = startingMoney;
        this.boardIndex = 0; 
        this.inJail = inJail;
        this.jailTurns = 0;
        this.bankrupt = bankrupt;
        this.assets = new ArrayList<>();
        this.keepableCards = new ArrayList<>();

    }

    // --- Basic getters ---
    public int getPlayerIndex() 
    {
        return playerIndex;
    }

    public String getPlayerName() 
    {
        return playerName;
    }

    public int getPlayerMoney() 
    {
        return playerMoney;
    }

    public int getBoardIndex() 
    {
        return boardIndex;
    }

    public boolean isInJail() 
    {
        return inJail;
    }

    public boolean isBankrupt() 
    {
        return bankrupt;
    }

    public List<PropertyData> getPlayerAssets() 
    {
        return assets;
    }

    // --- Money management ---
    public void addMoney(int amount) 
    {
        playerMoney += amount;
    }

    public void loseMoney(int amount) 
    {
        playerMoney -= amount;
    }

    public void setBankrupt(boolean bankrupt) 
    {
        this.bankrupt = bankrupt;
    }

    // --- Board movement ---
    public void setBoardIndex(int index) 
    {
        this.boardIndex = index;
    }

    public void moveSteps(int steps) 
    {
        int oldIndex = boardIndex;
        boardIndex = (boardIndex + steps) % 40;

        if (boardIndex < oldIndex) 
        {
            addMoney(200); // passed GO
            System.out.println(playerName + " passed GO! +$200");
        }
    }

    // --- Jail management ---
    public void setInJail(boolean inJail) 
    {
        this.inJail = inJail;
        if (!inJail) 
        {
            jailTurns = 0; 
        }
    }

    public void incrementJailTurns() 
    {
        jailTurns++;
    }

    public int getJailTurns() 
    {
        return jailTurns;
    }

    // --- Property management ---
    public void addProperty(PropertyData property) 
    {
        assets.add(property);
    }

    public void removeProperty(PropertyData property) 
    {
        assets.remove(property);
    }

    // --- Card management ---
    public void addKeepableCard(CardData card) 
    {
        keepableCards.add(card);
    }

    public boolean useKeepableCard(CardData card) 
    {
        return keepableCards.remove(card);
    }

    @Override
public String toString() 
    {

        StringBuilder propertyNames = new StringBuilder();

        if (assets.isEmpty()) 
        {
            propertyNames.append("None");
        } 
        else 
        {
            for (PropertyData property : assets) 
            {
                propertyNames.append(property.getName()).append(", ");
            }
            // remove trailing comma and space
            propertyNames.setLength(propertyNames.length() - 2);
        }

        return "Player " + playerIndex + ": " + playerName +
               " | Money: $" + playerMoney +
               " | Position: " + boardIndex +
               " | Jail: " + (inJail ? "Yes" : "No") +
               " | Bankrupt: " + (bankrupt ? "Yes" : "No") +
               " | Properties: " + propertyNames.toString();

    }
}
