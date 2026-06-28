package pe.com.utp.algoritmos.ordenamiento;

import pe.com.utp.modelo.pago.Pago;

public class MergeSortPagos {

    /*
     * MergeSort aplicado a pagos.
     * Divide el arreglo y luego fusiona los pagos ordenados por monto.
     * Es util cuando se quiere explicar divide y venceras con arreglos.
     */

    public void ordenarPorMonto(Pago[] pagos, int inicio, int fin) {
        if (pagos == null || inicio >= fin) {
            // Caso base: si el tramo tiene cero o un elemento, ya esta ordenado.
            return;
        }

        // La mitad separa el arreglo en dos subproblemas mas pequenos.
        int mitad = (inicio + fin) / 2;

        ordenarPorMonto(pagos, inicio, mitad);
        ordenarPorMonto(pagos, mitad + 1, fin);
        fusionarPorMonto(pagos, inicio, mitad, fin);
    }

    private void fusionarPorMonto(Pago[] pagos, int inicio, int mitad, int fin) {
        int tamanioIzquierda = mitad - inicio + 1;
        int tamanioDerecha = fin - mitad;

        // Arreglos auxiliares: permiten mezclar dos mitades ya ordenadas.
        Pago[] izquierda = new Pago[tamanioIzquierda];
        Pago[] derecha = new Pago[tamanioDerecha];

        for (int i = 0; i < tamanioIzquierda; i++) {
            izquierda[i] = pagos[inicio + i];
        }

        for (int j = 0; j < tamanioDerecha; j++) {
            derecha[j] = pagos[mitad + 1 + j];
        }

        int indiceIzquierda = 0;
        int indiceDerecha = 0;
        int indiceArreglo = inicio;

        while (indiceIzquierda < tamanioIzquierda && indiceDerecha < tamanioDerecha) {
            // Criterio de ordenamiento: menor monto queda primero.
            if (izquierda[indiceIzquierda].getMonto() <= derecha[indiceDerecha].getMonto()) {
                pagos[indiceArreglo] = izquierda[indiceIzquierda];
                indiceIzquierda++;
            } else {
                pagos[indiceArreglo] = derecha[indiceDerecha];
                indiceDerecha++;
            }

            indiceArreglo++;
        }

        while (indiceIzquierda < tamanioIzquierda) {
            pagos[indiceArreglo] = izquierda[indiceIzquierda];
            indiceIzquierda++;
            indiceArreglo++;
        }

        while (indiceDerecha < tamanioDerecha) {
            pagos[indiceArreglo] = derecha[indiceDerecha];
            indiceDerecha++;
            indiceArreglo++;
        }
    }
}
