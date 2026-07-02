
public class Cliente {

	private static int ultimoID = 10_000;

    private String nome;
    private int documento;

    /**
     * Construtor do cliente. Cria um novo cliente a partir do nome informado.
     * utilize obrigatoriamente o método setNome para atribuir o nome ao cliente.
     * atribua ao cliente um número de documento gerado sequencialmente a partir
     * do contador estático ultimoID (e incremente o contador).
     */
    @SuppressWarnings("OverridableMethodCallInConstructor")
    public Cliente (String nome) {
        setNome(nome);
        documento = ultimoID++;
    }

    public String getNome() {
    	return this.nome;
    }

    /**
     * Atribui ao cliente o nome informado como parâmetro.
     * esse nome deve conter, pelo menos, duas palavras; caso contrário,
     * lance a exceção adequada (IllegalArgumentException).
     */
    public void setNome(String nome) {
        String[] dadosLinha = nome.split(" ");
        if (dadosLinha.length <= 1){
            throw new IllegalArgumentException("Nome inválido. Deve conter ao menos duas palavras!");
        }
        this.nome = nome;
    }

    public int getDocumento(){
        return this.documento;
    }


    /**
     *retorne uma representação textual do cliente, incluindo seu nome e documento.
     */
    @Override
    public String toString() {
    	return String
                .format("ID (documento): " + documento + " Nome: " + nome);
    }

    /**
     * etorne um código hash para o cliente, que corresponde a seu documento.
     */
    @Override
    public int hashCode(){
    	return documento;
    }

    @Override
    public boolean equals(Object obj) {
        try {
            Cliente outro = (Cliente) obj;
            return this.hashCode() == outro.hashCode();
        } catch (ClassCastException ex) {
            return false;
        }
    }

    static Cliente criarDoTexto(String linha) {

        //String[] dadosLinha;

        String nome;
        Cliente cliente;
        nome = linha;
        cliente = new Cliente(nome);

        return cliente;
    }

    public static void main(String[] args) {
        Cliente teste = new Cliente("Gabriel Galo");
        Cliente teste2 = new Cliente("Wesley");
        String resposta = teste2.toString();
        System.out.println(resposta);
    }
}
