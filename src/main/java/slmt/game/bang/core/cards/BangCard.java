package slmt.game.bang.core.cards;

import slmt.game.bang.core.Player;


public class BangCard implements Card {
	
	@Override
	public void useFunction(Player usingPlayer, Player targetPlayer) {
		targetPlayer.beBang();
	}
	
	@Override
	public String toString() {
		return "Bang!";
	}

}
