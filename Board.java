package minesweeper;

import java.util.*;

public class Board {
    Scanner sc = new Scanner(System.in);
    int numberOfMines;
    List<String> board = new ArrayList<>(Collections.nCopies(81, "."));
    Random random = new Random();

    Board() {
        System.out.println("How many mines do you want on the field?");
        this.numberOfMines = sc.nextInt();
        initializeMines();
        initializeHints();
        printBoard();
    }

    private void initializeHints() {
        for (int i = 0; i < board.size(); i++) {
            if (board.get(i).equals(".")) {
                int mines = countMines(i);
                if (mines > 0) {
                    board.set(i, String.valueOf(mines));
                }
            }
        }
    }

    private int countMines(int i) {
        return checkRightSide(i) +
                checkLeftSide(i) +
                checkUp(i) +
                checkDown(i);
    }

    private int checkDown(int i) {
        if ((i < 72) && board.get(i + 9).equals("X")) {
            return 1;
        }
        return 0;
    }

    private int checkUp(int i) {
        if ((i > 8) && board.get(i - 9).equals("X")) {
            return 1;
        }
        return 0;
    }

    private int checkLeftSide(int i) {
        int result = 0;
        if (i % 9 != 0) {
            if (board.get(i - 1).equals("X")) {
                result++;
            }
            return result + checkUp(i - 1) + checkDown(i - 1);
        }
        return 0;
    }

    private int checkRightSide(int i) {
        int result = 0;
        if (((i + 1) % 9) != 0) {
            if (board.get(i + 1).equals("X")) {
                result++;
            }
            return result + checkUp(i + 1) + checkDown(i + 1);
        }
        return 0;
    }

    private void printBoard() {
        for (int i = 0; i < board.size(); i++) {
            System.out.print(board.get(i));
            if ((i + 1) % 9 == 0) {
                System.out.println();
            }
        }
    }

    private void initializeMines() {
        int rand;
        for (int i = 0; i < numberOfMines; i++) {
            while (true){
                rand = random.nextInt(81);
                if (board.get(rand).equals(".")){
                    board.set(rand, "X");
                    break;
                }
            }
        }
    }
}
