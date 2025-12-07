package main.java.com.example.math;

import java.util.Locale;

public class Matrix4 {
    private final double[][] data;

    public Matrix4(double[][] data) {
        if (data.length != 4 || data[0].length != 4) {
            throw new IllegalArgumentException("Матрица должна быть вида 4x4.");
        }
        this.data = new double[4][4];
        for (int i = 0; i < 4; i++) {
            System.arraycopy(data[i], 0, this.data[i], 0, 4);
        }
    }

    public Matrix4() {
        this.data = new double[4][4];
    }

    public static Matrix4 identity() {
        double[][] identity = {
                {1, 0, 0, 0},
                {0, 1, 0, 0},
                {0, 0, 1, 0},
                {0, 0, 0, 1}
        };
        return new Matrix4(identity);
    }

    public static Matrix4 zero() {
        return new Matrix4(new double[4][4]);
    }

    public double get(int row, int col) {
        return data[row][col];
    }

    public Matrix4 add(Matrix4 other) {
        double[][] result = new double[4][4];
        for (int i = 0; i < 4; i++) {
            for (int j = 0; j < 4; j++) {
                result[i][j] = this.data[i][j] + other.data[i][j];
            }
        }
        return new Matrix4(result);
    }

    public Matrix4 subtract(Matrix4 other) {
        double[][] result = new double[4][4];
        for (int i = 0; i < 4; i++) {
            for (int j = 0; j < 4; j++) {
                result[i][j] = this.data[i][j] - other.data[i][j];
            }
        }
        return new Matrix4(result);
    }

    public Matrix4 multiply(Matrix4 other) {
        double[][] result = new double[4][4];
        for (int i = 0; i < 4; i++) {
            for (int j = 0; j < 4; j++) {
                for (int k = 0; k < 4; k++) {
                    result[i][j] += this.data[i][k] * other.data[k][j];
                }
            }
        }
        return new Matrix4(result);
    }

    public Matrix4 transpose() {
        double[][] result = new double[4][4];
        for (int i = 0; i < 4; i++) {
            for (int j = 0; j < 4; j++) {
                result[j][i] = this.data[i][j];
            }
        }
        return new Matrix4(result);
    }

    public double determinant() {
        double det = 0;
        for (int i = 0; i < 4; i++) {
            det += (i % 2 == 0 ? 1 : -1) * data[0][i] * minor(0, i);
        }
        return det;
    }

    private double minor(int row, int col) {
        double[][] minorMatrix = new double[3][3];
        int minorRow = 0;
        for (int i = 0; i < 4; i++) {
            if (i == row) continue;
            int minorCol = 0;
            for (int j = 0; j < 4; j++) {
                if (j == col) continue;
                minorMatrix[minorRow][minorCol] = data[i][j];
                minorCol++;
            }
            minorRow++;
        }
        Matrix3 minor = new Matrix3(minorMatrix);
        return minor.determinant();
    }

    public Matrix4 inverse() {
        double det = determinant();
        if (Math.abs(det) < 1e-10) {
            throw new IllegalArgumentException("Нельзя найти обратную матрицу, определитель равен нулю.");
        }

        double[][] result = new double[4][4];
        double invDet = 1.0 / det;

        for (int i = 0; i < 4; i++) {
            for (int j = 0; j < 4; j++) {
                double cofactor = Math.pow(-1, i + j) * minor(i, j);
                result[j][i] = cofactor * invDet;
            }
        }

        return new Matrix4(result);
    }

    public Vector4 multiplyByVector(Vector4 v){
        double[][] result = new double[4][1];
        result[0][0] = (data[0][0] + data[0][1] + data[0][2] + data[0][3]) * v.getX();
        result[1][0] = (data[1][0] + data[1][1] + data[1][2] + data[1][3]) * v.getY();
        result[2][0] = (data[2][0] + data[2][1] + data[2][2] + data[2][3]) * v.getZ();
        result[3][0] = (data[3][0] + data[3][1] + data[3][2] + data[3][3]) * v.getW();
        Vector4 result_vector = new Vector4(result[0][0], result[1][0], result[2][0], result[3][0]);
        return result_vector;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;
        if (obj == null || getClass() != obj.getClass()) return false;
        Matrix4 matrix4 = (Matrix4) obj;
        for (int i = 0; i < 4; i++) {
            for (int j = 0; j < 4; j++) {
                if (Math.abs(data[i][j] - matrix4.data[i][j]) >= 1e-10) {
                    return false;
                }
            }
        }
        return true;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < 4; i++) {
            sb.append("[");
            for (int j = 0; j < 4; j++) {
                sb.append(String.format(Locale.US, "%.4f", data[i][j]));
                if (j < 3) sb.append(", ");
            }
            sb.append("]\n");
        }
        return sb.toString();
    }
}