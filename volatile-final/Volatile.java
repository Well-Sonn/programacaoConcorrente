class SistemaThreads {


     // VOLATILE:  Garante VISIBILIDADE entre threads.
     // Quando uma thread altera essa variável,
     // as outras threads enxergarão imediatamente

    private volatile boolean executando = true;



    public void iniciar() {


        // cria um thread
        Thread thread = new Thread(() -> {

            System.out.println("Sistema Java iniciado.");


            while (executando) {

                System.out.println("Thread trabalhando...");
                // Simula processamento da thread
            }

            // QUANDO EXECUTAR SAI DO LOOP

            System.out.println("Thread encerrada.");
        });

        // INICIA A THREAD
        thread.start();

        try {

            //SLEP DE 3 SEGUNDOS ANTES DE "ALTERAR A VARIAVEL"
            Thread.sleep(3000);

        } catch (InterruptedException e) {

            e.printStackTrace();
        }



        // SIMULAÇÃO DE VISIBILIDADE

        // a thread principal altera o valor da variável para false.
        //  a outra thread consegue enxergar essa alteração imediatamente
        System.out.println("\n\nExecutando para FALSE");
        executando = false;

        //SE EU TIRAR O VOLATILE DA VARIAVEL DEMORA PARA ENCERRAR O PROGRAMA
    }

    public static void main(String[] args) {

        SistemaThreads sistema = new SistemaThreads();

        // Inicia execução
        sistema.iniciar();

    }
}