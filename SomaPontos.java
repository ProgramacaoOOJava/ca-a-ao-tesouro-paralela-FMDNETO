import java.util.concurrent.RecursiveTask;

/**
 * Tarefa Fork/Join para soma paralela dos pontos dos exploradores.
 */
public class SomaPontos extends RecursiveTask<Double> {
    private double[] pontos;
    private int inicio;
    private int fim;

    public SomaPontos(double[] pontos, int inicio, int fim) {
        this.pontos = pontos;
        this.inicio = inicio;
        this.fim = fim;
    }

    @Override
    protected Double compute() {
        if (fim - inicio <= 2) {
            double somaDireta = 0.0;
            for (int i = inicio; i < fim; i++) {
                somaDireta += pontos[i];
            }
            return somaDireta;
        }

        int meio = (inicio + fim) / 2;
        SomaPontos esquerda = new SomaPontos(pontos, inicio, meio);
        SomaPontos direita = new SomaPontos(pontos, meio, fim);

        esquerda.fork();
        double somaDireita = direita.compute();
        double somaEsquerda = esquerda.join();
        return somaEsquerda + somaDireita;
    }
}