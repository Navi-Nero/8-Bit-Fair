package Games.Monopoly_Assets;

import Games.Input_Handling;
import Games.Monopoly_Assets.Cards.*;
import Games.Monopoly_Assets.Players.*;


public class Board 
{

    private final static Input_Handling input = new Input_Handling();
    private final static Players players = new Players();
    private final static Cards cards = new Cards();

    public static void draw_Chance_Card()
    {

        // Draws a Chance card
        Cards_Constructor chance_Card = cards.draw_Chance_Card();
        System.out.println("Chance Card: " + chance_Card.getDescription());

        // If it's keepable
        if (chance_Card.isKeepable()) 
        {

            System.out.println("Player keeps this card until used.");

            // Later, when used:
            cards.return_Chance_Card(chance_Card);
            System.out.println("Card returned to bottom of deck.");

        }

    }

    public static void draw_Community_Chest_Card()
    {

        // Draws a Community Chest card
        Cards_Constructor chest_Card = cards.draw_Community_Chest_Card();
        System.out.println("Community Chest Card: " + chest_Card.getDescription());

        // If it's keepable
        if (chest_Card.isKeepable()) 
        {

            System.out.println("Player keeps this card until used.");

            // Later, when used:
            cards.return_Community_Chest_Card(chest_Card);
            System.out.println("Card returned to bottom of deck.");

        }

    }
    
    public static void main(String[] args) 
    {
        
        cards.shuffle();

        int player_Choice = 0;

        while (player_Choice != 4) 
        { 

            player_Choice = input.getInt("\nWhat would you like to do? \n[1] Check Player Stats \n[2] Upgrade Property \n[3] Draw Card \n[4]Exit \n\n");

            switch (player_Choice) 
            {

            case 1:

                int target_Print_Index = input.getInt("\nEnter the number of the Player you want to check (Type 0 to see all).\n");
                players.print_Players_Stats(target_Print_Index);
                
                break;

                case 2:

                break;

                case 3:

                    draw_Chance_Card();

                break;

                case 4:

                    System.out.print("Until next time! Bye!");
                    
                break;

                default:

                throw new AssertionError();

            }
        }
    }
}