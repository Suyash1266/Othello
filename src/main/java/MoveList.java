import java.util.HashSet;
import java.util.Random;

public class MoveList {
    private final HashSet<Move> moveList;
    // int size;
    {
        moveList = new HashSet<>();
    }

    public void addMove(Move m) {
        moveList.add(m);
    }

    public int getSize() {
        return moveList.size();
    }

    public String toString() {
        StringBuilder output = new StringBuilder();
        int index = 1;
        for (Move move : moveList) {
            output.append(index++).append(". ").append(move.toString()).append("\n");
        }
        return output.toString();
    }

    public boolean isEmpty() {
        return moveList.isEmpty();
    }

    public Move getMove() {
        if (isEmpty())
            return null;
        Random rand = new Random();
        Move[] moves = moveList.toArray(new Move[0]);
        return moves[rand.nextInt(moveList.size())];
    }

    public Move getMove(int index) {
        if (isEmpty())
            return null;
        Move[] moves = moveList.toArray(new Move[0]);
        return moves[index - 1];
    }
}
