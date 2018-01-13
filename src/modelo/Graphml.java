package modelo;

import java.util.ArrayList;
import thirdyparty.Chaves;

public class Graphml {

    private ArrayList<Chaves> keys;
    private Grafo graph;

    public Graphml(Grafo graph, ArrayList<Chaves> keys) {
        this.graph = graph;
        this.keys = keys;
    }

    public Graphml(Grafo graph) {
        this.graph = graph;
    }

    public Graphml() {
        this.keys = new ArrayList<Chaves>();
        this.graph = new Grafo();
    }

    public ArrayList<Chaves> getKeys() {
        return keys;
    }

    public void setKeys(ArrayList<Chaves> keys) {
        this.keys = keys;
    }

    public Grafo getGraph() {
        return graph;
    }

    public void setGraph(Grafo graph) {
        this.graph = graph;
    }

    public void addicionarKeys(Chaves key) {
        this.keys.add(key);
    }

    public void removerKeys(Chaves key) {
        this.keys.remove(key);
    }

}
