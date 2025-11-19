def main():
    try:
        entrada_usuario = input("Ingrese los valores de a, b y c : ")
        coeficientes_str = entrada_usuario.split()

        if len(coeficientes_str) != 3:
            print("Error: Debe ingresar exactamente 3 valores. Inténtelo de nuevo.")
            sys.exit(1)

        a, b, c = [float(val) for val in coeficientes_str]

        # Evitar divisiones por cero si 'a' es 0
        if a == 0:
            print("Error: El coeficiente 'a' no puede ser 0 para una ecuación cuadrática.")
            sys.exit(1)

        ec = EcuacionCuadratica(a, b, c)
        discriminante = ec.getDiscriminante()

        if discriminante > 0:
            raiz1 = ec.getRaiz1()
            raiz2 = ec.getRaiz2()
            print(f"La ecuación tiene dos raíces {raiz1:.6f} y {raiz2:.6f}")
        elif discriminante == 0:
            raiz = ec.getRaiz1()
            print(f"La ecuación tiene una raíz {raiz:.6f}")
        else:
            print("La ecuación no tiene raíces reales")

    except ValueError:
        print("Error: Entrada inválida. Asegúrese de ingresar solo números para los coeficientes.")
    except Exception as e:
        print(f"Ocurrió un error inesperado: {e}")
if __name__ == "__main__":
import sys
from EcuacionCuadratica import EcuacionCuadratica


def main():
    try:
        entrada_usuario = input("Ingrese los valores de a, b y c : ")
        coeficientes_str = entrada_usuario.split()

        if len(coeficientes_str) != 3:
            print("Error: Debe ingresar exactamente 3 valores. Inténtelo de nuevo.")
            sys.exit(1)

        a, b, c = [float(val) for val in coeficientes_str]

        # Evitar divisiones por cero si 'a' es 0
        if a == 0:
            print("Error: El coeficiente 'a' no puede ser 0 para una ecuación cuadrática.")
            sys.exit(1)

        ec = EcuacionCuadratica(a, b, c)
        discriminante = ec.getDiscriminante()

        if discriminante > 0:
            raiz1 = ec.getRaiz1()
            raiz2 = ec.getRaiz2()
            print(f"La ecuación tiene dos raíces {raiz1:.6f} y {raiz2:.6f}")
        elif discriminante == 0:
            raiz = ec.getRaiz1()
            print(f"La ecuación tiene una raíz {raiz:.6f}")
        else:
            print("La ecuación no tiene raíces reales")

    except ValueError:
        print("Error: Entrada inválida. Asegúrese de ingresar solo números para los coeficientes.")
    except Exception as e:
        print(f"Ocurrió un error inesperado: {e}")
if __name__ == "__main__":
    main()