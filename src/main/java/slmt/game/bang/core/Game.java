package slmt.game.bang.core;

import java.util.LinkedList;
import java.util.List;
import java.util.Random;

import slmt.game.bang.core.cards.BangCard;
import slmt.game.bang.core.cards.Card;
import slmt.game.bang.core.characters.DummyCharacter;
import slmt.game.bang.core.characters.GameCharacter;
import slmt.game.bang.core.listeners.GameEventListener;
import slmt.game.bang.core.listeners.InputRequestListener;
import slmt.game.bang.core.roles.DeputySheriff;
import slmt.game.bang.core.roles.Outlaw;
import slmt.game.bang.core.roles.Renegade;
import slmt.game.bang.core.roles.Role;
import slmt.game.bang.core.roles.Sheriff;
import slmt.game.bang.ui.Logger;

public class Game {

	private Player[] players;
	private LinkedList<Card> deck;
	private LinkedList<Card> usedCards;

	// status
	private int nextPlayerNum; // start from 1
	
	// UI Listeners
	private GameEventListener eventListener;
	private InputRequestListener requestListener;
	
	public Game(int numOfPlayers, GameEventListener eventListener, InputRequestListener requestListener) {
		this.eventListener = eventListener;
		this.requestListener = requestListener;
		
		setUp(numOfPlayers);
	}
	
	public Turn nextTurn() {
		int activePlayerNum = -1;
		
		do {
			activePlayerNum = nextPlayerNum;
			// iterater to next number
			nextPlayerNum = (nextPlayerNum < players.length)? nextPlayerNum + 1 : 0;
		} while (getPlayer(activePlayerNum).isDead());
		
		return new Turn(this, activePlayerNum);
	}

	public int getDistance(int p1, int p2) {
		int distance1 = p1 - p2;
		int distance2 = p2 - p1;

		distance1 = (distance1 < 0) ? distance1 + players.length : distance1;
		distance2 = (distance2 < 0) ? distance2 + players.length : distance2;

		return Math.min(distance1, distance2);
	}
	
	/**
	 * Get the i-th player.
	 * 
	 * @param num the index of the player, starting from 1
	 * @return the i-th player
	 */
	Player getPlayer(int num) {
		return players[num - 1];
	}
	
	LinkedList<Card> getDeck() {
		return deck;
	}
	
	void addAUsedCard(Card card) {
		usedCards.add(card);
	}
	
	private void setUp(int numOfPlayers) {
		// Check the number of players
		if (numOfPlayers < 4 || numOfPlayers > 8)
			throw new IllegalArgumentException(
					"the number of players must be in 4 ~ 8");
		
		// Create roles
		Role[] roles = getInitialRoles(numOfPlayers);
		shuffle(roles);

		// Create characters
		GameCharacter[] chs = getInitialCharacters();
		shuffle(chs);

		// Create deck
		Card[] cards = getInitialCards();
		shuffle(cards);
		deck = new LinkedList<Card>();
		for (int i = 0; i < cards.length; i++)
			deck.add(cards[i]);
		
		usedCards = new LinkedList<Card>();
		
		// Create players
		players = new Player[numOfPlayers];
		for (int i = 0; i < numOfPlayers; i++) {
			players[i] = new Player(roles[i], chs[i]);
			Logger.log("Player " + (i + 1) + " is " + players[i]);
			
			// Check whether he is the first player
			if (roles[i].getClass().equals(Sheriff.class))
				nextPlayerNum = i + 1;
		}

		// Draw initial cards for all players
		for (int i = 0; i < numOfPlayers; i++)
			players[i].drawInitialCards(deck);
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
		Role[] otherRoles = new Role[] { new DeputySheriff(), new Outlaw(),
				new DeputySheriff(), new Renegade() };
		for (int i = 0; i < numOfPlayers - 4; i++)
			roles.add(otherRoles[i]);

		return roles.toArray(new Role[numOfPlayers]);
	}

	private GameCharacter[] getInitialCharacters() {
		// XXX: Update after adding other characters
		GameCharacter[] chs = new GameCharacter[8];
		for (int i = 0; i < chs.length; i++)
			chs[i] = new DummyCharacter(i + 1);
		return chs;
	}

	private Card[] getInitialCards() {
		// XXX: Update after adding other cards
		Card[] cards = new Card[100];
		for (int i = 0; i < cards.length; i++)
			cards[i] = new BangCard();
		return cards;
	}
}
