package com.sort;

public class SortTester {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		int bestArr[]={1,2,3,4,5};
		int worstArr[]={10,9,8,7,6};
		int normalArr[]={3,4,1,2,5};
		int arrayChosen[]=normalArr;
		SortTechniques sorting=new MergeSort(arrayChosen);
		sorting.sort();
		System.out.println(sorting);
		System.out.println("No of swaps-->"+sorting.getSwapCount());
		System.out.println("No of comparisons-->"+sorting.getComparisonsCount());
		System.out.println("Time complexity-->"+sorting.calcTimeComplexity());
		System.out.println("Loop count-->"+sorting.getLoopCount());
		for (int i : arrayChosen) {
			System.out.print(i+" ");
		}
	}

}
