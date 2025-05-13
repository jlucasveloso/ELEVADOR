public class PainelControle {
    private boolean[] botoes;

    public PainelControle() {
        this.botoes = new boolean[10];
    }

    public void selecionarAndar(int andar) {
        if (andar >= 0 && andar < botoes.length) {
            botoes[andar] = true;
        }
    }

    public void resetarAndar(int andar) {
        if (andar >= 0 && andar < botoes.length) {
            botoes[andar] = false;
        }
    }

    public boolean isAndarSelecionado(int andar) {
        return andar >= 0 && andar < botoes.length && botoes[andar];
    }
}
