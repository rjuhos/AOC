package AOC2022.Day11;

import java.math.BigInteger;
import java.util.*;

public class Monkey {
    Queue<Long> items = new LinkedList<>();
    private char operator = Character.MIN_VALUE;
    private int operand = 0;
    private long noOfInspections = 0;
    private long modulo = 3;
    private int testValue = 1;
    private int ifTrue = 0;
    private int ifFalse = 0;
    private int toMonkey = 0;

    public Monkey(String[] definition) {
        Scanner sc;
        // get items
        sc = new Scanner(definition[1].replace(","," "));
        while (sc.hasNext()) {
            if (sc.hasNextInt()) this.items.add(sc.nextLong());
            else sc.next();
        }
        // get operator and operation value

        sc = new Scanner(definition[2]);
        while (sc.hasNext()) {
            if (sc.hasNextInt()) {
                this.operator = definition[2].charAt(23);
                this.operand = sc.nextInt();
                break;
            }
            else sc.next();
            this.operator = '^';
            this.operand = 1 ;
        }
        // get divisor
        sc = new Scanner(definition[3]);
        while (sc.hasNext()) {
            if (sc.hasNextInt()) { this.testValue = sc.nextInt(); if (this.testValue == 0) this.testValue = 1; break; }
            else sc.next();
        }
        // get monkey if true
        sc = new Scanner(definition[4]);
        while (sc.hasNext()) {
            if (sc.hasNextInt()) { this.ifTrue = sc.nextInt(); break; }
            else sc.next();
        }
        // get monkey if false
        sc = new Scanner(definition[5]);
        while (sc.hasNext()) {
            if (sc.hasNextInt()) { this.ifFalse = sc.nextInt(); break; }
            else sc.next();
        }
    }

    public int getTestValue() {
        return testValue;
    }

    public void setModulo(long modulo) {
        this.modulo = modulo;
    }

    public void updateNoOfInspections() {
        this.noOfInspections += items.size();
    }

    public void addItem(long item) {
        this.items.add(item);
    }

    public boolean hasNext() {
        return (items.size() > 0 ? true : false);
    }

    public long Next() {
        long value = items.poll();
        switch (operator) {
            case '+' -> value += this.operand;
            case '-' -> value -= this.operand;
            case '*' -> value *= this.operand;
            case '/' -> value /= this.operand;
            case '^' -> value *= value;
        }
        value %= modulo;
        this.toMonkey = (value % testValue == 0 ? ifTrue : ifFalse);
        return value;
    }

    public int getToMonkey() {
        return toMonkey;
    }

    public long getNoOfInspections() {
        return noOfInspections;
    }
}
