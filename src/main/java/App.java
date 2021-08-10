import java.io.*;
import java.util.Scanner;

class OutOfRangeException extends Exception {
    OutOfRangeException() {
        super("Entered value is out of range");
    }
}

public class App {

    static Board getBoard() {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        System.out.println("Choose board size");
        System.out.println("4 -> 4 X 4");
        System.out.println("6 -> 6 X 6");
        System.out.println("8 -> 8 X 8");
        int size;
        while (true) {
            try {
                size = Integer.parseInt(reader.readLine());
                if (size != 4 && size != 6 && size != 8)
                    throw new OutOfRangeException();
                break;
            } catch (NumberFormatException e) {
                System.out.println("Please enter a valid number.");
            } catch (IOException e) {
                System.out.println("Error encountered. Please try again.");
            } catch (OutOfRangeException e) {
                System.out.println(e.getMessage());
                System.out.println("Please enter again");
            }
        }
        return new Board(size);
    }

    static int getMode() {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        System.out.println("Choose your opponent");
        System.out.println("1. CPU vs You");
        System.out.println("2. Player vs Player");
        int opponent;
        while (true) {
            try {
                opponent = Integer.parseInt(reader.readLine());
                if (opponent != 1 && opponent != 2)
                    throw new OutOfRangeException();
                break;
            } catch (NumberFormatException e) {
                System.out.println("Please enter a valid number.");
            } catch (IOException e) {
                System.out.println("Error encountered. Please try again.");
            } catch (OutOfRangeException e) {
                System.out.println(e.getMessage());
                System.out.println("Please enter again");
            }
        }
        return opponent;
    }

    static int getColour() {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        System.out.println("Choose colour");
        System.out.println("1. BLACK (X) (plays first)");
        System.out.println("2. WHITE (O) (plays after)");
        int colour;
        while (true) {
            try {
                colour = Integer.parseInt(reader.readLine());
                if (colour != 1 && colour != 2)
                    throw new OutOfRangeException();
                break;
            } catch (NumberFormatException e) {
                System.out.println("Please enter a valid number.");
            } catch (IOException e) {
                System.out.println("Error encountered. Please try again.");
            } catch (OutOfRangeException e) {
                System.out.println(e.getMessage());
                System.out.println("Please enter again");
            }
        }
        return colour;
    }

    static String getName() {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        System.out.print("Name: ");
        String name;
        while (true) {
            try {
                name = reader.readLine();
                break;
            } catch (IOException e) {
                System.out.println("Error encountered. Please try again.");
            }
        }
        return name;
    }

    static String leadMessage(Player[] player, int lead, int score) {
        String leadMsg;
        if (score != 0)
            leadMsg = player[lead - 1] + " " + player[lead - 1].getPronoun() + " leading by " + Math.abs(score);
        else
            leadMsg = "Scores Tied";
        return leadMsg;
    }

    public static void clearConsole() {
        try {
            if (System.getProperty("os.name").contains("Windows")) {
                new ProcessBuilder("cmd", "/c", "cls").inheritIO().start().waitFor();
            } else {
                System.out.print("\033\143");
            }
        } catch (IOException | InterruptedException ex) {
            System.out.println("Unable to clear screen");
        }
    }

    public static void promptEnterKey() {
        System.out.println("Press \"ENTER\" to continue...");
        while (true) {
            Scanner scanner = new Scanner(System.in);
            try {
                scanner.nextLine();
                break;
            } catch (Exception ignored) {
            }
            scanner.close();
        }
    }

    static void play() {
        clearConsole();
        System.out.println("Welcome to Othello. Have fun!!!");
        System.out.println();
        Board board = getBoard();
        clearConsole();
        int mode = getMode();
        clearConsole();
        Player[] player = new Player[2];
        if (mode == 1) {
            int colour = getColour();
            player[colour - 1] = new HumanPlayer(colour);
            player[colour % 2] = new CpuPlayer(0, colour % 2 + 1);
        } else {
            System.out.println("Player 1");
            String name = getName();
            int colour = getColour();
            player[colour - 1] = new HumanPlayer(name, colour);
            player[colour % 2] = new HumanPlayer(getName(), colour % 2 + 1);
        }
        clearConsole();
        System.out.println(player[0] + "(plays first)");
        System.out.println();
        System.out.println(player[1] + "(plays after)");
        System.out.println();
        promptEnterKey();
        clearConsole();
        System.out.println("Game Begins");
        int currentPlayer = 1;
        while (!board.gameOver()) {
            System.out.println(leadMessage(player, board.getLead(), board.getScore()));
            System.out.println();
            System.out.println(board);
            System.out.println();
            System.out.println(player[currentPlayer - 1] + "'s turn");
            System.out.println();
            Move move = player[currentPlayer - 1].getMove(board);
            System.out.println();
            if (move == null) {
                System.out.println("No legal moves available. Chance skipped");
            } else {
                board.makeMove(currentPlayer, move);
                System.out.println(player[currentPlayer - 1].getName() + " played " + move);
                System.out.println(leadMessage(player, board.getLead(), board.getScore()));
            }
            promptEnterKey();
            currentPlayer = currentPlayer % 2 + 1;
            clearConsole();
        }
        System.out.println("Game over!!!");
        System.out.println(board);
        int finalScore = board.getScore();
        if (finalScore == 0)
            System.out.println("Game Drawn");
        else
            System.out.println(player[board.getLead() - 1] + " Won by " + Math.abs(finalScore));
    }

    public static void main(String[] args) {
        play();
    }
}