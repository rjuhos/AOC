package AOC2021.Test;

public class Packet {
    private String message;
    private int version;            // packet version
    private int typeID;             // packet type ID
    private long literalValue;      // non operator message value
    private int messageLength;      // message length

    public Packet(String inString) {
        message = inString;
        version = Integer.parseInt(message,0,3,2);
        typeID = Integer.parseInt(message,3,6,2);
        parseMessage();
    }

    public int getVersion() {
        return version;
    }

    public int getTypeID() {
        return typeID;
    }

    public long getLiteralValue() {
        return literalValue;
    }

    public int getMessageLength() { return messageLength; }

    private void parseMessage() {
        int value = 0;
        int start = 6; int end = start + 5;
        while (end <= message.length()) {
            value = 0x10* value + Integer.parseInt(message, start+1, end, 2);
            if (message.charAt(start) == '0') break;
            start = end; end = start + 5;
        }
        literalValue = value;
        messageLength = end;
    }

}
