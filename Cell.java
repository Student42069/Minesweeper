package minesweeper;

import static minesweeper.Board.board;

public class Cell {
    final String EMPTY = ".";
    final String MARK = "*";

    private boolean mine = false;
    private String state = EMPTY;
    private int minesAround = 0;

    @Override
    public String toString() {
        if (minesAround == 0) {
            return this.state;
        } else {
            return String.valueOf(minesAround);
        }
    }

    public void setMine() {
        this.mine = true;
    }

    public boolean isMine() {
        return this.mine;
    }

    public boolean isEmpty() {
        return !this.mine;
    }

    public boolean isNumber() {
        return this.minesAround > 0;
    }

    public boolean isMarked() {
        return this.state.equals(MARK);
    }

    public void toggle() {
        if (this.state.equals(MARK)) {
            this.state = EMPTY;
        } else {
            this.state = MARK;
        }
    }

    public void setAmountOfMineAround(int i) {
        this.minesAround = countMines(i);
    }

    private int countMines(int i) {
        return checkRightSide(i) +
                checkLeftSide(i) +
                checkUp(i) +
                checkDown(i);
    }

    private int checkDown(int i) {
        if ((i < 72) && board.get(i + 9).isMine()) {
            return 1;
        }
        return 0;
    }

    private int checkUp(int i) {
        if ((i > 8) && board.get(i - 9).isMine()) {
            return 1;
        }
        return 0;
    }

    private int checkLeftSide(int i) {
        int result = 0;
        if (i % 9 != 0) {
            if (board.get(i - 1).isMine()) {
                result++;
            }
            return result + checkUp(i - 1) + checkDown(i - 1);
        }
        return 0;
    }

    private int checkRightSide(int i) {
        int result = 0;
        if (((i + 1) % 9) != 0) {
            if (board.get(i + 1).isMine()) {
                result++;
            }
            return result + checkUp(i + 1) + checkDown(i + 1);
        }
        return 0;
    }
}
