package utfpr.model;

public class Municipio extends Localidade{
    private Uf uf;
    private String regiao;

    public Municipio(int id, String nome, Uf uf, String regiao){
        super(id, nome);
        this.regiao = regiao;
        this.uf = uf;
    }
    public String getRegiao() {
        return regiao;
    }

    public Uf getUf(){return this.uf;}
}
