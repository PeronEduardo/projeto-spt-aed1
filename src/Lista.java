public class Lista {

    private Object[] dados = new Object[100];
    private StringBuilder builder;
    private int total = 0;

    public void adiciona(Object elemento) {
        this.liberaEspaco();
        this.dados[this.total] = elemento;
        this.total++;
    }

    public void adiciona(int posicao, Object elemento) {
        if (!this.posicaoValida(posicao)) {
            throw new IllegalArgumentException("Posicao invalida");
        }
        this.liberaEspaco();
        for (int i = this.total - 1; i >= posicao; i--) {
            this.dados[i + 1] = this.dados[i];
        }
        this.dados[posicao] = elemento;
        this.total++;
    }

    public Object pega(int posicao) {
        if (!this.posicaoOcupada(posicao)) {
            throw new IllegalArgumentException("Posicao invalida");
        }
        return this.dados[posicao];
    }

    public void remove(int posicao) {
        if (!this.posicaoOcupada(posicao)) {
            throw new IllegalArgumentException("Posicao invalida");
        }
        for (int i = posicao; i < this.total - 1; i++) {
            this.dados[i] = this.dados[i + 1];
        }
        this.total--;
    }

    public boolean contem(Object dados) {
        this.toString();
        return builder.indexOf(dados.toString()) >= 0 ? true : false;
    }

    public int tamanho() {
        return this.total;
    }

    public boolean vazia() {
        if (this.total > 0) {
            return false;
        }
        return true;
    }

    private boolean posicaoOcupada(int posicao) {
        return posicao >= 0 && posicao < this.total;
    }

    private boolean posicaoValida(int posicao) {
        return posicao >= 0 && posicao <= this.total;
    }

    private void liberaEspaco() {
        if (this.total == this.dados.length) {
            Object[] novaArray = new Object[this.dados.length * 2];
            for (int i = 0; i <= this.dados.length - 1; i++) {
                novaArray[i] = this.dados[i];
            }
            this.dados = novaArray;
        }
    }

    public String toString() {
        if (this.total == 0) {
            return "[]";
        }
        builder = new StringBuilder();
        builder.append("[");
        for (int i = 0; i < this.total - 1; i++) {
            builder.append(this.dados[i]);
            builder.append(", ");
        }
        builder.append(this.dados[this.total - 1]);
        builder.append("]");
        return builder.toString();
    }
}