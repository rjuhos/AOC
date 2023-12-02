package AOC2021.Day12;

import java.util.ArrayList;
import java.util.List;

public class Cave {
    private List<String> adjacent;
    private List<Integer> visited;
    private String name;
    private boolean root;
    private boolean end;
    private boolean bigCave;

    private String[] adjacentNames;
    private int[] visitCount;

    public Cave(String inString) {
        name = inString;
        adjacent = new ArrayList<>();
        visited = new ArrayList<>();
        root = inString.equals("start");
        end = inString.equals("end");
        bigCave = !root && !end && Character.isUpperCase(inString.charAt(0));
    }

    public void addAdjacent(String inString) {
        if (!this.adjacent.contains(inString)) {
            adjacent.add(inString);
            visited.add(1);
            adjacentNames = adjacent.toArray(new String[0]);
            visitCount = visited.stream().mapToInt(Integer::intValue).toArray();
        }
    }

    public String getName() {
        return name;
    }

    public boolean isStart() {
        return root;
    }

    public boolean isEnd() {
        return end;
    }

    public boolean isBigCave() {
        return bigCave;
    }

    public boolean hasNextAdjacent () {
        for (int i=0; i< visited.size(); i++)
            if (visitCount[i] > 0) return true;
        return false;
    }

    public String getNextAdjacent() {
        for (int i=0; i< visited.size(); i++)
            if (visitCount[i] > 0) return (adjacent.get(i));
        return null;
    }

    public List<String> getAdjacentList() {
        return adjacent;
    }

    public void setVisited(String name) {
        if (adjacent.contains(name)) {
            int i = adjacent.indexOf(name);
            visitCount[i]--;
        }
    }

    public void addVisit(List<String> caveNames, List<Boolean> smallCaves ) {
        for (int i=0; i<adjacent.size(); i++) {
            boolean smallCave = smallCaves.get(caveNames.indexOf(adjacent.get(i)));
            if (smallCave && (visitCount[i] > 0)) {
                visitCount[i]++;
                break;
            }
        }
    }

}
