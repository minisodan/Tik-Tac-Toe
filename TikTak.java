import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Random;
import java.util.Scanner;

public class TikTak {

    static ArrayList<Integer> playerPositions = new ArrayList<Integer>();
    static ArrayList<Integer> cpuPositions = new ArrayList<Integer>();

    public static void main(String[] args) {
        char[][] gameBoard = {
                // Game board
                { ' ', '|', ' ', '|', ' ' },
                { '-', '+', '-', '+', '-' },
                { ' ', '|', ' ', '|', ' ' },
                { '-', '+', '-', '+', '-' },
                { ' ', '|', ' ', '|', ' ' }
        };

        while (true) {
            // Setting the enviroment for players input and CPU's random value.
            Scanner input = new Scanner(System.in);
            System.out.println("Pick a Placement 1-9: ");
            int playerPos = input.nextInt();
            while (playerPositions.contains(playerPos) || cpuPositions.contains(playerPositions)) {
                System.out.println("Position taken! try again: ");
                playerPos = input.nextInt();

            }
            positionGame(gameBoard, playerPos, "Player");
            String result = checkWinner();
            if (result.length() > 0) {
                System.out.println(result);
                break;
            }

            Random rand = new Random();
            int cpuPos = rand.nextInt(9) + 1;
            while (playerPositions.contains(cpuPos) || cpuPositions.contains(cpuPos)) {
                cpuPos = rand.nextInt(9) + 1;

            }
            positionGame(gameBoard, cpuPos, "CPU");
            printGameBoard(gameBoard);

            result = checkWinner();
            if (result.length() > 0) {
                System.out.println(result);
                break;

            }

        }

    }

    public static void printGameBoard(char[][] gameBoard) {
        for (char[] row : gameBoard) {
            for (char c : row) {
                System.out.print(c);
            }
            System.out.println();
        }
        // Printing positions and gameboard.

    }

    public static void positionGame(char[][] gameBoard, int pos, String user) {

        // initializing a char for user and CPU
        char symbol = ' ';

        if (user.equals("Player")) {
            symbol = 'X';
            playerPositions.add(pos);
        } else if (user.equals("CPU")) {
            symbol = 'O';
            cpuPositions.add(pos);
        }

        // picking position for Player or CPU
        switch (pos) {
            case 1:
                gameBoard[0][0] = symbol;
                break;
            case 2:
                gameBoard[0][2] = symbol;
                break;
            case 3:
                gameBoard[0][4] = symbol;
                break;
            case 4:
                gameBoard[2][0] = symbol;
                break;
            case 5:
                gameBoard[2][2] = symbol;
                break;
            case 6:
                gameBoard[2][4] = symbol;
                break;
            case 7:
                gameBoard[4][0] = symbol;
                break;
            case 8:
                gameBoard[4][2] = symbol;
                break;
            case 9:
                gameBoard[4][4] = symbol;
                break;
            default:
                break;
        }
    }

    public static String checkWinner() {

        // rows
        List topRow = Arrays.asList(1, 2, 3);
        List midRow = Arrays.asList(4, 5, 6);
        List botRow = Arrays.asList(7, 8, 9);

        // cols
        List topCol = Arrays.asList(1, 4, 7);
        List midCol = Arrays.asList(2, 5, 8);
        List botCol = Arrays.asList(3, 6, 9);

        // cross
        List cros1 = Arrays.asList(1, 5, 9);
        List cros2 = Arrays.asList(3, 5, 7);

        List<List> winning = new ArrayList<List>();
        winning.add(topRow);
        winning.add(midRow);
        winning.add(botRow);
        winning.add(topCol);
        winning.add(midCol);
        winning.add(botCol);
        winning.add(cros1);
        winning.add(cros2);

        for (List l : winning) {
            if (playerPositions.containsAll(l)) {
                return "Congradulations you won";
            } else if (cpuPositions.containsAll(l)) {
                return "Congradulations you lost to a computer";
            }
        }
        if (playerPositions.size() + cpuPositions.size() == 9) {
            return "CAT'S GAME";
        }

        return "";

    }
}
