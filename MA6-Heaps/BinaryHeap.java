/*
 *  Assignment: Implementing Percolates
 *
 *  [this file]: Main Heap class
 *   Heaps Microassignment
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
import java.lang.IndexOutOfBoundsException;

public class BinaryHeap<AnyType extends Comparable<? super AnyType>>
{
    private ArrayList<AnyType> data = new ArrayList<>();
    private Integer currentSize = 0;
    private static final int DEFAULT_CAPACITY = 10;

    // Default constructor reserves 10 items worth of space
    public BinaryHeap () {
        this(DEFAULT_CAPACITY);
    }

    public BinaryHeap (int reserve_size) {
        ensureHeapSize(reserve_size);
        currentSize = 0;
    }

    // Array initializer-based constructor
    //  Added every item in items to heap
    public BinaryHeap( AnyType [ ] items ) {
        this();             // Call default constructor
        for (AnyType item : items) {
            insert(item);
        }
    }

    // Quick hack of a print out of the heap's data
    public void dump() {
        for (AnyType val : data) {
            System.out.print(val + ", ");
        }
    }

    // Returns a SHALLOW COPY of the data arraylist
    public ArrayList<AnyType> getData() {
        ArrayList<AnyType> newData = new ArrayList<>();
        for (AnyType item : data) {
            if( item != null) {
                newData.add(item);
            }
        }
        return newData;
    }

    // Expands the heap's internal sized ArrayList
    public void ensureHeapSize(int newSize) {
        data.ensureCapacity(newSize);    // Prevent excessive copying while we're adding
        while (data.size() < newSize) {
            data.add(null);
        }
    }

    public AnyType findMin() {
        if( isEmpty( ) ) {
            throw new IndexOutOfBoundsException( );
        }
        return data.get(0);
    }

    public boolean isEmpty() {
        return (currentSize <= 0);
    }

    public void makeEmpty() {
        while( !isEmpty() ) {
            deleteMin();
        }
    }

    public int size() {
        return currentSize;
    }

    public AnyType deleteMin() {
        if( isEmpty( ) ) {
            throw new IndexOutOfBoundsException( );
        }

        AnyType minItem = findMin( );
        data.set(0, data.get(--currentSize));
        data.set(currentSize, null);
        percolateDown( 0 );

        return minItem;
    }

    // ********************************************************************* //
    //  Microassignment Section: Implement percolations
    // ********************************************************************* //
    public void insert(AnyType x) {
        data.add(currentSize, x);
        percolateUp(currentSize++);
    }

    private void percolateUp(int hole) {
        if (hole > 0) {
            AnyType parent = data.get((hole - 1) / 2);
            AnyType child = data.get(hole);
            if (parent.compareTo(child) > 0) {
                data.set(hole, parent);
                data.set ((hole - 1) / 2, child);
                percolateUp((hole - 1) / 2);
            }
        }
    }

    private void percolateDown( int hole ) {
        if ((hole * 2) + 1 <= currentSize - 1) {
            AnyType parent = data.get(hole);
            AnyType child1 = data.get((hole * 2) + 1);
            AnyType child2 = data.get((hole * 2) + 2);
            if ((hole * 2) + 2 <= currentSize - 1 && child2.compareTo(child1) < 0) {
                if (parent.compareTo(child2) > 0) {
                    data.set((hole * 2) + 2, parent);
                    data.set(hole, child2);
                    percolateDown((hole * 2) + 2);
                }
            } else {
                if (parent.compareTo(child1) > 0) {
                    data.set((hole * 2) + 1, parent);
                    data.set(hole, child1);
                    percolateDown((hole * 2) + 1);
                }
            }
        }
    }
}
