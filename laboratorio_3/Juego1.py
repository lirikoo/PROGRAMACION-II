import random
from Juego import Juego  # Se importa la clase padre

class JuegoAdivinaNumero(Juego):
    def __init__(self, numero_de_vidas):
        super().__init__(numero_de_vidas)
        self._numero_a_adivinar = 0

    def get_numero_a_adivinar(self):
        return self._numero_a_adivinar

    def set_numero_a_adivinar(self, numero):
        self._numero_a_adivinar = numero

    def juega(self):

        self.reinicia_partida()
        self.set_numero_a_adivinar(random.randint(0, 10))

        print("¡Bienvenido al juego de adivinar el número!")
        while self.get_numero_de_vidas() > 0:
            try:
                print(f"Tienes {self.get_numero_de_vidas()} vidas.")
                intento = int(input("Adivina un número entre 0 y 10: "))

                if intento == self.get_numero_a_adivinar():
                    self.actualiza_record()
                    print("¡Acertaste!")
                    return
                else:
                    print("¡Número incorrecto!")
                    if self.quita_vida():
                        if intento < self.get_numero_a_adivinar():
                            print("El número a adivinar es mayor. ¡Intenta de nuevo!")
                        else:
                            print("El número a adivinar es menor. ¡Intenta de nuevo!")
            except ValueError:
                print("Entrada inválida. Por favor, ingresa un número entero.")

class Aplicacion1:

    @staticmethod
    def main():
        juego = JuegoAdivinaNumero(5)  # Se crea una instancia con 5 vidas
        juego.juega()

if __name__ == "__main__":
    Aplicacion1.main()