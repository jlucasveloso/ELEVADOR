public class PainelControle {
    private boolean[] botoes;

    public PainelControle() {
        this.botoes = new boolean[10]; // 10 andares
    }

    public void selecionarAndar(int andar) {
        if (andar >= 0 && andar < botoes.length) {
            botoes[andar] = true;
            System.out.println("[LOG] Painel: Andar " + andar + " selecionado.");
        }
    }

    public void resetarAndar(int andar) {
        if (andar >= 0 && andar < botoes.length) {
            botoes[andar] = false;
            System.out.println("[LOG] Painel: Andar " + andar + " resetado.");
        }
    }

    public boolean isAndarSelecionado(int andar) {
        return andar >= 0 && andar < botoes.length && botoes[andar];
    }

    public void exibirBotoesAtivos() {
        System.out.print("[LOG] Andares selecionados: ");
        boolean algumSelecionado = false;
        for (int i = 0; i < botoes.length; i++) {
            if (botoes[i]) {
                System.out.print(i + " ");
                algumSelecionado = true;
            }
        }
        if (!algumSelecionado) {
            System.out.print("nenhum");
        }
        System.out.println();
    }
}
