package AOC2022.Day10;

public class Display {
    private char[][] pixels = new char[6][40];
    private char lit = '█';
    private char dark = ' ';
    private int sparkPosition = 1;
    private int clockAcc = 0;
    private boolean clockEvent = false;
    private int clockEventValue = 0;

    public Display() {
        for (int r = 0; r < pixels.length; r++)
            for (int c = 0; c < pixels[0].length; c++)
                pixels[r][c] = dark;
    }

    public void cmdAddX(int valueX) {
        incrementClock();
        incrementClock();
        sparkPosition += valueX;
    }

    public void cmdNoop() {
        incrementClock();
    }

    public boolean isClockEvent() {
        return clockEvent;
    }

    public int getClockEventValue() {
        clockEvent = false;
        return clockEventValue;
    }

    public void printOut() {
        System.out.println();
        for (int r = 0; r < pixels.length; r++) {
            StringBuilder outString = new StringBuilder();
            for (int c = 0; c < pixels[0].length; c++) {
                outString.append(pixels[r][c]);
            }
            System.out.println(outString);
        }
        System.out.println();
    }

    private void incrementClock() {
        int pixelRow = clockAcc / 40;
        int pixelCol = clockAcc % 40;

        if (Math.abs(sparkPosition - pixelCol) <= 1) setPixel(pixelRow, pixelCol);
        else clearPixel(pixelRow, pixelCol);

        clockAcc++;
        if (((clockAcc - 20) % 40) == 0) {
            clockEvent = true;
            clockEventValue = sparkPosition * clockAcc;
        }
    }

    private void setPixel(int row, int col) {
        if (row >= 0 && row < pixels.length && col >= 0 && col < pixels[0].length)
            pixels[row][col] = lit;
    }

    private void clearPixel(int row, int col) {
        if (row >= 0 && row < pixels.length && col >= 0 && col < pixels[0].length)
            pixels[row][col] = dark;
    }

}