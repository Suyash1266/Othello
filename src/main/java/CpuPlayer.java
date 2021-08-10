public class CpuPlayer extends Player {

    int difficulty;

    CpuPlayer(int difficulty, int colour) {
        super("Computer", "is", colour);
        this.difficulty = difficulty;
    }

    public Move getMove(Board board) {
        if(board.allValidMoves(super.getPlayerColour()).isEmpty())
            return null;
        return board.allValidMoves(super.getPlayerColour()).getMove();
    }
    
    public int getDifficulty() {
        return this.difficulty;
    }

    public void setDifficulty(int difficulty) {
        this.difficulty = difficulty;
    }

}
