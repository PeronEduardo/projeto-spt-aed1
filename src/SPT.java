public class SPT {

    public void adicionarTarefa(Lista backlog, Dados novaTarefa) {
        int posicao = backlog.tamanho();

        while (posicao > 0) {
            Dados anterior = (Dados) backlog.pega(posicao - 1);
            if (anterior.getPegaPrioridade() > novaTarefa.getPegaPrioridade()) {
                posicao--;
            } else {
                break;
            }
        }

        backlog.adiciona(posicao, novaTarefa);
    }
}