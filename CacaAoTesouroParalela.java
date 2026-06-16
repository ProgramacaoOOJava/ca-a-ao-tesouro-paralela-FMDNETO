import java.util.ArrayList;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.ForkJoinPool;
import java.util.concurrent.Future;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

/**
 * Classe principal que simula a Caça ao Tesouro Paralela.
 * Demonstra o uso de ExecutorService, Callable, ScheduledExecutorService e Fork/Join.
 */
public class CacaAoTesouroParalela {

    public static void main(String[] args) {
        System.out.println("=== NÍVEL MESTRE: CAÇA AO TESOURO PARALELA ===\n");

        Missao missao1 = new Missao("Mapear cavernas", "Caverna Norte", 7);
        Missao missao2 = new Missao("Recuperar artefatos", "Ruínas Sombrias", 8);
        Missao missao3 = new Missao("Inspecionar trilhas", "Floresta Nebulosa", 6);
        Missao missao4 = new Missao("Coletar relíquias", "Templo Antigo", 9);

        ArrayList<Explorador> exploradores = new ArrayList<>();
        exploradores.add(new Rastreador("Lina", 7, 90, missao1));
        exploradores.add(new Saqueador("Drogan", 6, 85, missao2));
        exploradores.add(new Rastreador("Iris", 5, 88, missao3));
        exploradores.add(new Saqueador("Kael", 8, 92, missao4));

        ExecutorService executor = Executors.newFixedThreadPool(2);
        ScheduledExecutorService agendador = Executors.newScheduledThreadPool(1);

        // Mensagem periódica para indicar monitoramento da operação paralela.
        agendador.scheduleAtFixedRate(
            () -> System.out.println("[Monitor] Missões em processamento..."),
            0,
            1,
            TimeUnit.SECONDS
        );

        ArrayList<Future<Double>> futuros = new ArrayList<>();
        for (Explorador explorador : exploradores) {
            futuros.add(executor.submit(() -> {
                explorador.exibirStatus();
                Double pontos = explorador.executarMissao();
                System.out.println("Pontos obtidos: " + pontos);
                System.out.println();
                return pontos;
            }));
        }

        double[] pontos = new double[exploradores.size()];
        for (int i = 0; i < futuros.size(); i++) {
            try {
                Double resultado = futuros.get(i).get();
                pontos[i] = resultado;
                System.out.println("Resultado confirmado de " + exploradores.get(i).getNome() + ": " + resultado);
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
                System.out.println("Execução interrompida ao obter resultado de missão.");
            } catch (ExecutionException e) {
                System.out.println("Falha ao executar missão: " + e.getMessage());
            }
        }

        executor.shutdown();
        agendador.shutdown();

        ForkJoinPool pool = new ForkJoinPool();
        SomaPontos tarefaSoma = new SomaPontos(pontos, 0, pontos.length);
        Double somaTotal = pool.invoke(tarefaSoma);
        pool.shutdown();

        System.out.println("\nSoma total dos pontos: " + somaTotal);
    }
}

