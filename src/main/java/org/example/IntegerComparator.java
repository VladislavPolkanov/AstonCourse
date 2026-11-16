package org.example;

public class IntegerComparator {
    public static String compare(int a, int b) {
        if (a > b) return "Первое число больше второго";
        if (a < b) return "Первое число меньше второго";
        return "Числа равны";
    }
}
