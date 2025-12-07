package test.java.com.example.math;

import main.java.com.example.math.Vector3;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;
import main.java.com.example.math.Matrix3;

class Matrix3Test {

    @Test
    void testConstructorValid() {
        double[][] data = {{1, 2, 3}, {4, 5, 6}, {7, 8, 9}};
        Matrix3 m = new Matrix3(data);
        assertEquals(1, m.get(0, 0), 1e-10);
        assertEquals(9, m.get(2, 2), 1e-10);
    }

    @Test
    void testConstructorInvalid() {
        double[][] invalid1 = {{1, 2}, {3, 4}};
        double[][] invalid2 = {{1, 2, 3}, {4, 5, 6}};

        assertThrows(IllegalArgumentException.class, () -> new Matrix3(invalid1));
        assertThrows(IllegalArgumentException.class, () -> new Matrix3(invalid2));
    }

    @Test
    void testIdentity() {
        Matrix3 identity = Matrix3.identity();
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                if (i == j) {
                    assertEquals(1, identity.get(i, j), 1e-10);
                } else {
                    assertEquals(0, identity.get(i, j), 1e-10);
                }
            }
        }
    }

    @Test
    void testZero() {
        Matrix3 zero = Matrix3.zero();
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                assertEquals(0, zero.get(i, j), 1e-10);
            }
        }
    }

    @Test
    void testAdd() {
        double[][] data1 = {{1, 2, 3}, {4, 5, 6}, {7, 8, 9}};
        double[][] data2 = {{9, 8, 7}, {6, 5, 4}, {3, 2, 1}};
        Matrix3 m1 = new Matrix3(data1);
        Matrix3 m2 = new Matrix3(data2);
        Matrix3 result = m1.add(m2);

        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                assertEquals(10, result.get(i, j), 1e-10);
            }
        }
    }

    @Test
    void testSubtract() {
        double[][] data1 = {{10, 10, 10}, {10, 10, 10}, {10, 10, 10}};
        double[][] data2 = {{1, 2, 3}, {4, 5, 6}, {7, 8, 9}};
        Matrix3 m1 = new Matrix3(data1);
        Matrix3 m2 = new Matrix3(data2);
        Matrix3 result = m1.subtract(m2);

        assertEquals(9, result.get(0, 0), 1e-10);
        assertEquals(8, result.get(0, 1), 1e-10);
        assertEquals(7, result.get(0, 2), 1e-10);
    }

    @Test
    void testMultiply() {
        double[][] data1 = {{1, 2, 3}, {4, 5, 6}, {7, 8, 9}};
        double[][] data2 = {{9, 8, 7}, {6, 5, 4}, {3, 2, 1}};
        Matrix3 m1 = new Matrix3(data1);
        Matrix3 m2 = new Matrix3(data2);
        Matrix3 result = m1.multiply(m2);

        assertEquals(30, result.get(0, 0), 1e-10);
        assertEquals(69, result.get(1, 1), 1e-10);
    }

    @Test
    void testMultiplyWithIdentity() {
        double[][] data = {{1, 2, 3}, {4, 5, 6}, {7, 8, 9}};
        Matrix3 m = new Matrix3(data);
        Matrix3 identity = Matrix3.identity();

        Matrix3 result1 = m.multiply(identity);
        Matrix3 result2 = identity.multiply(m);

        assertEquals(m, result1);
        assertEquals(m, result2);
    }

    @Test
    void testTranspose() {
        double[][] data = {{1, 2, 3}, {4, 5, 6}, {7, 8, 9}};
        Matrix3 m = new Matrix3(data);
        Matrix3 transposed = m.transpose();

        assertEquals(1, transposed.get(0, 0), 1e-10);
        assertEquals(4, transposed.get(0, 1), 1e-10);
        assertEquals(7, transposed.get(0, 2), 1e-10);
        assertEquals(2, transposed.get(1, 0), 1e-10);
    }

    @Test
    void testDeterminant() {
        double[][] data = {{1, 2, 3}, {0, 1, 4}, {5, 6, 0}};
        Matrix3 m = new Matrix3(data);
        assertEquals(1, m.determinant(), 1e-10);
    }

    @Test
    void testInverse() {
        double[][] data = {{4, 7, 2}, {3, 6, 1}, {2, 5, 3}};
        Matrix3 m = new Matrix3(data);
        Matrix3 inverse = m.inverse();

        Matrix3 product = m.multiply(inverse);

        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                if (i == j) {
                    assertEquals(1, product.get(i, j), 1e-8);
                } else {
                    assertEquals(0, product.get(i, j), 1e-8);
                }
            }
        }
    }

    @Test
    void testInverseSingularMatrix() {
        double[][] data = {{1, 2, 3}, {4, 5, 6}, {7, 8, 9}};
        Matrix3 m = new Matrix3(data);
        assertThrows(IllegalArgumentException.class, m::inverse);
    }

    @Test
    void testToString() {
        double[][] data = {{1.123456, 2.234567, 3.345678},
                {4.456789, 5.567890, 6.678901},
                {7.789012, 8.890123, 9.901234}};
        Matrix3 m = new Matrix3(data);
        String str = m.toString();

        assertTrue(str.contains("["));
        assertTrue(str.contains("]"));
        assertTrue(str.contains("1.1235"));
        assertTrue(str.contains("9.9012"));
    }

    @Test
    void testMultiplyByVector(){
        Matrix3 m = Matrix3.identity();
        Vector3 v = new Vector3(0, 0, 0);
        assertEquals(v, m.multiplyByVector(v));
    }
}