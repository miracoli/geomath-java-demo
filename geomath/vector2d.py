"""2D vector helpers for the geomath demo project.

This module intentionally mirrors the original Java implementation.
It includes the same deliberate bug in :meth:`Vector2D.rotate` where the
``degrees`` flag is ignored.
"""

from __future__ import annotations

from dataclasses import dataclass
import math


@dataclass(frozen=True)
class Vector2D:
    """Immutable two dimensional vector."""

    x: float
    y: float

    def add(self, other: "Vector2D") -> "Vector2D":
        """Return the vector sum of ``self`` and ``other``."""

        return Vector2D(self.x + other.x, self.y + other.y)

    def subtract(self, other: "Vector2D") -> "Vector2D":
        """Return the vector difference ``self - other``."""

        return Vector2D(self.x - other.x, self.y - other.y)

    def scale(self, k: float) -> "Vector2D":
        """Return the vector scaled by ``k``."""

        return Vector2D(self.x * k, self.y * k)

    def dot(self, other: "Vector2D") -> float:
        """Return the dot product of the vectors."""

        return self.x * other.x + self.y * other.y

    def cross(self, other: "Vector2D") -> float:
        """Return the 2D cross product magnitude (signed area)."""

        return self.x * other.y - self.y * other.x

    def norm(self) -> float:
        """Return the Euclidean magnitude of the vector."""

        return math.hypot(self.x, self.y)

    def rotate(self, angle: float, degrees: bool) -> "Vector2D":
        """Rotate the vector counter-clockwise by ``angle``.

        The ``degrees`` flag is supposed to indicate the angle unit. This
        implementation intentionally ignores the flag to preserve the bug
        present in the original Java version.
        """

        theta = angle  # BUG: should convert when degrees is True
        c = math.cos(theta)
        s = math.sin(theta)
        return Vector2D(self.x * c - self.y * s, self.x * s + self.y * c)

    def almost_equals(self, other: "Vector2D", tol: float) -> bool:
        """Return ``True`` when the coordinates differ by at most ``tol``."""

        return abs(self.x - other.x) <= tol and abs(self.y - other.y) <= tol
