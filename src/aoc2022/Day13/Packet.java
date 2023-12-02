package AOC2022.Day13;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.IntStream;

public class Packet {
    public SubPacket left;
    public SubPacket right;
    public PacketData leftData =  new PacketData();
    public PacketData rightData =  new PacketData();
    public boolean rightOrder = false;

    public Packet(String inString1, String inString2) {
        left = parseInString(inString1);
        right = parseInString(inString2);
        if (left.subPackets == null  && right.subPackets != null) left.pushData();;
        if (left.subPackets != null  && right.subPackets == null) right.pushData();;
        getData(left, leftData);
        getData(right, rightData);
        rightOrder = compareData(leftData, rightData);
    }

    private void getData(SubPacket subPacket, PacketData subPacketData) {
        if (subPacket.subPackets == null)
            subPacketData.data.add(subPacket.data);
        else {
            for (SubPacket s : subPacket.subPackets) {
                getData(s, subPacketData);
                subPacketData.subPackets++;
            }
        }
    }

    private boolean compareData(PacketData left, PacketData right) {
        if (left.subPackets > right.subPackets)
            // right side run out - number of subPockets
            return false;
        else
            for (int i = 0; i < left.data.size(); i++) {
                List<Integer> l = left.data.get(i);
                List<Integer> r = right.data.get(i);
                int max = l.size(); if (r.size() < max) max = r.size();
                if (l.size() > 0 && IntStream.range(0, max).anyMatch(j -> l.get(j) < r.get(j)))
                    // left side is smaller
                    return true;
                if (l.size() > 0 && IntStream.range(0, max).anyMatch(j -> l.get(j) > r.get(j)))
                    // right side is smaller
                    return false;
                if (l.size() > r.size())
                    // right side run out - number of data
                    return false;
            }
        return true;
    }

    private SubPacket parseInString(String inString) {
        SubPacket subPacket = new SubPacket();
        char[] chars = inString.toCharArray();
        Integer value = null;
        for(int i = 1; i < chars.length - 1; i++) {
            switch (chars[i]) {
                case '[' -> {
                    if (value != null) { subPacket.addValue(value); value = null; }
                    subPacket = subPacket.newPacket();
                }
                case ']' -> {
                    if (value != null) { subPacket.addValue(value); value = null; }
                    if (subPacket.subPackets != null && subPacket.data.size() != 0) subPacket.pushData();  // mixed mode
                    subPacket = subPacket.moveOut();
                }
                case '0','1','2','3','4','5','6','7','8','9' -> {
                    if (value == null) value = 0;
                    value = 10* value + (int) (chars[i] - '0');
                }
                case ',' -> {
                    if (value != null) { subPacket.addValue(value); value = null; }
                }
            }
        }
        if (value != null) { subPacket.addValue(value); value = null; }
        if (subPacket.subPackets != null && subPacket.data.size() != 0) subPacket.pushData();  // mixed mode
        return subPacket;
    }

}
