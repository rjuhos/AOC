package aoc2023.day08;

public class Node {
    private String name;
    private String dirL;
    private String dirR;

    public String getName() { return name; }

    public String getDirL() { return dirL; }

    public String getDirR() { return dirR; }

    public Node(String inString) {
        String[] s = inString.replaceAll("1","A")
                             .replaceAll("2","B")
                             .replaceAll("[^A-Z]+",",")
                             .replaceAll(",+", ":")
                             .split(":");
        this.name = s[0];
        this.dirL = s[1];
        this.dirR = s[2];
    }
}
