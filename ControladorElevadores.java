public class ControladorElevadores {
    private Elevador[] elevadores;
    private Configuracao config;
    private Horario horario;
    private FilaPrioridade filaPrioridade;

    public ControladorElevadores(Configuracao config) {
        this.config = config;
        this.elevadores = new Elevador[config.numeroElevadores];
        for (int i = 0; i < config.numeroElevadores; i++) {
            elevadores[i] = new Elevador(i + 1, config);
        }
        this.horario = new Horario();
        this.filaPrioridade = new FilaPrioridade(100); // Capacidade arbitrária
    }

    public void simularCiclo() {
        System.out.println("\n=== Hora " + horario.getHoraAtual() + ":" + String.format("%02d", horario.getMinutoAtual()) + " ===");
        System.out.println("Horário de pico? " + (horario.isHorarioPico() ? "Sim" : "Não"));

        int numPessoasNovas = horario.isHorarioPico() ? 10 : 3;

        // Gerar pessoas automaticamente
        for (int i = 0; i < numPessoasNovas; i++) {
            Pessoa p = gerarPessoaAleatoria();
            filaPrioridade.adicionarPessoa(p);
        }

        // Distribuir passageiros entre os elevadores
        while (!filaPrioridade.estaVazia()) {
            Pessoa p = filaPrioridade.removerPessoa();
            Elevador elevadorSelecionado = null;
            int menorDistancia = Integer.MAX_VALUE;

            // Encontrar o elevador mais próximo com capacidade
            for (Elevador elevador : elevadores) {
                if (elevador.podeAdicionarPessoa(p)) {
                    int distancia = Math.abs(elevador.getAndarAtual() - p.getAndarOrigem());
                    if (distancia < menorDistancia) {
                        menorDistancia = distancia;
                        elevadorSelecionado = elevador;
                    }
                }
            }

            if (elevadorSelecionado != null) {
                elevadorSelecionado.adicionarPessoa(p);
                elevadorSelecionado.atenderProximo();
            } else {
                System.out.println("Nenhum elevador disponível para atender " + p.getNome());
            }
        }

        horario.avancarMinuto();
    }

    private Pessoa gerarPessoaAleatoria() {
        String nome = "Pessoa" + (int)(Math.random() * 1000);
        int idade = (int)(Math.random() * 80) + 1;
        boolean cadeirante = Math.random() < 0.1;
        int peso = 50 + (int)(Math.random() * 50);
        int andarOrigem = 1 + (int)(Math.random() * (config.numeroAndares - 1));
        int andarDestino = 1 + (int)(Math.random() * (config.numeroAndares - 1));
        return new Pessoa(nome, idade, cadeirante, peso, andarOrigem, andarDestino);
    }

    public void imprimirResumo() {
        int totalViagens = 0;
        int tempoTotalViagens = 0;
        int energiaTotalConsumida = 0;

        for (Elevador elevador : elevadores) {
            totalViagens += elevador.getTotalViagens();
            tempoTotalViagens += elevador.getTempoTotalViagens();
            energiaTotalConsumida += elevador.getEnergiaConsumida();
        }

        System.out.println("\n==================================");
        System.out.println("         RESUMO DA SIMULAÇÃO      ");
        System.out.println("==================================");
        System.out.printf("Total de viagens realizadas: %d%n", totalViagens);
        System.out.printf("Tempo médio de viagem:       %.2f segundos%n", (totalViagens == 0 ? 0 : (double) tempoTotalViagens / totalViagens));
        System.out.printf("Energia total consumida:     %d unidades%n", energiaTotalConsumida);
        System.out.println("==================================\n");
    }

    public Horario getHorario() {
        return horario;
    }
}
