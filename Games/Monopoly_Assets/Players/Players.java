package Games.Monopoly_Assets.Players;

import Games.Input_Handling;
import java.util.LinkedList;

public class Players {

    private final static Input_Handling input = new Input_Handling();
    private LinkedList<Players_Constructor> player;

    public Players()
    {

        player = new LinkedList<>();
        int number_Of_Players = get_Number_Of_Players();

        for (int i = 0; i < number_Of_Players; i++)
        {
            player.add(new Players_Constructor(i + 1, input_Player_Name(i + 1), 1500, false, false));
        }

    }

    public int get_Number_Of_Players()
    {

        int number_Of_Players = input.getInt("How many players are there? ");
        return number_Of_Players;

    }

     public String input_Player_Name(int index)
    {

        String player_Name = input.getStr("Enter the name for player [" + (index) + "]: ");
        return player_Name;

    }

    public void print_Players_Stats(int index) 
    {

        if (index == 0) 
        {

            System.out.println();

            // Prints all the players
            for (Players_Constructor p : player) 
            {

                System.out.println("Player " + p.get_Player_Index() + ": " + p.get_Player_Name() +
                                " | Money: " + p.get_Player_Money() +
                                " | Assets: " + p.get_Player_Assets());
            }

        } else {

        // Find and print the specific player
            for (Players_Constructor p : player) 
            {

                if (p.get_Player_Index() == index) 
                {

                    System.out.println();

                    System.out.println("Player " + p.get_Player_Index() + ": " + p.get_Player_Name() +
                                    " | Money: " + p.get_Player_Money() +
                                    " | Assets: " + p.get_Player_Assets());
                    return; // stops after printing the match
                }

            }

            // If no player found with that index
            System.out.println("No player found with index " + index);
            
        }
    }
}