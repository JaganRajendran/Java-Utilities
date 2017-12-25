package com.sort;

import java.util.Arrays;

public abstract class SortTechniques {
	
	private int swapCount;
	protected int comparisonsCount;
	protected int loopCount;
	public abstract void sort();
	protected int[] arr;
	public SortTechniques(int[] arr)
	{
		this.arr=arr;
	}
	public String calcTimeComplexity()
	{
		int nsquare=(arr.length * (arr.length -1))/2;
		int n=arr.length;
		if(comparisonsCount==nsquare || swapCount == nsquare)
			return "O(N^2)";
		else if(comparisonsCount<=n && swapCount<=n)
			return "O(N)";
		else
			return "O(NLogN)";
			
	}
	public void swap(int arr[], int i, int j)
	{
		int temp;
		temp=arr[i];
		arr[i]=arr[j];
		arr[j]=temp;
		swapCount++;
	}
	public int getSwapCount() {
		return swapCount;
	}
	public void setSwapCount(int swapCount) {
		this.swapCount = swapCount;
	}
	public int getComparisonsCount() {
		return comparisonsCount;
	}
	public void setComparisonsCount(int comparisonsCount) {
		this.comparisonsCount = comparisonsCount;
	}
	public int getLoopCount() {
		return loopCount;
	}
	public void setLoopCount(int loopCount) {
		this.loopCount = loopCount;
	}

}
/**
 Looks for the smallest element in each pass from the starting position to ending position
 and replaces with the smallest element found.
 only one swap will happen for each pass. i.e smallest element found is swapped with the current position.
 Time complexity - O(N^2)
 Number of comparisons - (M*(M-1))/2 Where M=N-1
 Number of swaps possible = N-1 
 */
class SelectionSort extends SortTechniques{

	public SelectionSort(int[] arr) {
		super(arr);
		// TODO Auto-generated constructor stub
	}
	@Override
	public void sort() {
		
		for(int i=0;i<arr.length;i++)
		{
			int smallElem=i;
			for(int j=i+1;j<arr.length;j++)
			{
				if(arr[smallElem]>arr[j])
					smallElem=j;
				comparisonsCount++;
				loopCount++;
			}
			if(i!=arr.length-1)
			swap(arr,smallElem,i);
		}
	}
	@Override
	public String toString() {
		return "SelectionSort []";
	}
	
}
/**
 * Looks for the largest element in each pass and bubbles to the last. It is reverse of selection but not exactly.
Time complexity - O(N^2)
Number of comparisons - (M*(M-1))/2 Where M=N-1
Number of swaps possible = Number of comparisons.
*/
class BubbleSort extends SortTechniques{

	public BubbleSort(int[] arr) {
		super(arr);
		// TODO Auto-generated constructor stub
	}

	@Override
	public void sort() {
		for(int i=0;i<arr.length;i++)
		{
			for(int j=0;j<(arr.length-i-1);j++)
			{
				if(arr[j]>arr[j+1])
				{
					swap(arr, j, j+1);
				}
				comparisonsCount++;
				loopCount++;
			}
		}
		
	}

	@Override
	public String toString() {
		return "BubbleSort []";
	}
	
}

/**
 * Compares the first element of the array with the successive position and swaps if the first element is greater.
Time complexity - O(N^2) (because of the swaps)
It takes 0(N^2) to sort if the element are in the reverse order and O(N) if the elements are already in order.
Number of comparisons - N-1
Number of swaps possible = (M*(M-1))/2 Where M=N-1
*/
class InsertionSort extends SortTechniques{

	public InsertionSort(int[] arr) {
		super(arr);
		// TODO Auto-generated constructor stub
	}
	@Override
	public void sort() {
		/*3,4,1,2,5*/
		for(int i=1;i<arr.length;i++)
		{
			int k=0;
			while(k<i)
			{
			int smallElem=k;
			loopCount++;
			if(arr[smallElem]>arr[i])
			{
				for(int j=i;j>k;j--)
				{
					swap(arr,j,j-1);
					loopCount++;
				}
				comparisonsCount++;
				break;
			}
			else
				loopCount++;
			k++;
			}
		}

	}
	@Override
	public String toString() {
		return "InsertionSort []";
	}
	
}

/**
 * Divides the arrays into two halves recursively until it becomes a single element to compare
 * Then it merges the arrays recursively to get the sorted array.
*/
class MergeSort extends SortTechniques{

	public MergeSort(int[] arr) {
		super(arr);
	}
	@Override
	public void sort() {
		divide(arr);
	}
	public void divide(int[] arr)
	{
		int n=arr.length;
		if(n<2) return;
		int mid=arr.length/2;
		int[] left=Arrays.copyOfRange(arr, 0,n/2);
		int[] right=Arrays.copyOfRange(arr,n/2,n);
		divide(left);
		divide(right);
		merge(left,right,arr);
	}
	public void merge(int[] left, int[] right , int[] dest)
	{
		int i=0,j=0,k=0;
		while(i<left.length && j<right.length)
		{
			if(left[i]<right[j])
				dest[k++]=left[i++]; 
			else
				dest[k++]=right[j++];
			comparisonsCount++;
		}
		while(i<left.length)
			dest[k++]=left[i++];
		while(j<right.length)
			dest[k++]=right[j++];
		
	}
	@Override
	public String toString() {
		return "MergeSort []";
	}
	
}
/**
 * It picks the last element as pivot and then moves all the element which are less than pivot to left side and 
 * all the elements which are greater than pivot to right side.
*/
class QuickSort extends SortTechniques{

	public QuickSort(int[] arr) {
		super(arr);
		// TODO Auto-generated constructor stub
	}
	@Override
	public void sort() {
		sort(arr,0,arr.length-1);
	}
	public void sort(int[] arr, int from, int to)
	{
		if(from<to)
		{
		int pindex=partition(arr,from,to);
		sort(arr,0,pindex-1);
		sort(arr,pindex+1,to);
		}
	}
	public int partition(int[] arr, int from , int to)
	{
		int pivot=arr[to];
		int partitionIndex=from;
		for(int i=from;i<to;i++)
		{
			if(arr[i]<pivot)
			{
				swap(arr,i,partitionIndex);
				partitionIndex++;
			}
			comparisonsCount++;
		}
		swap(arr,to,partitionIndex);
		return partitionIndex;
	}
	
	@Override
	public String toString() {
		return "QuickSort []";
	}
	
}
