import java.util.Random;

public class Elevador {
    private int id;
    private int andarAtual;
    private int capacidade;
    private int pesoAtual;
    private int passageirosAtuais;
    private FilaPrioridade filaPrioridade;
    private Configuracao config;
    private PainelControle painelControle;
    private int energiaConsumida;
    private int totalViagens;
    private int tempoTotalViagens;

    public Elevador(int id, Configuracao config) {
        this.id = id;
        this.andarAtual = 0;
        this.capacidade = config.capacidadeElevador;
        this.pesoAtual = 0;
        this.passageirosAtuais = 0;
        this.filaPrioridade = new FilaPrioridade(config.capacidadeElevador);
        this.config = config;
        this.painelControle = new PainelControle();
        this.energiaConsumida = 0;
        this.totalViagens = 0;
        this.tempoTotalViagens = 0;
    }

    public boolean adicionarPessoa(Pessoa pessoa) {
        if (passageirosAtuais < capacidade && pesoAtual + pessoa.getPeso() <= config.pesoMaximoElevador) {
            filaPrioridade.adicionarPessoa(pessoa);
            pesoAtual += pessoa.getPeso();
            passageirosAtuais++;
            painelControle.selecionarAndar(pessoa.getAndarDestino());

            System.out.println("[LOG] Pessoa adicionada ao Elevador " + id + ": " + pessoa.getNome() +
                               " (Destino: " + pessoa.getAndarDestino() + ", Peso: " + pessoa.getPeso() + ")");
            return true;
        }
        System.out.println("[LOG] Pessoa não pôde ser adicionada ao Elevador " + id + ": " + pessoa.getNome());
        return false;
    }

    public boolean podeAdicionarPessoa(Pessoa pessoa) {
        return passageirosAtuais < capacidade && (pesoAtual + pessoa.getPeso()) <= config.pesoMaximoElevador;
    }

    public void atenderProximo() {
        Pessoa pessoa = filaPrioridade.removerPessoa();
        if (pessoa != null) {
            int andarDestino = pessoa.getAndarDestino();
            if (andarDestino != andarAtual) {
                int andarOrigem = andarAtual;
                int distancia = Math.abs(andarDestino - andarOrigem);

                int energiaViagem = config.consumoEnergiaDeslocamento * distancia + config.consumoEnergiaParada;
                energiaConsumida += energiaViagem;

                moverParaAndar(andarDestino);

                int tempoViagem = calcularTempoViagem();

                System.out.println("\n==== [LOG] Elevador " + id + " ====");
                System.out.println("[LOG] Passageiro: " + pessoa.getNome() + " | Idade: " + pessoa.getIdade() +
                                   " | Cadeirante: " + (pessoa.isCadeirante() ? "Sim" : "Não"));
                System.out.println("[LOG] Andar de origem: " + andarOrigem + " | Andar de destino: " + andarDestino);
                System.out.println("[LOG] Tempo estimado da viagem: " + tempoViagem + " segundos");
                System.out.println("[LOG] Energia usada nesta viagem: " + energiaViagem + " unidades");
                System.out.println("[LOG] Energia acumulada total: " + energiaConsumida + " unidades");

                tempoTotalViagens += tempoViagem;
                totalViagens++;
            } else {
                System.out.println("[LOG] Elevador " + id + " já está no andar " + andarDestino +
                                   ". Atendendo " + pessoa.getNome());
            }
            pesoAtual -= pessoa.getPeso();
            passageirosAtuais--;
        }
    }

    private int calcularTempoViagem() {
        Random rand = new Random();
        return rand.nextInt(config.tempoMaximoViagem - config.tempoMinimoViagem + 1) + config.tempoMinimoViagem;
    }

    public void moverParaAndar(int andarDestino) {
        System.out.println("[LOG] Elevador " + id + " se movendo do andar " + andarAtual + " para o andar " + andarDestino);
        try {
            Thread.sleep(100 * Math.abs(andarDestino - andarAtual));
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        andarAtual = andarDestino;
    }

    public int getAndarAtual() {
        return andarAtual;
    }

    public int getId() {
        return id;
    }

    public boolean temPessoas() {
        return filaPrioridade.temPessoas();
    }

    public int getEnergiaConsumida() {
        return energiaConsumida;
    }

    public int getTotalViagens() {
        return totalViagens;
    }

    public int getTempoTotalViagens() {
        return tempoTotalViagens;
    }

    public double getTempoMedioViagem() {
        if (totalViagens == 0) return 0;
        return (double) tempoTotalViagens / totalViagens;
    }
}
