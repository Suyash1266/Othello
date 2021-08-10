public class DirectionList {
    private final Direction[] directionList;
    int size;

    DirectionList() {
        directionList = new Direction[8];
        size = 0;
    }

    public void addDirection(Direction d) {
        directionList[size++] = d;
    }

    public int length() {
        return size;
    }

    public Direction getDirection(int i) {
        return directionList[i];
    }

    public String toString() {
        StringBuilder output = new StringBuilder();
        for (int i = 0; i < size; i++) {
            output.append(directionList[i].toString());
            if (i < size - 1)
                output.append(",");
        }
        output = new StringBuilder("{" + output + "}");
        return output.toString();
    }

    public static DirectionList allDirections() {
        DirectionList obj = new DirectionList();
        int index = 0;
        for (int i = -1; i <= 1; i++)
            for (int j = -1; j <= 1; j++)
                if (i != 0 || j != 0)
                    obj.directionList[index++] = new Direction(i, j);
        obj.size = 8;
        return obj;
    }
}
