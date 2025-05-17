public class FilaPrioridade {
    private Pessoa[] fila;
    private int tamanho;

    public FilaPrioridade(int capacidade) {
        fila = new Pessoa[capacidade];
        tamanho = 0;
    }

    public void adicionarPessoa(Pessoa pessoa) {
        if (tamanho == fila.length) {
            System.out.println("[LOG] Fila cheia! Pessoa não adicionada: " + pessoa.getNome());
            return;
        }
        fila[tamanho] = pessoa;
        tamanho++;
        ordenarFila();
        System.out.println("[LOG] Pessoa adicionada à fila de prioridade: " + pessoa.getNome() + " (Prioridade " + pessoa.getPrioridade() + ")");
    }

    public Pessoa removerPessoa() {
        if (tamanho == 0) return null;
        Pessoa pessoa = fila[0];
        for (int i = 1; i < tamanho; i++) {
            fila[i - 1] = fila[i];
        }
        tamanho--;
        System.out.println("[LOG] Pessoa removida da fila de prioridade: " + pessoa.getNome());
        return pessoa;
    }

    public boolean temPessoas() {
        return tamanho > 0;
    }

    private void ordenarFila() {
        for (int i = 0; i < tamanho - 1; i++) {
            for (int j = i + 1; j < tamanho; j++) {
                if (fila[i].getPrioridade() > fila[j].getPrioridade()) {
                    Pessoa temp = fila[i];
                    fila[i] = fila[j];
                    fila[j] = temp;
                }
            }
        }
    }

    public boolean estaVazia() {
        return tamanho == 0;
    }
}
