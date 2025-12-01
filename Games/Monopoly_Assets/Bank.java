package Games.Monopoly_Assets;

import Games.Monopoly_Assets.Players.PlayerData;

// Handles all the money transactions in monopoly
// When players buy property, pay rent, or land on taxes - the bank handles it
public class Bank 
{

    private int bankMoney;

    // Initialize the bank with unlimited money (represented by a large number)
    public Bank() 
    {
        this.bankMoney = 1000000; // Basically unlimited for this game
    }

    // Player wants to buy a property - check if they have enough money
    public boolean playerCanBuyProperty(PlayerData player, int propertyPrice) 
    {
        return player.getPlayerMoney() >= propertyPrice;
    }

    // Player buys a property from the bank
    public void playerBuyProperty(PlayerData player, int propertyPrice) 
    {
        if (playerCanBuyProperty(player, propertyPrice)) 
        {
            player.loseMoney(propertyPrice);
            bankMoney += propertyPrice;
        }
    }

    // Player pays rent to another player (not to bank)
    public void playerPayRent(PlayerData payer, PlayerData receiver, int rentAmount) 
    {
        payer.loseMoney(rentAmount);
        receiver.addMoney(rentAmount);
    }

    // Player lands on tax tile and pays the bank
    public void playerPayTax(PlayerData player, int taxAmount) 
    {
        if (player.getPlayerMoney() >= taxAmount) 
        {
            player.loseMoney(taxAmount);
            bankMoney += taxAmount;
        }
    }

    // --- New convenience methods ---

    // Pay a player directly from the bank (e.g., passing Go)
    public void payPlayer(PlayerData player, int amount) 
    {
        player.addMoney(amount);
        bankMoney -= amount;
    }

    // Charge a player directly to the bank (e.g., fees, fines)
    public void chargePlayer(PlayerData player, int amount) 
    {
        player.loseMoney(amount);
        bankMoney += amount;
    }

    // Transfer money between two players
    public void transfer(PlayerData from, PlayerData to, int amount) 
    {
        from.loseMoney(amount);
        to.addMoney(amount);
    }

    // Get bank's current money
    public int getBankMoney() 
    {
        return bankMoney;
    }

    // Check if a player is bankrupt (has 0 or less money)
    public boolean isPlayerBankrupt(PlayerData player) 
    {
        return player.getPlayerMoney() <= 0;
    }

    // Note there are still some features missing, we are just too tired to continue, total days of coding for 16hrs: 6 days
}