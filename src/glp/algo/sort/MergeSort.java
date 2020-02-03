package glp.algo.sort;
import java.util.ArrayList;
import java.util.List;

public class MergeSort {

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

			// Either i or j will cross its limit first - Condition 1 or
			// Condition 2.

			// When Condition 1 or Condition 2 happens, we don't want to check
			// for Condition 3 or Condition 4 - Otherwise we will be comparing
			// elements outside the intended bounds.
			// So, we just put whatever is remaining into aux array.

			// Conditions have to be in following order and everything has to be
			// in "else if".

			// Between Condition 1 and Condition 2 - We don't want to execute
			// one when the other happens.
			// See what happens when you use "if + if" instead of "if + elseif"
			// on Condition 1 and Condition 2.
			// For example, if "i" crosses first, j will still be within its
			// limit but "j" CAN/WILL be pointing to the last element at some
			// point after "i" crossed.
			// Now after Condition 1 "j" will be incremented and "j" will also
			// cross its limit but we will proceed to Condition 2 and process
			// the element at "i" (which already crossed) (+ also "k" would have
			// crossed its limit) thinking "j" crossed first.
			// So either we can check if "i" is in the limit (mid) OR "k" is in
			// the limit (hi).

			// Condition 1
			if (i > mid) {
				aux.set(k, arr.get(j));
				j++;
				k++;
			}

			// Condition 2
			else if (j > hi) {
				aux.set(k, arr.get(i));
				i++;
				k++;
			}

			// Condition 3
			else if (arr.get(i) <= arr.get(j)) {
				aux.set(k, arr.get(i));
				i++;
				k++;
			}

			// Condition 4
			else { // arr[i] > arr[j]
				aux.set(k, arr.get(j));
				j++;
				k++;
			}

		}

		// Copy from aux to arr.

		// We are making comparisons against original array and place the
		// elements in sorted order in aux array.

		// So we have to move the sorted elements from aux to original arr. If
		// not, when we compare and merge again, the elements will be out of
		// order.

		// For example : aux : 5,4,3,2,1 , arr : 5,4,3,2,1
		// 1st iteration - aux : 4,5,3,2,1 , arr : 5,4,3,2,1 - not copied.
		// (so 4,5 which is sorted is not reflected in the original array. It is
		// still 5,4. So it breaks the property of mergesort - left most of
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
