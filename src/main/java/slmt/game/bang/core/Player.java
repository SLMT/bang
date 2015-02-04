package slmt.game.bang.core;

import java.util.LinkedList;
import java.util.List;

import slmt.game.bang.core.cards.Card;
import slmt.game.bang.core.characters.GameCharacter;
import slmt.game.bang.core.roles.Role;
import slmt.game.bang.ui.Logger;

public class Player {

	// abilities
	private Role role;
	private GameCharacter character;

	// status
	private int HP;
	private boolean isDead = false;

	// cards
	private LinkedList<Card> hand;
	private LinkedList<Card> equipments;

	public Player(Role role, GameCharacter character) {
		this.role = role;
		this.character = character;

		this.HP = character.getInitHP() + role.getBonusHP();
		this.hand = new LinkedList<Card>();
		this.equipments = new LinkedList<Card>();
	}

	public void drawInitialCards(LinkedList<Card> deck) {
		int num = character.getInitHP() + role.getBonusHP();
		for (int i = 0; i < num; i++)
			hand.add(deck.poll());

		Logger.log(character.toString() + " have " + num + " cards.");
	}

	public void draw(LinkedList<Card> deck) {
		int num = character.getNumOfDraw();
		for (int i = 0; i < num; i++)
			hand.add(deck.poll());

		Logger.log(character.toString() + " draw " + num + " cards.");
	}

	/**
	 * Play a specified card to a specified player.
	 * 
	 * @param index
	 *            the index of the card in hand
	 * @param targetPlayer
	 *            the target player
	 * @return the card this player just used.
	 */
	public Card playACard(int index, Player targetPlayer) {
		Card card = hand.remove(index);
		
		Logger.log(character.toString() + " use " + card + " to " + targetPlayer.getCharacter() + ".");
		
		card.use(this, targetPlayer);
		return card;
	}
	
	public Card discardACard(int index) {
		return hand.remove(index);
	}

	public void beBanged() {
		this.HP--;
		Logger.log(character.toString() + " get 1 damage and leave " + HP + " HP.");
		
		if (this.HP <= 0) {
			isDead = true;
			Logger.log(character.toString() + " is dead.");
		}
	}

	public String printHand() {
		return printCards(hand);
	}

	public String printEquipments() {
		return printCards(equipments);
	}

	public int getHp() {
		return HP;
	}
	
	public boolean isDead() {
		return isDead;
	}

	public GameCharacter getCharacter() {
		return character;
	}

	public Role getRole() {
		return role;
	}

	@Override
	public String toString() {
		return role.toString() + ", " + character.toString();
	}

	private String printCards(List<Card> cards) {
		if (cards.isEmpty())
			return "No cards";

		StringBuilder sb = new StringBuilder();
		int index = 1;
		for (Card card : cards) {
			sb.append(index++);
			sb.append(" ");
			sb.append(card);
			sb.append(", ");
		}
		return sb.substring(0, sb.length() - 2);
	}
}
