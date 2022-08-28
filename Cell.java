package minesweeper;

import static minesweeper.Board.board;

public class Cell {
    final String HIDDEN = ".";
    final String MARKED = "*";
    final String EMPTY = "/";
    final String MINE = "X";

    private boolean mine = false;
    private boolean showMine = false;
    private boolean marked = false;
    private boolean shown = false;

    private int minesAround = 0;
    private final int index;

    Cell(int i) {
        this.index = i;
    }

    @Override
    public String toString() {
        if (showMine) {
            return MINE;
        } else if (isMarked()) {
            return MARKED;
        } else if (isHidden()) {
            return HIDDEN;
        } else if (isNumber()) {
            return String.valueOf(minesAround);
        }
        return EMPTY;
    }

    public boolean isShown() {
        return shown;
    }

    public boolean isHidden() {
        return !shown;
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
        return marked;
    }

    public void toggle() {
        marked = !marked;
    }

    public void setAmountOfMineAround() {
        this.minesAround = countMines(this.index);
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

    public void showMine() {
        this.showMine = true;
    }

    public void explore() {
        this.marked = false;
        this.shown = true;
        if (!isNumber()) {
            exploreAround();
        }
    }

    private void exploreAround() {
        exploreRightSide();
        exploreLeftSide();
        exploreUp();
        exploreDown();
    }

    private void exploreRightSide() {
        if (((index + 1) % 9) != 0) {
            if (board.get(index + 1).isHidden()){
                board.get(index + 1).explore();
            }
            //Diagonals on right side
            board.get(index + 1).exploreUp();
            board.get(index + 1).exploreDown();
        }
    }

    private void exploreLeftSide() {
        if (index % 9 != 0) {
            if (board.get(index - 1).isHidden()){
                board.get(index - 1).explore();
            }
            //Diagonals on left side
            board.get(index - 1).exploreUp();
            board.get(index - 1).exploreDown();
        }
    }

    private void exploreDown() {
        if ((index < 72)) {
            if (board.get(index + 9).isHidden()){
                board.get(index + 9).explore();
            }
        }
    }

    private void exploreUp() {
        if ((index > 8)) {
            if (board.get(index - 9).isHidden()){
                board.get(index - 9).explore();
            }
        }
    }
}
