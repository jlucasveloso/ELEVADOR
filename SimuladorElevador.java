import java.util.Random;

public class SimuladorElevador {
    public static void main(String[] args) {
        Configuracao config = new Configuracao();
        Elevador[] elevadores = new Elevador[2];
        for (int i = 0; i < 2; i++) {
            elevadores[i] = new Elevador(i + 1, config);
        }

        gerarPessoasAutomaticamente(elevadores);
        
        for (Elevador elevador : elevadores) {
            System.out.println("\n=== Elevador " + elevador.getId() + " iniciando atendimento ===");
            while (elevador.temPessoas()) {
                elevador.atenderProximo();
            }
        }
    }

    public static void gerarPessoasAutomaticamente(Elevador[] elevadores) {
        Random random = new Random();
        int pessoasAdicionadas = 0;

        while (pessoasAdicionadas < 10) {
            String nome = "Pessoa " + (pessoasAdicionadas + 1);
            int idade = random.nextInt(100) + 1;
            boolean cadeirante = random.nextBoolean();
            int peso = random.nextInt(100) + 50;
            int andarDestino = random.nextInt(10);

            Pessoa pessoa = new Pessoa(nome, idade, cadeirante, peso, andarDestino);
            boolean adicionada = false;

            for (Elevador elevador : elevadores) {
                if (elevador.adicionarPessoa(pessoa)) {
                    adicionada = true;
                    pessoasAdicionadas++;
                    break;
                }
            }

            if (!adicionada) {
                System.out.println("Não foi possível adicionar " + pessoa.getNome() + ": excede limite de peso ou capacidade.");
            }
        }
    }
}
