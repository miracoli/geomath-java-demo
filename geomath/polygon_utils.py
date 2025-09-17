"""Polygon helpers for the geomath demo project."""

from __future__ import annotations

from typing import Sequence

from .vector2d import Vector2D


def area(vertices: Sequence[Vector2D] | None) -> float:
    """Return the non-negative area of a simple polygon.

    The vertices are processed using the shoelace formula.  Returns ``0.0``
    when fewer than three vertices are provided.
    """

    if not vertices or len(vertices) < 3:
        return 0.0

    s = 0.0
    n = len(vertices)
    for i in range(n):
        a = vertices[i]
        b = vertices[(i + 1) % n]
        s += a.x * b.y - a.y * b.x
    return abs(s) * 0.5


def is_convex(vertices: Sequence[Vector2D] | None) -> bool:
    """Return ``True`` when the polygon defined by ``vertices`` is convex."""

    if not vertices or len(vertices) < 3:
        return False

    n = len(vertices)
    sign = 0
    for i in range(n):
        a = vertices[i]
        b = vertices[(i + 1) % n]
        c = vertices[(i + 2) % n]
        ab = b.subtract(a)
        bc = c.subtract(b)
        cross = ab.cross(bc)
        if cross != 0.0:
            curr = 1 if cross > 0 else -1
            if sign == 0:
                sign = curr
            elif sign != curr:
                return False
    return True
