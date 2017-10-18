package main;

import java.util.ArrayList;

public class IHeap {

    public static class MyHeap<T extends Comparable<T>> {

        private class HeapElement {
            T value;

            HeapElement(T value) {
                this.value = value;
            }
        }

        ArrayList<T> array;

        MyHeap() {

        }

    }

}
