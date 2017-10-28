package package6;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class MyTree {

    static class ITree <T extends Comparable<T>> {

        private class Node <T1 extends Comparable<T1>> {
            T1 value;
            Node<T1> left;
            Node<T1> right;

            public Node(T1 value, Node<T1> left, Node<T1> right) {
                this.value = value;
                this.left = left;
                this.right = right;
            }
        }

        private int size;
        private Node<T> root;

        public ITree() {
            this(0);
        }

        public ITree(int size) {
            this.size = size;
        }

        public boolean contains(T value) {
            return contains(root, value) != null;
        }

        public Node<T> contains(Node<T> node, T value) {
            if (node == null || node.value == value) {
                return node;
            }
            if (value.compareTo(node.value) < 0) {
                return contains(node.left, value);
            }
            else {
                return contains(node.right, value);
            }
        }

        public void add(T value) {
            root = add(root, value);
        }

        private Node<T> add(Node<T> node, T value) {
            if (node == null) {
                return new Node<>(value, null, null);
            }
            if (value.compareTo(node.value) < 0) {
                node.left = add(node.left, value);
            }
            else {
                node.right = add(node.right, value);
            }
            return node;
        }

        private Node<T> successor(Node<T> node) {
            Node<T> temp = node.right;
            while (temp.left != null) {
                temp = temp.left;
            }
            return temp;
        }

        public boolean remove(T value) {
            return remove(root, value) != null;
        }

        private Node<T> remove(Node<T> node, T value) {
            if (node == null) {
                return root;
            }
            int comp = value.compareTo(node.value);
            if  (comp < 0) {
                node = remove(node.left, value);
            }
            else if (comp > 0) {
                node = remove(node.right, value);
            }
            else if (node.left != null && node.right != null) {
                node.value = successor(node).value;
                node.right = remove(node.right, node.value);
            }
            else {
                if (node.left != null) {
                    node = node.left;
                }
                else {
                    node = node.right;
                }
            }
            return node;
        }

        public void traverse() {
            traverse(root);
        }

        private void traverse(Node<T> node) {
            if (node != null) {
                traverse(node.left);
                if (node.right == null && node.left == null) {
                    System.out.print(node.value + " ");
                }
                traverse(node.right);
            }
        }

        public int getSize() {
            return size;
        }

        public boolean isEmpty() {
            return size == 0;
        }
    }


    public static void main(String[] args) {
        int size = 0;
        String nums;
        ITree<Integer> tree = new ITree<>();
        try (BufferedReader in = new BufferedReader(new InputStreamReader(System.in))) {
            size = Integer.parseInt(in.readLine());
            nums = in.readLine();
        }
        catch (IOException e) {
            nums = "";
            e.printStackTrace();
        }
        StringTokenizer separatedNums = new StringTokenizer(nums, " ");
        for (int i = 0; i < size; i++) {
            tree.add(Integer.parseInt(separatedNums.nextToken()));
        }
        tree.traverse();
    }

}