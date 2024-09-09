public class Bnode {
    private int x;
    private Bnode esq, dir;

    public Bnode(int valor) {
        x = valor;
        esq = null;
        dir = null;
    }

    public void add(int valor) {
        if (valor > x) {
            if (dir != null) {
                dir.add(valor);
            } else {
                dir = new Bnode(valor);
            }
        } else {
            if (esq != null) {
                esq.add(valor);
            } else {
                esq = new Bnode(valor);
            }
        }
    }


    public void show() {
        if (dir != null) {
            dir.show();
        }
        System.out.println(x);
        if (esq != null) {
            esq.show();
        }
    }

    public int size() {
        int e = 0, d = 0;
        if (esq != null) e = esq.size();
        if (dir != null) d = dir.size();
        return 1 + e + d;
    }

    public int soma() {
        int e = 0, d = 0;
        if (esq != null) e = esq.soma();
        if (dir != null) d = dir.soma();
        return x + e + d;
    }

    public void printFolha() {
        if (esq == null && dir == null) {
            System.out.println(x);
        }
        if (esq != null) esq.printFolha();
        if (dir != null) dir.printFolha();
    }

    public int qntdFolha() {
        int e = 0, d = 0;
        if (esq == null && dir == null) return 1;
        if (esq != null) e = esq.qntdFolha();
        if (dir != null) d = dir.qntdFolha();
        return e + d;
    }

    public int somaFolhas() {
        if (esq == null && dir == null) {
            return x; // Retorna o valor do nó folha
        }
        int somaEsq = 0, somaDir = 0;
        if (esq != null) somaEsq = esq.somaFolhas(); // Soma das folhas na subárvore esquerda
        if (dir != null) somaDir = dir.somaFolhas(); // Soma das folhas na subárvore direita
        return somaEsq + somaDir; // Soma dos valores das folhas
    }

    public int qntdNoInterno() {
        return size() - qntdFolha();

    }

    public int somaNoInterno(){
        if(esq == null && dir == null) return 0;

        int soma = x;
        if(esq != null) soma += esq.somaNoInterno();
        if(dir != null) soma += dir.somaNoInterno();

        return soma;
    }

    public int maiorValor(){
    if(dir == null) return x;
    else return dir.maiorValor();
    }

    public int menorValor(){
        if(esq == null) return x;
        else return esq.menorValor();
    }

    public int alturaArvore(){
                   if (esq == null && dir == null) {
                return 1;
            }

            // Calcula a altura dos filhos
            int alturaEsq = (esq != null) ? esq.alturaArvore() : 0;
            int alturaDir = (dir != null) ? dir.alturaArvore() : 0;

            // A altura do nó atual é a maior altura dos filhos mais 1
            return Math.max(alturaEsq, alturaDir) + 1;
        }
    }

