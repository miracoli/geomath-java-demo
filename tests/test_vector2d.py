"""Unit tests for :mod:`geomath.vector2d`."""

from __future__ import annotations

import math

import pytest

from geomath.vector2d import Vector2D


def assert_approx(vec: Vector2D, x: float, y: float, tol: float = 1e-12) -> None:
    expected = Vector2D(x, y)
    assert vec.almost_equals(expected, tol), (
        f"Expected approximately ({x}, {y}) but got {vec}"
    )


def test_add_sub_dot_norm() -> None:
    a = Vector2D(1, 2)
    b = Vector2D(3, 4)
    assert_approx(a.add(b), 4, 6)
    assert_approx(b.subtract(a), 2, 2)
    assert a.dot(b) == pytest.approx(11.0)
    assert a.norm() == pytest.approx(math.sqrt(5.0))


@pytest.mark.xfail(reason="Known bug: rotate treats degree input as radians", strict=True)
def test_rotate_degrees_bug() -> None:
    # Rotating (1, 0) by +90 degrees should give (0, 1)
    v = Vector2D(1, 0).rotate(90.0, True)
    assert v.almost_equals(Vector2D(0, 1), 1e-9)


def test_rotate_radians_ok() -> None:
    v = Vector2D(1, 0).rotate(math.pi / 2, False)
    assert v.almost_equals(Vector2D(0, 1), 1e-9)
