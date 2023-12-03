package aoc2022.Day04;

public class Assignment {
    public Section first = new Section();
    public Section second = new Section();
    public boolean overlap = false;
    private boolean firstDefined, secondDefined;

    public Assignment(String input) {
        // init assignment
            init();
        // parse input string
            String[] sSections = input.split(",");
        // parse start point
            String[] sAssignment = sSections[0].split("-");
            first.from = Integer.parseInt(sAssignment[0]);
            first.to = Integer.parseInt(sAssignment[1]);
            firstDefined = first.from != 0 && first.to != 0;
        // parse stop point
            sAssignment = sSections[1].split("-");
            second.from = Integer.parseInt(sAssignment[0]);
            second.to = Integer.parseInt(sAssignment[1]);
            secondDefined = second.from != 0 && second.to != 0;
        // set overlap
            overlap = getOverlap();
    }

    private void init() {
        // set the first assignment0
            first.from = 0;
            first.to = 0;
        // set the second assignment0
            second.from = 0;
            second.to = 0;
    }

    private boolean getOverlap() {
        return
            (firstDefined && first.from <= second.to && first.to >= second.from) ? true :
            (secondDefined && second.from <= first.to && second.to >= first.from) ? true :
            false;
    }
}