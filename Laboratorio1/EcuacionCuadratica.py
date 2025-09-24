import math

class EcuacionCuadratica:
    def __init__(self, a, b, c):
        self.__a = a
        self.__b = b
        self.__c = c

    def getDiscriminante(self):
        return self.__b ** 2 - 4 * self.__a * self.__c
    def getRaiz1(self):
        discriminante = self.getDiscriminante()
        if discriminante >= 0:
            return (-self.__b + math.sqrt(discriminante)) / (2 * self.__a)
        else:
            return 0
    def getRaiz2(self):
        discriminante = self.getDiscriminante()
        if discriminante >= 0:
            return (-self.__b - math.sqrt(discriminante)) / (2 * self.__a)
        else:
            return 0