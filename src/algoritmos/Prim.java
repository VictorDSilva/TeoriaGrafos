package algoritmos;

import java.util.ArrayList;
import java.util.Collections;
import modelo.Edge;
import modelo.Grafo;
import modelo.Node;
import thirdyparty.ComparaAresta;

public class Prim {

    private ArrayList<Edge> arestas;
    private ArrayList<Edge> arestasAdicionadas;
    private ArrayList<Node> nos;
    private ArrayList<Node> nosAdicionados;
    private ArrayList<String> nosIdAdicionados;
    private ArrayList<String> nosId;

    private Grafo grafo;

    public Prim() {
        this.arestas = new ArrayList();
        this.arestasAdicionadas = new ArrayList();
        this.nos = new ArrayList();
        this.nosAdicionados = new ArrayList();
        this.nosIdAdicionados = new ArrayList();
        this.nosId = new ArrayList();
    }

    public Grafo getPrim(Grafo grafoml) {
        this.grafo = grafoml;
        this.nos = grafo.getNodes();
        for (int i = 0; i < nos.size(); i++) {
            this.nosId.add(grafo.getNodes().get(i).getId());
        }

        ComparaAresta comparador = new ComparaAresta();
        this.arestas = (ArrayList<Edge>) grafo.getEdges().clone();
        Collections.sort(arestas, comparador);

        this.nosAdicionados.add(nos.get(0));
        this.nosIdAdicionados.add(nos.get(0).getId());

        while (this.nosAdicionados.size() != this.nos.size()) {
            for (int j = 0; j < arestas.size(); j++) {
                // nosAdicionados.add(nos.get(i));
                if (nosIdAdicionados.contains(arestas.get(j).getOrigem().getId())
                        && !nosIdAdicionados.contains(arestas.get(j).getDestino().getId())) {
                    this.arestasAdicionadas.add(arestas.get(j));
                    this.nosAdicionados.add(arestas.get(j).getDestino());
                    this.nosIdAdicionados.add(arestas.get(j).getDestino().getId());
                    this.arestas.remove(j);
                    break;
                }
                if (nosIdAdicionados.contains(arestas.get(j).getDestino().getId())
                        && !nosIdAdicionados.contains(arestas.get(j).getOrigem().getId())) {
                    this.arestasAdicionadas.add(arestas.get(j));
                    this.nosAdicionados.add(arestas.get(j).getOrigem());
                    this.nosIdAdicionados.add(arestas.get(j).getOrigem().getId());
                    this.arestas.remove(j);
                    break;
                }
            }
        }
        Grafo grafoPrim = new Grafo(grafoml.getId(), grafoml.getEdgedefault(), nos, arestasAdicionadas);
        return grafoPrim;
    }
}
