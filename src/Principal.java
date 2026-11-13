public class Principal {
    public static void main(String[] args) {
        Lista lista = new Lista();
        System.out.println(lista.vazia());       // true

        Dados t1 = new Dados();
        t1.setAdiciona(1, 3, "Erro no login");
        Dados t2 = new Dados();
        t2.setAdiciona(2, 8, "Cor do botão");
        Dados t3 = new Dados();
        t3.setAdiciona(3, 1, "Servidor caiu");

        lista.adiciona(t1);
        lista.adiciona(t2);
        lista.adiciona(0, t3);
        System.out.println(lista);               // [#3 (Prio 1), #1 (Prio 3), #2 (Prio 8)]
        System.out.println(lista.tamanho());     // 3
        lista.remove(1);
        System.out.println(lista);               // [#3 (Prio 1), #2 (Prio 8)]
    }
}