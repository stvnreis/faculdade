package utfpr.model;

import java.util.ArrayList;

public class Ufs{
    private ArrayList<Uf> ufs;

    public Ufs(){ufs = new ArrayList<>();}

    public void addUf(Uf newuf){
        for(Uf uf: this.ufs){
            if(uf.getId() == newuf.getId())
               return;
        }
        this.ufs.add(newuf);
    }

    public ArrayList<Uf> getUfs(){return this.ufs;}
}
