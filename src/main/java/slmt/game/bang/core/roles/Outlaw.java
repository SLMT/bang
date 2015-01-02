package slmt.game.bang.core.roles;

public class Outlaw implements Role {

	@Override
	public int getBonusHP() {
		return 0;
	}

	@Override
	public String toString() {
		return "Outlaw";
	}
}