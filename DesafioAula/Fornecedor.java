public class Fornecedor implements Runnable {
    private final ProdutoEstoque estoque;

    public Fornecedor(ProdutoEstoque estoque) {
        this.estoque = estoque;
    }

    @Override
    public void run() {
        while (!Thread.currentThread().isInterrupted()) {
            try {
                Thread.sleep(800);
                estoque.repor(2);
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
            }
        }
        System.out.println("Fornecedor encerrado.");
    }
}
