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
        	
        	printWriter.println("-------------------------- Insertion Sort -------------------------------------------------");
            
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

        if (ascending) {
            insertionSort(arrayToSort, true);
        } else {
            insertionSort(arrayToSort, false);
        }
        
        long endTime = System.nanoTime();
        return endTime - startTime;
    }

    private static void insertionSort(int[] array, boolean ascending) {
        for (int i = 1; i < array.length; i++) {
            int key = array[i];
            int j = i - 1;

            if (ascending) {
                while (j >= 0 && array[j] > key) {
                    array[j + 1] = array[j];
                    j = j - 1;
                }
            } else {
                while (j >= 0 && array[j] < key) {
                    array[j + 1] = array[j];
                    j = j - 1;
                }
            }
            array[j + 1] = key;
        }
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