<<<<<<< HEAD
class EcuacionLineal:

    def __init__(self, a, b, c, d, e, f):
        self.__a = a
        self.__b = b
        self.__c = c
        self.__d = d
        self.__e = e
        self.__f = f

    def tiene_solucion(self):
        denominador = (self.__a * self.__d - self.__b * self.__c)
        return abs(denominador) > 1e-9

    def get_x(self):
        return (self.__e * self.__d - self.__b * self.__f) / (self.__a * self.__d - self.__b * self.__c)

    def get_y(self):
=======
class EcuacionLineal:

    def __init__(self, a, b, c, d, e, f):
        self.__a = a
        self.__b = b
        self.__c = c
        self.__d = d
        self.__e = e
        self.__f = f

    def tiene_solucion(self):
        denominador = (self.__a * self.__d - self.__b * self.__c)
        return abs(denominador) > 1e-9

    def get_x(self):
        return (self.__e * self.__d - self.__b * self.__f) / (self.__a * self.__d - self.__b * self.__c)

    def get_y(self):
>>>>>>> 73efa502bfc2416a2695050ec53eb297133162ef
        return (self.__a * self.__f - self.__e * self.__c) / (self.__a * self.__d - self.__b * self.__c)