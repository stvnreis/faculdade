package utfpr.model;

import java.util.ArrayList;

public class Municipios{
    private ArrayList<Municipio> municipios;

    public Municipios(){municipios = new ArrayList<>();}

    public void add(Municipio newmunicipio){
        for(Municipio municipio: this.municipios){
            if(municipio.getId() == newmunicipio.getId())
                return;
        }
        this.municipios.add(newmunicipio);
    }

    public ArrayList<Municipio> getMunicipios(){return this.municipios;}

    public Municipios getMunicipiosByUfName(String nome){
        Municipios municipiosByUf = new Municipios();
        for(Municipio m : this.municipios){
            if(m.getUf().getNome().equals(nome))
                municipiosByUf.add(m);
        }
        if(municipiosByUf.getMunicipios().isEmpty())
            return null;
        else
            return municipiosByUf;
    }

    public Municipio getMunicipioAt(int index){
        return this.municipios.get(index);
    }
}
