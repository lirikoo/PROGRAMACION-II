<<<<<<< HEAD
import sys
from EcuacionLineal import EcuacionLineal
def main():
    try:
        entrada_usuario = input("Ingrese los valores de a, b, c, d, e y f : ")
        coeficientes_str = entrada_usuario.split()

        if len(coeficientes_str) != 6:
            print("Error: Debe ingresar exactamente 6 valores. Inténtelo de nuevo.")
            sys.exit(1)

        coeficientes = [float(val) for val in coeficientes_str]
        a, b, c, d, e, f = coeficientes

        ec = EcuacionLineal(a, b, c, d, e, f)

        if ec.tiene_solucion():
            x = ec.get_x()
            y = ec.get_y()
            print(f"x = {x:.1f}, y = {y:.1f}")
        else:
            print("La ecuación no tiene solución")

    except ValueError:
        print("Error: Entrada inválida. Asegúrese de ingresar solo números para los coeficientes.")
    except Exception as e:
        print(f"Ocurrió un error: {e}")


if __name__ == "__main__":
=======
import sys
from EcuacionLineal import EcuacionLineal
def main():
    try:
        entrada_usuario = input("Ingrese los valores de a, b, c, d, e y f : ")
        coeficientes_str = entrada_usuario.split()

        if len(coeficientes_str) != 6:
            print("Error: Debe ingresar exactamente 6 valores. Inténtelo de nuevo.")
            sys.exit(1)

        coeficientes = [float(val) for val in coeficientes_str]
        a, b, c, d, e, f = coeficientes

        ec = EcuacionLineal(a, b, c, d, e, f)

        if ec.tiene_solucion():
            x = ec.get_x()
            y = ec.get_y()
            print(f"x = {x:.1f}, y = {y:.1f}")
        else:
            print("La ecuación no tiene solución")

    except ValueError:
        print("Error: Entrada inválida. Asegúrese de ingresar solo números para los coeficientes.")
    except Exception as e:
        print(f"Ocurrió un error: {e}")


if __name__ == "__main__":
>>>>>>> 73efa502bfc2416a2695050ec53eb297133162ef
    main()