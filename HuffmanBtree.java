import java.io.*;
import java.util.PriorityQueue;
import java.util.Comparator;

public class HuffmanBtree {

    static class ImplementComparator implements Comparator<Bnode> {
        public int compare(Bnode x, Bnode y) {
            return x.freq - y.freq;
        }
    }

    public static void main(String[] args) {
        char[] charArray = new char[52];
        int[] freqArray = new int[52];
        StringBuilder inputString = new StringBuilder();

        try (BufferedReader br = new BufferedReader(new FileReader("C:\\Users\\Rafael\\Downloads\\IntroductiontoDataStructuresandAlgorithm.txt"))) {
            String line;
            while ((line = br.readLine()) != null) {
                inputString.append(line).append(" ");
            }

            String text = inputString.toString();
            for (char c : text.toCharArray()) {
                if (Character.isLetter(c)) {
                    int index = charToIndex(c);
                    if (index >= 0 && index < freqArray.length) {
                        freqArray[index]++;
                    }
                }
            }

            // Preencher charArray com caracteres presentes
            int index = 0;
            for (int i = 0; i < 26; i++) {
                if (freqArray[i] > 0) {
                    charArray[index++] = (char) ('A' + i);
                }
            }
            for (int i = 0; i < 26; i++) {
                if (freqArray[i + 26] > 0) {
                    charArray[index++] = (char) ('a' + i);
                }
            }

            // Criar a fila de prioridade apenas com caracteres presentes
            PriorityQueue<Bnode> q = new PriorityQueue<>(new ImplementComparator());
            for (int i = 0; i < index; i++) { // Use `index` em vez de `charArray.length`
                q.add(new Bnode(charArray[i], freqArray[charToIndex(charArray[i])]));
            }

            // Construir a árvore de Huffman
            while (q.size() > 1) {
                Bnode x = q.poll();
                Bnode y = q.poll();
                Bnode f = new Bnode(x.freq + y.freq, x, y);
                q.add(f);
            }

            Btree huffmanTree = new Btree();
            huffmanTree.setRoot(q.poll());

            String[] huffmanCodeArray = new String[52];

            System.out.println(" Char | Huffman code ");
            System.out.println("--------------------");
            huffmanTree.printCode(huffmanCodeArray, charArray);

            StringBuilder encodedString = new StringBuilder();
            for (char c : inputString.toString().toCharArray()) {
                if (Character.isLetter(c)) {
                    int indexInCode = charToIndex(c);
                    if (indexInCode >= 0 && indexInCode < huffmanCodeArray.length) {
                        String code = huffmanCodeArray[indexInCode];
                        if (code != null) {
                            encodedString.append(code);
                        }
                    }
                } else {
                    encodedString.append(c);
                }
            }

            String decodedString = decodeHuffman(huffmanTree, encodedString.toString());

            System.out.println("\nString Original: " + inputString.toString());
            System.out.println("String Codificada: " + encodedString.toString());
            System.out.println("String Decodificada: " + decodedString);

            try (BufferedWriter writer = new BufferedWriter(new FileWriter("C:\\Users\\Rafael\\Downloads\\IntroductiontoDataStructuresandAlgorithm.txt"))) {
                writer.write("String Original: \n" + inputString.toString() + "\n\n");
                writer.write("String Codificada: \n" + encodedString.toString() + "\n\n");
                writer.write("String Decodificada: \n" + decodedString);
            } catch (IOException e) {
                e.printStackTrace();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private static String decodeHuffman(Btree huffmanTree, String encodedString) {
        StringBuilder decodedString = new StringBuilder();
        Bnode currentNode = huffmanTree.root;

        for (char bit : encodedString.toCharArray()) {
            if (bit == '0') {
                currentNode = currentNode.esq;
            } else if (bit == '1') {
                currentNode = currentNode.dir;
            }

            if (currentNode.esq == null && currentNode.dir == null) {
                decodedString.append(currentNode.c);
                currentNode = huffmanTree.root;
            }
        }

        return decodedString.toString();
    }

    private static int charToIndex(char c) {
        if (Character.isUpperCase(c)) {
            return c - 'A';
        } else if (Character.isLowerCase(c)) {
            return c - 'a' + 26;
        } else {
            return -1; // Retornar -1 para caracteres não alfabéticos
        }
    }
}
