package algoritmos;

import java.util.ArrayList;
import java.util.Collections;
import modelo.Grafo;
import modelo.Edge;
import modelo.Node;
import thirdyparty.ComparaAresta;

public class Kruskal {

    private ArrayList<ArrayList<String>> listaPais = new ArrayList<ArrayList<String>>();
    private ArrayList<Edge> arestas;
    private ArrayList<Node> vertices;
    private ArrayList<String> verticesId = new ArrayList<String>();

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
        for (int i = 0; i < vertices.size(); i++) {
            String e = grafo.getNodes().get(i).getId();
            //System.out.println(e);
            verticesId.add(e);
        }

        ArrayList<Edge> arvore = new ArrayList<Edge>();

        for (int i = 0; i < vertices.size(); i++) {
            ArrayList<String> listaAux = new ArrayList<String>();
            listaAux.add(grafo.getNodes().get(i).getId());
            listaPais.add(listaAux);
        }

        ComparaAresta comparador = new ComparaAresta();

        arestas = (ArrayList<Edge>) grafo.getEdges().clone();
        Collections.sort(arestas, comparador);

        for (int i = 0; i < arestas.size(); i++) {

            if (comparaPais(arestas.get(i).getOrigem().getId(), arestas.get(i).getDestino().getId())) {
                arvore.add(arestas.get(i));
                unir(arestas.get(i).getOrigem(), arestas.get(i).getDestino());
                unir(arestas.get(i).getDestino(), arestas.get(i).getOrigem());
            }
        }

        return arestasParaArvore(arvore);
    }

    private boolean comparaPais(String origem, String destino) {
        return Collections.disjoint(pais(origem), pais(destino));
    }

    private ArrayList<String> pais(String idNo) {
        return listaPais.get(verticesId.indexOf(idNo));
    }

    private void unir(Node origem, Node destino) {
        int m = listaPais.get(verticesId.indexOf(destino.getId())).size();

        for (int i = 0; i < m; i++) {
            String x = listaPais.get(verticesId.indexOf(destino.getId())).get(i);
            if (!(listaPais.get(verticesId.indexOf(origem.getId())).contains(x))) {
                listaPais.get(verticesId.indexOf(origem.getId())).add(x);
            }
        }
    }

    private Grafo arestasParaArvore(ArrayList<Edge> arvore) {
        Grafo grafo = new Grafo(this.grafo.getId(), this.grafo.getEdgedefault(), this.vertices, arvore);
        return grafo;
    }

}
