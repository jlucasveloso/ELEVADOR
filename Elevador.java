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
            System.out.println("Elevador " + id + " atendendo " + pessoa.getNome() + " no andar " + andarDestino);
            System.out.println("Tempo estimado: " + tempoViagem + " segundos");
            System.out.println("Energia total consumida: " + energiaConsumida + " unidades");
            pesoAtual -= pessoa.getPeso();
            passageirosAtuais--;
        }
    }

    private int calcularTempoViagem() {
        Random rand = new Random();
        return rand.nextInt(config.tempoMaximoViagem - config.tempoMinimoViagem + 1) + config.tempoMinimoViagem;
    }

    public void moverParaAndar(int andarDestino) {
        System.out.println("Elevador " + id + " indo do andar " + andarAtual + " para o andar " + andarDestino);
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
}
