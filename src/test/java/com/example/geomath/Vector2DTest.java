package com.example.geomath;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

class Vector2DTest {

    private static void assertApprox(Vector2D v, double x, double y, double tol) {
        assertTrue(v.almostEquals(new Vector2D(x, y), tol),
                () -> "Expected approx (" + x + ", " + y + ") but got " + v);
    }

    @Test
    void testAddSubDotNorm() {
        Vector2D a = new Vector2D(1, 2);
        Vector2D b = new Vector2D(3, 4);
        Vector2D sum = a.add(b);
        Vector2D diff = b.subtract(a);
        assertApprox(sum, 4, 6, 1e-12);
        assertApprox(diff, 2, 2, 1e-12);
        assertEquals(11.0, a.dot(b), 1e-12);
        assertEquals(Math.sqrt(5.0), a.norm(), 1e-12);
    }

    @Test
    void testRotateDegrees90FailsIntentionally() {
        // Rotating (1,0) by +90 degrees should give (0,1)
        Vector2D v = new Vector2D(1, 0).rotate(90.0, true);
        assertTrue(v.almostEquals(new Vector2D(0, 1), 1e-9),
                () -> "Expected (0,1) after 90° rotation, got " + v);
    }

    @Test
    void testRotateRadiansOk() {
        Vector2D v = new Vector2D(1, 0).rotate(Math.PI / 2, false);
        assertTrue(v.almostEquals(new Vector2D(0, 1), 1e-9));
    }
}
