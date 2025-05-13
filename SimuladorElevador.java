public class SimuladorElevador {
    public static void main(String[] args) {
        Configuracao config = new Configuracao();
        Elevador elevador = new Elevador(1, config);

        Pessoa p1 = new Pessoa("João", 65, false, 80, 5); // Idoso
        Pessoa p2 = new Pessoa("Maria", 30, true, 70, 7); // Cadeirante
        Pessoa p3 = new Pessoa("Carlos", 25, false, 75, 3); // Adulto

        elevador.adicionarPessoa(p1);
        elevador.adicionarPessoa(p2);
        elevador.adicionarPessoa(p3);

        elevador.atenderProximo(); // Atende Maria
        elevador.atenderProximo(); // Atende João
        elevador.atenderProximo(); // Atende Carlos
    }
}
