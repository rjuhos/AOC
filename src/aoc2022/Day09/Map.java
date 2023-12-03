package aoc2022.Day09;

import java.util.List;

public class Map {
    private int sizeX = 0;
    private int sizeY = 0;
    private int startX = 0;
    private int startY = 0;
    private char[][] map;
    private char cStart = '░';
    private char cVisited = '█';
    private char cNotVisited = '·';


    public Map(List<MoveCmd> commands) {
        createMap(commands);
    };

    public int getSizeX() { return sizeX; }
    public void setSizeX(int sizeX) { this.sizeX = sizeX; }

    public int getSizeY() { return sizeY; }
    public void setSizeY(int sizeY) { this.sizeY = sizeY; }

    public int getStartX() { return startX; }
    public void setStartX(int startX) { this.startX = startX; }

    public int getStartY() { return startY; }
    public void setStartY(int startY) { this.startY = startY; }

    public void setVisited(int posX, int posY) {
        char c = map[posY][posX];
        if (map[posY][posX] == cNotVisited) map[posY][posX] = cVisited;
    }

    public boolean isVisited(int posX, int posY) {
        if (map[posY][posX] != cNotVisited) return true;
        else return false;
    }

    private void createMap(List<MoveCmd> commands) {
        // init values
        int posX = 0; int maxX = 0; int minX = 0;
        int posY = 0; int maxY = 0; int minY = 0;
        // read all commands
        for (MoveCmd cmd : commands) {
            switch (cmd.getDir()) {
                case 'R' -> posX += cmd.getDist();
                case 'L' -> posX -= cmd.getDist();
                case 'U' -> posY += cmd.getDist();
                case 'D' -> posY -= cmd.getDist();
            }
            if (posX > maxX) maxX = posX;
            if (posX < minX) minX = posX;
            if (posY > maxY) maxY = posY;
            if (posY < minY) minY = posY;
        }
        // init map
        sizeX = maxX - minX + 1;
        sizeY = maxY - minY + 1;
        map = new char[sizeY][sizeX];
        for (int y = 0; y <= sizeY - 1; y++)
            for (int x = 0; x < sizeX - 1; x++)
                map[y][x] = cNotVisited;
        // set start
        startX = Math.abs(minX);
        startY = Math.abs(minY);
        map[startY][startX] = cStart;
    }

    public char read(int posX, int posY) {
        return map[posY][posX];
    }
}
