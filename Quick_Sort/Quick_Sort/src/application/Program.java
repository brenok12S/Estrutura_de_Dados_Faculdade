package application;

import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Random;

public class Program {

    public static void main(String[] args) {
        
        int[] sizes = {10, 100, 1000, 10000, 100000};
        
        try (FileWriter fileWriter = new FileWriter("C:\\temp\\output.txt");
             PrintWriter printWriter = new PrintWriter(fileWriter)) {
        	
        	printWriter.println("-------------------------- Quick Sort -------------------------------------------------");
            
            for (int size : sizes) {
                int[] vet = generateRandomArray(size);

                // Exibir e medir o tempo para o vetor aleatório (sem ordenar)
                long randomDuration = measureRandomArray(vet);
                printWriter.println("Tempo de execução para vetor de tamanho " + size + " (aleatório): " + randomDuration + " nanosegundos");
                System.out.println("Tempo de execução para vetor de tamanho " + size + " (aleatório): " + randomDuration + " nanosegundos");
                
                // Exibir e medir tempo de execução para ordenação crescente
                long ascendingDuration = sortAndMeasureTime(vet, true);
                printWriter.println("Tempo de execução para vetor de tamanho " + size + " (crescente): " + ascendingDuration + " nanosegundos");
                System.out.println("Tempo de execução para vetor de tamanho " + size + " (crescente): " + ascendingDuration + " nanosegundos");
                
                // Exibir e medir tempo de execução para ordenação decrescente
                long descendingDuration = sortAndMeasureTime(vet, false);
                printWriter.println("Tempo de execução para vetor de tamanho " + size + " (decrescente): " + descendingDuration + " nanosegundos");
                System.out.println("Tempo de execução para vetor de tamanho " + size + " (decrescente): " + descendingDuration + " nanosegundos");
                
                printWriter.println("----------------------------------------------------------------------------------------");
                
            }
            
        } catch (IOException e) {
            System.err.println("Erro ao escrever no arquivo: " + e.getMessage());
        }
    }

    private static int[] generateRandomArray(int size) {
        Random random = new Random();
        int[] array = new int[size];
        for (int i = 0; i < size; i++) {
            array[i] = random.nextInt(10000);
        }
        return array;
    }

    private static long measureRandomArray(int[] vet) {
        long startTime = System.nanoTime();
        // Simula o processamento do vetor aleatório
        long endTime = System.nanoTime();
        return endTime - startTime;
    }

    private static long sortAndMeasureTime(int[] vet, boolean ascending) {
        int[] arrayToSort = vet.clone();
        long startTime = System.nanoTime();

        quickSort(arrayToSort, 0, arrayToSort.length - 1, ascending);
        
        long endTime = System.nanoTime();
        return endTime - startTime;
    }

    private static void quickSort(int[] array, int low, int high, boolean ascending) {
        if (low < high) {
            int pi = partition(array, low, high, ascending);

            quickSort(array, low, pi - 1, ascending);
            quickSort(array, pi + 1, high, ascending);
        }
    }

    private static int partition(int[] array, int low, int high, boolean ascending) {
        int pivot = array[high];
        int i = (low - 1);
        for (int j = low; j < high; j++) {
            if ((ascending && array[j] <= pivot) || (!ascending && array[j] >= pivot)) {
                i++;

                int temp = array[i];
                array[i] = array[j];
                array[j] = temp;
            }
        }

        int temp = array[i + 1];
        array[i + 1] = array[high];
        array[high] = temp;

        return i + 1;
    }

    private static String arrayToString(int[] array) {
        StringBuilder sb = new StringBuilder();
        sb.append("[");
        for (int i = 0; i < array.length; i++) {
            sb.append(array[i]);
            if (i < array.length - 1) {
                sb.append(", ");
            }
        }
        sb.append("]");
        return sb.toString();
    }
}
