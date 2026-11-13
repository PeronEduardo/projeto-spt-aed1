public class Principal {
    public static void main(String[] args) {
        SPT spt = new SPT();
        Lista backlog = new Lista();

        Dados t1 = new Dados(); t1.setAdiciona(1, 1, "Servidor fora do ar");
        Dados t2 = new Dados(); t2.setAdiciona(2, 3, "Erro no login");
        Dados t3 = new Dados(); t3.setAdiciona(3, 5, "Relatorio lento");
        Dados t4 = new Dados(); t4.setAdiciona(4, 8, "Cor do botao");
        spt.adicionarTarefa(backlog, t1);
        spt.adicionarTarefa(backlog, t2);
        spt.adicionarTarefa(backlog, t3);
        spt.adicionarTarefa(backlog, t4);
        System.out.println(backlog);

        Dados t5 = new Dados(); t5.setAdiciona(5, 4, "Bug no carrinho");
        spt.adicionarTarefa(backlog, t5);
        System.out.println(backlog);
    }
}