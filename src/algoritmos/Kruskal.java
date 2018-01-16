package algoritmos;

import java.util.ArrayList;
import java.util.Collections;
import modelo.Grafo;
import modelo.Edge;
import modelo.Node;
import thirdParty.ComparaAresta;

public class Kruskal {

    private ArrayList<ArrayList<String>> listaPais = new ArrayList<>();
    private ArrayList<Edge> arestas;
    private ArrayList<Node> vertices;
    private ArrayList<String> verticesId = new ArrayList<>();

    private Grafo grafo;

    public Kruskal(ArrayList<Edge> arestas, ArrayList<Node> vertices, Grafo grafo) {
        this.arestas = arestas;
        this.vertices = vertices;
        this.grafo = grafo;
    }

    public Kruskal() {
    }

    public ArrayList<Edge> getEdges() {
        return arestas;
    }

    public void setEdges(ArrayList<Edge> arestas) {
        this.arestas = arestas;
    }

    public ArrayList<Node> getNodes() {
        return vertices;
    }

    public void setNodes(ArrayList<Node> vertices) {
        this.vertices = vertices;
    }

    public Grafo getKruskal() {
        //Varre a lista de vertices e armazena os ids na lista de verticesId
        for (int i = 0; i < this.vertices.size(); i++) {
            String e = this.grafo.getNodes().get(i).getId();
            //System.out.println(e);
            this.verticesId.add(e);
        }

        ArrayList<Edge> arvoreAresta = new ArrayList<>();

        for (int i = 0; i < this.vertices.size(); i++) {
            ArrayList<String> listaAux = new ArrayList<>();
            listaAux.add(grafo.getNodes().get(i).getId());
            this.listaPais.add(listaAux);
        }

        ComparaAresta comparador = new ComparaAresta();

        this.arestas = (ArrayList<Edge>) this.grafo.getEdges().clone();
        Collections.sort(this.arestas, comparador);

        for (int i = 0; i < this.arestas.size(); i++) {

            if (comparaPais(this.arestas.get(i).getOrigem().getId(), this.arestas.get(i).getDestino().getId())) {
                arvoreAresta.add(this.arestas.get(i));
                unirNos(this.arestas.get(i).getOrigem(), this.arestas.get(i).getDestino());
                unirNos(this.arestas.get(i).getDestino(), this.arestas.get(i).getOrigem());
            }
        }

        return arestasParaArvore(arvoreAresta);
    }

    private boolean comparaPais(String origem, String destino) {
        return Collections.disjoint(Nopai(origem), Nopai(destino));
    }

    private ArrayList<String> Nopai(String idNo) {
        return this.listaPais.get(this.verticesId.indexOf(idNo));
    }

    private void unirNos(Node origem, Node destino) {
        int tamanhoLista = this.listaPais.get(this.verticesId.indexOf(destino.getId())).size();

        for (int i = 0; i < tamanhoLista; i++) {
            String x = this.listaPais.get(this.verticesId.indexOf(destino.getId())).get(i);
            if (!(this.listaPais.get(this.verticesId.indexOf(origem.getId())).contains(x))) {
                this.listaPais.get(this.verticesId.indexOf(origem.getId())).add(x);
            }
        }
    }

    private Grafo arestasParaArvore(ArrayList<Edge> arvore) {
        Grafo arvoreMinima = new Grafo(this.grafo.getId(), this.grafo.getEdgedefault(), this.vertices, arvore);
        return arvoreMinima;
    }

}
