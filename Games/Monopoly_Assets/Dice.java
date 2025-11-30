package Games.Monopoly_Assets;

import Games.Input_Handling;

// Rolls two six-sided dice like in real monopoly
// Used for moving players around the board
public class Dice
{
    private static final Input_Handling input = new Input_Handling();
    private int lastRoll;

    // Initialize the dice roller
    public Dice()
    {
        
        lastRoll = 0;
    }

    // Roll both dice and return the sum (2-12)
    public int rollDice()
    {

        int die1 = input.randomize(6) + 1;
        int die2 = input.randomize(6) + 1;
        lastRoll = die1 + die2;
        return lastRoll;

    }

    // Get the result of the last roll without rolling again
    public int getLastRoll()
    {
        return lastRoll;
    }

    // Check if the player rolled doubles (same number on both dice)
    // This is useful for getting another turn in monopoly
    public boolean isDoubles()
    {

        int die1 = lastRoll / 2;  // This is a simplified check
        int die2 = lastRoll - die1;
        return die1 == die2;

    }
}
