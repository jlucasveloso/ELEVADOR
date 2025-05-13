import java.util.Random;

public class Elevador {
    private int id;
    private int andarAtual;
    private int capacidade; // Capacidade máxima de passageiros
    private int pesoAtual;
    private int passageirosAtuais;
    private FilaPrioridade filaPrioridade;
    private Configuracao config;
    private PainelControle painelControle;
    private int energiaConsumida;

    public Elevador(int id, Configuracao config) {
        this.id = id;
        this.andarAtual = 0;
        this.capacidade = config.capacidadeElevador;
        this.pesoAtual = 0;
        this.passageirosAtuais = 0;
        this.filaPrioridade = new FilaPrioridade();
        this.config = config;
        this.painelControle = new PainelControle();
        this.energiaConsumida = 0;
    }

    public boolean adicionarPessoa(Pessoa pessoa) {
        if (passageirosAtuais < capacidade && pesoAtual + pessoa.getPeso() <= config.pesoMaximoElevador) {
            filaPrioridade.adicionarPessoa(pessoa);
            pesoAtual += pessoa.getPeso();
            passageirosAtuais++;
            painelControle.selecionarAndar(pessoa.getAndarDestino());
            return true;
        } else {
            System.out.println("Capacidade ou peso excede o limite do elevador.");
            return false;
        }
    }

    public void atenderProximo() {
        Pessoa pessoa = filaPrioridade.removerPessoa();
        if (pessoa != null) {
            int andarDestino = pessoa.getAndarDestino();
            moverParaAndar(andarDestino);
            int tempoViagem = calcularTempoViagem();
            energiaConsumida += config.consumoEnergiaDeslocamento * Math.abs(andarDestino - andarAtual);
            energiaConsumida += config.consumoEnergiaParada;
            System.out.println("Atendendo " + pessoa.getNome() + " no andar " + andarDestino);
            System.out.println("Tempo estimado de viagem: " + tempoViagem + " segundos");
            System.out.println("Energia consumida até agora: " + energiaConsumida + " unidades");
            pesoAtual -= pessoa.getPeso();
            passageirosAtuais--;
        } else {
            System.out.println("Nenhuma pessoa na fila.");
        }
    }

    private int calcularTempoViagem() {
        Random rand = new Random();
        return rand.nextInt(config.tempoMaximoViagem - config.tempoMinimoViagem + 1) + config.tempoMinimoViagem;
    }

    public int getAndarAtual() {
        return andarAtual;
    }

    // public void moverParaAndar(int andarDestino) {
    //     System.out.println("Elevador " + id + " movendo-se do andar " + andarAtual + " para o andar " + andarDestino);
    //     andarAtual = andarDestino;
    // }

    public void moverParaAndar(int andarDestino) {
    System.out.println("Elevador " + id + " iniciando deslocamento do andar " + andarAtual + " para o andar " + andarDestino);
    
    while (andarAtual != andarDestino) {
        if (andarAtual < andarDestino) {
            andarAtual++;
        } else {
            andarAtual--;
        }

        System.out.println("Elevador " + id + " no andar " + andarAtual);
        
        // Simula o tempo de deslocamento entre andares
        try {
            Thread.sleep(1000); // 1 segundo por andar
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    System.out.println("Elevador " + id + " chegou ao andar " + andarDestino);
}

}
