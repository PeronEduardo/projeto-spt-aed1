public class Tarefa {

    // Atributos privados: só a própria classe mexe neles
    private int id;
    private int prioridade;
    private String descricao;

    // Construtor: cria a tarefa e já valida a prioridade
    public Tarefa(int id, int prioridade, String descricao) {
        if (prioridade < 1 || prioridade > 10) {
            throw new IllegalArgumentException("Prioridade deve estar entre 1 e 10");
        }
        this.id = id;
        this.prioridade = prioridade;
        this.descricao = descricao;
    }

    public int getId() {
        return id;
    }

    public int getPrioridade() {
        return prioridade;
    }

    public String getDescricao() {
        return descricao;
    }

    // Como a tarefa aparece quando é impressa: #5 (Prio 4)
    @Override
    public String toString() {
        return "#" + id + " (Prio " + prioridade + ")";
    }
}