
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author straby
 */
public class Vertice {

    private String nome;
    private List<Aresta> adj;

    Vertice(String nome) {
        this.nome = nome;
        this.adj = new ArrayList<Aresta>();
    }

    public Vertice() {
    
    }    

    void addAdj(Aresta e) {
        adj.add(e);
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public List<Aresta> getAdj() {
        return adj;
    }

    public void setAdj(List<Aresta> adj) {
        this.adj = adj;
    }


}
