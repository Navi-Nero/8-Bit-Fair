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

public class MonopolyBackEnd {

    public final Input_Handling input;
    public final PlayerManager players;
    public final CardDeck cards;
    public final Bank bank;
    public final Dice dice;
    public final PropertyManager properties;
    public final SpecialTileManager specialTiles;
    public final BoardTilesManager board;

    public MonopolyBackEnd() {
        this.input = new Input_Handling();
        this.players = new PlayerManager(input);
        this.cards = new CardDeck();
        this.bank = new Bank();
        this.dice = new Dice();
        this.properties = new PropertyManager();
        this.specialTiles = new SpecialTileManager();
        this.board = new BoardTilesManager(); // ensure BoardTilesManager no longer places nulls
    }

    // ---------- Card drawing ----------
    public void drawChanceCard(PlayerData player) {
        CardData chanceCard = cards.drawChanceCard();
        if (chanceCard == null) {
            System.out.println("No Chance card available.");
            return;
        }

        System.out.println("Chance Card: " + chanceCard.getDescription());

        if (chanceCard.isKeepable()) {
            // Add the actual CardData object to player (keeps reference so we can return it later)
            player.addKeepableCard(chanceCard);
            System.out.println(player.getPlayerName() + " keeps this card until used.");
        } else {
            applyCardEffect(player, chanceCard);
        }
    }

    public void drawCommunityChestCard(PlayerData player) {
        CardData chestCard = cards.drawCommunityChestCard();
        if (chestCard == null) {
            System.out.println("No Community Chest card available.");
            return;
        }

        System.out.println("Community Chest Card: " + chestCard.getDescription());

        if (chestCard.isKeepable()) {
            player.addKeepableCard(chestCard);
            System.out.println(player.getPlayerName() + " keeps this card until used.");
        } else {
            applyCardEffect(player, chestCard);
        }
    }

    // Apply card effects - extend this mapping as you add card types / enums
    private void applyCardEffect(PlayerData player, CardData card) {
        if (player == null || card == null) return;

        String desc = card.getDescription();

        switch (desc) {
            case "Advance to Go (Collect $200)":
            case "Advance to Go":
                // Move to Go and collect
                player.setBoardIndex(0);
                bank.payPlayer(player, 200);
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

            // Add more cards here. Consider switching to CardType-based dispatch for maintainability.
            default:
                System.out.println("Card effect not implemented yet: " + desc);
        }

        if (bank.isPlayerBankrupt(player)) {
            players.setToBankrupt(player);
        }
    }

    // ---------- Turn flow ----------
    public void playerTurn() {
        PlayerData currentPlayer = players.getCurrentPlayer();
        if (currentPlayer == null) {
            System.out.println("No current player (all players may be bankrupt).");
            return;
        }

        System.out.println("\n" + currentPlayer.getPlayerName() + "'s turn!");

        if (currentPlayer.isInJail()) {
            
            System.out.println(currentPlayer.getPlayerName() + " is in Jail. (Use menu to pay bail or attempt to roll.)");
            
            players.nextTurn();
            return;
        }

        int roll = dice.rollDice();
        System.out.println(currentPlayer.getPlayerName() + " rolled: " + roll);

        movePlayer(currentPlayer, roll);

        if (bank.isPlayerBankrupt(currentPlayer)) {
            players.setToBankrupt(currentPlayer);
        }
    }

    private void sendPlayerToJail(PlayerData player) {
        if (player == null) return;
        player.setBoardIndex(10);
        player.setInJail(true);
        System.out.println(player.getPlayerName() + " was sent to Jail.");
    }

    // ---------- Movement & tile resolution ----------
    public void movePlayer(PlayerData player, int steps) {
        if (player == null) return;

        int start = player.getBoardIndex();
        int end = (start + steps) % 40;

        // Detect passing GO (if end wrapped past start)
        if (start + steps >= 40) {
            bank.payPlayer(player, 200);
            System.out.println("\n" + player.getPlayerName() + " passed Go and collected $200.");
        }

        player.setBoardIndex(end);

        BoardTilesData tile = board.getTileByBoardIndex(end);
        if (tile == null) {
            // Defensive: BoardTilesManager should not return null, but protect anyway.
            System.out.println("\n" + player.getPlayerName() + " landed on an empty tile (no effect).");
            return;
        }

        System.out.println("\n" + player.getPlayerName() + " landed on: " + tile.getName() + " (" + tile.getDescription() + ")");

        resolveTile(player, tile);
    }

    private void resolveTile(PlayerData player, BoardTilesData tile) {
        if (tile == null || player == null) return;

        if (tile instanceof PropertyData) {
            resolvePropertyTile(player, (PropertyData) tile);
        } else if (tile instanceof SpecialTileData) {
            resolveSpecialTile(player, (SpecialTileData) tile);
        } else {
            System.out.println("No effect on this tile.");
        }
    }

    // ---------- Property handling ----------
    private void resolvePropertyTile(PlayerData player, PropertyData property) {
        if (player == null || property == null) return;

        // PropertyData now stores PlayerData as owner (nullable)
        PlayerData owner = property.getPropertyOwner();

        if (owner == null) {
            // OFFER BUY
            System.out.println(property.getPropertyName() + " is unowned. Price: $" + property.getPropertyPrice());

            boolean buy = input.getYesNo("Do you want to buy it? (y/n): ");
            if (buy && bank.playerCanBuyProperty(player, property.getPropertyPrice())) {
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

        // If owner equals current player
        if (owner == player) {
            System.out.println("You own this property.");
            return;
        }

        // Owned by another player: pay rent
        int rent = calculateRent(property, owner);
        System.out.println("Rent due: $" + rent + " to " + owner.getPlayerName() + ".");

        bank.transfer(player, owner, rent);

        if (bank.isPlayerBankrupt(player)) {
            players.setToBankrupt(player);
        }
    }

    // Simple auction: last highest bidder wins (synchronous console auction)
    private void startAuction(PropertyData property) {
        if (property == null) return;

        System.out.println("Auction started for " + property.getPropertyName() + " (Price $" + property.getPropertyPrice() + ")");

        int highestBid = 0;
        PlayerData highestBidder = null;
        int passes = 0;
        int totalPlayers = players.getActivePlayerCount();

        // iterate until everyone except bidders have passed
        while (passes < totalPlayers) {
            for (PlayerData p : players.getAllPlayers()) {
                if (p.isBankrupt()) continue;

                System.out.println(p.getPlayerName() + ", current highest bid is $" + highestBid);
                boolean wantsToBid = input.getYesNo("Do you want to bid higher? (y/n): ");

                if (wantsToBid) {
                    int newBid = input.getInt("Enter your bid: ");
                    if (newBid > highestBid && newBid <= p.getPlayerMoney()) {
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

        if (highestBidder != null) {
            bank.chargePlayer(highestBidder, highestBid);
            if (bank.isPlayerBankrupt(highestBidder)) {
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
    private int calculateRent(PropertyData property, PlayerData owner) {
        if (property == null) return 0;

        String type = property.getPropertyType();

        if (type.equalsIgnoreCase("Railroad")) {
            // Use PropertyManager helper
            int rrOwned = properties.countRailroadsOwned(owner);
            int[] rrRent = new int[]{25, 50, 100, 200};
            int idx = Math.max(0, Math.min(rrOwned - 1, rrRent.length - 1));
            return rrRent[idx];
        }

        if (type.equalsIgnoreCase("Utility")) {
            // Utilities use last dice roll × multiplier (4 or 10)
            int utilitiesOwned = properties.countUtilitiesOwned(owner);
            int multiplier = (utilitiesOwned >= 2) ? 10 : 4;
            int lastRoll = dice.getLastRoll();
            return lastRoll * multiplier;
        }

        // Standard street: use houseCount as index into rent array
        int houses = property.getHouseCount();
        try {
            return property.getRent(houses);
        } catch (Exception ex) {
            // Defensive fallback
            System.err.println("Error calculating rent for " + property.getPropertyName() + ": " + ex.getMessage());
            return 0;
        }
    }

    // ---------- Special tile handling ----------
    private void resolveSpecialTile(PlayerData player, SpecialTileData tile) {
        if (player == null || tile == null) return;

        String name = tile.getTileName();

        switch (name) {
            case "Go":
                // Landing on Go: if landing directly you could give $200 (we already give on pass)
                System.out.println("Landing on Go. (Collect $200 if passing.)");
                break;

            case "Go to Jail":
                sendPlayerToJail(player);
                break;

            case "Jail":
                System.out.println("Just visiting Jail.");
                break;

            case "Free Parking":
                System.out.println("Free Parking — no game rules effect (house rule may vary).");
                break;

            case "Income Tax":
                // simple fixed tax
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

        if (bank.isPlayerBankrupt(player)) {
            players.setToBankrupt(player);
        }
    }

    // ---------- Utilities ----------
    public void checkPlayerStats() {
        int targetIndex = input.getInt("\nEnter the player number to check (0 to see all):\n");
        players.printPlayersStats(targetIndex);
    }
}