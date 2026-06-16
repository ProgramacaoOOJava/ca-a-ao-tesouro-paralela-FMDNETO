import java.util.concurrent.Callable;

/**
 * Explorador saqueador focado em recuperação rápida de artefatos.
 */
public class Saqueador extends Explorador implements Callable<Double> {

    public Saqueador(String nome, int nivel, int energia, Missao tarefa) {
        super(nome, "Saqueador", nivel, energia, tarefa);
    }

    @Override
    public Double executarMissao() {
        double pontos = getTarefa().getDificuldade() * 22.0 + getNivel() * 8.0 + getEnergia() * 0.7;
        System.out.println("Status: Recuperando artefatos valiosos em alta pressão.");
        return pontos;
    }

    @Override
    public Double call() {
        exibirStatus();
        Double pontos = executarMissao();
        System.out.println("Pontos obtidos: " + pontos);
        System.out.println();
        return pontos;
    }
}