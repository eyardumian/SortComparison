import java.util.Arrays;
import java.util.Random;
import java.io.File;
import java.io.PrintWriter;
import java.io.*;
import java.util.*;
/**
 * Application to sort an array of random numbers to compare 
 * the runnting times of InsertSort, QuickSort, and MergeSort. 
 *
 *Program 3 
 *@author Erika Yardumian - CPSC - 3273
 *@version 4/11/19
 */

public class CollectDataThree {
           
    //MergeSort algorithm to sort array A.  
   static void Merge(int A[], int p, int q, int r) {
      int n1 = q - p + 1;
      int n2 = r - q;
      int L[] = new int[n1 +1];
      int R[] = new int[n2 +1];
      for (int i = 0; i < n1; i++) {
         L[i] = A[p + i];
      }
      for (int j = 0; j < n2; j++) {
         R[j] = A[q +1 +j];
      }
      L[n1] = 100000; 
      R[n2] = 100000; 
      int i = 0;
      int j = 0;
      int k = p;
      for (k = p; k < r + 1; k++) {
         if (L[i] <= R[j]) {
            A[k] = L[i];
            i = i + 1;
         }
         else {
            A[k] = R[j];
            j = j+1;
         }         
      }          
   }
    //Second part of MergeSort algorithm to sort array A.
   static void MergeSort(int A[],int p, int r) {
      if (p < r) {
         int q = (p + r)/2;
         MergeSort(A, p, q);
         MergeSort(A, q + 1, r);
         Merge(A, p, q, r);
      }
   }
   
    //Quicksort algorithm to sort array A.
   static int partition(int A[], int low, int high) { 
     
      int pivot = A[high];  
      int i = (low - 1); // index of smaller element 
      for (int j = low; j < high; j++)  {
         if (A[j] <= pivot) 
         { 
            i++; 
         
            // swap A[i] and A[j] 
            int temp = A[i]; 
            A[i] = A[j]; 
            A[j] = temp; 
         } 
      } 
   
      // swap A[i+1] and A[high]  
      int temp = A[i+1]; 
      A[i+1] = A[high]; 
      A[high] = temp; 
   
      return i+1;  
   }
   
    //Second part of Quicksort algorithm to sort array A.
   static void sort(int A[], int low, int high) { 
     
      if (low < high) { 
         int pi = partition(A, low, high); 
      
         // Recursively sort elements before 
         // partition and after partition 
         sort(A, low, pi - 1); 
         sort(A, pi + 1, high); 
      } 
   } 
   
    //Insertion Sort to sort array A.
   static void InsertSort(int A[], int n) {
      for (int i = 1; i < n; i++) { 
         int key = A[i]; 
         int j = i - 1; 
      
        //Move left element one position forward.
         while (j >= 0 && A[j] > key) { 
            A[j + 1] = A[j]; 
            j = j - 1; 
         }
          
         A[j + 1] = key; 
      } 
   } 
  
   public static void main(String[] args) {
   //Generates random numbers.
      Random random = new Random();
   //Array to store 1000 random values.
      int[] G = new int[50000];
      int[] A = new int[50000];
      int maxvalue = 50000;
      int counter = 0;
      int n;
      ArrayList<Long> time = new ArrayList<Long>(counter);      
      ArrayList<Long> size = new ArrayList<Long>(counter);
      ArrayList<Long> MergeExecutionTime = new ArrayList<Long>(counter);
      ArrayList<Long> QuickExecutionTime = new ArrayList<Long>(counter);
      ArrayList<Long> InsertExecutionTime = new ArrayList<Long>(counter);
      //Generates the random values array.         
      for (int i = 0; i < G.length; i++) {
         G[i] = random.nextInt(maxvalue);
      }   
      for (n = 10; n < G.length + 1; n += 1000) { 
      
         String SortingAlg[] = {"InsertSort", "QuickSort", "MergeSort"};
         for (String item: SortingAlg) {
         
            if (item == "InsertSort") {
            //Copies values from array G to array A.
               for (int k = 0; k < n; k++) {
                  A[k] = G[k];
               }
            //Start timing InsertionSort 
               long InsertStartTime = System.nanoTime();
               System.out.println(Arrays.toString(A));
            //Sorts array A. 
               InsertSort(A, n);
            //End timing.
               long InsertEndTime = System.nanoTime();
               long InsertExTime = InsertEndTime - InsertStartTime;
               InsertExecutionTime.add(InsertExTime);
               System.out.println(Arrays.toString(A));
               System.out.println("I" + InsertExTime);
            }
         
            if (item == "QuickSort") {
               //Copies values from array G to array A.
               for (int k = 0; k < n; k++) {
                  A[k] = G[k];
               }   
               
            //Start timing QuickSort 
               long QuickStartTime = System.nanoTime();
               System.out.println(Arrays.toString(A));
            //Sorts array A. 
               sort(A, 0, n - 1);
            //End timing.
               long QuickEndTime = System.nanoTime();
            
               long QuickExTime = QuickEndTime - QuickStartTime;
               QuickExecutionTime.add(QuickExTime);
               System.out.println(Arrays.toString(A));
            
               System.out.println("Q" + QuickExTime);
            }
         
            if (item == "MergeSort") {
               //Copies values from array G to array A.
               for (int k = 0; k < n; k++) {
                  A[k] = G[k];
               }   
            
            //Start timing MergeSort
               long startTime = System.nanoTime();
               System.out.println(Arrays.toString(A));
            //Sorts array A. 
               MergeSort(A, 0, n - 1);
            //End timing.
               long endTime = System.nanoTime();
            
               long MergeExTime = endTime - startTime;        
               //time.add(MergeTotalTime);
               MergeExecutionTime.add(MergeExTime);
               System.out.println(Arrays.toString(A));
               System.out.println("M" + MergeExTime);     
            } 
         }  
         size.add((long)n);
         counter++;
       
      }
   
      File F = new File("Times.txt");
      try {
         PrintWriter printWriter = new PrintWriter(F);
         printWriter.println("A, B, C, D");
         for (int i = 0; i < counter; i++) {
            printWriter.println(size.get(i) + ", " + MergeExecutionTime.get(i) + ", "
                + QuickExecutionTime.get(i) + ", " + InsertExecutionTime.get(i));
         }
         printWriter.close();
      }
      catch (FileNotFoundException ex) {
         System.out.println("File not found.");
      }
   }
}
