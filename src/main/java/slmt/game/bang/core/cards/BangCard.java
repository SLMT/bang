package slmt.game.bang.core.cards;

import slmt.game.bang.core.Player;


public class BangCard implements Card {
	
	@Override
	public void use(Player usingPlayer, Player targetPlayer) {
		targetPlayer.beBanged();
	}
	
	@Override
	public String toString() {
		return "Bang!";
	}

}
