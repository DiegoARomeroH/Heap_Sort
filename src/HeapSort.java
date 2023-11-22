import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

import files.Files;

public class HeapSort {

    public static void main(String[] args) throws IOException {
        Files files = new Files();

        // Obtener el nombre del archivo desde la entrada del usuario o de alguna manera
        System.out.println("Ingrese el nombre del archivo:");
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        String fileName = reader.readLine();

        // Mostrar el contenido del archivo antes de ordenar
        System.out.println("Contenido del archivo antes de ordenar:");
        mostrarContenidoArchivo(files, fileName);

        // Leer el archivo y obtener un arreglo de int
        int[] array = files.archivoAint(fileName);

        // Aplicar el algoritmo de Heap Sort
        heapSort(array);

        // Escribir el arreglo ordenado de nuevo en el archivo
        files.arregloArchivo(fileName, array);

        // Mostrar el arreglo ordenado
        System.out.println("Arreglo ordenado:");
        for (int num : array) {
            System.out.print(num + " ");
        }
    }

    public static void mostrarContenidoArchivo(Files files, String fileName) {
        // Obtener el contenido del archivo como un arreglo de strings
        String[] contenido = files.archivoAstrings(fileName);

        // Mostrar cada línea del archivo
        for (String linea : contenido) {
            System.out.println(linea);
        }
        System.out.println(); // Agregar una línea en blanco para mayor claridad
    }

    public static void heapSort(int[] arr) {
        int n = arr.length;

        // Construir un montículo (heap) máximo
        for (int i = n / 2 - 1; i >= 0; i--) {
            heapify(arr, n, i);
        }

        // Extraer elementos del montículo uno por uno
        for (int i = n - 1; i > 0; i--) {
            // Mover la raíz actual al final del arreglo
            int temp = arr[0];
            arr[0] = arr[i];
            arr[i] = temp;

            // Llamar a heapify en el montículo reducido
            heapify(arr, i, 0);
        }
    }

    // Para construir un montículo (heap) subárbol que tiene la raíz en el índice 'i'
    static void heapify(int[] arr, int n, int i) {
        int largest = i; // Inicializar la raíz como el máximo
        int left = 2 * i + 1; // índice del hijo izquierdo = 2 * i + 1
        int right = 2 * i + 2; // índice del hijo derecho = 2 * i + 2

        // Si el hijo izquierdo es más grande que la raíz
        if (left < n && arr[left] > arr[largest]) {
            largest = left;
        }

        // Si el hijo derecho es más grande que el máximo hasta ahora
        if (right < n && arr[right] > arr[largest]) {
            largest = right;
        }

        // Si el máximo no es la raíz
        if (largest != i) {
            int swap = arr[i];
            arr[i] = arr[largest];
            arr[largest] = swap;

            // Recursivamente heapify el subárbol afectado
            heapify(arr, n, largest);
        }
    }
}
