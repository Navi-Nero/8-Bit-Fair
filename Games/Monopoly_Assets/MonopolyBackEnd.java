package Games.Monopoly_Assets;

import Games.Input_Handling;
import Games.Monopoly_Assets.Board.BoardTilesData;
import Games.Monopoly_Assets.Board.BoardTilesManager;
import Games.Monopoly_Assets.Cards.CardData;
import Games.Monopoly_Assets.Cards.CardDeck;
import Games.Monopoly_Assets.Players.PlayerData;
import Games.Monopoly_Assets.Players.PlayerManager;
import Games.Monopoly_Assets.Properties.PropertyData;
import Games.Monopoly_Assets.Properties.PropertyManager;
import Games.Monopoly_Assets.Special_Tiles.SpecialTileData;
import Games.Monopoly_Assets.Special_Tiles.SpecialTileManager;

public class MonopolyBackEnd 
{

    public final Input_Handling input;
    public final PlayerManager players;
    public final CardDeck cards;
    public final Bank bank;
    public final Dice dice;
    public final PropertyManager properties;
    public final SpecialTileManager specialTiles;
    public final BoardTilesManager board;

    public MonopolyBackEnd() 
    {

        this.input = new Input_Handling();
        this.players = new PlayerManager(input);
        this.cards = new CardDeck();
        this.bank = new Bank();
        this.dice = new Dice();
        this.properties = new PropertyManager();
        this.specialTiles = new SpecialTileManager();
        this.board = new BoardTilesManager();

    }

    // ---------- Card drawing ----------
    public void drawChanceCard(PlayerData player) 
    {

        CardData chanceCard = cards.drawChanceCard();
        if (chanceCard == null) 
        {

            System.out.println("No Chance card available.");
            return;

        }

        System.out.println("Chance Card: " + chanceCard.getDescription());

        if (chanceCard.isKeepable()) 
        {

            player.addKeepableCard(chanceCard);
            System.out.println(player.getPlayerName() + " keeps this card until used.");

        } else {

            applyCardEffect(player, chanceCard);
        }
    }

    public void drawCommunityChestCard(PlayerData player) 
    {
        
        CardData chestCard = cards.drawCommunityChestCard();
        if (chestCard == null) 
        {

            System.out.println("No Community Chest card available.");
            return;

        }

        System.out.println("Community Chest Card: " + chestCard.getDescription());

        if (chestCard.isKeepable()) 
        {

            player.addKeepableCard(chestCard);
            System.out.println(player.getPlayerName() + " keeps this card until used.");

        } else {

            applyCardEffect(player, chestCard);
        }
    }

    // Apply card effects
    private void applyCardEffect(PlayerData player, CardData card) 
    {

        if (player == null || card == null) return;

        String desc = card.getDescription();

        switch (desc)
        {

            case "Advance to Go":

                player.setBoardIndex(0);
                player.addMoney(200);

                System.out.println(player.getPlayerName() + " advances to Go and collects $200.");
                break;

            case "Go to Jail":

                sendPlayerToJail(player);
                break;

            case "Bank pays you dividend of $50":

                bank.payPlayer(player, 50);
                break;

            case "Doctor's fees (Pay $50)":

                bank.chargePlayer(player, 50);
                break;

            default:
                System.out.println("This shouldn't appear. What the fuck did you do.");
        }

        if (bank.isPlayerBankrupt(player)) 
        {
            players.setToBankrupt(player);
        }

    }

    // ---------- Turn flow ----------
    // This method handles the full rolling part of a single player's turn.
    // It DOES NOT advance to the next player — that happens in the UI (Monopoly.java) when the user selects End Turn.
    public void playerTurn() 
    {

        PlayerData currentPlayer = players.getCurrentPlayer();

        if (currentPlayer == null) 
        {

            System.out.println("No current player (all players may be bankrupt).");
            return;

        }

        System.out.println("\n" + currentPlayer.getPlayerName() + "'s turn!");

        // If in jail: attempt to roll doubles to get out, up to 3 attempts. If roll doubles, released and move normally.
        if (currentPlayer.isInJail()) 
        {

            System.out.println(currentPlayer.getPlayerName() + " is in Jail. Attempting to roll to get out...");

            int die1 = dice.rollDice(); // rollDice sets lastRoll = die1 + die2

            if (dice.isDoubles()) 
            {

                currentPlayer.setInJail(false);
                currentPlayer.setBoardIndex((currentPlayer.getBoardIndex() + dice.getLastRoll()) % 40);

                System.out.println(currentPlayer.getPlayerName() + " rolled doubles and is released from Jail! Moving " + dice.getLastRoll() + " spaces.");
                BoardTilesData landed = board.getTileByBoardIndex(currentPlayer.getBoardIndex());

                if (landed != null) 
                {

                    System.out.println("Landed on: " + landed.getName() + " (" + landed.getDescription() + ")");
                    resolveTile(currentPlayer, landed);

                }

            } else {

                currentPlayer.incrementJailTurns();
                System.out.println("No doubles. Jail turns: " + currentPlayer.getJailTurns());
                if (currentPlayer.getJailTurns() >= 3) 
                {

                    // After 3 failed attempts player pays bail automatically and moves
                    System.out.println(currentPlayer.getPlayerName() + " failed to roll doubles in 3 attempts. Paying $50 bail and released.");
                    bank.chargePlayer(currentPlayer, 50);
                    currentPlayer.setInJail(false);
                    // then allow a normal roll and move
                    int roll = dice.rollDice();
                    System.out.println(currentPlayer.getPlayerName() + " rolled: " + roll);
                    movePlayer(currentPlayer, roll);

                }
            }
            // In-jail attempt ends player's rolling portion for now (menu can bail or use card)
            return;
        }

        // Normal turn: handle up to 3 consecutive doubles
        int roll = 0;
        int doublesCount = 0;
        boolean continueRolling = true;

        while (continueRolling) 
        {

            roll = dice.rollDice();
            System.out.println(currentPlayer.getPlayerName() + " rolled: " + roll);

            if (bank.isPlayerBankrupt(currentPlayer))
            {

                players.setToBankrupt(currentPlayer);
                return;

            }

            if (dice.isDoubles()) 
            {

                doublesCount++;
                System.out.println("Doubles! (" + doublesCount + ")");
                if (doublesCount >= 3) 
                {

                    // Three doubles -> go to jail immediately
                    System.out.println(currentPlayer.getPlayerName() + " rolled 3 doubles in a row — sent to Jail!");
                    sendPlayerToJail(currentPlayer);
                    return;
                }

                // allow another roll (loop continues)
                System.out.println(currentPlayer.getPlayerName() + " gets to roll again because of doubles.");
                // continue loop

            } else {

                // not doubles -> turn's rolling portion ends
                continueRolling = false;

            }
        }
        
        movePlayer(currentPlayer, roll);

    }

    private void sendPlayerToJail(PlayerData player) 
    {

        if (player == null) return;
        player.setBoardIndex(10);
        player.setInJail(true);

        System.out.println(player.getPlayerName() + " was sent to Jail.");

    }

    // ---------- Movement & tile resolution ----------
    public void movePlayer(PlayerData player, int steps) 
    {

        if (player == null) return;

        // Use PlayerData.moveSteps so passing GO is handled consistently and player receives $200
        player.moveSteps(steps);

        int end = player.getBoardIndex();

        BoardTilesData tile = board.getTileByBoardIndex(end);
        if (tile == null) 
        {

            System.out.println("\n" + player.getPlayerName() + " landed on an empty tile (no effect).");
            return;

        }

        System.out.println("\n" + player.getPlayerName() + " landed on: " + tile.getName() + " (" + tile.getDescription() + ")");

        resolveTile(player, tile);
    }

    private void resolveTile(PlayerData player, BoardTilesData tile) 
    {

        if (tile == null || player == null) return;

        if (tile instanceof PropertyData) 
        {

            resolvePropertyTile(player, (PropertyData) tile);

        } else if (tile instanceof SpecialTileData) 
        {

            resolveSpecialTile(player, (SpecialTileData) tile);

        } else {

            System.out.println("No effect on this tile.");

        }
    }

    // ---------- Property handling ----------
    private void resolvePropertyTile(PlayerData player, PropertyData property) 
    {
        
        if (player == null || property == null) return;

        PlayerData owner = property.getPropertyOwner();

        if (owner == null) 
        {

            System.out.println(property.getPropertyName() + " is unowned. Price: $" + property.getPropertyPrice());

            boolean buy = input.getYesNo("Do you want to buy it? (y/n): ");

            if (buy && bank.playerCanBuyProperty(player, property.getPropertyPrice())) 
            {

                bank.playerBuyProperty(player, property.getPropertyPrice());
                property.setPropertyOwner(player);
                player.addProperty(property);

                System.out.println(player.getPlayerName() + " bought " + property.getPropertyName() + ".");

            } else {

                System.out.println(player.getPlayerName() + " did not buy " + property.getPropertyName() + ".");
                startAuction(property);
            }

            return;
        }

        if (owner == player) 
        {

            System.out.println("You own this property.");
            return;

        }

        int rent = calculateRent(property, owner);
        System.out.println("Rent due: $" + rent + " to " + owner.getPlayerName() + ".");

        bank.transfer(player, owner, rent);

        if (bank.isPlayerBankrupt(player)) 
        {
            players.setToBankrupt(player);
        }

    }

    // Simple auction: last highest bidder wins (synchronous console auction)
    private void startAuction(PropertyData property) 
    {

        if (property == null) return;

        System.out.println("Auction started for " + property.getPropertyName() + " (Price $" + property.getPropertyPrice() + ")");

        int highestBid = 0;
        PlayerData highestBidder = null;
        int passes = 0;
        int totalPlayers = players.getActivePlayerCount();

        // iterate until everyone except bidders have passed
        while (passes < totalPlayers) 
        {

            for (PlayerData p : players.getAllPlayers()) 
            {

                if (p.isBankrupt()) continue;

                System.out.println(p.getPlayerName() + ", current highest bid is $" + highestBid);
                boolean wantsToBid = input.getYesNo("Do you want to bid higher? (y/n): ");

                if (wantsToBid) 
                {

                    int newBid = input.getInt("Enter your bid: ");

                    if (newBid > highestBid && newBid <= p.getPlayerMoney()) 
                    {

                        highestBid = newBid;
                        highestBidder = p;
                        passes = 0; // reset passes

                    } else {

                        System.out.println("Invalid bid or insufficient funds.");

                    }

                } else {

                    passes++;

                }
            }
        }

        if (highestBidder != null) 

        {
            bank.chargePlayer(highestBidder, highestBid);

            if (bank.isPlayerBankrupt(highestBidder)) 
            {

                players.setToBankrupt(highestBidder);
                property.setPropertyOwner(null); // remains unowned

                System.out.println(highestBidder.getPlayerName() + " went bankrupt during the auction!");

            } else {

                property.setPropertyOwner(highestBidder);
                highestBidder.addProperty(property);

                System.out.println(highestBidder.getPlayerName() + " won the auction for " + property.getPropertyName() + " with $" + highestBid);

            }

        } else {

            System.out.println("No one bid on " + property.getPropertyName() + ". Property remains unowned.");

        }
    }

    // ---------- Rent calculation ----------
    private int calculateRent(PropertyData property, PlayerData owner) 
    {

        if (property == null) return 0;

        String type = property.getPropertyType();

        if (type.equalsIgnoreCase("Railroad")) 
        {

            int rrOwned = properties.countRailroadsOwned(owner);
            int[] rrRent = new int[]{25, 50, 100, 200};
            int idx = Math.max(0, Math.min(rrOwned - 1, rrRent.length - 1));
            return rrRent[idx];

        }

        if (type.equalsIgnoreCase("Utility")) 
        {

            int utilitiesOwned = properties.countUtilitiesOwned(owner);
            int multiplier = (utilitiesOwned >= 2) ? 10 : 4;
            int lastRoll = dice.getLastRoll();
            return lastRoll * multiplier;

        }

        int houses = property.getHouseCount();

        try {

            return property.getRent(houses);

        } catch (Exception ex) {

            System.err.println("Error calculating rent for " + property.getPropertyName() + ": " + ex.getMessage());
            return 0;

        }

    }

    // ---------- Special tile handling ----------
    private void resolveSpecialTile(PlayerData player, SpecialTileData tile) 
    {

        if (player == null || tile == null) return;

        String name = tile.getTileName();

        switch (name) 
        {

            case "Empty":

                System.out.println("Nothing happens on this tile.");
                break;

            case "Go":

                System.out.println("Landing on Go.");
                break;

            case "Go to Jail":

                sendPlayerToJail(player);
                break;

            case "Jail":

                System.out.println("Just visiting Jail.");
                break;

            case "Free Parking":

                System.out.println("Free Parking — no effect.");
                break;

            case "Income Tax":

                int tax = 200;
                bank.chargePlayer(player, tax);
                System.out.println(player.getPlayerName() + " paid Income Tax: $" + tax + ".");
                break;

            case "Luxury Tax":

                bank.chargePlayer(player, 100);
                System.out.println(player.getPlayerName() + " paid Luxury Tax: $100.");
                break;

            case "Chance":

                drawChanceCard(player);
                break;

            case "Community Chest":

                drawCommunityChestCard(player);
                break;

            default:
                System.out.println("Unhandled special tile: " + name);

        }

        if (bank.isPlayerBankrupt(player)) 
        {
            players.setToBankrupt(player);
        }

    }

    // ---------- Utilities ----------
    public void checkPlayerStats() 
    {
        
        int targetIndex = input.getInt("\nEnter the player number to check (0 to see all):\n");
        players.printPlayersStats(targetIndex);

    }
}