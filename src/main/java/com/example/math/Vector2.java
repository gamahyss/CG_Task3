package main.java.com.example.math;

public class Vector2 {
    private final double x;
    private final double y;

    public Vector2(double x, double y) {
        this.x = x;
        this.y = y;
    }

    public double getX() { return x; }
    public double getY() { return y; }

    public Vector2 add(Vector2 other) {
        return new Vector2(this.x + other.x, this.y + other.y);
    }

    public Vector2 subtract(Vector2 other) {
        return new Vector2(this.x - other.x, this.y - other.y);
    }

    public Vector2 multiply(double scalar) {
        return new Vector2(this.x * scalar, this.y * scalar);
    }

    public Vector2 divide(double scalar) {
        if (Math.abs(scalar) < 1e-10) {
            throw new IllegalArgumentException("Невозможно осуществить деление вектора на ноль.");
        }
        return new Vector2(this.x / scalar, this.y / scalar);
    }

    public double length() {
        return Math.sqrt(x * x + y * y);
    }

    public Vector2 normalize() {
        double len = length();
        if (len < 1e-10) {
            throw new IllegalArgumentException("Невозможно нормализовать нулевой вектор.");
        }
        return new Vector2(x / len, y / len);
    }

    public double dot(Vector2 other) {
        return this.x * other.x + this.y * other.y;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;
        if (obj == null || getClass() != obj.getClass()) return false;
        Vector2 vector2 = (Vector2) obj;
        return Math.abs(x - vector2.x) < 1e-10 &&
                Math.abs(y - vector2.y) < 1e-10;
    }

    @Override
    public String toString() {
        return String.format("Вектор2(%.2f, %.2f)", x, y);
    }
}