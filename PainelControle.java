public class PainelControle {
    private boolean[] botoes;

    public PainelControle() {
        this.botoes = new boolean[10]; // Supondo 10 andares
    }

    public void selecionarAndar(int andar) {
        if (andar >= 0 && andar < botoes.length) {
            botoes[andar] = true;
        }
    }

    public boolean isAndarSelecionado(int andar) {
        if (andar >= 0 && andar < botoes.length) {
            return botoes[andar];
        }
        return false;
    }

    public void resetarAndar(int andar) {
        if (andar >= 0 && andar < botoes.length) {
            botoes[andar] = false;
        }
    }
}
