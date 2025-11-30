package Games;

import Games.Monopoly_Assets.MonopolyBackEnd;
import Games.Monopoly_Assets.Players.PlayerData;
import Games.Monopoly_Assets.Properties.PropertyData;

// Main Program for Monopoly
public class Monopoly extends MonopolyBackEnd
{

    // Start the monopoly game
    public static void main(String[] args)
    {

        PlayerData current = players.getCurrentPlayer();
        cards.shuffle();

        while (!players.isGameOver())
        {

            playerTurn();

            int menuChoice = 0;

            while (menuChoice != 3)
            {

                menuChoice = input.getInt("\n=== MONOPOLY MENU ===\n" +
                        "[1] Check Player Stats\n" +
                        "[2] Upgrade Property\n" +
                        "[3] Pay Bail ($50)\n" +
                        "[4] End Turn\n" +
                        "[5] Exit for everyone\n\n");

                switch (menuChoice)
                {

                    case 1:

                        checkPlayerStats();
                        break;

                    case 2:

                        if (current.getPlayerAssets().isEmpty()) 
                        {
                            System.out.println(current.getPlayerName() + " owns no properties to upgrade.");
                            break;
                        }

                        // List owned properties
                        System.out.println("Choose a property to upgrade:");
                        int i = 1;
                        for (PropertyData prop : current.getPlayerAssets()) 
                        {
                            System.out.println("[" + i + "] " + prop.getPropertyName() + " (Houses: " + prop.getHouseCount() + ")");
                            i++;
                        }

                        int choice = input.getInt("Enter property number: ");
                        if (choice < 1 || choice > current.getPlayerAssets().size()) 
                        {
                            System.out.println("Invalid choice.");
                            break;
                        }

                        PropertyData selected = current.getPlayerAssets().get(choice - 1);

                        // Check if player owns full color set before allowing upgrade
                        int totalInSet = properties.getProperties().stream()
                            .filter(p -> p.getPropertyType().equalsIgnoreCase(selected.getPropertyType()))
                            .toArray().length;

                        if (!players.ownsFullColorSet(current.getPlayerName(), selected.getPropertyType(), totalInSet)) 
                        {
                            System.out.println("You must own the full color set to build houses.");
                            break;
                        }

                        // Upgrade cost (simplified: $50 per house, can adjust per color)
                        int upgradeCost = (selected.getHouseCount() < 4) ? 50 : 100;

                        if (selected.hasHotel()) 
                        {
                            System.out.println(selected.getPropertyName() + " already has a Hotel. No further upgrades possible.");
                            break;
                        }

                        if (bank.playerCanBuyProperty(current, upgradeCost)) 
                        {
                            bank.playerBuyProperty(current, upgradeCost);
                            selected.addHouseOrHotel();

                            if (selected.hasHotel()) 
                            {
                                System.out.println(current.getPlayerName() + " built a Hotel on " + selected.getPropertyName() + "!");

                            } else {
                                
                                System.out.println(current.getPlayerName() + " built a house on " + selected.getPropertyName() + 
                                                ". Total houses: " + selected.getHouseCount());
                            }

                        } else {

                            System.out.println("Not enough money to upgrade.");
                            
                        }

                        break;

                    case 3:

                        if (current.isInJail()) 
                        {
                            bank.chargePlayer(current, 50);
                            current.setInJail(false);
                            System.out.println(current.getPlayerName() + " paid $50 bail and is released from Jail.");

                        } else {

                            System.out.println(current.getPlayerName() + " is not in Jail.");
                        }

                        break;

                    case 4:

                        players.nextTurn();
                        break;

                    case 5:

                        System.out.println("Thanks for playing Monopoly! Bye!");
                        return;

                    default:

                        System.out.println("Invalid choice. Try again.");
                }
            }
        }

        System.out.print("Game Over! The winner was " + players.getWinner() + "!");
        
    }
}
