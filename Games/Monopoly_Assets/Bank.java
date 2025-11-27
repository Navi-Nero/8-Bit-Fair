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
    // This is included here for organization - money goes to the other player
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
}
