package com.example.geomath;

import java.util.Objects;

public final class Vector2D {
    private final double x;
    private final double y;

    public Vector2D(double x, double y) {
        this.x = x;
        this.y = y;
    }

    public double x() { return x; }
    public double y() { return y; }

    public Vector2D add(Vector2D other) {
        return new Vector2D(this.x + other.x, this.y + other.y);
    }

    public Vector2D subtract(Vector2D other) {
        return new Vector2D(this.x - other.x, this.y - other.y);
    }

    public Vector2D scale(double k) {
        return new Vector2D(this.x * k, this.y * k);
    }

    public double dot(Vector2D other) {
        return this.x * other.x + this.y * other.y;
    }

    public double cross(Vector2D other) {
        return this.x * other.y - this.y * other.x;
    }

    public double norm() {
        return Math.hypot(this.x, this.y);
    }

    /**
     * Rotate this vector counter-clockwise by the given angle.
     * @param angle angle value
     * @param degrees if true, angle is in degrees; otherwise radians
     *
     * INTENTIONAL BUG (for the demo):
     * - When degrees==true, this currently treats the input as radians.
     */
    public Vector2D rotate(double angle, boolean degrees) {
        double theta = angle; // BUG: should convert when degrees==true
        // if (degrees) {
        //     theta = Math.toRadians(angle);
        // }
        double c = Math.cos(theta);
        double s = Math.sin(theta);
        return new Vector2D(this.x * c - this.y * s, this.x * s + this.y * c);
    }

    public boolean almostEquals(Vector2D other, double tol) {
        return Math.abs(this.x - other.x) <= tol && Math.abs(this.y - other.y) <= tol;
    }

    @Override
    public String toString() {
        return "Vector2D(" + x + ", " + y + ")";
    }

    // Deliberately do not override equals/hashCode for exact FP equality,
    // tests use almostEquals instead.
    @Override
    public boolean equals(Object o) {
        return super.equals(o);
    }

    @Override
    public int hashCode() {
        return Objects.hash(x, y);
    }
}
