package edu.hw2.task2;

public class Rectangle {
    protected int width;
    protected int height;

    public Rectangle(int width, int height) {
        if (width > 0) {
            this.width = width;
        } else {
            this.width = 1;
        }
        if (height > 0) {
            this.height = height;
        } else {
            this.height = 1;
        }
    }

    public Rectangle() {
        width = 1;
        height = 1;
    }

    public Rectangle setWidth(int width) {
        if (width > 0) {
            this.width = width;
        }

        return new Rectangle(width, this.height);
    }

    public Rectangle setHeight(int height) {
        if (height > 0) {
            this.height = height;
        }

        return new Rectangle(this.width, height);
    }

    double calculateArea() {
        return width * height;
    }
}
