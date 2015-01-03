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
import slmt.game.bang.ui.Logger;

public class Game {

	private Player[] players;
	private LinkedList<Card> deck;
	private LinkedList<Card> usedCards;

	// status
	private int currentPlayer = 1; // start from 1

	public Game(int numOfPlayers) {
		if (numOfPlayers < 4 || numOfPlayers > 8)
			throw new IllegalArgumentException(
					"the number of players must be in 4 ~ 8");

		Logger.log("Game set up with " + numOfPlayers + " players.");
		setUp(numOfPlayers);
		Logger.log("Game starts!!");
	}

	public void drawCards() {
		getCurrentPlayer().draw(deck);
	}
	
	public void useCard(int index, int targetPlayer) {
		Player curP = getCurrentPlayer();
		Player tarP = players[targetPlayer - 1];
		
		Card usedCard = curP.useCard(index, tarP);
		usedCards.add(usedCard);
	}
	
	public String printCurrentPlayerStatus() {
		StringBuilder sb = new StringBuilder();
		Player player = getCurrentPlayer();
		
		sb.append("Name: " + player.getCharacter() + "\n");
		sb.append("Role: " + player.getRole() + "\n");
		sb.append("HP: " + player.getHp() + "\n");
		sb.append("Hand: " + player.printHand() + "\n");
		sb.append("Equipments: " + player.printEquipments() + "\n");
		
		return sb.toString();
	}

	public int getDistance(int p1, int p2) {
		int distance1 = p1 - p2;
		int distance2 = p2 - p1;

		distance1 = (distance1 < 0) ? distance1 + players.length : distance1;
		distance2 = (distance2 < 0) ? distance2 + players.length : distance2;

		return Math.min(distance1, distance2);
	}

	private void setUp(int numOfPlayers) {
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
	
	private Player getCurrentPlayer() {
		return players[currentPlayer - 1];
	}
}
