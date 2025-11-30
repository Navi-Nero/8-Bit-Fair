package Games;
import Games.Poker_Assets.MyPokerGame;

public class Poker 
{
    public static void main(String args[]) 
    {

		MyPokerGame mypokergame;

		if (args.length > 0)
		{

			mypokergame = new MyPokerGame(Integer.parseInt(args[0]));

		} else {
			
			mypokergame = new MyPokerGame();

		}

	mypokergame.play();

    } 
}