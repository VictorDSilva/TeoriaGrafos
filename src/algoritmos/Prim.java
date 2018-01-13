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

    private Grafo graphml;

    public Prim() {
        this.arestas = new ArrayList();
        this.arestasAdicionadas = new ArrayList();
        this.nos = new ArrayList();
        this.nosAdicionados = new ArrayList();
        this.nosIdAdicionados = new ArrayList();
        this.nosId = new ArrayList();
    }

    public Grafo getPrim(Grafo grafoml) {
        graphml = grafoml;
        nos = graphml.getNodes();
        for (int i = 0; i < nos.size(); i++) {
            nosId.add(graphml.getNodes().get(i).getId());
        }

        ComparaAresta c = new ComparaAresta();
        arestas = (ArrayList<Edge>) graphml.getEdges().clone();
        Collections.sort(arestas, c);

        nosAdicionados.add(nos.get(0));
        nosIdAdicionados.add(nos.get(0).getId());

        while (nosAdicionados.size() != nos.size()) {
            for (int j = 0; j < arestas.size(); j++) {
                // nosAdicionados.add(nos.get(i));
                if (nosIdAdicionados.contains(arestas.get(j).getOrigem().getId())
                        && !nosIdAdicionados.contains(arestas.get(j).getDestino().getId())) {
                    arestasAdicionadas.add(arestas.get(j));
                    nosAdicionados.add(arestas.get(j).getDestino());
                    nosIdAdicionados.add(arestas.get(j).getDestino().getId());
                    arestas.remove(j);
                    break;
                }
                if (nosIdAdicionados.contains(arestas.get(j).getDestino().getId())
                        && !nosIdAdicionados.contains(arestas.get(j).getOrigem().getId())) {
                    arestasAdicionadas.add(arestas.get(j));
                    nosAdicionados.add(arestas.get(j).getOrigem());
                    nosIdAdicionados.add(arestas.get(j).getOrigem().getId());
                    arestas.remove(j);
                    break;
                }
            }
        }
        Grafo grafoPrim = new Grafo(grafoml.getId(), grafoml.getEdgedefault(), nos, arestasAdicionadas);
        return grafoPrim;
    }
}
