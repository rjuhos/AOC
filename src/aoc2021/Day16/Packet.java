package AOC2021.Day16;

import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;
import java.util.List;

public class Packet {
    private int version;            // packet version
    private int typeID;             // packet type ID
    private boolean isOperational;  // operational packet
    private long value;             // non operator message value
    List<Packet> subPackets;
    private String message;
    private int messageLength;      // message length

    public Packet(String inString) {
        subPackets = new ArrayList<>();
        version = Integer.parseInt(inString,0,3,2);
        typeID = Integer.parseInt(inString,3,6,2);
        isOperational = isOperational(inString);
        value = 0;
        if (!isOperational) {
            messageLength = parseValue(inString);
        } else {
            if (inString.charAt(6) == '0')
                messageLength = parseOpPacketTypeZero(inString);
            if (inString.charAt(6) == '1')
                messageLength = parseOpPacketTypeOne(inString);
            getPacketValue();
        }
        message = inString.substring(0,messageLength);
    }

    public int getVersion() {
        return version;
    }

    public int getTypeID() {
        return typeID;
    }

    public long getValue() {
        return value;
    }

    public int getMessageLength() {
        return messageLength;
    }

    public long getVersionSum() {
        long sum = 0;
        for (Packet opPacket : subPackets)
            sum += opPacket.getVersionSum();
        return (sum + version);
    }

    private void getPacketValue() {
        if (typeID == 0) { // sum
            value = 0;
            for (Packet packet : subPackets) value += packet.getValue();
        }
        else if (typeID == 1) { // product
            value = 1;
            for (Packet packet : subPackets) value *= packet.getValue();
        }
        else if (typeID == 2) { // minimal
            value = Long.MAX_VALUE;
            for (Packet packet : subPackets)
                if (packet.getValue() < value)
                    value = packet.getValue();
        }
        else if (typeID == 3) { // maximal
            value = Long.MIN_VALUE;
            for (Packet packet : subPackets)
                if (packet.getValue() > value)
                    value = packet.getValue();
        }
        else if (typeID == 5) { // greater than
            value = 0;
            if (subPackets.size() > 1)
                if (subPackets.get(0).getValue() > subPackets.get(1).getValue())
                    value = 1;
        }
        else if (typeID == 6) { // less than
            value = 0;
            if (subPackets.size() > 1)
                if (subPackets.get(0).getValue() < subPackets.get(1).getValue())
                    value = 1;
        }
        else if (typeID == 7) { // equal
            value = 0;
            if (subPackets.size() > 1)
                if (subPackets.get(0).getValue() == subPackets.get(1).getValue())
                    value = 1;
        }
        else {
            value = 0;
        }
    }

    private static boolean isOperational(@NotNull String inString) {
        return (!inString.substring(3,6).equals("100"));
    }

    private int parseOpPacketTypeZero(String inString) {
        String subString;
        // get substring length
        int begin = 7; int end = begin + 15;
        int packetEnd = end + Integer.parseInt(inString, begin, end, 2);
        // get sub packets
        do {
            begin = end; end = inString.length();
            subString = inString.substring(begin, end);
                Packet p = new Packet(subString);
                subPackets.add(p);
                end = begin + p.getMessageLength();
        } while (end < packetEnd);
        return end;
    }

    private int parseOpPacketTypeOne(String inString) {
        String subString;
        // get the number of sub packets
        int begin = 7; int end = begin + 11;
        int numberOfSubPackets = Integer.parseInt(inString, begin, end, 2);
        // get sub packets
        for (int i=0; i<numberOfSubPackets; i++) {
            begin = end; end = inString.length();
            subString = inString.substring( begin, end);
                Packet opPacket = new Packet(subString);
                subPackets.add(opPacket);
                end = begin + opPacket.getMessageLength();
        }
        return end;
    }

    private int parseValue(String inString) {
        long value = 0;
        int start = 6; int end = start + 5;
        while (end <= inString.length()) {
            value = 0x10* value + Long.parseLong(inString, start+1, end, 2);
            if (inString.charAt(start) == '0') break;
            start = end; end = start + 5;
        }
        this.value = value;
        return end;
    }

    private void prettyPrint(int depth) {
        if (depth > 0) {
            for (int i = 0; i < depth - 1; i++) {
                System.out.print("   ");
            }
            System.out.print("|- ");
        }
        if (!isOperational) System.out.println(value);
        else {
            System.out.println(typeID);
            for (Packet sub : subPackets)
                sub.prettyPrint(depth + 1);
        }
    }

    public void prettyPrint() {
        prettyPrint(0);
    }

}
