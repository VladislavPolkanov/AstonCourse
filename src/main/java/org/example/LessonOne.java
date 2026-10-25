package org.example;

import java.util.Arrays;

public class LessonOne {
    //Задание 1
    public static void printThreeWords() {
        System.out.println("Orange");
        System.out.println("Banana");
        System.out.println("Apple");
    }
    //Задание 2
    public static void checkSumSign() {
        int a = 2;
        int b = -5;
        if (a + b >= 0) {
            System.out.println("Сумма положительная");
        } else {
            System.out.println("Сумма отрицательная");
        }
    }
    //Задание 3
    public static void printColor() {
        int value = 55;
        if (value <= 0) {
            System.out.println("Красный");
        } else if (value <= 100) {
            System.out.println("Желтый");
        } else {
            System.out.println("Зеленый");
        }
    }
    //Задание 4
    public static void compareNumbers() {
        int a = 7;
        int b = 8;
        if (a >= b) {
            System.out.println("a >= b");
        } else {
            System.out.println("a < b");
        }
    }
    //Задание 5
    public static boolean isSumInRange(int a, int b) {
        int sum = a + b;
        return sum >= 10 && sum <= 20;
    }

    //Задание 6
    public static void printNumberSign(int number) {
        if (number >= 0) {
            System.out.println("Число положительное");
        } else {
            System.out.println("Число отрицательное");
        }
    }

    //Задание 7
    public static boolean isNegative(int number) {
        return number < 0;
    }

    //Задание 8
    public static void printStringNTimes(String str, int count) {
        for (int i = 0; i < count; i++) {
            System.out.println(str);
        }
    }
    //Задание 9
    public static boolean isLeapYear(int year) {
        return (year % 4 == 0 && year % 100 != 0) || (year % 400 == 0);
    }

    //Задание 10
    public static void invertZeroOne(int[] array) {
        for (int i = 0; i < array.length; i++) {
            if (array[i] == 0) {
                array[i] = 1;
            } else {
                array[i] = 0;
            }
        }
    }

    //Задание 11
    public static int[] fillArray() {
        int[] array = new int[100];
        for (int i = 0; i < array.length; i++) {
            array[i] = i + 1;
        }
        return array;
    }

    //Задание 12
    public static void MultiplyLessSix(int[] array) {
        for (int i = 0; i < array.length; i++) {
            if (array[i] < 6) {
                array[i] = array[i] * 2;
            }
        }
    }

    //Задание 13
    public static int[][] createMatrixWithDiagonals(int n) {
        int[][] matrix = new int[n][n];
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                if (i == j || i + j == n - 1) {
                    matrix[i][j] = 1;
                }
            }
        }
        return matrix;
    }

    //Задание 14
    public static int[] createArray(int len, int initialValue) {
        int[] array = new int[len];
        for (int i = 0; i < len; i++) {
            array[i] = initialValue;
        }
        return array;
    }

    public static void main(String[] args) {
        System.out.println("Задание 1");
        printThreeWords();

        System.out.println("Задание 2");
        checkSumSign();

        System.out.println("Задание 3");
        printColor();

        System.out.println("Задание 4");
        compareNumbers();

        System.out.println("Задание 5");
        System.out.println("isSumInRange(15, 5) = " + isSumInRange(15, 5));
        System.out.println("isSumInRange(9, 1)  = " + isSumInRange(9, 1));
        System.out.println("isSumInRange(1, 2)  = " + isSumInRange(1, 2));
        System.out.println("isSumInRange(15, 6) = " + isSumInRange(15, 6));

        System.out.println("Задание 6");
        printNumberSign(0);

        System.out.println("Задание 7");
        System.out.println(isNegative(-3));

        System.out.println("Задание 8");
        printStringNTimes("stringString",5);

        System.out.println("Задание 9");
        System.out.println("2024 " + isLeapYear(2024));
        System.out.println("200 " + isLeapYear(1900));
        System.out.println("1200 " + isLeapYear(2000));
        System.out.println("2023 " + isLeapYear(2023));

        System.out.println("Задание 10");

        int[] binary = {1, 1, 0, 0, 1, 0, 1, 1, 0, 0};
        System.out.println("До:    " + Arrays.toString(binary));
        invertZeroOne(binary);
        System.out.println("После: " + Arrays.toString(binary));

        System.out.println("Задание 11");

        int[] hundred = fillArray();
        System.out.println(Arrays.toString(hundred));

        System.out.println("Задание 12");

        int[] arr = {1, 5, 3, 2, 11, 4, 5, 2, 4, 8, 9, 1};
        System.out.println("До:    " + Arrays.toString(arr));
        MultiplyLessSix(arr);
        System.out.println("После: " + Arrays.toString(arr));

        System.out.println("Задание 13");

        int[][] matrix = createMatrixWithDiagonals(5);
        for (int[] row : matrix) {
            System.out.println(Arrays.toString(row));
        }

        System.out.println("Задание 14");
        System.out.println(Arrays.toString(createArray(5, 7)));

    }
}
