# Assignment 5
# Motivation
Many tasks, including some of those solved as assignments in PPA2E (interval subdivision search), assume sorted sequence of elements as input. In practice, however, data are often created in an arbitrary order, and only get sorted during processing. The objective of this assignment is to demonstrate, that employing an efficient or inefficient sorting alorithm has a profound effect on the speed and reliability of algorithms.

# Task 1 (debugging) (up to 2 points)
The following piece of code represent the InsertSort algorithm, testing of the correctness and measuring the number of comparisons, however, it does not work correctly. For measuring the number of comparisons, we will only consider the comparisons involving the elements of the input data, i.e. excluding for example the comparisons used to control the for- cycles. Find and fix the errors.

```
import java.util.Arrays;
import java.util.Random;

interface ISortingAlgorithm {
	void sort(int[] data);	
	int comparesInLastSort();
}

class InsertSort implements ISortingAlgorithm {
		
	int compares= 0;

	boolean greaterThan(int[] data, int i, int v) {
		compares++;
		return data[i]>v;
	}
	
	public void sort(int[] data) {				
		for (int i = 1;i<data.length;i++) {
			int v = data[i];
			int j = i-1;
			while((j>0)&&(greaterThan(data, i, v))) {		
				data[j+1] = data[j];				
				j--;
			}		
			data[j+1] = v;
		}
	}	
	
	public int comparesInLastSort() {
		return compares;
	}
}

public class SortingTest {
	public static void main(String[] args) {		
		ISortingAlgorithm algorithm = new InsertSort();
		
		if (testCorrectness(algorithm)) {		
			testCounts(algorithm);
		}
				
	}

	private static void testCounts(ISortingAlgorithm algorithm) {
		int MIN_LENGTH = 100;
		int MAX_LENGTH = 100000;
		int TEST_COUNT = 100;
		for (int length = MIN_LENGTH;length<MAX_LENGTH;length*=2) {
			int minComp = Integer.MAX_VALUE;
			int maxComp = 0;
			for (int test = 0;test<TEST_COUNT;test++) {
				int[] data = generateData(length);
				algorithm.sort(data);
				if (algorithm.comparesInLastSort()>maxComp)
					maxComp = algorithm.comparesInLastSort();
				if (algorithm.comparesInLastSort()<minComp)
					minComp = algorithm.comparesInLastSort();				
			}
			System.out.println("Length: " + length + ", Min:" + minComp + ", Max:" + maxComp);
		}		
	}

	private static boolean testCorrectness(ISortingAlgorithm algorithm) {
		for (int i = 0;i<100;i++) {
			int[] data = generateData(100);
			int[] dataCopy = data.clone();
			algorithm.sort(data);
			Arrays.sort(dataCopy);
			for(int j = 0;j<data.length;j++) {
				if (data[j]!=dataCopy[i]) {
					System.out.println("Algorithm failed, terminating.");
					return false;
				}
			}			
		}
		System.out.println("Algorithm passed test, continuing.");
		return true;
	}

	private static int[] generateData(int c) {
		int[] result = new int[c];
		Random rnd = new Random();
		for (int i = 0;i<c;i++)
			result[i] = rnd.nextInt(c);
		return result;
	}	
}
```

# Task 2 (determining complexity)(up to 2 points)
* Add class QuickSort, which implements the ISortingAlgorithm interface using the QuickSort algorithm. You may (this time) use/adapt the source code presented in the lecture slides.
* Expand the output by reporting the number of comparisons in the case when the input sequence is already sorted.
* If a problem occurs during testing, try to reason why it occured and find a way to continue the testing.
