package glp.algo.sort;
import java.util.ArrayList;
import java.util.List;

public class MergeSortVariation1 {

	public static void main(String[] args) {

		List<Integer> arr = new ArrayList<Integer>();
		// arr.add(5);
		// arr.add(4);
		// arr.add(3);
		// arr.add(2);
		// arr.add(1);

		arr.add(1);
		arr.add(2);
		arr.add(3);
		arr.add(4);
		arr.add(5);

		arr.add(5);
		arr.add(4);
		arr.add(3);
		arr.add(1);
		arr.add(2);

		arr.add(3);
		arr.add(4);
		arr.add(5);
		arr.add(2);
		arr.add(1);

		List<Integer> aux = new ArrayList<Integer>();
		aux.addAll(arr);

		sort(arr, aux, 0, arr.size() - 1);

		System.out.println("Sorted Array : " + arr);
		System.out.println("Sorted Array Aux : " + aux);
	}

	public static void merge(List<Integer> arr, List<Integer> aux, int lo, int mid, int hi) {

		System.out.println("Inside Merge");
		System.out.println("arr: " + arr);
		System.out.println("aux: " + aux);
		System.out.println("lo: " + lo);
		System.out.println("mid:" + mid);
		System.out.println("hi:" + hi);

		int i = lo;
		int j = mid + 1;
		int k = lo;

		while (true) {

			// break when you have placed all the elements in order in the aux
			// array
			if (k > hi) {
				break;
			}

			if (i > mid || j > hi) {

				// Condition 1
				if (i > mid) {
					aux.set(k, arr.get(j));
					j++;
					k++;
				}

				// Condition 2
				if (j > hi && i <= mid) { // (OR) if (j > hi && k <= hi)
					aux.set(k, arr.get(i));
					i++;
					k++;
				}

				// if order of Condition 1 and Condition 2 is interchanged, then
				// if (i > mid && j <= hi) // (OR) if (i > mid && k <= hi)
				// The Condition that appears second will have this issue and so
				// this additional check has to be performed if you want to do
				// with only "if" and NO "if else".

			}

			else {
				// Condition 3
				if (arr.get(i) <= arr.get(j)) {
					aux.set(k, arr.get(i));
					i++;
					k++;
				}

				// Condition 4
				if (arr.get(i) > arr.get(j)) { // or use "else" here
					aux.set(k, arr.get(j));
					j++;
					k++;
				}

			}

		}

		// Copy from aux to arr.

		// We are making comparisons against original array and place the
		// elements in sorted order in aux array.

		// So we have to move the sorted elements from aux to original arr. If
		// not, when we compare again and merge the elements will be out of
		// order.

		// For example : arr : 5,4,3,2,1 , aux : 5,4,3,2,1
		// 1st iteration - aux : 4,5,3,2,1 , arr : 5,4,3,2,1 - not copied. (so
		// 4,5 which is sorted is not reflected in the original array. It is
		// still 5,4. So it breaks the property of mergesort where left most of
		// left array and left most of right array should be minimum in the
		// respective arrays.)

		// 2nd iteration - aux : 3,5,4,2,1 , arr : 5,4,3,2,1 - instead of
		// 4,5,3,2,1

		for (int copy = lo; copy <= hi;) {
			arr.set(copy, aux.get(copy));
			copy++;
		}

	}

	public static void sort(List<Integer> arr, List<Integer> aux, int lo, int hi) {

		System.out.println("lo:" + lo);
		System.out.println("hi:" + hi);

		if (hi <= lo)
			return;

		int mid = (hi - lo) / 2 + lo;

		sort(arr, aux, lo, mid);
		sort(arr, aux, mid + 1, hi);
		System.out.println("calling merge lo: " + lo + " mid: " + mid + " hi: " + hi);

		merge(arr, aux, lo, mid, hi);

	}

}
