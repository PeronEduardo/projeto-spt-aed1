public class Dados {

    private int id;
    private int prioridade;
    private String descricao;

    public void setAdiciona(int id, int prioridade, String descricao) {
        this.id = id;
        this.prioridade = prioridade;
        this.descricao = descricao;
    }

    public int getPegaId() {
        return this.id;
    }

    public int getPegaPrioridade() {
        return this.prioridade;
    }

    public String getPegaDescricao() {
        return this.descricao;
    }

    public String toString() {
        return "#" + this.id + " (Prio " + this.prioridade + ")";
    }
}