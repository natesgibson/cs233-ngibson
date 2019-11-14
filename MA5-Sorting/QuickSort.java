/*
 *  Assignment: Implementing Quick, Merge, and Radix sort
 *
 *  [this file]: QuickSort Implementation
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

public class QuickSort<T extends Comparable<T>> extends Sorter<T> {
    QuickSort() {
        name = "QuickSort";
    }

  	public ArrayList<T> sort(SortStats stats, ArrayList<T> data) {
        if (data.size() > 1) {
            int pivotIndex = getPivot(data);
            T pivotValue = data.remove(pivotIndex);

            ArrayList<T> less = new ArrayList<T>();
            ArrayList<T> greater = new ArrayList<T>(); // technically >=

            for (T curr : data) {
                // STATS COMPARISONS++
                // STATS SWAPS++
                if (pivotValue.compareTo(curr) > 0) {
                    less.add(curr);
                } else {
                    greater.add(curr);
                }
            }

            data = new ArrayList<T>();
            data.addAll(sort(stats, less));
            data.add(pivotValue);
            data.addAll(sort(stats, greater));
        }

        return data;
	}

    // Returns the index of pivot = Median(first, mid, last).
    // Returns 0 if less than 3 elements.
    private int getPivot(ArrayList<T> data) {
        int size = data.size();
        if (size < 3){
            return 0;
        } else {
            T first = data.get(0);
            T mid = data.get(size / 2);
            T last = data.get(size - 1);

            // check pivot is first
            // STATS COMPARISONS += 4
            if ((first.compareTo(mid) > 0 && first.compareTo(last) < 0) ||
                (first.compareTo(mid) < 0 && first.compareTo(last) > 0)) {
                return 0;
            }

            // check pivot is middle
            // STATS COMPARISONS += 4
            if ((mid.compareTo(first) > 0 && mid.compareTo(last) < 0) ||
                (mid.compareTo(first) < 0 && mid.compareTo(last) > 0)) {
                return size / 2;
            }

            //pivot is last
            return size - 1;
        }
    }
}
