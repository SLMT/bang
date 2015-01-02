package slmt.game.bang.core;

import java.util.LinkedList;
import java.util.List;
import java.util.Random;

import slmt.game.bang.core.cards.BangCard;
import slmt.game.bang.core.cards.Card;
import slmt.game.bang.core.characters.DummyCharacter;
import slmt.game.bang.core.characters.GameCharacter;
import slmt.game.bang.core.roles.DeputySheriff;
import slmt.game.bang.core.roles.Outlaw;
import slmt.game.bang.core.roles.Renegade;
import slmt.game.bang.core.roles.Role;
import slmt.game.bang.core.roles.Sheriff;

public class Game {
	
	private Player[] players;
	private Card[] deck;
	
	public Game(int numOfPlayers) {
		if (numOfPlayers < 4 || numOfPlayers > 8)
			throw new IllegalArgumentException("the number of players must be in 4 ~ 8");
		
		setUp(numOfPlayers);
	}
	
	private void setUp(int numOfPlayers) {
		// Decide roles
		Role[] roles = getInitialRoles(numOfPlayers);
		shuffle(roles);
		
		// Decide characters
		GameCharacter[] chs = getInitialCharacters();
		shuffle(chs);
		
		// Create players
		players = new Player[numOfPlayers];
		for (int i = 0; i < numOfPlayers; i++)
			players[i] = new Player(roles[i], chs[i]);
		
		// Create deck
		deck = getInitialCards();
		shuffle(deck);
	}
	
	private void shuffle(Object[] objs) {
		Random random = new Random();
		
		for (int i = 0; i < objs.length; i++) {
			// swap an object with another randomly chosen object
			int another = random.nextInt(objs.length);
			Object tmp = objs[i];
			objs[i] = objs[another];
			objs[another] = tmp;
		}
	}
	
	private Role[] getInitialRoles(int numOfPlayers) {
		List<Role> roles = new LinkedList<Role>();
		
		// Add basic roles
		roles.add(new Sheriff());
		roles.add(new Renegade());
		roles.add(new Outlaw());
		roles.add(new Outlaw());
		
		// Add other roles according to the number of players
		Role[] otherRoles = new Role[] {new DeputySheriff(), new Outlaw(),
				new DeputySheriff(), new Renegade() };
		for (int i = 0; i < numOfPlayers - 4; i++)
			roles.add(otherRoles[i]);
		
		return roles.toArray(new Role[numOfPlayers]);
	}
	
	private GameCharacter[] getInitialCharacters() {
		// XXX: Update after adding other characters
		GameCharacter[] chs = new GameCharacter[8];
		for (int i = 0; i < chs.length; i++)
			chs[i] = new DummyCharacter();
		return chs;
	}
	
	private Card[] getInitialCards() {
		// XXX: Update after adding other cards
		Card[] cards = new Card[52];
		for (int i = 0; i < cards.length; i++)
			cards[i] = new BangCard();
		return cards;
	}
}
