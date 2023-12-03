package aoc2021.Day04;

public class Board {
    private int id;
    private Integer[][] boardData = new Integer[5][5];
    private Integer[] foundRowMask = new Integer[5];
    private Integer[] foundColumnMask = new Integer[5];
    private Integer foundRow;
    private Integer foundColumn;
    private boolean completed;
    private int rows;

    public Board() {
        clearBoard();
    }

    public void clearBoard() {
        id = id;
        rows = 0;
        for (int row=0; row<5; row++) {
            for (int column=0; column<5; column++) {
                boardData[row][column] = -1;
            }
            foundRowMask[row] = 0;
            foundColumnMask[row] = 0;
            foundRow = 0;
            foundColumn = 0;
            completed = false;
        }
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getId() {
        return id;
    }

    public int getRowCount() {
        return rows;
    }

    public boolean Completed() {
        return this.completed;
    }

    public int FoundRow() {
        return foundRow;
    }

    public int FoundColumn() {
        return foundColumn;
    }

    public int getRowMask(int row) {
        return foundRowMask[row - 1];
    }

    public int getColumnMask(int column) {
        return foundColumnMask[column - 1];
    }

    public void addRow(String string) {
        String[] data = string.trim().replace("  ", " ").split(" ");
        int row = rows;
        if (row< 5) {
            int column = 0;
            for (String d : data) {
                if (column < 5) {
                    try {
                        boardData[row][column] = Integer.parseInt(d);
                    } catch (NumberFormatException e) {
                        boardData[row][column] = -1;
                    }
                }
                column++;
            }
            rows++;
        }
    }

    public boolean findNumber(int number) {
        boolean found = false;
        for (int row=0; row<5; row++) {
            for (int column=0; column<5; column++) {
                if (boardData[row][column] == number) {
                    found = true;
                    foundRowMask[row] = setMaskBit(foundRowMask[row],column);
                    foundColumnMask[column] = setMaskBit(foundColumnMask[column],row);
                    completed = checkMask(foundRowMask[row]) || checkMask(foundColumnMask[column]);
                    foundRow = row + 1;
                    foundColumn = column + 1;
                    break;
                }
            }
            if (found) break;
        }
        return found;
    }

    public long getResult() {
        long result = 0;
        for (int row=0; row<5; row++) {
            for (int column=0; column<5; column++) {
                if (!getMaskBit(foundRowMask[row],column)) {
                    result += boardData[row][column];
                };
            }
        }
        return result;
    }

    public int getNumberAtPosition(int row, int column) {
        if ((row >= 0) && (row <= rows) && (column >= 0) && (column <= 5))
            return this.boardData[row-1][column-1];
        else
            return -1;
    }

    private static int setMaskBit(int mask, int bit) {
        mask |= (int)Math.pow(2,bit);
        return mask;
    }

    private static boolean getMaskBit(int mask, int bit) {
        return ((mask & (int)Math.pow(2,bit)) != 0);
    }

    private static boolean checkMask(int mask) {
        return (mask == ((int)Math.pow(2,5) - 1));
    }

}

