package slmt.game.bang.core;

import slmt.game.bang.core.cards.Card;

public class Turn {

	private static enum Action {
		CHECK, DRAW, PLAY, DISCARD, END
	};

	private Game game;
	private int activePlayerNum;
	private Player activePlayer;

	private Action nextAction;

	Turn(Game game, int activePlayerNum) {
		this.game = game;
		this.activePlayerNum = activePlayerNum;
		this.activePlayer = game.getPlayer(activePlayerNum);

		this.nextAction = Action.DRAW;
	}

	public void check() {
		if (nextAction != Action.CHECK)
			throw new IllegalStateException("you should not checking now");

		// TODO: Implement 'Check' action
	}

	public void drawCards() {
		if (nextAction != Action.DRAW)
			throw new IllegalStateException("you should not drawing now");

		// draw
		activePlayer.draw(game.getDeck());

		// change to next action
		nextAction = Action.PLAY;
	}

	public void playCard(int index, int targetPlayerIndex) {
		if (nextAction != Action.PLAY)
			throw new IllegalStateException("you should not playing now");

		// play a card
		Player targetPlayer = game.getPlayer(targetPlayerIndex);
		Card usedCard = activePlayer.playACard(index, targetPlayer);
		game.addAUsedCard(usedCard);
	}

	public void endPlayingAction() {
		if (nextAction != Action.PLAY)
			return;

		// change to next action
		nextAction = Action.DISCARD;
	}

	public void discardCard(int index) {
		if (nextAction != Action.DISCARD)
			throw new IllegalStateException("you should not discard now");
		
		// discard a card
		activePlayer.discardACard(index);
	}

	public void endDiscardingAction() {
		if (nextAction != Action.DISCARD)
			return;

		// change to next action
		nextAction = Action.END;
	}
	
	public String printActivePlayerStatus() {
		StringBuilder sb = new StringBuilder();
		
		sb.append("Number: " + activePlayerNum + "\n");
		sb.append("Name: " + activePlayer.getCharacter() + "\n");
		sb.append("Role: " + activePlayer.getRole() + "\n");
		sb.append("HP: " + activePlayer.getHp() + "\n");
		sb.append("Hand: " + activePlayer.printHand() + "\n");
		sb.append("Equipments: " + activePlayer.printEquipments() + "\n");
		
		return sb.toString();
	}
}
