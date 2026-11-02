package org.example;

interface Shape {
    double getArea();
    String getName();
    String getFillColor();
    String getBorderColor();

    default double getPerimeter() {
        return 0;
    }

    default void printInfo() {
        System.out.println("Фигура: " + getName());
        System.out.println("Периметр: " + String.format("%.2f", getPerimeter()));
        System.out.println("Площадь: " + String.format("%.2f", getArea()));
        System.out.println("Цвет фона: " + getFillColor());
        System.out.println("Цвет границ: " + getBorderColor());
    }
}

class Circle implements Shape {
    private String name;
    private double radius;
    private String fillColor;
    private String borderColor;
    public Circle(String name, double radius, String fillColor, String borderColor) {
        this.name = name;
        this.radius = radius;
        this.fillColor = fillColor;
        this.borderColor = borderColor;
    }
    @Override
    public String getName() {
        return name;
    }
    @Override
    public double getArea() {
        return Math.PI * radius * radius;
    }

    @Override
    public double getPerimeter() {
        return 2 * Math.PI * radius;
    }

    @Override
    public String getFillColor() {
        return fillColor;
    }

    @Override
    public String getBorderColor() {
        return borderColor;
    }
}

class Rectangle implements Shape {
    private String name;
    private double width;
    private double height;
    private String fillColor;
    private String borderColor;

    public Rectangle(String name, double width, double height, String fillColor, String borderColor) {
        this.name = name;
        this.width = width;
        this.height = height;
        this.fillColor = fillColor;
        this.borderColor = borderColor;
    }
    @Override
    public String getName() {
        return name;
    }

    @Override
    public double getArea() {
        return width * height;
    }

    @Override
    public double getPerimeter() {
        return 2 * (width + height);
    }

    @Override
    public String getFillColor() {
        return fillColor;
    }

    @Override
    public String getBorderColor() {
        return borderColor;
    }
}

class Triangle implements Shape {
    private String name;
    private double side1;
    private double side2;
    private double side3;
    private String fillColor;
    private String borderColor;

    public Triangle(String name, double side1, double side2, double side3, String fillColor, String borderColor) {
        this.name = name;
        this.side1 = side1;
        this.side2 = side2;
        this.side3 = side3;
        this.fillColor = fillColor;
        this.borderColor = borderColor;
    }
    @Override
    public String getName() {
        return name;
    }
    @Override
    public double getArea() {
        double s = (side1 + side2 + side3) / 2;
        return Math.sqrt(s * (s - side1) * (s - side2) * (s - side3));
    }

    @Override
    public double getPerimeter() {
        return side1 + side2 + side3;
    }

    @Override
    public String getFillColor() {
        return fillColor;
    }

    @Override
    public String getBorderColor() {
        return borderColor;
    }
}

public class LessonThree2 {

    public static void main(String[] args) {
        Shape[] shapes = new Shape[3];

        shapes[0] = new Circle("Круг", 5, "красный", "черный");
        shapes[1] = new Rectangle("Прямоугольник", 4, 6, "синий", "белый");
        shapes[2] = new Triangle("Треугольник", 3, 4, 5, "зеленый", "желтый");

        for (int i = 0; i < shapes.length; i++) {
            System.out.println("--- Фигура " + (i + 1) + " ---");
            shapes[i].printInfo();
            System.out.println();
        }
    }
}
