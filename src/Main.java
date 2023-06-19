import java.util.List;

public class Main {
    public static void main(String[] args) {
        AVLTree tree = new AVLTree();

        // Menambahkan node ke dalam tree
        tree.insert(10);
        tree.insert(20);
        tree.insert(30);
        tree.insert(40);
        tree.insert(50);

        /* Mencetak semua nilai dari pohon dalam bentuk sorted array */
        List<Integer> sortedArray =tree.getSortedArray();

        System.out.println("Isi pohon dalam bentuk sorted array: ");

        for (int i : sortedArray) {
            System.out.print(i + " ");
        }
    }

}
