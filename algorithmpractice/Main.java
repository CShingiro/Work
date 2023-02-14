package algorithmpractice;

import java.util.Arrays;

public class Main {
    public static void main(String args[]) {
        int[] arr = { 12, 11, 13, 5, 6, 7 };
        int[] arr2 = {};
        int[] arr3 = {0};
        int[] arr4 = {0, -1};
        int[] arr5 = {22,22,33,1,33,4,5};
        System.out.print("Printing original array: ");
        System.out.println(Arrays.toString(arr));

        // sort array
        mergeSort(arr, 0, arr.length - 1);
        mergeSort(arr5, 0, arr.length - 1);

        System.out.print("Printing sorted array: ");
        System.out.println(Arrays.toString(arr));
        System.out.print("Printing sorted array: ");
        System.out.println(Arrays.toString(arr2));
        System.out.print("Printing sorted array: ");
        System.out.println(Arrays.toString(arr3));
        System.out.print("Printing sorted array: ");
        System.out.println(Arrays.toString(arr4));
        System.out.print("Printing sorted array: ");
        System.out.println(Arrays.toString(arr5));
    }

    // Merges two subarrays of arr[].
    // First subarray is arr[l..m]
    // Second subarray is arr[m+1..r]
    static void merge(int arr[], int l, int m, int r) {
        int a1 = m - l + 1;
        int a2 = r - m;
        int arr1[] = new int[a1];
        int arr2[] = new int[a2];
        for(int i = 0;i<a1;i++) {
            arr1[i] = arr[i];
        }
        for(int j = 0;j<a2;j++) {
            arr2[j] = arr[m + 1 + j];
        }

        int t = 0;
        int u = 0;
        int x = l;

        while(t < a1 && u < a2) {
            if(arr1[t] <= arr2[u]) {
                arr[x] = arr1[t];
                t++;
            } else {
                arr[x] = arr2[u];
                u++;
            }
            x++;
        }

        while(t < a1) {
            arr[x] = arr1[t];
            t++;
            x++;
        }

        while(u < a2) {
            arr[x] = arr2[u];
            u++;
            x++;
        }
    }
 
    // Main function that sorts arr[l..r] using
    // merge()
    static void mergeSort(int arr[], int l, int r) {
       // Add code here
        if(l < r || arr[l] < arr[r]) {
            int middle = (l + (r - l))/2;
            merge(arr,l,middle,r);
        }
    }
}
