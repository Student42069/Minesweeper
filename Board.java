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
        initializeBoard();
        printBoard();
    }

    private void printBoard() {
        for (int i = 0; i < board.size(); i++) {
            System.out.print(board.get(i));
            if ((i + 1) % 9 == 0) {
                System.out.println();
            }
        }
    }

    private void initializeBoard() {
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
