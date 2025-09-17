"""Unit tests for :mod:`geomath.polygon_utils`."""

from __future__ import annotations

from geomath.polygon_utils import area, is_convex
from geomath.vector2d import Vector2D


def square() -> list[Vector2D]:
    return [
        Vector2D(0, 0),
        Vector2D(1, 0),
        Vector2D(1, 1),
        Vector2D(0, 1),
    ]


def test_polygon_area_square() -> None:
    assert area(square()) == 1.0


def test_is_convex_true() -> None:
    assert is_convex(square())


def test_is_convex_false() -> None:
    concave = [
        Vector2D(0, 0),
        Vector2D(2, 0),
        Vector2D(1, 1),
        Vector2D(2, 2),
        Vector2D(0, 2),
    ]
    assert not is_convex(concave)
