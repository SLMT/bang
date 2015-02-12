package slmt.game.bang.core.listeners;

import java.util.LinkedList;
import java.util.List;

import slmt.game.bang.core.cards.Card;
import slmt.game.bang.core.characters.GameCharacter;
import slmt.game.bang.core.roles.Role;

public class PlayerStatus {

	// For all players
	private int playerNum;
	private Role role;
	private int HP;
	private List<Card> equiments;

	// For the current player
	private GameCharacter character;
	private List<Card> hand;
	
	/**
	 * Saving information for other players.
	 */
	public PlayerStatus(int playerNum, Role role, int HP, List<Card> equiments) {
		this(playerNum, role, null, HP, null, equiments);
	}

	/**
	 * Saving information for the current player.
	 */
	public PlayerStatus(int playerNum, Role role, GameCharacter character,
			int HP, List<Card> hand, List<Card> equiments) {
		this.playerNum = playerNum;
		this.role = role;
		this.character = character;
		this.HP = HP;

		if (hand != null)
			this.hand = new LinkedList<Card>(hand);
		if (equiments != null)
			this.equiments = new LinkedList<Card>(equiments);
	}
	
	public int getPlayerNum() {
		return playerNum;
	}
	
	public Role getRole() {
		return role;
	}
	
	public int getHP() {
		return HP;
	}
	
	public List<Card> getEquiments() {
		return equiments;
	}
	
	public GameCharacter getCharacter() {
		return character;
	}
	
	public List<Card> getHand() {
		return hand;
	}
}
