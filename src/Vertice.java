
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author straby
 */
public class Vertice {

    private String id;
    private List<Aresta> adj;

    Vertice(String id) {
        this.id = id;
        this.adj = new ArrayList<Aresta>();
    }

    public Vertice() {

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

    public void setAdj(List<Aresta> adj) {
        this.adj = adj;
    }

}
