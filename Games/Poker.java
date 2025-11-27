/*************************************************************************************
 *  A Console based poker game by Dana Muise (SFSU 913048115) 6/2016
 *  This program is used to test MyPokerGame class
 *  default credit is $100
 *  
 **************************************************************************************/

package Games;
import Games.Poker_Assets.MyPokerGame;

class Poker {
    public static void main(String args[]) 
    {
	MyPokerGame mypokergame;
	if (args.length > 0)
		mypokergame = new MyPokerGame(Integer.parseInt(args[0]));
	else
		mypokergame = new MyPokerGame();
	mypokergame.play();
    } 
}