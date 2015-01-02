package slmt.game.bang.core;

import slmt.game.bang.core.cards.Card;
import slmt.game.bang.core.characters.GameCharacter;
import slmt.game.bang.core.roles.Role;

public class Player {

	// abilities
	private Role role;
	private GameCharacter character;
	
	// status
	private int HP;
	
	// cards
	private Card hand;
	private Card equipments;
	
	public Player(Role role, GameCharacter character) {
		this.role = role;
		this.character = character;
		
		this.HP = character.getInitHP() + role.getBonusHP();
	}
	
	public int getHp() {
		return HP;
	}
	
	@Override
	public String toString() {
		return role.toString() + ", " + character.toString();
	}
}
