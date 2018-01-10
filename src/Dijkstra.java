
import Modelos.Vertice;
import Modelos.Grafo;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class Dijkstra {

    private Grafo grafo;
    private HashMap<String, Float> distancias;

    public Dijkstra(Grafo grafo) {
        this.grafo = grafo;
        this.distancias = new HashMap();
    }

    public Dijkstra() {

    }

    public void execute(Vertice source) {
        int i;

        for (i = 0; i < this.grafo.getVertices().size(); i++) {
            this.distancias.put(grafo.getVertices().get(i).getId(), Float.MAX_VALUE);
        }

        int indice = Integer.parseInt(this.grafo.buscaVertice(source.getId()).getId());
        this.distancias.put(this.grafo.getVertices().get(indice).getId(), 0F);
        ArrayList<Vertice> nosJaVisitados = new ArrayList();
        nosJaVisitados.add(this.grafo.getVertices().get(indice));
        visitarTodosNos(this.grafo.getVertices().get(indice).getId(), 0, nosJaVisitados);
    }

    private int calcularDistancia(String inicioId, String alvoId) {
        int i;
        for (i = 0; i < this.grafo.getArestas().size(); i++) {
            if (this.grafo.isOrientado()) {
                if (this.grafo.getArestas().get(i).getOrigem().getId().equals(inicioId) && this.grafo.getArestas().get(i).getDestino().getId().equals(alvoId)) {
                    return this.grafo.getArestas().get(i).getPeso();
                }
            } else if ((this.grafo.getArestas().get(i).getOrigem().getId().equals(inicioId) && this.grafo.getArestas().get(i).getDestino().getId().equals(alvoId))
                    || (this.grafo.getArestas().get(i).getOrigem().getId().equals(alvoId) && this.grafo.getArestas().get(i).getDestino().getId().equals(inicioId))) {
                return this.grafo.getArestas().get(i).getPeso();
            }
        }
        return Integer.MAX_VALUE;
    }

    private void visitarTodosNos(String noId, float distanciaAnterior, ArrayList<Vertice> nosJaVisitados) {

        ArrayList<Vertice> alcancaveis;
        int indice, i;
        indice = Integer.parseInt(this.grafo.buscaVertice(noId).getId());
        alcancaveis = this.grafo.getListaAdjacencia(this.grafo.getVertices().get(indice));
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

    public float menorDistancia(Vertice destino) {
        if (this.distancias.get(destino.getId()) != null) {
            return this.distancias.get(destino.getId());
        } else {
            return -1;
        }
    }

    public HashMap getDistancias() {
        return this.distancias;
    }

    public Grafo getGraph() {
        return this.grafo;
    }

}
