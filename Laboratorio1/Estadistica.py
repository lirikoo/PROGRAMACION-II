<<<<<<< HEAD
import math


class Estadistica:
    def __init__(self, lista):
        self.__lista_numeros = lista

    def promedio(self):
        if not self.__lista_numeros:
            return 0
        suma = sum(self.__lista_numeros)
        n = len(self.__lista_numeros)
        return suma / n

    def desviacion(self):
        if len(self.__lista_numeros) < 2:
            return 0

        prom = self.promedio()
        suma_cuadrados = 0
        for x in self.__lista_numeros:
            suma_cuadrados += (x - prom) ** 2

        n = len(self.__lista_numeros)
        varianza = suma_cuadrados / (n - 1)
=======
import math


class Estadistica:
    def __init__(self, lista):
        self.__lista_numeros = lista

    def promedio(self):
        if not self.__lista_numeros:
            return 0
        suma = sum(self.__lista_numeros)
        n = len(self.__lista_numeros)
        return suma / n

    def desviacion(self):
        if len(self.__lista_numeros) < 2:
            return 0

        prom = self.promedio()
        suma_cuadrados = 0
        for x in self.__lista_numeros:
            suma_cuadrados += (x - prom) ** 2

        n = len(self.__lista_numeros)
        varianza = suma_cuadrados / (n - 1)
>>>>>>> 73efa502bfc2416a2695050ec53eb297133162ef
        return math.sqrt(varianza)