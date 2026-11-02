package org.example;

//Animals
abstract class Animal {
    private static int animalCount = 0;

    protected String name;
    protected int maxRunDistance;
    protected int maxSwimDistance;

    public Animal(String name, int maxRunDistance, int maxSwimDistance) {
        this.name = name;
        this.maxRunDistance = maxRunDistance;
        this.maxSwimDistance = maxSwimDistance;
        animalCount++;
    }

    public void run(int distance) {
        if (distance <= maxRunDistance) {
            System.out.println(name + " пробежал " + distance + " м.");
        } else {
            System.out.println(name + " не может пробежать " + distance + " м. (максимум " + maxRunDistance + " м.)");
        }
    }

    public void swim(int distance) {
        if (maxSwimDistance == 0) {
            System.out.println(name + " не умеет плавать.");
        } else if (distance <= maxSwimDistance) {
            System.out.println(name + " проплыл " + distance + " м.");
        } else {
            System.out.println(name + " не может проплыть " + distance + " м. (максимум " + maxSwimDistance + " м.)");
        }
    }

    public static int getAnimalCount() {
        return animalCount;
    }

    public String getName() {
        return name;
    }
}

//Cats
class Cat extends Animal {
    private static int catCount = 0;
    private boolean isSatiated;

    public Cat(String name) {
        super(name, 200, 0);
        this.isSatiated = false;
        catCount++;
    }

    public boolean eat(Bowl bowl, int foodAmount) {
        if (bowl.getFood() >= foodAmount) {
            bowl.removeFood(foodAmount);
            isSatiated = true;
            System.out.println(name + " поел " + foodAmount + " еды и наелся.");
            return true;
        } else {
            System.out.println(name + " не может поесть " + foodAmount + " еды. В миске только " + bowl.getFood() + ".");
            return false;
        }
    }

    public boolean isSatiated() {
        return isSatiated;
    }

    public static int getCatCount() {
        return catCount;
    }
}

//Dogs
class Dog extends Animal {
    private static int dogCount = 0;

    public Dog(String name) {
        super(name, 500, 10);
        dogCount++;
    }

    public static int getDogCount() {
        return dogCount;
    }
}

//Bowl
class Bowl {
    private int food;

    public Bowl(int food) {
        this.food = food;
    }

    public int getFood() {
        return food;
    }

    public void removeFood(int amount) {
        if (amount <= food) {
            food -= amount;
        }
    }

    public void addFood(int amount) {
        if (amount > 0) {
            food += amount;
        }
    }
}
public class LessonThree1 {
    public static void main(String[] args) {

        Cat cat1 = new Cat("Барсик");
        Cat cat2 = new Cat("Мурзик");
        Cat cat3 = new Cat("Пушок");

        Dog dog1 = new Dog("Бобик");
        Dog dog2 = new Dog("Шарик");

        System.out.println(" Тестирование животных ");
        cat1.run(150);
        cat1.run(250);
        dog1.run(400);
        dog1.run(600);

        cat1.swim(10);
        dog1.swim(5);
        dog1.swim(15);

        System.out.println("\n Статистика ");
        System.out.println("Создано котов: " + Cat.getCatCount());
        System.out.println("Создано собак: " + Dog.getDogCount());
        System.out.println("Всего животных: " + Animal.getAnimalCount());

        System.out.println("\n Кормление котов ");
        Bowl bowl = new Bowl(50);
        System.out.println("В миске " + bowl.getFood() + " еды.");

        Cat[] cats = {cat1, cat2, cat3};

        // Кормим котов
        for (Cat cat : cats) {
            cat.eat(bowl, 20);
        }

        // Добавляем еду в миску
        System.out.println("\nДобавляем 30 еды в миску.");
        bowl.addFood(30);
        System.out.println("В миске " + bowl.getFood() + " еды.");

        // Пытаемся покормить третьего кота снова
        cat3.eat(bowl, 25);
        System.out.println("В миске " + bowl.getFood() + " еды.");
        // Выводим информацию о сытости
        System.out.println("\n Информация о сытости котов ");
        for (Cat cat : cats) {
            System.out.println(cat.getName() + ": " + (cat.isSatiated() ? "сыт" : "голоден"));
        }
    }

}
