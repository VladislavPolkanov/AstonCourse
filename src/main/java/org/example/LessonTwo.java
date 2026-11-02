package org.example;

class Product {
    private String name;
    private String productionDate;
    private String manufacturer;
    private String countryOfOrigin;
    private double price;
    private boolean isReserved;

    public Product(String name, String productionDate, String manufacturer,
                   String countryOfOrigin, double price, boolean isReserved) {
        this.name = name;
        this.productionDate = productionDate;
        this.manufacturer = manufacturer;
        this.countryOfOrigin = countryOfOrigin;
        this.price = price;
        this.isReserved = isReserved;
    }

    public void printInfo() {
        System.out.println("Товар: " + name);
        System.out.println("Дата производства: " + productionDate);
        System.out.println("Производитель: " + manufacturer);
        System.out.println("Страна происхождения: " + countryOfOrigin);
        System.out.println("Цена: " + price + " руб.");
        System.out.println("Забронирован: " + (isReserved ? "Да" : "Нет"));
        System.out.println("------------------");
    }
}

class Park {
    private String parkName;

    public Park(String parkName) {
        this.parkName = parkName;
    }

    public class Attraction {
        private String name;
        private String workingHours;
        private double cost;

        public Attraction(String name, String workingHours, double cost) {
            this.name = name;
            this.workingHours = workingHours;
            this.cost = cost;
        }

        public void printInfo() {
            System.out.println("Аттракцион: " + name);
            System.out.println("Время работы: " + workingHours);
            System.out.println("Стоимость: " + cost + " руб.");
        }
    }
}

public class LessonTwo {
    public static void main(String[] args) {

        System.out.println(" СПИСОК ТОВАРОВ ");

        Product[] productsArray = new Product[5];

        productsArray[0] = new Product("Samsung S25 Ultra", "01.02.2025",
                "Samsung Corp.", "Korea", 5599, true);
        productsArray[1] = new Product("iPhone 16 Pro", "15.09.2024",
                "Apple Inc.", "USA", 8999, false);
        productsArray[2] = new Product("Sony PlayStation 5", "10.11.2020",
                "Sony Interactive Entertainment", "Japan", 4999, true);
        productsArray[3] = new Product("Xiaomi Redmi Note 13", "20.01.2024",
                "Xiaomi Corp.", "China", 1999, false);
        productsArray[4] = new Product("MacBook Air M3", "05.03.2024",
                "Apple Inc.", "USA", 12999, false);

        for (Product product : productsArray) {
            product.printInfo();
        }

        System.out.println("\n ИНФОРМАЦИЯ О ПАРКЕ ");

        Park park = new Park("Центральный Парк Развлечений");

        Park.Attraction rollerCoaster = park.new Attraction("Американские горки", "10:00 - 22:00", 500);
        Park.Attraction ferrisWheel = park.new Attraction("Колесо обозрения", "12:00 - 23:00", 300);
        Park.Attraction carousel = park.new Attraction("Карусель", "10:00 - 20:00", 150);

        System.out.println("Парк: Центральный Парк Развлечений");
        System.out.println("--------------------------------------------------");
        rollerCoaster.printInfo();
        System.out.println("--------------------------------------------------");
        ferrisWheel.printInfo();
        System.out.println("--------------------------------------------------");
        carousel.printInfo();
    }
}
