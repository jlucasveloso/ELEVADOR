public class Main {
    public static void main(String[] args) {
        Configuracao config = new Configuracao();
        ControladorElevadores controlador = new ControladorElevadores(config);

        System.out.println("=== Início da Simulação ===");

        // Simula 24 horas, minuto a minuto (24 * 60 = 1440 ciclos)
        for (int i = 0; i < 24 * 60; i++) {
            int hora = controlador.getHorario().getHoraAtual();
            int minuto = controlador.getHorario().getMinutoAtual();

            System.out.printf("\n--- Ciclo %d - Horário: %02d:%02d ---\n", i + 1, hora, minuto);

            controlador.simularCiclo();

            // Descomente para visualizar mais devagar:
            // try {
            //     Thread.sleep(50);
            // } catch (InterruptedException e) {
            //     e.printStackTrace();
            // }
        }

        System.out.println("\n=== Fim da Simulação ===");
        controlador.imprimirResumo();
    }
}
