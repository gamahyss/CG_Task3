// Math/Matrix3.java
package main.java.com.example.math;

import java.util.Locale;

public class Matrix3 {
    private final double[][] data;

    public Matrix3(double[][] data) {
        if (data.length != 3 || data[0].length != 3) {
            throw new IllegalArgumentException("Матрица должна быть вида 3x3.");
        }
        this.data = new double[3][3];
        for (int i = 0; i < 3; i++) {
            System.arraycopy(data[i], 0, this.data[i], 0, 3);
        }
    }

    public Matrix3() {
        this.data = new double[3][3];
    }

    public static Matrix3 identity() {
        double[][] identity = {
                {1, 0, 0},
                {0, 1, 0},
                {0, 0, 1}
        };
        return new Matrix3(identity);
    }

    public static Matrix3 zero() {
        return new Matrix3(new double[3][3]);
    }

    public double get(int row, int col) {
        return data[row][col];
    }

    public Matrix3 add(Matrix3 other) {
        double[][] result = new double[3][3];
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                result[i][j] = this.data[i][j] + other.data[i][j];
            }
        }
        return new Matrix3(result);
    }

    public Matrix3 subtract(Matrix3 other) {
        double[][] result = new double[3][3];
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                result[i][j] = this.data[i][j] - other.data[i][j];
            }
        }
        return new Matrix3(result);
    }

    public Matrix3 multiply(Matrix3 other) {
        double[][] result = new double[3][3];
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                for (int k = 0; k < 3; k++) {
                    result[i][j] += this.data[i][k] * other.data[k][j];
                }
            }
        }
        return new Matrix3(result);
    }

    public Matrix3 transpose() {
        double[][] result = new double[3][3];
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                result[j][i] = this.data[i][j];
            }
        }
        return new Matrix3(result);
    }

    public double determinant() {
        return data[0][0] * (data[1][1] * data[2][2] - data[1][2] * data[2][1])
                - data[0][1] * (data[1][0] * data[2][2] - data[1][2] * data[2][0])
                + data[0][2] * (data[1][0] * data[2][1] - data[1][1] * data[2][0]);
    }

    public Matrix3 inverse() {
        double det = determinant();
        if (Math.abs(det) < 1e-10) {
            throw new IllegalArgumentException("Нельзя найти обратную матрицу, определитель равен нулю.");
        }

        double[][] result = new double[3][3];
        double invDet = 1.0 / det;

        result[0][0] = (data[1][1] * data[2][2] - data[1][2] * data[2][1]) * invDet;
        result[0][1] = (data[0][2] * data[2][1] - data[0][1] * data[2][2]) * invDet;
        result[0][2] = (data[0][1] * data[1][2] - data[0][2] * data[1][1]) * invDet;
        result[1][0] = (data[1][2] * data[2][0] - data[1][0] * data[2][2]) * invDet;
        result[1][1] = (data[0][0] * data[2][2] - data[0][2] * data[2][0]) * invDet;
        result[1][2] = (data[0][2] * data[1][0] - data[0][0] * data[1][2]) * invDet;
        result[2][0] = (data[1][0] * data[2][1] - data[1][1] * data[2][0]) * invDet;
        result[2][1] = (data[0][1] * data[2][0] - data[0][0] * data[2][1]) * invDet;
        result[2][2] = (data[0][0] * data[1][1] - data[0][1] * data[1][0]) * invDet;

        return new Matrix3(result);
    }

    public Vector3 multiplyByVector(Vector3 v){
        double[][] result = new double[3][1];
        result[0][0] = data[0][0] * v.getX() + data[0][1] * v.getY() + data[0][2] * v.getZ();
        result[1][0] = data[1][0] * v.getX() + data[1][1] * v.getY() + data[1][2] * v.getZ();
        result[2][0] = data[2][0] * v.getX() + data[2][1] * v.getY() + data[2][2] * v.getZ();
        Vector3 result_vector = new Vector3(result[0][0], result[1][0], result[2][0]);
        return result_vector;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;
        if (obj == null || getClass() != obj.getClass()) return false;
        Matrix3 matrix3 = (Matrix3) obj;
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                if (Math.abs(data[i][j] - matrix3.data[i][j]) >= 1e-10) {
                    return false;
                }
            }
        }
        return true;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < 3; i++) {
            sb.append("[");
            for (int j = 0; j < 3; j++) {
                sb.append(String.format(Locale.US,"%.4f", data[i][j]));
                if (j < 2) sb.append(", ");
            }
            sb.append("]\n");
        }
        return sb.toString();
    }
}