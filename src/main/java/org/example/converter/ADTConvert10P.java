package org.example.converter;

public class ADTConvert10P {

    public static String convertToBase(double number, int base, int precision) {

        if (base < 2 || base > 16)
            throw new IllegalArgumentException("Основание должно быть в промежутке от [2,16]");

        if (precision < 0 || precision > 10)
            throw new IllegalArgumentException("Точность должна быть в промежутке от [0,10]");

        int leftSide = (int) number;
        double rightSide = number - leftSide;

        if (rightSide < 0) rightSide *= -1;
        String leftSideStr = integerToBaseString(leftSide, base);
        String rightSideStr = doubleToBaseString(rightSide, base, precision);
        return leftSideStr + (rightSideStr.isEmpty() ? "" : ".") + rightSideStr;
    }

    public static char digitToChar(int digit) {

        if (digit < 0 || digit > 15)
            throw new IllegalArgumentException("Цифра должна быть в промежутке от [0,15]");

        return "0123456789ABCDEF".charAt(digit);
    }

    public static String integerToBaseString(int number, int base) {

        if (base < 2 || base > 16)
            throw new IllegalArgumentException("Основание должно быть в промежутке от [2,16]");

        if (number == 0) return "0";
        if (base == 10) return String.valueOf(number);

        boolean isNegative = number < 0;
        long absNumber = Math.abs(number);

        StringBuilder result = new StringBuilder();
        while (absNumber > 0) {
            result.append(digitToChar((int) (absNumber % base)));
            absNumber /= base;
        }

        if (isNegative) result.append('-');

        return result.reverse().toString();

    }

    public static String doubleToBaseString(double number, int base, int precision) {

        if (base < 2 || base > 16)
            throw new IllegalArgumentException("Основание должно быть в промежутке от [2,16]");

        if (precision < 0 || precision > 10)
            throw new IllegalArgumentException("Точность должна быть в промежутке от [0,10]");

        StringBuilder pNumber = new StringBuilder();
        for (int i = 0; i < precision; i++) {
            pNumber.append(digitToChar((int) (number * base)));
            number = number * base - (int)(number * base);
        }

        return pNumber.toString();
    }

}
