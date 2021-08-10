import java.io.BufferedReader;
import java.io.InputStreamReader;

public class HumanPlayer extends Player {

    HumanPlayer(int playerColour) {
        super(playerColour);
    }

    HumanPlayer(String name,int playerColour) {
        super(name, "is", playerColour);
    }

    @Override
    public Move getMove(Board board) {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        MoveList validMoves = board.allValidMoves(super.getPlayerColour());
        if(validMoves.isEmpty())
            return null;
        System.out.println("List of moves you can make");
        System.out.println(validMoves);
        System.out.println("\nEnter a number to play the corresponding move");
        int moveIndex;
        while (true) {
            try {
                moveIndex = Integer.parseInt(reader.readLine());
                if (moveIndex > validMoves.getSize() || moveIndex < 1)
                    throw new Exception("Invalid Choice");
                break;
            } catch (Exception e) {
                System.out.println("Enter a valid choice");
            }
        }
        return validMoves.getMove(moveIndex);
    }

}