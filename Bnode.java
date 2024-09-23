public class Bnode {
    int freq;
    char c;
    Bnode esq, dir;


    public Bnode(char c, int freq) {
        this.c = c;
        this.freq = freq;
        this.esq = null;
        this.dir = null;
    }


    public Bnode(int freq, Bnode esq, Bnode dir) {
        this.c = '-';
        this.freq = freq;
        this.esq = esq;
        this.dir = dir;
    }


    public void printCode(String s, String[] huffmanCodeArray, char[] charArray) {
        if (esq == null && dir == null) {
            int index = charToIndex(c);
            huffmanCodeArray[index] = s;
            System.out.println(c + "   |  " + s);
            return;
        }
        if (esq != null) {
            esq.printCode(s + "0", huffmanCodeArray, charArray);
        }
        if (dir != null) {
            dir.printCode(s + "1", huffmanCodeArray, charArray);
        }
    }

    private int charToIndex(char c) {
        if (Character.isUpperCase(c)) {
            return c - 'A';
        } else {
            return c - 'a' + 26;
        }
    }
}