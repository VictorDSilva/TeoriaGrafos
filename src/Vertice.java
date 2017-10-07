
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author straby
 */
public class Vertice {

<<<<<<< HEAD
    private String nome;
    private List<Aresta> adj;

    Vertice(String nome) {
        this.nome = nome;
=======
    private int id;
    private List<Aresta> adj;

    Vertice(int id) {
        this.id = id;
>>>>>>> e567f4f0a7c76d25411caa31568a621cad793fce
        this.adj = new ArrayList<Aresta>();
    }

    public Vertice() {
<<<<<<< HEAD
    
    }    

    void addAdj(Aresta e) {
        adj.add(e);
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
=======

    }

    void addAdj(Aresta e) {//add aresta Adjacente
        adj.add(e);
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
>>>>>>> e567f4f0a7c76d25411caa31568a621cad793fce
    }

    public List<Aresta> getAdj() {
        return adj;
    }

    public void setAdj(List<Aresta> adj) {
        this.adj = adj;
    }

<<<<<<< HEAD

=======
>>>>>>> e567f4f0a7c76d25411caa31568a621cad793fce
}
