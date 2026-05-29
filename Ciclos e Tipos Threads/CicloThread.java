package Ciclos;

public class CicloThread {
    public static void main(String[] args) throws Exception {

        Thread t1 = new Thread(() -> {
            try {
                System.out.println("t1 rodando");
                Thread.sleep(1000); // TIMED_WAITING
                System.out.println("t1 finalizando");

            } catch (InterruptedException e) {
                System.out.println("t1 interrompida");
            }
        });

        Thread t2 = new Thread(() -> {
            try {
                System.out.println("t2 esperando t1");
                t1.join(); // WAITING
                System.out.println("t2 continuou");

            } catch (InterruptedException e) {
                System.out.println("t2 interrompida");
            }
        });

        // NEW
        System.out.println("t1: " + t1.getState());
        t1.start();

        // RUNNABLE
        System.out.println("t1: " + t1.getState());
        Thread.sleep(200);

        // TIMED_WAITING
        System.out.println("t1: " + t1.getState());
        t2.start();
        Thread.sleep(100);

        // WAITING
        System.out.println("t2: " + t2.getState());

        t1.join();
        t2.join();

        // TERMINATED
        System.out.println("t1: " + t1.getState());
        System.out.println("t2: " + t2.getState());
    }
}