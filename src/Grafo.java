
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author straby
 */
public class Grafo {

    private List<Vertice> vertices;
    private List<Aresta> arestas;

    public Grafo() {
        this.vertices = new ArrayList<Vertice>();
        this.arestas = new ArrayList<Aresta>();
    }

    public List<Vertice> getVertices() {
        return vertices;
    }

    public void setVertices(List<Vertice> vertices) {
        this.vertices = vertices;
    }

    public List<Aresta> getArestas() {
        return arestas;
    }

    public void setArestas(List<Aresta> arestas) {
        this.arestas = arestas;
    }

    public Vertice addVertice(String nome) {
        Vertice v = new Vertice(nome);
        this.vertices.add(v);
        return v;
    }

    public Aresta addAresta(Vertice origem, Vertice destino) {
        Aresta e = new Aresta(origem, destino);
        origem.addAdj(e);
        this.arestas.add(e);
        return e;
    }

    public Aresta addAresta(Vertice origem, Vertice destino, boolean idaVolta) {
        if (idaVolta == true) {
            Aresta e = new Aresta(origem, destino, idaVolta);
            origem.addAdj(e);
            this.addAresta(destino, origem);
            this.arestas.add(e);
            return e;
        } else {
            Aresta e = new Aresta(origem, destino, idaVolta);
            origem.addAdj(e);
            this.arestas.add(e);
            return e;
        }
    }

    public Vertice buscaVertice(String nome) {
        for (Vertice u : this.vertices) {
            if (u.getNome() == nome) {
                System.out.println("Mesmo nome " + nome);
                return u;
            } else {
                return null;
            }
        }
        return null;
    }

    @Override
    public String toString() {
        String r = "";

        for (Vertice u : this.vertices) {
            r += u.getNome() + " -> ";

            for (Aresta e : u.getAdj()) {
                Vertice v = e.getDestino();
                r += v.getNome() + ", ";
            }
            r += "\n";
        }
        return r;
    }

}
