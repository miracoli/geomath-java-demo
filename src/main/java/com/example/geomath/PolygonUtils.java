package com.example.geomath;

import java.util.List;

public final class PolygonUtils {
    private PolygonUtils() {}

    /**
     * Non-negative polygon area via the shoelace formula.
     * Assumes points form a simple (non self-intersecting) polygon.
     */
    public static double area(List<Vector2D> pts) {
        if (pts == null || pts.size() < 3) return 0.0;
        double s = 0.0;
        int n = pts.size();
        for (int i = 0; i < n; i++) {
            Vector2D a = pts.get(i);
            Vector2D b = pts.get((i + 1) % n);
            s += a.x() * b.y() - a.y() * b.x();
        }
        return Math.abs(s) * 0.5;
    }

    /**
     * Basic convexity check: polygon edges should not change the sign
     * of successive cross products. Colinear steps are allowed.
     * Returns false for fewer than 3 points.
     */
    public static boolean isConvex(List<Vector2D> pts) {
        if (pts == null || pts.size() < 3) return false;

        int n = pts.size();
        int sign = 0; // -1, 0, +1
        for (int i = 0; i < n; i++) {
            Vector2D a = pts.get(i);
            Vector2D b = pts.get((i + 1) % n);
            Vector2D c = pts.get((i + 2) % n);
            Vector2D ab = b.subtract(a);
            Vector2D bc = c.subtract(b);
            double cross = ab.cross(bc);
            if (cross != 0.0) {
                int curr = cross > 0 ? 1 : -1;
                if (sign == 0) {
                    sign = curr;
                } else if (sign != curr) {
                    return false;
                }
            }
        }
        return true;
    }
}
