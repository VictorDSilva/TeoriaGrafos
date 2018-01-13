package algoritmos;

import java.util.ArrayList;
import java.util.HashMap;
import modelo.Grafo;
import modelo.Graphml;
import modelo.Node;

public class Dijkstra {

    private Grafo graph;
    private HashMap<String, Float> distancias;

    public Dijkstra(Grafo graph) {
        this.graph = graph;
        this.distancias = new HashMap();
    }

    public Dijkstra(Graphml graphml) {
        this.graph = graphml.getGraph();
        this.distancias = new HashMap();
    }

    public Dijkstra() {

    }

    public void execute(Node source) {
        int i;

        for (i = 0; i < this.graph.getNodes().size(); i++) {
            this.distancias.put(graph.getNodes().get(i).getId(), Float.MAX_VALUE);
        }

        int indice = this.graph.getNodeIndice(source.getId());
        this.distancias.put(this.graph.getNodes().get(indice).getId(), 0F);
        ArrayList<Node> nosJaVisitados = new ArrayList();
        nosJaVisitados.add(this.graph.getNodes().get(indice));
        visitarTodosNos(this.graph.getNodes().get(indice).getId(), 0, nosJaVisitados);
    }

    private float calcularDistancia(String inicioId, String alvoId) {
        int i;
        for (i = 0; i < this.graph.getEdges().size(); i++) {
            if (this.graph.getEdges().get(i).getDirected()) {
                if (this.graph.getEdges().get(i).getOrigem().getId().equals(inicioId) && this.graph.getEdges().get(i).getDestino().getId().equals(alvoId)) {
                    return this.graph.getEdges().get(i).getPeso();
                }
            } else if ((this.graph.getEdges().get(i).getOrigem().getId().equals(inicioId) && this.graph.getEdges().get(i).getDestino().getId().equals(alvoId))
                    || (this.graph.getEdges().get(i).getOrigem().getId().equals(alvoId) && this.graph.getEdges().get(i).getDestino().getId().equals(inicioId))) {
                return this.graph.getEdges().get(i).getPeso();
            }
        }
        return Float.MAX_VALUE;
    }

    private void visitarTodosNos(String noId, float distanciaAnterior, ArrayList<Node> nosJaVisitados) {

        ArrayList<Node> alcancaveis;
        int indice, i;
        indice = this.graph.getNodeIndice(noId);
        alcancaveis = this.graph.getNodeAdjacentes(this.graph.getNodes().get(indice));
        alcancaveis.removeAll(nosJaVisitados);

        for (i = 0; i < alcancaveis.size(); i++) {
            float distancia = calcularDistancia(noId, alcancaveis.get(i).getId());

            if (distancia + distanciaAnterior < this.distancias.get(alcancaveis.get(i).getId())) {
                this.distancias.put(alcancaveis.get(i).getId(), distancia + distanciaAnterior);
            }
        }

        for (i = 0; i < alcancaveis.size(); i++) {
            nosJaVisitados.add(alcancaveis.get(i));
            visitarTodosNos(alcancaveis.get(i).getId(), this.distancias.get(alcancaveis.get(i).getId()), nosJaVisitados);
            nosJaVisitados.remove(alcancaveis.get(i));
        }
    }

    public float menorDistancia(Node destino) {
        if (this.distancias.get(destino.getId()) != null) {
            return this.distancias.get(destino.getId());
        } else {
            return -1;
        }
    }

    public HashMap getDistancias() {
        return this.distancias;
    }

    public Grafo getGrafo() {
        return this.graph;
    }
}
