import java.util.ArrayList;
import java.util.List;

class AVLTree {

    private Node root;

    // Method untuk menambahkan node ke dalam AVL Tree
    public void insert(int key) {
        root = insertNode(root, key);
    }

    // Method rekursif yang digunakan untuk memasukkan sebuah node baru ke dalam pohon
    private Node insertNode(Node current, int key) {

        if (current == null)
            return new Node(key);

        else if (key < current.key) {
            current.left = insertNode(current.left, key);
            updateHeightAndBalanceFactor(current); // Perbarui tinggi dan faktor keseimbangan setelah penambahan
            return balance(current); // Lakukan operasi keseimbangan pada simpul saat ini
        }
        else if (key > current.key) {
            current.right = insertNode(current.right, key);
            updateHeightAndBalanceFactor(current); // Perbarui tinggi dan faktor keseimbangan setelah penambahan
            return balance(current); // Lakukan operasi keseimbangan pada simpul saat ini
        }
        /* Updating the balance factor of every node and checking for imbalances */

        updateHeightAndBalanceFactor(current);

        int bf = getHeight(current);

        if (bf > 1 && key < current.left.key) {
            return rightRotate(current);

        } else if (bf < -1 && key > current.right.key) {
            return leftRotate(current);

        } else if (bf > 1 && key > current.left.key) {
            current.left = leftRotate(current.left);
            return rightRotate(current);

        } else if (bf < -1 && key < current.right.key) {
            current.right = rightRotate(current.right);
            return leftRotate(current);

        }

        /* Return unchanged node pointer */
        return current;

    }

    private Node balance(Node node) {
        // Check the balance factor of the node
        int balanceFactor = getBalanceFactor(node);

        // Left-Left case
        if (balanceFactor > 1 && getBalanceFactor(node.left) >= 0) {
            return rightRotate(node);
        }

        // Left-Right case
        if (balanceFactor > 1 && getBalanceFactor(node.left) < 0) {
            node.left = leftRotate(node.left);
            return rightRotate(node);
        }

        // Right-Right case
        if (balanceFactor < -1 && getBalanceFactor(node.right) <= 0) {
            return leftRotate(node);
        }

        // Right-Left case
        if (balanceFactor < -1 && getBalanceFactor(node.right) > 0) {
            node.right = rightRotate(node.right);
            return leftRotate(node);
        }

        return node;
    }

    private int getBalanceFactor(Node node) {
        if (node == null) {
            return 0;
        }
        return getHeight(node.left) - getHeight(node.right);
    }



    /*
     * Method ini melakukan rotasi pada subtree apabila terdapat ketidakseimbangan ketinggian subpohon kiri dan kanan.
     */

    private Node rotate(Node y, Node x, Node t) {
        y.left = t.right;
        x.right = t.left;
        t.left = x;
        t.right = y;

        /* Update the heights */

        updateHeightAndBalanceFactor(y);
        updateHeightAndBalanceFactor(x);
        updateHeightAndBalanceFactor(t);

        return t;
    }

    private void updateHeightAndBalanceFactor(Node node) {
        int leftSubtreeHeight = getHeight(node.left);
        int rightSubtreeHeight = getHeight(node.right);

        // Updating height of current node
        node.height = 1 + Math.max(leftSubtreeHeight, rightSubtreeHeight);

        // Updating balance factor of current node
        int bf = leftSubtreeHeight - rightSubtreeHeight;
        node.balanceFactor = bf;

    }

    /*
     * Method untuk melakukan operasi rotasi ke kanan pada subtree.
     */

    private Node rightRotate(Node y) {
        Node x = y.left;
        Node t = x.right;
        y.left = t;
        x.right = y;

        // Perbarui tinggi dan faktor keseimbangan
        updateHeightAndBalanceFactor(y);
        updateHeightAndBalanceFactor(x);

        return x;
    }

    /*
     * Method untuk melakukan operasi rotasi ke kiri pada subtree.
     */

    private Node leftRotate(Node x) {
        Node y = x.right;
        Node t = y.left;
        x.right = t;
        y.left = x;

        // Perbarui tinggi dan faktor keseimbangan
        updateHeightAndBalanceFactor(x);
        updateHeightAndBalanceFactor(y);

        return y;
    }

    /*
     * Metode rekursif yang digunakan untuk menghitung ketinggian suatu subpohon dalam AVL tree.
     */

    private int getHeight(Node root) {
        if (root == null)
            return 0;

        else {
            int left = getHeight(root.left);
            int right = getHeight(root.right);

            if (left > right)
                return (left + 1);

            else
                return(right + 1);

        }
    }


    /*
     * Metode rekursif yang digunakan untuk mendapatkan semua nilai dari sebuah AVL Tree dalam bentuk sorted array menggunakan traversal inorder.
     */

    public List<Integer> getSortedArray() {

        List<Integer> sortedArray = new ArrayList<>();
        inorderTraversal(root, sortedArray);

        return sortedArray;
    }

    private void inorderTraversal(Node current, List<Integer> result) {
        if (current != null) {
            inorderTraversal(current.left, result);
            result.add(current.key);
            inorderTraversal(current.right, result);
        }
    }


}
