package app.melhoroftheworld.agenda.tarefa;

public class Tarefa {

    private int id;
    private String nome;
    private String descricao;
    private String periodo;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    public String getPeriodo() {
        return periodo;
    }

    public void setPeriodo(String recorrencia) {
        this.periodo = recorrencia;
    }
}
