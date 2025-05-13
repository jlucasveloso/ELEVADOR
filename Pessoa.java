public class Pessoa {
    private String nome;
    private int idade;
    private boolean cadeirante;
    private int peso;
    private int andarDestino;

    public Pessoa(String nome, int idade, boolean cadeirante, int peso, int andarDestino) {
        this.nome = nome;
        this.idade = idade;
        this.cadeirante = cadeirante;
        this.peso = peso;
        this.andarDestino = andarDestino;
    }

    public int getPrioridade() {
        if (cadeirante || idade >= 60) return 1;
        return 2;
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
}
