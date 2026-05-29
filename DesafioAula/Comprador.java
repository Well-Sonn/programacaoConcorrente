import java.util.UUID;

public class Comprador implements Runnable {
    private final String nome;
    private final ProdutoEstoque estoque;
    private static final ThreadLocal<String> sessao =
            ThreadLocal.withInitial(() -> UUID.randomUUID().toString());

    public Comprador(String nome, ProdutoEstoque estoque) {
        this.nome = nome;
        this.estoque = estoque;
    }

    @Override
    public void run() {
        int comprasRealizadas = 0;
        int totalDesejado = 3;

        while (comprasRealizadas < totalDesejado) {
            try {
                Thread.sleep(300);
                boolean comprou = estoque.comprar(nome, sessao.get());
                if (comprou) {
                    comprasRealizadas++;
                } else {
                    System.out.println(nome + " desistiu (loja fechada sem estoque).");
                    break;
                }
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
                System.out.println(nome + " foi interrompido.");
                break;
            }
        }
        System.out.println(nome + " encerrou. Total comprado: " + comprasRealizadas);
    }
}
