import java.util.PriorityQueue;
import java.util.Comparator;

public class HuffmanBtree {

    // Class representing a binary node (Bnode) for Huffman Tree
    public static class Bnode {
        int freq;  // Frequency of the character
        char c;    // Character (for leaf nodes)
        Bnode esq, dir;  // Left and right child

        // Constructor for leaf nodes
        public Bnode(char c, int freq) {
            this.c = c;
            this.freq = freq;
            this.esq = null;
            this.dir = null;
        }

        // Constructor for internal nodes (no character)
        public Bnode(int freq, Bnode esq, Bnode dir) {
            this.c = '-';  // Internal nodes don't have characters
            this.freq = freq;
            this.esq = esq;
            this.dir = dir;
        }

        // Recursively print the Huffman codes
        public void printCode(String s) {
            // If it's a leaf node (character node), print the character and its code
            if (esq == null && dir == null && Character.isLetter(c)) {
                System.out.println(c + "   |  " + s);
                return;
            }
            // Traverse the left child with an appended '0'
            if (esq != null) {
                esq.printCode(s + "0");
            }
            // Traverse the right child with an appended '1'
            if (dir != null) {
                dir.printCode(s + "1");
            }
        }
    }

    // Comparator to order the PriorityQueue based on frequency
    static class ImplementComparator implements Comparator<Bnode> {
        public int compare(Bnode x, Bnode y) {
            return x.freq - y.freq;
        }
    }

    // Main class for Huffman coding using Btree
    public static class Btree {
        Bnode root;

        public Btree() {
            root = null;
        }

        // Set the root of the Huffman tree
        public void setRoot(Bnode node) {
            this.root = node;
        }

        // Print the Huffman codes
        public void printCode() {
            if (root != null) {
                root.printCode("");
            }
        }
    }

    // Main method
    public static void main(String[] args) {
        int n = 4;
        char[] charArray = { 'A', 'B', 'C', 'D' };
        int[] charfreq = { 5, 1, 6, 3 };

        // Create a PriorityQueue with custom comparator
        PriorityQueue<Bnode> q = new PriorityQueue<>(n, new ImplementComparator());

        // Add the leaf nodes to the PriorityQueue
        for (int i = 0; i < n; i++) {
            q.add(new Bnode(charArray[i], charfreq[i]));
        }

        // Build the Huffman Tree
        Bnode root = null;
        while (q.size() > 1) {
            // Extract two nodes with the lowest frequency
            Bnode x = q.poll();
            Bnode y = q.poll();

            // Create a new internal node with the sum of frequencies
            Bnode f = new Bnode(x.freq + y.freq, x, y);

            // Add the new node back to the PriorityQueue
            root = f;
            q.add(f);
        }

        // Create the Huffman tree and set the root
        Btree huffmanTree = new Btree();
        huffmanTree.setRoot(root);

        // Print the Huffman codes
        System.out.println(" Char | Huffman code ");
        System.out.println("--------------------");
        huffmanTree.printCode();
    }
}
