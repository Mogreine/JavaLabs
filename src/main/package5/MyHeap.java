package main.package5;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;

public class MyHeap<T extends Comparable<T>> {

    private ArrayList<T> array;

    MyHeap() {
        array = new ArrayList<>();
    }

    MyHeap(T[] arr) {
        array = new ArrayList<>(Arrays.asList(arr));
        heapify();
    }

    public void heapify() {
        for (int i = array.size() - 1; i >= 0; i--) {
            top(i);
        }
    }

    public void down(int index) {
        for (int i = index, bottomNode = i * 2 + 1; bottomNode < array.size() - 1; ) {
            int indMax;
            if (bottomNode + 1 < array.size() - 1) {
                indMax = array.get(bottomNode).compareTo(array.get(bottomNode + 1)) > 0 ? bottomNode : bottomNode + 1;
            }
            else {
                indMax = bottomNode;
            }
            if (array.get(indMax).compareTo(array.get(index)) > 0) {
                Collections.swap(array, i, indMax);
                i = indMax;
            }
        }
    }

    public void top(int index) {
        for (int i = index, topNode = (i - 1) / 2; topNode >= 0 && i != 0; i = topNode) {
            if (array.get(topNode).compareTo(array.get(index)) < 0) {
                Collections.swap(array, i, topNode);
                down(i);
            }
        }
    }
}
