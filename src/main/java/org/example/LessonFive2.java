package org.example;
import java.util.*;

public class LessonFive2 {

    static class PhoneBook {
        // Хранилище: Фамилия -> Список номеров
        private Map<String, List<String>> book;

        public PhoneBook() {
            this.book = new HashMap<>();
        }

        public void add(String surname, String phoneNumber) {
            // computeIfAbsent создает новый список, если для этой фамилии его еще нет
            book.computeIfAbsent(surname, k -> new ArrayList<>()).add(phoneNumber);
        }

        public List<String> get(String surname) {
            // getOrDefault вернет список из справочника или пустой список, если ключа нет
            return book.getOrDefault(surname, Collections.emptyList());
        }
    }

    public static void main(String[] args) {
        PhoneBook phoneBook = new PhoneBook();

        // Добавляем записи
        phoneBook.add("Иванов", "+7-900-111-22-33");
        phoneBook.add("Петров", "+7-900-444-55-66");
        phoneBook.add("Иванов", "+7-900-777-88-99"); // Однофамилец или второй номер
        phoneBook.add("Сидоров", "+7-900-000-11-22");

        // Проверяем работу метода get()
        System.out.println("Номера Иванова: " + phoneBook.get("Иванов"));
        System.out.println("Номера Петрова: " + phoneBook.get("Петров"));
        System.out.println("Номера Васечкина (нет в книге): " + phoneBook.get("Васечкин"));
    }
}
