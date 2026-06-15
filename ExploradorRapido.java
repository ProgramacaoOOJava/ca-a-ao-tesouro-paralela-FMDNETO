/**
 * Explorador rápido que executa tarefas com alta velocidade e eficiência.
 * Implementa Runnable para execução em thread separada.
 */
public class ExploradorRapido extends Explorador implements Runnable {
    
    // * Construtor do explorador rápido.
    public ExploradorRapido() {
        this("Explorador Rápido", "Explorar rapidamente");
    }

    public ExploradorRapido(String nome, String tarefa) {
        super(nome, "Rápido", Thread.MAX_PRIORITY, tarefa);
    }
    
    /**
     * Implementação específica da execução de tarefa para exploradores rápidos.
     * Exploradores rápidos executam tarefas com maior agilidade.
     * @throws TarefaInvalidaException Se a tarefa for nula ou vazia
     */
    @Override
    public void executarTarefa() throws TarefaInvalidaException {
        if (!tarefaValida()) {
            throw new TarefaInvalidaException("Tarefa inválida para " + getNome());
        }
        System.out.println(getNome() + " está executando rapidamente: " + getTarefa());
    }
    
    /**
     * Método run() executado quando a thread é iniciada.
     * Trata exceções e chama executarTarefa().
     */
    @Override
    public void run() {
        try {
            exibirStatus();
            executarTarefa();
        } catch (TarefaInvalidaException e) {
            System.out.println("Erro: " + e.getMessage());
        }
    }
}

