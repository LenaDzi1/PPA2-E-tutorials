/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 */

package com.mycompany.ppa2_lab5;
import java.util.Arrays;
import java.util.Random;


/////////////////////////// DEBUGGING TASK ////////////////////

interface ISortingAlgorithm {
	void sort(int[] data, int start, int end);	
	int comparesInLastSort();
}

class InsertSort implements ISortingAlgorithm {
		
	int compares= 0;

	boolean greaterThan(int[] data, int j, int v) {
		compares++;
		return data[j]>v;    /// but data [i] = v .... [29]   // CHANGED TO data[j]
	}
	
        @Override   //// override added
	public void sort(int[] data, int start, int end) {			
            compares = 0;
		for (int i = 1;i<data.length;i++) {
			int v = data[i];           //// second value
			int j = i-1;               //// first value
			while((j>=0)&&(greaterThan(data, j, v))) {  /// j at least second index   // changed to data[j]		
				data[j+1] = data[j];		/// moving the index so like data[i] = data[j]	
                              
				j--;                            // decrementing the j
			}		
			data[j+1] = v;   //////// id the data is less in index[j] and we didnt reach the beggining of arryay 
                        // we put our value at j+1 index ??
                        
                       
		}
	}	
	
        @Override   //// override added
	public int comparesInLastSort() {
		return compares;
	}
}


class QuickSort implements ISortingAlgorithm {
    
    @Override
    public void sort(int[] data, int start, int end) {
        
       if (end<=start) return;
      int i = split(data, start, end);
      sort(data, start, i-1);
        sort(data, i+1, end);
    
        // pviot index = i
        // partition = split
      
    }
  
 
    @Override
    public int comparesInLastSort() {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

     int split(int[] data, int left, int right){
           int pivot = data[right];
           while (true){
                while ((data[left]<pivot)&&(left<right))
                    left++;
                if (left<right){
                    data[right] = data[left];
                    right--;
                } else break;
                   
                while ((data[right]>pivot)&&(left<right))
                right--;
                    if (left<right){
                        data[left] = data[right];
                        left++;
                    } else break;
                }
               
            data[left] = pivot;
        return(left);
     }
    
    
}

public class SortingTest {
	public static void main(String[] args) {		
		ISortingAlgorithm algorithm = new InsertSort();
		
                
                
		if (testCorrectness(algorithm)) {		
			//testCounts(algorithm);
		}
                
                ISortingAlgorithm algorithm2 = new QuickSort();
                
                if(testCorrectness(algorithm2)){
                    System.out.println("Quicksort works ok!");
                }
				
	}

	private static void testCounts(ISortingAlgorithm algorithm) {
		int MIN_LENGTH = 100;
		int MAX_LENGTH = 100000;
		int TEST_COUNT = 100;
                
                int start = 0;
                int end = 0;
                
		for (int length = MIN_LENGTH;length<MAX_LENGTH;length*=2) {  // multiply length by 2 each time
			int minComp = Integer.MAX_VALUE;    
			int maxComp = 0;
			for (int test = 0;test<TEST_COUNT;test++) {
				int[] data = generateData(length);   /// generating big arrays
				algorithm.sort(data, start, end);                 /// sorting this
				if (algorithm.comparesInLastSort()>maxComp && maxComp <Integer.MAX_VALUE )   ///  > min?
                                    
					maxComp = algorithm.comparesInLastSort();   /// min = compares
                                
                              //  System.out.println("Max nr: " +algorithm.comparesInLastSort());
                                
                                
				if (algorithm.comparesInLastSort()<minComp && minComp>0 )   // < max
					minComp = algorithm.comparesInLastSort();	// max = compares
                                
                              //   System.out.println("Min nr: " +algorithm.comparesInLastSort());
                                
			}
			System.out.println("Length: " + length + ", Min:" + minComp + ", Max:" + maxComp);
		}		
	}

	private static boolean testCorrectness(ISortingAlgorithm algorithm) {
		//for (int i = 0;i<100;i++) {   // do we need this???  // changed
                
                
                
			int[] data = generateData(100);
			int[] dataCopy = data.clone();   
                       
                        int start = 0;
                        int end = data.length - 1;
                        
                        
			algorithm.sort(data, start, end);    //// sorting with the method
			Arrays.sort(dataCopy);    /// sorting with build function
                        
                        for(int j = 0;j<data.length;j++) {
                            System.out.print(data[j]+",");
                        }
                        System.out.println("");
                        
                        for(int j = 0;j<dataCopy.length;j++) {
                            System.out.print(dataCopy[j]+",");
                        }
                        System.out.println("");
                        
			for(int j = 0;j<data.length;j++) {      /////// put everything in one for loop 
//                                System.out.print(data[j]+",");
//                                System.out.print(dataCopy[j]+",");
				if (data[j]!=dataCopy[j]) {    /// changed for dataCopy[j]
					System.out.println("Algorithm failed, terminating.");
					return false;
				}
			}			
		//}
		System.out.println("Algorithm passed test, continuing.");
		return true;
	}

	private static int[] generateData(int c) {   /// seems ok
		int[] result = new int[c];
		Random rnd = new Random();
		for (int i = 0;i<c;i++)
			result[i] = rnd.nextInt(c);
		return result;
	}	
}