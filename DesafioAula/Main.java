public class Main {
    public static void main(String[] args) throws InterruptedException {
        ProdutoEstoque estoque = new ProdutoEstoque(3, 10);
        // Thread: cria uma nova thread que executa o Runnable Fornecedor
        Thread threadFornecedor = new Thread(new Fornecedor(estoque), "Fornecedor");

        String[] nomes = {"Ricardo", "Maria", "João", "Ana", "Pedro"};
        Thread[] compradores = new Thread[nomes.length];
        for (int i = 0; i < nomes.length; i++) {
            // Thread: cada comprador é executado em uma thread separada
            compradores[i] = new Thread(new Comprador(nomes[i], estoque), nomes[i]);
        }

        // start(): inicia a execução das threads
        threadFornecedor.start();
        for (Thread c : compradores) c.start();

        // join(): espera cada thread de comprador terminar antes de continuar
        for (Thread c : compradores) c.join();

        estoque.fecharLoja();
        // interrupt(): envia sinal de interrupção para o fornecedor finalizar
        threadFornecedor.interrupt();
        threadFornecedor.join();

        System.out.println("Estoque final: " + estoque.getQuantidade());
        System.out.println("Sistema encerrado.");
    }
}
