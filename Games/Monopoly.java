package Games;

import Games.Monopoly_Assets.MonopolyBackEnd;
import Games.Monopoly_Assets.Players.PlayerData;
import Games.Monopoly_Assets.Properties.PropertyData;

public class Monopoly {

    public static void main(String[] args) {

        // Create backend instance
        MonopolyBackEnd backend = new MonopolyBackEnd();

        // Shuffle cards before starting
        backend.cards.shuffle();

        // Main game loop
        while (!backend.players.isGameOver()) {

            // Run the current player's turn (roll + move + resolve)
            backend.playerTurn();

            // Get the (same) current player who just took their turn
            PlayerData current = backend.players.getCurrentPlayer();

            // Defensive: if null (all bankrupt etc), break
            if (current == null) {
                System.out.println("No active players remain.");
                break;
            }

            int menuChoice = 0;

            // LOOP until the player chooses "End Turn" (option 5)
            while (menuChoice != 5) {

                menuChoice = backend.input.getInt("\n=== MONOPOLY MENU ===\n" +
                        "[1] Check Player Stats\n" +
                        "[2] Upgrade Property\n" +
                        "[3] Pay Bail ($50)\n" +
                        "[4] Sell a House\n" +
                        "[5] End Turn\n" +
                        "[6] Exit for everyone\n\n");

                switch (menuChoice) {

                    case 1:
                        backend.checkPlayerStats();
                        break;

                    case 2: {
                        // Always refresh current in case something changed
                        current = backend.players.getCurrentPlayer();
                        if (current == null) break;

                        if (current.getPlayerAssets().isEmpty()) {
                            System.out.println(current.getPlayerName() + " owns no properties to upgrade.");
                            break;
                        }

                        System.out.println("Choose a property to upgrade:");
                        int i = 1;
                        for (PropertyData prop : current.getPlayerAssets()) {
                            System.out.println("[" + i + "] " + prop.getPropertyName() + " (Houses: " + prop.getHouseCount() + ")");
                            i++;
                        }

                        int choice = backend.input.getInt("Enter property number: ");
                        if (choice < 1 || choice > current.getPlayerAssets().size()) {
                            System.out.println("Invalid choice.");
                            break;
                        }

                        PropertyData selected = current.getPlayerAssets().get(choice - 1);

                        // Make sure method exists in your PropertyManager (getProperties or getAllProperties)
                        int totalInSet = (int) backend.properties.getAllProperties().stream()
                                .filter(p -> p.getPropertyType().equalsIgnoreCase(selected.getPropertyType()))
                                .count();

                        if (!backend.players.ownsFullColorSet(current.getPlayerName(), selected.getPropertyType(), totalInSet)) {
                            System.out.println("You must own the full color set to build houses.");
                            break;
                        }

                        int upgradeCost = (selected.getHouseCount() < 4) ? 50 : 100;

                        if (selected.hasHotel()) {
                            System.out.println(selected.getPropertyName() + " already has a Hotel. No further upgrades possible.");
                            break;
                        }

                        if (backend.bank.playerCanBuyProperty(current, upgradeCost)) {
                            backend.bank.playerBuyProperty(current, upgradeCost);
                            selected.addHouseOrHotel();

                            if (selected.hasHotel()) {
                                System.out.println(current.getPlayerName() + " built a Hotel on " + selected.getPropertyName() + "!");
                            } else {
                                System.out.println(current.getPlayerName() + " built a house on " + selected.getPropertyName() +
                                        ". Total houses: " + selected.getHouseCount());
                            }
                        } else {
                            System.out.println("Not enough money to upgrade.");
                        }
                        break;
                    }

                    case 3: {
                        // Pay bail
                        current = backend.players.getCurrentPlayer();
                        if (current == null) break;

                        if (current.isInJail()) {
                            backend.bank.chargePlayer(current, 50);
                            current.setInJail(false);
                            System.out.println(current.getPlayerName() + " paid $50 bail and is released from Jail.");
                        } else {
                            System.out.println(current.getPlayerName() + " is not in Jail.");
                        }
                        break;
                    }

                    case 4: {
                        // Sell house/hotel
                        current = backend.players.getCurrentPlayer();
                        if (current == null) break;

                        if (current.getPlayerAssets().isEmpty()) {
                            System.out.println(current.getPlayerName() + " owns no properties to downgrade.");
                            break;
                        }

                        System.out.println("Choose a property to sell a house/hotel from:");
                        int i = 1;
                        for (PropertyData prop : current.getPlayerAssets()) {
                            String upgradeStatus = prop.hasHotel() ? "Hotel" : prop.getHouseCount() + " Houses";
                            System.out.println("[" + i + "] " + prop.getPropertyName() + " (" + upgradeStatus + ")");
                            i++;
                        }

                        int choice = backend.input.getInt("Enter property number: ");
                        if (choice < 1 || choice > current.getPlayerAssets().size()) {
                            System.out.println("Invalid choice.");
                            break;
                        }

                        PropertyData selected = current.getPlayerAssets().get(choice - 1);

                        if (selected.getHouseCount() == 0) {
                            System.out.println("No houses or hotel to sell on " + selected.getPropertyName() + ".");
                            break;
                        }

                        int sellValue = (selected.hasHotel()) ? 50 : 25;

                        if (selected.sellHouseOrHotel()) {
                            backend.bank.payPlayer(current, sellValue);
                            String status = selected.hasHotel() ? "Hotel" : selected.getHouseCount() + " Houses";
                            System.out.println(current.getPlayerName() + " sold a building on " + selected.getPropertyName() +
                                    ". Now: " + status + ". Received $" + sellValue);
                        }
                        break;
                    }

                    case 5:
                        // End turn: now advance to the next player
                        backend.players.nextTurn();
                        System.out.println("Turn ended. Next player's turn.");
                        break;

                    case 6:
                        System.out.println("Thanks for playing Monopoly! Bye!");
                        return;

                    default:
                        System.out.println("Invalid choice. Try again.");
                }
            } // end menu loop
        } // end game loop

        System.out.print("Game Over! The winner was " + backend.players.getWinner() + "!");
    }
}