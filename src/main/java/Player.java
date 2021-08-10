public abstract class Player {
    private String name;
    private String pronoun;
    private int playerColour;

    Player(int playerColour) {
        name = "You";
        pronoun = "are";
        this.playerColour = playerColour;
    }

    Player(String name, String pronoun, int playerColour) {
        this.name = name;
        this.pronoun = pronoun;
        this.playerColour = playerColour;
    }

    public String getName() {
        return this.name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPronoun() {
        return this.pronoun;
    }

    public void setPronoun(String pronoun) {
        this.pronoun = pronoun;
    }

    public int getPlayerColour() {
        return this.playerColour;
    }

    public String getPlayerColourString() {
        if (playerColour == 1) {
            return "BLACK X";
        } else {
            return "WHITE O";
        }
    }

    public void setPlayerColour(int playerColour) {
        this.playerColour = playerColour;
    }

    public abstract Move getMove(Board board);

    @Override
    public String toString() {
        return getName() + " " + getPlayerColourString();
    }
}
