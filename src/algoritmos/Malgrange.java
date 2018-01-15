package algoritmos;

import java.util.List;
import java.util.Map;
import modelo.Grafo;

public class Malgrange {

    Grafo graph = null;

    public Malgrange(Grafo grafo) {
        this.graph = grafo;
    }

    public List<String> malgrangeTransitivoDireto(int linha, String primeiroNo, List<String> visitados, Map<Integer, String> graphNodes, int[][] matrizAdjacencia) {
        int counter = 0;
        while (linha < this.graph.getNodes().size()) {
            while (counter < this.graph.getNodes().size()) {
                if (visitados.contains(primeiroNo)) {
                    visitados.remove(primeiroNo);
                    return visitados;
                }
                if (matrizAdjacencia[linha][counter] == 1) {
                    visitados.add(graphNodes.get(counter));
                    if (this.graph.possuiElementoRepetido(visitados)) {
                        visitados.remove(graphNodes.get(counter));
                        return visitados;
                    }
                    malgrangeTransitivoDireto(counter, primeiroNo, visitados, graphNodes, matrizAdjacencia);
                }
                counter++;
            }
            return visitados;
        }
        return visitados;
    }

    public List<String> malgrangeTransitivoInverso(int linha, String primeiroNo, List<String> nosVisitados, Map<Integer, String> nosDoGrafo, int[][] matrizAdjacencia) {
        int counter = 0;
        while (linha <  this.graph.getNodes().size()) {
            while (counter <  this.graph.getNodes().size()) {
                if (nosVisitados.contains(primeiroNo)) {
                    nosVisitados.remove(primeiroNo);
                    return nosVisitados;
                }
                if (matrizAdjacencia[counter][linha] == 1) {
                    nosVisitados.add(nosDoGrafo.get(counter));
                    if ( this.graph.possuiElementoRepetido(nosVisitados)) {
                        nosVisitados.remove(nosDoGrafo.get(counter));
                        return nosVisitados;
                    }
                    malgrangeTransitivoInverso(counter, primeiroNo, nosVisitados, nosDoGrafo, matrizAdjacencia);
                }
                counter++;
            }
            return nosVisitados;
        }
        return nosVisitados;
    }

}
