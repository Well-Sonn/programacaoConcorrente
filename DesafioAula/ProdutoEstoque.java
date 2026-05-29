public class ProdutoEstoque {
    private int quantidade;
    private final int capacidadeMaxima; // final: valor fixo após inicialização
    private volatile boolean lojaAberta; // volatile: visibilidade imediata entre threads

    public ProdutoEstoque(int quantidadeInicial, int capacidadeMaxima) {
        this.quantidade = quantidadeInicial;
        this.capacidadeMaxima = capacidadeMaxima;
        this.lojaAberta = true;
    }

    // synchronized: protege o acesso concorrente ao método comprar
    public synchronized boolean comprar(String usuario, String sessao) throws InterruptedException {
        while (quantidade == 0) {
            if (!lojaAberta) return false;
            System.out.println(usuario + " aguardando reposição...");
            // wait(): libera o monitor e aguarda notificação de reposição ou fechamento
            wait();
        }
        quantidade--;
        String sessaoCurta = sessao.substring(0, 4);
        System.out.println("[" + sessaoCurta + "] " + usuario + " comprou 1 produto. Restam: " + quantidade);
        return true;
    }

    public synchronized void repor(int qtd) {
        quantidade = Math.min(quantidade + qtd, capacidadeMaxima);
        System.out.println("Fornecedor repôs produtos. Estoque: " + quantidade);
        // notifyAll(): acorda todas as threads que estão em wait() neste objeto
        notifyAll();
    }

    public synchronized void fecharLoja() {
        lojaAberta = false;
        notifyAll();
    }

    public synchronized int getQuantidade() {
        return quantidade;
    }
}
