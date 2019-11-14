/*
 *  Assignment: Implementing Quick, Merge, and Radix sort
 *
 *  [this file]: MergeSort Implementation
 *
 *  Contributors:
 *    Aaron S. Crandall <acrandal@wsu.edu>, 2019
 *
 *  Copyright:
 *   For academic use only under the Creative Commons
 *   Attribution-NonCommercial-NoDerivatives 4.0 International License
 *   http://creativecommons.org/licenses/by-nc-nd/4.0
 */

import java.util.ArrayList;

public class MergeSort<T extends Comparable<T>> extends Sorter<T> {
    MergeSort() {
        name = "MergeSort";
    }

  	public ArrayList<T> sort(SortStats stats, ArrayList<T> data) {
        int size = data.size();

        if (size > 1) {
            // sorts left half of data:
            ArrayList<T> left = new ArrayList<T>();
            for (int i = 0; i < size / 2; i++) {
                left.add(data.get(i));
            }
            left = sort(stats, left);

            // sorts right half of data:
            ArrayList<T> right = new ArrayList<T>();
            for (int i = size / 2; i < size; i++) {
                right.add(data.get(i));
            }
            right = sort(stats, right);

            // merges sorted left and right halves of data:
            data = new ArrayList<T>();

            while (!left.isEmpty() && !right.isEmpty()) {
                stats.comparisons++;
                stats.swaps++;
                if (left.get(0).compareTo(right.get(0)) < 0) {
                    data.add(left.remove(0));
                } else {
                    data.add(right.remove(0));
                }
            }

            // adds remaining elements if one list is empty and the other is not (no comparisons)
            while (!left.isEmpty()) {
                stats.swaps++;
                data.add(left.remove(0));
            }
            while (!right.isEmpty()) {
                stats.swaps++;
                data.add(right.remove(0));
            }

        }

		return data;
	}
}
