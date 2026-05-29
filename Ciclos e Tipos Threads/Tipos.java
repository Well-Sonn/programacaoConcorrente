package Ciclos e Tipos Threads;

public class Tipos {
    public static void main(String[] args) {

        // Thread user
        Thread userThread = new Thread(() -> {
            for (int i = 1; i <= 5; i++) {
                System.out.println("USER THREAD executando: " + i);
                try {
                    Thread.sleep(500);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
            System.out.println("USER THREAD finalizou");
        });

        // Thread daemon
        Thread daemonThread = new Thread(() -> {
            while (true) {
                System.out.println("Daemon thread rodando em background");
                try {
                    Thread.sleep(300);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        });

        daemonThread.setDaemon(true);

        userThread.start();
        daemonThread.start();

        System.out.println("MAIN finalizou");
    }
}