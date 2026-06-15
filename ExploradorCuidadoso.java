/**
 * Explorador cuidadoso que executa tarefas com precisão e atenção aos detalhes.
 * Implementa Runnable para execução em thread separada.
 */
public class ExploradorCuidadoso extends Explorador implements Runnable {
    
    // * Construtor do explorador cuidadoso.
    public ExploradorCuidadoso() {
        this("Explorador Cuidadoso", "Explorar com atenção aos detalhes");
    }

    public ExploradorCuidadoso(String nome, String tarefa) {
        super(nome, "Cuidadoso", Thread.MIN_PRIORITY, tarefa);
    }
    
    /**
     * Implementação específica da execução de tarefa para exploradores cuidadosos.
     * Exploradores cuidadosos executam tarefas com mais cautela e precisão.
     * @throws TarefaInvalidaException Se a tarefa for nula ou vazia
     */
    @Override
    public void executarTarefa() throws TarefaInvalidaException {
        if (!tarefaValida()) {
            throw new TarefaInvalidaException("Tarefa inválida para " + getNome());
        }
        System.out.println(getNome() + " está executando com cuidado: " + getTarefa());
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

