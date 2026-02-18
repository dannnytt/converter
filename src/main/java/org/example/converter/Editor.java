package org.example.converter;

import java.util.Objects;

public class Editor {

    private String number;

    private static final String ZERO = "0";
    private static final String DELIM = ".";

    public enum Commands {
        add0, add1, add2, add3,
        add4, add5, add6, add7,
        add8, add9, addA, addB,
        addC, addD, addE, addF,
        addDot, bs, clear, exec
    }

    public Editor() { number = ""; }

    public String getNumber() {
        return number;
    }

    public void addDigit(int digit) {

        if (digit < 0 || digit > 16)
            throw new IllegalArgumentException("Параметр digit должен быть [0, 16]");

        if (Objects.equals(number, ZERO))
            number = String.valueOf(ADTConvert10P.digitToChar(digit));
        else number += String.valueOf(ADTConvert10P.digitToChar(digit));
    }

    public int acc() {

        if (number.contains(DELIM)) {
            String[] parts = number.split("\\.");
            return parts[1].length();
        }

        return 0;
    }

    public void addZero() {
        number += ZERO;
    }

    public void addDelim() {

        if (number.isEmpty())
            number += ZERO;

        if(!number.contains(DELIM))
            number += DELIM;
    }

    public void bs() {

        if (number.length() > 1)
            number = number.substring(0, number.length() - 1);
        else number = ZERO;
    }

    public void clear() {
        number = "";
    }

    public void doEdit(Commands command) {
        switch (command) {
            case Commands.add0 -> addZero();
            case Commands.add1 -> addDigit(1);
            case Commands.add2 -> addDigit(2);
            case Commands.add3 -> addDigit(3);
            case Commands.add4 -> addDigit(4);
            case Commands.add5 -> addDigit(5);
            case Commands.add6 -> addDigit(6);
            case Commands.add7 -> addDigit(7);
            case Commands.add8 -> addDigit(8);
            case Commands.add9 -> addDigit(9);
            case Commands.addA -> addDigit(10);
            case Commands.addB -> addDigit(11);
            case Commands.addC -> addDigit(12);
            case Commands.addD -> addDigit(13);
            case Commands.addE -> addDigit(14);
            case Commands.addF -> addDigit(15);
            case Commands.addDot -> addDelim();
            case Commands.bs -> bs();
            case Commands.clear -> clear();
        }
    }
}
