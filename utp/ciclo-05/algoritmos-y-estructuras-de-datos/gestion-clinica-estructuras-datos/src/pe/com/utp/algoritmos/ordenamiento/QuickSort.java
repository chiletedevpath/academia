package pe.com.utp.algoritmos.ordenamiento;

import java.util.Comparator;

public class QuickSort {

    /*
     * QuickSort reutilizable para arreglos de objetos.
     * <T> indica que el algoritmo es generico y puede ordenar distintos tipos.
     * Comparator es una interfaz de Java que recibe el criterio de comparacion.
     */

    public <T> void ordenar(T[] arreglo, int inicio, int fin, Comparator<T> comparador) {
        if (arreglo == null || comparador == null) {
            // return corta el metodo si no hay arreglo o criterio valido para ordenar.
            return;
        }

        if (inicio < fin) {
            // El pivote queda en su posicion final y divide el problema en dos partes.
            int posicionPivote = particionar(arreglo, inicio, fin, comparador);

            // Recursion: se ordena la parte izquierda y luego la parte derecha.
            ordenar(arreglo, inicio, posicionPivote - 1, comparador);
            ordenar(arreglo, posicionPivote + 1, fin, comparador);
        }
    }

    private <T> int particionar(T[] arreglo, int inicio, int fin, Comparator<T> comparador) {
        // Se toma como pivote el ultimo elemento del tramo evaluado.
        T pivote = arreglo[fin];
        int indiceMenor = inicio - 1;

        for (int indiceActual = inicio; indiceActual < fin; indiceActual++) {
            // compare devuelve negativo, cero o positivo segun el orden definido en el Main.
            if (comparador.compare(arreglo[indiceActual], pivote) <= 0) {
                indiceMenor++;
                intercambiar(arreglo, indiceMenor, indiceActual);
            }
        }

        intercambiar(arreglo, indiceMenor + 1, fin);
        return indiceMenor + 1;
    }

    private <T> void intercambiar(T[] arreglo, int posicionA, int posicionB) {
        T auxiliar = arreglo[posicionA];

        arreglo[posicionA] = arreglo[posicionB];
        arreglo[posicionB] = auxiliar;
    }
}
