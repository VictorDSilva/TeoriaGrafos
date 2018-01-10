package Modelos;

import com.thoughtworks.xstream.annotations.XStreamAsAttribute;
import java.util.ArrayList;
import java.util.List;


public class Vertice {
    
    @XStreamAsAttribute
    private String id;
    
    private ArrayList<Aresta> adj;

    public Vertice(String id) {
        this.id = id;
        this.adj = new ArrayList<Aresta>();
    }


    void addAdj(Aresta e) {//add aresta Adjacente
        adj.add(e);
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public List<Aresta> getAdj() {
        return adj;
    }

    public void setAdj(ArrayList<Aresta> adj) {
        this.adj = adj;
    }

}
