# Assignment 4
# Motivation
Reducing data is a common task. A model case might be data from a mobile phone operator, recording the phone calls in the network. Based on this data, it is possible to determine the set of (and the number of) active phone numbers, i.e. those that were actually used in the given timespan. Such information might be valuable, but it also might be difficult to extract from a large set of data.

In the model scenario, we have an array of positive integers in the range 0 - 999 999, where each item represents a phonecall made from that number. Our objective is to create an array that contains each used number at most once, i.e. to remove duplicates in the input data.

# Task 1 (debugging) (up to 2 points)
The following source code introduces three possible approaches to solving the problem. Try to understand their function and suggest a way of testing, whether or not they work correctly. Fix any bugs you find.

```
import java.util.Random;

public class RemoveDuplicates {
	
	/**
	 * Removes an item at index i from an array
	 * @param input array of data
	 * @param index index if the item to be removed
	 * @return new array with one item removed
	 */
	public static int[] removeItem(int[] data, int index){
		// resulting array will be smaller by 1 element
		int[] result = new int[data.length-1];
		// we copy all elements up to index i
		for (int i = 0;i<index;i++)
			result[i] = data[i];
		// skip i-th element, copy the rest
		for (int i = index + 1;i<data.length;i++)
			result[i-1] = data[i];
		return result;
	}
	
	/**
	 * Traverses the array and removes items using the removeItem mehtod
	 * @param data input array
	 * @return data with removed duplicates
	 */
	static int[] removeDuplicates1(int[] data){
		int[] result = data;
		for (int i = 0;i<result.length;i++){
			for (int j = i+1;j<result.length;j++){
				if (result[j] == result[i])
					result = removeItem(result, j);
			}
		}
		return result;
	}
	
	/**
	 * Traverses the array and removes all duplicates of a single element at the same time
	 * @param data input array
	 * @return data with duplicates removed
	 */
	static int[] removeDuplicates2(int[] data){
		int[] result = data;
		for(int i = 0;i<result.length;i++){
			// how many duplicates of result[i] are there?
			int count = 0; // number of duplicates
			for (int j = i+1;j<result.length;j++)
				if (result[j] == result[i])
					Count++;
			// if there is at least one duplicate, we remove it/them
			if (count>0){
				// the result will be by count shorter
				int[] newResult = new int[result.length-count];
				// up to element i we simply copy the elements
				for (int k = 0;k<=i;k++)
					newResult[k] = result[k];
				int index = i; // index in target array
				for (int k = i+1;k<result.length;k++){
					if (result[k]!=result[i]){
						// not a duplicate
						newResult[index] = result[k];
						index++;
					}
				}
				result = newResult;
			}
		}
		return result;
	}
	
	/**
	 * Uses an array of booleans that indicate, whether a number has been visited already or not
	 * @param data input array
	 * @return data with duplicates removed
	 */
	static int[] removeDuplicates3(int[] data){
		// first, find out how many unique numbers we have
		boolean[] encountered = new boolean[1000000];
		int count = 0; // count of unique numbers
		for (int i = 0;i<data.length;i++){
			if (!encountered[data[i]]){
				// newly found number
				encountered[data[i]] = true;
				count++;
			}
		}
		// in count we have the count of unique numbers
		// we use the array encountered once more in the same way
		encountered = new boolean[1000000];
		int[] result = new int[count];
		int index = 0;
		for (int i = 0;i<data.length;i++){
			if (!encountered[data[i]]){
				result[index] = data[i];
				encountered[data[i]] = true;
				index++;
			}
		}
		return result;
	}
	
	/**
	 * generates random numbers up to 100 000,
	 * thus simulating, that roughly 90% of numbers are "inactive"
	 * @param count number of requested numbers
	 * @return array of random numbers
	 */
	static int[] generateData(int count)
	{
		int[] result = new int[count];
		Random r = new Random();
		for (int i = 0;i<result.length;i++)
			result[i] = r.nextInt(100000);
		return result;
	}
	
	public static void main(String[] args)
	{
		int count = 30000;
		int[] data = generateData(count);
		
		int[] reducedData1 = removeDuplicates1(data);
		int[] reducedData2 = removeDuplicates2(data);	
		int[] reducedData3 = removeDuplicates3(data);
		System.out.println("All done.");
	}
}
```

# Task 2 (determining complexity)(up to 2 points)
Assume that RemoveDuplicates1 has been passed an array of length n containing no duplicates. Write down a function g(n) capturing the number of evaluations of the condition "if (result[j] == result[i])". Find out to which Theta complexity set this function belongs, perform proof. Present your solution to your tutor.
