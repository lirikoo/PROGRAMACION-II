import random
from Juego import Juego
class JuegoAdivinaNumero(Juego):

    def __init__(self, numero_de_vidas):
        super().__init__(numero_de_vidas)
        self._numero_a_adivinar = 0

    def get_numero_a_adivinar(self):
        return self._numero_a_adivinar

    def set_numero_a_adivinar(self, numero):
        self._numero_a_adivinar = numero

    def valida_numero(self, numero):
        return 0 <= numero <= 10

    def juega(self):
        self.reinicia_partida()
        self.set_numero_a_adivinar(random.randint(0, 10))

        print(f"¡Adivina un número entre 0 y 10!")
        while self.get_numero_de_vidas() > 0:
            try:
                print(f"Tienes {self.get_numero_de_vidas()} vidas.")
                intento = int(input("Ingresa tu intento: "))

                if not self.valida_numero(intento):
                    continue

                if intento == self.get_numero_a_adivinar():
                    self.actualiza_record()
                    print("¡Acertaste!")
                    return
                else:
                    if self.quita_vida():
                        if intento < self.get_numero_a_adivinar():
                            print("El número a adivinar es mayor. ¡Intenta de nuevo!")
                        else:
                            print("El número a adivinar es menor. ¡Intenta de nuevo!")
                    else:
                        print(f"El número era {self.get_numero_a_adivinar()}")
                        return
            except ValueError:
                print("Entrada inválida. Por favor, ingresa un número entero.")


class JuegoAdivinaPar(JuegoAdivinaNumero):

    def valida_numero(self, numero):
        if super().valida_numero(numero):
            if numero % 2 == 0:
                return True
            else:
                print("Error: El número ingresado es impar. Debe ser par.")
                return False
        return False


class JuegoAdivinaImpar(JuegoAdivinaNumero):

    def valida_numero(self, numero):
        if super().valida_numero(numero):
            if numero % 2 != 0:
                return True
            else:
                print("Error: El número ingresado es par. Debe ser impar.")
                return False
        return False


class Aplicacion2:
    @staticmethod
    def main():
        juego_normal = JuegoAdivinaNumero(3)
        print("--- JUEGO: ADIVINA CUALQUIER NÚMERO ---")
        juego_normal.juega()
        print("\n" + "=" * 50 + "\n")

        juego_par = JuegoAdivinaPar(3)
        print("--- JUEGO: ADIVINA UN NÚMERO PAR ---")
        juego_par.juega()
        print("\n" + "=" * 50 + "\n")

        juego_impar = JuegoAdivinaImpar(3)
        print("--- JUEGO: ADIVINA UN NÚMERO IMPAR ---")
        juego_impar.juega()


if __name__ == "__main__":
    Aplicacion2.main()