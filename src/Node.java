class Node {
    int key, height, balanceFactor;
    Node left, right;

    public Node(int key) {
        this.key = key;
        height = 1;
        balanceFactor = 0;
        this.left = null;
        this.right =null;
    }
}

