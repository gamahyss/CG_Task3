package Math;

public class Vector3 {
    private final double x;
    private final double y;
    private final double z;

    public Vector3(double x, double y, double z) {
        this.x = x;
        this.y = y;
        this.z = z;
    }

    public double getX() { return x; }
    public double getY() { return y; }
    public double getZ() { return z; }

    public Vector3 add(Vector3 other) {
        return new Vector3(this.x + other.x, this.y + other.y, this.z + other.z);
    }

    public Vector3 subtract(Vector3 other) {
        return new Vector3(this.x - other.x, this.y - other.y, this.z - other.z);
    }

    public Vector3 multiply(double scalar) {
        return new Vector3(this.x * scalar, this.y * scalar, this.z * scalar);
    }

    public Vector3 divide(double scalar) {
        if (Math.abs(scalar) < 1e-10) {
            throw new IllegalArgumentException("Невозможно осуществить деление вектора на ноль.");
        }
        return new Vector3(this.x / scalar, this.y / scalar, this.z / scalar);
    }

    public double length() {
        return Math.sqrt(x * x + y * y + z * z);
    }

    public Vector3 normalize() {
        double len = length();
        if (len < 1e-10) {
            throw new IllegalArgumentException("Невозможно нормализовать нулевой вектор.");
        }
        return new Vector3(x / len, y / len, z / len);
    }

    public double dot(Vector3 other) {
        return this.x * other.x + this.y * other.y + this.z * other.z;
    }

    public Vector3 cross(Vector3 other) {
        return new Vector3(
                this.y * other.z - this.z * other.y,
                this.z * other.x - this.x * other.z,
                this.x * other.y - this.y * other.x
        );
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;
        if (obj == null || getClass() != obj.getClass()) return false;
        Vector3 vector3 = (Vector3) obj;
        return Math.abs(x - vector3.x) < 1e-10 &&
                Math.abs(y - vector3.y) < 1e-10 &&
                Math.abs(z - vector3.z) < 1e-10;
    }

    @Override
    public String toString() {
        return String.format("Вектор3(%.4f, %.4f, %.4f)", x, y, z);
    }
}