package com.example.geomath;

import org.junit.jupiter.api.Test;
import java.util.List;
import static org.junit.jupiter.api.Assertions.*;

class PolygonUtilsTest {

    @Test
    void testPolygonAreaSquare() {
        var square = List.of(
                new Vector2D(0,0),
                new Vector2D(1,0),
                new Vector2D(1,1),
                new Vector2D(0,1)
        );
        assertEquals(1.0, PolygonUtils.area(square), 1e-12);
    }

    @Test
    void testIsConvexTrue() {
        var square = List.of(
                new Vector2D(0,0),
                new Vector2D(1,0),
                new Vector2D(1,1),
                new Vector2D(0,1)
        );
        assertTrue(PolygonUtils.isConvex(square));
    }

    @Test
    void testIsConvexFalse() {
        var concave = List.of(
                new Vector2D(0,0),
                new Vector2D(2,0),
                new Vector2D(1,1),
                new Vector2D(2,2),
                new Vector2D(0,2)
        );
        assertFalse(PolygonUtils.isConvex(concave));
    }
}
