package main.java.com.example.math;

import java.util.Locale;

public class Vector4 {
    private final double x;
    private final double y;
    private final double z;
    private final double w;

    public Vector4(double x, double y, double z, double w) {
        this.x = x;
        this.y = y;
        this.z = z;
        this.w = w;
    }

    public double getX() { return x; }
    public double getY() { return y; }
    public double getZ() { return z; }
    public double getW() { return w; }

    public Vector4 add(Vector4 other) {
        return new Vector4(this.x + other.x, this.y + other.y,
                this.z + other.z, this.w + other.w);
    }

    public Vector4 subtract(Vector4 other) {
        return new Vector4(this.x - other.x, this.y - other.y,
                this.z - other.z, this.w - other.w);
    }

    public Vector4 multiply(double scalar) {
        return new Vector4(this.x * scalar, this.y * scalar,
                this.z * scalar, this.w * scalar);
    }

    public Vector4 divide(double scalar) {
        if (Math.abs(scalar) < 1e-10) {
            throw new IllegalArgumentException("Невозможно осуществить деление вектора на ноль.");
        }
        return new Vector4(this.x / scalar, this.y / scalar,
                this.z / scalar, this.w / scalar);
    }

    public double length() {
        return Math.sqrt(x * x + y * y + z * z + w * w);
    }

    public Vector4 normalize() {
        double len = length();
        if (len < 1e-10) {
            throw new IllegalArgumentException("Невозможно нормализовать нулевой вектор.");
        }
        return new Vector4(x / len, y / len, z / len, w / len);
    }

    public double dot(Vector4 other) {
        return this.x * other.x + this.y * other.y +
                this.z * other.z + this.w * other.w;
    }

    public static Vector3 toVector3(Vector4 v){
        Vector3 result = new Vector3(v.getX(), v.getY(), v.getZ());
        return result;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;
        if (obj == null || getClass() != obj.getClass()) return false;
        Vector4 vector4 = (Vector4) obj;
        return Math.abs(x - vector4.x) < 1e-10 &&
                Math.abs(y - vector4.y) < 1e-10 &&
                Math.abs(z - vector4.z) < 1e-10 &&
                Math.abs(w - vector4.w) < 1e-10;
    }

    @Override
    public String toString() {
        return String.format(Locale.US,"Vector4(%.4f, %.4f, %.4f, %.4f)", x, y, z, w);
    }
}