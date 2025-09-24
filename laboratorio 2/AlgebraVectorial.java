package laboratorio_2;
import java.util.Arrays;
class AlgebraVectorial {
    private double[] a;
    private double[] b;

    public AlgebraVectorial(double[] a, double[] b) {
        this.a = a;
        this.b = b;
    }

    public AlgebraVectorial(double[] a) {
        this.a = a;
    }

    private double calcularMagnitud(double[] v) {
        double s = 0;
        for (double c : v) {
            s += c * c;
        }
        return Math.sqrt(s);
    }

    private double calcularProductoPunto(double[] v1, double[] v2) {
        double p = 0;
        for (int i = 0; i < v1.length; i++) {
            p += v1[i] * v2[i];
        }
        return p;
    }

    // (1) Determinar si dos vectores son perpendiculares
    public boolean esPerpendicular() {
        if (b == null) {
            return false;
        }

        // a)
        double[] s = new double[a.length];
        double[] d = new double[a.length];
        for (int i = 0; i < a.length; i++) {
            s[i] = a[i] + b[i];
            d[i] = a[i] - b[i];
        }
        double mS = calcularMagnitud(s);
        double mD = calcularMagnitud(d);
        if (Math.abs(mS - mD) < 1e-9) {
            return true;
        }

        // b)
        double[] dAB = new double[a.length];
        double[] dBA = new double[a.length];
        for (int i = 0; i < a.length; i++) {
            dAB[i] = a[i] - b[i];
            dBA[i] = b[i] - a[i];
        }
        double mDAB = calcularMagnitud(dAB);
        double mDBA = calcularMagnitud(dBA);
        if (Math.abs(mDAB - mDBA) < 1e-9) {
            return true;
        }

        // c)
        double p = calcularProductoPunto(a, b);
        if (Math.abs(p) < 1e-9) {
            return true;
        }

        // d)
        double mCSq = mS * mS;
        double mASq = calcularProductoPunto(a, a);
        double mBSq = calcularProductoPunto(b, b);
        if (Math.abs(mCSq - (mASq + mBSq)) < 1e-9) {
            return true;
        }

        return false;
    }

    // (2) Determinar si dos vectores son paralelos
    public boolean esParalela() {
        if (b == null) {
            return false;
        }

        // e)
        double r = 0.0;
        boolean encontrado = false;
        boolean esParalelo = true;
        for (int i = 0; i < a.length; i++) {
            if (b[i] != 0) {
                if (!encontrado) {
                    r = a[i] / b[i];
                    encontrado = true;
                } else {
                    if (Math.abs((a[i] / b[i]) - r) > 1e-9) {
                        esParalelo = false;
                        break;
                    }
                }
            } else if (a[i] != 0) {
                esParalelo = false;
                break;
            }
        }
        if (esParalelo) {
            return true;
        }

        // f)
        if (a.length == 3 && b.length == 3) {
            double cX = a[1] * b[2] - a[2] * b[1];
            double cY = a[2] * b[0] - a[0] * b[2];
            double cZ = a[0] * b[1] - a[1] * b[0];

            if (Math.abs(cX) < 1e-9 && Math.abs(cY) < 1e-9 && Math.abs(cZ) < 1e-9) {
                return true;
            }
        }
        return false;
    }

    // (3) Determinar la proyección de a sobre b
    public double[] proyeccion_de_a_sobre_b() {
        if (b == null || calcularMagnitud(b) == 0) {
            return null;
        }

        double p = calcularProductoPunto(a, b);
        double mBSq = calcularProductoPunto(b, b);
        double e = p / mBSq;

        double[] proyeccion = new double[b.length];
        for (int i = 0; i < b.length; i++) {
            proyeccion[i] = e * b[i];
        }
        return proyeccion;
    }

    // (4) Determinar el componente de a en b
    public double componente_de_a_en_b() {
        if (b == null || calcularMagnitud(b) == 0) {
            return 0;
        }

        double p = calcularProductoPunto(a, b);
        double mB = calcularMagnitud(b);

        if (Math.abs(mB) < 1e-9) {
            return 0;
        }
        return p / mB;
    }
}
