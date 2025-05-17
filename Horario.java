public class Horario {
    private int horaAtual;
    private int minutoAtual;
    private Intervalo[] intervalosPico;

    public Horario() {
        this.horaAtual = 0;
        this.minutoAtual = 0;
        this.intervalosPico = new Intervalo[] {
            new Intervalo(7, 9),
            new Intervalo(17, 19)
        };
    }

    public void avancarMinuto() {
        minutoAtual++;
        if (minutoAtual == 60) {
            minutoAtual = 0;
            horaAtual = (horaAtual + 1) % 24;
        }
    }

    public boolean isHorarioPico() {
        for (Intervalo intervalo : intervalosPico) {
            if (horaAtual >= intervalo.inicio && horaAtual <= intervalo.fim) {
                return true;
            }
        }
        return false;
    }

    public int getHoraAtual() {
        return horaAtual;
    }

    public int getMinutoAtual() {
        return minutoAtual;
    }

    public static class Intervalo {
        int inicio;
        int fim;

        public Intervalo(int inicio, int fim) {
            this.inicio = inicio;
            this.fim = fim;
        }
    }
}
