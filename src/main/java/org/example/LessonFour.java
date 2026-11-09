package org.example;


// Исключение для неверного размера массива
class MyArraySizeException extends Exception {
    public MyArraySizeException(String message) {
        super(message);
    }
}

// Исключение для неверных данных в ячейке
class MyArrayDataException extends Exception {
    public MyArrayDataException(String message) {
        super(message);
    }
}

public class LessonFour {
    public static int processArray(String[][] array) throws MyArraySizeException, MyArrayDataException {
        if (array == null || array.length != 4) {
            throw new MyArraySizeException("Ошибка размера массива: ожидается 4x4, получено " +
                    (array == null ? "null" : array.length + "x" + (array.length > 0 ? array[0].length : 0)));
        }

        int sum = 0;

        for (int i = 0; i < 4; i++) {
            if (array[i] == null || array[i].length != 4) {
                throw new MyArraySizeException("Ошибка размера массива: в строке " + i + " ожидается 4 элемента.");
            }

            for (int j = 0; j < 4; j++) {
                try {
                    sum += Integer.parseInt(array[i][j]);
                } catch (NumberFormatException e) {
                    throw new MyArrayDataException("Неверные данные в ячейке [" + i + "][" + j + "]: '" + array[i][j] + "'");
                }
            }
        }
        return sum;
    }
    public static void main(String[] args) {
        System.out.println("--- Тестирование задания 3 (Обработка массива) ---\n");

        // Тест 1: Успешное выполнение (корректный массив 4х4)
        String[][] correctArray = {
                {"1", "2", "3", "4"},
                {"5", "6", "7", "8"},
                {"9", "10", "11", "12"},
                {"13", "14", "15", "16"}
        };

        try {
            int result = processArray(correctArray);
            System.out.println("Тест 1 (Успех): Сумма элементов = " + result + "\n");
        } catch (MyArraySizeException | MyArrayDataException e) {
            e.printStackTrace();
        }

        // Тест 2: Неверный размер массива (MyArraySizeException)
        String[][] wrongSizeArray = {
                {"1", "2", "3"}, // 3 элемента
                {"1", "2", "3", "4"},
                {"1", "2", "3", "4"},
                {"1", "2", "3", "4"}
        };

        try {
            processArray(wrongSizeArray);
        } catch (MyArraySizeException e) {
            System.out.println("Тест 2 (MyArraySizeException): " + e.getMessage() + "\n");
        } catch (MyArrayDataException e) {
            e.printStackTrace();
        }

        // Тест 3: Неверные данные в ячейке (MyArrayDataException)
        String[][] wrongDataArray = {
                {"1", "2", "3", "4"},
                {"5", "6", "7", "8"},
                {"9", "10", "текст", "12"}, // Ошибка
                {"13", "14", "15", "16"}
        };

        try {
            processArray(wrongDataArray);
        } catch (MyArraySizeException e) {
            e.printStackTrace();
        } catch (MyArrayDataException e) {
            System.out.println("Тест 3 (MyArrayDataException): " + e.getMessage() + "\n");
        }


        System.out.println("--- Тестирование задания 4 (ArrayIndexOutOfBoundsException) ---\n");

        // Тест 4: Генерация и поимка ArrayIndexOutOfBoundsException
        int[] smallArray = {10, 20, 30};

        try {
            System.out.println("Пытаемся получить элемент по индексу 5");
            int value = smallArray[5];
        } catch (ArrayIndexOutOfBoundsException e) {
            System.out.println("Тест 4 (ArrayIndexOutOfBoundsException): Поймано исключение");
            System.out.println("Сообщение: " + e.getMessage());
        }
    }

}
