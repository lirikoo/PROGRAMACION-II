import sys
from Estadistica import Estadistica


def main():
    try:
        entrada_usuario = input("Ingrese 10 números : ")
        numeros_str = entrada_usuario.split()

        if len(numeros_str) != 10:
            print("Error: Debe ingresar exactamente 10 números. Inténtelo de nuevo.")
            sys.exit(1)

        numeros_float = [float(val) for val in numeros_str]

        # Creamos una instancia de la clase Estadistica
        estadistica = Estadistica(numeros_float)

        promedio = estadistica.promedio()
        desviacion = estadistica.desviacion()
        print(f"El promedio es {promedio:.2f}")
        print(f"La desviación estandar es {desviacion:.6f}")
    except ValueError:
        print("Error: Entrada inválida. Asegúrese de ingresar solo números.")
    except Exception as e:
        print(f"Ocurrió un error inesperado: {e}")
if __name__ == "__main__":
    main()