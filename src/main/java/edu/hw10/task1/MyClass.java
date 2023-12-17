package edu.hw10.task1;

public class MyClass {
    @NotNull
    private String name;

    @Min(value = 0)
    @Max(value = 120)
    private int age;

    private MyClass() {
    }

    private MyClass(String name, int age) {
        this.name = name;
        this.age = age;
    }

    public static MyClass create(String name, int age) {
        return new MyClass(name, age);
    }

    public String getName() {
        return name;
    }

    public int getAge() {
        return age;
    }

    @Override public String toString() {
        return "MyClass{name=" + name + ", age=" + age + '}';
    }
}
