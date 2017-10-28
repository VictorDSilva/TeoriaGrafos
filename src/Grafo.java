
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author straby
 */
public class Grafo {

    private List<Vertice> vertices;
    private List<Aresta> arestas;
    private boolean ordenacao;

    public boolean isOrdenacao() {
        return ordenacao;
    }

    public void setOrdenacao(boolean ordenacao) {
        this.ordenacao = ordenacao;
    }

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

    public void gravarXML() {
        try {
            FileWriter arquivo = new FileWriter("grafo.xml");
            PrintWriter gravarArquivo = new PrintWriter(arquivo);

            gravarArquivo.printf("<?xml version=\"1.0\" encoding=\"UTF-8\"?>\n");
            gravarArquivo.printf("<graphml xmlns=\"http://graphml.graphdrawing.org/xmlns\"  \n"
                    + "    xmlns:xsi=\"http://www.w3.org/2001/XMLSchema-instance\"\n"
                    + "    xsi:schemaLocation=\"http://graphml.graphdrawing.org/xmlns\n"
                    + "     http://graphml.graphdrawing.org/xmlns/1.0/graphml.xsd\">\n");

            gravarArquivo.printf("  <graph id='1' edgedefault='direcao'>\n");

            for (Vertice v : this.getVertices()) {
                gravarArquivo.printf("      <node id='" + v.getId() + "'/>\n");
            }

            for (Aresta aresta : this.getArestas()) {
                gravarArquivo.printf("      <edge source='" + aresta.getOrigem().getId() + "' target='" + aresta.getDestino().getId() + "'/>\n");
            }

            gravarArquivo.printf("  </graph>\n");
            gravarArquivo.printf("</graphml>");

            arquivo.close();
        } catch (IOException ex) {
            System.out.println("Erro ao gerar XML!");
        }
    }

    public void lerXML(String caminhoArquivo) {
        try {
            BufferedReader arquivo = new BufferedReader(new FileReader(caminhoArquivo));

            while (arquivo.ready()) {
                System.out.println(arquivo.readLine());
            }
        } catch (IOException ex) {
            System.out.println("Erro ao ler XML!");
        }
    }

    public void infoGrafo() {

        System.out.println(" A ordem do grafo é: " + getVertices().size());

        for (int i = 0; i <= getArestas().size() - 1; i++) {
            System.out.println("Os vertices: " + getArestas().get(i).getOrigem() + " e " + getArestas().get(i).getDestino() + " são incidentes a aresta: " + getArestas().get(i).getNome());
        }

        int x = 0;
        int y = 0;
        if (isOrdenacao()) {
            for (int i = 0; i <= getVertices().size() - 1; i++) {
                int vertice = getVertices().get(i).getId();
                for (int j = 0; j <= getArestas().size() - 1; j++) {
                    if (vertice.equals(getArestas().get(j).getOrigem()) || vertice.equals(getArestas().get(j).getDestino())) {
                        x++;
                    }
                }
                System.out.println("Vertice: " + getVertices().get(i).getId() + ": " + x);
                x = 0;
            }
        } else {
            for (int i = 0; i <= getVertices().size() - 1; i++) {
                int vertice = getVertices().get(i).getId();
                for (int j = 0; j <= getArestas().size() - 1; j++) {
                    if (vertice.equals(getArestas().get(i).getOrigem())) {
                        x++;
                    }
                    if (vertice.equals(getArestas().get(j).getDestino())) {
                        y++;
                    }
                }
                System.out.println("Vertice: " + getVertices().get(i).getId() + " Grau de Emissão: " + x);
                System.out.println("Vertice: " + getVertices().get(i).getId() + " Grau de Recepção: " + y);
                x = 0;
                y = 0;
            }
        }

    }
}
