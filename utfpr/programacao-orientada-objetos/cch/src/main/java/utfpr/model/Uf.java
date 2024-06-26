package utfpr.model;

public class Uf extends Localidade{
    private String sigla;
    public Uf(int id, String nome, String sigla){
        super(id, nome);
        this.sigla = sigla;
    }

    public String getSigla(){return this.sigla;}
}
