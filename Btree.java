import java.sql.SQLOutput;

public class Btree {
    Bnode raiz;

    public Btree() {
        raiz = null;
    }

    public void add(int valor) {
        if (raiz != null) {
            raiz.add(valor);
        } else {
            raiz = new Bnode(valor);
        }
    }

    public void show() {
        if (raiz != null) {
            raiz.show();
        } else {
            System.out.println("Árvore vazia!!");
        }
    }

    public int size() {
        if (raiz != null) {
            return raiz.size();
        }
        return 0;
    }

    public int soma() {
        if (raiz != null) {
            return raiz.soma();
        }
        return 0;
    }

    public void printFolha() {
        if (raiz != null) {
            raiz.printFolha();
        } else {
            System.out.println("Árvore vazia!!");
        }
    }

    public int qntdFolha() {
        if (raiz != null) {
            return raiz.qntdFolha();
        }
        return 0;
    }

    public int somaFolhas() {
        if (raiz != null) {
            return raiz.somaFolhas();
        }
        return 0;
    }

    public int qntdNoInterno() {
        if (raiz != null) {
            return raiz.qntdNoInterno();
        } else {
           return 0;
        }
    }

    public int somaNoInterno(){
        if (raiz != null){
            return raiz.somaNoInterno();
        } else {
            return 0;
        }
    }

    public int maiorValor(){
        if(raiz != null){
            return raiz.maiorValor();
        } else {
            return 0;
        }
    }

    public int menorValor(){
        if(raiz != null){
            return raiz.menorValor();
        } else {
            return 0;
        }
    }

    public int alturaArvore(){
        if(raiz != null){
            return raiz.alturaArvore();
        } else {
            return 0;
        }
    }

    //public void imprimirNivelArvore (int nivel){
      //  if(raiz != null)
        //    int altura =
    //}

}

