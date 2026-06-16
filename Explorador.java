/**
 * Classe abstrata que representa um explorador na Caça ao Tesouro Paralela.
 * Define a estrutura básica para diferentes tipos de exploradores.
 */
public abstract class Explorador {
    private String nome;
    private String especialidade;
    private int nivel;
    private int energia;
    private Missao tarefa;

    // Construtor que inicializa todos os atributos do explorador.
    public Explorador(String nome, String especialidade, int nivel, int energia, Missao tarefa) {
        this.nome = nome;
        this.especialidade = especialidade;
        this.nivel = nivel;
        this.energia = energia;
        this.tarefa = tarefa;
    }

    /**
     * Método abstrato que deve ser implementado pelas subclasses.
     * Define como cada tipo de explorador executa sua tarefa.
     */
    public abstract Double executarMissao();

    /**
     * Exibe o status completo do explorador com formatação clara.
     */
    public void exibirStatus() {
        System.out.println("Explorador: " + nome);
        System.out.println("Especialidade: " + especialidade);
        System.out.println("Nível: " + nivel);
        System.out.println("Energia: " + energia);
        System.out.println(
            "Missão: " + tarefa.getDescricao() +
            " (Local: " + tarefa.getLocal() + ", Dificuldade " + tarefa.getDificuldade() + ")"
        );
    }

    // Getters para acesso aos atributos encapsulados
    public String getNome() {
        return nome;
    }

    public String getEspecialidade() {
        return especialidade;
    }

    public int getNivel() {
        return nivel;
    }

    public int getEnergia() {
        return energia;
    }

    public Missao getTarefa() {
        return tarefa;
    }
}

