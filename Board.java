package minesweeper;

import java.util.*;

public class Board {
    Scanner sc = new Scanner(System.in);
    int numberOfMines;
    Random random = new Random();

    static List<Cell> board = new ArrayList<>();

    Board() {
        System.out.println("How many mines do you want on the field?");
        this.numberOfMines = sc.nextInt();

        initializeBoard();
        initializeMines();
        initializeHints();
        printBoard();
    }

    private void initializeBoard() {
        for (int i = 0; i < 81; i++) {
            board.add(new Cell());
        }
    }

    private void initializeMines() {
        int rand;
        for (int i = 0; i < numberOfMines; i++) {
            while (true){
                rand = random.nextInt(81);
                if (board.get(rand).isEmpty()){
                    board.get(rand).setMine();
                    break;
                }
            }
        }
    }

    private void initializeHints() {
        for (int i = 0; i < board.size(); i++) {
            if (board.get(i).isEmpty()) {
                board.get(i).setAmountOfMineAround(i);
            }
        }
    }

    private void printBoard() {
        System.out.print("\n |123456789|\n" +
                "-|---------|\n1|");
        for (int i = 0; i < board.size(); i++) {
                System.out.print(board.get(i));
            if ((i + 1) % 9 == 0 && i < 80) {
                System.out.print("|\n" + ((i / 9) + 2) + "|");
            }
        }
        System.out.print("|\n-|---------|\n");
    }

    public void start() {
        int x;
        int y;

        while (true) {
            System.out.print("Set/delete mines marks (x and y coordinates):");
            x = sc.nextInt();
            y = sc.nextInt();
            placeMark(x, y);
            if (checkIfAllMinesMarked()) {
                System.out.println("Congratulations! You found all mines!");
                break;
            };
        }
    }

    private boolean checkIfAllMinesMarked() {
        int foundMines = 0;
        int markedCells = 0;
        for (Cell cell : board) {
            if (cell.isMine() && cell.isMarked()) {
                foundMines++;
            }
            if (cell.isMarked()) {
                markedCells++;
            }
        }
        return markedCells == foundMines && markedCells == numberOfMines;
    }

    private void placeMark(int x, int y) {
        int position = (x - 1) + (y - 1) * 9;
        if (board.get(position).isNumber()) {
            System.out.println("There is a number here!");
        } else {
            board.get(position).toggle();
            printBoard();
        }
    }
}
