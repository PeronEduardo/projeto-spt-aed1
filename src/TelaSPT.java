import javax.swing.*;
import java.awt.*;

public class TelaSPT extends JFrame {

    // ---------- Estruturas de dados ----------
    private Lista backlog = new Lista();
    private SPT spt = new SPT();
    private int proximoId = 1;
    private Dados ultimaInserida = null;

    // ---------- Componentes da tela ----------
    private JSpinner campoPrioridade = new JSpinner(new SpinnerNumberModel(5, 1, 10, 1));
    private JTextField campoDescricao = new JTextField(15);
    private JButton botaoAdicionar = new JButton("Adicionar");
    private JButton botaoExemplo = new JButton("Exemplo do enunciado");
    private JButton botaoAtender = new JButton("Atender próxima");
    private JButton botaoLimpar = new JButton("Limpar");
    private JTextArea areaLog = new JTextArea(8, 60);
    private PainelBacklog painel = new PainelBacklog();

    public TelaSPT() {
        super("SPT - Sistema de Priorização de Tarefas");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(new BorderLayout());

        // Topo: campos e botões
        JPanel topo = new JPanel(new FlowLayout(FlowLayout.LEFT));
        topo.add(new JLabel("Prioridade (1 = mais crítica):"));
        topo.add(campoPrioridade);
        topo.add(new JLabel("Descrição:"));
        topo.add(campoDescricao);
        topo.add(botaoAdicionar);
        topo.add(botaoExemplo);
        topo.add(botaoAtender);
        topo.add(botaoLimpar);
        add(topo, BorderLayout.NORTH);

        // Centro: desenho do backlog
        add(painel, BorderLayout.CENTER);

        // Baixo: histórico das operações
        areaLog.setEditable(false);
        areaLog.setFont(new Font(Font.MONOSPACED, Font.PLAIN, 13));
        add(new JScrollPane(areaLog), BorderLayout.SOUTH);

        // Ações dos botões
        botaoAdicionar.addActionListener(e -> adicionar());
        botaoExemplo.addActionListener(e -> carregarExemplo());
        botaoAtender.addActionListener(e -> atenderProxima());
        botaoLimpar.addActionListener(e -> limpar());

        setSize(1000, 450);
        setLocationRelativeTo(null);
    }

    // ---------- Ações ----------

    private void adicionar() {
        String descricao = campoDescricao.getText().trim();
        if (descricao.isEmpty()) {
            JOptionPane.showMessageDialog(this, "Digite a descrição da tarefa.");
            return;
        }
        int prioridade = (Integer) campoPrioridade.getValue();

        Dados nova = new Dados();
        nova.setAdiciona(proximoId, prioridade, descricao);
        proximoId++;

        String antes = backlog.toString();
        spt.adicionarTarefa(backlog, nova);
        ultimaInserida = nova;

        areaLog.append("Nova tarefa " + nova + " - " + descricao + "\n");
        areaLog.append("  Antes:  " + antes + "\n");
        areaLog.append("  Depois: " + backlog + "\n");
        areaLog.append("  Inserida na posição " + posicaoDe(nova) + "\n\n");

        campoDescricao.setText("");
        painel.repaint();
    }

    private void carregarExemplo() {
        limpar();
        int[] prioridades = {1, 3, 5, 8};
        String[] descricoes = {"Servidor fora do ar", "Erro no login",
                               "Relatorio lento", "Cor do botao"};
        for (int i = 0; i < prioridades.length; i++) {
            Dados t = new Dados();
            t.setAdiciona(proximoId, prioridades[i], descricoes[i]);
            proximoId++;
            spt.adicionarTarefa(backlog, t);
        }
        areaLog.append("Exemplo carregado: " + backlog + "\n");
        areaLog.append("Adicione uma tarefa com prioridade 4.\n\n");
        campoPrioridade.setValue(4);
        campoDescricao.setText("Bug no carrinho");
        painel.repaint();
    }

    private void atenderProxima() {
        if (backlog.vazia()) {
            JOptionPane.showMessageDialog(this, "Backlog vazio.");
            return;
        }
        Dados atendida = (Dados) backlog.pega(0);
        backlog.remove(0);
        areaLog.append("Atendida: " + atendida + " - " + atendida.getPegaDescricao() + "\n");
        areaLog.append("  Backlog: " + backlog + "\n\n");
        painel.repaint();
    }

    private void limpar() {
        backlog = new Lista();
        proximoId = 1;
        ultimaInserida = null;
        areaLog.setText("");
        painel.repaint();
    }

    private int posicaoDe(Dados tarefa) {
        for (int i = 0; i < backlog.tamanho(); i++) {
            if (backlog.pega(i) == tarefa) {
                return i;
            }
        }
        return -1;
    }

    // ---------- Desenho do backlog ----------

    private class PainelBacklog extends JPanel {

        private static final int LARGURA = 105;
        private static final int ALTURA = 110;
        private static final int ESPACO = 12;

        protected void paintComponent(Graphics g) {
            super.paintComponent(g);
            Graphics2D g2 = (Graphics2D) g;
            g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING,
                                RenderingHints.VALUE_ANTIALIAS_ON);

            g2.setFont(new Font(Font.SANS_SERIF, Font.BOLD, 14));
            g2.drawString("Mais crítica  <<<   BACKLOG   >>>  Menos crítica", 20, 25);

            for (int i = 0; i < backlog.tamanho(); i++) {
                Dados t = (Dados) backlog.pega(i);
                int coluna = i % 8;
                int linha = i / 8;
                int x = 20 + coluna * (LARGURA + ESPACO);
                int y = 45 + linha * (ALTURA + 35);

                // Cor: vermelho (1) até verde (10)
                float tom = (t.getPegaPrioridade() - 1) / 9f * 0.33f;
                g2.setColor(Color.getHSBColor(tom, 0.35f, 1f));
                g2.fillRoundRect(x, y, LARGURA, ALTURA, 15, 15);

                // Borda azul na última tarefa inserida
                if (t == ultimaInserida) {
                    g2.setColor(new Color(30, 90, 220));
                    g2.setStroke(new BasicStroke(4));
                } else {
                    g2.setColor(Color.GRAY);
                    g2.setStroke(new BasicStroke(1));
                }
                g2.drawRoundRect(x, y, LARGURA, ALTURA, 15, 15);

                g2.setColor(Color.BLACK);
                g2.setFont(new Font(Font.SANS_SERIF, Font.BOLD, 20));
                g2.drawString("P" + t.getPegaPrioridade(), x + 10, y + 30);
                g2.setFont(new Font(Font.SANS_SERIF, Font.PLAIN, 12));
                g2.drawString("ID #" + t.getPegaId(), x + 10, y + 52);
                g2.drawString(resumir(t.getPegaDescricao()), x + 10, y + 74);

                g2.setColor(Color.GRAY);
                g2.drawString("[" + i + "]", x + LARGURA / 2 - 8, y + ALTURA + 18);
            }
        }

        private String resumir(String texto) {
            if (texto.length() > 14) {
                return texto.substring(0, 13) + "...";
            }
            return texto;
        }
    }
}
