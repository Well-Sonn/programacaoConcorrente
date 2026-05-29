import java.util.UUID;

// Runnable: esta classe define um trabalho que pode ser executado por uma Thread
public class Comprador implements Runnable {
    private final String nome;
    private final ProdutoEstoque estoque;
    // ThreadLocal: cada thread de comprador mantém sua própria sessão única
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
                // sleep(): pausa a thread comprador por 300ms entre tentativas
                Thread.sleep(300);
                boolean comprou = estoque.comprar(nome, sessao.get());
                if (comprou) {
                    comprasRealizadas++;
                } else {
                    System.out.println(nome + " desistiu (loja fechada sem estoque).");
                    break;
                }
            } catch (InterruptedException e) {
                // interrupt(): preserva o estado de interrupção antes de sair
                Thread.currentThread().interrupt();
                System.out.println(nome + " foi interrompido.");
                break;
            }
        }
        System.out.println(nome + " encerrou. Total comprado: " + comprasRealizadas);
    }
}
