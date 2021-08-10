public class Board {
    private final int[][] boardState;
    int size;
    public final static int EMPTY = 0;
    public final static int BLACK = 1;
    public final static int WHITE = 2;

    Board(int size) {
        this.size = size;
        boardState = new int[size][size];
        for (int i = 0; i < size; i++)
            for (int j = 0; j < size; j++)
                boardState[i][j] = EMPTY;
        int pivot = size / 2;
        boardState[pivot][pivot] = WHITE;
        boardState[pivot - 1][pivot - 1] = WHITE;
        boardState[pivot - 1][pivot] = BLACK;
        boardState[pivot][pivot - 1] = BLACK;
    }

    public String toString() {
        StringBuilder output = new StringBuilder(" ");
        for (int i = 0; i < size; i++) {
            output.append(" ").append((char) (65 + i));
        }
        output.append("  \n");
        for (int i = 0; i < size; i++) {
            output.append(i + 1).append(" ");
            for (int j = 0; j < size; j++) {
                if (boardState[i][j] == EMPTY)
                    output.append(".");
                else if (boardState[i][j] == WHITE)
                    output.append("O");
                else if (boardState[i][j] == BLACK)
                    output.append("X");
                output.append(" ");
            }
            output.append(i + 1);
            output.append("\n");
        }
        output.append(" ");
        for (int i = 0; i < size; i++) {
            output.append(" ").append((char) (65 + i));
        }
        output.append("  \n");
        return output.toString();
    }

    public static int opponentOf(int player) {
        if (player == WHITE)
            return BLACK;
        return WHITE;
    }

    public static String nameOf(int player) {
        if (player == WHITE)
            return "White";
        return "Black";
    }

    public int getScore() {
        int blackPieces = 0, whitePieces = 0;
        for (int i = 0; i < size; i++) {
            for (int j = 0; j < size; j++) {
                if (boardState[i][j] == WHITE)
                    whitePieces++;
                else if (boardState[i][j] == BLACK)
                    blackPieces++;
            }
        }
        return blackPieces - whitePieces;
    }

    public int getLead() {
        if(getScore()>0)
            return 1;
        else if (getScore()<0)
            return 2;
        return 0;
    }

    private boolean checkFlip(int row, int column, int rowChange, int columnChange, int player) {
        int opponent = opponentOf(player);
        try {
            if (boardState[row][column] == opponent) {
                while (row < size && column < size) {
                    row += rowChange;
                    column += columnChange;
                    if (boardState[row][column] == EMPTY)
                        return false;
                    if (boardState[row][column] == player)
                        return true;
                }
            }
        } catch (Exception ignored) {
        }
        return false;
    }

    public MoveList allValidMoves(int player) {
        MoveList validMoves = new MoveList();
        DirectionList directionList = DirectionList.allDirections();
        for (int i = 0; i < size; i++) {
            for (int j = 0; j < size; j++) {
                Move move = new Move(i, j, new DirectionList());
                if (boardState[i][j] == EMPTY) {
                    for (int k = 0; k < directionList.length(); k++) {
                        int rowChange = directionList.getDirection(k).getRowChange();
                        int columnChange = directionList.getDirection(k).getColumnChange();
                        int row = move.row() + rowChange;
                        int column = move.column() + columnChange;
                        if (checkFlip(row, column, rowChange, columnChange, player)) {
                            move.directionList.addDirection(new Direction(rowChange, columnChange));
                            validMoves.addMove(move);
                        }
                    }
                }
            }
        }
        return validMoves;
    }

    private void flipPieces(int row, int column, int rowChange, int columnChange, int player) {
        while (boardState[row][column] == opponentOf(player)) {
            boardState[row][column] = player;
            row += rowChange;
            column += columnChange;
        }
    }

    public void makeMove(int player, Move theMove) {
        boardState[theMove.row()][theMove.column()] = player;
        DirectionList directionList = theMove.directions();
        for (int i = 0; i < directionList.length(); i++) {
            int rowChange = directionList.getDirection(i).getRowChange();
            int columnChange = directionList.getDirection(i).getColumnChange();
            int row = theMove.row() + rowChange;
            int column = theMove.column() + columnChange;
            flipPieces(row, column, rowChange, columnChange, player);
        }
    }

    public boolean gameOver() {
        return allValidMoves(BLACK).isEmpty() && allValidMoves(WHITE).isEmpty();
    }
}
