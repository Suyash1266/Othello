public class Move {
    private final int rowNumber;
    private final int columnNumber;
    DirectionList directionList;

    Move(int rno, int cno, DirectionList directionList) {
        rowNumber = rno;
        columnNumber = cno;
        this.directionList = directionList;
    }

    public int row() {
        return rowNumber;
    }

    public int column() {
        return columnNumber;
    }

    public DirectionList directions() {
        return directionList;
    }

    public String toString() {
        String output;
        output = (char) (columnNumber + 65) + "" + (rowNumber + 1) + " flips directions " + directionList.toString();
        return output;
    }
}
