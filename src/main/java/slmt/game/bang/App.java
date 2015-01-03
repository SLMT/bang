package slmt.game.bang;

import slmt.game.bang.core.Game;

public class App 
{
    public static void main( String[] args )
    {
        Game game = new Game(8);
        
        game.drawCards();
        printStatus(game);
        game.useCard(2, 2);
        game.useCard(2, 2);
        game.useCard(2, 2);
        game.useCard(2, 2);
    }
    
    private static void printStatus(Game game) {
    	System.out.print("===== Status =====\n");
        System.out.print(game.printCurrentPlayerStatus());
        System.out.print("==================\n");
    }
}
