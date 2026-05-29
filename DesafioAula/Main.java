public class Main {
    public static void main(String[] args) throws InterruptedException {
        ProdutoEstoque estoque = new ProdutoEstoque(3, 10);
        Thread threadFornecedor = new Thread(new Fornecedor(estoque), "Fornecedor");

        String[] nomes = {"Ricardo", "Maria", "João", "Ana", "Pedro"};
        Thread[] compradores = new Thread[nomes.length];
        for (int i = 0; i < nomes.length; i++) {
            compradores[i] = new Thread(new Comprador(nomes[i], estoque), nomes[i]);
        }

        threadFornecedor.start();
        for (Thread c : compradores) c.start();
        for (Thread c : compradores) c.join(); // oq faz join

        estoque.fecharLoja();
        threadFornecedor.interrupt();
        threadFornecedor.join();

        System.out.println("Estoque final: " + estoque.getQuantidade());
        System.out.println("Sistema encerrado.");
    }
}
