public record Direction(int rowChange, int columnChange) {

    public int getRowChange() {
        return rowChange;
    }

    public int getColumnChange() {
        return columnChange;
    }

    public String toString() {
        String output = "";
        if (rowChange == 0 && columnChange == 1)
            output = "<right>";
        else if (rowChange == 0 && columnChange == -1)
            output = "<left>";
        else if (rowChange == -1 && columnChange == 0)
            output = "<up>";
        else if (rowChange == 1 && columnChange == 0)
            output = "<down>";
        else if (rowChange == -1 && columnChange == -1)
            output = "<up left>";
        else if (rowChange == 1 && columnChange == -1)
            output = "<down left>";
        else if (rowChange == -1 && columnChange == 1)
            output = "<up right>";
        else if (rowChange == 1 && columnChange == 1)
            output = "<down right>";
        return output;
    }
}