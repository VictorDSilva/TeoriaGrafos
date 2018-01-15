package algoritmos;

import java.util.ArrayList;
import java.util.HashMap;
import modelo.Grafo;
import modelo.Node;

public class Dijkstra {

    private Grafo grafo;
    private HashMap<String, Float> distancias;

    public Dijkstra(Grafo graph) {
        this.grafo = graph;
        this.distancias = new HashMap();
    }

    public void buscarCaminhos(Node source) {
        ArrayList<Node> nosJaVisitados = new ArrayList();
        int index = this.grafo.getNodeIndice(source.getId());

        for (int j = 0; j < this.grafo.getNodes().size(); j++) {
            this.distancias.put(grafo.getNodes().get(j).getId(), Float.MAX_VALUE);
        }
        this.distancias.put(this.grafo.getNodes().get(index).getId(), 0F);
        nosJaVisitados.add(this.grafo.getNodes().get(index));
        visitarTodosNos(this.grafo.getNodes().get(index).getId(), 0, nosJaVisitados);
    }

    private void visitarTodosNos(String noId, float distanciaAnterior, ArrayList<Node> nosJaVisitados) {
        ArrayList<Node> alcancaveis;
        int index, i;
        index = this.grafo.getNodeIndice(noId);
        alcancaveis = this.grafo.getNodeAdjacentes(this.grafo.getNodes().get(index));
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

    private float calcularDistancia(String inicioId, String alvoId) {
        int i;
        for (i = 0; i < this.grafo.getEdges().size(); i++) {
            if (this.grafo.getEdges().get(i).getDirected()) {
                if (this.grafo.getEdges().get(i).getOrigem().getId().equals(inicioId) 
                        && this.grafo.getEdges().get(i).getDestino().getId().equals(alvoId)) {
                    return this.grafo.getEdges().get(i).getPeso();
                }
            } else if ((this.grafo.getEdges().get(i).getOrigem().getId().equals(inicioId) 
                    && this.grafo.getEdges().get(i).getDestino().getId().equals(alvoId))
                    || (this.grafo.getEdges().get(i).getOrigem().getId().equals(alvoId) 
                    && this.grafo.getEdges().get(i).getDestino().getId().equals(inicioId))) {
                return this.grafo.getEdges().get(i).getPeso();
            }
        }
        return Float.MAX_VALUE;
    }

    public float getMenorDistancia(Node destino) {
        if (this.distancias.get(destino.getId()) != null) {
            return this.distancias.get(destino.getId());
        } else {
            return -1;
        }
    }

    public Grafo getGrafo() {
        return this.grafo;
    }
}
