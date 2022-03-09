/**
 * Implementing a Heapsort class that contains a sort and a reheapdown method
 * 
 * @author Shivam Maji
 * @version 3/8/2021
 * 
 * @author Period - 6
 * @author Assignment - Lab: JM Ch25.5 - Heapsort
 * 
 * @author Sources - Me Myself and I
 */
public class Heapsort {
    /**
     * Main Heapsort Method
     * 
     * @param a Array you want to sort
     */
    public static void sort(double[] a) {
        int n = a.length;
        for (int i = n / 2; i >= 1; i--) {
            reheapDown(a, i, n);
        }

        while (n > 1) {
            double temp = a[0];
            a[0] = a[n - 1];
            a[n - 1] = temp;
            n--;
            reheapDown(a, 1, n);
        }

    }

    /**
     * Helper Method for Heapsort
     * 
     * Assuming that the root of the heap is the only element out of place, restore
     * the heap property: data[Parent] >= data[Child] O(log n)
     * 
     * @param a Data
     * @param i Root
     * @param n Size
     */
    public static void reheapDown(double[] a, int i, int n) {
        while (2 * i - 1 < n) {
            int largest = i * 2;
            if (n <= 1) {
                break;
            }
            if (i * 2 < n && a[i * 2] > a[i * 2 - 1]) {
                largest = i * 2 + 1;
            }
            if (a[largest - 1] > a[i - 1]) {
                double swap = a[largest - 1];
                a[largest - 1] = a[i - 1];
                a[i - 1] = swap;
            } else {
                break;
            }
            i *= 2;
        }
    }
}
