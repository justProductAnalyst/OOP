package ru.nsu.vetrov;

import java.util.Arrays;

/**
 * Class implements the heap sort algorithm.
 */
public class HeapSort {
    /**
     * Makes a heap.
     */
    public static void heapify(int[] arr, int heapSize, int currentNode) {
        int largest = currentNode;
        int leftLeaf = 2 * currentNode + 1;
        int rightLeaf = 2 * currentNode + 2;

        if (leftLeaf < heapSize && arr[leftLeaf] > arr[largest]) {
            largest = leftLeaf;
        }
        if (rightLeaf < heapSize && arr[rightLeaf] > arr[largest]) {
            largest = rightLeaf;
        }

        if (largest != currentNode) {
            int swap = arr[currentNode];
            arr[currentNode] = arr[largest];
            arr[largest] = swap;

            heapify(arr, heapSize, largest);
        }
    }

    /**
     * Sorts the given array using the heap sort method.
     */
    public static void sort(int[] arr) {
        int arrayLength = arr.length;

        for (int i = arrayLength / 2 - 1; i >= 0; i--) {
            heapify(arr, arrayLength, i);
        }

        for (int i = arrayLength - 1; i >= 0; i--) {
            int temp = arr[0];
            arr[0] = arr[i];
            arr[i] = temp;

            heapify(arr, i, 0);
        }
    }
}
