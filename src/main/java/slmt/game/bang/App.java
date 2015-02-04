package slmt.game.bang;

import slmt.game.bang.core.Game;
import slmt.game.bang.core.Turn;

public class App 
{
    public static void main(String[] args)
    {
    	// === Testing code ===
        Game game = new Game(8);
        
        Turn turn = game.nextTurn();
        printStatus(turn);
        
        turn = game.nextTurn();
        printStatus(turn);
    }
    
    private static void printStatus(Turn turn) {
    	System.out.print("===== Player Status =====\n");
        System.out.print(turn.printActivePlayerStatus());
        System.out.print("=========================\n");
    }
}
