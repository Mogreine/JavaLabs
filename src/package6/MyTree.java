package package6;

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

        int size;
        Node<T> root;

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
            add(root, value);
        }

        public void add(Node<T> node, T value) {
            if (node == null) {
                node = new Node<>(value, null, null);
            }
            else if (value.compareTo(node.value) < 0) {
                add(node.left, value);
            }
            else {
                add(node.right, value);
            }
        }

        public boolean remove(T value) {

        }
    }

}
