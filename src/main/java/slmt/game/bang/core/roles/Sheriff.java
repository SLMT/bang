package slmt.game.bang.core.roles;

public class Sheriff implements Role {

	@Override
	public int getBonusHP() {
		return 1;
	}
	
	@Override
	public String toString() {
		return "Sheriff";
	}

}
