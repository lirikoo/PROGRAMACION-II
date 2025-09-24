package laboratorio_2;

public class TestAv {
    public static void main(String[] args) {
        AlgebraVectorial v1 = new AlgebraVectorial(new double[]{2, 3}, new double[]{-3, 2});
        AlgebraVectorial v2 = new AlgebraVectorial(new double[]{-3, 2}, null);

        // (1) Perpendicularidad
        System.out.println("¿Perpendiculares?: " + v1.esPerpendicular());

        // (2) Paralelismo
        System.out.println("¿Paralelos?: " + v1.esParalela());

        // (3) Proyección de v1 sobre v2
        System.out.println("Proyección de v1 sobre v2: " + java.util.Arrays.toString(v1.proyeccion_de_a_sobre_b()));

        // (4) Componente de v1 sobre v2
        System.out.println("Componente de v1 sobre v2: " + v1.componente_de_a_en_b());
    }
}
