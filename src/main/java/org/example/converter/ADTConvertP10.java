package org.example.converter;

import java.util.regex.Pattern;

public class ADTConvertP10 {


    public static double dval(String pNum, int base) {

        if (base < 2 || base > 16)
            throw new IllegalArgumentException("Основание должно быть в промежутке [2,16]");

        for (char ch : pNum.toCharArray()) {
            if (ch == '.') continue;
            if (charToNum(ch) >= base) throw new IllegalArgumentException();
        }

        Pattern leftRight = Pattern.compile("^[0-9A-Fa-f]+\\.[0-9A-Fa-f]+$");
        Pattern right = Pattern.compile("^0\\.[0-9A-Fa-f]+$");
        Pattern left = Pattern.compile("^[0-9A-Fa-f]+$");

        double number;
        int dotIndex = pNum.indexOf('.');

        if (leftRight.matcher(pNum).matches()) {
            String digits = pNum.substring(0, dotIndex) + pNum.substring(dotIndex + 1);
            double weight = Math.pow(base, dotIndex);
            number = convert(digits, base, weight);
        } else if (left.matcher(pNum).matches()) {
            number = convert(pNum, base, Math.pow(base, pNum.length()));
        } else if (right.matcher(pNum).matches()) {
            String digits = pNum.substring(2);
            number = convert(digits, base, 0);
        } else {
            throw new NumberFormatException();
        }

        return number;
    }

    public static int charToNum(char ch) {

        String allNums = "0123456789ABCDEF";
        int index = allNums.indexOf(ch);

        if (index == -1)
            throw new IllegalArgumentException("Символ должен быть в промежутке [0, F]");

        return index;
    }

    public static double convert(String pNum, int base, double weight) {

        int degree = pNum.length() - 1;
        double result = 0.0;

        for (int i = 0; i < pNum.length(); i++, degree--) {
            result += charToNum(pNum.charAt(i)) * Math.pow(base, degree);
        }

        return result;
    }

}
