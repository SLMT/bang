package slmt.game.bang.core.characters;

public class DummyCharacter implements GameCharacter {

	@Override
	public int getInitHP() {
		return 4;
	}

	@Override
	public String toString() {
		return "Useless Person";
	}
}
