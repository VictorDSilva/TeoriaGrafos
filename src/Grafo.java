
import java.util.ArrayList;

/**
 *
 * @author straby
 */
public class Grafo {
    private ArrayList<Node> node;
    private ArrayList<Aresta> aresta;

    public Grafo() {
    }

    public Grafo(ArrayList<Node> node, ArrayList<Aresta> aresta) {
        this.node = node;
        this.aresta = aresta;
    }
    
    public ArrayList<Node> getNode() {
        return node;
    }

    public void setNode(ArrayList<Node> node) {
        this.node = node;
    }

    public ArrayList<Aresta> getAresta() {
        return aresta;
    }

    public void setAresta(ArrayList<Aresta> aresta) {
        this.aresta = aresta;
    }
    
    
    
}
