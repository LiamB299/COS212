// Name:
// Student number:
import java.util.Arrays;

public class Sort
{
	
	////// Implement the functions below this line //////
	
	/********** MERGE **********/
	public static <T extends Comparable<? super T>> void mergesort(T[] data, int first, int last, boolean debug)
	{

		// Your code here
		if (first < last)
		{
			int mid = (first + last)/2;
			mergesort(data, first, mid, debug);
			mergesort(data, mid + 1, last, debug);
			merge(data, first, last, debug);
			}
	}
     
	private static <T extends Comparable<? super T>> void merge(T[] data, int first, int last, boolean debug)
	{

		// Your code here
        
		//DO NOT MOVE OR REMOVE!
		
		
		int mid = (first + last)/2;
		int l = mid - first + 1;
		int r = last - mid;
		
		T [] LArr= (T[]) new Comparable [l];
		T [] RArr = (T[]) new Comparable [r];
		
		for (int i = 0; i < l; ++i)
		{
			LArr[i] = data[first + i];
			}
		for (int j = 0; j < r; ++j)
		{
			RArr[j] = data[mid + 1 + j];
			}
			
		int i = 0; 
		int j = 0;
		int k = first;
		
		while ((i < l) && (j < r))
		{
			if (LArr[i].compareTo(RArr[j]) <= 0)
			{
				data[k] = LArr[i];
				i++;
				}
			else
			{
				data[k] = RArr[j];
				j++;
				}
			k++;
			}
		while (i < l)
		{
			data[k] = LArr[i];
			i++;
			k++;
			}
		while (j < r)
		{
			data[k] = RArr[j];
			j++;
			k++;
			}
			
		if (debug)
			System.out.println(Arrays.toString(data));
	}
     
	/********** COUNTING **********/
	//reference: Adam Drozdek Data structures and algorithsms. p531-532
	public static void countingsort(int[] data, boolean debug)
	{

		// Your code here

		//DO NOT MOVE OR REMOVE!
		
		
		int i, largest = data[0], n = data.length;
		
		int [] tmp = new int[n];
		
		for (i = 1; i < n; i++)
		{
			if (largest < data[i])
			{
				largest = data[i];
				}
			}
		int [] count = new int [largest + 1];
		
		for (i = 0; i <= largest; i++)
		{
			count[i] = 0;
			}
		for (i = 0; i < n; i++)
		{
			count[data[i]]++;
			}
		for (i = 1; i <= largest; i++)
		{
			count[i] = count[i - 1] + count[i];
			}
		for (i = n - 1; i >= 0; i--)
		{
			tmp[count[data[i]] - 1] = data[i];
			count[data[i]]--;
			}
		for (i = 0; i < n; i++)
		{
			data[i] = tmp[i];
			}
			
		if (debug)
			System.out.println(Arrays.toString(data));
	}

}