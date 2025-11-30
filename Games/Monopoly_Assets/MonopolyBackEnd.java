package Games.Monopoly_Assets;

import Games.Input_Handling;
import Games.Monopoly_Assets.Board.BoardTilesData;
import Games.Monopoly_Assets.Board.BoardTilesManager;
import Games.Monopoly_Assets.Cards.*;
import Games.Monopoly_Assets.Players.*;
import Games.Monopoly_Assets.Properties.*;
import Games.Monopoly_Assets.Special_Tiles.*;

// Main game board and controller for monopoly
// Manages the game flow, player turns, and interactions with the board
public class MonopolyBackEnd 
{

    protected static final Input_Handling input = new Input_Handling();
    protected static final PlayerManager players = new PlayerManager();
    protected static final CardDeck cards = new CardDeck();
    protected static final Bank bank = new Bank();
    protected static final Dice dice = new Dice();
    protected static final PropertyManager properties = new PropertyManager();
    protected static final SpecialTileManager specialTiles = new SpecialTileManager();
    protected static final BoardTilesManager board = new BoardTilesManager();

    // Player draws a chance card
    public static void drawChanceCard(PlayerData player) 
    {

        CardData chanceCard = cards.drawChanceCard();
        System.out.println("Chance Card: " + chanceCard.getDescription());

        if (chanceCard.isKeepable()) 
        {
            player.addKeepableCard(chanceCard.getDescription());
            System.out.println(player.getPlayerName() + " keeps this card until used.");

        } else {

            // TODO: apply immediate effect
        }
    }

    // Player draws a community chest card
    public static void drawCommunityChestCard(PlayerData player) 
    {

        CardData chestCard = cards.drawCommunityChestCard();
        System.out.println("Community Chest Card: " + chestCard.getDescription());

        if (chestCard.isKeepable()) 
        {
            player.addKeepableCard(chestCard.getDescription());
            System.out.println(player.getPlayerName() + " keeps this card until used.");

        } else {

            // TODO: apply immediate effect
        }
    }

    // Check the status of all players
    public static void checkPlayerStats() 
    {

        int targetIndex = input.getInt("\nEnter the player number to check (0 to see all):\n");
        players.printPlayersStats(targetIndex);

    }

    // Handle a player's turn: roll dice and move
    public static void playerTurn() 
    {

        PlayerData currentPlayer = players.getCurrentPlayer();
        System.out.println("\n" + currentPlayer.getPlayerName() + "'s turn!");

        // --- Jail check ---
        if (currentPlayer.isInJail()) 
        {
            System.out.println(currentPlayer.getPlayerName() + " is in Jail.");

            // Option 1: Use Get Out of Jail Free card
            if (currentPlayer.useKeepableCard("Get Out of Jail Free")) 
            {
                currentPlayer.setInJail(false);
                System.out.println(currentPlayer.getPlayerName() + " used a Get Out of Jail Free card!");
            } 
            else 
            {
                // Option 2: Pay $50 immediately
                boolean payBail = input.getYesNo("Do you want to pay $50 bail to leave Jail immediately? (y/n): ");
                if (payBail) 
                {
                    bank.chargePlayer(currentPlayer, 50);
                    currentPlayer.setInJail(false);
                    System.out.println(currentPlayer.getPlayerName() + " paid $50 bail and is released from Jail.");
                } 
                else 
                {
                    // Option 3: Try rolling doubles
                    int roll = dice.rollDice();
                    System.out.println(currentPlayer.getPlayerName() + " rolled: " + dice.getDie1() + " + " + dice.getDie2() + " = " + roll);

                    if (dice.isDoubles()) 
                    {
                        currentPlayer.setInJail(false);
                        System.out.println("Doubles rolled! " + currentPlayer.getPlayerName() + " is released from Jail.");
                        movePlayer(currentPlayer, roll);
                    } 
                    else 
                    {
                        currentPlayer.stayInJail();

                        if (currentPlayer.mustPayToLeaveJail()) 
                        {
                            System.out.println(currentPlayer.getPlayerName() + " failed to roll doubles for 3 turns. Must pay $50.");
                            bank.chargePlayer(currentPlayer, 50);
                            currentPlayer.setInJail(false);
                            movePlayer(currentPlayer, roll);
                        } 
                        else 
                        {
                            System.out.println(currentPlayer.getPlayerName() + " stays in Jail (turn " + currentPlayer.getJailTurns() + ").");
                        }
                    }
                }
            }

            return; // end turn if still in jail
        }
        

        // --- Normal turn if not in Jail ---
        int doublesCount = 0;
        int roll = dice.rollDice();

        if (dice.isDoubles()) 
        {
            System.out.println(currentPlayer.getPlayerName() + " rolled doubles! Rolling again.");
            doublesCount++;

            if (doublesCount == 3) 
            {
                System.out.println(currentPlayer.getPlayerName() + " rolled doubles three times in a row! Sent to Jail.");
                sendPlayerToJail(currentPlayer);
                return;
            }
        }

        System.out.println(currentPlayer.getPlayerName() + " rolled: " + roll);
        movePlayer(currentPlayer, roll);

        if (bank.isPlayerBankrupt(currentPlayer)) 
        {
            players.setToBankrupt(currentPlayer);
        }

        players.nextTurn();
    }

    private static void sendPlayerToJail(PlayerData player) 
    {
        player.setBoardIndex(10); 
        player.setInJail(true);   // automatically resets jailTurns
        System.out.println(player.getPlayerName() + " was sent to Jail.");
    }


    public static void movePlayer(PlayerData player, int steps) 
    {

        int start = player.getBoardIndex();
        int end = (start + steps) % 40;

        if (start + steps >= 40) 
        {
            bank.payPlayer(player, 200);
            System.out.println("\n" + player.getPlayerName() + " passed Go and collected $200.");
        }

        player.setBoardIndex(end);

        BoardTilesData tile = board.getTileByBoardIndex(end);

        System.out.println("\n" + player.getPlayerName() + " landed on: " + tile.getName() + " (" + tile.getDescription() + ")");

        resolveTile(player, tile);
    }

    private static void resolveTile(PlayerData player, BoardTilesData tile) 
    {

        if (tile instanceof PropertyData) 
        {
            resolvePropertyTile(player, (PropertyData) tile);
        } 
        else if (tile instanceof SpecialTileData) 
        {
            resolveSpecialTile(player, (SpecialTileData) tile);
        } 
        else 
        {
            System.out.println("No effect on this tile.");
        }
    }

    private static void resolvePropertyTile(PlayerData player, PropertyData property) 
    {

        String owner = property.getPropertyOwner();

        // Unowned property: offer to buy
        if (owner == null || owner.isEmpty()) 
        {
            System.out.println(property.getPropertyName() + " is unowned. Price: $" + property.getPropertyPrice());

            boolean buy = input.getYesNo("Do you want to buy it? (y/n): ");

            if (buy && bank.playerCanBuyProperty(player, property.getPropertyPrice())) 
            {
                bank.playerBuyProperty(player, property.getPropertyPrice());
                property.setPropertyOwner(player.getPlayerName());
                player.addProperty(property);

                System.out.println(player.getPlayerName() + " bought " + property.getPropertyName() + ".");

            } else {

                System.out.println(player.getPlayerName() + " did not buy " + property.getPropertyName() + ".");
                startAuction(property); // 🔥 trigger auction here
            }

            return;
        }

        // Owned by self
        if (owner.equals(player.getPlayerName())) 
        {
            System.out.println("You own this property.");
            return;
        }

        // Owned by another player: pay rent
        PlayerData ownerPlayer = players.getPlayerByName(owner);
        int rent = calculateRent(property);

        System.out.println("Rent due: $" + rent + " to " + ownerPlayer.getPlayerName() + ".");
        bank.transfer(player, ownerPlayer, rent);

        if (bank.isPlayerBankrupt(player)) 
        {
            players.setToBankrupt(player);
        }
    }

    private static void startAuction(PropertyData property) 
    {
        System.out.println("Auction started for " + property.getPropertyName() + " (Price $" + property.getPropertyPrice() + ")");

        int highestBid = 0;
        PlayerData highestBidder = null;
        int passes = 0;
        int totalPlayers = players.getActivePlayerCount();

        while (passes < totalPlayers) 
        {
            for (PlayerData p : players.getAllPlayers()) 
            {
                if (p.isBankrupt()) continue;

                System.out.println(p.getPlayerName() + ", current highest bid is $" + highestBid);
                boolean bid = input.getYesNo("Do you want to bid higher? (y/n): ");

                if (bid) 
                {
                    int newBid = input.getInt("Enter your bid: ");
                    if (newBid > highestBid && newBid <= p.getPlayerMoney()) 
                    {
                        highestBid = newBid;
                        highestBidder = p;
                        passes = 0; // reset passes since someone bid
                    } 
                    else 
                    {
                        System.out.println("Invalid bid.");
                    }
                } 
                else 
                {
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
                property.setPropertyOwner(null); // property remains unowned
                System.out.println(highestBidder.getPlayerName() + " went bankrupt during the auction!");

            } else {

                property.setPropertyOwner(highestBidder.getPlayerName());
                highestBidder.addProperty(property);
                System.out.println(highestBidder.getPlayerName() + " won the auction for " + property.getPropertyName() + " with a bid of $" + highestBid);

            }
        }

    }


    private static int calculateRent(PropertyData property) 
    {
        String type = property.getPropertyType();

        if (type.equalsIgnoreCase("Railroad")) 
        {

            int rrOwned = players.countOwnedInGroup(property.getPropertyOwner(), "Railroad");
            int[] rrRent = new int[]{25, 50, 100, 200};
            return rrRent[Math.max(0, Math.min(rrOwned - 1, rrRent.length - 1))];

        }

        if (type.equalsIgnoreCase("Utility")) 
        {

            int lastRoll = dice.getLastRoll();
            int utilitiesOwned = players.countOwnedInGroup(property.getPropertyOwner(), "Utility");
            int multiplier = (utilitiesOwned >= 2) ? 10 : 4;
            return lastRoll * multiplier;

        }

        return property.getRent(property.getHouseCount());

    }


    private static void resolveSpecialTile(PlayerData player, SpecialTileData tile) 
    {

        String name = tile.getTileName();

        switch (name) 
        {
            case "Go":
                System.out.println("Landing on Go. Collect $200 if passing.");
                break;

            case "Go to Jail":
                sendPlayerToJail(player);
                break;

            case "Jail":
                System.out.println("Just visiting Jail.");
                break;

            case "Free Parking":
                System.out.println("Nothing happens on Free Parking.");
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
}