public class SistemaLogin {

    private static ThreadLocal<String> usuarioLogado = new ThreadLocal<>();

    public static void main(String[] args) {

        Thread usuario1 = new Thread(() -> {

            login("Lucas");

            verPerfil();
            realizarCompra("Notebook");
            gerarHistorico();

            logout();
        });

        Thread usuario2 = new Thread(() -> {

            login("Bianca");

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
        esperar(1000);
        System.out.println(usuarioLogado.get() + " acessou o perfil");
    }

    public static void realizarCompra(String produto) {
        esperar(1500);
        System.out.println(usuarioLogado.get() + " comprou: " + produto);
    }

    public static void gerarHistorico() {
        esperar(800);
        System.out.println("Histórico gerado para: " + usuarioLogado.get());
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
}