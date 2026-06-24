public class Main {

    public static void main(String[] args) throws Exception {

        Thread t = new Thread(() -> {

            while (!Thread.currentThread().isInterrupted()) {
                System.out.println("Executando...");
            }

            System.out.println("Thread encerrada");
        });

        t.start();

        Thread.sleep(2000);

        t.interrupt();
    }
}