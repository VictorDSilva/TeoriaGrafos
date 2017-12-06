

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class Dijkstra {

    private Graph graph;
    private HashMap<String, Float> distancias;

    public Dijkstra(Graph graph) {
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

        int indice = this.graph.getIndiceNodePorId(source.getId());
        this.distancias.put(this.graph.getNodes().get(indice).getId(), 0F);
        ArrayList<Node> nosJaVisitados = new ArrayList();
        nosJaVisitados.add(this.graph.getNodes().get(indice));
        visitarTodosNos(this.graph.getNodes().get(indice).getId(), 0, nosJaVisitados);
    }

    private float calcularDistancia(String inicioId, String alvoId) {
        int i;
        for (i = 0; i < this.graph.getEdges().size(); i++) {
            if (this.graph.getEdges().get(i).getDirected()) {
                if (this.graph.getEdges().get(i).getSource().getId().equals(inicioId) && this.graph.getEdges().get(i).getTarget().getId().equals(alvoId)) {
                    return Float.parseFloat(this.graph.getEdges().get(i).getData().getData());
                }
            } else if ((this.graph.getEdges().get(i).getSource().getId().equals(inicioId) && this.graph.getEdges().get(i).getTarget().getId().equals(alvoId))
                    || (this.graph.getEdges().get(i).getSource().getId().equals(alvoId) && this.graph.getEdges().get(i).getTarget().getId().equals(inicioId))) {
                return Float.parseFloat(this.graph.getEdges().get(i).getData().getData());
            }
        }
        return Float.MAX_VALUE;
    }

    private void visitarTodosNos(String noId, float distanciaAnterior, ArrayList<Node> nosJaVisitados) {

        ArrayList<Node> alcancaveis;
        int indice, i;
        indice = this.graph.getIndiceNodePorId(noId);
        alcancaveis = this.graph.getNosAdjacentes(this.graph.getNodes().get(indice));
        alcancaveis.removeAll(nosJaVisitados);

        for (i = 0; i < alcancaveis.size(); i++) {
            float distancia = calcularDistancia(noId, alcancaveis.get(i).getId());

            if (distancia+distanciaAnterior < this.distancias.get(alcancaveis.get(i).getId())) {
                this.distancias.put(alcancaveis.get(i).getId(), distancia+distanciaAnterior);
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

    public Graph getGraph() {
        return this.graph;
    }

}
