package aoc2021.Test;

import java.util.ArrayList;
import java.util.List;

public class OpPacket {
    private String message;
    private int version;            // packet version
    private int typeID;             // packet type ID
    private long literalValue;      // non operator message value
    private int messageLength;      // message length
    List<OpPacket> opPackets;
    List<Packet> packets;

    public OpPacket(String inString) {
        packets = new ArrayList<>();
        opPackets = new ArrayList<>();
        message = inString;
        version = Integer.parseInt(message,0,3,2);
        typeID = Integer.parseInt(message,3,6,2);
        if (inString.charAt(6) == '0')
            parseTypeZero(inString);
        if (inString.charAt(6) == '1')
            parseTypeOne(inString);
    }

    public int getMessageLength() { return messageLength; }

    public long getVersionSum() {
        long sum = 0;
        for (OpPacket opPacket : opPackets)
            sum += opPacket.getVersionSum();
        for (Packet packet : packets)
            sum += packet.getVersion();
        return (sum + version);
    }

    public long getResult() {
        long result = 0;
        if (typeID == 0) { // sum
            result = 0;
            for (Packet packet : packets) result += packet.getLiteralValue();
        }
        if (typeID == 1) { // product
            result = 1;
            for (Packet packet : packets) result *= packet.getLiteralValue();
        }
        if (typeID == 2) { // minimal
            result = Long.MAX_VALUE;
            for (Packet packet : packets)
                if (packet.getLiteralValue() < result)
                    result = packet.getLiteralValue();
        }
        if (typeID == 3) { // maximal
            result = Long.MIN_VALUE;
            for (Packet packet : packets)
                if (packet.getLiteralValue() > result)
                    result = packet.getLiteralValue();
        }
        if (typeID == 5) { // greater than
            result = 0;
            if (packets.size() > 1)
                if (packets.get(0).getLiteralValue() > packets.get(1).getLiteralValue())
                    result = 1;
        }
        if (typeID == 6) { // less than
            result = 0;
            if (packets.size() > 1)
                if (packets.get(0).getLiteralValue() < packets.get(1).getLiteralValue())
                    result = 1;
        }
        if (typeID == 6) { // equal
            result = 0;
            if (packets.size() > 1)
                if (packets.get(0).getLiteralValue() == packets.get(1).getLiteralValue())
                    result = 1;
        }

        return result;
    }

    private void parseTypeZero(String inString) {
        String subString;
        // get substring length
        int begin = 7; int end = begin + 15;
        int packetEnd = end + Integer.parseInt(inString, begin, end, 2);
        // get the first sub packet
        do {
            begin = end; end = inString.length();
            subString = inString.substring(begin, end);
            if (isOperational(subString)) {
                OpPacket p = new OpPacket(subString);
                opPackets.add(p);
                end = begin + p.getMessageLength();
            } else {
                Packet packet = new Packet(subString);
                packets.add(packet);
                end = begin + packet.getMessageLength();
            }
        } while (end < packetEnd);
        messageLength = end;
    }

    private void parseTypeOne(String inString) {
        String subString;
        // get the number of sub packets
        int begin = 7; int end = begin + 11;
        int numberOfSubPackets = Integer.parseInt(inString, begin, end, 2);
        // get sub packets
        for (int i=0; i<numberOfSubPackets; i++) {
            begin = end; end = inString.length();
            subString = inString.substring( begin, end);
            if (isOperational(subString)) {
                OpPacket opPacket = new OpPacket(subString);
                opPackets.add(opPacket);
                end = begin + opPacket.getMessageLength();
            } else {
                Packet packet = new Packet(subString);
                packets.add(packet);
                end = begin + packet.getMessageLength();
            }
        }
        messageLength = end;
    }

    private static boolean isOperational(String inString) {
        return (!inString.substring(3,6).equals("100"));
    }

}
