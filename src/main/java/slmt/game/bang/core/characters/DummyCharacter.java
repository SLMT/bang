package slmt.game.bang.core.characters;

public class DummyCharacter implements GameCharacter {

	private int num;
	
	public DummyCharacter(int num) {
		this.num = num;
	}
	
	@Override
	public int getInitHP() {
		return 4;
	}
	
	@Override
	public int getNumOfDraw() {
		return 2;
	}

	@Override
	public String toString() {
		return "Useless Person " + num;
	}
}
