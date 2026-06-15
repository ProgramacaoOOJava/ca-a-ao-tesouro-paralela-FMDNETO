import java.util.ArrayList;

/**
 * Classe principal que simula a Caça ao Tesouro Paralela.
 * Demonstra o uso de threads, prioridades, tipos (user e daemon) e exceções personalizadas
 * através de diferentes tipos de exploradores.
 */
public class CacaAoTesouroParalela {
    
    public static void main(String[] args) {
        System.out.println("=== CAÇA AO TESOURO PARALELA ===");
        System.out.println("Exploradores em ação: threads, prioridades e exceções em Java\n");
        
        // Lista para gerenciar as threads dos exploradores
        ArrayList<Thread> threads = new ArrayList<>();

        
        // Criando exploradores rápidos
        ExploradorRapido rapido1 = new ExploradorRapido("Alice", "Vasculhar a caverna");
        ExploradorRapido rapido2 = new ExploradorRapido("Clara", "Encontrar a relíquia");


        // Criando exploradores cuidadosos
        ExploradorCuidadoso cuidadoso1 = new ExploradorCuidadoso("Bob", "Mapear a floresta");
        ExploradorCuidadoso cuidadoso2 = new ExploradorCuidadoso("Diana", "Registrar pegadas");


        // Criando um explorador com tarefa inválida para demonstrar exceção
        ExploradorRapido rapido3 = new ExploradorRapido("Clara", "");

        // Encapsulando exploradores em threads
        Thread thread1 = new Thread(rapido1);
        Thread thread2 = new Thread(rapido2);
        Thread thread3 = new Thread(cuidadoso1);
        Thread thread4 = new Thread(cuidadoso2);
        Thread thread5 = new Thread(rapido3);

        thread1.setName(rapido1.getNome());
        thread2.setName(rapido2.getNome());
        thread3.setName(cuidadoso1.getNome());
        thread4.setName(cuidadoso2.getNome());
        thread5.setName(rapido3.getNome());

        // Configurando prioridades das threads
        thread1.setPriority(Thread.MAX_PRIORITY);
        thread2.setPriority(Thread.MAX_PRIORITY);
        thread3.setPriority(Thread.MIN_PRIORITY);
        thread4.setPriority(Thread.MIN_PRIORITY);
        thread5.setPriority(Thread.MAX_PRIORITY);

        // Configurando algumas threads como daemon (tarefas secundárias)
        thread1.setDaemon(false); // Explorer principal
        thread2.setDaemon(false); // Explorer principal
        thread3.setDaemon(true);  // Explorer secundário
        thread4.setDaemon(false); // Explorer principal
        thread5.setDaemon(false); // Explorer principal

        // Adicionando threads à lista
        threads.add(thread1);
        threads.add(thread2);
        threads.add(thread3);
        threads.add(thread4);
        threads.add(thread5);

        // Exibindo informações das threads antes da execução
        System.out.println("\n=== INFORMAÇÕES DAS THREADS ===");
        for (Thread thread : threads) {
            System.out.println("Thread: " + thread.getName() + 
                               " | Prioridade: " + thread.getPriority() + 
                               " | Daemon: " + thread.isDaemon());
        }        
        
        // Iniciando todas as threads
        System.out.println("\n=== INICIANDO EXPLORAÇÃO ===");
        for (Thread thread : threads) {
            thread.start();
        }

        System.out.println("\n=== ESTADO IMEDIATO DAS THREADS ===");
        for (Thread thread : threads) {
            System.out.println("Thread: " + thread.getName() + " | Estado: " + thread.getState());
        }

        // Aguardando conclusão das threads não-daemon
        System.out.println("\n=== AGUARDANDO CONCLUSÃO DOS EXPLORADORES ===");
        for (Thread thread : threads) {
            if (!thread.isDaemon()) {
                try {
                    thread.join();
                } catch (InterruptedException e) {
                    System.out.println("Thread interrompida: " + e.getMessage());
                }
            }
        }

        // Verificando estado final das threads
        System.out.println("\n=== ESTADO FINAL DAS THREADS ===");
        for (Thread thread : threads) {
            System.out.println("Thread: " + thread.getName() + 
                               " | Estado: " + thread.getState() + 
                               " | Daemon: " + thread.isDaemon());
        }

        System.out.println("\n=== CAÇA AO TESOURO PARALELA FINALIZADA ===");
        System.out.println("Todos os exploradores principais completaram suas missões!");
    }
}

