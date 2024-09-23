public class Btree {
    Bnode root;

    public Btree() {
        root = null;
    }

    public void setRoot(Bnode node) {
        this.root = node;
    }

    public void printCode(String[] huffmanCodeArray, char[] charArray) {
        if (root != null) {
            root.printCode("", huffmanCodeArray, charArray);
        }
    }


    public String decode(String encodedString) {
        StringBuilder decodedString = new StringBuilder();
        Bnode currentNode = root;

        for (char bit : encodedString.toCharArray()) {
            if (bit == '0') {
                currentNode = currentNode.esq;
            } else if (bit == '1') {
                currentNode = currentNode.dir;
            }


            if (currentNode.esq == null && currentNode.dir == null) {

                if (currentNode.c == '_') {
                    decodedString.append(' ');
                } else {
                    decodedString.append(currentNode.c);
                }

                currentNode = root;
            }
        }

        return decodedString.toString();
    }
}