import math


class Vector:
    def __init__(self, x, y, z):
        self.x = x
        self.y = y
        self.z = z

    def __str__(self):
        return f"({self.x}, {self.y}, {self.z})"

    def __add__(self, otro):
        return Vector(self.x + otro.x, self.y + otro.y, self.z + otro.z)

    def __sub__(self, otro):
        return Vector(self.x - otro.x, self.y - otro.y, self.z - otro.z)

    def __mul__(self, otro):
        if isinstance(otro, (int, float)):
            return Vector(self.x * otro, self.y * otro, self.z * otro)
        elif isinstance(otro, Vector):
            return self.x * otro.x + self.y * otro.y + self.z * otro.z
        else:
            raise TypeError("Operación no soportada")

    def __rmul__(self, escalar):
        return self.__mul__(escalar)

    def __eq__(self, otro):
        if not isinstance(otro, Vector):
            return False
        epsilon = 1e-9
        return (math.isclose(self.x, otro.x, rel_tol=epsilon) and
                math.isclose(self.y, otro.y, rel_tol=epsilon) and
                math.isclose(self.z, otro.z, rel_tol=epsilon))

    def magnitud(self):
        return math.sqrt(self.x ** 2 + self.y ** 2 + self.z ** 2)

    def normal(self):
        mag = self.magnitud()
        if mag == 0:
            return Vector(0, 0, 0)
        return Vector(self.x / mag, self.y / mag, self.z / mag)

    def producto_cruz(self, otro):
        cx = self.y * otro.z - self.z * otro.y
        cy = self.z * otro.x - self.x * otro.z
        cz = self.x * otro.y - self.y * otro.x
        return Vector(cx, cy, cz)

    def es_perpendicular(self, otro):
        prod_punto = self * otro
        if math.isclose(prod_punto, 0, rel_tol=1e-9):
            return True

        diag1 = (self + otro).magnitud()
        diag2 = (self - otro).magnitud()
        if math.isclose(diag1, diag2, rel_tol=1e-9):
            return True

        return False

    def proyeccion_sobre(self, otro):
        if otro.magnitud() == 0:
            return Vector(0, 0, 0)

        escalar = (self * otro) / (otro.magnitud() ** 2)
        return otro * escalar

    def componente_sobre(self, otro):
        if otro.magnitud() == 0:
            return 0.0
        return (self * otro) / otro.magnitud()


if __name__ == "__main__":
    a = Vector(1, 2, 3)
    b = Vector(4, 5, 6)
    c = Vector(-2, 1, 0)

    print(f"a: {a}")
    print(f"b: {b}")
    print(f"a + b: {a + b}")
    print(f"2 * a: {2 * a}")
    print(f"|a|: {a.magnitud():.2f}")
    print(f"a normalizado: {a.normal()}")
    print(f"a . b (prod. punto): {a * b}")
    print(f"a x b (prod. cruz): {a.producto_cruz(b)}")
    print(f"¿(1,2,0) y (-2,1,0) son perpendiculares? {Vector(1, 2, 0).es_perpendicular(c)}")
    print(f"Proyección de a sobre b: {a.proyeccion_sobre(b)}")
    print(f"Componente de a en b: {a.componente_sobre(b):.2f}")