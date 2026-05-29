public class Fornecedor implements Runnable {
    private final ProdutoEstoque estoque;

    public Fornecedor(ProdutoEstoque estoque) {
        this.estoque = estoque;
    }

    @Override
    public void run() {
        // isInterrupted(): verifica se a thread recebeu sinal de interrupção
        while (!Thread.currentThread().isInterrupted()) {
            try {
                // sleep(): pausa o fornecedor por 800ms entre reposições
                Thread.sleep(800);
                estoque.repor(2);
            } catch (InterruptedException e) {
                // interrupt(): preserva o estado de interrupção e permite sair do loop
                Thread.currentThread().interrupt();
            }
        }
        System.out.println("Fornecedor encerrado.");
    }
}
