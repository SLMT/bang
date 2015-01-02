package slmt.game.bang.core;

import java.util.LinkedList;

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
		int num = HP;
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
	
	public int getHp() {
		return HP;
	}
	
	@Override
	public String toString() {
		return role.toString() + ", " + character.toString();
	}
}
