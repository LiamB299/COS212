// Name:
// Student number:
import java.util.Arrays;

public class Sort
{
	
	////// Implement the functions below this line //////
	
	/********** MERGE **********/
	public static <T extends Comparable<? super T>> void mergesort(T[] data, int first, int last, boolean debug)
	{
            if(first<last) {
                    int middle = (first+last)/2;
                    mergesort(data, first, middle, debug);
                    mergesort(data, middle+1, last, debug);
                    merge(data, first, last, debug);
                }
	}
     
	private static <T extends Comparable<? super T>> void merge(T[] data, int first, int last, boolean debug)
	{
            int middle = (first + last)/2;
            int l = middle - first +1;
            int r = last - middle;
            
            T left[]= (T[]) new Comparable[l];
            T right[] = (T[]) new Comparable[r];
            
            for(int i=0; i<l; ++i) {
                left[i] = data[first+i];
            }
            
            for(int j=0; j<r; ++j) {
                right[j] = data[middle+1+j];
            }
            //===================================
            int i=0;
            int j=0;
            int k = first;
            while((i<l) && (j<r)) {
                if(left[i].compareTo(right[j]) <= 0) {
                    data[k] = left[i];
                    i++;
                }
                else {
                    data[k] = right[j];
                    j++;
                }
                k++;
            }
            
            while(i<l) {
                data[k] = left[i];
                i++;
                k++;
            }
            
            while(j<r) {
                data[k] = right[j];
                j++;
                k++;
            }
            
            if (debug)
		System.out.println(Arrays.toString(data));
            
	}
     
	/********** COUNTING **********/
	public static void countingsort(int[] data, boolean debug)
	{

            int q, biggest = data[0], n = data.length;
            int[] tmp = new int[n];
            for(q=1; q<n; q++)
                if(biggest < data[q])
                    biggest = data[q];
            int[] count = new int[biggest+1];
            
            for(q=0; q<=biggest; q++) {
                count[q]=0;
            }
            
            for(q=0; q<n; q++)
                count[data[q]]++;
            
            for(q=1; q<=biggest; q++)
                count[q] = count[q-1] + count[q];
            for(q=n-1; q>=0; q--) {
                tmp[count[data[q]]-1] = data[q];
                count[data[q]]--;
            }
            for(q=0; q<n; q++)
                data[q] = tmp[q];

		//DO NOT MOVE OR REMOVE!
		if (debug)
			System.out.println(Arrays.toString(data));
	}

}