package Games.Monopoly_Assets;

import Games.Input_Handling;

// Rolls two six-sided dice like in real monopoly
// Used for moving players around the board
public class Dice
{
    private static final Input_Handling input = new Input_Handling();
    private int lastRoll;
    private int die1;
    private int die2;

    // Initialize the dice roller
    public Dice()
    {
        lastRoll = 0;
    }

    // Roll both dice and return the sum (2-12)
    public int rollDice()
    {

        die1 = input.randomize(6) + 1;
        die2 = input.randomize(6) + 1;

        lastRoll = die1 + die2;

        return lastRoll;

    }

    // Get the result of the last roll without rolling again
    public int getLastRoll()
    {
        return lastRoll;
    }

    // Check if the player rolled doubles 
    public boolean isDoubles()
    {
        return die1 == die2;
    }

    public int getDie1() 
    {
        return die1;
    }

    public int getDie2() 
    {
        return die2;
    }
}