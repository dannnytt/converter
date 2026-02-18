package org.example.converter;

import java.util.ArrayList;
import java.util.List;

public class History {

    public record Record(int p1, int p2, String number1, String number2) {

        public List<String> toList() {
            return new ArrayList<>(List.of(
                    String.valueOf(p1),
                    number1,
                    String.valueOf(p2),
                    number2
            ));
        }

    }

    public List<Record> recordList;

    public History() {
        recordList = new ArrayList<>();
    }

    public void addRecord(int p1, int p2, String n1, String n2) {
        recordList.add(new Record(p1, p2, n1, n2));
    }

    public Record get(int i) {
        if (i < 0 || i > recordList.size())
            throw new ArrayIndexOutOfBoundsException();
        return recordList.get(i);
    }

    public void set(int i, Record value) {
        if (i < 0 || i > recordList.size())
            throw new ArrayIndexOutOfBoundsException();
        recordList.set(i, value);
    }

    public void clear() {
        recordList.clear();
    }

    public int count() {
        return recordList.size();
    }
}
