public class SistemaLoginInherit {

    private static InheritableThreadLocal<String> usuarioLogado = new InheritableThreadLocal<>();

    public static void main(String[] args) {
        login("Admin");

        Thread usuario1 = new Thread(() -> {

            login("Lucas");

            verPerfil();
            realizarCompra("Notebook");
            gerarHistorico();

            logout();

        });

        Thread usuario2 = new Thread(() -> {

            //login("Maria");

            verPerfil();
            realizarCompra("Mouse");
            gerarHistorico();

            logout();

        });

        usuario1.start();
        usuario2.start();
    }

    public static void login(String usuario) {
        usuarioLogado.set(usuario);
        System.out.println(usuario + " fez login");
    }

    public static void verPerfil() {
        Thread threadPerfil =
                new Thread(() -> {
                    esperar(1000);
                    System.out.println(usuarioLogado.get() + " acessou o perfil");
                });
        threadPerfil.start();
        esperarThread(threadPerfil);
    }

    public static void realizarCompra(String produto) {

        Thread threadCompra = new Thread(() -> {
                    esperar(1500);
                    System.out.println(usuarioLogado.get() + " comprou: " + produto);
                });
        threadCompra.start();
        esperarThread(threadCompra);
    }

    public static void gerarHistorico() {
        Thread threadHistorico =
                new Thread(() -> {
                    esperar(800);
                    System.out.println("Histórico gerado para: " + usuarioLogado.get());
                });
        threadHistorico.start();
        esperarThread(threadHistorico);
    }

    public static void logout() {
        System.out.println(usuarioLogado.get() + " fez logout");
        usuarioLogado.remove();
    }

    public static void esperar(int tempo) {

        try {
            Thread.sleep(tempo);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    public static void esperarThread(Thread thread) {
        try {
            thread.join();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}