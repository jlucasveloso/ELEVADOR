public class Pessoa {
    private String nome;
    private int idade;
    private boolean cadeirante;
    private int peso;
    private int andarOrigem;
    private int andarDestino;

    public Pessoa(String nome, int idade, boolean cadeirante, int peso, int andarOrigem, int andarDestino) {
        this.nome = nome;
        this.idade = idade;
        this.cadeirante = cadeirante;
        this.peso = peso;
        this.andarOrigem = andarOrigem;
        this.andarDestino = andarDestino;

        System.out.println("[LOG] Nova pessoa criada: " + this);
    }

    public boolean isCadeirante() {
        return cadeirante;
    }

    public int getPrioridade() {
        return (isCadeirante() || idade >= 60) ? 1 : 2;
    }

    public int getPeso() {
        return peso;
    }

    public int getAndarDestino() {
        return andarDestino;
    }

    public String getNome() {
        return nome;
    }

    public int getIdade() {
        return idade;
    }

    public int getAndarOrigem() {
        return andarOrigem;
    }

    @Override
    public String toString() {
        return nome + " | Idade: " + idade + " | Cadeirante: " + (cadeirante ? "Sim" : "Não") +
               " | Peso: " + peso + "kg | Origem: " + andarOrigem + " | Destino: " + andarDestino;
    }
}
