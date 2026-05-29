public class ExemploFinal {

    public static void main(String[] args) {
        //FINAL, O valor dessa variável não pode ser alterado depois da inicialização
        final String imutavel = "VALOR FIXO";

        //É utilizado final para garantir que esse valor não seja alterado
        //Exemplo: Senha de banco ou uma url fixa da api
        final String codigoBanco = "BANCO-001";
        final String API_URL = "https://api.site.com";

        // A variável pode ser UTILIZADA normalmente.
        System.out.println("SENHA BANCO: " + codigoBanco);

        // Concatenação de Strings
        // Se tenar concatena a variavel codigoBanco + "SENHA NOVA"
        // Com oa variável é final, ela não pode receber valor
        // Quebrando o código, com o erro: Cannot assign a value to final variable

    }

}



