package test.java.com.example.math;

import main.java.com.example.math.Matrix3;
import main.java.com.example.math.Vector3;
import main.java.com.example.math.Vector4;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;
import main.java.com.example.math.Matrix4;

class Matrix4Test {

    @Test
    void testConstructorValid() {
        double[][] data = {
                {1, 2, 3, 4},
                {5, 6, 7, 8},
                {9, 10, 11, 12},
                {13, 14, 15, 16}
        };
        Matrix4 m = new Matrix4(data);
        assertEquals(1, m.get(0, 0), 1e-10);
        assertEquals(16, m.get(3, 3), 1e-10);
    }

    @Test
    void testConstructorInvalid() {
        double[][] invalid1 = {{1, 2}, {3, 4}};
        double[][] invalid2 = new double[3][4];

        assertThrows(IllegalArgumentException.class, () -> new Matrix4(invalid1));
        assertThrows(IllegalArgumentException.class, () -> new Matrix4(invalid2));
    }

    @Test
    void testIdentity() {
        Matrix4 identity = Matrix4.identity();
        for (int i = 0; i < 4; i++) {
            for (int j = 0; j < 4; j++) {
                if (i == j) {
                    assertEquals(1, identity.get(i, j), 1e-10);
                } else {
                    assertEquals(0, identity.get(i, j), 1e-10);
                }
            }
        }
    }

    @Test
    void testAdd() {
        double[][] data1 = {
                {1, 1, 1, 1},
                {1, 1, 1, 1},
                {1, 1, 1, 1},
                {1, 1, 1, 1}
        };
        double[][] data2 = {
                {2, 2, 2, 2},
                {2, 2, 2, 2},
                {2, 2, 2, 2},
                {2, 2, 2, 2}
        };
        Matrix4 m1 = new Matrix4(data1);
        Matrix4 m2 = new Matrix4(data2);
        Matrix4 result = m1.add(m2);

        for (int i = 0; i < 4; i++) {
            for (int j = 0; j < 4; j++) {
                assertEquals(3, result.get(i, j), 1e-10);
            }
        }
    }

    @Test
    void testMultiply() {
        double[][] data1 = {
                {1, 2, 3, 4},
                {5, 6, 7, 8},
                {9, 10, 11, 12},
                {13, 14, 15, 16}
        };
        double[][] data2 = {
                {16, 15, 14, 13},
                {12, 11, 10, 9},
                {8, 7, 6, 5},
                {4, 3, 2, 1}
        };
        Matrix4 m1 = new Matrix4(data1);
        Matrix4 m2 = new Matrix4(data2);
        Matrix4 result = m1.multiply(m2);

        assertEquals(80, result.get(0, 0), 1e-10);

        assertEquals(386, result.get(3, 3), 1e-10);
    }

    @Test
    void testMultiplyWithIdentity() {
        double[][] data = {
                {1, 2, 3, 4},
                {5, 6, 7, 8},
                {9, 10, 11, 12},
                {13, 14, 15, 16}
        };
        Matrix4 m = new Matrix4(data);
        Matrix4 identity = Matrix4.identity();

        Matrix4 result1 = m.multiply(identity);
        Matrix4 result2 = identity.multiply(m);

        assertEquals(m, result1);
        assertEquals(m, result2);
    }

    @Test
    void testTranspose() {
        double[][] data = {
                {1, 2, 3, 4},
                {5, 6, 7, 8},
                {9, 10, 11, 12},
                {13, 14, 15, 16}
        };
        Matrix4 m = new Matrix4(data);
        Matrix4 transposed = m.transpose();

        assertEquals(1, transposed.get(0, 0), 1e-10);
        assertEquals(5, transposed.get(0, 1), 1e-10);
        assertEquals(9, transposed.get(0, 2), 1e-10);
        assertEquals(13, transposed.get(0, 3), 1e-10);
        assertEquals(2, transposed.get(1, 0), 1e-10);
    }

    @Test
    void testDeterminant() {
        Matrix4 identity = Matrix4.identity();
        assertEquals(1, identity.determinant(), 1e-10);

        double[][] data = {
                {1, 0, 0, 0},
                {0, 2, 0, 0},
                {0, 0, 3, 0},
                {0, 0, 0, 4}
        };
        Matrix4 m = new Matrix4(data);
        assertEquals(24, m.determinant(), 1e-10); // 1*2*3*4 = 24
    }

    @Test
    void testInverse() {
        double[][] data = {
                {2, 0, 0, 0},
                {0, 3, 0, 0},
                {0, 0, 4, 0},
                {0, 0, 0, 5}
        };
        Matrix4 m = new Matrix4(data);
        Matrix4 inverse = m.inverse();

        assertEquals(0.5, inverse.get(0, 0), 1e-10);
        assertEquals(1.0/3, inverse.get(1, 1), 1e-10);
        assertEquals(0.25, inverse.get(2, 2), 1e-10);
        assertEquals(0.2, inverse.get(3, 3), 1e-10);
    }

    @Test
    void testInverseSingularMatrix() {
        double[][] data = {
                {1, 2, 3, 4},
                {5, 6, 7, 8},
                {9, 10, 11, 12},
                {13, 14, 15, 16}
        };
        Matrix4 m = new Matrix4(data);
        assertThrows(IllegalArgumentException.class, m::inverse);
    }

    @Test
    void testToString() {
        double[][] data = {
                {1.1, 2.2, 3.3, 4.4},
                {5.5, 6.6, 7.7, 8.8},
                {9.9, 10.10, 11.11, 12.12},
                {13.13, 14.14, 15.15, 16.16}
        };
        Matrix4 m = new Matrix4(data);
        String str = m.toString();

        assertTrue(str.contains("["));
        assertTrue(str.contains("]"));
        assertTrue(str.contains("1.1000"));
        assertTrue(str.contains("16.1600"));
    }

    @Test
    void testMultiplyByVector(){
        double[][] data = {
                {1, 2, 3, 4},
                {5, 6, 7, 8},
                {9, 10, 11, 12},
                {13, 14, 15, 16}
        };
        Matrix4 m = new Matrix4(data);
        Vector4 v = new Vector4(0, 0, 0, 0);
        assertEquals(v, m.multiplyByVector(v));
    }
}