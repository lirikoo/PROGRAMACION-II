class Juego:

    def __init__(self, numero_de_vidas):
        self._numero_de_vidas = numero_de_vidas
        self._record = 0
        self._vidas_iniciales = numero_de_vidas

    def get_numero_de_vidas(self):
        return self._numero_de_vidas

    def set_numero_de_vidas(self, vidas):
        self._numero_de_vidas = vidas

    def get_record(self):
        return self._record

    def set_record(self, record):
        self._record = record

    def reinicia_partida(self):
        self._numero_de_vidas = self._vidas_iniciales

    @staticmethod
    def actualiza_record():
        print("¡Acertaste! Se actualizó tu récord.")

    def quita_vida(self):
        self._numero_de_vidas -= 1
        if self._numero_de_vidas > 0:
            return True
        else:
            print("¡Te quedaste sin vidas! Fin del juego.")
            return False