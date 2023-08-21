package utfpr.model;

public abstract class Localidade {
    private int id;
    private String nome;

    public Localidade(int id, String nome) {
        this.id = id;
        this.nome = nome;
    }

    public int getId() {
        return id;
    }

    public String getNome() {
        return nome;
    }
}
