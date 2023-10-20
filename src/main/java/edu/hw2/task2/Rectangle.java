package edu.hw2.task2;

public class Rectangle {
    protected int width;
    protected int height;

    public Rectangle(int width, int height) {
        if (width > 0) {
            this.width = width;
        }
        if (height > 0) {
            this.height = height;
        }
    }

    public Rectangle() {
    }

    public Rectangle setWidth(int width) {
        if (width > 0) {
            this.width = width;
        }

        return this;
    }

    public Rectangle setHeight(int height) {
        if (height > 0) {
            this.height = height;
        }

        return this;
    }

    double calculateArea() {
        return width * height;
    }
}
