
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

    public Vertice addVertice(int id) {
        Vertice v = new Vertice(id);
        this.vertices.add(v);
        return v;
    }

    //Remover futuramente
    public Aresta addAresta(Vertice origem, Vertice destino) {
        Aresta e = new Aresta(origem, destino);
        origem.addAdj(e);
        this.arestas.add(e);
        return e;
    }

    //Nova implementacao do metodo
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

    public Vertice buscaVertice(int id) {
        for (Vertice u : this.vertices) {
            if (u.getId() == id) {
                System.out.println("Mesmo nome " + id);
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
            r += u.getId() + " -> ";

            for (Aresta e : u.getAdj()) {
                Vertice v = e.getDestino();
                r += v.getId() + ", ";
            }
            r += "\n";
        }
        return r;
    }

    public void criarVertice(int qt) {
        for (int i = 0; i < qt; i++) {
            Vertice v = this.addVertice(i);
        }
    }

    public void criarAresta(int idOrigem, int idDestino) {
        if (this.getVertices().isEmpty()) {
            System.out.println("Nao existem vertices");
        } else {
            Aresta aresta = this.addAresta(this.getVertices().get(idOrigem),
                    this.getVertices().get(idDestino));
        }
    }

    public String listarVertice() {
        String r = "";
        for (Vertice u : this.getVertices()) {
            r += "V: " + u.getId();
            r += "\n";
        }
        return r;
    }
}
