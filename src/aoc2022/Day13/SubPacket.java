package aoc2022.Day13;

import java.util.ArrayList;
import java.util.List;

public class SubPacket {
    public List<SubPacket> subPackets = null;
    public List<Integer> data = new ArrayList<>();
    private SubPacket parent = null;

    public SubPacket() {}

    public void addValue(int value) {
        data.add(value);
    }

    private void addParent(SubPacket parent) { this.parent = parent; }

    public SubPacket newPacket() {
        if (subPackets == null) subPackets = new ArrayList<>();
        SubPacket subPacket = new SubPacket();
        subPacket.addParent(this);
        subPackets.add(subPacket);
        return subPacket;
    }

    public SubPacket moveOut() {
        if (parent != null) return parent;
        else return this;
    }

    public void pushData() {
        if (data != null) {
            SubPacket subPacket = new SubPacket();
            subPacket.addParent(this);
            subPacket.data = data;
            if (subPackets == null) subPackets = new ArrayList<>();
            subPackets.add(subPacket);
            data = null;
        }
    }
}
