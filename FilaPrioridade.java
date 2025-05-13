public class FilaPrioridade {
    private class Nodo {
        Pessoa pessoa;
        Nodo proximo;

        Nodo(Pessoa pessoa) {
            this.pessoa = pessoa;
            this.proximo = null;
        }
    }

    private Nodo inicio;

    public FilaPrioridade() {
        this.inicio = null;
    }

    public void adicionarPessoa(Pessoa pessoa) {
        Nodo novo = new Nodo(pessoa);
        if (inicio == null || inicio.pessoa.getPrioridade() > pessoa.getPrioridade()) {
            novo.proximo = inicio;
            inicio = novo;
        } else {
            Nodo atual = inicio;
            while (atual.proximo != null && atual.proximo.pessoa.getPrioridade() <= pessoa.getPrioridade()) {
                atual = atual.proximo;
            }
            novo.proximo = atual.proximo;
            atual.proximo = novo;
        }
    }

    public Pessoa removerPessoa() {
        if (inicio == null) return null;
        Pessoa pessoa = inicio.pessoa;
        inicio = inicio.proximo;
        return pessoa;
    }

    public boolean temPessoas() {
        return inicio != null;
    }
}
