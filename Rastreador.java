import java.util.concurrent.Callable;

/**
 * Explorador rastreador focado em reconhecimento detalhado do terreno.
 */
public class Rastreador extends Explorador implements Callable<Double> {

    public Rastreador(String nome, int nivel, int energia, Missao tarefa) {
        super(nome, "Rastreador", nivel, energia, tarefa);
    }

    @Override
    public Double executarMissao() {
        double pontos = getTarefa().getDificuldade() * 18.0 + getNivel() * 10.0 + getEnergia() * 0.5;
        System.out.println("Status: Rastreando caminhos e mapeando áreas ocultas.");
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